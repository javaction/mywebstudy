/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.rest;

import com.unilever.entity.HysGroup;
import com.unilever.entity.exp.Parameter;
import com.unilever.entity.exp.Result;
import com.unilever.util.LogManager;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Ussopp.Su
 */
@Stateless
@Path("hysgroup")
public class HysGroupFacadeREST extends AbstractFacade<HysGroup> {
    private static final Logger  logger = LogManager.getLogger(HysGroupFacadeREST.class);
    @PersistenceContext(unitName = "TestPU")
    private EntityManager em;

    public HysGroupFacadeREST() {
        super(HysGroup.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    /**
     * 返回所有的角色 ---用于测试
     * @return 
     */
    @POST
    @Path("/findAll")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<HysGroup> findAll(){
        
        Query query = em.createNamedQuery("HysGroup.findAll");
        List<HysGroup> list = query.getResultList();
        return list;
    }
    
    //测试 @manytoMany 
    //测试后，可以级联删除,but..
    @POST
    @Path("/dele")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Result dele(Parameter parameter){
        logger.info("---dele.Test--");
        String result = "successed";
        try {
            String ids = parameter.getIds();
            logger.info("--ids--:"+ids);
            String id[] = ids.split(",");
            if(id.length>0){
                for(String groupId : id){
                    Query query =  em.createNamedQuery("HysGroup.deleteByHysStorageId");
                    query.setParameter("hysStorageId", Long.valueOf(groupId));
                    query.executeUpdate();
                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return new Result(result);
    } 
    
    //测试中间表的情况
    @POST
    @Path("/addGroup")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Result addGroup(HysGroup group){
        logger.info("---addGroup--");
        String result = "successed";
        try {
            
        } catch (Exception e) {
        
        }
        return new Result(result);
    }
    
    
    //测试修改
    @POST
    @Path("/editGroup")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Result editGroup(HysGroup group){
        logger.info("---editGroup--");
        String result = "successed";
        try {
            em.merge(group);
            
        } catch (Exception e) {
        
        }
        return new Result(result);
    }
    
    
}
