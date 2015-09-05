package ee.tty.model;

/**
 * Created by ovek on 16.08.2015.
 */
public class Segment extends RoutingInfo{
    private String start;
    private String end;
    private String type;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getStartDouble() {
        return Double.parseDouble(this.start);
    }

    public double getEndDouble() {
        return Double.parseDouble(this.end);
    }

    @Override
    public String toString() {
        return "Segment{" +
                "start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
