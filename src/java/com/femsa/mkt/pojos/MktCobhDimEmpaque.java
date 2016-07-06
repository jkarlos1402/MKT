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
@Table(name = "MKT_COBH_DIM_EMPAQUE")
@NamedQueries({
    @NamedQuery(name = "MktCobhDimEmpaque.findAll", query = "SELECT m FROM MktCobhDimEmpaque m")})
public class MktCobhDimEmpaque implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_SEQ_COBH_EMPAQUE")
    @SequenceGenerator(name = "MKT_SEQ_COBH_EMPAQUE", sequenceName = "MKT_SEQ_COBH_EMPAQUE", allocationSize = 1)
    @Column(name = "PK_EMPAQUE")
    private Long pkEmpaque;

    @Column(name = "GV_EMPAQUE")
    private String gvEmpaque;

//    @Column(name = "FK_SEGMENTO")
//    private Long fksegmento;
//    @Column(name = "FK_MARCA")
//    private Long fkmarca;
    @Column(name = "EMPAQUETEXTO")
    private String empaqueTexto;

//RELACIONES DE TABLAS    
//    @JoinColumn(name = "FK_SEGMENTO", referencedColumnName = "PK_SEGMENTO")
//    @ManyToOne(fetch = FetchType.EAGER)    
//    private MktCobhDimSegmento fkseSegmento;
//    public MktCobhDimSegmento getFkseSegmento() {
//        return fkseSegmento;
//    }
//
//    public void setFkseSegmento(MktCobhDimSegmento fkseSegmento) {
//        this.fkseSegmento = fkseSegmento;
//    }
//    public MktCobhDimMarca getFkMarca() {
//        return fkMarca;
//    }
//
//    public void setFkMarca(MktCobhDimMarca fkMarca) {
//        this.fkMarca = fkMarca;
//    }
//
//    @JoinColumn(name = "FK_MARCA", referencedColumnName = "PK_MARCA")
//    @ManyToOne(fetch = FetchType.EAGER)    
//    private MktCobhDimMarca fkMarca;    
//    @OneToMany(mappedBy = "idempaque")
//    private List<MktCobhStHisDatos> mktCobhStHisDatosList;
    public MktCobhDimEmpaque() {
    }

    public MktCobhDimEmpaque(Long pkEmpaque) {
        this.pkEmpaque = pkEmpaque;
    }

    public Long getPkEmpaque() {
        return pkEmpaque;
    }

    public void setPkEmpaque(Long pkEmpaque) {
        this.pkEmpaque = pkEmpaque;
    }

    public String getGvEmpaque() {
        return gvEmpaque;
    }

    public void setGvEmpaque(String gvEmpaque) {
        this.gvEmpaque = gvEmpaque;
    }

    public String getEmpaqueTexto() {
        return empaqueTexto;
    }

    public void setEmpaqueTexto(String empaqueTexto) {
        this.empaqueTexto = empaqueTexto;
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
        hash = 41 * hash + (int) (this.pkEmpaque ^ (this.pkEmpaque >>> 32));
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
        final MktCobhDimEmpaque other = (MktCobhDimEmpaque) obj;
        if ((this.empaqueTexto == null) ? (other.empaqueTexto != null) : !this.empaqueTexto.equals(other.empaqueTexto)) {
            return false;
        }
        return true;
    }

//    @Override
//    public String toString() {
//        return "MktCobhDimEmpaque{" + "pkEmpaque=" + pkEmpaque + ", gvEmpaque=" + gvEmpaque + ", fksegmento=" + fksegmento + ", fkmarca=" + fkmarca + ", empaque=" + empaque +'}';        
//    }    
    @Override
    public String toString() {
        return "MktCobhDimEmpaque{" + "pkEmpaque=" + pkEmpaque + ", gvEmpaque=" + gvEmpaque + ", empaqueTexto=" + empaqueTexto + '}';
    }

}
