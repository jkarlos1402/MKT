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
@Table(name = "MKT_COBH_DIM_CATEGORIAESTRA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MktCobhDimCategoriaestra.findAll", query = "SELECT m FROM MktCobhDimCategoriaestra m")})
public class MktCobhDimCategoriaestra implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_COBH_SEQ_CATEGORIAESTRA")
    @SequenceGenerator(name = "MKT_COBH_SEQ_CATEGORIAESTRA", sequenceName = "MKT_COBH_SEQ_CATEGORIAESTRA", allocationSize = 1)
    @Column(name = "PK_CATEGORIAESTRA")
    private Integer pkCategoriaestra;
    @Column(name = "GV_CATEGORIAESTRA")
    private String gvCategoriaestra;
    @Column(name = "CATEGORIAESTRATEXTO")
    private String categoriaestratexto;

    public MktCobhDimCategoriaestra() {
    }

    public MktCobhDimCategoriaestra(Integer pkCategoriaestra) {
        this.pkCategoriaestra = pkCategoriaestra;
    }

    public Integer getPkCategoriaestra() {
        return pkCategoriaestra;
    }

    public void setPkCategoriaestra(Integer pkCategoriaestra) {
        this.pkCategoriaestra = pkCategoriaestra;
    }

    public String getGvCategoriaestra() {
        return gvCategoriaestra;
    }

    public void setGvCategoriaestra(String gvCategoriaestra) {
        this.gvCategoriaestra = gvCategoriaestra;
    }

    public String getCategoriaestratexto() {
        return categoriaestratexto;
    }

    public void setCategoriaestratexto(String categoriaestratexto) {
        this.categoriaestratexto = categoriaestratexto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkCategoriaestra != null ? pkCategoriaestra.hashCode() : 0);
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
        final MktCobhDimCategoriaestra other = (MktCobhDimCategoriaestra) obj;
        if ((this.categoriaestratexto == null) ? (other.categoriaestratexto != null) : !this.categoriaestratexto.equals(other.categoriaestratexto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MktCobhDimCategoriaestra{" + "pkCategoriaestra=" + pkCategoriaestra + ", gvCategoriaestra=" + gvCategoriaestra + ", categoriaestratexto=" + categoriaestratexto + '}';
    }

}
