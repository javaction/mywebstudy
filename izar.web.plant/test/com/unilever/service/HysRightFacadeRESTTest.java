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
public class HysRightFacadeRESTTest extends BasicRESTTest{
    
    @Test
    public void findAll(){
        String json = "";
        String response = getResponseFromPostMethod("hysright/findAll", json);
        System.out.println(response);    
        assertNotNull(response);
    }
    
    
}
