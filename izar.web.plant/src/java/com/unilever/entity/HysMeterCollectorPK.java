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
public class HysMeterCollectorPK implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Basic(optional=false)
    @NotNull()
    @Column(name="hys_meter_id")
    private Long hysMeterId;
    
    @Basic(optional=false)
    @NotNull
    @Column(name="hys_collector_id")
    private Long hysCollectorId;

    public HysMeterCollectorPK() {
        
    }

    public HysMeterCollectorPK(Long hysMeterId, Long hysCollectorId) {
        this.hysMeterId = hysMeterId;
        this.hysCollectorId = hysCollectorId;
    }

    public void setHysMeterId(long hysMeterId) {
        this.hysMeterId = hysMeterId;
    }

    public void setHysCollectorId(Long hysCollectorId) {
        this.hysCollectorId = hysCollectorId;
    }

    public long getHysMeterId() {
        return hysMeterId;
    }

    public Long getHysCollectorId() {
        return hysCollectorId;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hysMeterId != null ? hysMeterId.hashCode() : 0);
        hash += (hysCollectorId != null ? hysCollectorId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HysMeterCollectorPK)) {
            return false;
        }
        HysMeterCollectorPK other = (HysMeterCollectorPK) object;
        if ((this.hysMeterId == null && other.hysMeterId != null) || (this.hysMeterId != null && !this.hysMeterId.equals(other.hysMeterId))) {
            return false;
        }
        if ((this.hysCollectorId == null && other.hysCollectorId != null) || (this.hysCollectorId != null && !this.hysCollectorId.equals(other.hysCollectorId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unilever.entity.HysMeterCollectorPK[ hysMeterId=" + hysMeterId + ",hysCollectorId = "+hysCollectorId +" ]";
    }
    
}
