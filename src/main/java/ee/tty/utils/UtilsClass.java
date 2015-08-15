package ee.tty.utils;

import java.io.*;
import java.util.Properties;

/**
 * Created by ovek on 15.08.2015.
 */
public class UtilsClass {
    public static String getPropValue(String propName) {
        String result = "";

        try {
            Properties prop = new Properties();
            String propFileName = "resource.properties";

            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(propFileName);
            if(inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("Property file: " + propFileName + " not found!");
            }

            result = prop.getProperty(propName);
            return result;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }

    public static String readXmlFromFile(String location) {
        try {
            File file = new File(location);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            StringBuilder sb = new StringBuilder();

            while((line=br.readLine()) != null) {
                sb.append(line.trim());
            }

            return sb.toString();
        } catch (Exception e) {
            System.out.println("Exception e: " + e.getMessage());
        }
        return null;
    }
}
