package org.dedriver;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.dedriver.model.Adr;
import org.dedriver.model.Msg;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.Scanner;

class TxImpl implements Tx {

    private final static Logger LOG = LoggerFactory.getLogger(TxImpl.class);

    public TxImpl() {
    }

    @Override
    public void send(final Msg msg, final Adr adr) {
        KeyStore trustStore =
                null;
        try {
            trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        try {
            if (trustStore != null) {
                trustStore.load(null, null);
            }
        } catch (IOException e) {
            LOG.error("IOException, message: "
                    + e.getMessage() + ", trace: "
                    + Arrays.toString(e.getStackTrace()));
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            LOG.error("NoSuchAlgorithmException, message: "
                    + e.getMessage() + ", trace: "
                    + Arrays.toString(e.getStackTrace()));
            e.printStackTrace();
        } catch (CertificateException e) {
            LOG.error("CertificateException, message: "
                    + e.getMessage() + ", trace: "
                    + Arrays.toString(e.getStackTrace()));
            e.printStackTrace();
        }

        SSLSocketFactory sf = null;
        try {
            sf = new CustomSSLSocketFactory(trustStore);
        } catch (NoSuchAlgorithmException e) {
            LOG.error("NoSuchAlgorithmException, message: "
                    + e.getMessage() + ", trace: "
                    + Arrays.toString(e.getStackTrace()));
            e.printStackTrace();
        } catch (KeyManagementException e) {
            LOG.error("KeyManagementException, message: "
                    + e.getMessage() + ", trace: "
                    + Arrays.toString(e.getStackTrace()));
            e.printStackTrace();
        } catch (KeyStoreException e) {
            LOG.error("KeyStoreException, message: "
                    + e.getMessage() + ", trace: "
                    + Arrays.toString(e.getStackTrace()));
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            LOG.error("UnrecoverableKeyException, message: "
                    + e.getMessage() + ", trace: "
                    + Arrays.toString(e.getStackTrace()));
            e.printStackTrace();
        }
        if (sf != null) {
            sf.setHostnameVerifier(
                    SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        }

        HttpParams params = new BasicHttpParams();
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("http",
                PlainSocketFactory.getSocketFactory(), 80));
        registry.register(new Scheme("https", sf, 443));

        ClientConnectionManager ccm =
                new ThreadSafeClientConnManager(params, registry);

        HttpClient client = new DefaultHttpClient(ccm, params);

        String address = adr.getUrl() + ":" + adr.getPort() + adr.getRoute();
        LOG.debug("address: " + address);

        //create a HTTP POST request
        //use web service endpoint or web site page as url
        HttpPost post =
                null;
        try {
            post = new HttpPost(new URI(address));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        //set request headers for request data in JSON format
        Header[] headers = {
                new BasicHeader("Content-type", "application/json"),
        };
        //the request payload is in JSON format

        //set request headers
        if (post != null) {
            post.setHeaders(headers);
        }

        //create payload
        //create request data in JSON format
        //create JSON object
        JSONObject payload = new JSONObject();
        try {
            payload.put("uuid", msg.getId());
            payload.put("latitude", msg.getLat());
            payload.put("longitude", msg.getLon());
            payload.put("timestamp", msg.getTs());
            payload.put("alias", msg.getAlias());
            payload.put("vehicle", msg.getType());
        } catch (JSONException e) {
            LOG.error("JSON error detected, message: "
                    + e.getMessage() + ", trace: "
                    + Arrays.toString(e.getStackTrace()));
            e.printStackTrace();
        }

        //set the payload
        HttpEntity entity;
        entity = new ByteArrayEntity(payload.toString().getBytes(StandardCharsets.UTF_8));
        if (post != null) {
            post.setEntity(entity);
        }

        //send request
        HttpResponse response = null;
        try {
            response = client.execute(post);
        } catch (IOException e) {
            LOG.error("IO error detected, message: "
                    + e.getMessage() + ", trace: "
                    + Arrays.toString(e.getStackTrace()));
            e.printStackTrace();
        }

        //read response status
        Scanner sc = null;
        if (response != null) {
            try {
                sc = new Scanner(response.getEntity().getContent());
            } catch (IOException e) {
                LOG.error("IO error detected, message: "
                        + e.getMessage() + ", trace: "
                        + Arrays.toString(e.getStackTrace()));
                e.printStackTrace();
            }
        }

        //print status line
        if (response != null) {
            LOG.debug("status line: " + response.getStatusLine());
        }
        if (sc != null) {
            while (sc.hasNext()) {
                LOG.debug("status line: " + sc.nextLine());
            }
        }

        //close interaction
        try {
            if (response != null) {
                response.getEntity().getContent().close();
            }
        } catch (IOException e) {
            LOG.error("IO error detected, message: "
                    + e.getMessage() + ", trace: "
                    + Arrays.toString(e.getStackTrace()));
            e.printStackTrace();
        }

        //verify response
        int responseCode = 0;
        if (response != null) {
            responseCode = response.getStatusLine().getStatusCode();
            LOG.debug("responseCode: " + responseCode);
        }
        String statusPhrase = null;
        if (response != null) {
            statusPhrase = response.getStatusLine().getReasonPhrase();
            LOG.debug("statusPhrase: " + statusPhrase);
        }
    }
}
