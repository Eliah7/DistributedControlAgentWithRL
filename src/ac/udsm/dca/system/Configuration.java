package ac.udsm.dca.system;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Configuration {
    private boolean loaded = false;
    private long id;
    private int type;
    private boolean gui = false;
    private String host;
    private Map<String, String> properties;

    public Configuration(String... args) {
        if (args == null || args.length == 0) {
            Scanner sc = null;
            try {
                sc = new Scanner(new FileReader("manifest.txt"));
                properties = new HashMap<>();
                while (sc.hasNextLine()) {
                    String line = sc.next();
                    if (!line.isEmpty()) {
                        String[] parts = line.split("=");
                        String key = parts[0];
                        String value = null;
                        if (parts.length > 1) {
                            value = parts[1];
                        }
                        setProperty(key, value);
                    }
                }
                loaded = true;
            } catch (FileNotFoundException e) {
                Log.e(e.getMessage());
            }
        } else {

        }
    }

    private void setProperty(String key, String value) {
        switch (key) {
            case "id":
                id = Long.parseLong(value);
                break;
            case "type":
                type = Integer.parseInt(value);
                break;
            case "gui":
                gui = Boolean.parseBoolean(value);
                break;
            case "host":
                host = value;
                break;
            default:
                properties.put(key, value);
                break;
        }
    }

    public Object getProperty(String key, Object defaultValue) {
        if (properties.containsKey(key)) {
            return properties.get(key);
        }
        return defaultValue;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public boolean isGui() {
        return gui;
    }

    public long getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public String getHost() {
        return host;
    }
}
