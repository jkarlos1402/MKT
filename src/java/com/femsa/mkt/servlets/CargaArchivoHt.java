package com.femsa.mkt.servlets;
import java.util.*;
//import org.apache.commons.fileupload.disk.*;
//import org.apache.commons.fileupload.servlet.*;
//import org.apache.commons.fileupload.*;
//import org.apache.commons.io.*;
//import java.io.*;
//import java.util.List;

public class CargaArchivoHt {

public boolean validaArchivo(String nombreArchivo){
//        String femsadt = "cobHistorica.xls";
//        String NombreArchivofinal = "cobHistorica.xls";
//
//	/*FileItemFactory es una interfaz para crear FileItem*/
//        FileItemFactory file_factory = new DiskFileItemFactory();
//
//	/*ServletFileUpload esta clase convierte los input file a FileItem*/
//        ServletFileUpload servlet_up = new ServletFileUpload(file_factory);
//	
//	/*sacando los FileItem del ServletFileUpload en una lista */
//        List items = servlet_up.parseRequest(request);
//
//        for(int i=0;i<items.size();i++){
//	    /*FileItem representa un archivo en memoria que puede ser pasado al disco duro*/
//            FileItem item = (FileItem) items.get(0);
// 	    String n[] =item.getName().replace("\\","-").split("-");
//	    String nombreReal=n[n.length-1]; //nombre real del archivo para guardar
//
//	    /*item.isFormField() false=input file; true=text field*/
//            if (! item.isFormField()){
//		/*cual sera la ruta al archivo en el servidor*/
//                File archivo_server1 = new File("S:\\csv\\MKT\\csv_Descuentos\\Presupuesto\\"+NombreArchivofinal);					
//		/*y lo escribimos en el servidor*/
//		if (!nombreReal.equals(femsadt))
//		{
//			item.write(archivo_server1);
//			Runtime aplicacion = Runtime.getRuntime(); 
//       			try{
//				aplicacion.exec("cmd.exe /C S:/csv/MKT/file_copy_Descuentos_Presupuesto.bat"); 
//			}
//       			catch(Exception e){System.out.println(e);
//			}
//		}
//		else
//			item.write(archivo_server2);				
//            }
//        }  
        return true;
}    

    public boolean cargaArchivoBd(String Archivo){                
        String NombreTabla = "MKT_COB_ST_HIS_AGRUPACION";                       
    return true;
    }
    public boolean EjecutaEtl(){       
        String NombreArchivo = "MKT_HISTORICAS_ETL.sql";
        String RutaArchivo = "S:\\csv\\MKT\\csv_Descuentos\\Presupuesto\\";  
        
        /*Proceso de Ejecucion del Etl*/
        
        return true;        
    }
    public boolean ProcesoInicial(String NombreScrip){
        String sql = "INSERT INTO TBLLOGREGIST " ;        
        sql += " VALUES (seq_TBLLOGREGIST.NEXTVAL,(SELECT ID_SCRIPT FROM TBLLOGSCRIPT WHERE DESC_SCRIPT='" + NombreScrip + "'),current_date,null,null,null,0,0,0,0);" ;
        sql += " COMMIT;";
        sql += " INSERT INTO TBLLOGREGEST SELECT MAX(ID_REGISTRO), 1 FROM TBLLOGREGIST WHERE ID_SCRIPT=(SELECT ID_SCRIPT FROM TBLLOGSCRIPT WHERE DESC_SCRIPT='" + NombreScrip + "'); " ;
        sql += " COMMIT;";        
        /* funcion para Ejecutar */        
        return true;        

    }
    public boolean ProcesoFinal(String NombreScrip){
        String sql = "/****** REGISTRO DE CONTROL ******/";
        sql += " UPDATE TBLLOGREGIST Set CANTIDAD_REG=(Select COUNT(*) FROM MKT_COB_ST_COINCIDENCIA) WHERE ID_REGISTRO=(Select MAX(ID_REGISTRO) FROM TBLLOGREGIST WHERE ID_SCRIPT=(Select ID_SCRIPT FROM TBLLOGSCRIPT WHERE DESC_SCRIPT='" + NombreScrip + "'));";        
        sql += " COMMIT;" ;
        sql += " UPDATE TBLLOGREGIST SET AVANCE=100 WHERE ID_REGISTRO=(SELECT MAX(ID_REGISTRO) FROM TBLLOGREGIST WHERE ID_SCRIPT=(SELECT ID_SCRIPT FROM TBLLOGSCRIPT WHERE DESC_SCRIPT='" +  NombreScrip + "'));" ;
        sql += " COMMIT;" ;                
        sql += " /****** REGISTRO DE CONTROL ******/" ;
        sql += " UPDATE  TBLLOGREGEST SET ID_ESTATUS=2 WHERE ID_REGISTRO=(SELECT MAX(ID_REGISTRO) FROM TBLLOGREGIST WHERE ID_SCRIPT=(SELECT ID_SCRIPT FROM TBLLOGSCRIPT WHERE DESC_SCRIPT='" + NombreScrip + "'));" ;
        sql += " COMMIT;" ;
        sql += " UPDATE TBLLOGREGIST SET FEC_FIN=current_date WHERE ID_REGISTRO=(SELECT MAX(ID_REGISTRO) FROM TBLLOGREGIST WHERE ID_SCRIPT=(SELECT ID_SCRIPT FROM TBLLOGSCRIPT WHERE DESC_SCRIPT='" + NombreScrip + "'));" ;
        sql += " COMMIT;" ;        
        sql += "/****** TERMINA PROCESO DE CARGA **********/" ;
        
        //CANTIDAD_TOTAL
        sql += " UPDATE TBLLOGREGIST Set CANTIDAD_TOT= 0,CANTIDAD_BAD=0 WHERE ID_REGISTRO=(Select MAX(ID_REGISTRO) FROM TBLLOGREGIST WHERE ID_SCRIPT=(Select ID_SCRIPT FROM TBLLOGSCRIPT WHERE DESC_SCRIPT='" + NombreScrip + "'));" ;
        sql += " COMMIT;" ;

        sql += " UPDATE TBLLOGREGIST Set CANTIDAD_ST=(Select COUNT(*) FROM MKT_COB_ST_COINCIDENCIA) WHERE ID_REGISTRO=(Select MAX(ID_REGISTRO) FROM TBLLOGREGIST WHERE ID_SCRIPT=(Select ID_SCRIPT FROM TBLLOGSCRIPT WHERE DESC_SCRIPT='" + NombreScrip + "'));" ;
        sql += " COMMIT;" ;
        
        sql += " EXIT;" ;
        /* funcion para Ejecutar */        
        return true;        
    }            
        
    
}
