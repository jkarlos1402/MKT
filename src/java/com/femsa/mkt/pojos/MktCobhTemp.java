/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.femsa.mkt.pojos;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TMXIDSJPINAM
 */
@Entity
@Table(name = "MKT_COBH_TEMP2016")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MktCobhTemp.findAll", query = "SELECT m FROM MktCobhTemp m")})
public class MktCobhTemp implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MKT_SEQ_COBH_TMP")
    @SequenceGenerator(name = "MKT_SEQ_COBH_TMP", sequenceName = "MKT_SEQ_COBH_TMP", allocationSize = 1)
    @Column(name = "ID_COBH_TMP")
    private Integer idCobhTmp;
    @Column(name = "CLAVE")
    private String clave = "S/N";
    @Column(name = "ANIO_MES")
    private String anioMes = "S/N";
    @Column(name = "MARCA")
    private String marca = "S/N";
    @Column(name = "TAMANIO")
    private String tamanio = "S/N";
    @Column(name = "EMPAQUE")
    private String empaque = "S/N";
    @Column(name = "RETORNABLE")
    private String retornable = "S/N";
    @Column(name = "CATEGORIA")
    private String categoria = "S/N";
    @Column(name = "GRUPO_RM1")
    private String grupoRm1 = "S/N";
    @Column(name = "GEC")
    private String gec = "S/N";
    @Column(name = "CLAVE_ESTADO")
    private String claveEstado = "S/N";
    @Column(name = "ESTADO")
    private String estado = "S/N";
    @Column(name = "REGION_CLAVE")
    private String regionClave = "S/N";
    @Column(name = "REGION")
    private String region = "S/N";
    @Column(name = "CLAVE_CENTRO")
    private String claveCentro = "S/N";
    @Column(name = "CENTRO")
    private String centro = "S/N";
    @Column(name = "IDAGRUPADOR_BW")
    private String idagrupadorBw = "S/N";
    @Column(name = "IDAGRUPADOR_MKT")
    private String idagrupadorMkt = "S/N";
    @Column(name = "GRUPO_RM2")
    private String grupoRm2 = "S/N";
    @Column(name = "TIPOCONSUMO")
    private String tipoconsumo = "S/N";
    @Column(name = "SEGCALORICO")
    private String segcalorico = "S/N";
    @Column(name = "CANALRM1")
    private String canalrm1 = "S/N";
    @Column(name = "CANAL")
    private String canal = "S/N";
    @Column(name = "CANALKOF")
    private String canalkof = "S/N";
    @Column(name = "PLANVISITA")
    private String planvisita = "S/N";
    @Column(name = "TERRITORIO")
    private String territorio = "S/N";
    @Column(name = "CLAVE_RUTA")
    private String claveRuta = "S/N";
    @Column(name = "RUTA")
    private String ruta = "S/N";
    @Column(name = "SABOR")
    private String sabor = "S/N";
    @Column(name = "SKU")
    private String sku = "S/N";
    @Column(name = "FAMILIAMARCA")
    private String familiamarca = "S/N";
    @Column(name = "NIVELMERCADEO")
    private String nivelmercadeo = "S/N";
    @Column(name = "SUBCATEGORIA")
    private String subcategoria = "S/N";
    @Column(name = "CATEGORIAESTRA")
    private String categoriaestra = "S/N";
    @Column(name = "GRUPOCANAL")
    private String grupocanal = "S/N";
    @Column(name = "GRUPOCATEGORIA")
    private String grupocategoria = "S/N";
    @Column(name = "CLIENTE")
    private String cliente = "S/N";
    @Column(name = "OCACONSUMO")
    private String ocaconsumo = "S/N";
    @Column(name = "SEGMENTO_MKT")
    private String segmentoMkt = "S/N";
    @Column(name = "GEC_MKT")
    private String gecMkt = "S/N";
    @Column(name = "MARCA_MKT")
    private String marcaMkt = "S/N";
    @Column(name = "TAMANIO_MKT")
    private String tamanioMkt = "S/N";
    @Column(name = "EMPAQUE_MKT")
    private String empaqueMkt = "S/N";
    @Column(name = "CONCEPTO_MKT")
    private String conceptoMkt = "S/N";
    @Column(name = "AGRU1_MKT")
    private String agru1Mkt = "S/N";
    @Column(name = "AGRU2_MKT")
    private String agru2Mkt = "S/N";
    @Column(name = "AGRU3_MKT")
    private String agru3Mkt = "S/N";
    @Column(name = "CLIENTES_ACTIVOS")
    private Integer clientesActivos;
    @Column(name = "CLIENTES_VENTA")
    private Integer clientesVenta;
    @Column(name = "OBJETIVO_ESTADO")
    private Integer objetivoEstado;
    @Column(name = "FK_UO")
    private Integer fkUo;
    @Column(name = "FK_IDAGRUPADOR")
    private Integer fkIdagrupadorMkt;
    @Column(name = "FK_MARCA")
    private Integer fkMarca;
    @Column(name = "FK_TAMANIO")
    private Integer fkTamanio;
    @Column(name = "FK_EMPAQUE")
    private Integer fkEmpaque;
    @Column(name = "FK_RETORNABLE")
    private Integer fkRetornable;
    @Column(name = "FK_ESTADO")
    private Integer fkEstado;
    @Column(name = "FK_REGION")
    private Integer fkRegion;
    @Column(name = "FK_CATEGORIA")
    private Integer fkCategoria;
    @Column(name = "FK_RM1")
    private Integer fkRm1;
    @Column(name = "FK_RM2")
    private Integer fkRm2;
    @Column(name = "FK_GEC")
    private Integer fkGec;
    @Column(name = "FK_TIPOCONSUMO")
    private Integer fkTipoconsumo;
    @Column(name = "FK_SEGCALORICO")
    private Integer fkSegcalorico;
    @Column(name = "FK_CANALRM1")
    private Integer fkCanalrm1;
    @Column(name = "FK_CANAL")
    private Integer fkCanal;
    @Column(name = "FK_CANALKOF")
    private Integer fkCanalkof;
    @Column(name = "FK_PLANVISITA")
    private Integer fkPlanvisita;
    @Column(name = "FK_TERRITORIO")
    private Integer fkTerritorio;
    @Column(name = "FK_RUTA")
    private Integer fkRuta;
    @Column(name = "FK_SABOR")
    private Integer fkSabor;
    @Column(name = "FK_SKU")
    private Integer fkSku;
    @Column(name = "FK_CLIENTE")
    private Integer fkCliente;
    @Column(name = "FK_OCACONSUMO")
    private Integer fkOcaconsumo;
    @Column(name = "FK_FAMILIAMARCA")
    private Integer fkFamiliamarca;
    @Column(name = "FK_NIVELMERCADEO")
    private Integer fkNivelmercadeo;
    @Column(name = "FK_SUBCATEGORIA")
    private Integer fkSubcategoria;
    @Column(name = "FK_CATEGORIAESTRA")
    private Integer fkCategoriaestra;
    @Column(name = "FK_GRUPOCANAL")
    private Integer fkGrupocanal;
    @Column(name = "FK_GRUPOCATEGORIA")
    private Integer fkGrupocategoria;
    @Column(name = "FK_SEGMENTO_MKT")
    private Integer fkSegmentoMkt;
    @Column(name = "FK_GEC_MKT")
    private Integer fkGecMkt;
    @Column(name = "FK_MARCA_MKT")
    private Integer fkMarcaMkt;
    @Column(name = "FK_TAMANIO_MKT")
    private Integer fkTamanioMkt;
    @Column(name = "FK_EMPAQUE_MKT")
    private Integer fkEmpaqueMkt;
    @Column(name = "FK_CONCEPTO_MKT")
    private Integer fkConceptoMkt;
    @Column(name = "FK_AGRU1_MKT")
    private Integer fkAgru1Mkt;
    @Column(name = "FK_AGRU2_MKT")
    private Integer fkAgru2Mkt;
    @Column(name = "FK_AGRU3_MKT")
    private Integer fkAgru3Mkt;
    @Column(name = "OBJETIVO_CENTRO")
    private Integer objetivoCentro;
    @Column(name = "OBJETIVO_REGION")
    private Integer objetivoRegion;
    @Column(name = "OBJETIVO_RUTA")
    private Integer objetivoRuta;
    @Column(name = "FECHA_CARGA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCarga;
    @Column(name = "FK_IDREGISTRODET")
    private Integer fkidregistrodet;

    public MktCobhTemp() {
    }

    public MktCobhTemp(Integer idCobhTmp) {
        this.idCobhTmp = idCobhTmp;
    }

    public Date getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(Date fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public Integer getFkidregistrodet() {
        return fkidregistrodet;
    }

    public void setFkidregistrodet(Integer fkidregistrodet) {
        this.fkidregistrodet = fkidregistrodet;
    }

    public Integer getIdCobhTmp() {
        return idCobhTmp;
    }

    public void setIdCobhTmp(Integer idCobhTmp) {
        this.idCobhTmp = idCobhTmp;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getAnioMes() {
        return anioMes;
    }

    public void setAnioMes(String anioMes) {
        this.anioMes = anioMes;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }

    public String getEmpaque() {
        return empaque;
    }

    public void setEmpaque(String empaque) {
        this.empaque = empaque;
    }

    public String getRetornable() {
        return retornable;
    }

    public void setRetornable(String retornable) {
        this.retornable = retornable;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getGrupoRm1() {
        return grupoRm1;
    }

    public void setGrupoRm1(String grupoRm1) {
        this.grupoRm1 = grupoRm1;
    }

    public String getGec() {
        return gec;
    }

    public void setGec(String gec) {
        this.gec = gec;
    }

    public String getClaveEstado() {
        return claveEstado;
    }

    public void setClaveEstado(String claveEstado) {
        this.claveEstado = claveEstado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRegionClave() {
        return regionClave;
    }

    public void setRegionClave(String regionClave) {
        this.regionClave = regionClave;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getClaveCentro() {
        return claveCentro;
    }

    public void setClaveCentro(String claveCentro) {
        this.claveCentro = claveCentro;
    }

    public String getCentro() {
        return centro;
    }

    public void setCentro(String centro) {
        this.centro = centro;
    }

    public String getIdagrupadorBw() {
        return idagrupadorBw;
    }

    public void setIdagrupadorBw(String idagrupadorBw) {
        this.idagrupadorBw = idagrupadorBw;
    }

    public String getIdagrupadorMkt() {
        return idagrupadorMkt;
    }

    public void setIdagrupadorMkt(String idagrupadorMkt) {
        this.idagrupadorMkt = idagrupadorMkt;
    }

    public String getGrupoRm2() {
        return grupoRm2;
    }

    public void setGrupoRm2(String grupoRm2) {
        this.grupoRm2 = grupoRm2;
    }

    public String getTipoconsumo() {
        return tipoconsumo;
    }

    public void setTipoconsumo(String tipoconsumo) {
        this.tipoconsumo = tipoconsumo;
    }

    public String getSegcalorico() {
        return segcalorico;
    }

    public void setSegcalorico(String segcalorico) {
        this.segcalorico = segcalorico;
    }

    public String getCanalrm1() {
        return canalrm1;
    }

    public void setCanalrm1(String canalrm1) {
        this.canalrm1 = canalrm1;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public String getCanalkof() {
        return canalkof;
    }

    public void setCanalkof(String canalkof) {
        this.canalkof = canalkof;
    }

    public String getPlanvisita() {
        return planvisita;
    }

    public void setPlanvisita(String planvisita) {
        this.planvisita = planvisita;
    }

    public String getTerritorio() {
        return territorio;
    }

    public void setTerritorio(String territorio) {
        this.territorio = territorio;
    }

    public String getClaveRuta() {
        return claveRuta;
    }

    public void setClaveRuta(String claveRuta) {
        this.claveRuta = claveRuta;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getSabor() {
        return sabor;
    }

    public void setSabor(String sabor) {
        this.sabor = sabor;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getFamiliamarca() {
        return familiamarca;
    }

    public void setFamiliamarca(String familiamarca) {
        this.familiamarca = familiamarca;
    }

    public String getNivelmercadeo() {
        return nivelmercadeo;
    }

    public void setNivelmercadeo(String nivelmercadeo) {
        this.nivelmercadeo = nivelmercadeo;
    }

    public String getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
    }

    public String getCategoriaestra() {
        return categoriaestra;
    }

    public void setCategoriaestra(String categoriaestra) {
        this.categoriaestra = categoriaestra;
    }

    public String getGrupocanal() {
        return grupocanal;
    }

    public void setGrupocanal(String grupocanal) {
        this.grupocanal = grupocanal;
    }

    public String getGrupocategoria() {
        return grupocategoria;
    }

    public void setGrupocategoria(String grupocategoria) {
        this.grupocategoria = grupocategoria;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getOcaconsumo() {
        return ocaconsumo;
    }

    public void setOcaconsumo(String ocaconsumo) {
        this.ocaconsumo = ocaconsumo;
    }

    public String getSegmentoMkt() {
        return segmentoMkt;
    }

    public void setSegmentoMkt(String segmentoMkt) {
        this.segmentoMkt = segmentoMkt;
    }

    public String getGecMkt() {
        return gecMkt;
    }

    public void setGecMkt(String gecMkt) {
        this.gecMkt = gecMkt;
    }

    public String getMarcaMkt() {
        return marcaMkt;
    }

    public void setMarcaMkt(String marcaMkt) {
        this.marcaMkt = marcaMkt;
    }

    public String getTamanioMkt() {
        return tamanioMkt;
    }

    public void setTamanioMkt(String tamanioMkt) {
        this.tamanioMkt = tamanioMkt;
    }

    public String getEmpaqueMkt() {
        return empaqueMkt;
    }

    public void setEmpaqueMkt(String empaqueMkt) {
        this.empaqueMkt = empaqueMkt;
    }

    public String getConceptoMkt() {
        return conceptoMkt;
    }

    public void setConceptoMkt(String conceptoMkt) {
        this.conceptoMkt = conceptoMkt;
    }

    public String getAgru1Mkt() {
        return agru1Mkt;
    }

    public void setAgru1Mkt(String agru1Mkt) {
        this.agru1Mkt = agru1Mkt;
    }

    public String getAgru2Mkt() {
        return agru2Mkt;
    }

    public void setAgru2Mkt(String agru2Mkt) {
        this.agru2Mkt = agru2Mkt;
    }

    public String getAgru3Mkt() {
        return agru3Mkt;
    }

    public void setAgru3Mkt(String agru3Mkt) {
        this.agru3Mkt = agru3Mkt;
    }

    public Integer getClientesActivos() {
        return clientesActivos;
    }

    public void setClientesActivos(Integer clientesActivos) {
        this.clientesActivos = clientesActivos;
    }

    public Integer getClientesVenta() {
        return clientesVenta;
    }

    public void setClientesVenta(Integer clientesVenta) {
        this.clientesVenta = clientesVenta;
    }

    public Integer getObjetivoEstado() {
        return objetivoEstado;
    }

    public void setObjetivoEstado(Integer objetivoEstado) {
        this.objetivoEstado = objetivoEstado;
    }

    public Integer getFkUo() {
        return fkUo;
    }

    public void setFkUo(Integer fkUo) {
        this.fkUo = fkUo;
    }

    public Integer getFkIdagrupadorMkt() {
        return fkIdagrupadorMkt;
    }

    public void setFkIdagrupadorMkt(Integer fkIdagrupadorMkt) {
        this.fkIdagrupadorMkt = fkIdagrupadorMkt;
    }

    public Integer getFkMarca() {
        return fkMarca;
    }

    public void setFkMarca(Integer fkMarca) {
        this.fkMarca = fkMarca;
    }

    public Integer getFkTamanio() {
        return fkTamanio;
    }

    public void setFkTamanio(Integer fkTamanio) {
        this.fkTamanio = fkTamanio;
    }

    public Integer getFkEmpaque() {
        return fkEmpaque;
    }

    public void setFkEmpaque(Integer fkEmpaque) {
        this.fkEmpaque = fkEmpaque;
    }

    public Integer getFkRetornable() {
        return fkRetornable;
    }

    public void setFkRetornable(Integer fkRetornable) {
        this.fkRetornable = fkRetornable;
    }

    public Integer getFkEstado() {
        return fkEstado;
    }

    public void setFkEstado(Integer fkEstado) {
        this.fkEstado = fkEstado;
    }

    public Integer getFkRegion() {
        return fkRegion;
    }

    public void setFkRegion(Integer fkRegion) {
        this.fkRegion = fkRegion;
    }

    public Integer getFkCategoria() {
        return fkCategoria;
    }

    public void setFkCategoria(Integer fkCategoria) {
        this.fkCategoria = fkCategoria;
    }

    public Integer getFkRm1() {
        return fkRm1;
    }

    public void setFkRm1(Integer fkRm1) {
        this.fkRm1 = fkRm1;
    }

    public Integer getFkRm2() {
        return fkRm2;
    }

    public void setFkRm2(Integer fkRm2) {
        this.fkRm2 = fkRm2;
    }

    public Integer getFkGec() {
        return fkGec;
    }

    public void setFkGec(Integer fkGec) {
        this.fkGec = fkGec;
    }

    public Integer getFkTipoconsumo() {
        return fkTipoconsumo;
    }

    public void setFkTipoconsumo(Integer fkTipoconsumo) {
        this.fkTipoconsumo = fkTipoconsumo;
    }

    public Integer getFkSegcalorico() {
        return fkSegcalorico;
    }

    public void setFkSegcalorico(Integer fkSegcalorico) {
        this.fkSegcalorico = fkSegcalorico;
    }

    public Integer getFkCanalrm1() {
        return fkCanalrm1;
    }

    public void setFkCanalrm1(Integer fkCanalrm1) {
        this.fkCanalrm1 = fkCanalrm1;
    }

    public Integer getFkCanal() {
        return fkCanal;
    }

    public void setFkCanal(Integer fkCanal) {
        this.fkCanal = fkCanal;
    }

    public Integer getFkCanalkof() {
        return fkCanalkof;
    }

    public void setFkCanalkof(Integer fkCanalkof) {
        this.fkCanalkof = fkCanalkof;
    }

    public Integer getFkPlanvisita() {
        return fkPlanvisita;
    }

    public void setFkPlanvisita(Integer fkPlanvisita) {
        this.fkPlanvisita = fkPlanvisita;
    }

    public Integer getFkTerritorio() {
        return fkTerritorio;
    }

    public void setFkTerritorio(Integer fkTerritorio) {
        this.fkTerritorio = fkTerritorio;
    }

    public Integer getFkRuta() {
        return fkRuta;
    }

    public void setFkRuta(Integer fkRuta) {
        this.fkRuta = fkRuta;
    }

    public Integer getFkSabor() {
        return fkSabor;
    }

    public void setFkSabor(Integer fkSabor) {
        this.fkSabor = fkSabor;
    }

    public Integer getFkSku() {
        return fkSku;
    }

    public void setFkSku(Integer fkSku) {
        this.fkSku = fkSku;
    }

    public Integer getFkCliente() {
        return fkCliente;
    }

    public void setFkCliente(Integer fkCliente) {
        this.fkCliente = fkCliente;
    }

    public Integer getFkOcaconsumo() {
        return fkOcaconsumo;
    }

    public void setFkOcaconsumo(Integer fkOcaconsumo) {
        this.fkOcaconsumo = fkOcaconsumo;
    }

    public Integer getFkFamiliamarca() {
        return fkFamiliamarca;
    }

    public void setFkFamiliamarca(Integer fkFamiliamarca) {
        this.fkFamiliamarca = fkFamiliamarca;
    }

    public Integer getFkNivelmercadeo() {
        return fkNivelmercadeo;
    }

    public void setFkNivelmercadeo(Integer fkNivelmercadeo) {
        this.fkNivelmercadeo = fkNivelmercadeo;
    }

    public Integer getFkSubcategoria() {
        return fkSubcategoria;
    }

    public void setFkSubcategoria(Integer fkSubcategoria) {
        this.fkSubcategoria = fkSubcategoria;
    }

    public Integer getFkCategoriaestra() {
        return fkCategoriaestra;
    }

    public void setFkCategoriaestra(Integer fkCategoriaestra) {
        this.fkCategoriaestra = fkCategoriaestra;
    }

    public Integer getFkGrupocanal() {
        return fkGrupocanal;
    }

    public void setFkGrupocanal(Integer fkGrupocanal) {
        this.fkGrupocanal = fkGrupocanal;
    }

    public Integer getFkGrupocategoria() {
        return fkGrupocategoria;
    }

    public void setFkGrupocategoria(Integer fkGrupocategoria) {
        this.fkGrupocategoria = fkGrupocategoria;
    }

    public Integer getFkSegmentoMkt() {
        return fkSegmentoMkt;
    }

    public void setFkSegmentoMkt(Integer fkSegmentoMkt) {
        this.fkSegmentoMkt = fkSegmentoMkt;
    }

    public Integer getFkGecMkt() {
        return fkGecMkt;
    }

    public void setFkGecMkt(Integer fkGecMkt) {
        this.fkGecMkt = fkGecMkt;
    }

    public Integer getFkMarcaMkt() {
        return fkMarcaMkt;
    }

    public void setFkMarcaMkt(Integer fkMarcaMkt) {
        this.fkMarcaMkt = fkMarcaMkt;
    }

    public Integer getFkTamanioMkt() {
        return fkTamanioMkt;
    }

    public void setFkTamanioMkt(Integer fkTamanioMkt) {
        this.fkTamanioMkt = fkTamanioMkt;
    }

    public Integer getFkEmpaqueMkt() {
        return fkEmpaqueMkt;
    }

    public void setFkEmpaqueMkt(Integer fkEmpaqueMkt) {
        this.fkEmpaqueMkt = fkEmpaqueMkt;
    }

    public Integer getFkConceptoMkt() {
        return fkConceptoMkt;
    }

    public void setFkConceptoMkt(Integer fkConceptoMkt) {
        this.fkConceptoMkt = fkConceptoMkt;
    }

    public Integer getFkAgru1Mkt() {
        return fkAgru1Mkt;
    }

    public void setFkAgru1Mkt(Integer fkAgru1Mkt) {
        this.fkAgru1Mkt = fkAgru1Mkt;
    }

    public Integer getFkAgru2Mkt() {
        return fkAgru2Mkt;
    }

    public void setFkAgru2Mkt(Integer fkAgru2Mkt) {
        this.fkAgru2Mkt = fkAgru2Mkt;
    }

    public Integer getFkAgru3Mkt() {
        return fkAgru3Mkt;
    }

    public void setFkAgru3Mkt(Integer fkAgru3Mkt) {
        this.fkAgru3Mkt = fkAgru3Mkt;
    }

    public Integer getObjetivoCentro() {
        return objetivoCentro;
    }

    public void setObjetivoCentro(Integer objetivoCentro) {
        this.objetivoCentro = objetivoCentro;
    }

    public Integer getObjetivoRegion() {
        return objetivoRegion;
    }

    public void setObjetivoRegion(Integer objetivoRegion) {
        this.objetivoRegion = objetivoRegion;
    }

    public Integer getObjetivoRuta() {
        return objetivoRuta;
    }

    public void setObjetivoRuta(Integer objetivoRuta) {
        this.objetivoRuta = objetivoRuta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCobhTmp != null ? idCobhTmp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MktCobhTemp)) {
            return false;
        }
        MktCobhTemp other = (MktCobhTemp) object;
        if ((this.idCobhTmp == null && other.idCobhTmp != null) || (this.idCobhTmp != null && !this.idCobhTmp.equals(other.idCobhTmp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.femsa.mkt.pojos.MktCobhTemp2016[ idCobhTmp=" + idCobhTmp + " ]";
    }

}
