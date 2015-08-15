package ee.tty.model;

import java.math.BigDecimal;

/**
 * Created by ovek on 10.08.2015.
 */
public class Situation {
    private String name;
    private String type;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private BigDecimal altitude;
    private Integer threatLevel;

    public Situation(){};

    public Situation(String name, String type, BigDecimal longitude, BigDecimal latitude, BigDecimal altitude, Integer threatLevel) {
        this.name = name;
        this.type = type;
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.threatLevel = threatLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getAltitude() {
        return altitude;
    }

    public void setAltitude(BigDecimal altitude) {
        this.altitude = altitude;
    }

    public Integer getThreatLevel() {
        return threatLevel;
    }

    public void setThreatLevel(Integer threatLevel) {
        this.threatLevel = threatLevel;
    }
}
