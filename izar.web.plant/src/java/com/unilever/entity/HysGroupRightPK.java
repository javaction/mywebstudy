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
public class HysGroupRightPK implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Column(name="hys_group_id")
    @NotNull
    @Basic(optional=false)
    private Long hysGroupId;
    
    @Column(name="hys_right_id")
    @NotNull
    @Basic(optional=false)
    private Long hysRightId;

    public HysGroupRightPK() {
    }

    public HysGroupRightPK(Long hysGroupId, Long hysRightId) {
        this.hysGroupId = hysGroupId;
        this.hysRightId = hysRightId;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getHysGroupId() != null ? getHysGroupId().hashCode() : 0);
        hash += (getHysRightId() != null ? getHysRightId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HysGroupRightPK)) {
            return false;
        }
        HysGroupRightPK other = (HysGroupRightPK) object;
        if ((this.getHysGroupId() == null && other.getHysGroupId() != null) || (this.getHysGroupId() != null && !this.hysGroupId.equals(other.hysGroupId))) {
            return false;
        }
        if ((this.getHysRightId() == null && other.getHysRightId() != null) || (this.getHysRightId() != null && !this.hysRightId.equals(other.hysRightId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unilever.entity.HysGroupRightPK[ hysGroupId=" + getHysGroupId() + ",hysRightId= ]"+getHysRightId();
    }

    /**
     * @return the hysGroupId
     */
    public Long getHysGroupId() {
        return hysGroupId;
    }

    /**
     * @param hysGroupId the hysGroupId to set
     */
    public void setHysGroupId(Long hysGroupId) {
        this.hysGroupId = hysGroupId;
    }

    /**
     * @return the hysRightId
     */
    public Long getHysRightId() {
        return hysRightId;
    }

    /**
     * @param hysRightId the hysRightId to set
     */
    public void setHysRightId(Long hysRightId) {
        this.hysRightId = hysRightId;
    }
    
}
