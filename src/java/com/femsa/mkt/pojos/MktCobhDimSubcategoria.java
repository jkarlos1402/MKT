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
@Table(name = "MKT_COBH_DIM_SUBCATEGORIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MktCobhDimSubcategoria.findAll", query = "SELECT m FROM MktCobhDimSubcategoria m")})
public class MktCobhDimSubcategoria implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_COBH_SEQ_SUBCATEGORIA")
    @SequenceGenerator(name = "MKT_COBH_SEQ_SUBCATEGORIA", sequenceName = "MKT_COBH_SEQ_SUBCATEGORIA", allocationSize = 1)
    @Column(name = "PK_SUBCATEGORIA")
    private Integer pkSubcategoria;
    @Column(name = "GV_SUBCATEGORIA")
    private String gvSubcategoria;
    @Column(name = "SUBCATEGORIATEXTO")
    private String subcategoriatexto;

    public MktCobhDimSubcategoria() {
    }

    public MktCobhDimSubcategoria(Integer pkSubcategoria) {
        this.pkSubcategoria = pkSubcategoria;
    }

    public Integer getPkSubcategoria() {
        return pkSubcategoria;
    }

    public void setPkSubcategoria(Integer pkSubcategoria) {
        this.pkSubcategoria = pkSubcategoria;
    }

    public String getGvSubcategoria() {
        return gvSubcategoria;
    }

    public void setGvSubcategoria(String gvSubcategoria) {
        this.gvSubcategoria = gvSubcategoria;
    }

    public String getSubcategoriatexto() {
        return subcategoriatexto;
    }

    public void setSubcategoriatexto(String subcategoriatexto) {
        this.subcategoriatexto = subcategoriatexto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkSubcategoria != null ? pkSubcategoria.hashCode() : 0);
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
        final MktCobhDimSubcategoria other = (MktCobhDimSubcategoria) obj;
        if ((this.subcategoriatexto == null) ? (other.subcategoriatexto != null) : !this.subcategoriatexto.equals(other.subcategoriatexto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MktCobhDimSubcategoria{" + "pkSubcategoria=" + pkSubcategoria + ", gvSubcategoria=" + gvSubcategoria + ", subcategoriatexto=" + subcategoriatexto + '}';
    }

}
