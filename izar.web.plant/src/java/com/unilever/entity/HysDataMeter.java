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
@Table(name="hys_data_meter")
@NamedQueries({
    @NamedQuery(name="HysDataMeter.deleteByMeterId",query="delete from HysDataMeter d where d.hysDataMeterPK.hysMeterId =:hysMeterId"),
    @NamedQuery(name="HysDataMeter.findMeterIdByDataId",query="select d.hysDataMeterPK.hysMeterId from HysDataMeter d where d.hysDataMeterPK.hysDataId=:hysDataId")
})
public class HysDataMeter implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    private HysDataMeterPK hysDataMeterPK;

    public HysDataMeter() {
    }

    public HysDataMeter(HysDataMeterPK hysDataMeterPK) {
        this.hysDataMeterPK = hysDataMeterPK;
    }

    public HysDataMeter(Long hysMeterId, Long hysDataId) {
        this.hysDataMeterPK = new HysDataMeterPK(hysMeterId, hysDataId);
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getHysDataMeterPK() != null ? getHysDataMeterPK().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HysDataMeter)) {
            return false;
        }
        HysDataMeter other = (HysDataMeter) object;
        if ((this.getHysDataMeterPK() == null && other.getHysDataMeterPK() != null) || (this.getHysDataMeterPK() != null && !this.hysDataMeterPK.equals(other.hysDataMeterPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unilever.entity.HysDataMeter[ hysDataMeterPK=" + getHysDataMeterPK() + " ]";
    }

    /**
     * @return the hysDataMeterPK
     */
    public HysDataMeterPK getHysDataMeterPK() {
        return hysDataMeterPK;
    }

    /**
     * @param hysDataMeterPK the hysDataMeterPK to set
     */
    public void setHysDataMeterPK(HysDataMeterPK hysDataMeterPK) {
        this.hysDataMeterPK = hysDataMeterPK;
    }
    
}
