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
 * 参数表
 * @author Ussopp.Su
 */
@Entity
@XmlRootElement
@Table(name="hys_parameter")
@NamedQueries({
    @NamedQuery(name="HysParameter.findAllPara",query="SELECT hp FROM HysParameter hp ") //此处先添加，sql先保留
    
})
public class HysParameter implements Serializable {
    private static final long serialVersionUID = 1L;
     
    @Id  
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="mseq")
    @SequenceGenerator(name="mseq",sequenceName="hys_seq_storage_id",allocationSize=1)
    @Column(name = "hys_storage_id")  
    private Long hysStorageId; 
    
    @Basic(optional=false)
    @NotNull()
    @Column(name="hys_parameter_code")
    @Size(max=128)
    private String hysParameterCode;
    
    @Basic(optional=false)
    @NotNull()
    @Column(name="hys_parameter_value")
    @Size(max=128)
    private String hysParameterValue;
    
    @Basic(optional=false)
    @NotNull()
    @Column(name="hys_parameter_type")
    @Size(max=128)
    private String hysParameterType;
    
    @Column(name="hys_remark")
    @Size(max=128)
    private String hysRemark;

    public void setHysStorageId(Long hysStorageId) {
        this.hysStorageId = hysStorageId;
    }

    public void setHysParameterCode(String hysParameterCode) {
        this.hysParameterCode = hysParameterCode;
    }

    public void setHysParameterValue(String hysParameterValue) {
        this.hysParameterValue = hysParameterValue;
    }

    public void setHysParameterType(String hysParameterType) {
        this.hysParameterType = hysParameterType;
    }

    public void setHysRemark(String hysRemark) {
        this.hysRemark = hysRemark;
    }

    public Long getHysStorageId() {
        return hysStorageId;
    }

    public String getHysParameterCode() {
        return hysParameterCode;
    }

    public String getHysParameterValue() {
        return hysParameterValue;
    }

    public String getHysParameterType() {
        return hysParameterType;
    }

    public String getHysRemark() {
        return hysRemark;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hysStorageId != null ? hysStorageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HysParameter)) {
            return false;
        }
        HysParameter other = (HysParameter) object;
        if ((this.hysStorageId == null && other.hysStorageId != null) || (this.hysStorageId != null && !this.hysStorageId.equals(other.hysStorageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.unilever.entity.HysParameter[ id=" + hysStorageId + " ]";
    }
    
}
