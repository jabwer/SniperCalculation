package ee.tty.utils;

import ee.tty.model.Situation;
import jdk.internal.org.xml.sax.SAXException;
import jdk.internal.org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.util.List;
import java.util.Stack;
import java.util.jar.Attributes;

/**
 * Created by ovek on 10.08.2015.
 */
public class SituationSaxHandler extends DefaultHandler {
    public List<Situation> situations;
    private Stack<String> elementStack = new Stack<String>();
    private Stack<Object> objectStack = new Stack<Object>();
    private boolean redForces = false;
    private boolean blueForces = false;

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        this.elementStack.push(qName);

        if("redforces".equals(qName)) {
            redForces = true;
            blueForces = false;
        } else if("blueforces".equals(qName)) {
            redForces = false;
            blueForces = true;
        } else if("object".equals(qName) && redForces) {
            Situation sit = new Situation();
            String name = attributes.getValue("name");
            String type = attributes.getValue("type");
            sit.setName(name);
            sit.setType(type);
            this.objectStack.push(sit);
            this.situations.add(sit);
        } else if ("latlng".equals(qName) && redForces) {
            if(situationOnStack()) {
                String lonX = attributes.getValue("lonX");
                String latY = attributes.getValue("latY");
                Situation sit = (Situation)this.objectStack.peek();
                sit.setLatitude(new BigDecimal(latY));
                sit.setLongitude(new BigDecimal(lonX));
            }
        } else if ("property".equals(currentElement()) && "Altitude".equals(attributes.getValue("name"))) {
            if(situationOnStack()) {
                String value = attributes.getValue("value");
                Situation sit = (Situation)this.objectStack.peek();
                sit.setAltitude(new BigDecimal(value));
            }
        } else if ("property".equals(currentElement()) && "Threat level".equals(attributes.getValue("name"))) {
            if(situationOnStack()) {
                String value = attributes.getValue("value");
                Situation sit = (Situation)this.objectStack.peek();
                sit.setThreatLevel(new Integer(value));
            }
        } else if ("property".equals(currentElement()) && "Danger zone radius".equals(attributes.getValue("name"))) {
            if(situationOnStack()) {
                String value = attributes.getValue("value");
                Situation sit = (Situation)this.objectStack.peek();
                sit.setAltitude(new BigDecimal(value));
            }
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        this.elementStack.pop();
        if("redforces".equals(qName) && redForces) {
            redForces = false;
        } else if("blueforces".equals(qName) && blueForces) {
            blueForces = false;
        }
    }

    public void characters(char ch[], int start, int length) throws SAXException {
        String value = new String(ch, start, length).trim();
        if(value.length() == 0) return;
    }

    private String currentElement() {
        return this.elementStack.peek();
    }

    private boolean situationOnStack() {
        if(this.objectStack.empty())
            return false;

        if(this.objectStack.peek().getClass().isInstance(Situation.class))
            return true;

        return false;
    }

    /*public static void parseSituationXML() {
        try {
            File xmlFile = new File("C:\\CARDINAL_data_stationary\\tartu\\situation.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

            NodeList redForces = doc.getElementsByTagName("redforces");

            for(int i = 0; i < redForces.getLength(); i++) {
                Node nNode = redForces.item(i);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
