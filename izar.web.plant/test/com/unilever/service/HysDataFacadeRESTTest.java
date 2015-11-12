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
public class HysDataFacadeRESTTest extends BasicRESTTest{
     
//     @Test
     public void findAllData(){
         String json = "{}";
         String response = getResponseFromPostMethod("hysdata/findAllData", json);
         System.out.println(response);
         assertNotNull(response);
         
     }
        
     @Test
     public void findleafInfoByNode(){
         String json = "{\"treeLevel\":\"3\",\"dataState\":\"\",\"nodeId\":\"7\",\"pagesize\":50,\"search\":\"\",\"nd\":1437024396449,\"currpage\":1,\"sidx\":\"\",\"sord\":\"desc\"}";
         String response = getResponseFromPostMethod("hysdata/findleafInfoByNode", json);
         System.out.println(response);
         assertNotNull(response);
         
     }
     
//     @Test
     public void addArea(){   
//         String json = ""
//                 + "{\"areaId\":\"100036\",\"areaName\":\"area2\",\"beltlineId\":\"100037\",\"beltlineName\":\"belt2\","
//                 + "\"workTeamId\":\"\",\"workTeamName\":\"workTeam2\",\"isPublic\":\"\",\"meterId\":\"1011\",\"meterNum\":\"1011\"}";
         
         String json = "" //1.全部添加
                 + "{\"areaName\":\"area2\",\"beltlineName\":\"belt2\","
                 + "\"workTeamName\":\"workTeam2\",\"isPublic\":\"\",\"meterId\":\"1012\"}";
         
//          String json = "" //区域、生产线、生产组都已存在，只是添加设备关联
//                 + "{\"areaId\":\"100042\",\"areaName\":\"area1\",\"beltlineId\":\"100043\",\"beltlineName\":\"belt1\","
//                 + "\"workTeamId\":\"100044\",\"workTeamName\":\"workTeam1\",\"isPublic\":\"\",\"meterId\":\"1012\",\"meterNum\":\"1012\"}";
          
//          String json = "" //区域、生产线、生产组都已存在（手写），只是添加设备关联
//                 + "{\"areaId\":\"\",\"areaName\":\"area1\",\"beltlineId\":\"\",\"beltlineName\":\"belt1\","
//                 + "\"workTeamId\":\"\",\"workTeamName\":\"workTeam1\",\"isPublic\":\"\",\"meterId\":\"1012\",\"meterNum\":\"1012\"}";
         
//        String json = "" //部分添加，区域存在，添加生产线、组，设备关联
//                 + "{\"areaId\":\"100042\",\"areaName\":\"area1\",\"beltlineId\":\"\",\"beltlineName\":\"belt11\","
//                 + "\"workTeamId\":\"\",\"workTeamName\":\"workTeam11\",\"isPublic\":\"\",\"meterId\":\"1012\",\"meterNum\":\"1012\"}";
         
//         String json = "" //全部添加
//                 + "{\"areaId\":\"100050\",\"areaName\":\"area2\","
//                 + "\"workTeamId\":\"\",\"workTeamName\":\"workTeam11\",\"isPublic\":\"public\",\"meterId\":\"1012\",\"meterNum\":\"1012\"}";
         
//            String json = "" //1.全部添加
//                 + "{\"areaId\":\"\",\"areaName\":\"area2\",\"beltlineId\":\"\",\"beltlineName\":\"\","
//                 + "\"workTeamId\":\"\",\"workTeamName\":\"workTeam111\",\"isPublic\":\"public\",\"meterId\":\"\",\"meterNum\":\"\"}";
         
         String response = getResponseFromPostMethod("hysdata/addArea", json);
         System.out.println(response);
         assertNotNull(response);
     
     }
     
     
//     @Test
     public void editArea(){
         String json = ""
                 + "{\"areaId\":\"100085\",\"areaName\":\"area11\",\"beltlineId\":\"100086\",\"beltlineName\":\"belt11\","
                 + "\"workTeamId\":\"100087\",\"workTeamName\":\"workTeam1111\",\"isPublic\":\"\",\"meterId\":\"1011\",\"meterNum\":\"1011\"}";
         String response = getResponseFromPostMethod("hysdata/editArea", json);
         System.out.println(response);
         assertNotNull(response);
     
     }
        
//     @Test
     public void deleteArea(){  
         String json = "{\"ids\":\"100064,100065\"}";
         String response = getResponseFromPostMethod("hysdata/deleteArea", json);
         System.out.println(response);
         assertNotNull(response);
     
     
     }
     
//     @Test
     public void findSubNodes(){
         String json = "{\"id\":\"0\",\"currLevel\":\"0\",\"pid\":\"999\"}";
         String response = getResponseFromPostMethod("hysdata/findSubNodes", json);
         System.out.println(response);
         assertNotNull(response);
     
     }
     
//     @Test
      public void findAllArea(){      
         String json = "";
         String response = getResponseFromPostMethod("hysdata/findAllArea", json);
         System.out.println(response);
         assertNotNull(response);
      
      }
      
//      @Test
      public void findTreeByParNode(){
          String json = "{\"id\":\"0\",\"name\":\"root\"}";
         String response = getResponseFromPostMethod("hysdata/findTreeByParNode", json);
         System.out.println(response);
         assertNotNull(response);
          
      }
}
