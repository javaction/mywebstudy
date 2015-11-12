/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.service;

import com.unilever.entity.HysCollector;
import com.unilever.entity.HysMeterCollector;
import com.unilever.entity.exp.PageHysCollector;
import com.unilever.entity.exp.Parameter;
import com.unilever.entity.exp.Result;
import com.unilever.util.HysBasicInfoSqlResource;
import com.unilever.util.LogManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * 
 * @author Ussopp.Su
 */
@Stateless
public class HysCollectorService {
    
    private static final Logger logger = LogManager.getLogger(HysCollectorService.class);
    @PersistenceContext(unitName = "TestPU")
    private EntityManager em;
    
    /**
     * 基础信息之采集器信息 --分页列表数据
     * @param page
     * @param userId
     * @param userName
     * @return 
     */
    public PageHysCollector<HysCollector> findCollectorBasicInfo(PageHysCollector page){
    logger.info("--------plant.HysCollectorService.findCollectorBasicInfo----------");
        PageHysCollector<HysCollector> pageCollectorExp = new PageHysCollector<HysCollector>();
        
        int beginNum = 0; //起始页
        StringBuilder sql = new StringBuilder("");
        sql.append("select c.hys_storage_id, c.hys_collector_number, c.hys_collector_type, c.hys_manufacturer, c.hys_remark  from hys_collector c where 1 = 1 ");
      
        //过滤条件 
        if(page.getHysCollectorNumber()!=null&&!"".equals(page.getHysCollectorNumber())){
            //过滤采集器号
            sql.append(" and c.hys_collector_number like  ?collectorNum ");
        }
        
        if(page.getHysCollectorType()!=null&&!"".equals(page.getHysCollectorType())){
            //过滤采集器类型
            sql.append(" and c.hys_collector_type like ?collectorType ");
        }
        
        if(page.getHysManufacturer()!=null&&!"".equals(page.getHysManufacturer())){
            //过滤厂商
            sql.append(" and c.hys_manufacturer like ?manufacturer ");
        }
        
        if(page.getHysRemark()!=null&&!"".equals(page.getHysRemark())){
            //过滤备注
            sql.append(" and c.hys_remark like ?remark  ");
        }
        
        //排序条件
        if("hysCollectorNumber".equals(page.getSidx())){
            //按采集器号排序
            sql.append(" order by c.hys_collector_number ").append(page.getSord());
        }
        if("hysCollectorType".equals(page.getSidx())){
            //按型号排序
            sql.append(" order by c.hys_collector_type ").append(page.getSord());
        }
        if("hysManufacturer".equals(page.getSidx())){
            //按厂商排序
            sql.append(" order by c.hys_manufacturer ").append(page.getSord());
        }
        if("hysRemark".equals(page.getSidx())){
            //按备注排序
            sql.append(" order by c.hys_remark ").append(page.getSord());
        }
        
        //查询总行数，用于分页
        Query countQuery = em.createNativeQuery("select count(1) from ( "+sql.toString()+" ) dual ");      
        if(countQuery!=null){            
            //只是查询count的话，不需要排序，这里只传入需要过滤的字段数据
            //如果存在过滤的话，这里需要根据传来的过滤字段，对数据进行过滤       
            //过滤条件 
            if(page.getHysCollectorNumber()!=null&&!"".equals(page.getHysCollectorNumber())){
                //过滤采集器号
                countQuery.setParameter("collectorNum", "%"+page.getHysCollectorNumber()+"%");
            }

            if(page.getHysCollectorType()!=null&&!"".equals(page.getHysCollectorType())){
                //过滤采集器类型
                countQuery.setParameter("collectorType", "%"+page.getHysCollectorType()+"%");
            }

            if(page.getHysManufacturer()!=null&&!"".equals(page.getHysManufacturer())){
                //过滤厂商
                countQuery.setParameter("manufacturer", "%"+page.getHysManufacturer()+"%");
            }

            if(page.getHysRemark()!=null&&!"".equals(page.getHysRemark())){
                //过滤备注
                countQuery.setParameter("remark", "%"+page.getHysRemark()+"%");
            }
            
            List countList = countQuery.getResultList();
            BigDecimal countNum = new BigDecimal(countList.get(0).toString());
            float f = countNum.floatValue();
            //总页数
            int totalpages = (int) Math.ceil(f/page.getPagesize());
            //封装分页信息
            pageCollectorExp.setCurrpage(page.getCurrpage());
            pageCollectorExp.setTotalpages(totalpages);
            pageCollectorExp.setTotalrecords(countNum.intValue());
            pageCollectorExp.setPagesize(page.getPagesize());
            //计算从第几条查询，如果是第一页，默认为0
            if(page.getCurrpage()>1){
                beginNum = (page.getCurrpage()-1)*page.getPagesize();
            }               
        }
        
        Query query = em.createNativeQuery(sql.toString());
        query.setFirstResult(beginNum);
        query.setMaxResults(page.getPagesize());
        
        //如果存在需要过滤的字段，需要再进行对应的传参，如count处 //过滤条件 
        if(page.getHysCollectorNumber()!=null&&!"".equals(page.getHysCollectorNumber())){
            //过滤采集器号
            query.setParameter("collectorNum", "%"+page.getHysCollectorNumber()+"%");
        }

        if(page.getHysCollectorType()!=null&&!"".equals(page.getHysCollectorType())){
            //过滤采集器类型
            query.setParameter("collectorType", "%"+page.getHysCollectorType()+"%");
        }

        if(page.getHysManufacturer()!=null&&!"".equals(page.getHysManufacturer())){
            //过滤厂商
            query.setParameter("manufacturer", "%"+page.getHysManufacturer()+"%");
        }

        if(page.getHysRemark()!=null&&!"".equals(page.getHysRemark())){
            //过滤备注
            query.setParameter("remark", "%"+page.getHysRemark()+"%");
        }
        
        List list = query.getResultList();
        if(list!=null&&list.size()>0){
            Iterator it = list.iterator();
            while(it.hasNext()){
                Object[] obj = (Object[])it.next();
                HysCollector hysCollector = new HysCollector();
                if(obj.length==5){
                    String storageId = obj[0]==null?"":obj[0].toString();
                    hysCollector.setHysStorageId(Long.valueOf(storageId));
                    hysCollector.setHysCollectorNumber(obj[1]==null?"":obj[1].toString());
                    hysCollector.setHysCollectorType(obj[2]==null?"":obj[2].toString());
                    hysCollector.setHysManufacturer(obj[3]==null?"":obj[3].toString());
                    hysCollector.setHysRemark(obj[4]==null?"":obj[4].toString());                   
                }
               pageCollectorExp.getRows().add(hysCollector);
            }
        }

        return pageCollectorExp;
    }
    
    
     /**
     * 新增采集器 //现在只是单纯的新增采集器，如果后期需求提出新的改动。再对这部分进行修改
     * @return 
     */
    public Result addCollector(HysCollector hysCollector){
    logger.info("------plant.HysCollectorFacadeREST.addCollector--------");
        String result = "successed";
        try {
            
            em.persist(hysCollector);
            
        } catch (Exception e) {
            result = "failed";
            logger.severe("[addCollector Exception]:--"+e.getMessage());
        }
        
        return new Result(result);
    }
    
