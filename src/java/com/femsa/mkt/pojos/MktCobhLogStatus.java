/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.femsa.mkt.pojos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TMXIDSJPINAM
 */
@Entity
@Table(name = "MKT_COBH_LOG_STATUS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MktCobhLogStatus.findAll", query = "SELECT m FROM MktCobhLogStatus m")})
public class MktCobhLogStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "PK_IDSTATUS")
    private Integer pkIdstatus;
    @Column(name = "DESCRIPCION")
    private String descripcion;    

    public MktCobhLogStatus() {
    }

    public MktCobhLogStatus(Integer pkIdstatus) {
        this.pkIdstatus = pkIdstatus;
    }

    public Integer getPkIdstatus() {
        return pkIdstatus;
    }

    public void setPkIdstatus(Integer pkIdstatus) {
        this.pkIdstatus = pkIdstatus;
    }

    public String getDescripcion() {
        return descripcion;
    }

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
        if (!(object instanceof MktCobhLogStatus)) {
            return false;
        }
        MktCobhLogStatus other = (MktCobhLogStatus) object;
        if ((this.pkIdstatus == null && other.pkIdstatus != null) || (this.pkIdstatus != null && !this.pkIdstatus.equals(other.pkIdstatus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.femsa.mkt.pojos.MktCobhLogStatus[ pkIdstatus=" + pkIdstatus + " ]";
    }
    
}
