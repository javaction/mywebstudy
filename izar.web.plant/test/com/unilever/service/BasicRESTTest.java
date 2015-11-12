/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author Administrator
 */
public class BasicRESTTest {
    
    private static String host="localhost";
    private static  String port="8088";
    private static   String context="plant";
    
    public static String url= "http://"+host+":"+port+"/"+context+"/webresources/";
    
    public String getResponseFromGetMethod(String url){
        String response=null;
        URL visiturl = null;
        try {
            visiturl=new URL(BasicRESTTest.url+url);
            HttpURLConnection conn = (HttpURLConnection) visiturl.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException(visiturl+" Failed : HTTP error code : "
                                + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream()),"UTF-8"));
            String output;
            while ((output = br.readLine()) != null) {
                response=output;
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
              e.printStackTrace();
        } catch (IOException e) {
              e.printStackTrace();
        }
        return response;
    }
    
    public String getResponseFromPostMethod(String url,String jsonData){
        String response=null;
        URL visiturl = null;
        try {
            visiturl=new URL(BasicRESTTest.url+url);
            HttpURLConnection conn = (HttpURLConnection) visiturl.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            
            String input = jsonData;
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();
            
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream()),"UTF-8"));
            String output;
            while ((output = br.readLine()) != null) {
               response=output;
            }
            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}