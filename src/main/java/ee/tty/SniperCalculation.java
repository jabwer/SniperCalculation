package ee.tty;

import ee.tty.model.Segment;
import ee.tty.model.Situation;
import ee.tty.model.Vertex;
import ee.tty.utils.RoutingHandler;
import ee.tty.utils.SituationChangesWriter;
import ee.tty.utils.SituationHandler;
import ee.tty.utils.UtilsClass;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ovek on 10.08.2015.
 */
public class SniperCalculation {
    public static void main(String[] args) {
        System.out.println("Main class");

        try {
            String xmlLocation = UtilsClass.getPropValue("situationXml");

            String situationXml = UtilsClass.readXmlFromFile(xmlLocation);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            SituationHandler handler = new SituationHandler();
            InputStream inputStream = new ByteArrayInputStream(situationXml.getBytes(Charset.forName("UTF-8")));
            saxParser.parse(inputStream, handler);
            List<Situation> situations = handler.situations;


            xmlLocation = UtilsClass.getPropValue("routingXml");
            String routingXml = UtilsClass.readXmlFromFile(xmlLocation);
            RoutingHandler rHandler = new RoutingHandler();
            inputStream = new ByteArrayInputStream(routingXml.getBytes(Charset.forName("UTF-8")));
            saxParser.parse(inputStream, rHandler);
            List<Segment> segments = rHandler.segments;
            List<Vertex> vertices = rHandler.vertices;

            List<Vertex> changedVertex = new ArrayList<Vertex>();
            List<Segment> changedSegment = new ArrayList<Segment>();
            Integer maxThreatLevel = Integer.parseInt(UtilsClass.getPropValue("maxThreatLevel"));

            for(Situation situation:situations) {
                situation.setDangerZoneRadius(Integer.parseInt(UtilsClass.getPropValue("sniperRange")));
                for (Vertex vertex : vertices) {
                    if (UtilsClass.isInDistanceFromSniper(situation.getLatitude().doubleValue(),
                            situation.getLongitude().doubleValue(),vertex.getLatY().doubleValue(), vertex.getLonX().doubleValue())) {
                        vertex.setThreatLevel(maxThreatLevel);
                        if(!changedVertex.contains(vertex))
                            changedVertex.add(vertex);
                        for(Segment segment:segments) {
                            if(segment.getStart().equals(vertex.getId()) || segment.getEnd().equals(vertex.getId())) {
                                segment.setThreatLevel(maxThreatLevel);
                                changedSegment.add(segment);
                            }
                        }
                    }
                }
            }

            SituationChangesWriter.writeSituationChangesXml(situations);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
