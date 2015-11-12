/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.util;

/**
 * 常量定义
 * @author Administrator
 */
public class Constant {
    
    //保存session中登录用户信息的id和fullname
    public static final String SESSION_USER_ID_KEY = "user_id";
    //用户session信息中的全名对应的key
    public static final String SESSION_USER_NAME_KEY = "user_name";
    //用户角色session信息中的全名对应的key
    public static final String SESSION_USER_GROUP_KEY = "user_group_name";
      
    //需要的参数：脚本文件夹，脚本文件名称，需要转出的数据库的名称、地址、密码，需要备份到的文件夹的名称
    public static final String DATABACE_NAME = "plant"; //需要备份的数据库的名称
    
    public static final String DATABACE_ADDRESS = "localhost"; //需要备份的数据库的地址  // 192.168.1.162    localhost
    
    public static final String DATABACE_USER = "postgres"; //需要备份的数据库的默认用户名
    
    public static final String DATABACE_PASSWORD = "hydroradio"; //需要备份的数据库的默认用户的密码
    
    public static final String DATABACE_BACKUP_PATH = "c:\\databackup"; //数据库备份默认保存的文件夹路径  c:\\databackup
    
    public static final String DATABACE_PORT_NUM = "5432";//数据库默认端口号
    
}
