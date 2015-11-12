/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.rest;

import com.unilever.entity.HysParameter;
import com.unilever.entity.exp.PageBaseExp;
import com.unilever.entity.exp.Parameter;
import com.unilever.entity.exp.Result;
import com.unilever.job.DatabaseBackupTimeJobStarter;
import com.unilever.service.HysParameterService;
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
@Path("hysparameter")
public class HysParameterFacadeREST extends AbstractFacade<HysParameter> {
    private static final Logger logger = LogManager.getLogger(HysParameterFacadeREST.class);
    @PersistenceContext(unitName = "TestPU")
    private EntityManager em;

    public HysParameterFacadeREST() {
        super(HysParameter.class);
    }
 
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @EJB
    private HysParameterService hysParameterService ;
    
     /**
      * 获得所有参数 ---用于测试
      * @return 
      */   
    @POST  
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/findAllPara")
    public List<HysParameter> findAllPara(){
        Query query = em.createNamedQuery("HysParameter.findAllPara");     
        List<HysParameter> li = query.getResultList();
        return li;
    }
     
    
    /**
     * 立即备份数据库
     * @param param
     * @return 
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/postgresBackup")
    public Result postgresBackup(Parameter param){  
        
        return hysParameterService.postgresBackup(param);
    }
    
   /**
     * 备份数据库备份功能开关方法
     * @param parameter
     * @return 
     */
    @POST
    @Path("/databaseBackupSwitch")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Result databaseBackupSwitch(Parameter parameter) {
    
        return hysParameterService.databaseBackupSwitch(parameter);
    }
    
    /**
     * 编辑参数
     * @param paramter
     * @return 
     */
    @POST
    @Path("/editHySysParameter")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Result editHySysParameter(Parameter paramter) {        
        String result = "successed";
        Result results =null;
        try {
            results = hysParameterService.editHySysParameter(paramter);
            //判断是否需要重新调用定时备份数据库,如果时间间隔做了修改，则需要调用下定时备份数据库job  // true / false
            if(results.getMessage()!=null&&"true".equals(results.getMessage())){
                DatabaseBackupTimeJobStarter.startJob(hysParameterService);
            }           
        } catch (Exception e) {
            result = "failed";
            results = new Result(result);
            logger.severe("[editHySysParameter Exception]:"+e.getMessage());
        }
        return results;
        
    }
    
    /**
     * 检查盘符是否存在
     * @return 
     */
    @POST
    @Path("/checkDisk")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Result checkDisk(Parameter parameter) {
        return hysParameterService.checkDisk(parameter);
    }
    
    /**
     * 查询所有参数信息 //目前只有数据库备份路径和时间间隔 
     * @return 
     */
    @POST
    @Path("/findAllParameters")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public PageBaseExp<HysParameter> findAllParameters(){
    
        return hysParameterService.findAllParameters();
    }
    
    
}
