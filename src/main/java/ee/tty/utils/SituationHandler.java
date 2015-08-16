package ee.tty.utils;

import ee.tty.model.Situation;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by ovek on 10.08.2015.
 */
public class SituationHandler extends DefaultHandler {
    public List<Situation> situations = new ArrayList<Situation>();
    private Stack<String> elementStack = new Stack<String>();
    private Stack<Object> objectStack = new Stack<Object>();
    private boolean redForces = false;

    public SituationHandler() {
    }

    public void startElement(String uri, String localName,
                      String qName, Attributes attributes) throws SAXException {
        this.elementStack.push(qName);

        if("redforces".equals(qName)) {
            redForces = true;
        } else if("object".equals(qName) && redForces) {
            Situation sit = new Situation();
            String name = attributes.getValue("name");
            String type = attributes.getValue("type");
            sit.setName(name);
            sit.setType(type);
            this.objectStack.push(sit);
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
        if("redforces".equals(qName) && redForces) {
            redForces = false;
        }

        if(this.objectStack.isEmpty())
            return;

        if("object".equals(qName) && redForces && this.objectStack.peek().getClass().equals(Situation.class)) {
            situations.add((Situation)this.objectStack.peek());
            this.objectStack.pop();
        }
    }

    public void characters(char ch[], int start, int length) throws SAXException {
        String value = new String(ch, start, length).trim();
        if(value.length() == 0) return;
    }

    private String currentElement() {
        if(!this.elementStack.isEmpty())
            return this.elementStack.peek();

        return null;
    }

    private boolean situationOnStack() {
        if(this.objectStack.empty())
            return false;

        if(this.objectStack.peek().getClass().equals(Situation.class))
            return true;

        return false;
    }
}
