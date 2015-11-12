/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.service;

import com.unilever.entity.exp.Result;
import com.unilever.util.LogManager;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.unilever.entity.HysGroup;
import com.unilever.entity.exp.Parameter;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Ussopp.Su
 */
public class HysGroupService {
    private static final Logger logger = LogManager.getLogger(HysGroupService.class);
    @PersistenceContext(unitName = "TestPU")
    private EntityManager em;
    
    /**
     * 判断角色名称是否已经存在 --用于新增的校验
     * @param groupName
     * @return 
     */
    public boolean judgeGroupNameExistForAdd(String groupName){
        boolean judge = false;
        Query query = em.createNamedQuery("HysGroup.findByHysGroupName"); 
        query.setParameter("hysGroupName", groupName);
        List  list = query.getResultList();
        int i = list.size();
        if(i>=1){
            //已经存在
            judge = false;
        }else{
            judge = true;
        }
        
        return judge;
    }
    
    /**
     * 新增角色
     * @param hysGroup
     * @return 
     */
    public Result addUserGroup(HysGroup hysGroup){
        logger.info("----plant.HysGroupService.addUserGroup---");
        String result = "successed";
        try {
            if(hysGroup!=null){
                boolean judge = this.judgeGroupNameExistForAdd(hysGroup.getHysGroupName());
                if(judge == true){
                    em.persist(hysGroup);
                    
                }else{
                    //名称已经存在，不能新增
                    result = "nameExists";
                }
            }
        } catch (Exception e) {
            result = "failed";
            logger.severe("[addUserGroup Exception]"+e.getMessage());
        }
        
        return new Result(result);
    }
    
    /**
     * 判断角色名称是否已经存在 --用于修改的校验
     * @param groupName
     * @return 
     */
    public boolean judgeGroupNameExistForEdit(String groupName){
        boolean judge = false;
        Query query = em.createNamedQuery("HysGroup.findByHysGroupName"); 
        query.setParameter("hysGroupName", groupName);
        List  list = query.getResultList();
        int i = list.size();
        if(i<=1){
            //存在一个或没有(没有的情况，可以忽略)
            judge = true;
        }else{
            judge = false;
        }
        
        return judge;
    }
    
    /**
     * 修改角色信息
     * @param hysGroup
     * @return 
     */
    public Result editUserGroup(HysGroup hysGroup){
        logger.info("---plant.HysGroupService.editUserGroup--");
        String result = "successed";
        try {
            if(hysGroup!=null){
                boolean judge = this.judgeGroupNameExistForEdit(hysGroup.getHysGroupName());
                if(judge == true){
                    em.merge(hysGroup);
                    
                }else{
                    //名称已经存在
                    result = "nameExists";
                }
            }
        } catch (Exception e) {
            result = "failed";
        }
        
        return new Result(result);
    }
    
    /**
     * 删除角色 --单个删除
     * @return 
     */
    public Result delUserGroup(Parameter param){
        logger.info("------plant.HysGroupService.delUserGroup-----");
        String result = "successed";
        try {
            String id  = param.getIds();
            //根据角色id，查看是否还关联着用户  HysUser.findByHyGroup
            Query query = em.createNamedQuery("HysUser.findByHyGroup");
            query.setParameter("hysGroupId", Long.valueOf(id));
            List list = query.getResultList();
            if(list!=null&&list.size()>0){
                //角色跟用户还有关联
                result = "existRelation";
            }else{
                //没有关联，可以删除 //根据角色id来删除，还需要删除角色与权限的关联 
                // 1.删除角色信息
                Query deleteByGroupIdQ1 = em.createNamedQuery("HysGroup.deleteByHysStorageId");
                deleteByGroupIdQ1.setParameter("hysStorageId", Long.valueOf(id));
                deleteByGroupIdQ1.executeUpdate();
                
                //2.删除角色与权限的关联 
                Query deleteByGroupIdQ2 = em.createNamedQuery("HysGroupRight.deleteByGroupId");
                deleteByGroupIdQ2.setParameter("groupId", Long.valueOf(id));
                deleteByGroupIdQ2.executeUpdate();
                
            }
            
        } catch (Exception e) {
            result = "failed";
            logger.severe("[delUserGroup Exception]"+e.getMessage());
        }
        
        return new Result(result);
    }
    
    
    
}
