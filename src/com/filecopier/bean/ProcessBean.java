/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filecopier.bean;

import com.filecopier.db.dbutil;
import com.filecopier.dtos.RequestDto;
import com.filecopier.dtos.ResponseDto;
import com.filecopier.util.FileUtil;
import static com.filecopier.util.Util.writeToFile;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cenebeli
 */
public class ProcessBean {
    private static final ResourceBundle rb = ResourceBundle.getBundle("config");
    
    public static void main(String args[])
    {
         String basePath = rb.getString("fileFindLocation");       
        File file = new File(String.valueOf(basePath));
        
        try
        {
        dbutil db = new dbutil();
        FileUtil t = new FileUtil();
            //int processId = Integer.parseInt(args[0]);
            //System.out.println("processId: " + processId);
            int checkStatus = 0;
            
     while (checkStatus == 0) 
            {
                checkStatus = 1;
       List<RequestDto> pendings = db.fetchRequestIds();
       if (pendings != null)
                {
         for (RequestDto item : pendings) {
                                           
                   System.out.println("item.getRequestId(): " + item.getRequestId());
                   ResponseDto res = t.filesearch(item.getRequestId().replaceAll(" ", "").trim() + ".pdf", file);
                   if (res != null)
                   {
                    res.setRequestId(item.getRequestId());
                    ResponseDto resp = db.UpdateRequestStatus(res, item);
                    System.out.println(String.format("Ref: %s Response Code: %s ResponseMessage: %s ", new Object[] { res.getRequestId(), res.getResponseCode(), res.getResponseDescription()}));                            
                   }
                    
                    } 
                    checkStatus = 0;
                }
       try {
         Thread.sleep(10000L);
         System.out.println("waiting for 10 secs");
       } catch (InterruptedException ex) {
         Logger.getLogger(ProcessBean.class.getName()).log(Level.SEVERE, (String)null, ex);
       } 
     }
     
        }
         catch (Exception ex) {
         System.out.println(ex.getMessage());
         ex.printStackTrace();
         Logger.getLogger(ProcessBean.class.getName()).log(Level.SEVERE, (String)null, ex);
             try {
                 writeToFile(ex.getMessage());
             } catch (IOException ex1) {
                 Logger.getLogger(ProcessBean.class.getName()).log(Level.SEVERE, null, ex1);
             }
       } 
        
    }
}
