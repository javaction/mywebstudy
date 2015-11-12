/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.service;

import com.unilever.dao.HysUserDao;
import com.unilever.entity.HysGroup;
import com.unilever.entity.exp.Result;
import com.unilever.util.LogManager;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.unilever.entity.HysUser;
import com.unilever.entity.exp.Parameter;
import com.unilever.util.Constant;
import com.unilever.util.MD5Util;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ussopp.Su
 */
@Stateless
public class HysUserService {
    private static final Logger logger = LogManager.getLogger(HysUserService.class);
    @PersistenceContext(unitName = "TestPU")
    private EntityManager em;
    
    @EJB
    private HysUserDao userDao;
    
    /**
     * 用户修改密码
     * @return 
     */
    public Result updateUserPwd(Parameter parm,String userName){
        logger.info("------plant.HysUserService.updateUserPwd--------");
        String result = "successed";
        String message = "";
 
        String oldPass = parm.getOldPassword();    
        HysUser user = this.findUserByNameAndPass(userName,oldPass);
        String newPass = parm.getNewPassword();
        if(user!=null){
            //可以修改;
            try {
                user.setHysUserPassword(MD5Util.mD5(newPass));
                logger.info(user.getHysUserPassword()+"  id:-- "+user.getHysStorageId());
                em.merge(user);
                message ="密码修改成功！";
                logger.info("----密码修改成功！---");
            } catch (Exception e) {
                result = "failed";
                message = "用户密码修改不成功，请重新修改！";
                logger.severe("[updateUserPwd Exception]:"+e.getMessage());
            }            
        }else{
            logger.info("--输入的原始密码不正确，请重新输入！--");
            result = "failed";
            message = "输入的原始密码不正确，请重新输入！";
        }
             
        return new Result(result,message);
    }
    
    public HysUser getUserByNameAndPassword(String name,String password){
        
        HysUser user = userDao.getUserByNameAndPassword(name.toLowerCase(), MD5Util.mD5(password));
        /*
        //查询用户名密码是否存在
        Query query = em.createNamedQuery("HysUser.login");
         //参数为登录名并转化为小写
        query.setParameter("name", user.getHysUserName().toLowerCase());
        // 参数为登录密码并进行加密
        query.setParameter("password",MD5Util.mD5(user.getHysUserPassword()));
        List<HysUser> list = query.getResultList();
        if(list!=null && list.size()>0){
            //用户名密码存在
            HysUser hysUser = list.get(0);
            
            String groupName = "";
            if(hysUser.getHysGroupId()!=null){
                //查询角色
                Query queryGroup = em.createNamedQuery("HysGroup.findByHysStorageId");
                queryGroup.setParameter("hysStorageId", hysUser.getHysGroupId());
                List<HysGroup> groupList = queryGroup.getResultList();

                if(groupList!=null && groupList.size()>0){
                    groupName = groupList.get(0).getHysGroupName();
                }
            }
            
            login.setLogin("successful"); 
            login.setUserId(hysUser.getHysStorageId());
            login.setUserName(hysUser.getHysUserName());
            login.setGroupName(groupName);
            
            //保存用户登录登出信息?
            
            //保存session
            HttpSession session = req.getSession();
            session.setAttribute(Constant.SESSION_USER_ID_KEY, hysUser.getHysStorageId());
            session.setAttribute(Constant.SESSION_USER_NAME_KEY, hysUser.getHysUserName());
            session.setAttribute(Constant.SESSION_USER_GROUP_KEY, groupName);
            
        }
        else{
            login.setLogin("failure");
        }
        
        return login;*/
        return user;
    }
    
    /**
     * 根据传来的用户名和密码，查询这个用户
     * @param hysUser
     * @return 
     */
    public HysUser findUserByNameAndPass(String userName,String passWord){
        logger.info("------plant.HysUserService.findUserByNameAndPass--------");
        HysUser user = userDao.getUserByNameAndPassword(userName.toLowerCase(),MD5Util.mD5(passWord)); 
        /*
        String sql = "select u.hys_storage_id, u.hys_user_name,u.hys_user_password,u.hys_group_id,u.hys_desc from hys_user u  "
                + "where u.hys_user_name = ?userName and u.hys_user_password = ?passWord  ";
        
        Query query = em.createNativeQuery(sql);
        query.setParameter("userName", userName);
        query.setParameter("passWord", MD5Util.mD5(passWord));
        List list = query.getResultList();
        HysUser user = null; 
        if(list!=null&&list.size()>0){            
            Iterator it = list.iterator();
            while(it.hasNext()){
                Object[] obj = (Object[])it.next();
                user = new HysUser();
                if(obj.length == 5){
                    user.setHysStorageId(Long.valueOf(obj[0]==null?"":obj[0].toString()));
                    user.setHysUserName(obj[1]==null?"":obj[1].toString());
                    user.setHysUserPassword(obj[2]==null?"":obj[2].toString());
                    user.setHysGroupId(Long.valueOf(obj[3]==null?"":obj[3].toString()));
                    user.setHysDesc(obj[4]==null?"":obj[4].toString());                   
                }            
            }           
        }*/
       return user;       
    }
    
   
    
    
    
}
