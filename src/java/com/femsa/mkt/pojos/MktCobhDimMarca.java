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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author TMXIDSCPEREZ
 */
@Entity
@Table(name = "MKT_COBH_DIM_MARCA")
@NamedQueries({
    @NamedQuery(name = "MktCobhDimMarca.findAll", query = "SELECT m FROM MktCobhDimMarca m")})
public class MktCobhDimMarca implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_SEQ_COBH_MARCA")
    @SequenceGenerator(name = "MKT_SEQ_COBH_MARCA", sequenceName = "MKT_SEQ_COBH_MARCA", allocationSize = 1)
    @Column(name = "PK_MARCA")
    private Integer pkMarca;

    @Column(name = "GV_MARCA")
    private String gvMarca;

    @Column(name = "MARCATEXTO")
    private String marcaTexto;

//    @OneToMany(mappedBy = "idmarca")
//    private List<MktCobhStHisDatos> mktCobhStHisDatosList;
    public MktCobhDimMarca() {
    }

    public MktCobhDimMarca(Integer pkMarca) {
        this.pkMarca = pkMarca;
    }

    public MktCobhDimMarca(String marcaTexto) {
        this.marcaTexto = marcaTexto;
    }

    public Integer getPkMarca() {
        return pkMarca;
    }

    public void setPkMarca(Integer pkMarca) {
        this.pkMarca = pkMarca;
    }

    public String getGvMarca() {
        return gvMarca;
    }

    public void setGvMarca(String gvMarca) {
        this.gvMarca = gvMarca;
    }

    public String getMarcaTexto() {
        return marcaTexto;
    }

    public void setMarcaTexto(String marcaTexto) {
        this.marcaTexto = marcaTexto;
    }

//    public List<MktCobhStHisDatos> getMktCobhStHisDatosList() {
//        return mktCobhStHisDatosList;
//    }
//
//    public void setMktCobhStHisDatosList(List<MktCobhStHisDatos> mktCobhStHisDatosList) {
//        this.mktCobhStHisDatosList = mktCobhStHisDatosList;
//    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.pkMarca != null ? this.pkMarca.hashCode() : 0);
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
        final MktCobhDimMarca other = (MktCobhDimMarca) obj;
        if ((this.marcaTexto == null) ? (other.marcaTexto != null) : !this.marcaTexto.equals(other.marcaTexto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MktCobhDimMarca{" + "pkMarca=" + pkMarca + ", gvMarca=" + gvMarca + ", marcaTexto=" + marcaTexto + '}';
    }

}
