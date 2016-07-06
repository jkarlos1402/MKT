/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.femsa.mkt.pojos;

import java.io.Serializable;
import java.util.Comparator;
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
@Table(name = "MKT_COBH_RELARCHIVOTABLA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MktCobhRelarchivotabla.findAll", query = "SELECT m FROM MktCobhRelarchivotabla m")})
public class MktCobhRelarchivotabla implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "PK_IDPROCESODET")
    private Integer pkIdprocesodet;
    @Column(name = "FK_IDPROCESOENC")
    private Integer fkIdprocesoenc;
    @Column(name = "FK_IDPROYECTO")
    private Integer fkIdproyecto;
    @Column(name = "CAMPO_TABLA")
    private String campoTabla;
    @Column(name = "CAMPO_ARCHIVO")
    private String campoArchivo;
    @Column(name = "REQUERIDO")
    private boolean requerido;
    @Column(name = "NOMBRE_TABLA_CATALOGO")
    private String nombreTablaCatalogo;
    @Column(name = "NOMBRE_CAMPO_REGRESO")
    private String nombreCampoRegreso;
    @Column(name = "NOMBRE_CAMPO_CATALOGO")
    private String nombreCampoCatalogo;
    @Column(name = "ORDEN_EVAL")
    private Integer ordenEval;

    public MktCobhRelarchivotabla() {
    }

    public MktCobhRelarchivotabla(Integer pkIdprocesodet) {
        this.pkIdprocesodet = pkIdprocesodet;
    }

    public MktCobhRelarchivotabla(String campoArchivo) {
        this.campoArchivo = campoArchivo;
    }

    public Integer getOrdenEval() {
        return ordenEval;
    }

    public void setOrdenEval(Integer ordenEval) {
        this.ordenEval = ordenEval;
    }

    public Integer getPkIdprocesodet() {
        return pkIdprocesodet;
    }

    public void setPkIdprocesodet(Integer pkIdprocesodet) {
        this.pkIdprocesodet = pkIdprocesodet;
    }

    public Integer getFkIdprocesoenc() {
        return fkIdprocesoenc;
    }

    public void setFkIdprocesoenc(Integer fkIdprocesoenc) {
        this.fkIdprocesoenc = fkIdprocesoenc;
    }

    public Integer getFkIdproyecto() {
        return fkIdproyecto;
    }

    public void setFkIdproyecto(Integer fkIdproyecto) {
        this.fkIdproyecto = fkIdproyecto;
    }

    public String getCampoTabla() {
        return campoTabla;
    }

    public void setCampoTabla(String campoTabla) {
        this.campoTabla = campoTabla;
    }

    public String getCampoArchivo() {
        return campoArchivo;
    }

    public void setCampoArchivo(String campoArchivo) {
        this.campoArchivo = campoArchivo;
    }

    public boolean getRequerido() {
        return requerido;
    }

    public void setRequerido(boolean requerido) {
        this.requerido = requerido;
    }

    public String getNombreTablaCatalogo() {
        return nombreTablaCatalogo;
    }

    public void setNombreTablaCatalogo(String nombreTablaCatalogo) {
        this.nombreTablaCatalogo = nombreTablaCatalogo;
    }

    public String getNombreCampoRegreso() {
        return nombreCampoRegreso;
    }

    public void setNombreCampoRegreso(String nombreCampoRegreso) {
        this.nombreCampoRegreso = nombreCampoRegreso;
    }

    public String getNombreCampoCatalogo() {
        return nombreCampoCatalogo;
    }

    public void setNombreCampoCatalogo(String nombreCampoCatalogo) {
        this.nombreCampoCatalogo = nombreCampoCatalogo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkIdprocesodet != null ? pkIdprocesodet.hashCode() : 0);
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
        final MktCobhRelarchivotabla other = (MktCobhRelarchivotabla) obj;
        if ((this.campoArchivo == null) ? (other.campoArchivo != null) : !this.campoArchivo.equals(other.campoArchivo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.femsa.mkt.pojos.MktCobhRelarchivotabla[ pkIdprocesodet=" + pkIdprocesodet + " ]";
    }

    
}