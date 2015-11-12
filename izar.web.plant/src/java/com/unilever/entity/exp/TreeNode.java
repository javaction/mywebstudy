/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.entity.exp;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ussopp.Su
 */
@XmlRootElement
public class TreeNode implements Serializable{
    private static final long serialVersionUID = -8079868256837582676L; 
    
    private String id ;//当前节点id
    private String pid ;//父节点id
    private String name;//节点名称
    private String currLevel; //节点等级 
    private String isParent; //节点是否有下级节点
    
    private String dataState; //用于区分公共区域及其子节点与生产区域及其下级节点 ； 公共区域及其子节点 ---- public ;其他的为“”

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the currLevel
     */
    public String getCurrLevel() {
        return currLevel;
    }

    /**
     * @param currLevel the currLevel to set
     */
    public void setCurrLevel(String currLevel) {
        this.currLevel = currLevel;
    }

    /**
     * @return the isParent
     */
    public String getIsParent() {
        return isParent;
    }

    /**
     * @param isParent the isParent to set
     */
    public void setIsParent(String isParent) {
        this.isParent = isParent;
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
