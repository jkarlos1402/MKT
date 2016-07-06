/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.femsa.mkt.pojos;

import com.femsa.mkt.util.Encabezado;
import com.femsa.mkt.util.EncabezadoIndex;
import com.femsa.mkt.util.Record;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author TMXIDSJPINAM
 */
public class MktProcesoArchivo {

    private List<String> omittedSheets;
    private List<String> loadedSheets;
    private List<String> errors;
    private List<Record> cargas;
    private List<String> nombresClave;
    private String nameFile;
    private long numRegistros;
    private Long numProcesados = 0L;
    private Long numFaltantes = 0L;

    private UploadedFile uploadedFile;
    private InputStream stream;
    private MktAdmin_Usuario usuario;

    private List<EncabezadoIndex> encabezadosExcel; 
    private List<EncabezadoIndex> encabezadosCsv; 
    private List<MktCobhRelarchivotabla> encabezadosTodos; 
    
    public MktProcesoArchivo(MktAdmin_Usuario usuario) {
        omittedSheets = new ArrayList<String>();
        loadedSheets = new ArrayList<String>();
        errors = new ArrayList<String>();
        cargas = new ArrayList<Record>();
        nombresClave = new ArrayList<String>();
        this.usuario = usuario;
    }

    public List<EncabezadoIndex> getEncabezadosCsv() {
        return encabezadosCsv;
    }

    public void setEncabezadosCsv(List<EncabezadoIndex> encabezadosCsv) {
        this.encabezadosCsv = encabezadosCsv;
    }

    public List<MktCobhRelarchivotabla> getEncabezadosTodos() {
        return encabezadosTodos;
    }

    public void setEncabezadosTodos(List<MktCobhRelarchivotabla> encabezadosTodos) {
        this.encabezadosTodos = encabezadosTodos;
    }

    public List<EncabezadoIndex> getEncabezadosExcel() {
        return encabezadosExcel;
    }

    public void setEncabezadosExcel(List<EncabezadoIndex> encabezadosExcel) {
        this.encabezadosExcel = encabezadosExcel;
    }

    public MktAdmin_Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(MktAdmin_Usuario usuario) {
        this.usuario = usuario;
    }

    
    public List<String> getOmittedSheets() {
        return omittedSheets;
    }

    public void setOmittedSheets(List<String> omittedSheets) {
        this.omittedSheets = omittedSheets;
    }

    public List<String> getLoadedSheets() {
        return loadedSheets;
    }

    public void setLoadedSheets(List<String> loadedSheets) {
        this.loadedSheets = loadedSheets;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public List<Record> getCargas() {
        return cargas;
    }

    public void setCargas(List<Record> cargas) {
        this.cargas = cargas;
    }

    public List<String> getNombresClave() {
        return nombresClave;
    }

    public void setNombresClave(List<String> nombresClave) {
        this.nombresClave = nombresClave;
    }

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public long getNumRegistros() {
        return numRegistros;
    }

    public void setNumRegistros(long numRegistros) {
        this.numRegistros = numRegistros;
    }

    public Long getNumProcesados() {
        return numProcesados;
    }

    public void setNumProcesados(Long numProcesados) {
        this.numProcesados = numProcesados;
    }

    public Long getNumFaltantes() {
        return numFaltantes;
    }

    public void setNumFaltantes(Long numFaltantes) {
        this.numFaltantes = numFaltantes;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public InputStream getStream() {
        return stream;
    }

    public void setStream(InputStream stream) {
        this.stream = stream;
    }
    
    
}
