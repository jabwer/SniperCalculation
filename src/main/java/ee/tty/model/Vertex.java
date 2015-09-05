package ee.tty.model;

import java.math.BigDecimal;

/**
 * Created by ovek on 16.08.2015.
 */
public class Vertex extends RoutingInfo{
    private BigDecimal latY;
    private BigDecimal lonX;

    public BigDecimal getLatY() {
        return latY;
    }

    public void setLatY(BigDecimal latY) {
        this.latY = latY;
    }

    public BigDecimal getLonX() {
        return lonX;
    }

    public void setLonX(BigDecimal lonX) {
        this.lonX = lonX;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "latY=" + latY +
                ", lonX=" + lonX +
                '}';
    }
}
