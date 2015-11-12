/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.rest;

import com.unilever.entity.HysCollector;
import com.unilever.entity.exp.PageHysCollector;
import com.unilever.entity.exp.Parameter;
import com.unilever.entity.exp.Result;
import com.unilever.service.HysCollectorService;
import com.unilever.util.LogManager;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
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
@Path("hyscollector")
public class HysCollectorFacadeREST extends AbstractFacade<HysCollector> {
    private static final Logger logger = LogManager.getLogger(HysCollectorFacadeREST.class);
    @PersistenceContext(unitName = "TestPU")
    private EntityManager em;

    public HysCollectorFacadeREST() {
        super(HysCollector.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @EJB
    private HysCollectorService hysCollectorService;
    
    /**
     * 查询所有集中器 --用于测试
     * @return 
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/findAllCollector")
    public List<HysCollector> findAllCollector(){  
        logger.info("----plant.HysCollectorFacadeREST.findAllCollector----");
        Query query = em.createNamedQuery("HysCollector.findAll");
        List<HysCollector> collectorList = query.getResultList();
    
        return collectorList;
    }
    
    /**
     * 基础信息之采集器信息 --分页列表数据
     * @param page
     * @param req
     * @return 
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/findCollectorBasicInfo")
    public PageHysCollector<HysCollector> findCollectorBasicInfo(PageHysCollector page){
        logger.info("----------plant.HysCollectorFacadeREST.findCollectorBasicInfo---------");     
        return hysCollectorService.findCollectorBasicInfo(page);
    }
    
    
    /**
     * 新增采集器
     * @param hysCollector
     * @return 
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addCollector")
    public Result addCollector(HysCollector hysCollector){
        logger.info("-------plant.HysCollectorFacadeREST.addCollector-------");
        return hysCollectorService.addCollector(hysCollector);
    }
    
    
    /**
      * 编辑采集器信息
      * @param hysCollector
      * @return 
      */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/editCollector")
    public Result editCollector(HysCollector hysCollector){         
         logger.info("--------plant.HysCollectorFacadeREST.editCollector----------");        
         return hysCollectorService.editCollector(hysCollector);
      
    }
      
    /**
     * 删除采集器，支持批量
     * @param parameter
     * @return 
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/deleteCollector")
    public Result deleteCollector(Parameter parameter){
        logger.info("-------plant.HysCollectorFacadeREST.deleteCollector-------");
        return hysCollectorService.deleteCollector(parameter);
    }
      
    
      /**
       * 查询所有的采集器，用于设备新增和修改时候设置
       * @param parameter
       * @return 
       */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/findAllCollectorForSet")
    public List<HysCollector> findAllCollectorForSet(Parameter parameter){
        logger.info("---plant.HysCollectorFacadeREST.findAllCollectorForSet-----");
        return hysCollectorService.findAllCollectorForSet(parameter);
    }
    
}
