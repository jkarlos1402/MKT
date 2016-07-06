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
@Table(name = "MKT_TMP_PS_RUTA_CANAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MktTmpPsRutaCanal.findAll", query = "SELECT m FROM MktTmpPsRutaCanal m")})
public class MktTmpPsRutaCanal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_SEQ_PS_RUTA_CANAL")
    @SequenceGenerator(name = "MKT_SEQ_PS_RUTA_CANAL", sequenceName = "MKT_SEQ_PS_RUTA_CANAL", allocationSize = 1)
    @Column(name = "ID_PS_RUTA_CANAL")
    private Integer idPsRutaCanal;
    @Column(name = "RUTA_REPARTO")
    private String rutaReparto;
    @Column(name = "CANAL_MKT")
    private String canalMkt;

    public MktTmpPsRutaCanal() {
    }

    public MktTmpPsRutaCanal(Integer idPsRutaCanal) {
        this.idPsRutaCanal = idPsRutaCanal;
    }

    public String getRutaReparto() {
        return rutaReparto;
    }

    public void setRutaReparto(String rutaReparto) {
        this.rutaReparto = rutaReparto;
    }

    public String getCanalMkt() {
        return canalMkt;
    }

    public void setCanalMkt(String canalMkt) {
        this.canalMkt = canalMkt;
    }

    public Integer getIdPsRutaCanal() {
        return idPsRutaCanal;
    }

    public void setIdPsRutaCanal(Integer idPsRutaCanal) {
        this.idPsRutaCanal = idPsRutaCanal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPsRutaCanal != null ? idPsRutaCanal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MktTmpPsRutaCanal)) {
            return false;
        }
        MktTmpPsRutaCanal other = (MktTmpPsRutaCanal) object;
        if ((this.idPsRutaCanal == null && other.idPsRutaCanal != null) || (this.idPsRutaCanal != null && !this.idPsRutaCanal.equals(other.idPsRutaCanal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.femsa.mkt.pojos.MktTmpPsRutaCanal[ idPsRutaCanal=" + idPsRutaCanal + " ]";
    }

}
