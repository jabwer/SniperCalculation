package ee.tty.utils;

import com.sun.xml.internal.txw2.output.IndentingXMLStreamWriter;
import ee.tty.model.RoutingInfo;
import ee.tty.model.Segment;
import ee.tty.model.Vertex;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by ovek on 5.09.2015.
 */
public class RoutingChangesWriter {
    public static void writeRoutingChangesXml(List<Segment> segs, List<Vertex> verts) {
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(5);
        df.setMinimumFractionDigits(0);
        df.setGroupingUsed(false);
        try {
        	FileOutputStream outputStream = new FileOutputStream(UtilsClass.getPropValue("routingChangesXml"));
        	XMLStreamWriter writer = new IndentingXMLStreamWriter(factory.createXMLStreamWriter(outputStream));
            UtilsClass.deleteFile(UtilsClass.getPropValue("routingChangesXml"));
            try {
                writer.writeStartDocument();
                writer.writeStartElement("routing");
                if(verts != null && verts.size() > 0) {
                    writer.writeStartElement("vertices");
                    for(Vertex ver : verts) {
                        writer.writeStartElement("vertex");
                        writer.writeAttribute("ID",ver.getId());
                        writer.writeEmptyElement("latlng");
                        writer.writeAttribute("lonX", df.format(ver.getLonX()).replace(',','.'));
                        writer.writeAttribute("latY", df.format(ver.getLatY()).replace(',','.'));
                        writer.writeEmptyElement("description");
                        writeWeights(writer, ver, df);
                        writer.writeEndElement();
                    }
                    writer.writeEndElement();
                }

                if(segs != null && segs.size() > 0) {
                    writer.writeStartElement("segments");
                    for(Segment seg:segs) {
                        writer.writeStartElement("segment");
                        writer.writeAttribute("ID", seg.getId());
                        writer.writeAttribute("start", seg.getStart());
                        writer.writeAttribute("end", seg.getEnd());
                        writer.writeAttribute("type", seg.getType());

                        writer.writeEmptyElement("description");
                        writeWeights(writer, seg, df);
                        writer.writeEndElement();
                    }
                }

                writer.writeEndElement();
                writer.writeEndElement();
                writer.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                writer.close();
                outputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeWeights(XMLStreamWriter writer, RoutingInfo rout, DecimalFormat df) {
        try {
            writer.writeStartElement("weights");

            writer.writeEmptyElement("weight");
            writer.writeAttribute("ID", "1");
            writer.writeAttribute("name", "Length");
            writer.writeAttribute("type", "number");
            writer.writeAttribute("value", df.format(rout.getLength()).replace(',','.'));

            writer.writeEmptyElement("weight");
            writer.writeAttribute("ID", "2");
            writer.writeAttribute("name", "Width");
            writer.writeAttribute("type", "number");
            writer.writeAttribute("value", df.format(rout.getWidth()).replace(',','.'));

            writer.writeEmptyElement("weight");
            writer.writeAttribute("ID", "3");
            writer.writeAttribute("name", "Ground type");
            writer.writeAttribute("type", "string");
            writer.writeAttribute("value", rout.getGroundType());

            writer.writeEmptyElement("weight");
            writer.writeAttribute("ID", "4");
            writer.writeAttribute("name", "Environment");
            writer.writeAttribute("type", "string");
            writer.writeAttribute("value", rout.getEnvironment());

            writer.writeEmptyElement("weight");
            writer.writeAttribute("ID", "5");
            writer.writeAttribute("name", "Road type");
            writer.writeAttribute("type", "string");
            writer.writeAttribute("value", rout.getRoadType());

            writer.writeEmptyElement("weight");
            writer.writeAttribute("ID", "6");
            writer.writeAttribute("name", "Road infrastructure");
            writer.writeAttribute("type", "string");
            writer.writeAttribute("value", rout.getRoadInfrastructure());

            writer.writeEmptyElement("weight");
            writer.writeAttribute("ID", "7");
            writer.writeAttribute("name", "MaxSpeed");
            writer.writeAttribute("type", "number");
            writer.writeAttribute("value", df.format(rout.getMaxSpeed()).replace(',','.'));

            writer.writeEmptyElement("weight");
            writer.writeAttribute("ID", "8");
            writer.writeAttribute("name", "MaxBearingCapacity");
            writer.writeAttribute("type", "number");
            writer.writeAttribute("value", df.format(rout.getMaxBearingCapacity()).replace(',','.'));

            writer.writeEmptyElement("weight");
            writer.writeAttribute("ID", "9");
            writer.writeAttribute("name", "Units hostility");
            writer.writeAttribute("type", "string");
            writer.writeAttribute("value", rout.getUnitsHostility());

            writer.writeEmptyElement("weight");
            writer.writeAttribute("ID", "10");
            writer.writeAttribute("name", "Altitude");
            writer.writeAttribute("type", "number");
            writer.writeAttribute("value", df.format(rout.getAltitude()).replace(',','.'));

            writer.writeEmptyElement("weight");
            writer.writeAttribute("ID", "11");
            writer.writeAttribute("name", "Threat level");
            writer.writeAttribute("type", "number");
            writer.writeAttribute("value", df.format(rout.getThreatLevel()).replace(',','.'));

            writer.writeEndElement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
