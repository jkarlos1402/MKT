/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.femsa.mkt.pojos;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author TMXIDSCPEREZ
 */
@Entity
@Table(name = "MKT_COBH_DIM_SEGMENTO_MKT")
@NamedQueries({
    @NamedQuery(name = "MktCobhDimSegmento.findAll", query = "SELECT m FROM MktCobhDimSegmento m")})
public class MktCobhDimSegmento implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_COBH_SEQ_SEGMENTO_MKT")
    @SequenceGenerator(name = "MKT_COBH_SEQ_SEGMENTO_MKT", sequenceName = "MKT_COBH_SEQ_SEGMENTO_MKT", allocationSize = 1)
    @Column(name = "PK_SEGMENTO")
    private Integer pkSegmento;

    @Column(name = "GV_SEGMENTO")
    private String gvSegmento;
    @Column(name = "SEGMENTOTEXTO")
    private String segmentoTexto;
//    @OneToMany(mappedBy = "fkseSegmento", fetch = FetchType.EAGER)
//    private List<MktCobhDimMarca> marcas;

    public MktCobhDimSegmento() {
    }
   

    public MktCobhDimSegmento(Integer pkSegmento) {
        this.pkSegmento = pkSegmento;
    }

    public Integer getPkSegmento() {
        return pkSegmento;
    }

    public void setPkSegmento(Integer pkSegmento) {
        this.pkSegmento = pkSegmento;
    }

    public String getGvSegmento() {
        return gvSegmento;
    }

    public void setGvSegmento(String gvSegmento) {
        this.gvSegmento = gvSegmento;
    }

    public String getSegmentoTexto() {
        return segmentoTexto;
    }

    public void setSegmentoTexto(String segmentoTexto) {
        this.segmentoTexto = segmentoTexto;
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
        int hash = 3;
        hash = 67 * hash + (this.pkSegmento != null ? this.pkSegmento.hashCode() : 0);
        hash = 67 * hash + (this.gvSegmento != null ? this.gvSegmento.hashCode() : 0);
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
        final MktCobhDimSegmento other = (MktCobhDimSegmento) obj;
        if ((this.segmentoTexto == null) ? (other.segmentoTexto != null) : !this.segmentoTexto.equals(other.segmentoTexto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MktCobhDimSegmento{" + "pkSegmento=" + pkSegmento + ", gvSegmento=" + gvSegmento + ", segmentoTexto=" + segmentoTexto + '}';
    }
    
}
