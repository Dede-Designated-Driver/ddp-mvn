package org.dedriver.model;

public class Address {
    private final String port;
    private final String route;
    private final String url;

    public Address(String port, String route, String url) {
        this.port = port;
        this.route = route;
        this.url = url;
    }

    @Override
    public String toString() {
        return "Address{" +
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
