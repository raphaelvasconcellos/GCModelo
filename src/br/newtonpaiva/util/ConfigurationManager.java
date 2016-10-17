/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tarle
 */
public class ConfigurationManager {

    private static final Properties CONFIG = new Properties();

    static {
        try {
            //CONFIG.loadFromXML(new FileInputStream("config.xml"));
            CONFIG.loadFromXML(ConfigurationManager
                    .class.getResourceAsStream("/config.xml"));
            DB_URL = CONFIG.getProperty("db.url");
            DB_USUARIO = CONFIG.getProperty("db.usuario");
            DB_SENHA = CONFIG.getProperty("db.senha");
        } catch (IOException ex) {
            Logger.getLogger(ConfigurationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String DB_URL;
    public static String DB_USUARIO;
    public static String DB_SENHA;
    
    public static String appSettings(String key) {
        return CONFIG.getProperty(key);
    }
}
