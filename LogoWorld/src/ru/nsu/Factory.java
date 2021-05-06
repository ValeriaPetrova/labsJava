package ru.nsu;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

public class Factory {
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Factory.class);

    private final Map <String, Object> classes = new HashMap<>();//Key: command name. Value: object of class Object
    private final Properties prop = new Properties ();

    public Factory() throws IOException {
        logger.info("Reading property from config.txt.");
        prop.load(ClassLoader.getSystemClassLoader().getResourceAsStream("config"));
    }

    public Object create(String commandName) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String className = prop.getProperty(commandName, "Unknown command");
        Object command;
        if (className.equals("Unknown command")) {
            logger.error("Unknown command.");
            System.out.println("Unknown command");
            return null;
        }
        command = classes.get(className);
        if (command == null) {
            Class c = Class.forName(className);
            command = c.newInstance();
            logger.info("Create object of class " + className);
            classes.put(className, command);
        }
        return command;
    }
}
