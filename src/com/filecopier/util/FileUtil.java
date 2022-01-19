/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filecopier.util;

import com.filecopier.dtos.ResponseDto;
import static com.filecopier.util.Util.writeToFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 *
 * @author cenebeli
 */
public class FileUtil {
     private static final ResourceBundle rb = ResourceBundle.getBundle("config");
    public static ResponseDto filesearch(String name, File file) throws IOException
    {
        ResponseDto res = new ResponseDto();
        
         try {

            File[] list = file.listFiles();
            System.out.println("list.length: " + list.length);
        if(list!=null)
//        for (File fil : list)
//        {
//            if (fil.isDirectory())
//            {
//                filesearch(name,fil);
//            }
//            else if (name.equalsIgnoreCase(fil.getName()))
//            {
                System.out.println("fil.getParentFile(): " + file.getParentFile());
                res = fileCreate(file, name);
//            }
//        }
        
         }
         catch(Exception ex)
         {
             res.setResponseCode("96");
            res.setResponseDescription("Failed to fetch");
             System.out.println(ex.getMessage());
             ex.printStackTrace();
            writeToFile(res.getResponseDescription() + "\t" + ex.getMessage());
         }
        
        return res;
    }
    
    
    
    public static ResponseDto fileCreate(File filePath, String name) throws IOException
    {
        ResponseDto res = new ResponseDto();
        try
        {
            String oldPath = filePath + "/" + name;
            File filePathName = new File(String.valueOf(oldPath));
            if(filePathName.exists())
            {
        String basePath = rb.getString("fileSaveLocation");       
        File directory = new File(String.valueOf(basePath));
            if(!directory.exists()){
                        directory.mkdir();                    
           }
       
        String filename=filePathName.getName();
        System.out.println(filename);
        
        byte[] finalimagebyte=Files.readAllBytes(filePathName.toPath());
         Path destinationFile = Paths.get(basePath+"/", filename);
         Files.write(destinationFile, finalimagebyte);
         
         res.setResponseCode("00");
         res.setResponseDescription("Successful");
            }
            else
            {
               res.setResponseCode("00");
         res.setResponseDescription("No Such File"); 
            }
         
        }
        catch(Exception ex)
        {
            res.setResponseCode("96");
            res.setResponseDescription("Failed to save");
         System.out.println(ex.getMessage());
         ex.printStackTrace();
             writeToFile(res.getResponseDescription() + "\t" + ex.getMessage());   
        }
        
        return res;
    }
}
