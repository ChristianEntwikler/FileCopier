/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filecopier.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 *
 * @author cenebeli
 */
public class Util {
  public static void writeToFile(String keyString) throws IOException {
    
        String textFilePAth="";
        
          SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
        String timestampz1=sdf1.format(timestamp1);
        
        
         SimpleDateFormat sdffiledate = new SimpleDateFormat("yyyy-MM-dd");
        Timestamp timestampfiledate = new Timestamp(System.currentTimeMillis());
        String timestampzfiledate=sdffiledate.format(timestampfiledate);
        
        String basePath3 = new File("").getAbsolutePath() + "/FileLog/";
        
        File directory = new File(String.valueOf(basePath3));
            if(!directory.exists()){
                        directory.mkdir();
           }
        
        basePath3 = basePath3+"STPLog"+timestampzfiledate+".txt";
        keyString=keyString+" -"+timestampz1;
    
        textFilePAth=basePath3;
    
    File a = new File(textFilePAth);

    if (!a.exists()) {
        a.createNewFile();
    }
    FileWriter fw = new FileWriter(a.getAbsoluteFile(), true);
    BufferedWriter bw = new BufferedWriter(fw);
    bw.append(keyString);
    bw.newLine();
    bw.close();
    System.out.println(keyString +" saved...");
}  
}
