package ee.tty.utils;

import com.sun.xml.internal.txw2.output.IndentingXMLStreamWriter;
import ee.tty.model.Situation;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by ovek on 1.09.2015.
 */
public class SituationChangesWriter {
    public static void writeSituationChangesXml(List<Situation> sits) {
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        try {
            XMLStreamWriter writer = new IndentingXMLStreamWriter(factory.createXMLStreamWriter(new FileWriter(UtilsClass.getPropValue("situationChangesXml"))));
            try {
                UtilsClass.deleteFile(UtilsClass.getPropValue("situationChangesXml"));
                boolean redForces = false;
                boolean blueForces = false;

                writer.writeStartDocument();
                writer.writeStartElement("situation");
                writer.writeStartElement("redforces");

                for (Situation sit : sits) {
                    if (sit.getType() != null && sit.getType().contains("red")) {
                        redForces = true;
                        blueForces = false;
                    }

                    if (sit.getType() != null && sit.getType().contains("blue")) {
                        if (redForces && !blueForces) {
                            writer.writeEndElement();
                            writer.writeStartElement("blueforces");
                            redForces = false;
                        }
                        blueForces = true;
                        redForces = false;
                    }

                    writeObjectElement(writer, sit);
                }
                writer.writeEndElement();
                writer.writeEndElement();
            } finally {
                if (writer != null)
                    writer.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeObjectElement(XMLStreamWriter writer, Situation sit) {
        try {
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(5);
            df.setMinimumFractionDigits(0);
            df.setGroupingUsed(false);

            writer.writeStartElement("object");
            writer.writeAttribute("name", sit.getName());
            writer.writeAttribute("type", sit.getType());
            writer.writeEmptyElement("latlng");
            writer.writeAttribute("lonX", df.format(sit.getLongitude()));
            writer.writeAttribute("latY", df.format(sit.getLatitude()));
            writer.writeEmptyElement("description");
            writer.writeStartElement("properties");

            df.setMaximumFractionDigits(2);

            writer.writeEmptyElement("property");
            writer.writeAttribute("name", "Altitude");
            writer.writeAttribute("type", "number");
            writer.writeAttribute("value", df.format(sit.getAltitude()));
            writer.writeEndElement();

            writer.writeEmptyElement("property");
            writer.writeAttribute("name", "Threat level");
            writer.writeAttribute("type", "number");
            writer.writeAttribute("value", df.format(sit.getThreatLevel()));
            writer.writeEndElement();

            writer.writeEmptyElement("property");
            writer.writeAttribute("name", "Danger zone radius");
            writer.writeAttribute("type", "number");
            writer.writeAttribute("value", df.format(sit.getDangerZoneRadius()));
            writer.writeEndElement();

            writer.writeEndElement();
            writer.writeEndElement();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }


    }

}
