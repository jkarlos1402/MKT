package com.femsa.mkt.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author TMXIDSJPINAM
 */
@Entity
@Table(name = "MKT_CAT_PAIS")
public class MktAdmin_CatPais implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "MKT_SEQ_CAT_PAIS")
    @SequenceGenerator(name = "MKT_SEQ_CAT_PAIS",sequenceName = "MKT_SEQ_CAT_PAIS", allocationSize = 1)
    @Column(name = "PK_PAIS")
    private Integer pkPais;

    @Column(name = "CLAVE_CORTA")
    private String claveCorta;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "IDSTATUS")
    private boolean idstatus;
    
    @Column(name = "NOMBRE_TABLA")
    private String nombreTabla;     

    /**
     *
     * @return
     */
    public String getNombreTabla() {
        return nombreTabla;
    }

    /**
     *
     * @param nombreTabla
     */
    public void setNombreTabla(String nombreTabla) {
        this.nombreTabla = nombreTabla.toUpperCase();
    }


    /**
     *
     * @return
     */
    public Integer getPkPais() {
        return pkPais;
    }

    /**
     *
     * @param pkPais
     */
    public void setPkPais(Integer pkPais) {
        this.pkPais = pkPais;
    }

    /**
     *
     * @return
     */
    public String getClaveCorta() {
        return claveCorta;
    }

    /**
     *
     * @param claveCorta
     */
    public void setClaveCorta(String claveCorta) {
        this.claveCorta = claveCorta.toUpperCase();
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre.toUpperCase();
    }

    /**
     *
     * @return
     */
    public boolean getIdstatus() {
        return idstatus;
    }

    /**
     *
     * @param idstatus
     */
    public void setIdstatus(boolean idstatus) {
        this.idstatus = idstatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkPais != null ? pkPais.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {        
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MktAdmin_CatPais)) {
            return false;
        }
        MktAdmin_CatPais other = (MktAdmin_CatPais) object;
        if ((this.pkPais == null && other.pkPais != null) || (this.pkPais != null && !this.pkPais.equals(other.pkPais))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
