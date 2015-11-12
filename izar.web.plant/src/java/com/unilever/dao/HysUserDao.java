/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.dao;

import com.unilever.entity.HysGroup;
import com.unilever.entity.HysUser;
import com.unilever.util.Constant;
import com.unilever.util.MD5Util;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tengguan
 */
@Stateless
public class HysUserDao extends ParentDao{
    private static final Logger LOG = Logger.getLogger(HysUserDao.class.getName());
    /**
     * 
     * @param name
     * @param password
     * @return 
     */
    public HysUser getUserByNameAndPassword(String name,String password){
        HysUser user = null;
        
        //查询用户名密码是否存在
        Query query = em.createNamedQuery("HysUser.login");
         //参数为登录名并转化为小写
        query.setParameter("name", name);
        // 参数为登录密码并进行加密
        query.setParameter("password",password);
        List<HysUser> list = query.getResultList();
        if(list!=null && list.size()>0){
            //用户名密码存在
            user = list.get(0);           
        }
        return user;
    }
}
