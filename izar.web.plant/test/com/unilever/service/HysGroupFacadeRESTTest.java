/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.service;

import org.junit.Test;

/**
 *
 * @author Administrator
 */
public class HysGroupFacadeRESTTest extends BasicRESTTest{
    
//    @Test
    public void  findAll(){   
        String json = "";
        String response = getResponseFromPostMethod("hysgroup/findAll", json);
        System.out.println(response);    
       
    }
    
//    @Test
    public void dele(){
        String json = "{\"ids\":\"1\"}";
        String response = getResponseFromPostMethod("hysgroup/dele", json);
        System.out.println(response);
    
    }
    
    @Test
    public void editGroup(){
        String json = "{\"hysStorageId\":\"2\",\"hysGroupName\":\"emp\",\"hysDesc\":\"emp desc...\"}";
        String response = getResponseFromPostMethod("hysgroup/editGroup", json);
        System.out.println(response);
    }
    
}
