/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.dao;

import com.unilever.entity.HysParameter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author tengguan
 */
@Stateless
public class HysParameterDao extends ParentDao{
    private static final Logger logger = Logger.getLogger(HysParameterDao.class.getName());
    
    /**
     * 根据参数code和type获取这个参数的信息
     * @return 
     */
    public HysParameter findByParamCodeAndType(HysParameter hysParameter){
        
        logger.info("-------plant.HysParameterService.findByParamCodeAndType--------");
        
        String sql = "select hys_storage_id,hys_parameter_code,hys_parameter_value, hys_parameter_type,hys_remark  "
                + "from hys_parameter hp where hp.hys_parameter_code = ?paramCode  and hp.hys_parameter_type = ?paramType ;";
        Query query = em.createNativeQuery(sql);
        query.setParameter("paramCode", hysParameter.getHysParameterCode());
        query.setParameter("paramType", hysParameter.getHysParameterType());
        List list = query.getResultList();
         HysParameter parameter = null;
        if(list!=null&&list.size()>0){
            Iterator it = list.iterator();  
            while(it.hasNext()){
                Object obj[] = (Object[])it.next();
                parameter = new HysParameter();
                if(obj.length==5){
                    parameter.setHysStorageId(obj[0]==null?null: Long.valueOf(obj[0].toString()));
                    parameter.setHysParameterCode(obj[1]==null?"":obj[1].toString());                  
                    parameter.setHysParameterValue(obj[2]==null?"":obj[2].toString());
                    parameter.setHysParameterType(obj[3]==null?"":obj[3].toString());
                    parameter.setHysRemark(obj[4]==null?"":obj[4].toString());
                    
                }
            }
            return parameter;
        }else{
            return null;
        }      
    }

    public HysParameter findByParamCodeAndType(String code, String type) {
        logger.info("-------plant.HysParameterService.findByParamCodeAndType--------");
        HysParameter parameter = new HysParameter();
        String sql = "select hys_storage_id,hys_parameter_code,hys_parameter_value, hys_parameter_type,hys_remark  "
                + "from hys_parameter hp where hp.hys_parameter_code = ?paramCode  and hp.hys_parameter_type = ?paramType ;";
        Query query = em.createNativeQuery(sql);
        query.setParameter("paramCode", code);
        query.setParameter("paramType", type);
        List list = query.getResultList();
        if(list!=null&&list.size()>0){
            Iterator it = list.iterator();  
            while(it.hasNext()){
                Object obj[] = (Object[])it.next();
                parameter = new HysParameter();
                if(obj.length==5){
                    parameter.setHysStorageId(obj[0]==null?null: Long.valueOf(obj[0].toString()));
                    parameter.setHysParameterCode(obj[1]==null?"":obj[1].toString());                  
                    parameter.setHysParameterValue(obj[2]==null?"":obj[2].toString());
                    parameter.setHysParameterType(obj[3]==null?"":obj[3].toString());
                    parameter.setHysRemark(obj[4]==null?"":obj[4].toString());
                    
                }
            }
            return parameter;
        }else{
            return null;
        }      
    }
}
