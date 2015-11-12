/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.entity.exp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ussopp.Su
 */
@XmlRootElement
public class PageHysAreaExp<T> extends  HysAreaExp implements Serializable {
    private static final long serialVersionUID = 1L;
    
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
    private String nodeName; //节点名称
    private String pid;//父节点id
    
    private String dataState; //用于区分公共区域及其子节点与生产区域及其下级节点 ； 公共区域及其子节点 ---- public ;其他的为“”
    
    private String sord;//排序标志  asc / desc
    private String sidx; //需要排序的字段
    
    private List<HysAreaExp> rows = new ArrayList<HysAreaExp>(); // **基础信息**// 用于返回区域信息

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
     * @return the rows
     */
    public List<HysAreaExp> getRows() {
        return rows;
    }

    /**
     * @param rows the rows to set
     */
    public void setRows(List<HysAreaExp> rows) {
        this.rows = rows;
    }

    /**
     * @return the nodeName
     */
    public String getNodeName() {
        return nodeName;
    }

    /**
     * @param nodeName the nodeName to set
     */
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    /**
     * @return the dataState
     */
    public String getDataState() {
        return dataState;
    }

    /**
     * @param dataState the dataState to set
     */
    public void setDataState(String dataState) {
        this.dataState = dataState;
    }
    
    
    
}
