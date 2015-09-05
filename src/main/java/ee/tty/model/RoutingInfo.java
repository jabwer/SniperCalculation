package ee.tty.model;

import java.math.BigDecimal;

/**
 * Created by ovek on 16.08.2015.
 */
public class RoutingInfo {
    private String id;
    private BigDecimal length;
    private BigDecimal width;
    private String groundType;
    private String environment;
    private String roadType;
    private String roadInfrastructure;
    private BigDecimal maxSpeed;
    private BigDecimal maxBearingCapacity;
    private String unitsHostility;
    private BigDecimal altitude;
    private Integer threatLevel;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public String getGroundType() {
        return groundType;
    }

    public void setGroundType(String groundType) {
        this.groundType = groundType;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getRoadType() {
        return roadType;
    }

    public void setRoadType(String roadType) {
        this.roadType = roadType;
    }

    public String getRoadInfrastructure() {
        return roadInfrastructure;
    }

    public void setRoadInfrastructure(String roadInfrastructure) {
        this.roadInfrastructure = roadInfrastructure;
    }

    public BigDecimal getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(BigDecimal maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public BigDecimal getMaxBearingCapacity() {
        return maxBearingCapacity;
    }

    public void setMaxBearingCapacity(BigDecimal maxBearingCapacity) {
        this.maxBearingCapacity = maxBearingCapacity;
    }

    public String getUnitsHostility() {
        return unitsHostility;
    }

    public void setUnitsHostility(String unitsHostility) {
        this.unitsHostility = unitsHostility;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "RoutingInfo{" +
                "id='" + id + '\'' +
                ", length=" + length +
                ", width=" + width +
                ", groundType='" + groundType + '\'' +
                ", environment='" + environment + '\'' +
                ", roadType='" + roadType + '\'' +
                ", roadInfrastructure='" + roadInfrastructure + '\'' +
                ", maxSpeed=" + maxSpeed +
                ", maxBearingCapacity=" + maxBearingCapacity +
                ", unitsHostility='" + unitsHostility + '\'' +
                ", altitude=" + altitude +
                ", threatLevel=" + threatLevel +
                ", description='" + description + '\'' +
                '}';
    }
}
