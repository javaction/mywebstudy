/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.entity.exp;

import com.unilever.entity.HysMeter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ussopp.Su
 */
@XmlRootElement
public class PageHysMeter<T> extends HysMeter implements Serializable {
    private static final long serialVersionUID = 1L;
    
    //总页数
    private int totalpages;//不能用get/set方式
    
    //当前页数
    private int currpage = 1;
    
    //总记录数
    private int totalrecords;
    
    //每页条数
    private int pagesize = 10;
    
    private String sord;//排序标志  asc / desc
    private String sidx; //需要排序的字段
    
    private List<HysMeter> rows = new ArrayList<HysMeter>();  // **基础信息**// 用于返回全部的设备信息

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
    public List<HysMeter> getRows() {
        return rows;
    }

    /**
     * @param rows the rows to set
     */
    public void setRows(List<HysMeter> rows) {
        this.rows = rows;
    }
    
    
}
