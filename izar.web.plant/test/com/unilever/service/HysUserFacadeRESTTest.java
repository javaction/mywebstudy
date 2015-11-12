/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.service;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Administrator
 */
public class HysUserFacadeRESTTest extends BasicRESTTest {
    
//    @Test
    public void findAllUsers() {
        String json = "{}";
        String response = getResponseFromPostMethod( "hysuser/findAllUsers",json);
        System.out.println(response);
        assertNotNull(response);
    }
    
//    @Test
    public void addUser() {
        String json = "{\"hysUserName\":\"user6\",\"hysUserPassword\":\"666666\",\"hysGroupId\":1,\"hysDesc\":\"666\"}";
        String response = getResponseFromPostMethod( "hysuser/addUser",json);
        System.out.println(response);
        assertNotNull(response);
    }
    
//    @Test
    public void editUser() {
        String json = "{\"hysStorageId\":\"100002\",\"hysUserName\":\"user22\",\"hysUserPassword\":\"222222\",\"hysGroupId\":1,\"hysDesc\":\"21212\"}";
        String response = getResponseFromPostMethod( "hysuser/editUser",json);
        System.out.println(response);
        assertNotNull(response);
    }
    
//    @Test
    public void delUser() {
        String json = "{\"ids\":\"100024\"}";
        String response = getResponseFromPostMethod( "hysuser/delUser",json);
        System.out.println(response);
        assertNotNull(response);
    }
    
//    @Test
    public void login() {
        String json = "{\"hysUserName\":\"user6\",\"hysUserPassword\":\"666666\"}";
        String response = getResponseFromPostMethod( "hysuser/login",json);
        System.out.println(response);
        assertNotNull(response);
    }
    
//    @Test
    public void logout() {
        String json = "";
        String response = getResponseFromPostMethod( "hysuser/logout",json);
        System.out.println(response);
        assertNotNull(response);
    }
}
