package ee.tty;

import ee.tty.utils.UtilsClass;

/**
 * Created by ovek on 10.08.2015.
 */
public class SniperCalculation {
    public static void main(String[] args) {
        System.out.println("Main class");

        try {
            String xmlLocation = UtilsClass.getPropValue("situationXml");

            String situationXml = UtilsClass.readXmlFromFile(xmlLocation);


        } catch (Exception e) {
            e.printStackTrace();
        }
        //XmlParser.parseSituationXML();
    }
}
