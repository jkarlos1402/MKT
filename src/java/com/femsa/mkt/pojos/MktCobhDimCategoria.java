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
@Table(name = "MKT_COBH_DIM_CATEGORIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MktCobhDimCategoria.findAll", query = "SELECT m FROM MktCobhDimCategoria m")})
public class MktCobhDimCategoria implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_COBH_SEQ_CATEGORIA")
    @SequenceGenerator(name = "MKT_COBH_SEQ_CATEGORIA", sequenceName = "MKT_COBH_SEQ_CATEGORIA", allocationSize = 1)
    @Column(name = "PK_CATEGORIA")
    private Integer pkCategoria;
    @Column(name = "GV_CATEGORIA")
    private String gvCategoria;
    @Column(name = "CATEGORIATEXTO")
    private String categoriatexto;

    public MktCobhDimCategoria() {
    }

    public MktCobhDimCategoria(Integer pkCategoria) {
        this.pkCategoria = pkCategoria;
    }

    public MktCobhDimCategoria(String categoriatexto) {
        this.categoriatexto = categoriatexto;
    }

    public Integer getPkCategoria() {
        return pkCategoria;
    }

    public void setPkCategoria(Integer pkCategoria) {
        this.pkCategoria = pkCategoria;
    }

    public String getGvCategoria() {
        return gvCategoria;
    }

    public void setGvCategoria(String gvCategoria) {
        this.gvCategoria = gvCategoria;
    }

    public String getCategoriatexto() {
        return categoriatexto;
    }

    public void setCategoriatexto(String categoriatexto) {
        this.categoriatexto = categoriatexto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkCategoria != null ? pkCategoria.hashCode() : 0);
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
        final MktCobhDimCategoria other = (MktCobhDimCategoria) obj;
        if ((this.categoriatexto == null) ? (other.categoriatexto != null) : !this.categoriatexto.equals(other.categoriatexto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MktCobhDimCategoria{" + "pkCategoria=" + pkCategoria + ", gvCategoria=" + gvCategoria + ", categoriatexto=" + categoriatexto + '}';
    }

}
