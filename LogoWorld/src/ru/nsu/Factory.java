package ru.nsu;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Factory {
    //private final static Logger logger = Logger.getLogger(Factory.class);

    private final Map <String, Object> classes = new HashMap<>();//Key: command name. Value: object of class Object
    private final Properties prop = new Properties ();

    public Factory() throws IOException {
        //logger.info("Reading property from config.txt.");
        prop.load(ClassLoader.getSystemClassLoader().getResourceAsStream("config"));
    }

    public Factory(String fileName) throws IOException {
        //logger.info("Reading property from " + fileName + ".");
        prop.load(ClassLoader.getSystemClassLoader().getResourceAsStream(fileName));
    }

    public Object create(String commandName, Game myGame) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String className = prop.getProperty(commandName, "Unknown command");
        Object command;
        if (className.equals("Unknown command")) {
            System.out.println("Unknown command");
            return null;
        }
        if (!classes.containsKey(className)) {
            Class c = Class.forName(className);
            command = c.newInstance();
            //logger.info("Create object of class " + className);
            classes.put(className, command);
        }
        else {
            command = classes.get(className);
        }
        return command;
    }
}
