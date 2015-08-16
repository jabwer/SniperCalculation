package ee.tty.utils;

import ee.tty.model.RoutingInfo;
import ee.tty.model.Segment;
import ee.tty.model.Vertex;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by ovek on 16.08.2015.
 */
public class RoutingHandler extends DefaultHandler {
    public List<Segment> segments = new ArrayList<Segment>();
    public List<Vertex> vertices = new ArrayList<Vertex>();
    private Stack<String> elementStack = new Stack<String>();
    private Stack<Object> objectStack = new Stack<Object>();
    private boolean bSegment = false;
    private boolean bVertex = false;

    public RoutingHandler() {
    }

    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) throws SAXException {
        this.elementStack.push(qName);

        if("vertices".equals(currentElement())) {
            bVertex = true;
            bSegment = false;
        } else if ("segments".equals(currentElement())) {
            bVertex = false;
            bSegment = true;
        } else if("segment".equals(currentElement())) {
            Segment seg = new Segment();
            String id = attributes.getValue("ID");
            String start = attributes.getValue("start");
            String end = attributes.getValue("end");
            String type = attributes.getValue("type");
            seg.setId(id);
            seg.setStart(start);
            seg.setEnd(end);
            seg.setType(type);
            this.objectStack.push(seg);
        } else if ("vertex".equals(currentElement())) {
            Vertex ver = new Vertex();
            String id = attributes.getValue("ID");
            ver.setId(id);
            this.objectStack.push(ver);
        } else if ("latlng".equals(currentElement())) {
            Vertex ver = (Vertex)this.objectStack.peek();
            String latY = attributes.getValue("latY");
            String lonX = attributes.getValue("lonX");
            ver.setLatY(new BigDecimal(latY));
            ver.setLonX(new BigDecimal(lonX));
        } else if ("weight".equals(currentElement()) && "Length".equals(attributes.getValue("name"))) {
            String value = attributes.getValue("value");
            RoutingInfo rou = (RoutingInfo)this.objectStack.peek();
            rou.setLength(new BigDecimal(value));
        } else if ("weight".equals(currentElement()) && "Width".equals(attributes.getValue("name"))) {
            String value = attributes.getValue("value");
            RoutingInfo rou = (RoutingInfo)this.objectStack.peek();
            rou.setWidth(new BigDecimal(value));
        } else if ("weight".equals(currentElement()) && "Ground type".equals(attributes.getValue("name"))) {
            String value = attributes.getValue("value");
            RoutingInfo rou = (RoutingInfo)this.objectStack.peek();
            rou.setGroundType(value);
        } else if ("weight".equals(currentElement()) && "Environment".equals(attributes.getValue("name"))) {
            String value = attributes.getValue("value");
            RoutingInfo rou = (RoutingInfo)this.objectStack.peek();
            rou.setEnvironment(value);
        } else if ("weight".equals(currentElement()) && "Road type".equals(attributes.getValue("name"))) {
            String value = attributes.getValue("value");
            RoutingInfo rou = (RoutingInfo)this.objectStack.peek();
            rou.setRoadType(value);
        } else if ("weight".equals(currentElement()) && "Road infrastructure".equals(attributes.getValue("name"))) {
            String value = attributes.getValue("value");
            RoutingInfo rou = (RoutingInfo)this.objectStack.peek();
            rou.setRoadInfrastructure(value);
        } else if ("weight".equals(currentElement()) && "MaxSpeed".equals(attributes.getValue("name"))) {
            String value = attributes.getValue("value");
            RoutingInfo rou = (RoutingInfo)this.objectStack.peek();
            rou.setMaxSpeed(new BigDecimal(value));
        } else if ("weight".equals(currentElement()) && "MaxBearingCapacity".equals(attributes.getValue("name"))) {
            String value = attributes.getValue("value");
            RoutingInfo rou = (RoutingInfo)this.objectStack.peek();
            rou.setMaxBearingCapacity(new BigDecimal(value));
        } else if ("weight".equals(currentElement()) && "Units hostility".equals(attributes.getValue("name"))) {
            String value = attributes.getValue("value");
            RoutingInfo rou = (RoutingInfo)this.objectStack.peek();
            rou.setUnitsHostility(value);
        } else if ("weight".equals(currentElement()) && "Altitude".equals(attributes.getValue("name"))) {
            String value = attributes.getValue("value");
            RoutingInfo rou = (RoutingInfo)this.objectStack.peek();
            rou.setAltitude(new BigDecimal(value));
        } else if ("weight".equals(currentElement()) && "Threat level".equals(attributes.getValue("name"))) {
            String value = attributes.getValue("value");
            RoutingInfo rou = (RoutingInfo)this.objectStack.peek();
            rou.setThreatLevel(new Integer(value));
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(this.objectStack.isEmpty())
            return;

        if("vertex".equals(qName)) {
            Vertex ver = (Vertex)this.objectStack.peek();
            this.vertices.add(ver);
            this.objectStack.pop();
        } else if("segment".equals(qName)) {
            Segment seg = (Segment)this.objectStack.peek();
            this.segments.add(seg);
            this.objectStack.pop();
        }
    }

    public void characters(char ch[], int start, int length) throws SAXException {
    }

    private String currentElement() {
        if(!this.elementStack.isEmpty())
            return this.elementStack.peek();

        return null;
    }

    private boolean situationOnStack() {
        if(this.objectStack.empty())
            return false;

        if(this.objectStack.peek().getClass().equals(Segment.class))
            return true;

        return false;
    }
}
