/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni_mannheim.informatik.ai.ludo.model.preferences;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class provides convinient access to the preferences.properties file.
 * This file entails all the additional information which are necessary to run
 * this application but should not be inserted by the user.
 * These informations are the artificial gamer class names, the class name of a
 * concrete StatisticsWriter and the output location of the statisticsfile.
 * Further, it provides access to internationalized messages which can be accessed
 * by any interrested party.
 * @author gtrefs
 */
public class Preferences {
    private static String MAX_ROUND = "ludo.game.maxRound";
    private static String MODE = "ludo.game.mode";
    private static String SIMULATION_MODE = "simulation";

    private String loadedMode;
    private int loadedMaxRound=-1;
    private Properties props;
    private String propertiesPath = "Preferences.properties";

    public int getMaxRound() {
        if(loadedMaxRound==-1){
            loadedMaxRound = Integer.parseInt(getProperty(MAX_ROUND));
        }
        return loadedMaxRound;
    }

    public boolean isInSimulationMode(){
        if(loadedMode == null){
            loadedMode = getProperty(MODE);
        }
        return loadedMode.equals(SIMULATION_MODE);
    }

    // START - Singleton
    private static Preferences instance = new Preferences();

    public static Preferences getInstance() {
        return instance;
    }

    private Preferences() {
        loadProps();
    }

    private void loadProps() {
        props = new Properties();
        try {
            props.load(getClass().getResourceAsStream(propertiesPath));
        } catch (IOException ex) {
            Logger.getLogger(Preferences.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized String getProperty(String arg0) {
        if (props == null) {
            loadProps();
        }
        // The user can alter the behaviour by entering the values directly on the prompt
        String ret = System.getProperty(arg0) == null ? props.getProperty(arg0) : System.getProperty(arg0);
        return ret;
    }

}
