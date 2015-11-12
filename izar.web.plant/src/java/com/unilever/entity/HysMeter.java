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
 * 设备表
 * @author Ussopp.Su
 */
@Entity
@XmlRootElement
@Table(name="hys_meter")
@NamedQueries({
     @NamedQuery(name="HysMeter.findAllMeter",query="select m from HysMeter m"),
     @NamedQuery(name="HysMeter.findIdByMeterNum",query="select m.hysStorageId  from HysMeter m where m.hysMeterNumber =:hysMeterNumber"),
     @NamedQuery(name="HysMeter.deleteByMeterNum",query = "delete from HysMeter m where m.hysMeterNumber =:hysMeterNumber"),
     @NamedQuery(name="HysMeter.deleteByMeterId",query="delete from HysMeter m where m.hysStorageId =:hysStorageId")
})
public class HysMeter implements Serializable {
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
    @Column(name="hys_meter_number")
    @Size(max=128)
    private String hysMeterNumber;
    
    @Basic(optional=false)
    @NotNull()
    @Column(name="hys_meter_name")
    @Size(max=128)
    private String hysMeterName;
    
    @Basic(optional=false)
    @NotNull()
    @Column(name="hys_meter_code")
    @Size(max=128)
    private String hysMeterCode;
    
    @Basic(optional=false)
    @NotNull()
    @Column(name="hys_meter_type")
    @Size(max=128)
    private String hysMeterType;
    
    @Basic(optional=false)
    @NotNull()
    @Column(name="hys_standard_power")
    private Double hysStandardPower;
    
    @Basic(optional=false)
    @NotNull()
    @Column(name="hys_manufacturer")
    @Size(max=128)
    private String hysManufacturer;//厂商
    
    @Transient
    private String hysCollectorNum; //设备对应的其采集器的编号
    @Transient 
    private String collectorId; // 设备对应的采集器id
    
    @Transient
    private String isSeting; //区域修改时候用于关联设备的默认选中，选中 --yes ；不选中 --no
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getHysStorageId() != null ? getHysStorageId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HysMeter)) {
            return false;
        }
        HysMeter other = (HysMeter) object;
        if ((this.getHysStorageId() == null && other.getHysStorageId() != null) || (this.getHysStorageId() != null && !this.hysStorageId.equals(other.hysStorageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unilever.entity.HysMeter[ id=" + getHysStorageId() + " ]";
    }

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
     * @return the hysMeterNumber
     */
    public String getHysMeterNumber() {
        return hysMeterNumber;
    }

    /**
     * @param hysMeterNumber the hysMeterNumber to set
     */
    public void setHysMeterNumber(String hysMeterNumber) {
        this.hysMeterNumber = hysMeterNumber;
    }

    /**
     * @return the hysMeterCode
     */
    public String getHysMeterCode() {
        return hysMeterCode;
    }

    /**
     * @param hysMeterCode the hysMeterCode to set
     */
    public void setHysMeterCode(String hysMeterCode) {
        this.hysMeterCode = hysMeterCode;
    }

    /**
     * @return the hysMeterType
     */
    public String getHysMeterType() {
        return hysMeterType;
    }

    /**
     * @param hysMeterType the hysMeterType to set
     */
    public void setHysMeterType(String hysMeterType) {
        this.hysMeterType = hysMeterType;
    }

    /**
     * @return the hysManufacturer
     */
    public String getHysManufacturer() {
        return hysManufacturer;
    }

    /**
     * @param hysManufacturer the hysManufacturer to set
     */
    public void setHysManufacturer(String hysManufacturer) {
        this.hysManufacturer = hysManufacturer;
    }

    /**
     * @return the hysCollectorNum
     */
    public String getHysCollectorNum() {
        return hysCollectorNum;
    }

    /**
     * @param hysCollectorNum the hysCollectorNum to set
     */
    public void setHysCollectorNum(String hysCollectorNum) {
        this.hysCollectorNum = hysCollectorNum;
    }

    /**
     * @return the hysMeterName
     */
    public String getHysMeterName() {
        return hysMeterName;
    }

    /**
     * @param hysMeterName the hysMeterName to set
     */
    public void setHysMeterName(String hysMeterName) {
        this.hysMeterName = hysMeterName;
    }

    /**
     * @return the hysStandardPower
     */
    public Double getHysStandardPower() {
        return hysStandardPower;
    }

    /**
     * @param hysStandardPower the hysStandardPower to set
     */
    public void setHysStandardPower(Double hysStandardPower) {
        this.hysStandardPower = hysStandardPower;
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

    /**
     * @return the collectorId
     */
    public String getCollectorId() {
        return collectorId;
    }

    /**
     * @param collectorId the collectorId to set
     */
    public void setCollectorId(String collectorId) {
        this.collectorId = collectorId;
    }
    
}
