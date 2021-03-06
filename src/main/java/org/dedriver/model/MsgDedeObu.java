package org.dedriver.model;

import org.dedriver.utils.MsgValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static org.dedriver.utils.StringUtils.isNumeric;

public class MsgDedeObu {

    public final static int ID_MIN = 0;
    public final static int LAT_MIN = -90;
    public final static int LAT_MAX = 90;
    public final static int LAT_INVALID = 91;
    public final static int LON_MIN = -180;
    public final static int LON_MAX = 180;
    public final static int LON_INVALID = -181;
    private final static Logger LOG = LoggerFactory.getLogger(MsgDedeObu.class);

    private final String id;
    private final String lat;
    private final String lon;
    private final String ts;
    private final String alias;
    private final String type;

    public MsgDedeObu(String id, String lat, String lon, String ts, String alias, String type) {

        //validate id
        if (!isValidId(id)) {
            throw new IllegalArgumentException("id length must be greater than " + ID_MIN);
        }
        this.id = id;

        //validate lat
        if (!isValidLat(lat)) {
            throw new IllegalArgumentException("lat value must be between " + LAT_MIN + " and " + LAT_MAX);
        }
        this.lat = lat;

        //validate lon
        if (!isValidLon(lon)) {
            throw new IllegalArgumentException("lon value must be between " + LON_MIN + " and " + LON_MAX);
        }
        this.lon = lon;

        //validate ts
        if (!MsgValidation.isValidTs(ts)) {
            throw new IllegalArgumentException("ts value must be greater than " + MsgValidation.TS_MIN);
        }
        this.ts = ts;

        this.alias = alias;
        this.type = type;
    }

    /**
     * validate String
     *
     * @param string String to be validated
     * @return true if valid; false otherwise
     */
    public static boolean isValidString(String string) {
        if (string != null) {
            if (string.length() > ID_MIN) {
                return true;
            }
        }
        LOG.info("string: " + string + " is invalid");
        return false;
    }

    /**
     * validate identifier
     *
     * @param id identifier to be validated
     * @return true if valid; false otherwise
     */
    public static boolean isValidId(String id) {
        if (id != null) {
            if (id.length() > ID_MIN) {
                return true;
            }
        }
        LOG.info("id: " + id + " is invalid");
        return false;
    }

    /**
     * validate longitude
     *
     * @param lon longitude to be validated
     * @return true if valid; false otherwise
     */
    public static boolean isValidLon(String lon) {

        boolean lonIsNumeric = isNumeric(lon);
        float lonFloat = LON_INVALID;
        if (lonIsNumeric) {
            try {
                lonFloat = Float.parseFloat(lon);
            } catch (NumberFormatException e) {
                LOG.info("parsing failed, message: " + e.getMessage() + ", trace: " + Arrays.toString(e.getStackTrace()));
                return false;
            }
        }
        if (lonFloat >= LON_MIN && lonFloat <= LON_MAX) {
            return true;
        }
        LOG.info("lon: " + lon + " is invalid");
        return false;
    }

    /**
     * validate latitude
     *
     * @param lat latitude to be validated
     * @return true if valid; false otherwise
     */
    public static boolean isValidLat(String lat) {

        boolean latIsNumeric = isNumeric(lat);
        float latFloat = LAT_INVALID;
        if (latIsNumeric) {
            try {
                latFloat = Float.parseFloat(lat);
            } catch (NumberFormatException e) {
                LOG.info("parsing failed, message: " + e.getMessage() + ", trace: " + Arrays.toString(e.getStackTrace()));
                return false;
            }
        }
        if (latFloat >= LAT_MIN && latFloat <= LAT_MAX) {
            return true;
        }
        LOG.info("lat: " + lat + " is invalid");
        return false;
    }

    @Override
    public String toString() {
        return "MsgDedeObu{" + "id='" + id + '\'' + ", lat='" + lat + '\'' + ", lon='" + lon + '\'' + ", ts='" + ts + '\'' + ", alias='" + alias + '\'' + ", type='" + type + '\'' + '}';
    }

    public String getId() {
        return id;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public String getTs() {
        return ts;
    }

    public String getAlias() {
        return alias;
    }

    public String getType() {
        return type;
    }
}
