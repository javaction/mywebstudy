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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 数据集
 * @author Ussopp.Su
 */
@Entity
@Table(name="hys_data")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name="HysData.findAll",query="select d from HysData d"),
    @NamedQuery(name="HysData.deleteById",query="delete from HysData d where d.hysStorageId =:hysStorageId"),
    @NamedQuery(name="HysData.findIdByName",query="select d.hysStorageId from HysData d where d.hysDataName=:hysDataName"),
    @NamedQuery(name="HysData.findDataByPid",query="select d.hysStorageId,d.hysDataName,d.hysDataPid ,d.hysDataLevel ,d.hysDataState from HysData d where d.hysDataPid =:hysDataPid"),
    @NamedQuery(name="HysData.findDataByPidAndName",query="select d.hysStorageId ,d.hysDataState from HysData d where d.hysDataPid=:hysDataPid and d.hysDataName =:hysDataName")
})
public class HysData implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id  
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="mseq")
    @SequenceGenerator(name="mseq",sequenceName="hys_seq_storage_id",allocationSize=1)
    @Column(name = "hys_storage_id")  
    private Long hysStorageId;
    
    @Basic(optional=false)
    @NotNull
    @Size(max=128)
    @Column(name="hys_data_name")
    private String hysDataName;
    
    @Basic(optional=false)
    @NotNull
    @Column(name="hys_data_pid")
    private int hysDataPid;
    
    @Basic(optional=false)
    @NotNull
    @Column(name="hys_data_level")
    private int hysDataLevel;
    
    @Basic(optional=false)
    @Column(name="hys_data_state")
    @Size(max=128)
    private String hysDataState;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getHysStorageId() != null ? getHysStorageId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HysData)) {
            return false;
        }
        HysData other = (HysData) object;
        if ((this.getHysStorageId() == null && other.getHysStorageId() != null) || (this.getHysStorageId() != null && !this.hysStorageId.equals(other.hysStorageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unilever.entity.HysData[ hysStorageId=" + getHysStorageId() + " ]";
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
     * @return the hysDataName
     */
    public String getHysDataName() {
        return hysDataName;
    }

    /**
     * @param hysDataName the hysDataName to set
     */
    public void setHysDataName(String hysDataName) {
        this.hysDataName = hysDataName;
    }

    /**
     * @return the hysDataPid
     */
    public int getHysDataPid() {
        return hysDataPid;
    }

    /**
     * @param hysDataPid the hysDataPid to set
     */
    public void setHysDataPid(int hysDataPid) {
        this.hysDataPid = hysDataPid;
    }

    /**
     * @return the hysDataLevel
     */
    public int getHysDataLevel() {
        return hysDataLevel;
    }

    /**
     * @param hysDataLevel the hysDataLevel to set
     */
    public void setHysDataLevel(int hysDataLevel) {
        this.hysDataLevel = hysDataLevel;
    }

    /**
     * @return the hysDataState
     */
    public String getHysDataState() {
        return hysDataState;
    }

    /**
     * @param hysDataState the hysDataState to set
     */
    public void setHysDataState(String hysDataState) {
        this.hysDataState = hysDataState;
    }
    
}
