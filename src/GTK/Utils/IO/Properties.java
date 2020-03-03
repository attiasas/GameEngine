package GTK.Utils.IO;

import java.io.*;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created By:      Assaf, On 23/02/2020
 * Description:     Properties Manager for programs.
 *                  Can store key,value String pairs of information, load/save properties from files.
 *
 *                  * This is a singleton and grants access to the property from any class.
 *
 * File Syntax:     * '//' at the start of the line will be a comment.
 *                  * the char: ':' is not allowed in both the key and the pair.
 *                  * each property per line, in the format- "<KEY> : <VALUE>".
 */
public class Properties
{
    private static Properties instance;

    private Hashtable<String,String> properties;

    private Properties()
    {
        properties = new Hashtable<>();
    }

    /**
     * Load a property file from the disk that matches the format.
     * If The file is not matching the format an IllegalArgumentException will be thrown
     * @param path - path of the property file.
     */
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

    /**
     * Saves the stored properties to a file in the format from the description.
     * @param path - path (full name) of the file that will be saved, if the file exists irs content will be overwritten.
     *               if the file not exists, a file will be generate.
     */
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

    /**
     * Get The value of a property base on a given key
     * @param key - of the value pair.
     * @return the value of the property, if the key not exists in the properties null will be returned.
     */
    public static String get(String key)
    {
        if(instance == null) instance = new Properties();

        return instance.properties.get(key);
    }

    /**
     * Put new property to the properties object.
     * @param key - unique key that defies the property
     * @param value - the value of the property
     */
    public static void put(String key, String value)
    {
        if(!key.matches("[a-zA-Z\\d ]+") || !value.matches("[a-zA-Z\\d ]+")) throw new IllegalArgumentException("Key/Value contains only [a-z][A-Z][1-9][ ]");

        if(instance == null) instance = new Properties();

        instance.properties.put(key,value);
    }
}
