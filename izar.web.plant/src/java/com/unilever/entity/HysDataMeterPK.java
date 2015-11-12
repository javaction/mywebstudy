/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Ussopp.Su
 */
@Embeddable
public class HysDataMeterPK implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Basic(optional=false)
    @NotNull
    @Column(name="hys_meter_id")
    private Long hysMeterId;
    
    @Basic(optional=false)
    @NotNull
    @Column(name="hys_data_id")
    private Long hysDataId;

    public HysDataMeterPK() {
    }

    public HysDataMeterPK(Long hysMeterId, Long hysDataId) {
        this.hysMeterId = hysMeterId;
        this.hysDataId = hysDataId;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getHysMeterId() != null ? getHysMeterId().hashCode() : 0);
        hash += (getHysDataId() != null ? getHysDataId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HysDataMeterPK)) {
            return false;
        }
        HysDataMeterPK other = (HysDataMeterPK) object;
        if ((this.getHysMeterId() == null && other.getHysMeterId() != null) || (this.getHysMeterId() != null && !this.hysMeterId.equals(other.hysMeterId))) {
            return false;
        }
        if ((this.getHysDataId() == null && other.getHysDataId() != null) || (this.getHysDataId() != null && !this.hysDataId.equals(other.hysDataId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unilever.entity.HysDataMeterPK[ hysMeterId=" + getHysMeterId() + ",hysDataId = "+getHysDataId()+" ]";
    }

    /**
     * @return the hysMeterId
     */
    public Long getHysMeterId() {
        return hysMeterId;
    }

    /**
     * @param hysMeterId the hysMeterId to set
     */
    public void setHysMeterId(Long hysMeterId) {
        this.hysMeterId = hysMeterId;
    }

    /**
     * @return the hysDataId
     */
    public Long getHysDataId() {
        return hysDataId;
    }

    /**
     * @param hysDataId the hysDataId to set
     */
    public void setHysDataId(Long hysDataId) {
        this.hysDataId = hysDataId;
    }
    
}
