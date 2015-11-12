/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.dao;

import com.unilever.entity.HysData;
import com.unilever.entity.HysDataMeter;
import com.unilever.entity.HysDataMeterPK;
import com.unilever.util.LogManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Ussopp.Su
 */
@Stateless
public class HysDataDao extends ParentDao{
    private static final Logger logger = LogManager.getLogger(HysDataDao.class);
    
    /**
     * 根据传入的信息保存数据，并返回id
     * @return 
     */
    public String addInfoAndReturnId(String name,int pid,int level,String dataState){
        logger.info("------plant.HysDataDao.addInfoAndReturnId------");
        String id = null;
        try {
            HysData hysData = new HysData();
            hysData.setHysDataName(name);
            hysData.setHysDataPid(pid);
            hysData.setHysDataLevel(level);
            hysData.setHysDataState(dataState);
            em.persist(hysData);
            
            //返回新添数据的id
            id = String.valueOf(hysData.getHysStorageId());
            
        } catch (Exception e) {
            logger.severe("[addInfoAndReturnId Exception]:"+e.getMessage());
            e.printStackTrace();
        }      
        return id;
    }
    
    /**
     * 通过名称查询其id --
     * @param name
     * @return 
     */
    public String findDataIdByName(String name){
        Query query = em.createNamedQuery("HysData.findIdByName");
        query.setParameter("hysDataName", name);
        List list = query.getResultList();
        String dataId = list.get(0).toString();
        
        return dataId;
    }
    
    
    /**
     * 校验同一父节点下的子节点的名称是否已存在
     * @return 
     */
    public String judgeTreeNameIsExist(String pid,String name){
        logger.info("----plant.HysDataDao.judgeTreeNameIsExist----");
        String judge = "";
        //父节点的id ,当前节点的名称 
        Query query = em.createNamedQuery("HysData.findDataByPidAndName");
        query.setParameter("hysDataPid", Long.valueOf(pid));
        query.setParameter("hysDataName", name);
        
        List list = query.getResultList();
        int i = list.size();     
        if(i>1){
           //存在多个
           judge = "nameExists";
        }else if(i==0){
           //不存在，需要新增
           judge = "add";
        }else if(i==1){
            //存在一个，是其本身，不用操作
            judge = "noHandle";
        }
        
        return judge;
    }
    
    /**
     * 校验根节点下区域名称是否存在重复 --同节点不同性质
     * @param pid
     * @param name
     * @param isPublic
     * @return 
     */
    public String judgeTreeNameIsExistForArea(String pid,String name,String isPublic){
        logger.info("----plant.HysDataDao.judgeTreeNameIsExistForArea----");
        String judge = ""; 
        Query query = em.createNamedQuery("HysData.findDataByPidAndName");
        query.setParameter("hysDataPid", Long.valueOf(pid));
        query.setParameter("hysDataName", name);
        List list = query.getResultList();
        int i = list.size();     
        if(i>1){
           //存在多个
           judge = "nameExists";
        }else if(i==0){
            //不存在，需要新增
            judge = "add";
        }else if(i==1){
            //存在1，需要判断
            Iterator it = list.iterator();
            while(it.hasNext()){
                Object[] obj = (Object[])it.next();
                String judgePublic = obj[1]==null?"":obj[1].toString();
                if(judgePublic.equals(isPublic)){
                    logger.info("---同性质，是其本身-");
                    //同性质，是其本身
                    judge = "noHandle";
                }else{
                    logger.info("---不同性质，名称重复-");
                    //不同性质，名称重复
                    judge = "nameExists";
                }
            }         
        }
        
        return judge;
    }
    
    
    
    /**
     * 增加设备与数据集的关联
     * @param dataId
     * @param meterId 
     */
    public void saveDataMeter(String dataId,String meterId){
        logger.info("-----saveDataMeter-----");
        try {
            HysDataMeter dataMeter = new HysDataMeter();
            HysDataMeterPK hysDataMeterPK = new HysDataMeterPK();

            hysDataMeterPK.setHysDataId(Long.valueOf(dataId));
            hysDataMeterPK.setHysMeterId(Long.valueOf(meterId));

            dataMeter.setHysDataMeterPK(hysDataMeterPK);

            em.persist(dataMeter);
            
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("[saveDataMeter Exception]"+e.getMessage());
        }
        
    }
    
    
    /**
     * 删除设备与数据集的关联
     * @param meterId 
     */
    public void deleteDataMeter(String meterId){
        logger.info("---deleteDataMeter---");
        try {
            
            Query deleteDataMeterByMeterIdQ = em.createNamedQuery("HysDataMeter.deleteByMeterId");
            deleteDataMeterByMeterIdQ.setParameter("hysMeterId", Long.valueOf(meterId));        
            deleteDataMeterByMeterIdQ.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("[deleteDataMeter Exception]"+e.getMessage());
        }
        
    }
    
    /**
     * 校验设备是否已经与节点关联
     * @param meterId
     * @return 
     */
    public boolean judgeMeterRelatedToData(String meterId){
        logger.info("---judgeMeterRelatedToData---");
        boolean judge = true;
        String sql = "select count(1) from hys_data_meter dm where dm.hys_meter_id =?meterId";
        Query query = em.createNativeQuery(sql);
        query.setParameter("meterId", Long.valueOf(meterId));
        List list = query.getResultList();
        int i = Integer.valueOf(list.get(0).toString());
        if(i==0){
            logger.info("---不存在关联");
            judge = false;
        }else{
            logger.info("---存在关联--");
            judge = true;
        }
        
        return judge;
    }
    
    
    /**
     * 根据节点id查询其下关联的设备id
     * @param dataId
     * @return 
     */
    public List<String> findMeterIdByDataId(String dataId){
        logger.info("----findMeterIdByDataId-----");
        List<String> meterIdList = new ArrayList<String>();
        if(dataId!=null&&!"".equals(dataId)){
            Query findMeterIdByDataIdQ = em.createNamedQuery("HysDataMeter.findMeterIdByDataId");
            findMeterIdByDataIdQ.setParameter("hysDataId", Long.valueOf(dataId));
            List list = findMeterIdByDataIdQ.getResultList();
            if(list!=null&&list.size()>0){
                Iterator it = list.iterator();
                String meterId = null;
                while(it.hasNext()){    
                    Long m = (Long)it.next();
                    meterId = String.valueOf(m);
                    meterIdList.add(meterId);
                }
            }
        }
        
        return meterIdList;
    }
    
    /**
     * 根据节点的id来修改其信息
     * @param dataId 
     * @param workTeamName
     */
    public void editDataInfo(String dataId,String name,int pid,int level,String dataState){
        logger.info("----saveWorkTeam---");
        try {
            if(dataId!=null&&!"".equals(dataId)){
                HysData data = em.getReference(HysData.class, Long.valueOf(dataId));
                data.setHysDataName(name);
                data.setHysDataPid(pid);
                data.setHysDataLevel(level);
                data.setHysDataState(dataState);

                em.merge(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.severe("[editDataInfo Exception]"+e.getMessage());
        }
        
    }

    
}