    /**
     * 编辑采集器信息 //现在只是单纯的编辑采集器的基础信息，如果需要编辑对应的设备之类的，就需要加入相应的处理
     * @param hysCollector
     * @return 
     */
    public Result editCollector(HysCollector hysCollector){
        logger.info("------plant.HysCollectorFacadeREST.editCollector-----");
        String result = "successed";
        try{
           if(hysCollector!=null&&hysCollector.getHysStorageId()!=null){
               HysCollector collector = em.getReference(HysCollector.class, hysCollector.getHysStorageId());
               collector.setHysCollectorNumber(hysCollector.getHysCollectorNumber());
               collector.setHysCollectorType(hysCollector.getHysCollectorType());
               collector.setHysManufacturer(hysCollector.getHysManufacturer());
               collector.setHysRemark(hysCollector.getHysRemark());
               
               em.merge(collector);
           } 
        }catch(Exception e){
            result = "failed";
            logger.severe("[editCollector Exception]:--"+e.getMessage());
        }
        
        return new Result(result);
    }
    
    /**
     * 删除采集器，支持批量
     * @return 
     */
    public Result deleteCollector(Parameter parameter){
        logger.info("-------plant.HysCollectorFacadeREST.deleteCollector-----");
        String result = "successed";
        try {
            if(parameter.getIds()!=null&&!"".equals(parameter.getIds())){
                String ids = parameter.getIds();
                String str[] = ids.split(",");
                if(str!=null&&str.length>0){
                    //现在的参数是采集器的编号，如果要删除，还得删除设备和采集器的关联表
                    //根据采集器编号获取其id，用于删除关联表信息  HysCollector.findIdByCollectorNum
                    for(String s : str){                        
                        Long collectorId = Long.valueOf(s);
                        
                        //1.删除关联表  HysMeterCollector.deleteByCollectorId
                        Query deleteByCollectorIdQuery = em.createNamedQuery("HysMeterCollector.deleteByCollectorId");
                        deleteByCollectorIdQuery.setParameter("hysCollectorId", collectorId);
                        deleteByCollectorIdQuery.executeUpdate();
                        //2.删除采集器信息
                        Query deleteByCollectorNumQuery = em.createNamedQuery("HysCollector.deleteById");
                        deleteByCollectorNumQuery.setParameter("hysStorageId", collectorId);
                        deleteByCollectorNumQuery.executeUpdate();    
                    }
                }
            }
        } catch (Exception e) {
            result = "failed";
            logger.severe("[deleteCollector Exception]"+e.getMessage());
        }
        
        return new Result(result);
    }
    
    /**
     * 查询所有的采集器，用于设备新增和修改时候设置
     * @param parameter
     * @return 
     */
    public List<HysCollector> findAllCollectorForSet(Parameter parameter){
        logger.info("----plant.HysCollectorFacadeREST.findAllCollectorForSet----");
        List<HysCollector> collectorList = new ArrayList<HysCollector>();
        try {
            String meterId = parameter.getIds();
            Query findAllCollectorQ = em.createNativeQuery(HysBasicInfoSqlResource.FIND_ALL_COLLECTOR_FOR_SET_SQL);
            findAllCollectorQ.setParameter("meterId", Long.valueOf(meterId));
            List list = findAllCollectorQ.getResultList();
            if(list!=null&&list.size()>0){
               Iterator it = list.iterator(); 
               while(it.hasNext()){
                   HysCollector collector = null;
                   Object[] obj = (Object[])it.next();
                   if(obj.length==3){
                       collector = new HysCollector();
                       String id = obj[0].toString()==null?"":obj[0].toString();
                       collector.setHysStorageId(Long.valueOf(id));
                       collector.setHysCollectorNumber(obj[1].toString()==null?"":obj[1].toString());
                       collector.setIsSeting(obj[2].toString()==null?"":obj[2].toString());
                   }
                   collectorList.add(collector);
               } 
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("[findAllCollectorForSet Exception]"+e.getMessage());
        }
        
        return collectorList;
    }
 
    
    
       
}
