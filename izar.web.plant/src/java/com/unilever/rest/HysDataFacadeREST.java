/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.rest;

import com.unilever.entity.HysData;
import com.unilever.entity.exp.HysAreaExp;
import com.unilever.entity.exp.PageHysAreaExp;
import com.unilever.entity.exp.Parameter;
import com.unilever.entity.exp.Result;
import com.unilever.entity.exp.TreeNode;
import com.unilever.service.HysDataService;
import com.unilever.service.TreeNodeService;
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
 * 数据集信息
 * @author Ussopp.Su
 */
@Stateless
@Path("hysdata")
public class HysDataFacadeREST extends AbstractFacade<HysData> {
    private static final Logger logger = LogManager.getLogger(HysDataFacadeREST.class);
    @PersistenceContext(unitName = "TestPU")
    private EntityManager em;

    public HysDataFacadeREST() {
        super(HysData.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @EJB
    private HysDataService hysDataService;
    
    @EJB
    private TreeNodeService treeNodeService;
    
    
    /**
     * 返回所有data ---- 用于测试
     * @return 
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/findAllData")
    public List<HysData> findAllData(){
        logger.info("-------plant.HysDataFacadeREST.findAllData-------");
        Query query = em.createNamedQuery("HysData.findAll");
        List<HysData> dataList = query.getResultList();
        return dataList;
        
    }
    
    /**
     * 查询所有的区域信息
     * @param page
     * @param req
     * @return 
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/findleafInfoByNode")
    public PageHysAreaExp<HysAreaExp> findleafInfoByNode(PageHysAreaExp page){    
        logger.info("------plant.HysDataFacadeREST.findAreaBasicInfo-------");

        return hysDataService.findleafInfoByNode (page);
    }
    
    /**
     *  左侧树查询 //分级查询 --关联了user查询
     * @param parNode
     * @param req
     * @return 
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/findSubNodesByUser")
    public List<TreeNode> findSubNodes_User(TreeNode parNode,@Context HttpServletRequest req){
        logger.info("------plant.HysDataFacadeREST.findSubNodes-------");       
        HttpSession session = req.getSession();
        String userName = (String)session.getAttribute(Constant.SESSION_USER_NAME_KEY);
        Long userId = (Long)session.getAttribute(Constant.SESSION_USER_ID_KEY);

        return  treeNodeService.findSubNodes_User(parNode, userId, userName);
    }
    
    
    /**
     *  左侧树查询 //分级查询 --不考虑user，展示所有的数据集范围
     * @param parNode
     * @param req
     * @return 
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/findSubNodes")
    public List<TreeNode> findSubNodes(TreeNode parNode){
        logger.info("------plant.HysDataFacadeREST.findSubNodes-------");       
        return  treeNodeService.findSubNodes(parNode);
    }
    
    
    /**
     * 添加区域  //暂时根据确认添加完成后，不需要跟用户关联
     * @param hysAreaExp
     * @return 
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/addArea")
    public Result addArea(HysAreaExp hysAreaExp){
        logger.info("------plant.HysDataFacadeREST.addArea-------");
        return hysDataService.addArea(hysAreaExp);
    }
     
    /**
     * 修改区域信息--（页面中整行）
     * @param hysAreaExp
     * @return 
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/editArea")
     public Result editArea(HysAreaExp hysAreaExp){
         logger.info("----------plant.HysDataFacadeREST.editArea--------");
         return hysDataService.editArea(hysAreaExp);
     }
    
    /**
     * 删除区域信息（对应的生产组）//支持批量
     * @param parameter
     * @return 
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/deleteArea")
    public Result deleteArea(Parameter parameter){
         logger.info("----------plant.HysDataFacadeREST.editArea--------");
         return hysDataService.deleteArea(parameter);
    
    }
    
    /**
     * 查询用户关联的所有的区域  --（暂时不使用）
     * @param req
     * @return 
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/findAllArea")
    public List<HysAreaExp> findAllArea(){
        logger.info("----------plant.HysDataFacadeREST.findAllArea--------");
        
        return hysDataService.findAllArea();
    }
    
    /**
     * 根据父节点查询其下的子节点 --用于新增区域时候，选择已有的节点
     * @param parNode
     * @return 
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/findTreeByParNode")
    public List<TreeNode> findTreeByParNode(TreeNode parNode){
        logger.info("----------plant.HysDataFacadeREST.findTreeByParNode--------");
        
        return treeNodeService.findTreeByParNode(parNode);
    }
    
    
}
