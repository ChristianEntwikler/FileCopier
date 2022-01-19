/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filecopier.db;

import com.filecopier.dtos.RequestDto;
import com.filecopier.dtos.ResponseDto;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cenebeli
 */
public class dbutil {
    
    public List<RequestDto> fetchRequestIds() { 
        
        Connection conn = new DatabaseResource().getLocalConnection();
        List<RequestDto> resp = new ArrayList<RequestDto>();
        
        if (conn != null) {

            try {
             CallableStatement  cstmt = conn.prepareCall("{call Proc_fetchRequests()}");
             boolean retnval = cstmt.execute();     
             ResultSet rs = cstmt.executeQuery();
               
                while (rs.next()) {
                    RequestDto respo = new RequestDto();
                    respo.setRequestId(rs.getString("requestId"));
                    respo.setSpoolDate(rs.getString("SpoolStatus"));
                    respo.setSpoolStatus(rs.getString("SpoolDate"));                  
                    
                    resp.add(respo);
                }
                rs.close();
                cstmt.close();

            } catch (SQLException ex) {
                Logger.getLogger(dbutil.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            } finally {
                try {
                    if(conn!=null)
                {
                conn.close();
                }
                    conn = null;
                } catch (SQLException ex) {
                    Logger.getLogger(dbutil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return resp;
    }
    
    
    //UPDATE TRANSACTION HOLD
      public ResponseDto UpdateRequestStatus(ResponseDto reqResp, RequestDto req) {
        Connection conn = new DatabaseResource().getLocalConnection();
        
        ResponseDto resp=new ResponseDto();
        
        CallableStatement cstmt = null;
        try {
            
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Timestamp timestamp2 = new Timestamp(System.currentTimeMillis());
            String timestampz2=sdf2.format(timestamp2);
            
            System.out.println("Updating file fetch status : " + req.getRequestId());
            cstmt = conn.prepareCall("{call Proc_updateFileCopyStatus(?,?,?)}");
            int i = 0;
            cstmt.setString(++i, reqResp.getRequestId());
            cstmt.setString(++i, reqResp.getResponseCode());
            cstmt.setString(++i, timestampz2);
            boolean retnval = cstmt.execute();
            
            if(retnval)
            {
                resp.setResponseCode("00");
                resp.setResponseDescription("Successful");
                resp.setRequestId(req.getRequestId());
            }

            cstmt.close();

            } catch (Exception ex) {
                
                resp.setResponseCode("96");
                resp.setResponseDescription("Failed");
                resp.setRequestId(req.getRequestId());
                
                ex.printStackTrace();
                //Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    conn.close();
                    conn = null;
                } catch (SQLException ex) {
                    Logger.getLogger(dbutil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
        return resp;
    }
    
}
