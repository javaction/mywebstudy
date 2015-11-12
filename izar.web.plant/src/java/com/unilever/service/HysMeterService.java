/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.service;

import com.unilever.entity.HysMeter;
import com.unilever.entity.HysMeterCollector;
import com.unilever.entity.HysMeterCollectorPK;
import com.unilever.entity.exp.PageHysMeter;
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
public class HysMeterService {
    private static final Logger logger = LogManager.getLogger(HysMeterService.class);
    @PersistenceContext(unitName = "TestPU")
    private EntityManager em;
    
    /**
     * 基础信息之设备信息 --分页列表数据 
     * @return 
     */
    public PageHysMeter<HysMeter> findMeterBasicInfo(PageHysMeter page){  
        logger.info("-------plant.HysMeterService.FindMeterBasicInfo------------");
        PageHysMeter<HysMeter> pageMeterExp = new PageHysMeter<HysMeter>();//用于返回数据
        int beginNum = 0; //起始页
        StringBuilder sql = new StringBuilder("");
        
        sql.append(HysBasicInfoSqlResource.FIND_ALL_METER_BASIC_INFO);
        
        //过滤条件                         
        if(page.getHysMeterNumber()!=null&&!"".equals(page.getHysMeterNumber())){
            //过滤设备号
            sql.append(" and  t.hys_meter_number like ?meterNum ");
        }
        
        if(page.getHysMeterCode()!=null&&!"".equals(page.getHysMeterCode())){
            sql.append(" and  t.hys_meter_code like  ?meterCode ");
        }
        
        if(page.getHysMeterType()!=null&&!"".equals(page.getHysMeterType())){
            sql.append(" and  t.hys_meter_type like ?meterType ");
        }
        
        if(page.getHysStandardPower()!=null&&!"".equals(page.getHysStandardPower())){
            //过滤标准功率
            sql.append(" and to_char(t.hys_standard_power ,'999D99S') like ?standardPower ");
        }
        
        if(page.getHysManufacturer()!=null&&!"".equals(page.getHysManufacturer())){
            //过滤厂商
            sql.append(" and  t.hys_manufacturer  like ?manufacturer ");
        }
        
        if(page.getHysCollectorNum()!=null&&!"".equals(page.getHysCollectorNum())){
            //过滤采集器
            sql.append(" and  t.collectorNum like ?collectorNum ");
        }
        
        //排序转换
        if("hysMeterNumber".equals(page.getSidx())){ 
            //按表号排序
            sql.append(" order by  t.hys_meter_number ").append(page.getSord());
        }
        if("hysMeterType".equals(page.getSidx())){ 
            //按设备型号（具体的类型）排序
            sql.append(" order by  t.hys_meter_type ").append(page.getSord());
        }
        if("hysMeterCode".equals(page.getSidx())){ 
            //按计量类型（区分水电气）排序
            sql.append(" order by  t.hys_meter_code ").append(page.getSord());
        }
        if("hysStandardPower".equals(page.getSidx())){ 
            //按标准功率排序
//            sql.append(" order by cast(t.hys_standard_power as numeric) ").append(page.getSord());
            sql.append(" order by t.hys_standard_power ").append(page.getSord());
        }
        if("hysManufacturer".equals(page.getSidx())){ 
            //按厂商排序
            sql.append(" order by t.hys_manufacturer ").append(page.getSord());
        }
        if("hysCollectorNum".equals(page.getSidx())){ 
            //按采集器号排序
            sql.append(" order by t.collectorNum ").append(page.getSord());
        }
                
        Query countQuery = em.createNativeQuery("select count(1) from ("+sql.toString()+" ) dual");       
        if(countQuery!=null){
            //只是查询count的话，不需要排序，这里只传入需要过滤的字段数据
            //如果存在过滤的话，这里需要根据传来的过滤字段，对数据进行过滤
            if(page.getHysMeterNumber()!=null&&!"".equals(page.getHysMeterNumber())){
                //过滤设备号
                countQuery.setParameter("meterNum", "%"+page.getHysMeterNumber()+"%");
            }

            if(page.getHysMeterCode()!=null&&!"".equals(page.getHysMeterCode())){
                countQuery.setParameter("meterCode", "%"+page.getHysMeterCode()+"%");
            }

            if(page.getHysMeterType()!=null&&!"".equals(page.getHysMeterType())){
                countQuery.setParameter("meterType", "%"+page.getHysMeterType()+"%");
            }

            if(page.getHysStandardPower()!=null&&!"".equals(page.getHysStandardPower())){
                String standPower  = String.valueOf(page.getHysStandardPower());
                if(standPower.indexOf(".0")!=-1){
                    standPower = standPower.substring(0, standPower.indexOf("."));
                }
                //过滤标准功率
                countQuery.setParameter("standardPower", "%"+standPower+"%");
            }

            if(page.getHysManufacturer()!=null&&!"".equals(page.getHysManufacturer())){
                //过滤厂商
                 countQuery.setParameter("manufacturer", "%"+page.getHysManufacturer()+"%");
            }

            if(page.getHysCollectorNum()!=null&&!"".equals(page.getHysCollectorNum())){
                //过滤采集器
                countQuery.setParameter("collectorNum", "%"+page.getHysCollectorNum()+"%");
            }
            
            List count =countQuery.getResultList();
            BigDecimal countNum = new BigDecimal(count.get(0).toString());
            float f = countNum.floatValue();
            //总页数
            int totalpages = (int) Math.ceil(f/page.getPagesize());
            //封装分页信息
            pageMeterExp.setCurrpage(page.getCurrpage());
            pageMeterExp.setTotalpages(totalpages);
            pageMeterExp.setTotalrecords(countNum.intValue());
            pageMeterExp.setPagesize(page.getPagesize());
            //计算从第几条查询，如果是第一页，默认为0
            if(page.getCurrpage()>1){
                beginNum = (page.getCurrpage()-1)*page.getPagesize();
            }           
        }
            
        Query query = em.createNativeQuery(sql.toString());
        query.setFirstResult(beginNum);
        query.setMaxResults(page.getPagesize());      
        //如果存在需要过滤的字段，需要再进行对应的传参，如count处
        if(page.getHysMeterNumber()!=null&&!"".equals(page.getHysMeterNumber())){
            //过滤设备号
            query.setParameter("meterNum", "%"+page.getHysMeterNumber()+"%");
        }

        if(page.getHysMeterCode()!=null&&!"".equals(page.getHysMeterCode())){
            query.setParameter("meterCode", "%"+page.getHysMeterCode()+"%");
        }

        if(page.getHysMeterType()!=null&&!"".equals(page.getHysMeterType())){
            query.setParameter("meterType", "%"+page.getHysMeterType()+"%");
        }

        if(page.getHysStandardPower()!=null&&!"".equals(page.getHysStandardPower())){
            //过滤标准功率
            String standPower  = String.valueOf(page.getHysStandardPower());
            if(standPower.indexOf(".0")!=-1){
                standPower = standPower.substring(0, standPower.indexOf("."));
            }
            query.setParameter("standardPower", "%"+standPower+"%");
        }

        if(page.getHysManufacturer()!=null&&!"".equals(page.getHysManufacturer())){
            //过滤厂商
             query.setParameter("manufacturer", "%"+page.getHysManufacturer()+"%");
        }

        if(page.getHysCollectorNum()!=null&&!"".equals(page.getHysCollectorNum())){
            //过滤采集器
            query.setParameter("collectorNum", "%"+page.getHysCollectorNum()+"%");
        }
        
        List list =query.getResultList();
        if(list!=null&&list.size()>0){
            Iterator it = list.iterator();
            HysMeter meter =null;
            while(it.hasNext()){
                Object obj[] = (Object[])it.next();
                meter = new HysMeter();
                if(obj.length==7){
                    String storagrId = obj[0]==null?"":obj[0].toString();
                    meter.setHysStorageId(Long.valueOf(storagrId));
                    meter.setHysMeterNumber(obj[1]==null?"":obj[1].toString());                   
                    meter.setHysMeterCode(obj[2]==null?"":obj[2].toString());                    
                    meter.setHysMeterType(obj[3]==null?"":obj[3].toString());                   
                    String standardPower = obj[4]==null?"":obj[4].toString();
                    meter.setHysStandardPower(new Double(standardPower));
                    meter.setHysManufacturer(obj[5]==null?"":obj[5].toString());
                    meter.setHysCollectorNum(obj[6]==null?"":obj[6].toString());   
                }   
                pageMeterExp.getRows().add(meter);
            }
        }
        
        return pageMeterExp;       
    }
    
    
    /**
     * 新增设备 //现在只是单纯的增加设备，如果后期需要关联采集器，包括之类的判断，都得重新加入，对逻辑新的考虑以保证正确
     * @param 
     * @return 
     */
    public Result addMeter(HysMeter hysMeter){
        logger.info("-----plant.HysMeterService.addMeter--------");
        String result = "successed";
        try {
            //1.保存设备基本信息
            em.persist(hysMeter);
            String meterId = String.valueOf(hysMeter.getHysStorageId());           
            String collectorId = hysMeter.getCollectorId();
            
            //2.增加设备与采集器的关联
            HysMeterCollector meterCollector = new HysMeterCollector();
            HysMeterCollectorPK meterCollectorPK = new HysMeterCollectorPK();            
            meterCollectorPK.setHysCollectorId(Long.valueOf(collectorId));
            meterCollectorPK.setHysMeterId(Long.valueOf(meterId));
            meterCollector.setHysMeterCollectorPK(meterCollectorPK);
            em.persist(meterCollector);
            
        } catch (Exception e) {
            result = "failed";
            logger.severe("[addMeter Exception]:"+e.getMessage());
        }
        
        return new Result(result);
    }
    
