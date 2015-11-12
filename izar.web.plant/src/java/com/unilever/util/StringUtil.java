/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.util;

/**
 *
 * @author Ussopp.Su
 */
public class StringUtil {
    
    /**
     * 路径中的双斜线改为单斜线
     * @param path
     * @return 
     */
    public static String dealPathSingle(String path){
        StringBuilder sb = new StringBuilder("");
        String[] s  = path.split("\\\\");
        for(String str:s){
            if(!"".equals(str)){
                if(!"".equals(sb.toString())){
                    sb.append("\\");
                }
                sb.append(str);
            }
        }
        return sb.toString();
    }
    
    /**
     * 路径中的单斜线\改为双斜线\\
     * @param path
     * @return 
     */
    public static String dealPathDouble(String path){
        StringBuilder sb = new StringBuilder("");
        String[] s  = path.split("\\\\");
        for(String str:s){
            if(!"".equals(str)){
                if(!"".equals(sb.toString())){
                    sb.append("\\\\");
                }
                sb.append(str);
            }
        }
        return sb.toString();
    }
    
    
    /**
     * 分析时间间隔
     * @param str
     * @return 
     */
    public static String analyseInterval(String value)
    {
        //如果是数据库备份间隔参数，需要先解析//0 0 2 1 * ?
        //与前台约定传值形式为：
        //weekly;01;03  每周一3时
        //monthly;01;03  每月1日3时
        //yearly;01;02;03  每年1月2日3时
        String[] ss = value.split(" ");
        StringBuffer databaseBackupInterval = new StringBuffer("");
        if(ss.length==6){
            if(!"?".equals(ss[5]) && !"*".equals(ss[5])){
                //周
                databaseBackupInterval.append("weekly");
                if("1".equals(ss[5])){//周日
                    databaseBackupInterval.append(";07");
                }
                else{
                    databaseBackupInterval.append(";0").append(Integer.parseInt(ss[5])-1);
                }

                if(ss[2].length()==2){
                    databaseBackupInterval.append(";").append(ss[2]);
                }
                else{
                    databaseBackupInterval.append(";0").append(ss[2]);
                }
            }
            else if(!"?".equals(ss[4]) && !"*".equals(ss[4])){
                databaseBackupInterval.append("yearly");
                if(ss[4].length()==2){
                    databaseBackupInterval.append(";").append(ss[4]);
                }
                else{
                    databaseBackupInterval.append(";0").append(ss[4]);
                }

                if(ss[3].length()==2){
                    databaseBackupInterval.append(";").append(ss[3]);
                }
                else{
                    databaseBackupInterval.append(";0").append(ss[3]);
                }

                if(ss[2].length()==2){
                    databaseBackupInterval.append(";").append(ss[2]);
                }
                else{
                    databaseBackupInterval.append(";0").append(ss[2]);
                }
            }
            else{
                databaseBackupInterval.append("monthly");
                if(ss[3].length()==2){
                    databaseBackupInterval.append(";").append(ss[3]);
                }
                else{
                    databaseBackupInterval.append(";0").append(ss[3]);
                }

                if(ss[2].length()==2){
                    databaseBackupInterval.append(";").append(ss[2]);
                }
                else{
                    databaseBackupInterval.append(";0").append(ss[2]);
                }
            }
        }
        return databaseBackupInterval.toString();
    }
    
}
