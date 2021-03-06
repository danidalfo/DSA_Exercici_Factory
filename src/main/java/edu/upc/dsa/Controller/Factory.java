package edu.upc.dsa.Controller;

import org.apache.log4j.Logger;
import java.util.HashMap;

public class Factory {

    private static Factory instance;

    final static Logger log = Logger.getLogger(Factory.class.getName());

    public static Factory getInstance(){
        if (instance==null)
            instance = new Factory();
        return instance;
    }

    HashMap<String, Command> cache;

    public Command getCommand(String sCMD) {
        Command cmd = cache.get(sCMD);

        if (cmd == null) {
            cmd = loadClass(sCMD);
            cache.put(sCMD, cmd);
        }

        return cmd;
    }


    private Command loadClass(String sCMD)
    {
        Command cmd=null;
        try {
            log.info("Començant la funció loadClass");

            Class c = Class.forName("edu.upc.dsa.Model."+sCMD);
            cmd = (Command) c.newInstance();
            log.info(("S'ha creat la clase"+sCMD));
        }
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex)
        {
            log.warn("caguen la puta");
        }

        return cmd;

    }
}