    /**
     * 删除设备，支持批量删除
     * @param parameter
     * @return 
     */
    public Result deleteMeter(Parameter parameter){
        logger.info("-------plant.HysMeterService.deleteMeter--------");
        String result = "successed";
        try {
            if(parameter.getIds()!=null&&!"".equals(parameter.getIds())){
                String ids = parameter.getIds();
                String str[] = ids.split(",");
                if(str!=null&&str.length>0){
                    for(String s : str){
                        Long meterId = Long.valueOf(s);
                        
                        //1.根据设备id 删除 设备采集器关联表信息
                        Query deleteByMeterIdQuery = em.createNamedQuery("HysMeterCollector.deleteByMeterId");
                        deleteByMeterIdQuery.setParameter("hysMeterId", meterId);
                        deleteByMeterIdQuery.executeUpdate();                           
                        
                        //2.删除设备
                        Query deleteByMeterNumQuery = em.createNamedQuery("HysMeter.deleteByMeterId");
                        deleteByMeterNumQuery.setParameter("hysStorageId", meterId);
                        deleteByMeterNumQuery.executeUpdate();                       
                    }                   
                }               
            }
        } catch (Exception e) {
            result = "failed";
            logger.severe("[deleteMeter Exception]:--"+e.getMessage());
        }
        
        return new Result(result);
    }
    
    
    /**
     * 修改设备信息
     * @param hysMeter
     * @return 
     */
    public Result editMeter(HysMeter hysMeter){
        logger.info("-------plant.HysMeterService.editMeter-------");
        String result = "successed";
        try { 
            //1.修改设备基本信息
            em.merge(hysMeter);
            Long meterId = hysMeter.getHysStorageId();
            String collectorId = hysMeter.getCollectorId();
            //2.删除设备与采集器的关联
            Query deleteByMeterIdQuery = em.createNamedQuery("HysMeterCollector.deleteByMeterId");
            deleteByMeterIdQuery.setParameter("hysMeterId", meterId);
            deleteByMeterIdQuery.executeUpdate();
            //3.增加新的关联
            HysMeterCollector meterCollector = new HysMeterCollector();
            HysMeterCollectorPK meterCollectorPK = new HysMeterCollectorPK();            
            meterCollectorPK.setHysCollectorId(Long.valueOf(collectorId));
            meterCollectorPK.setHysMeterId(Long.valueOf(meterId));
            meterCollector.setHysMeterCollectorPK(meterCollectorPK);
            em.persist(meterCollector);
            
            
        } catch (Exception e) {
            result = "failed";
            logger.severe("[editMeter Exception]:--"+e.getMessage());
        }
        
        return new Result(result); 
    }
    
    
    /**
     * 查询所有未分配下去的设备，用于给区域、生产线、生产组分配..(设备只能分配一次)
     * @param 
     * @return 
     */
    public List<HysMeter> findAllMeterForSet(Parameter parameter){
        logger.info("-----plant.HysMeterService.findAllMeterRorSet----");
        String workTeamId = parameter.getIds();
        List<HysMeter> meterList = new ArrayList<HysMeter>();
        Query findAllMeterForSetQ = em.createNativeQuery(HysBasicInfoSqlResource.FIND_ALL_METER_FOR_SET_SQL); 
        if(workTeamId!=null&&!"".equals(workTeamId)){
            findAllMeterForSetQ.setParameter("workTeamId", Long.valueOf(workTeamId));
        }else{
            findAllMeterForSetQ.setParameter("workTeamId",Long.valueOf("0"));
        }
        
        List list = findAllMeterForSetQ.getResultList();
        if(list!=null&&list.size()>0){
            Iterator it = list.iterator();
            HysMeter meter = null;
            while(it.hasNext()){
                Object[] obj = (Object[])it.next();
                meter = new HysMeter();
                if(obj.length == 4){
                    String id = obj[0]==null?"":obj[0].toString();
                    meter.setHysStorageId(Long.valueOf(id));
                    meter.setHysMeterNumber(obj[1]==null?"":obj[1].toString());
                    meter.setHysMeterName(obj[2]==null?"":obj[2].toString());
                    meter.setIsSeting(obj[3]==null?"":obj[3].toString());                       
                }
                meterList.add(meter);
            }
        }
        
        return meterList;
    }
    
}
