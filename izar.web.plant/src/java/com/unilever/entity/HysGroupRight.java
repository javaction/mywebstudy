/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ussopp.Su
 */
@Entity
@XmlRootElement
@Table(name="hys_group_right")
@NamedQueries({
    @NamedQuery(name="HysGroupRight.deleteByGroupId",query="delete from HysGroupRight gr where gr.hysGroupRightPK.hysGroupId =:groupId")
})
public class HysGroupRight implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected HysGroupRightPK hysGroupRightPK;

    public HysGroupRight() {
    }

    public HysGroupRight(HysGroupRightPK hysGroupRightPK) {
        this.hysGroupRightPK = hysGroupRightPK;
    }
    
    public HysGroupRight(Long hysGroupId, Long hysRightId) {
        this.hysGroupRightPK = new HysGroupRightPK(hysGroupId,hysRightId);
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hysGroupRightPK != null ? hysGroupRightPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HysGroupRight)) {
            return false;
        }
        HysGroupRight other = (HysGroupRight) object;
        if ((this.hysGroupRightPK == null && other.hysGroupRightPK != null) || (this.hysGroupRightPK != null && !this.hysGroupRightPK.equals(other.hysGroupRightPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unilever.entity.HysGroupRight[ hysGroupRightPK=" + hysGroupRightPK + " ]";
    }
    
}
