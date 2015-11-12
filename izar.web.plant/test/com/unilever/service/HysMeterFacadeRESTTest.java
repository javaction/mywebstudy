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
public class HysMeterFacadeRESTTest extends BasicRESTTest{
    
//    @Test
    public void findAllMeter(){
        String json = "{}";
        String response = getResponseFromPostMethod("hysmeter/findAllMeter",json);
        System.out.println(response);
        assertNotNull(response);
    
    }
    
//    @Test
    public void findMeterBasicInfo(){
        String json = "{\"pagesize\":50,\"search\":\"\",\"nd\":1437024396449,\"currpage\":1,\"sidx\":\"\",\"sord\":\"desc\"}";
        String response  = getResponseFromPostMethod("hysmeter/findMeterBasicInfo", json);
        System.out.println(response);
        assertNotNull(response);
        
    }
    
//    @Test
    public void addMeter(){
        String json = "{\"hysMeterNumber\":\"123123\",\"hysMeterName\":\"\",\"hysMeterCode\":\"heat\","
                + "\"hysMeterType\":\"AAA\",\"hysStandardPower\":\"521.9\",\"hysManufacturer\":\"changshang\",\"collectorId\":\"1\"}";
        String response = getResponseFromPostMethod("hysmeter/addMeter", json);
        System.out.println(response);
        assertNotNull(response);
        
    }
    
//    @Test
    public void deleteMeter(){
        String json = "{\"ids\":\"100029,100030\"}";
        String response = getResponseFromPostMethod("hysmeter/deleteMeter", json);
        System.out.println(response);
        assertNotNull(response);
        
    }
    
  //  @Test
    public void editMeter(){
        String json = "{\"hysStorageId\":\"100093\",\"hysMeterNumber\":\"123123\",\"hysMeterName\":\"\","
                + "\"hysMeterCode\":\"heat\",\"hysMeterType\":\"SSS\",\"hysStandardPower\":\"520.6\",\"hysManufacturer\":\"wuxing\",\"collectorId\":\"2\"}";
        String response = getResponseFromPostMethod("hysmeter/editMeter", json);
        System.out.println(response);
        assertNotNull(response);
    
    }
    
    @Test
    public void findAllMeterForSet(){
        String json = "{\"ids\":\"\"}";
        String response = getResponseFromPostMethod("hysmeter/findAllMeterForSet", json);
        System.out.println(response);
        assertNotNull(response);
    
    }
    
}
