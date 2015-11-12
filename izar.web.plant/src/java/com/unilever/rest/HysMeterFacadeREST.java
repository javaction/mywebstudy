/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.rest;

import com.unilever.entity.HysMeter;
import com.unilever.entity.exp.PageHysMeter;
import com.unilever.entity.exp.Parameter;
import com.unilever.entity.exp.Result;
import com.unilever.service.HysMeterService;
import com.unilever.util.Constant;
import com.unilever.util.LogManager;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Ussopp.Su
 */
@Stateless
@Path("hysmeter")
public class HysMeterFacadeREST extends AbstractFacade<HysMeter> {
    private static final Logger logger = LogManager.getLogger(HysMeterFacadeREST.class); 
    @PersistenceContext(unitName = "TestPU")
    private EntityManager em;

    public HysMeterFacadeREST() {
        super(HysMeter.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @EJB
    private HysMeterService hysMeterService ;
    
     /**
     * 查询所有的表 *-----用于测试
     * @return 
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/findAllMeter")
    public List<HysMeter> findAllMeter(){
        Query query = em.createNamedQuery("HysMeter.findAllMeter");
        List<HysMeter> list = query.getResultList();
        return list; 
    }
    
    /**
     * 基础信息之设备信息 --分页列表数据
     * @param page
     * @param userId
     * @param userName
     * @return 
     */
     @POST
     @Produces(MediaType.APPLICATION_JSON)
     @Consumes(MediaType.APPLICATION_JSON)
     @Path("/findMeterBasicInfo")
     public PageHysMeter<HysMeter> findMeterBasicInfo(PageHysMeter page){
         logger.info("------plant.HysMeterFacadeREST.FindMeterBasicInfo-------");
                 
         return hysMeterService.findMeterBasicInfo(page);   
     }
     
     
     /**
      * 新增设备
      * @param hysMeter
      * @return 
      */
     @POST
     @Produces(MediaType.APPLICATION_JSON)
     @Consumes(MediaType.APPLICATION_JSON)
     @Path("/addMeter")
     public Result addMeter(HysMeter hysMeter){
        logger.info("-----plant.HysMeterFacadeREST.addMeter--------");   
        return hysMeterService.addMeter(hysMeter);       
     }
    
     
     /**
      * 删除设备，支持批量删除
      * @param
      * @return 
      */
     @POST
     @Produces(MediaType.APPLICATION_JSON)
     @Consumes(MediaType.APPLICATION_JSON)
     @Path("/deleteMeter")
     public Result deleteMeter(Parameter parameter){
         logger.info("--------plant.HysMeterFacadeREST.deleteMeter----------");        
         return hysMeterService.deleteMeter(parameter);
     }
     
     /**
      * 修改设备基本信息
      * @param hysMeter
      * @return 
      */
     @POST
     @Produces(MediaType.APPLICATION_JSON)
     @Consumes(MediaType.APPLICATION_JSON)
     @Path("/editMeter")
     public Result editMeter(HysMeter hysMeter){
         logger.info("--------plant.HysMeterFacadeREST.editMeter----------");        
         return hysMeterService.editMeter(hysMeter);
     
     }
     
     /**
      * 查询所有未分配下去的设备，用于给区域、生产线、生产组分配..(设备只能分配一次)
      * @return 
      */
     @POST
     @Produces(MediaType.APPLICATION_JSON)
     @Consumes(MediaType.APPLICATION_JSON)
     @Path("/findAllMeterForSet")
     public List<HysMeter> findAllMeterForSet(Parameter parameter){
         logger.info("--------plant.HysMeterFacadeREST.findAllMeterForSet----------");
         return hysMeterService.findAllMeterForSet(parameter);
     }
     
     
}
