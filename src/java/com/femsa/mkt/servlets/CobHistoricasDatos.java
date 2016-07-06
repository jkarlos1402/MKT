/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.femsa.mkt.servlets;

import com.femsa.mkt.analizer.XlsAnalizer;
import com.femsa.mkt.dao.CobHistoricoDao;
import com.femsa.mkt.pojos.MktCobStHisAgrupacion;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.FileItemFactory;
//import org.apache.commons.fileupload.FileUploadException;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author TMXIDSCPEREZ
 */
@WebServlet(name = "CobHistoricasDatos", urlPatterns = {"/CobHistoricasDatos.do"})
public class CobHistoricasDatos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         PrintWriter out = response.getWriter();
//        XlsAnalizer analizer = new XlsAnalizer();
//        try {
//
//            /*FileItemFactory es una interfaz para crear FileItem*/
//            FileItemFactory file_factory = new DiskFileItemFactory();
//
//            /*ServletFileUpload esta clase convierte los input file a FileItem*/
//            ServletFileUpload servlet_up = new ServletFileUpload(file_factory);
//
//            /*sacando los FileItem del ServletFileUpload en una lista */
//            List items = servlet_up.parseRequest(request);
//
//            for (int i = 0; i < items.size(); i++) {
//                /*FileItem representa un archivo en memoria que puede ser pasado al disco duro*/
//                FileItem item = (FileItem) items.get(i);
//                String n[] = item.getName().replace("\\", "-").split("-");
//                String nombreReal = n[n.length - 1]; //nombre real del archivo para guardar
//
//                /*item.isFormField() false=input file; true=text field*/
//                if (!item.isFormField()) {
//                    /*cual sera la ruta al archivo en el servidor*/
//                    File archivo_server1 = new File("c:\\Pruebas\\" + nombreReal);
//                    /*y lo escribimos en el servidor*/
////                    if (!nombreReal.equals(femsadt)) {
//                    item.write(archivo_server1);
//                    FileInputStream inputStream = new FileInputStream(archivo_server1);
//                    List<MktCobStHisAgrupacion> lista = analizer.analizeXls(inputStream, archivo_server1.getName());
//                    if (lista != null) {
//                        System.out.println("tamaño:" + lista.size());
//                        CobHistoricoDao historicoDao = new CobHistoricoDao();
//                        if(historicoDao.boorrarTabla()){
//                            if(historicoDao.guardarLista(lista)){
//                                System.out.println("se han guardado los registros");
//                            }else{
//                                System.out.println("error al guardar los registros");
//                            }
//                        }else{
//                            System.out.println("error al eliminar tabla");
//                        } 
//                        
//                    }else{
//                        System.out.println(analizer.getErrors());
//                    }
//                    //Runtime aplicacion = Runtime.getRuntime();
//                    try {
//                        //aplicacion.exec("cmd.exe /C S:/csv/MKT/file_copy_Descuentos_Presupuesto.bat");
//                    } catch (Exception e) {
//                        System.out.println(e);
//                    }
////                    } 
//                }
//            }
//        } catch (FileUploadException ex) {
//            Logger.getLogger(CobHistoricas.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (Exception ex) {
//            Logger.getLogger(CobHistoricas.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        out.print("termino");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
