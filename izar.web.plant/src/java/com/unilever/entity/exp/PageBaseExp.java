/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.entity.exp;

import com.unilever.entity.HysParameter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ussopp.Su
 */
@XmlRootElement
public class PageBaseExp<T> implements Serializable{
    private static final long serialVersionUID = -8079868256837582676L;
    
    //总页数
    private int totalpages;//不能用get/set方式
    
    //当前页数
    private int currpage = 1;
    
    //总记录数
    private int totalrecords;
    
    //每页条数
    private int pagesize = 10;
    
    private int treeLevel = 0; //对应树的节点level，默认根节点0
    private String nodeId; //对应树的节点id
    private String pid;//父节点id
    
    private String sord;//排序标志  asc / desc
    private String sidx; //需要排序的字段
    
    // **基础信息**// 过滤查询字段 ///暂时用不到了..
    private String meterNum;//设备号
    private String meterName;//设备名称
    private String meterType;//具体的设备型号
    private String meterCode;//计量类型，区分水、电、气
    private String meterStandardPower; //设备标准功率
    private String meterManufacyurer; //设备厂商
    private String collectorNum; //采集器编号
    
    private String collectorType; //采集器型号
    private String collectorManufacyurer; //采集器厂商
    private String collectorRemark; // 采集器备注信息
    
    private String areaName ; //区域名称
    private String beltlineName; //生产线名称
    private String workTeamName; //生产组名称
    
    private String search; //统一查询过滤字段
    
    private String databaseBackupState;//**系统设置**// 数据库自动备份job的状态，open为开启，close为关闭，如果没有值也默认为关闭
        
    private List<HysParameter> hysParameterRows = new ArrayList<HysParameter>(); //**系统设置**// 用于返回全部的参数信息

    /**
     * @return the databaseBackupState
     */
    public String getDatabaseBackupState() {
        return databaseBackupState;
    }

    /**
     * @param databaseBackupState the databaseBackupState to set
     */
    public void setDatabaseBackupState(String databaseBackupState) {
        this.databaseBackupState = databaseBackupState;
    }

    /**
     * @return the currpage
     */
    public int getCurrpage() {
        return currpage;
    }

    /**
     * @param currpage the currpage to set
     */
    public void setCurrpage(int currpage) {
        this.currpage = currpage;
    }

    /**
     * @return the totalrecords
     */
    public int getTotalrecords() {
        return totalrecords;
    }

    /**
     * @param totalrecords the totalrecords to set
     */
    public void setTotalrecords(int totalrecords) {
        this.totalrecords = totalrecords;
    }

    /**
     * @return the pagesize
     */
    public int getPagesize() {
        return pagesize;
    }

    /**
     * @param pagesize the pagesize to set
     */
    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    /**
     * @return the treeLevel
     */
    public int getTreeLevel() {
        return treeLevel;
    }

    /**
     * @param treeLevel the treeLevel to set
     */
    public void setTreeLevel(int treeLevel) {
        this.treeLevel = treeLevel;
    }

    /**
     * @return the nodeId
     */
    public String getNodeId() {
        return nodeId;
    }

    /**
     * @param nodeId the nodeId to set
     */
    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    /**
     * @return the pid
     */
    public String getPid() {
        return pid;
    }

    /**
     * @param pid the pid to set
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * @return the sord
     */
    public String getSord() {
        return sord;
    }

    /**
     * @param sord the sord to set
     */
    public void setSord(String sord) {
        this.sord = sord;
    }

    /**
     * @return the sidx
     */
    public String getSidx() {
        return sidx;
    }

    /**
     * @param sidx the sidx to set
     */
    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    /**
     * @return the hysParameterRows
     */
    public List<HysParameter> getHysParameterRows() {
        return hysParameterRows;
    }

    /**
     * @param hysParameterRows the hysParameterRows to set
     */
    public void setHysParameterRows(List<HysParameter> hysParameterRows) {
        this.hysParameterRows = hysParameterRows;
    }

    /**
     * @return the totalpages
     */
    public int getTotalpages() {
        return totalpages;
    }

    /**
     * @param totalpages the totalpages to set
     */
    public void setTotalpages(int totalpages) {
        this.totalpages = totalpages;
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
     * @return the meterType
     */
    public String getMeterType() {
        return meterType;
    }

    /**
     * @param meterType the meterType to set
     */
    public void setMeterType(String meterType) {
        this.meterType = meterType;
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
     * @return the meterStandardPower
     */
    public String getMeterStandardPower() {
        return meterStandardPower;
    }

    /**
     * @param meterStandardPower the meterStandardPower to set
     */
    public void setMeterStandardPower(String meterStandardPower) {
        this.meterStandardPower = meterStandardPower;
    }

    /**
     * @return the meterManufacyurer
     */
    public String getMeterManufacyurer() {
        return meterManufacyurer;
    }

    /**
     * @param meterManufacyurer the meterManufacyurer to set
     */
    public void setMeterManufacyurer(String meterManufacyurer) {
        this.meterManufacyurer = meterManufacyurer;
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
     * @return the collectorType
     */
    public String getCollectorType() {
        return collectorType;
    }

    /**
     * @param collectorType the collectorType to set
     */
    public void setCollectorType(String collectorType) {
        this.collectorType = collectorType;
    }

    /**
     * @return the collectorManufacyurer
     */
    public String getCollectorManufacyurer() {
        return collectorManufacyurer;
    }

    /**
     * @param collectorManufacyurer the collectorManufacyurer to set
     */
    public void setCollectorManufacyurer(String collectorManufacyurer) {
        this.collectorManufacyurer = collectorManufacyurer;
    }

    /**
     * @return the collectorRemark
     */
    public String getCollectorRemark() {
        return collectorRemark;
    }

    /**
     * @param collectorRemark the collectorRemark to set
     */
    public void setCollectorRemark(String collectorRemark) {
        this.collectorRemark = collectorRemark;
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
     * @return the search
     */
    public String getSearch() {
        return search;
    }

    /**
     * @param search the search to set
     */
    public void setSearch(String search) {
        this.search = search;
    }
    
    
    
    
    
}
