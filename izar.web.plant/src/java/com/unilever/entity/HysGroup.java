/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "hys_group")  
@NamedQueries({
    @NamedQuery(name = "HysGroup.findAll", query = "SELECT h FROM HysGroup h"),
    @NamedQuery(name = "HysGroup.findByHysStorageId", query = "SELECT h FROM HysGroup h WHERE h.hysStorageId=:hysStorageId"),
    @NamedQuery(name = "HysGroup.findByHysGroupName", query = "SELECT h FROM HysGroup h WHERE h.hysGroupName=:hysGroupName"),
    @NamedQuery(name = "HysGroup.deleteByHysStorageId",query = "delete from HysGroup h where h.hysStorageId =:hysStorageId")
})
public class HysGroup implements Serializable {
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
    @Column(name = "hys_group_name")  
    private String hysGroupName;  
    
    @Basic(optional = false)
    @NotNull
    @Size(max = 128)
    @Column(name = "hys_desc")  
    private String hysDesc;  
    
    @ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="hys_group_right",joinColumns={@JoinColumn(name="hys_group_id")},inverseJoinColumns={@JoinColumn(name="hys_right_id")})
    private Collection<HysRight> rightList = new ArrayList<HysRight>();
    

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
     * @return the hysGroupName
     */
    public String getHysGroupName() {
        return hysGroupName;
    }

    /**
     * @param hysGroupName the hysGroupName to set
     */
    public void setHysGroupName(String hysGroupName) {
        this.hysGroupName = hysGroupName;
    }

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
     * @return the rightList
     */
    public Collection<HysRight> getRightList() {
        return rightList;
    }

    /**
     * @param rightList the rightList to set
     */
    public void setRightList(Collection<HysRight> rightList) {
        this.rightList = rightList;
    }

 
    
    
}
