/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.service;

import com.unilever.entity.exp.TreeNode;
import com.unilever.util.HysDataSqlResource;
import com.unilever.util.LogManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ussopp.Su
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class TreeNodeService {
    private static final Logger logger = LogManager.getLogger(TreeNodeService.class);
    @PersistenceContext(unitName = "TestPU")
    private EntityManager em;
    
    
    
    /**
     * 左侧树查询--关联user查询
     * @param 
     * @return 
     */
    public List<TreeNode> findSubNodes_User(TreeNode parNode,Long userId,String userName){
        logger.info("-------plant.TreeNodeService.findFirstTreeNode------");  
        List<TreeNode> subNodeList = new ArrayList<TreeNode>();
        StringBuilder sql = new StringBuilder("");             
        //用户直接关联区域,也就不存在用户只关联某个区域的某个生产线或者生产组的情况了 //默认根节点等级 为 0
        if("0".equals(parNode.getCurrLevel())){
            sql.append(HysDataSqlResource.FIND_TREE_NODE_SQL1);
        }else if("1".equals(parNode.getCurrLevel())||"2".equals(parNode.getCurrLevel())){
           //此时会传过来树信息 ，包括树节点，名称和等级
            sql.append(HysDataSqlResource.FIND_TREE_NODE_SQL2);    
        }
        
        Query query  = em.createNativeQuery(sql.toString());
        if(query!=null){
            if("0".equals(parNode.getCurrLevel())){
                query.setParameter("userId", userId);
                query.setParameter("userName", userName); 
                query.setParameter("dataPid", Long.valueOf(parNode.getId()));
            }else if("1".equals(parNode.getCurrLevel())||"2".equals(parNode.getCurrLevel())){
                query.setParameter("dataPid", Long.valueOf(parNode.getId()));
            }
            List treeList = query.getResultList();
            if(treeList!=null&&treeList.size()>0){
                Iterator it = treeList.iterator();
                TreeNode treeNode = null;
                while(it.hasNext()){
                    treeNode = new TreeNode();
                    Object[] obj = (Object[])it.next();
                    if(obj!=null&&obj.length>0){
                        treeNode.setId(obj[0]==null?"":obj[0].toString());
                        treeNode.setName(obj[1]==null?"":obj[1].toString());
                        treeNode.setPid(obj[2]==null?"":obj[2].toString());
                        treeNode.setCurrLevel(obj[3]==null?"":obj[3].toString());         
                        treeNode.setIsParent(obj[4]==null?"":obj[4].toString());  
                        treeNode.setDataState(obj[5]==null?"":obj[5].toString());
                    }
                  subNodeList.add(treeNode);  
                }               
            }   
        }
        return subNodeList;
    }
    
     /**
     * 左侧树查询--不关联user查询
     * @param 
     * @return 
     */
    public List<TreeNode> findSubNodes(TreeNode parNode){
        logger.info("-------plant.TreeNodeService.findFirstTreeNode------");  
        List<TreeNode> subNodeList = new ArrayList<TreeNode>();
        StringBuilder sql = new StringBuilder("");                   
        sql.append(HysDataSqlResource.FIND_TREE_NODE_SQL2);         
        Query query  = em.createNativeQuery(sql.toString());
        if(query!=null){
            query.setParameter("dataPid", Long.valueOf(parNode.getId()));           
            List treeList = query.getResultList();
            if(treeList!=null&&treeList.size()>0){
                Iterator it = treeList.iterator();
                TreeNode treeNode = null;
                while(it.hasNext()){
                    treeNode = new TreeNode();
                    Object[] obj = (Object[])it.next();
                    if(obj!=null&&obj.length>0){
                        treeNode.setId(obj[0]==null?"":obj[0].toString());
                        treeNode.setName(obj[1]==null?"":obj[1].toString());
                        treeNode.setPid(obj[2]==null?"":obj[2].toString());
                        treeNode.setCurrLevel(obj[3]==null?"":obj[3].toString());         
                        treeNode.setIsParent(obj[4]==null?"":obj[4].toString());  
                        treeNode.setDataState(obj[5]==null?"":obj[5].toString());
                    }
                  subNodeList.add(treeNode);  
                }               
            }   
        }
        return subNodeList;
    }
    
    /**
     * 根据父节点查询其下的子节点
     * @param parNode
     * @return 
     */
    public List<TreeNode> findTreeByParNode(TreeNode parNode){
        logger.info("------plant.TreeNodeService.findTreeByParNode----");
        List<TreeNode> treeNodeList = null;
        String id = parNode.getId();
        String pName = parNode.getName();

        if("root".equals(pName)){
            //根节点时，查询所有区域                
            treeNodeList = this.findAllArea();      
        }else{
            //其余情况                
            treeNodeList = this.findTreeNodeByPid(id);
            
        }
        
        return treeNodeList;
    }
    /**
     * 查询所有区域
     * @return 
     */
    public List<TreeNode> findAllArea(){
        logger.info("---plant.HysDataService.findAllArea----");
        List<TreeNode> areaList = new ArrayList<TreeNode>();
        Query findAllAreaQ = em.createNativeQuery(HysDataSqlResource.FIND_ALL_AREA_SQL);             
        List list = findAllAreaQ.getResultList();
        if(list!=null&&list.size()>0){
            Iterator it = list.iterator();
            TreeNode treeNode = null;
            while(it.hasNext()){
                Object[] obj = (Object[])it.next();
                treeNode = new TreeNode();
                if(obj.length == 5){
                    treeNode.setId(obj[0]==null?"":obj[0].toString());
                    treeNode.setName(obj[1]==null?"":obj[1].toString());
                    treeNode.setPid(obj[2]==null?"":obj[2].toString());
                    treeNode.setCurrLevel(obj[3]==null?"":obj[3].toString());
                    treeNode.setDataState(obj[4]==null?"":obj[4].toString());
                }
                areaList.add(treeNode);
            }
        }
        
        return areaList;
    }
    
    /**
     * 根据父节点的id查询其下的子节点
     * @param pid
     * @return 
     */
    public List<TreeNode> findTreeNodeByPid(String pid){
        logger.info("-----plant.HysDataService.findTreeNodeByPid------");
        List<TreeNode> treeNodeList = new ArrayList<TreeNode>();      
        Query findDataByPidQ = em.createNamedQuery("HysData.findDataByPid");
        findDataByPidQ.setParameter("hysDataPid", Long.valueOf(pid));
        List list = findDataByPidQ.getResultList();
        if(list!=null&&list.size()>0){
            Iterator it = list.iterator();
            TreeNode treeNode = null;
            while(it.hasNext()){
                treeNode = new TreeNode();
                Object[] obj = (Object[])it.next();
                if(obj.length == 5){
                    treeNode.setId(obj[0]==null?"":obj[0].toString());
                    treeNode.setName(obj[1]==null?"":obj[1].toString());
                    treeNode.setPid(obj[2]==null?"":obj[2].toString());
                    treeNode.setCurrLevel(obj[3]==null?"":obj[3].toString());
                    treeNode.setDataState(obj[4]==null?"":obj[4].toString());                  
                }
                treeNodeList.add(treeNode);
            }
        }
        
        return treeNodeList;
    }
             
    
    
    
}
