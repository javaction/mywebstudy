/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unilever.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 权限表
 * @author Ussopp.Su
 */
@Entity
@Table(name="hys_right")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name="HysRight.findAll",query="select r from  HysRight r")
})
public class HysRight implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id  
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="mseq")
    @SequenceGenerator(name="mseq",sequenceName="hys_seq_storage_id",allocationSize=1)
    @Column(name = "hys_storage_id")  
    private Long hysStorageId;
    
    @Column(name="hys_right_name")
    @NotNull
    @Basic(optional=false)
    @Size(max=128)
    private String hysRightName;

    @Column(name="hys_right_code")
    @NotNull
    @Basic(optional=false)
    @Size(max=128)
    private String hysRightCode;
    
    @Column(name="hys_right_pid")
    @NotNull
    @Basic(optional=false)
    @Size(max=128)
    private BigInteger hysRightPid;
    
//    @ManyToMany(cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},mappedBy="rightList",fetch= FetchType.LAZY)
//    private Collection<HysGroup> groupList = new ArrayList<HysGroup>();
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getHysStorageId() != null ? getHysStorageId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HysRight)) {
            return false;
        }
        HysRight other = (HysRight) object;
        if ((this.getHysStorageId() == null && other.getHysStorageId() != null) || (this.getHysStorageId() != null && !this.hysStorageId.equals(other.hysStorageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unilever.entity.HysRight[ hysStorageId=" + getHysStorageId() + " ]";
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
     * @return the hysRightName
     */
    public String getHysRightName() {
        return hysRightName;
    }

    /**
     * @param hysRightName the hysRightName to set
     */
    public void setHysRightName(String hysRightName) {
        this.hysRightName = hysRightName;
    }

    /**
     * @return the hysRightCode
     */
    public String getHysRightCode() {
        return hysRightCode;
    }

    /**
     * @param hysRightCode the hysRightCode to set
     */
    public void setHysRightCode(String hysRightCode) {
        this.hysRightCode = hysRightCode;
    }

    /**
     * @return the hysRightPid
     */
    public BigInteger getHysRightPid() {
        return hysRightPid;
    }

    /**
     * @param hysRightPid the hysRightPid to set
     */
    public void setHysRightPid(BigInteger hysRightPid) {
        this.hysRightPid = hysRightPid;
    }

//    /**
//     * @return the groupList
//     */
//    public Collection<HysGroup> getGroupList() {
//        return groupList;
//    }
//
//    /**
//     * @param groupList the groupList to set
//     */
//    public void setGroupList(Collection<HysGroup> groupList) {
//        this.groupList = groupList;
//    }

   
    
}
