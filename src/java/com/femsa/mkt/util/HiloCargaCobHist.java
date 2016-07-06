/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.femsa.mkt.util;

import com.femsa.mkt.analizer.XlsAnalizerDatos;
import com.femsa.mkt.dao.GenericDAO;
import com.femsa.mkt.exception.DAOException;
import com.femsa.mkt.exception.MKTException;
import com.femsa.mkt.pojos.MktCobhLogDet;
import com.femsa.mkt.pojos.MktCobhLogEnc;
import com.femsa.mkt.pojos.MktCobhLogStatus;
import com.femsa.mkt.pojos.MktProcesoArchivo;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TMXIDSJPINAM
 */
public class HiloCargaCobHist extends Thread {

    private MktProcesoArchivo archivo;
    private MktCobhLogEnc logCarga;
    private boolean error;
    private GenericDAO genericDAO;
    private List<MktCobhLogStatus> catStatus;
    private String rutaPropiedades;

    public HiloCargaCobHist(MktProcesoArchivo archivo, MktCobhLogEnc logCarga, GenericDAO genericDAO, List<MktCobhLogStatus> catStatus, String rutaPropiedades) {
        this.archivo = archivo;
        this.logCarga = logCarga;
        this.genericDAO = genericDAO;
        this.catStatus = catStatus;
        this.rutaPropiedades = rutaPropiedades;
    }

    @Override
    public void run() {
        MktCobhLogDet logDetalle = new MktCobhLogDet();
        SimpleDateFormat formatoArchivo = new SimpleDateFormat("ddMMyyyyHHmmss");
        try {
            logDetalle.setInicio(new Date());
            logDetalle.setNomarchivoOrigen(archivo.getNameFile());
            logDetalle.setTotalregistros(archivo.getNumRegistros());
            logDetalle.setFkIdstatus(catStatus.get(catStatus.indexOf(new MktCobhLogStatus(0))));
            synchronized (logCarga) {
                logDetalle.setFkIdregistro(logCarga);
                logCarga.getMktCobhLogDetList().add(logDetalle);
                genericDAO.saveOrUpdate(logCarga);
            }
            XlsAnalizerDatos excelDatos = new XlsAnalizerDatos();
            excelDatos.setEncabezadosExcel(archivo.getEncabezadosExcel());
            excelDatos.setEncabezadosTodos(archivo.getEncabezadosTodos());
            System.out.println("entrara a guardar");
            if (archivo.getEncabezadosExcel() == null) {
                excelDatos.saveSheetInfoDatos(archivo.getUploadedFile(), archivo.getStream(), archivo.getUsuario(), archivo.getNombresClave(), logDetalle.getPkIdregistrodet());
            } else {
                excelDatos.saveSheetInfoDatosDinamico(archivo.getUploadedFile(), archivo.getStream(), archivo.getUsuario(), archivo.getNombresClave(), logDetalle.getPkIdregistrodet());
            }
            System.out.println("paso de guardar");
            logDetalle.setFkIdstatus(catStatus.get(catStatus.indexOf(new MktCobhLogStatus(1))));
            logDetalle.setFinal1(new Date());
            int tiempo = ((int) (logDetalle.getFinal1().getTime() - logDetalle.getInicio().getTime())) / 1000;
            logDetalle.setTiempo(tiempo);
//            logDetalle.setNomarchivoBak(CarpetaCompartida.copyFileToRemoteDir(archivo, logCarga.getPkIdregistro() + "_" + logDetalle.getPkIdregistrodet() + "_CARGA_HIST_" + formatoArchivo.format(logDetalle.getFinal1()) + ".xlsx", CarpetaCompartida.getProperty("rutaDestino", rutaPropiedades), CarpetaCompartida.getProperty("usuario", rutaPropiedades), CarpetaCompartida.getProperty("password", rutaPropiedades)));
            synchronized (logCarga) {
                genericDAO.saveOrUpdate(logCarga);
            }
        } catch (MKTException ex) {
            logDetalle.setFkIdstatus(catStatus.get(catStatus.indexOf(new MktCobhLogStatus(2))));
            logDetalle.setFinal1(new Date());
            logDetalle.setDescripcionMensaje(ex.getMessage());
            synchronized (logCarga) {
                logCarga.setTotalerror(logCarga.getTotalerror() + archivo.getNumRegistros());
            }
            error = true;       
        } catch (IOException ex) {
            logDetalle.setFkIdstatus(catStatus.get(catStatus.indexOf(new MktCobhLogStatus(2))));
            logDetalle.setFinal1(new Date());
            logDetalle.setDescripcionMensaje(ex.getMessage());
            synchronized (logCarga) {
                logCarga.setTotalerror(logCarga.getTotalerror() + archivo.getNumRegistros());
            }
            error = true;            
        } catch (DAOException ex) {
            logDetalle.setFkIdstatus(catStatus.get(catStatus.indexOf(new MktCobhLogStatus(2))));
            logDetalle.setFinal1(new Date());
            logDetalle.setDescripcionMensaje(ex.getMessage());
            synchronized (logCarga) {
                logCarga.setTotalerror(logCarga.getTotalerror() + archivo.getNumRegistros());
            }
            error = true;            
        } catch (Exception ex) {
            logDetalle.setFkIdstatus(catStatus.get(catStatus.indexOf(new MktCobhLogStatus(2))));
            logDetalle.setFinal1(new Date());
            logDetalle.setDescripcionMensaje(ex.getMessage());
            synchronized (logCarga) {
                logCarga.setTotalerror(logCarga.getTotalerror() + archivo.getNumRegistros());
            }
            error = true;            
        }
    }

    public MktProcesoArchivo getArchivo() {
        return archivo;
    }

    public void setArchivo(MktProcesoArchivo archivo) {
        this.archivo = archivo;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

}
