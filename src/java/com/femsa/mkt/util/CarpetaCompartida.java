/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.femsa.mkt.util;

import com.femsa.mkt.exception.MKTException;
import com.femsa.mkt.pojos.MktProcesoArchivo;
import java.io.File;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Locale;
import java.util.Properties;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;

/**
 *
 * @author TMXIDSCZAMOR
 */
public class CarpetaCompartida {

    /**
     * @param fileSource
     * @param nombreArchivoDestino
     * @param urlDestino
     * @param user
     * @param password
     * @return Nombre del archivo generado
     * @throws com.femsa.mkt.exception.MKTException
     */
    public static String copyFileToRemoteDir(MktProcesoArchivo fileSource, String nombreArchivoDestino, String urlDestino,
            String user, String password) throws MKTException {        
        String fileDestino = urlDestino + File.separator + Calendar.getInstance().getDisplayName(Calendar.MONTH, 0, Locale.ENGLISH);
        SmbFile sfile;
        NtlmPasswordAuthentication auth;
        SmbFileOutputStream smbFileOutputStream = null;
        FileInputStream fileInputStream = null;
        InputStream inputStream;
        try {
            auth = new NtlmPasswordAuthentication("", user, password);
            sfile = new SmbFile(fileDestino, auth);
            if (!sfile.isDirectory()) {
                sfile.mkdirs();
            }
            fileDestino = fileDestino + File.separator + nombreArchivoDestino;
            sfile = new SmbFile(fileDestino, auth);
            smbFileOutputStream = new SmbFileOutputStream(sfile);
            inputStream = fileSource.getUploadedFile().getInputstream();
            final byte[] buf = new byte[16 * 1024 * 1024];
            int len;
            while ((len = inputStream.read(buf)) > 0) {
                smbFileOutputStream.write(buf, 0, len);
            }
        } catch (MalformedURLException ex) {
            throw new MKTException(ex.getMessage());
        } catch (SmbException ex) {
            throw new MKTException(ex.getMessage());
        } catch (UnknownHostException ex) {
            throw new MKTException(ex.getMessage());
        } catch (FileNotFoundException ex) {
            throw new MKTException(ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new MKTException(ex.getMessage());
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException ex) {
                throw new MKTException(ex.getMessage());
            }
            try {
                if (smbFileOutputStream != null) {
                    smbFileOutputStream.close();
                }
            } catch (IOException ex) {
                throw new MKTException(ex.getMessage());
            }
        }
        return nombreArchivoDestino;
    }

    public static SmbFile[] listFilesOfSource(final String directorio, final String usuario, final String password) throws MKTException {
        SmbFile[] ficheros = null;
        try {
            NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication("", usuario, password);
            final SmbFile sfile = new SmbFile(directorio, auth);
            if (sfile.isDirectory()) {
                ficheros = sfile.listFiles();
            } else {
                throw new MKTException("Ruta no válida, se esperaba un directorio");
            }
        } catch (MalformedURLException ex) {
            throw new MKTException(ex.getMessage());
        } catch (SmbException ex) {
            throw new MKTException(ex.getMessage());
        }
        return ficheros;
    }

    public static String getProperty(String nombrePropiedad, String ruta) throws MKTException {
        String propiedad;
        Properties propiedades = new Properties();
        try {
            //se lee el archivo .properties
            propiedades.load(new FileInputStream(ruta + File.separator + "ArchivoConf.properties"));
            //si el archivo de propiedades NO esta vacio retorna la propiedad leida
            if (!propiedades.isEmpty()) {
                propiedad = propiedades.getProperty(nombrePropiedad);
            } else {
                throw new MKTException("Archivo de propiedades vacío");
            }
        } catch (IOException ex) {
            throw new MKTException(ex.getMessage());
        }
        return propiedad;
    }

}
