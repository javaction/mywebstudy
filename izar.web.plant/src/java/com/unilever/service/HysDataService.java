/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.service;

import com.unilever.dao.HysDataDao;
import com.unilever.entity.HysData;
import com.unilever.entity.exp.HysAreaExp;
import com.unilever.entity.exp.PageHysAreaExp;
import com.unilever.entity.exp.Parameter;
import com.unilever.entity.exp.Result;
import com.unilever.util.HysDataSqlResource;
import com.unilever.util.LogManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Ussopp.Su
 */
@Stateless
public class HysDataService {
    private static final Logger logger = LogManager.getLogger(HysDataService.class);
    @PersistenceContext(unitName = "TestPU")
    private EntityManager em;
    
    @EJB
    private HysDataDao hysDataDao;
     
    
    /**
     * 查询所有的区域信息  --关联了user
     * @param page
     * @param userId
     * @param userName
     * @return 
     */
    public PageHysAreaExp<HysAreaExp> findAreaBasicInfo(PageHysAreaExp page,Long userId,String userName){
        logger.info("------plant.HysDataService.findAreaBasicInfo--------");
        //对于 userID 和 userName 用于后期的关联用户的数据集..
        PageHysAreaExp<HysAreaExp> pageAreaExp = new PageHysAreaExp<HysAreaExp>();
        int beginNum = 0; //起始页
        StringBuilder sql = new StringBuilder(""); 
        sql.append(HysDataSqlResource.FIND_ALL_AREA_BESIC_INFO1);
        
        //存在过滤条件      
        if(page.getAreaName()!=null&&!"".equals(page.getAreaName())){
            //过滤区域
            sql.append(" and mm.areaName like ?areaName ");
        }
        if(page.getBeltlineName()!=null&&!"".equals(page.getBeltlineName())){
            //过滤生产线
            sql.append(" and mm.beltlineName like ?beltlineName ");
        }
        if(page.getWorkTeamName()!=null&&!"".equals(page.getWorkTeamName())){
            //过滤生产组
            sql.append(" and mm.workTeamName like ?workTeamName ");
        }
        if(page.getCollectorNum()!=null&&!"".equals(page.getCollectorNum())){
            //过滤采集器
            sql.append(" and mm.hys_collector_number like ?collectorNum ");
        }
        if(page.getMeterNum()!=null&&!"".equals(page.getMeterNum())){
            //过滤设备号
            sql.append(" and mm.hys_meter_number like ?meterNum ");
        }
        if(page.getMeterCode()!=null&&!"".equals(page.getMeterCode())){
            //过滤计量类型
            sql.append(" and mm.hys_meter_code like ?meterCode ");
        }
        
        //根据左侧树过滤
        if(page.getTreeLevel() == 1){
            //区域节点时
            sql.append(" and mm.areaName = ?areaName1 ");
        }else if(page.getTreeLevel() == 2 &&"public".equals(page.getDataState())){
            //公共区域的子节点
            sql.append(" and mm.workTeamName = ?workTeamName1 ");
        }else if(page.getTreeLevel() == 2 &&"".equals(page.getDataState())){
            //生产线
            sql.append(" and mm.beltlineName = ?beltlineName1 ");
        }else if(page.getTreeLevel() == 3){
            //生产区域的生产组节点等级是3
             sql.append(" and mm.workTeamName = ?workTeamName1 ");
        }
        
        //存在排序  //这里需要确认前端传来的排序字段的名称
        if("areaName".equals(page.getSidx())){
            // 按区域名称排序
            sql.append(" order by mm.areaName ").append(page.getSord());
        }
        if("beltlineName".equals(page.getSidx())){
            //按生产线排序
            sql.append(" order by mm.beltlineName ").append(page.getSord());
        }
        if("workTeamName".equals(page.getSidx())){
            //按生产组排序
            sql.append(" order by mm.workTeamName ").append(page.getSord());
        }
        
        if("collectorNum".equals(page.getSidx())){
            //按采集器排序
            sql.append(" order by mm.hys_collector_number ").append(page.getSord());
        }
        
        if("meterNum".equals(page.getSidx())){
            //按采集器排序
            sql.append(" order by mm.hys_meter_number ").append(page.getSord());
        }
        if("meterCode".equals(page.getSidx())){
            //按采集器排序
            sql.append(" order by mm.hys_meter_code ").append(page.getSord());
        }
        
        //分页信息查询
        Query countQuery = em.createNativeQuery("select count(1) from ( "+sql.toString()+" ) dual "); 
        countQuery.setParameter("userName", userName);
        countQuery.setParameter("userId", userId);
        if(countQuery!=null){
            //查询count，不需要排序// 只对需要需要过滤的传参  
            if(page.getAreaName()!=null&&!"".equals(page.getAreaName())){
                //过滤区域
                countQuery.setParameter("areaName", "%"+page.getAreaName()+"%");
            }
            if(page.getBeltlineName()!=null&&!"".equals(page.getBeltlineName())){
                //过滤生产线
                countQuery.setParameter("beltlineName", "%"+page.getBeltlineName()+"%");
            }
            if(page.getWorkTeamName()!=null&&!"".equals(page.getWorkTeamName())){
                //过滤生产组
                countQuery.setParameter("workTeamName", "%"+page.getWorkTeamName()+"%");
            }
            if(page.getCollectorNum()!=null&&!"".equals(page.getCollectorNum())){
                //过滤采集器
                countQuery.setParameter("collectorNum", "%"+page.getCollectorNum()+"%");
            }
            if(page.getMeterNum()!=null&&!"".equals(page.getMeterNum())){
                //过滤设备号
                countQuery.setParameter("meterNum", "%"+page.getMeterNum()+"%");
            }
            if(page.getMeterCode()!=null&&!"".equals(page.getMeterCode())){
                //过滤计量类型
                countQuery.setParameter("meterCode", "%"+page.getMeterCode()+"%");
            }
            
            //根据左侧树的过滤
            if(page.getTreeLevel() == 1){
                //区域节点时
                countQuery.setParameter("areaName1", page.getNodeName());
            }else if(page.getTreeLevel() == 2 &&"public".equals(page.getDataState())){
                //公共区域的子节点
                countQuery.setParameter("workTeamName1", page.getNodeName());
            }else if(page.getTreeLevel() == 2 &&"".equals(page.getDataState())){
                //生产线
                countQuery.setParameter("beltlineName1", page.getNodeName());
            }else if(page.getTreeLevel() == 3){
                //生产区域的生产组节点等级是3
                 countQuery.setParameter("workTeamName1", page.getNodeName());
            }
            
            
            countQuery.setParameter("userName", userName);
            countQuery.setParameter("userId", userId);
            List count =countQuery.getResultList();
            logger.info("====="+count.get(0));
            BigDecimal countNum = new BigDecimal(count.get(0).toString());
            float f = countNum.floatValue();
            //总页数
            int totalpages = (int) Math.ceil(f/page.getPagesize());
            //封装分页信息
            pageAreaExp.setCurrpage(page.getCurrpage());
            pageAreaExp.setTotalpages(totalpages);
            pageAreaExp.setTotalrecords(countNum.intValue());
            pageAreaExp.setPagesize(page.getPagesize());
            //计算从第几条查询，如果是第一页，默认为0
            if(page.getCurrpage()>1){
                beginNum = (page.getCurrpage()-1)*page.getPagesize();
            }  
        }
        
        Query qu = em.createNativeQuery(sql.toString());
        qu.setFirstResult(beginNum);
        qu.setMaxResults(page.getPagesize());
        qu.setParameter("userName", userName);
        qu.setParameter("userId", userId);
        //存在需要过滤的字段，需要传参       
        if(page.getAreaName()!=null&&!"".equals(page.getAreaName())){
            //过滤区域
            qu.setParameter("areaName", "%"+page.getAreaName()+"%");
        }
        if(page.getBeltlineName()!=null&&!"".equals(page.getBeltlineName())){
            //过滤生产线
            qu.setParameter("beltlineName", "%"+page.getBeltlineName()+"%");
        }
        if(page.getWorkTeamName()!=null&&!"".equals(page.getWorkTeamName())){
            //过滤生产组
            qu.setParameter("workTeamName", "%"+page.getWorkTeamName()+"%");
        }
        if(page.getCollectorNum()!=null&&!"".equals(page.getCollectorNum())){
            //过滤采集器
            qu.setParameter("collectorNum", "%"+page.getCollectorNum()+"%");
        }
        if(page.getMeterNum()!=null&&!"".equals(page.getMeterNum())){
            //过滤设备号
            qu.setParameter("meterNum", "%"+page.getMeterNum()+"%");
        }
        if(page.getMeterCode()!=null&&!"".equals(page.getMeterCode())){
            //过滤计量类型
            qu.setParameter("meterCode", "%"+page.getMeterCode()+"%");
        }
        
        //根据左侧树节点信息过滤
        if(page.getTreeLevel() == 1){
            //区域节点时
            qu.setParameter("areaName1", page.getNodeName());
        }else if(page.getTreeLevel() == 2 &&"public".equals(page.getDataState())){
            //公共区域的子节点
            qu.setParameter("workTeamName1", page.getNodeName());
        }else if(page.getTreeLevel() == 2 &&"".equals(page.getDataState())){
            //生产线
            qu.setParameter("beltlineName1", page.getNodeName());
        }else if(page.getTreeLevel() == 3){
            //生产区域的生产组节点等级是3
             qu.setParameter("workTeamName1", page.getNodeName());
        }
        
        List list = qu.getResultList();
        if(list!=null&&list.size()>0){
           Iterator it = list.iterator();
           HysAreaExp hysAreaExp =null;
            while(it.hasNext()){
                Object obj[] = (Object[])it.next();
                hysAreaExp = new HysAreaExp();
                if(obj.length==10){
                    hysAreaExp.setAreaId(obj[0]==null?"":obj[0].toString());
                    hysAreaExp.setAreaName(obj[1]==null?"":obj[1].toString());
                    hysAreaExp.setBeltlineId(obj[2]==null?"":obj[2].toString());
                    hysAreaExp.setBeltlineName(obj[3]==null?"":obj[3].toString());
                    hysAreaExp.setWorkTeamId(obj[4]==null?"":obj[4].toString());
                    hysAreaExp.setWorkTeamName(obj[5]==null?"":obj[5].toString());  
                    hysAreaExp.setIsPublic(obj[6]==null?"":obj[6].toString());
                    hysAreaExp.setMeterNum(obj[7]==null?"":obj[7].toString());
                    hysAreaExp.setMeterCode(obj[8]==null?"":obj[8].toString());
                    hysAreaExp.setCollectorNum(obj[9]==null?"":obj[9].toString());
                }
                pageAreaExp.getRows().add(hysAreaExp);
            }
        }      
        return pageAreaExp;
    } 
    
    
    /**
     * 查询所有的区域信息 ---新的修改，不考虑user的情况
     * @param page
     * @param userId
     * @param userName
     * @return 
     */
    public PageHysAreaExp<HysAreaExp> findleafInfoByNode (PageHysAreaExp page){
        logger.info("------plant.HysDataService.findAreaBasicInfo--------");
        //对于 userID 和 userName 用于后期的关联用户的数据集..
        PageHysAreaExp<HysAreaExp> pageAreaExp = new PageHysAreaExp<HysAreaExp>();
        int beginNum = 0; //起始页
        StringBuilder sql = new StringBuilder(""); 
        sql.append(HysDataSqlResource.FIND_ALL_AREA_BESIC_INFO2);
        
        //存在过滤条件      
        if(page.getAreaName()!=null&&!"".equals(page.getAreaName())){
            //过滤区域
            sql.append(" and mm.areaName like ?areaName ");
        }
        if(page.getBeltlineName()!=null&&!"".equals(page.getBeltlineName())){
            //过滤生产线
            sql.append(" and mm.beltlineName like ?beltlineName ");
        }
        if(page.getWorkTeamName()!=null&&!"".equals(page.getWorkTeamName())){
            //过滤生产组
            sql.append(" and mm.workTeamName like ?workTeamName ");
        }
        if(page.getCollectorNum()!=null&&!"".equals(page.getCollectorNum())){
            //过滤采集器
            sql.append(" and mm.hys_collector_number like ?collectorNum ");
        }
//        if(page.getMeterNum()!=null&&!"".equals(page.getMeterNum())){
//            //过滤设备号
//            sql.append(" and mm.hys_meter_number like ?meterNum ");
//        }
        if(page.getMeterName()!=null&&!"".equals(page.getMeterName())){
            //过滤设备名称
            sql.append(" and mm.hys_meter_name like ?meterName ");
        }
        if(page.getMeterCode()!=null&&!"".equals(page.getMeterCode())){
            //过滤计量类型
            sql.append(" and mm.hys_meter_code like ?meterCode ");
        }
        
        //根据左侧树过滤
//        if(page.getTreeLevel() == 1){
//            //区域节点时
//            sql.append(" and mm.areaName = ?areaName1 ");
//        }else if(page.getTreeLevel() == 2 &&"public".equals(page.getDataState())){
//            //公共区域的子节点
//            sql.append(" and mm.workTeamName = ?workTeamName1 ");
//        }else if(page.getTreeLevel() == 2 &&"".equals(page.getDataState())){
//            //生产线
//            sql.append(" and mm.beltlineName = ?beltlineName1 ");
//        }else if(page.getTreeLevel() == 3){
//            //生产区域的生产组节点等级是3
//             sql.append(" and mm.workTeamName = ?workTeamName1 ");
//        }
        
        if(page.getTreeLevel()==1){
            //区域节点
            sql.append(" and mm.areaId = ?areaId ");
        }else if(page.getTreeLevel() == 2 &&"public".equals(page.getDataState())){
            //公共区域的子节点
            sql.append(" and mm.workTeamId = ?workTeamId1 ");       
        }else if(page.getTreeLevel() == 2 &&"".equals(page.getDataState())){
            //生产线
            sql.append(" and mm.beltlineId = ?beltlineId ");
        }else if(page.getTreeLevel() == 3){
            //生产区域的生产组节点等级是3
             sql.append(" and mm.workTeamId = ?workTeamId2 ");
        }
        
        //存在排序  //这里需要确认前端传来的排序字段的名称
        if("areaName".equals(page.getSidx())){
            // 按区域名称排序
            sql.append(" order by mm.areaName ").append(page.getSord());
        }
        if("beltlineName".equals(page.getSidx())){
            //按生产线排序
            sql.append(" order by mm.beltlineName ").append(page.getSord());
        }
        if("workTeamName".equals(page.getSidx())){
            //按生产组排序
            sql.append(" order by mm.workTeamName ").append(page.getSord());
        }
        
        if("collectorNum".equals(page.getSidx())){
            //按采集器排序
            sql.append(" order by mm.hys_collector_number ").append(page.getSord());
        }
        
        if("meterName".equals(page.getSidx())){
            //按设备名称
            sql.append(" order by mm.hys_meter_name ").append(page.getSord());
        }
        if("meterCode".equals(page.getSidx())){
            //按设备类型
            sql.append(" order by mm.hys_meter_code ").append(page.getSord());
        }
        
        //分页信息查询
        Query countQuery = em.createNativeQuery("select count(1) from ( "+sql.toString()+" ) dual "); 
        if(countQuery!=null){
            //查询count，不需要排序// 只对需要需要过滤的传参  
            if(page.getAreaName()!=null&&!"".equals(page.getAreaName())){
                //过滤区域
                countQuery.setParameter("areaName", "%"+page.getAreaName()+"%");
            }
            if(page.getBeltlineName()!=null&&!"".equals(page.getBeltlineName())){
                //过滤生产线
                countQuery.setParameter("beltlineName", "%"+page.getBeltlineName()+"%");
            }
            if(page.getWorkTeamName()!=null&&!"".equals(page.getWorkTeamName())){
                //过滤生产组
                countQuery.setParameter("workTeamName", "%"+page.getWorkTeamName()+"%");
            }
            if(page.getCollectorNum()!=null&&!"".equals(page.getCollectorNum())){
                //过滤采集器
                countQuery.setParameter("collectorNum", "%"+page.getCollectorNum()+"%");
            }
//            if(page.getMeterNum()!=null&&!"".equals(page.getMeterNum())){
//                //过滤设备号
//                countQuery.setParameter("meterNum", "%"+page.getMeterNum()+"%");
//            }
            if(page.getMeterName()!=null&&!"".equals(page.getMeterName())){
                //过滤设备名称
                countQuery.setParameter("meterName", "%"+page.getMeterName()+"%");
            }
            
            if(page.getMeterCode()!=null&&!"".equals(page.getMeterCode())){
                //过滤计量类型
                countQuery.setParameter("meterCode", "%"+page.getMeterCode()+"%");
            }
            
            //根据左侧树的过滤
            if(page.getTreeLevel() == 1){
                //区域节点时
                countQuery.setParameter("areaId", Long.valueOf(page.getNodeId()));
            }else if(page.getTreeLevel() == 2 &&"public".equals(page.getDataState())){
                //公共区域的子节点
                countQuery.setParameter("workTeamId1", Long.valueOf(page.getNodeId()));
            }else if(page.getTreeLevel() == 2 &&"".equals(page.getDataState())){
                //生产线
                countQuery.setParameter("beltlineId", Long.valueOf(page.getNodeId()));
            }else if(page.getTreeLevel() == 3){
                //生产区域的生产组节点等级是3
                 countQuery.setParameter("workTeamId2", Long.valueOf(page.getNodeId()));
            }
            
            List count =countQuery.getResultList();
            logger.info("====="+count.get(0));
            BigDecimal countNum = new BigDecimal(count.get(0).toString());
            float f = countNum.floatValue();
            //总页数
            int totalpages = (int) Math.ceil(f/page.getPagesize());
            //封装分页信息
            pageAreaExp.setCurrpage(page.getCurrpage());
            pageAreaExp.setTotalpages(totalpages);
            pageAreaExp.setTotalrecords(countNum.intValue());
            pageAreaExp.setPagesize(page.getPagesize());
            //计算从第几条查询，如果是第一页，默认为0
            if(page.getCurrpage()>1){
                beginNum = (page.getCurrpage()-1)*page.getPagesize();
            }  
        }
        
        Query qu = em.createNativeQuery(sql.toString());
        qu.setFirstResult(beginNum);
        qu.setMaxResults(page.getPagesize());
        //存在需要过滤的字段，需要传参       
        if(page.getAreaName()!=null&&!"".equals(page.getAreaName())){
            //过滤区域
            qu.setParameter("areaName", "%"+page.getAreaName()+"%");
        }
        if(page.getBeltlineName()!=null&&!"".equals(page.getBeltlineName())){
            //过滤生产线
            qu.setParameter("beltlineName", "%"+page.getBeltlineName()+"%");
        }
        if(page.getWorkTeamName()!=null&&!"".equals(page.getWorkTeamName())){
            //过滤生产组
            qu.setParameter("workTeamName", "%"+page.getWorkTeamName()+"%");
        }
        if(page.getCollectorNum()!=null&&!"".equals(page.getCollectorNum())){
            //过滤采集器
            qu.setParameter("collectorNum", "%"+page.getCollectorNum()+"%");
        }
//        if(page.getMeterNum()!=null&&!"".equals(page.getMeterNum())){
//            //过滤设备号
//            qu.setParameter("meterNum", "%"+page.getMeterNum()+"%");
//        }
        if(page.getMeterName()!=null&&!"".equals(page.getMeterName())){
            //过滤设备号
            qu.setParameter("meterName", "%"+page.getMeterName()+"%");
        }
           
        if(page.getMeterCode()!=null&&!"".equals(page.getMeterCode())){
            //过滤计量类型
            qu.setParameter("meterCode", "%"+page.getMeterCode()+"%");
        }
        
        //根据左侧树节点信息过滤
        if(page.getTreeLevel() == 1){
            //区域节点时
            qu.setParameter("areaId", Long.valueOf(page.getNodeId()));
        }else if(page.getTreeLevel() == 2 &&"public".equals(page.getDataState())){
            //公共区域的子节点
            qu.setParameter("workTeamId1", Long.valueOf(page.getNodeId()));
        }else if(page.getTreeLevel() == 2 &&"".equals(page.getDataState())){
            //生产线
            qu.setParameter("beltlineId", Long.valueOf(page.getNodeId()));
        }else if(page.getTreeLevel() == 3){
            //生产区域的生产组节点等级是3
             qu.setParameter("workTeamId2", Long.valueOf(page.getNodeId()));
        }
          
        List list = qu.getResultList();
        if(list!=null&&list.size()>0){
           Iterator it = list.iterator();
           HysAreaExp hysAreaExp =null;
            while(it.hasNext()){
                Object obj[] = (Object[])it.next();
                hysAreaExp = new HysAreaExp();
                if(obj.length==11){
                    hysAreaExp.setAreaId(obj[0]==null?"":obj[0].toString());
                    hysAreaExp.setAreaName(obj[1]==null?"":obj[1].toString());
                    hysAreaExp.setBeltlineId(obj[2]==null?"":obj[2].toString());
                    hysAreaExp.setBeltlineName(obj[3]==null?"":obj[3].toString());
                    hysAreaExp.setWorkTeamId(obj[4]==null?"":obj[4].toString());
                    hysAreaExp.setWorkTeamName(obj[5]==null?"":obj[5].toString());  
                    hysAreaExp.setIsPublic(obj[6]==null?"":obj[6].toString());
                    hysAreaExp.setMeterNum(obj[7]==null?"":obj[7].toString());
                    hysAreaExp.setMeterName(obj[8]==null?"":obj[8].toString());
                    hysAreaExp.setMeterCode(obj[9]==null?"":obj[9].toString());
                    hysAreaExp.setCollectorNum(obj[10]==null?"":obj[10].toString());
                }
                pageAreaExp.getRows().add(hysAreaExp);
            }
        }      
        return pageAreaExp;
    } 
    
    

    
    /**
     *  添加区域 ---考虑名称的校验和设备的关联
     * @param hysAreaExp
     * @return 
     */
    public Result addArea(HysAreaExp hysAreaExp){
        logger.info("--------plant.HysDataService.addArea1----------");
        String result = "successed";
        try {             
            String areaName = hysAreaExp.getAreaName(); //区域名称
            String beltlineName = hysAreaExp.getBeltlineName(); //生产线 //这个需要单独处理
            String workTeamName = hysAreaExp.getWorkTeamName(); //生产组
            String isPublic = hysAreaExp.getIsPublic(); //用于区分公共区域及其下还是生产区域及其下
            String meterId = hysAreaExp.getMeterId(); //设备id
            //查询根节点的id
            String rootId = hysDataDao.findDataIdByName("root");
            //校验区域名称在根节点下的情况 -- noHandle \ add \ nameExists
            String judge = hysDataDao.judgeTreeNameIsExistForArea(rootId, areaName,isPublic);
            logger.info("--对于区域校验的操作---:"+judge);
            if("add".equals(judge)){
                //需要新增 区域 //此时需要区分public//也就是全部都是新增，对于跟设备的关联，保存完之后进行关联进行
                if(isPublic!=null&&"public".equals(isPublic)){
                    //公共区域 --public --公共区域的标志
                    if(areaName!=null&&!"".equals(areaName)){
                        String areaId = hysDataDao.addInfoAndReturnId(hysAreaExp.getAreaName(), Integer.valueOf(rootId), 1, "public");//保存区域     // (String name,int pid,int level,String dataState)
                       if(workTeamName!=null&&!"".equals(workTeamName)){
                           String workTeamId = hysDataDao.addInfoAndReturnId(hysAreaExp.getWorkTeamName(), Integer.valueOf(areaId), 2, "public"); 
                           //增加设备与数据集的关联
                           if(meterId!=null&&!"".equals(meterId)){
                               hysDataDao.saveDataMeter(areaId, meterId);
                               hysDataDao.saveDataMeter(workTeamId, meterId);
                           }
                       } 
                    }
                }else{
                    //生产区域。保存成功后，返回id (String name,int pid,int level,String dataState)                    
                    if(areaName!=null&&!"".equals(areaName)){
                        String areaId = hysDataDao.addInfoAndReturnId(hysAreaExp.getAreaName(), Integer.valueOf(rootId), 1, "");//保存区域
                        if(beltlineName!=null&&!"".equals(beltlineName)){
                            String beltlineId =  hysDataDao.addInfoAndReturnId(hysAreaExp.getBeltlineName(),Integer.valueOf(areaId), 2, "");//保存生产线
                            if(workTeamName!=null&&!"".equals(workTeamName)){
                                String workTeamId = hysDataDao.addInfoAndReturnId(hysAreaExp.getWorkTeamName(), Integer.valueOf(beltlineId), 3, ""); //生产组
                                ///如果传来的设备数据存在，就增加设备与数据集的关联
                                if(meterId!=null&&!"".equals(meterId)){
                                    hysDataDao.saveDataMeter(areaId, meterId);
                                    hysDataDao.saveDataMeter(beltlineId, meterId);
                                    hysDataDao.saveDataMeter(workTeamId, meterId);
                                }
                            }
                        }                  
                    }
                }
                
            }else if("noHandle".equals(judge)){
                //第一层区域不用处理 //但是如果设备有的话，还是需要将区域与设备关联，没有设备的话，就不用关联了          
                //先区分public的情况
                //区域不处理，但是需要考虑区域名称是手写，并且名称与列表中的一致.
                 String areaId = hysAreaExp.getAreaId();//区域已经存在 
                 //通过名称查询其id //预防手写的情况
                 if(areaId==null||"".equals(areaId)){
                     areaId = hysDataDao.findDataIdByName(areaName);
                 }
                 if(isPublic!=null&&"public".equals(isPublic)){
                     //公共
                     String PuWorkTeamJudge = hysDataDao.judgeTreeNameIsExist(areaId, workTeamName);
                     logger.info("--公共区域不处理，对于其下级的校验--"+PuWorkTeamJudge);
                     if("add".equals(PuWorkTeamJudge)){
                         //对于public而言,非生产组也是新增
                         if(workTeamName!=null&&!"".equals(workTeamName)){
                           String workTeamId = hysDataDao.addInfoAndReturnId(hysAreaExp.getWorkTeamName(), Integer.valueOf(areaId), 2, "public"); 
                           //增加设备与数据集的关联
                           if(meterId!=null&&!"".equals(meterId)){
                               hysDataDao.saveDataMeter(areaId, meterId);
                               hysDataDao.saveDataMeter(workTeamId, meterId);
                           }
                         }
                     }else if("noHandle".equals(PuWorkTeamJudge)){  
                         //对于public时，二级节点已经存在，获取其id
                         String workTeamId = hysAreaExp.getWorkTeamId();
                         //获取公共区域的二级节点的id //
                         if(workTeamId==null||"".equals(workTeamId)){
                             workTeamId = hysDataDao.findDataIdByName(workTeamName);
                         }
                         
                         if(meterId!=null&&!"".equals(meterId)){
                            hysDataDao.saveDataMeter(areaId, meterId);
                            hysDataDao.saveDataMeter(workTeamId, meterId);
                         }
                     }else if("nameExists".equals(PuWorkTeamJudge)){
                         logger.info("--非生产组已经存在，不能添加--");
                         result = "workTeamNameExists";
                     }
                     
                 }else{
                     //第一层区域不处理后，区分public后的生产线
                     String betlineJudge = hysDataDao.judgeTreeNameIsExist(areaId, beltlineName);
                     logger.info("===区域不处理，对于生产线的校验=="+betlineJudge);
                     if("add".equals(betlineJudge)){
                         if(beltlineName!=null&&!"".equals(beltlineName)){
                             String beltlineId =  hysDataDao.addInfoAndReturnId(hysAreaExp.getBeltlineName(),Integer.valueOf(areaId), 2, "");//保存生产线
                             if(workTeamName!=null&&!"".equals(workTeamName)){
                                 String workTeamId = hysDataDao.addInfoAndReturnId(hysAreaExp.getWorkTeamName(), Integer.valueOf(beltlineId), 3, ""); //生产组
                                 ///如果传来的设备数据存在，就增加设备与数据集的关联
                                 if(meterId!=null&&!"".equals(meterId)){
                                     hysDataDao.saveDataMeter(areaId, meterId);
                                     hysDataDao.saveDataMeter(beltlineId, meterId);
                                     hysDataDao.saveDataMeter(workTeamId, meterId);
                                 }
                             }
                         }
                     }else if("noHandle".equals(betlineJudge)){
                         //生产区域、生产线级别都不处理data，关联设备即可.但是对于生产组还是需要判断
                         String beltlineId = hysAreaExp.getBeltlineId();
                         if(beltlineId==null||"".equals(beltlineId)){
                             beltlineId = hysDataDao.findDataIdByName(beltlineName);
                         }
                         String workTeamJudge = hysDataDao.judgeTreeNameIsExist(beltlineId, workTeamName);
                         logger.info("--区域、生产线不处理,对于生产组的校验---"+workTeamJudge);
                         if("add".equals(workTeamJudge)){
                             if(workTeamName!=null&&!"".equals(workTeamName)){
                                  String workTeamId = hysDataDao.addInfoAndReturnId(hysAreaExp.getWorkTeamName(), Integer.valueOf(beltlineId), 3, ""); 
                                  //增加设备与数据集的关联
                                  if(meterId!=null&&!"".equals(meterId)){
                                     hysDataDao.saveDataMeter(areaId, meterId);
                                     hysDataDao.saveDataMeter(beltlineId, meterId);
                                     hysDataDao.saveDataMeter(workTeamId, meterId);
                                 }
                              }
                         }else if("noHandle".equals(workTeamJudge)){
                             String workTeamId = hysAreaExp.getWorkTeamId();
                             //获取生产组id //手写情况
                             if(workTeamId==null||"".equals(workTeamId)){
                                 workTeamId = hysDataDao.findDataIdByName(workTeamName);
                             }
                             if(meterId!=null&&!"".equals(meterId)){
                                hysDataDao.saveDataMeter(areaId, meterId);
                                hysDataDao.saveDataMeter(beltlineId, meterId);
                                hysDataDao.saveDataMeter(workTeamId, meterId);
                             }
                             
                         }else if("nameExists".equals(workTeamJudge)){
                             logger.info("--生产组已经存在，不能添加--");
                             result = "workTeamNameExists";
                         }
                         
                     }else if("nameExists".equals(judge)){
                         logger.info("--生产线级别已经存在，不能添加--");
                         result = "betlineNameExists";
                     }
                 }
                
            }else if("nameExists".equals(judge)){
                logger.info("--区域名称已经存在，不能添加--");
                result = "areaNameExists";
            }
            
        } catch (Exception e) {
            result = "failed";
            logger.info("[addArea Exception]:"+e.getMessage());
        }
        
        return new Result(result);
    }
    
      
    /**
     *  修改区域信息  
     * @param hysAreaExp
     * @return 
     */
    public Result editArea(HysAreaExp hysAreaExp){
        logger.info("-----------plant.HysDataService.editArea----------");
        String result = "successed";     
        try {
            String areaName = hysAreaExp.getAreaName(); //区域名称
            String beltlineName = hysAreaExp.getBeltlineName(); //生产线 //这个需要单独处理
            String workTeamId = hysAreaExp.getWorkTeamId(); //生产组(叶子节点)id
            String workTeamName = hysAreaExp.getWorkTeamName(); //生产组
            String isPublic = hysAreaExp.getIsPublic(); //用于区分公共区域及其下还是生产区域及其下
            
            String meterNum = hysAreaExp.getMeterNum(); //设备号
            String meterId = hysAreaExp.getMeterId(); //设备id
             //查询根节点的id
            String rootId = hysDataDao.findDataIdByName("root");
            //校验区域名称在根节点下的情况 -- noHandle \ add \ nameExists
            String judge = hysDataDao.judgeTreeNameIsExistForArea(rootId, areaName,isPublic);
            logger.info("--对于区域校验的操作edit---:"+judge);
            if("add".equals(judge)){
                //如果没有区域，需要新增。需要考虑的东西：其下所有节点都是新增。（看设备是否为空，不为空的话，删除与其他节点的关联）如果设备已经与其他的关联，先删除关联
                if(isPublic!=null&&"public".equals(isPublic)){
                    if(areaName!=null&&!"".equals(areaName)){
                        String areaId = hysDataDao.addInfoAndReturnId(hysAreaExp.getAreaName(), Integer.valueOf(rootId), 1, "public");//保存区域   
                        
                        if(workTeamName!=null&&!"".equals(workTeamName)){
                            //现在是一个生产组关联一个设备 // String dataId,String name,int pid,int level,String dataState
                            hysDataDao.editDataInfo(workTeamId, workTeamName, Integer.valueOf(areaId), 2, "public");
                            //这里只是处理的先前的关联设备，进行新的设备与节点的关联 --应用先前的代码
                            List<String> meterIdList = hysDataDao.findMeterIdByDataId(workTeamId);
                            if(meterIdList!=null&&meterIdList.size()>0){
                                for(String meterID : meterIdList){
                                    //删除此设备关联的节点信息
                                    hysDataDao.deleteDataMeter(meterID);
                                }                                                
                            }
                            
                            //增加设备与数据集的关联
                           if(meterNum!=null&&!"".equals(meterNum)&&meterId!=null&&!"".equals(meterId)){
                                //校验设备是否已经关联节点 //true -- 存在关联 ；false -- 不存在关联
                               boolean judgeMeter = hysDataDao.judgeMeterRelatedToData(meterId); 
                               if(judgeMeter ==true){
                                   logger.info("---先删除旧的设备、数据集关联1--");
                                   hysDataDao.deleteDataMeter(meterId);
                               }
                               hysDataDao.saveDataMeter(areaId, meterId);
                               hysDataDao.saveDataMeter(workTeamId, meterId);
                           }
                       } 
                    }
                }else{
                    if(areaName!=null&&!"".equals(areaName)){
                        String areaId = hysDataDao.addInfoAndReturnId(hysAreaExp.getAreaName(), Integer.valueOf(rootId), 1, "");//保存区域
                        if(beltlineName!=null&&!"".equals(beltlineName)){
                            String beltlineId =  hysDataDao.addInfoAndReturnId(hysAreaExp.getBeltlineName(),Integer.valueOf(areaId), 2, "");//保存生产线
                            if(workTeamName!=null&&!"".equals(workTeamName)){                               
                                hysDataDao.editDataInfo(workTeamId, workTeamName, Integer.valueOf(beltlineId), 3, "");
                                //处理先前的设备数据集关联
                                List<String> meterIdList = hysDataDao.findMeterIdByDataId(workTeamId);
                                if(meterIdList!=null&&meterIdList.size()>0){
                                    for(String meterID : meterIdList){
                                        //删除此设备关联的节点信息（因为设备关联多级节点）
                                        hysDataDao.deleteDataMeter(meterID);
                                    }                                   
                                }
                                
                                if(meterNum!=null&&!"".equals(meterNum)&&meterId!=null&&!"".equals(meterId)){
                                    //校验设备是否已经关联节点 //true -- 存在关联 ；false -- 不存在关联
                                    boolean judgeMeter = hysDataDao.judgeMeterRelatedToData(meterId);
                                    if(judgeMeter ==true){
                                        logger.info("---先删除旧的设备、数据集关联2--");
                                        hysDataDao.deleteDataMeter(meterId);
                                    }
                                    hysDataDao.saveDataMeter(areaId, meterId);
                                    hysDataDao.saveDataMeter(beltlineId, meterId);
                                    hysDataDao.saveDataMeter(workTeamId, meterId);
                                }
                            }    
                        }
                    }
                }
                
            }else if("noHandle".equals(judge)){
                //第一级区域不处理。先区分是public情况，再来看二级节点的情况，
                String areaId = hysAreaExp.getAreaId();//区域已经存在 
                if(isPublic!=null&&"public".equals(isPublic)){
                    //公共区域
                    if(workTeamName!=null&&!"".equals(workTeamName)){                               
                        hysDataDao.editDataInfo(workTeamId, workTeamName, Integer.valueOf(areaId), 2, "public");
                        List<String> meterIdList = hysDataDao.findMeterIdByDataId(workTeamId);
                        if(meterIdList!=null&&meterIdList.size()>0){
                            for(String meterID : meterIdList){
                                //删除此设备关联的节点信息
                                hysDataDao.deleteDataMeter(meterID);
                            }                                                
                        }

                        //增加设备与数据集的关联
                       if(meterNum!=null&&!"".equals(meterNum)&&meterId!=null&&!"".equals(meterId)){
                            //校验设备是否已经关联节点 //true -- 存在关联 ；false -- 不存在关联
                           boolean judgeMeter = hysDataDao.judgeMeterRelatedToData(meterId); 
                           if(judgeMeter ==true){
                               logger.info("---先删除旧的设备、数据集关联3--");
                               hysDataDao.deleteDataMeter(meterId);
                           }
                           hysDataDao.saveDataMeter(areaId, meterId);
                           hysDataDao.saveDataMeter(workTeamId, meterId);
                       }
                    }
                    
                }else{
                    //生产XX
                    String betlineJudge = hysDataDao.judgeTreeNameIsExist(areaId, beltlineName);
                    logger.info("===区域不处理，对于生产线的校验edit=="+betlineJudge);
                    if("add".equals(betlineJudge)){
                        if(beltlineName!=null&&!"".equals(beltlineName)){
                            String beltlineId =  hysDataDao.addInfoAndReturnId(hysAreaExp.getBeltlineName(),Integer.valueOf(areaId), 2, "");//保存生产线
                            if(workTeamName!=null&&!"".equals(workTeamName)){
                                 hysDataDao.editDataInfo(workTeamId, workTeamName, Integer.valueOf(beltlineId), 3, ""); //修改生产组
                                //处理先前的设备数据集关联
                                List<String> meterIdList = hysDataDao.findMeterIdByDataId(workTeamId);
                                if(meterIdList!=null&&meterIdList.size()>0){
                                    for(String meterID : meterIdList){
                                        //删除此设备关联的节点信息（因为设备关联多级节点）
                                        hysDataDao.deleteDataMeter(meterID);
                                    }                                   
                                }
                                
                                if(meterNum!=null&&!"".equals(meterNum)&&meterId!=null&&!"".equals(meterId)){
                                    //校验设备是否已经关联节点 //true -- 存在关联 ；false -- 不存在关联
                                    boolean judgeMeter = hysDataDao.judgeMeterRelatedToData(meterId);
                                    if(judgeMeter ==true){
                                        logger.info("---先删除旧的设备、数据集关联4--");
                                        hysDataDao.deleteDataMeter(meterId);
                                    }
                                    hysDataDao.saveDataMeter(areaId, meterId);
                                    hysDataDao.saveDataMeter(beltlineId, meterId);
                                    hysDataDao.saveDataMeter(workTeamId, meterId);
                                }   
                             }
                        }
                    }else if("noHandle".equals(betlineJudge)){
                        //生产区域、生产线级别都不处理data，关联设备即可.但是对于生产组还是需要判断
                        String beltlineId = hysAreaExp.getBeltlineId();
                        if(workTeamName!=null&&!"".equals(workTeamName)){
                            hysDataDao.editDataInfo(workTeamId, workTeamName, Integer.valueOf(beltlineId), 3, ""); //修改生产组
                            //处理先前的设备数据集关联
                            List<String> meterIdList = hysDataDao.findMeterIdByDataId(workTeamId);
                            if(meterIdList!=null&&meterIdList.size()>0){
                                for(String meterID : meterIdList){
                                    //删除此设备关联的节点信息（因为设备关联多级节点）
                                    hysDataDao.deleteDataMeter(meterID);
                                }                                   
                            }

                            if(meterNum!=null&&!"".equals(meterNum)&&meterId!=null&&!"".equals(meterId)){
                                //校验设备是否已经关联节点 //true -- 存在关联 ；false -- 不存在关联
                                boolean judgeMeter = hysDataDao.judgeMeterRelatedToData(meterId);
                                if(judgeMeter ==true){
                                    logger.info("---先删除旧的设备、数据集关联2--");
                                    hysDataDao.deleteDataMeter(meterId);
                                }
                                hysDataDao.saveDataMeter(areaId, meterId);
                                hysDataDao.saveDataMeter(beltlineId, meterId);
                                hysDataDao.saveDataMeter(workTeamId, meterId);
                           }   
                      }
                        
                    }else if("nameExists".equals(judge)){
                        logger.info("--生产线级别已经存在，不能添加--");
                        result = "betlineNameExists";
                    }
                }
            }else if("nameExists".equals(judge)){
                logger.info("--区域名称已经存在，不能添加--");
                result = "areaNameExists";
            }
        } catch (Exception e) {
            result = "failed";
            logger.severe("[editArea Exception]"+e.getMessage());
        }
        return new Result(result);
    }
    

