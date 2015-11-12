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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 采集器表
 * @author Ussopp.Su
 */
@Entity
@XmlRootElement
@Table(name="hys_collector")
@NamedQueries({
     @NamedQuery(name="HysCollector.findAll",query="select c from  HysCollector c"),
     @NamedQuery(name="HysCollector.findIdByCollectorNum",query="select c.hysStorageId from HysCollector c where c.hysCollectorNumber =:hysCollectorNumber"),
     @NamedQuery(name="HysCollector.deleteById",query="delete from HysCollector c where c.hysStorageId =:hysStorageId")
})
public class HysCollector implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id  
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="mseq")
    @SequenceGenerator(name="mseq",sequenceName="hys_seq_storage_id",allocationSize=1)
    @Column(name = "hys_storage_id")  
    private Long hysStorageId;

    @Basic(optional=false)
    @NotNull()
    @Column(name="hys_collector_number")
    @Size(max=128)
    private String hysCollectorNumber;
        
    @Basic(optional=false)
    @NotNull()
    @Column(name="hys_collector_type")
    @Size(max=128)
    private String hysCollectorType;
            
    @Basic(optional=false)
    @NotNull()
    @Column(name="hys_manufacturer")
    @Size(max=128)
    private String hysManufacturer;
                
    @Basic(optional=false)
    @Column(name="hys_remark")
    @Size(max=128)
    private String hysRemark;
    
    @Transient
    private String isSeting; // 采集器是否关联某个设备 ： 关联 --yes ；未关联 ---no

    public void setHysStorageId(Long hysStorageId) {
        this.hysStorageId = hysStorageId;
    }

    public void setHysCollectorNumber(String hysCollectorNumber) {
        this.hysCollectorNumber = hysCollectorNumber;
    }

    public void setHysCollectorType(String hysCollectorType) {
        this.hysCollectorType = hysCollectorType;
    }

    public void setHysManufacturer(String hysManufacturer) {
        this.hysManufacturer = hysManufacturer;
    }

    public void setHysRemark(String hysRemark) {
        this.hysRemark = hysRemark;
    }

    public Long getHysStorageId() {
        return hysStorageId;
    }

    public String getHysCollectorNumber() {
        return hysCollectorNumber;
    }

    public String getHysCollectorType() {
        return hysCollectorType;
    }

    public String getHysManufacturer() {
        return hysManufacturer;
    }

    public String getHysRemark() {
        return hysRemark;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hysStorageId != null ? hysStorageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HysCollector)) {
            return false;
        }
        HysCollector other = (HysCollector) object;
        if ((this.hysStorageId == null && other.hysStorageId != null) || (this.hysStorageId != null && !this.hysStorageId.equals(other.hysStorageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unilever.entity.HysCollector[ id=" + hysStorageId + " ]";
    }

    /**
     * @return the isSeting
     */
    public String getIsSeting() {
        return isSeting;
    }

    /**
     * @param isSeting the isSeting to set
     */
    public void setIsSeting(String isSeting) {
        this.isSeting = isSeting;
    }
    
}
