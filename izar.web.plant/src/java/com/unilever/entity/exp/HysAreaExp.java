/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.entity.exp;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 区域信息
 * @author Ussopp.Su
 */
@XmlRootElement
public class HysAreaExp implements Serializable{
    private static final long serialVersionUID = -8079868256837582676L; 
    
    private String areaId; //区域id
    
    private String areaName ; //区域名称
    
    private String beltlineName; //生产线名称
    
    private String workTeamName; //生产组名称
    
    private String isPublic; //用于区分生产区域和公共区域 public为公共区域及其下子节点，空值为生产区域
    
    private String beltlineId; //生产线id
    
    private String workTeamId;//生产组id
    
    private String meterCode;//计量类型，区分水、电、气
    private String collectorNum; //采集器编号
    private String meterNum;//设备号
    private String meterId; //设备id
    private String meterName; //设备名称

    /**
     * @return the areaName
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * @param areaName the areaName to set
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    /**
     * @return the beltlineName
     */
    public String getBeltlineName() {
        return beltlineName;
    }

    /**
     * @param beltlineName the beltlineName to set
     */
    public void setBeltlineName(String beltlineName) {
        this.beltlineName = beltlineName;
    }

    /**
     * @return the workTeamName
     */
    public String getWorkTeamName() {
        return workTeamName;
    }

    /**
     * @param workTeamName the workTeamName to set
     */
    public void setWorkTeamName(String workTeamName) {
        this.workTeamName = workTeamName;
    }

    /**
     * @return the areaId
     */
    public String getAreaId() {
        return areaId;
    }

    /**
     * @param areaId the areaId to set
     */
    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    /**
     * @return the isPublic
     */
    public String getIsPublic() {
        return isPublic;
    }

    /**
     * @param isPublic the isPublic to set
     */
    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic;
    }

    /**
     * @return the beltlineId
     */
    public String getBeltlineId() {
        return beltlineId;
    }

    /**
     * @param beltlineId the beltlineId to set
     */
    public void setBeltlineId(String beltlineId) {
        this.beltlineId = beltlineId;
    }

    /**
     * @return the workTeamId
     */
    public String getWorkTeamId() {
        return workTeamId;
    }

    /**
     * @param workTeamId the workTeamId to set
     */
    public void setWorkTeamId(String workTeamId) {
        this.workTeamId = workTeamId;
    }

    /**
     * @return the meterCode
     */
    public String getMeterCode() {
        return meterCode;
    }

    /**
     * @param meterCode the meterCode to set
     */
    public void setMeterCode(String meterCode) {
        this.meterCode = meterCode;
    }

    /**
     * @return the collectorNum
     */
    public String getCollectorNum() {
        return collectorNum;
    }

    /**
     * @param collectorNum the collectorNum to set
     */
    public void setCollectorNum(String collectorNum) {
        this.collectorNum = collectorNum;
    }

    /**
     * @return the meterNum
     */
    public String getMeterNum() {
        return meterNum;
    }

    /**
     * @param meterNum the meterNum to set
     */
    public void setMeterNum(String meterNum) {
        this.meterNum = meterNum;
    }

    /**
     * @return the meterId
     */
    public String getMeterId() {
        return meterId;
    }

    /**
     * @param meterId the meterId to set
     */
    public void setMeterId(String meterId) {
        this.meterId = meterId;
    }

    /**
     * @return the meterName
     */
    public String getMeterName() {
        return meterName;
    }

    /**
     * @param meterName the meterName to set
     */
    public void setMeterName(String meterName) {
        this.meterName = meterName;
    }
    
    
}
