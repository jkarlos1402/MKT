package com.femsa.mkt.pojos;

import com.femsa.mkt.util.EncrypterKOF;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TMXIDSJPINAM
 */
@Entity
@Table(name = "MKT_CAT_USUARIO")
public class MktAdmin_Usuario implements Serializable {    

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_SEQ_USER")
    @SequenceGenerator(name = "MKT_SEQ_USER", sequenceName = "MKT_SEQ_USER", allocationSize = 1)
    @Column(name = "PK_USUARIO")
    private Integer pkUsuario;

    @Basic(optional = false)
    @Column(name = "USUARIO")
    private String usuario;

    @Basic(optional = false)
    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "PAIS")
    private String pais;

    @Column(name = "MAIL")
    private String mail;


    @JoinColumn(name = "FK_ID_ROL")
    @OneToOne(optional = false)
    private MktAdmin_CatRol rol;


    @Column(name = "LASTLOGIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "MKT_USUARIO_PROYECTO", joinColumns = {
        @JoinColumn(name = "ID_USER")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_PROYECTO")})
    private List<MktAdmin_CatProyecto> proyectos = new ArrayList<MktAdmin_CatProyecto>();

    @Column(name = "ESTATUS")
    private boolean estatus;
    
    @Column(name = "INTENTOS")
    private Integer intentos;
    
    @Column(name = "PASSRESET")
    private boolean passreset;
    
    @OneToMany(mappedBy = "fkUsuario")
    private List<MktCobhLogEnc> mktCobhLogEncList;

    /**
     *
     * @return
     */
    public Date getLastLogin() {
        return lastLogin;
    }

    /**
     *
     * @param lastLogin
     */
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    /**
     *
     * @return
     */
    public List<MktAdmin_CatProyecto> getProyectos() {
        return proyectos;
    }

    /**
     *
     * @param proyectos
     */
    public void setProyectos(List<MktAdmin_CatProyecto> proyectos) {
        this.proyectos = proyectos;
    }

    /**
     *
     * @return
     */
    public MktAdmin_CatRol getRol() {
        return rol;
    }

    /**
     *
     * @param rol
     */
    public void setRol(MktAdmin_CatRol rol) {
        this.rol = rol;
    }

    /**
     *
     * @return
     */
    public Integer getPkUsuario() {
        return pkUsuario;
    }

    /**
     *
     * @param pkUsuario
     */
    public void setPkUsuario(Integer pkUsuario) {
        this.pkUsuario = pkUsuario;
    }

    /**
     *
     * @return
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     *
     * @param usuario
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario != null ? usuario.toUpperCase() : usuario;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        EncrypterKOF encrypterKOF = new EncrypterKOF();
        return password != null ? encrypterKOF.decrypt(password) : password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        EncrypterKOF encrypterKOF = new EncrypterKOF();
        this.password = password != null ? encrypterKOF.encrypt(password) : password;
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
        this.nombre = nombre != null ? nombre.toUpperCase() : nombre;
    }

    /**
     *
     * @return
     */
    public String getPais() {
        return pais;
    }

    /**
     *
     * @param pais
     */
    public void setPais(String pais) {
        this.pais = pais != null ? pais.toUpperCase() : pais;
    }

    /**
     *
     * @return
     */
    public String getMail() {
        return mail;
    }

    /**
     *
     * @param mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkUsuario != null ? pkUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MktAdmin_Usuario)) {
            return false;
        }
        MktAdmin_Usuario other = (MktAdmin_Usuario) object;
        if ((this.pkUsuario == null && other.pkUsuario != null) || (this.pkUsuario != null && !this.pkUsuario.equals(other.pkUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }

    /**
     *
     * @param shortNameCountry
     * @return
     */
    public boolean haveCountry(String shortNameCountry) {

        return false;
    }

    public MktAdmin_Usuario() {
    }

    public boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }    

    public Integer getIntentos() {
        return intentos;
    }

    public void setIntentos(Integer intentos) {
        this.intentos = intentos;
    }

    public boolean getPassreset() {
        return passreset;
    }

    public void setPassreset(boolean passreset) {
        this.passreset = passreset;
    }

    @XmlTransient
    public List<MktCobhLogEnc> getMktCobhLogEncList() {
        return mktCobhLogEncList;
    }

    public void setMktCobhLogEncList(List<MktCobhLogEnc> mktCobhLogEncList) {
        this.mktCobhLogEncList = mktCobhLogEncList;
    }
}
