/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.femsa.mkt.pojos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "MKT_COBH_SCRIPT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MktCobhScript.findAll", query = "SELECT m FROM MktCobhScript m")})
public class MktCobhScript implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "MKT_SEQ_COBH_SCRIPT")
    @SequenceGenerator(name = "MKT_SEQ_COBH_SCRIPT",sequenceName = "MKT_SEQ_COBH_SCRIPT", allocationSize = 1)
    @Column(name = "IDSCRIP")
    private BigDecimal idscrip;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "NOMBRE_ARCHIVO")
    private String nombreArchivo;
    @Column(name = "IDSTATUS")
    private boolean idstatus;
    @Column(name = "RUTA")
    private String ruta;
    @Column(name = "DESC_PROCESO")
    private String descProceso;
    @Lob
    @Column(name = "SCRIPT")
    private String script;
    @Column(name = "ID_TIPO_SCRIPT")
    private BigInteger idTipoScript;

    public MktCobhScript() {
    }

    public MktCobhScript(BigDecimal idscrip) {
        this.idscrip = idscrip;
    }

    public BigDecimal getIdscrip() {
        return idscrip;
    }

    public void setIdscrip(BigDecimal idscrip) {
        this.idscrip = idscrip;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public boolean getIdstatus() {
        return idstatus;
    }

    public void setIdstatus(boolean idstatus) {
        this.idstatus = idstatus;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getDescProceso() {
        return descProceso;
    }

    public void setDescProceso(String descProceso) {
        this.descProceso = descProceso;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public BigInteger getIdTipoScript() {
        return idTipoScript;
    }

    public void setIdTipoScript(BigInteger idTipoScript) {
        this.idTipoScript = idTipoScript;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idscrip != null ? idscrip.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MktCobhScript)) {
            return false;
        }
        MktCobhScript other = (MktCobhScript) object;
        if ((this.idscrip == null && other.idscrip != null) || (this.idscrip != null && !this.idscrip.equals(other.idscrip))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.femsa.mkt.pojos.MktCobhScript[ idscrip=" + idscrip + " ]";
    }
    
    public static String getTextScript(String ruta) throws IOException {
        File scriptFile = new File(ruta);
        FileReader f = null;
        BufferedReader b = null;
        String cadena = null;
        String scriptText = "";
        f = new FileReader(scriptFile);
        b = new BufferedReader(f);
        while ((cadena = b.readLine()) != null) {
            scriptText += cadena.trim() + "\n";
        }
        if (b != null) {
            b.close();
        }
        if (f != null) {
            f.close();
        }
        return scriptText;
    }
    
    public static void saveText(String script,String ruta) throws IOException {
        FileWriter fw = null;
        fw = new FileWriter(ruta);
        fw.write(script);
        fw.close();        
    }
    
}
