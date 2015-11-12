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
@Table(name="hys_meter_collector")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name="HysMeterCollector.deleteByMeterId",query="delete from HysMeterCollector mc where mc.hysMeterCollectorPK.hysMeterId =:hysMeterId"),
    @NamedQuery(name="HysMeterCollector.deleteByCollectorId",query="delete from HysMeterCollector mc where mc.hysMeterCollectorPK.hysCollectorId =:hysCollectorId")
})
public class HysMeterCollector implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected HysMeterCollectorPK hysMeterCollectorPK;

    public HysMeterCollector() {
    }

    public HysMeterCollector(HysMeterCollectorPK hysMeterCollectorPK) {
        this.hysMeterCollectorPK = hysMeterCollectorPK;
    }
    
    public HysMeterCollector(Long hysMeterId, Long hysCollectorId) {
        this.hysMeterCollectorPK = new HysMeterCollectorPK(hysMeterId,hysCollectorId);
    }

    public void setHysMeterCollectorPK(HysMeterCollectorPK hysMeterCollectorPK) {
        this.hysMeterCollectorPK = hysMeterCollectorPK;
    }

    public HysMeterCollectorPK getHysMeterCollectorPK() {
        return hysMeterCollectorPK;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hysMeterCollectorPK != null ? hysMeterCollectorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HysMeterCollector)) {
            return false;
        }
        HysMeterCollector other = (HysMeterCollector) object;
        if ((this.hysMeterCollectorPK == null && other.hysMeterCollectorPK != null) || (this.hysMeterCollectorPK != null && !this.hysMeterCollectorPK.equals(other.hysMeterCollectorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unilever.entity.HysMeterCollector[ hysMeterCollectorPK=" + hysMeterCollectorPK + " ]";
    }
    
}
