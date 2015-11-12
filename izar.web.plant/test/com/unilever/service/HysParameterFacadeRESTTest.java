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
public class HysParameterFacadeRESTTest extends BasicRESTTest{
    
    @Test
    public void findAllPara(){
        String json = "{}";
        String response = getResponseFromPostMethod("hysparameter/findAllParameters",json);  
        System.out.println(response);
        assertNotNull(response);
    }
    

    
    
}
