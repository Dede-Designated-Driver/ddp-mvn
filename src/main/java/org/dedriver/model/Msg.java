package org.dedriver.model;

public class Msg {

    private final String id;
    private final String lat;
    private final String lon;
    private final String ts;
    private final String alias;
    private final String type;

    public Msg(String id, String lat, String lon, String ts, String alias, String type) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.ts = ts;
        this.alias = alias;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "id='" + id + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", ts='" + ts + '\'' +
                ", alias='" + alias + '\'' +
                ", type='" + type + '\'' +
                '}';
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
