/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.rest;

import com.unilever.entity.HysGroup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.unilever.entity.HysUser;
import com.unilever.entity.exp.Login;
import com.unilever.entity.exp.Parameter;
import com.unilever.entity.exp.Result;
import com.unilever.service.HysUserService;
import com.unilever.util.Constant;
import com.unilever.util.LogManager;
import com.unilever.util.MD5Util;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;

/**
 *
 * @author Administrator
 */
@Stateless
@Path("hysuser")
public class HysUserFacadeREST extends AbstractFacade<HysUser> {
    private static final Logger logger = LogManager.getLogger(HysUserFacadeREST.class);
    
    @PersistenceContext(unitName = "TestPU")
    private EntityManager em;

    public HysUserFacadeREST() {
        super(HysUser.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @EJB
    private HysUserService hysUserService;
    
    @Context 
    private HttpServletRequest req;

    /**
     * 查询所有用户
     * @param user
     * @return 
     */
    @POST  
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/findAllUsers")
    public List<HysUser> findAllUsers() {
        Query query = em.createNamedQuery("HysUser.findAll");
        List<HysUser> userList = query.getResultList();
        return userList; 
    }
    
    /**
     * 新增用户
     * @param hysUser
     * @return 
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addUser")
    public Result addUser(HysUser hysUser) {
        String result = "successed";
        try{
            hysUser.setHysUserPassword(MD5Util.mD5(hysUser.getHysUserPassword()));
            em.persist(hysUser);
        }
        catch(Exception e){
            result = "failed";
            e.printStackTrace();
        }
        return new Result(result);
    }
    
    /**
     * 编辑用户
     * @param hysUser
     * @return 
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/editUser")
    public Result editUser(HysUser hysUser) {
        String result = "successed";
        try{
            hysUser.setHysUserPassword(MD5Util.mD5(hysUser.getHysUserPassword()));
            em.merge(hysUser);
        }
        catch(Exception e){
            result = "failed";
            e.printStackTrace();
        }
        return new Result(result);
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/delUser")
    public Result delUser(Parameter paramter) {
        String result = "successed";
        try{
            //获取传过来的主键字符串
            String s = paramter.getIds();
            String[] strs = s.split(",");
            for(int i = 0; i < strs.length; i ++){
                //删除用户信息表
                em.remove(em.getReference(HysUser.class, new Long(strs[i])));
            }
        }
        catch(Exception e){
            result = "failed";
            e.printStackTrace();
        }
        return new Result(result);
    }
    
    /**
     * 根据登陆的用户名和密码
     * @param user 其中，只需传hyName和hyPassword
     * @return 
     */
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Login login(HysUser user) {
        user = hysUserService.getUserByNameAndPassword(user.getHysUserName(), user.getHysUserPassword());
        Login login = new Login();
        if(null==user)
        {
            login.setLogin("failure");
        }else{
            login.setLogin("successful");
            login.setUserId(user.getHysStorageId());
            login.setUserName(user.getHysUserName());
            if(null == user.getGroup()) login.setGroupName("");
            else login.setGroupName(user.getGroup().getHysGroupName());
            
            //保存session
            HttpSession session = req.getSession();
            session.setAttribute(Constant.SESSION_USER_ID_KEY, user.getHysStorageId());
            session.setAttribute(Constant.SESSION_USER_NAME_KEY, user.getHysUserName());
            //session.setAttribute(Constant.SESSION_USER_GROUP_KEY, groupName);
            
        }
        
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
        */
        return login;
    }
    
    /**
     * 登出系统
     * @param req
     * @return 
     */
    @POST
    @Path("/logout")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Login logout(@Context HttpServletRequest req){
        Login login = new Login();
        HttpSession session = req.getSession();
        session.setAttribute(Constant.SESSION_USER_ID_KEY, null);
        session.setAttribute(Constant.SESSION_USER_NAME_KEY, null);
        session.setAttribute(Constant.SESSION_USER_GROUP_KEY, null);
        
        //保存用户登出信息?
        
        login.setLogin("logoutSuccess");
        return login;
    }
    
    
    /**
     * 用户修改密码
     * @param parm
     * @param userName
     * @return 
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/updateUserPwd")
    public Result updateUserPwd(Parameter parm,@Context HttpServletRequest req){  
        logger.info("----------plant.HysUserFacadeREST.updateUserPwd-------------");
        HttpSession session = req.getSession();
        String userName = (String)session.getAttribute(Constant.SESSION_USER_NAME_KEY);

        return hysUserService.updateUserPwd(parm, userName);
    }
    
    
}
