package org.dedriver.model;

public class Adr {
    private final String port;
    private final String route;
    private final String url;

    public Adr(String port, String route, String url) {
        this.port = port;
        this.route = route;
        this.url = url;
    }

    @Override
    public String toString() {
        return "Adr{" +
                "port='" + port + '\'' +
                ", route='" + route + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String getPort() {
        return port;
    }

    public String getRoute() {
        return route;
    }

    public String getUrl() {
        return url;
    }
}
