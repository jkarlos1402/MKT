package com.femsa.mkt.pojos;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author TMXIDSJPINAM
 */
@Entity
@Table(name = "MKT_CAT_STATUS")
public class MktAdmin_CatStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "PK_IDSTATUS")
    private BigDecimal pkIdstatus;

    @Column(name = "DESCRIPCION")
    private String descripcion;    

    /**
     *
     * @return
     */
    public BigDecimal getPkIdstatus() {
        return pkIdstatus;
    }

    /**
     *
     * @param pkIdstatus
     */
    public void setPkIdstatus(BigDecimal pkIdstatus) {
        this.pkIdstatus = pkIdstatus;
    }

    /**
     *
     * @return
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     *
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdstatus != null ? pkIdstatus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MktAdmin_CatStatus)) {
            return false;
        }
        MktAdmin_CatStatus other = (MktAdmin_CatStatus) object;
        if ((this.pkIdstatus == null && other.pkIdstatus != null) || (this.pkIdstatus != null && !this.pkIdstatus.equals(other.pkIdstatus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descripcion;
    }
    
}
