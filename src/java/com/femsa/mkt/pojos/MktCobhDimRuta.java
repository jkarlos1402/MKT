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
@Table(name = "MKT_COBH_DIM_RUTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MktCobhDimRuta.findAll", query = "SELECT m FROM MktCobhDimRuta m")})
public class MktCobhDimRuta implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_COBH_SEQ_RUTA")
    @SequenceGenerator(name = "MKT_COBH_SEQ_RUTA", sequenceName = "MKT_COBH_SEQ_RUTA", allocationSize = 1)
    @Column(name = "PK_RUTA")
    private Integer pkRuta;
    @Column(name = "GV_RUTA")
    private String gvRuta;
    @Column(name = "RUTATEXTO")
    private String rutatexto;

    public MktCobhDimRuta() {
    }

    public MktCobhDimRuta(Integer pkRuta) {
        this.pkRuta = pkRuta;
    }

    public Integer getPkRuta() {
        return pkRuta;
    }

    public void setPkRuta(Integer pkRuta) {
        this.pkRuta = pkRuta;
    }

    public String getGvRuta() {
        return gvRuta;
    }

    public void setGvRuta(String gvRuta) {
        this.gvRuta = gvRuta;
    }

    public String getRutatexto() {
        return rutatexto;
    }

    public void setRutatexto(String rutatexto) {
        this.rutatexto = rutatexto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkRuta != null ? pkRuta.hashCode() : 0);
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
        final MktCobhDimRuta other = (MktCobhDimRuta) obj;
        if ((this.rutatexto == null) ? (other.rutatexto != null) : !this.rutatexto.equals(other.rutatexto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MktCobhDimRuta{" + "pkRuta=" + pkRuta + ", gvRuta=" + gvRuta + ", rutatexto=" + rutatexto + '}';
    }

}
