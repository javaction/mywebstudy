/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 抄表记录表s.
 * @author Administrator
 */
@Entity
@XmlRootElement
@Table
@NamedQueries({

})
public class HysValueLine implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id  
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="mseq")
    @SequenceGenerator(name="mseq",sequenceName="hys_seq_storage_id",allocationSize=1)
    @Column(name = "hys_storage_id")  
    private Long hysStorageId;
    
    @Column(name="hys_meter_number")
    @Basic(optional=false)
    @NotNull
    @Size(max=128)
    private String hysMeterNumber;
    
    @Column(name="hys_time_stamp")
    @Basic(optional=false)
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date hysTimeStamp;

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getHysStorageId() != null ? getHysStorageId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HysValueLine)) {
            return false;
        }
        HysValueLine other = (HysValueLine) object;
        if ((this.getHysStorageId() == null && other.getHysStorageId() != null) || (this.getHysStorageId() != null && !this.hysStorageId.equals(other.hysStorageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unilever.entity.HysValueLine[ hysStorageId=" + getHysStorageId() + " ]";
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
     * @return the hysTimeStamp
     */
    public Date getHysTimeStamp() {
        return hysTimeStamp;
    }

    /**
     * @param hysTimeStamp the hysTimeStamp to set
     */
    public void setHysTimeStamp(Date hysTimeStamp) {
        this.hysTimeStamp = hysTimeStamp;
    }
    
}
