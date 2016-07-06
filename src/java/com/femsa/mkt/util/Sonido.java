/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.femsa.mkt.util;

import java.io.File;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


/**
 *
 * @author TMXIDSCPEREZ
 */

public class Sonido {

    /**
     * Abre un fichero de sonido wav y lo reproduce
     * @param args
     */
    public static void ejecuta() {
        try {
            
            // Se obtiene un Clip de sonido
            Clip sonido = AudioSystem.getClip();
            
            // Se carga con un fichero wav
            System.out.println("ejecuta sonido");                        
            ServletContext sc = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String contextPathResources = sc.getRealPath("");
        File directorioBase = new File(contextPathResources + File.separator + "fin.wav");
            sonido.open(AudioSystem.getAudioInputStream(directorioBase));
//            File fichero = new File("text.txt");
//            System.out.println("La ruta del fichero es: " + fichero.getAbsolutePath());    

            // Comienza la reproducción
            sonido.start();
            
            // Espera mientras se esté reproduciendo.
            while (sonido.isRunning())
                Thread.sleep(1000);
            
            // Se cierra el clip.
            sonido.close();
        } catch (Exception e) {
            System.out.println("" + e);
        }
    }

}

