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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TMXIDSJPINAM
 */
@Entity
@Table(name = "MKT_COBH_DIM_NIVELMERCADEO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MktCobhDimNivelmercadeo.findAll", query = "SELECT m FROM MktCobhDimNivelmercadeo m")})
public class MktCobhDimNivelmercadeo implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_COBH_SEQ_NIVELMERCADEO")
    @SequenceGenerator(name = "MKT_COBH_SEQ_NIVELMERCADEO", sequenceName = "MKT_COBH_SEQ_NIVELMERCADEO", allocationSize = 1)
    @Column(name = "PK_NIVELMERCADEO")
    private Integer pkNivelmercadeo;
    @Column(name = "GV_NIVELMERCADEO")
    private String gvNivelmercadeo;
    @Column(name = "NIVELMERCADEOTEXTO")
    private String nivelmercadeotexto;

    public MktCobhDimNivelmercadeo() {
    }

    public MktCobhDimNivelmercadeo(Integer pkNivelmercadeo) {
        this.pkNivelmercadeo = pkNivelmercadeo;
    }

    public Integer getPkNivelmercadeo() {
        return pkNivelmercadeo;
    }

    public void setPkNivelmercadeo(Integer pkNivelmercadeo) {
        this.pkNivelmercadeo = pkNivelmercadeo;
    }

    public String getGvNivelmercadeo() {
        return gvNivelmercadeo;
    }

    public void setGvNivelmercadeo(String gvNivelmercadeo) {
        this.gvNivelmercadeo = gvNivelmercadeo;
    }

    public String getNivelmercadeotexto() {
        return nivelmercadeotexto;
    }

    public void setNivelmercadeotexto(String nivelmercadeotexto) {
        this.nivelmercadeotexto = nivelmercadeotexto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkNivelmercadeo != null ? pkNivelmercadeo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MktCobhDimNivelmercadeo other = (MktCobhDimNivelmercadeo) obj;
        if ((this.nivelmercadeotexto == null) ? (other.nivelmercadeotexto != null) : !this.nivelmercadeotexto.equals(other.nivelmercadeotexto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MktCobhDimNivelmercadeo{" + "pkNivelmercadeo=" + pkNivelmercadeo + ", gvNivelmercadeo=" + gvNivelmercadeo + ", nivelmercadeotexto=" + nivelmercadeotexto + '}';
    }

}
