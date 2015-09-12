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
            br.close();
            return sb.toString();
        } catch (Exception e) {
            System.out.println("Exception e: " + e.getMessage());
        }
        return null;
    }

    public static boolean isInDistanceFromSniper(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371.0;
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double sindLat = Math.sin(dLat/2);
        double sindLng = Math.sin(dLng/2);
        double a = Math.pow(sindLat,2) + Math.pow(sindLng,2) * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));
        double c= 2*Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
        double dist = earthRadius * c;

        double sniperRange = Double.parseDouble(getPropValue("sniperRange"));

        if (dist < sniperRange)
            return true;
        else
            return false;
    }

    public static void deleteFile(String fileLoc) {
        File f = new File(fileLoc);
        if(f.exists() && !f.isDirectory()) {
            f.delete();
        }
    }


}
