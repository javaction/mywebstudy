/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;  
import javax.persistence.Entity;  
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;  
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;  
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;  

/**
 *
 * @author Administrator
 */
@XmlRootElement  
@Entity  
@Table(name = "hys_user")  
@NamedQueries({
    //查询有没有符合的数据
    @NamedQuery(name = "HysUser.login", query = "SELECT h FROM HysUser h WHERE lower(h.hysUserName) =:name and h.hysUserPassword=:password"),
    @NamedQuery(name = "HysUser.findAll", query = "SELECT h FROM HysUser h"),
    @NamedQuery(name = "HysUser.findByHyStorageId", query = "SELECT h FROM HysUser h WHERE h.hysStorageId=:hysStorageId"),
    @NamedQuery(name = "HysUser.findByHyName", query = "SELECT h FROM HysUser h WHERE h.hysUserName=:hysUserName"),
//    @NamedQuery(name = "HysUser.findByHyGroup", query = "SELECT h FROM HysUser h WHERE h.hysGroupId=:hysGroupId"),
     @NamedQuery(name = "HysUser.findByHyGroup", query = "SELECT h FROM HysUser h WHERE h.group.hysStorageId =:hysGroupId")
})
public class HysUser implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id  
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="mseq")
    @SequenceGenerator(name="mseq",sequenceName="hys_seq_storage_id",allocationSize=1)
    @Column(name = "hys_storage_id")  
    private Long hysStorageId;  
    
    @Basic(optional = false)
    @NotNull
    @Size(max = 128)
    @Column(name = "hys_user_name")  
    private String hysUserName;  
    
    @Basic(optional = false)
    @NotNull
    @Size(max = 128)
    @Column(name = "hys_user_password")  
    private String hysUserPassword;  
    
    /*
    @Column(name = "hys_group_id") 
    private Long hysGroupId;  
    */
    @ManyToOne
    @JoinColumn(name="hys_group_id")
    private HysGroup group;
    
    @Column(name = "hys_desc")  
    private String hysDesc;  

    /**
     * @return the hysStorageId
     */
    public Long getHysStorageId() {
        return hysStorageId;
    }

    /**
     * @param hysStorageId the hysStorageId to set
     */
    public void setHysStorageId(Long hysStorageId) {
        this.hysStorageId = hysStorageId;
    }

    /**
     * @return the hysUserName
     */
    public String getHysUserName() {
        return hysUserName;
    }

    /**
     * @param hysUserName the hysUserName to set
     */
    public void setHysUserName(String hysUserName) {
        this.hysUserName = hysUserName;
    }

    /**
     * @return the hysUserPassword
     */
    public String getHysUserPassword() {
        return hysUserPassword;
    }

    /**
     * @param hysUserPassword the hysUserPassword to set
     */
    public void setHysUserPassword(String hysUserPassword) {
        this.hysUserPassword = hysUserPassword;
    }

    
    /**
     * @return the hysGroupId
     */
    /*
    public Long getHysGroupId() {
        return hysGroupId;
    }
*/
    /**
     * @param hysGroupId the hysGroupId to set
     */
    /*
    public void setHysGroupId(Long hysGroupId) {
        this.hysGroupId = hysGroupId;
    }
    */
    /**
     * @return the hysDesc
     */
    public String getHysDesc() {
        return hysDesc;
    }

    /**
     * @param hysDesc the hysDesc to set
     */
    public void setHysDesc(String hysDesc) {
        this.hysDesc = hysDesc;
    }

    /**
     * @return the group
     */
    public HysGroup getGroup() {
        return group;
    }

    /**
     * @param group the group to set
     */
    public void setGroup(HysGroup group) {
        this.group = group;
    }

} 