    /**
     * 删除区域信息 //对应的只删除与设备关联的最底层节点以及相关节点与设备的关联关系
     * @param 
     * @return 
     */
    public Result deleteArea(Parameter parameter){
        logger.info("--------plant.HysDataService.deleteArea--------");
        String result = "successed";
        try {        
            //1.删除最下级节点信息；2.删除节点后，其所有与设备的关联关系要删除；
            if(parameter.getIds()!=null&&!"".equals(parameter.getIds())){
                String ids = parameter.getIds();
                String str[] = ids.split(",");               
                if(str!=null&&str.length>0){
                    for(String dataId :str){
                       if(!"0".equals(dataId)){                  
                          //查询节点关联的设备list   
                          List<String> meterIdList = hysDataDao.findMeterIdByDataId(dataId);
                          if(meterIdList!=null&&meterIdList.size()>0){
                              for(String meterId : meterIdList){
                                  //删除此设备关联的节点信息（因为设备关联多级节点）
                                  hysDataDao.deleteDataMeter(meterId);
                              }
                          }
                          
                          //删除最下级的节点信息
                          Query deleteByIdQuery = em.createNamedQuery("HysData.deleteById"); 
                          deleteByIdQuery.setParameter("hysStorageId", Long.valueOf(dataId));
                          deleteByIdQuery.executeUpdate();
                          
                       }
                   }
                }  
            } 
        } catch (Exception e) {
            result = "failed";
            logger.severe("[deleteArea Exception]"+e.getMessage());
        }
        return new Result(result);
    }
    
    /**
     * 查询用户关联的所有的区域 --（暂时不使用）
     * @param userId
     * @param userName
     * @return 
     */
    public List<HysAreaExp> findAllArea(){
        logger.info("---plant.HysDataService.findAllArea----");
        List<HysAreaExp> areaList = new ArrayList<HysAreaExp>();
        Query findAllAreaQ = em.createNativeQuery(HysDataSqlResource.FIND_ALL_AREA_SQL);             
        List list = findAllAreaQ.getResultList();
        if(list!=null&&list.size()>0){
            Iterator it = list.iterator();
            HysAreaExp area = null;
            while(it.hasNext()){
                Object[] obj = (Object[])it.next();
                area = new HysAreaExp();
                if(obj.length == 5){
                    area.setAreaId(obj[0]==null?"":obj[0].toString());
                    area.setAreaName(obj[1]==null?"":obj[1].toString());
                    area.setIsPublic(obj[4]==null?"":obj[4].toString());
                }
                areaList.add(area);
            }
        }
        
        return areaList;
    }
    

}
