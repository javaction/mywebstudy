/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.service;

import static org.junit.Assert.*;
import org.junit.Test;
/**
 *
 * @author Ussopp.Su
 */
public class HysCollectorFacadeRESTTest extends BasicRESTTest{
        
//    @Test
    public void  findAllCollector(){ 
        String json = "{}";
        String response  = getResponseFromPostMethod("hyscollector/findAllCollector",json);
        System.out.print(response);
        assertNotNull(response);
         
    }   
    
//    @Test
    public void findCollectorBasicInfo(){
        String json = "{\"pagesize\":50,\"search\":\"\",\"nd\":1437024396449,\"currpage\":1,\"sidx\":\"\",\"sord\":\"desc\"}";
        String response  = getResponseFromPostMethod("hyscollector/findCollectorBasicInfo",json);
        System.out.print(response);
        assertNotNull(response);
    
    }
    
//    @Test
    public void addCollector(){
        String json = "{\"hysCollectorNumber\":\"5263\",\"hysCollectorType\":\"xxxxcvxz\",\"hysManufacturer\":\"changshang111\",\"hysRemark\":\"remark111\"}";
        String response  = getResponseFromPostMethod("hyscollector/addCollector",json);
        System.out.print(response);
        assertNotNull(response);
    
    }
    
//    @Test
    public void editCollector(){
       String json = "{\"hysStorageId\":\"100032\",\"hysCollectorNumber\":\"321321\",\"hysCollectorType\":\"aaadddd\",\"hysManufacturer\":\"changshang1222\",\"hysRemark\":\"remark111\"}";
       String response  = getResponseFromPostMethod("hyscollector/editCollector",json);
       System.out.print(response);
       assertNotNull(response);   
    }
    
//    @Test
    public void deleteCollector(){
       String json = "{\"ids\":\"100032,100033\"}";
       String response  = getResponseFromPostMethod("hyscollector/deleteCollector",json);
       System.out.print(response);
       assertNotNull(response);
    
    }
    
    @Test
    public void findAllCollectorForSet(){
        String json = "{\"ids\":\"1011\"}";
       String response  = getResponseFromPostMethod("hyscollector/findAllCollectorForSet",json);
       System.out.print(response);
       assertNotNull(response);
    
    }
    
}
