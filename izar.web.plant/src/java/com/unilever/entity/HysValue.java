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
 * 抄表数据表（原始表s.）
 * @author Administrator
 */
@Entity
@Table(name="hys_value")
@XmlRootElement
@NamedQueries({

})
public class HysValue implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id  
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="mseq")
    @SequenceGenerator(name="mseq",sequenceName="hys_seq_storage_id",allocationSize=1)
    @Column(name = "hys_storage_id")  
    private Long hysStorageId;

    @Column(name="hys_line_id")
    @NotNull
    @Basic(optional=false)
    private Long hysLineId;
    
    @Column(name="hys_semantic_id")
    @NotNull
    @Basic(optional=false)
    @Size(max=128)
    private String hysSemanticId;
    
    @Column(name="hys_org_value")
    @NotNull
    @Basic(optional=false)
    private Double hysOrgValue;
    
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
        if (!(object instanceof HysValue)) {
            return false;
        }
        HysValue other = (HysValue) object;
        if ((this.getHysStorageId() == null && other.getHysStorageId() != null) || (this.getHysStorageId() != null && !this.hysStorageId.equals(other.hysStorageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unilever.entity.HysValue[ hysStorageId=" + getHysStorageId() + " ]";
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
     * @return the hysLineId
     */
    public Long getHysLineId() {
        return hysLineId;
    }

    /**
     * @param hysLineId the hysLineId to set
     */
    public void setHysLineId(Long hysLineId) {
        this.hysLineId = hysLineId;
    }

    /**
     * @return the hysSemanticId
     */
    public String getHysSemanticId() {
        return hysSemanticId;
    }

    /**
     * @param hysSemanticId the hysSemanticId to set
     */
    public void setHysSemanticId(String hysSemanticId) {
        this.hysSemanticId = hysSemanticId;
    }

    /**
     * @return the hysOrgValue
     */
    public Double getHysOrgValue() {
        return hysOrgValue;
    }

    /**
     * @param hysOrgValue the hysOrgValue to set
     */
    public void setHysOrgValue(Double hysOrgValue) {
        this.hysOrgValue = hysOrgValue;
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
