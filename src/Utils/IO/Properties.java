package Utils.IO;

import java.io.*;
import java.util.Hashtable;
import java.util.Map;

public class Properties
{
    private static Properties instance;

    private Hashtable<String,String> properties;

    private Properties()
    {
        properties = new Hashtable<>();
    }

    public static void load(String path)
    {
        instance = new Properties();
        try(BufferedReader reader = new BufferedReader(new FileReader(new File(path))))
        {
            String line = "";

            while ((line = reader.readLine()) != null)
            {
                // read
                if(line.trim().startsWith("//") || line.isBlank()) continue; // comment/empty

                String[] split = line.split("[:]");
                if(split.length != 2) throw new IllegalArgumentException("File not in the right format, the character ':' is not allowed");

                split[0] = split[0].trim();
                split[1] = split[1].trim();

                if(!split[0].matches("[a-zA-Z\\d ]+") || !split[1].matches("[a-zA-Z\\d ]+")) throw new IllegalArgumentException("File not in the right format, Key/Value contains only [a-z][A-Z][1-9][ ]");

                instance.properties.put(split[0],split[1]);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void save(String path)
    {
        if(instance == null) return;

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(new File(path))))
        {
            // write intro
            writer.write("// -- 5m1l3 Properties File ---------------------------------------\n");

            // write string properties
            for(Map.Entry<String,String> property : instance.properties.entrySet())
            {
                writer.write(property.getKey() + ":" + property.getValue() + "\n");
            }

            writer.write("// ----------------------------------------------------------------\n");

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static String get(String key)
    {
        if(instance == null) instance = new Properties();

        return instance.properties.get(key);
    }

    public static void put(String key, String value)
    {
        if(!key.matches("[a-zA-Z\\d ]+") || !value.matches("[a-zA-Z\\d ]+")) throw new IllegalArgumentException("Key/Value contains only [a-z][A-Z][1-9][ ]");

        if(instance == null) instance = new Properties();

        instance.properties.put(key,value);
    }
}
