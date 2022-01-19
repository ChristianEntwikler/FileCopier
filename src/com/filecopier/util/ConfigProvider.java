/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filecopier.util;

import java.util.ResourceBundle;

//import com.bankphb.encryption.DataEncryptor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dofoleta
 */
public class ConfigProvider {

    private static ResourceBundle configBundle = ResourceBundle.getBundle("config");

    public static void initialize() {
        configBundle = ResourceBundle.getBundle("config");
    }
    
    public String getString(String key)
    {
        return configBundle.getString(key);
    }
    
//    public String decryptString(String request)
//    {
//        String resp = "";
//        resp = configBundle.getString(request);
//        try {
//            resp = DataEncryptor.Decrypt(resp);
//        } catch (Exception ex) {
//            Logger.getLogger(ConfigProvider.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        resp = resp.replace(configBundle.getString("NacL"), "");
//        
//        return resp;
//    }


}
