package com.saih.pojo;

import java.math.BigDecimal;

public class Station {

    private Integer stationID;
    private String stationName;
    private String stationAddress;
    private BigDecimal stationLongitude;

    @Override
    public String toString() {
        return "Station{" +
                "stationID=" + stationID +
                ", stationName='" + stationName + '\'' +
                ", stationAddress='" + stationAddress + '\'' +
                ", stationLongitude=" + stationLongitude +
                ", stationLatitude=" + stationLatitude +
                ", remarks='" + remarks + '\'' +
                '}';
    }

    private BigDecimal stationLatitude;
    private String remarks;

    public Station() {
    }

    public Station(Integer stationID, String stationName, String stationAddress, BigDecimal stationLongitude, BigDecimal stationLatitude, String remarks) {
        this.stationID = stationID;
        this.stationName = stationName;
        this.stationAddress = stationAddress;
        this.stationLongitude = stationLongitude;
        this.stationLatitude = stationLatitude;
        this.remarks = remarks;
    }

    public Integer getStationID() {
        return stationID;
    }

    public void setStationID(Integer stationID) {
        this.stationID = stationID;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationAddress() {
        return stationAddress;
    }

    public void setStationAddress(String stationAddress) {
        this.stationAddress = stationAddress;
    }

    public BigDecimal getStationLongitude() {
        return stationLongitude;
    }

    public void setStationLongitude(BigDecimal stationLongitude) {
        this.stationLongitude = stationLongitude;
    }

    public BigDecimal getStationLatitude() {
        return stationLatitude;
    }

    public void setStationLatitude(BigDecimal stationLatitude) {
        this.stationLatitude = stationLatitude;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
