<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %> 
<%@ page import="oracle.jdbc.driver.*" %>
<%@ page import="java.sql.DriverManager" %> 
<%@ page import="java.sql.ResultSet" %> 
<%@ page import="java.sql.Statement" %> 
<%@ page import="java.sql.Connection" %>
<%@ page import="java.util.*,java.lang.*,java.sql.*"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <link rel="stylesheet" type="text/css" href="../css/screen.css" media="screen" />
        <script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
        <script type="text/javascript" src="../js/funcionesHistoricas.js"></script>
        <title>Sistema de Carga de Informaci&oacute;n MKT</title>
    </head>
    <body>
        <div id="content">
            <p id="top">Bienvenido</p>
            <div id="logo" >
                <h1></h1>
            </div>
            <ul id="menu">
                <li><a href="../index.jsp">Home</a></li>
                <li><a href="../prom\promociones.jsp">Promociones y Samplings</a></li>
                <li><a href="../lanz\lanzamientos.jsp">Lanzamientos</a></li>
                <li><a href="../Planes\Planes.jsp">Planes</a></li>
                <li><a href="../dme\dme.jsp">Dme</a></li>                        
                <li><a href="../Liquidos\Liquidos.jsp">L�quidos</a></li>                        
                <li><a href="../Coberturas\Coberturas.jsp">Coberturas</a></li>                        		    
                <li class="current"><a href="Descuentos.jsp">Descuentos</a></li>								    
            </ul>
            <div class="line"></div>
            <div id="pitch">
            </div>	
            <div class="third" >
                <h3 ><a style="color:white">| Carga de Informaci&oacute;n |</a></h3><br>
                    <p>Cargue informaci&oacute;n en formato csv para los diferentes layouts.</p>
            </div>
            <div class="third">
                <h3><a href="Descuentos_status.jsp"style="color:white"> Estatus de proceso </a></h3>
                <p>Valide el estatus del proceso m&aacute;s reciente. Obtenga el res&uacute;men detallado.</p>
            </div>
            <div class="third last">
                <h3><a href="http://kofmxqrboapq1:8080/BOE/OpenDocument/opendoc/openDocument.jsp?sIDType=CUID&iDocID=AVAxM8_OfM9NqFf8xcyWcwg" target="_blank" style="color:white"> Reporte Res&uacute;men de Cargas </a></h3>
                <p>Consulte la informaci&oacute;n cargada a la base de datos correspondiente a Coberturas Historicas</p>
            </div>

            <div class="left">
                <h4>Carga de Archivos (.CSV)</h4>
                <p><em>Seleccione el archivo que desea adjuntar y a continuaci&oacute;n haga click en el bot&oacute;n enviar.</em></p>
                <br></br>			
                <table><td><form action=Descuentos_upload_aut.jsp method="post"><br>
                                <p><a align=center>Ejecutar proceso automatizado.</a></p>
                                <p><input type=submit style="background-color:white " size=50 value="Ejecutar" ></p></form></td></table>

            </div>
            <div class="right">
                <div class="line"></div><br/>
                <%
                    String urljdbc;
                    String loginjdbc;
                    String passjdbc;
                    Connection conexion = null;

// Objeto necesario para enviar instrucciones SQL a la Base de Datos 
                    Statement sentencia = null;
// Objeto necesario para guardar el resultado de un "select" 
                    ResultSet sentencia_sql = null;
                    try {
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        urljdbc = "jdbc:oracle:thin:@10.138.3.239:1527:QSE";
                        loginjdbc = "BOE14QASHA";
                        passjdbc = "B0EI4#QA5AH";
                        conexion = DriverManager.getConnection(urljdbc, loginjdbc, passjdbc);

                        sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                        sentencia_sql = sentencia.executeQuery("SELECT A.ID_REGISTRO FROM TBLLOGREGIST A LEFT OUTER JOIN TBLLOGSCRIPT B ON A.ID_SCRIPT=B.ID_SCRIPT LEFT OUTER JOIN TBLLOGPROYECT C ON B.ID_PROYECTO=C.ID_PROYECTO LEFT OUTER JOIN TBLLOGREGEST D ON A.ID_REGISTRO=D.ID_REGISTRO LEFT OUTER JOIN CATLOGESTATU E ON D.ID_ESTATUS=E.ID_ESTATUS LEFT OUTER JOIN TBLLOGTIPOSC F ON B.ID_SCRIPT=F.ID_SCRIPT LEFT OUTER JOIN CATLOGTIPOSC G ON F.ID_TIPO_SCRIPT=G.ID_TIPO_SCRIPT WHERE DESC_ESTATUS!='FINALIZADO' AND DESC_PROYECTO='MARKETING' AND B.DESC_SCRIPT LIKE '%COB_%'");

                        if (sentencia_sql.next()) {
                            out.println("<br/><br/><b><p align='center' >Proceso en ejecuci&oacute;n para Dme</p></b>");
                            out.println("<b><p align=center >Consulte el estatus del <a href=\"Descuentos_status.jsp\">proceso actual </a>antes de continuar.</p></b></br>");
                            out.println("<br/><br/></br></br>");
                        } else {

                            out.println("<h3>Proceso Carga de Coberturas Historicas</h3>");
                            out.println("<br/><br/>");
                            out.println("<form id='formFile' enctype='multipart/form-data' action='/MKT6/CobHistoricas.do' method='post' target='_blank'><br/>");
                            out.println("<p style=font-weight:bold;>Ingrese ruta de archivo:</p><br/>");
                            out.println("<input name='file' style=\"background-color:white\" type='file' size=40><p></br><input type='submit' style=\"background-color:white\" size='1000' value=\"Enviar Archivo\" id='btnEnviarArch' /></p></form><br />");
                            out.println("<div class=\"line\"></div><br/>");

                            out.println("<h3>Proceso Carga de Coberturas Objetivos</h3>");
                            out.println("<br/><br/>");
                            out.println("<form id='formFileObjetivo' enctype='multipart/form-data' action='/MKT6/CobHistoricas_objetivos.do' method='post' target='_blank'><br/>");
                            out.println("<p style=font-weight:bold;>Ingrese ruta de archivo:</p><br/>");
                            out.println("<input name='file' style=\"background-color:white\" type='file' size=40><p></br><input type='submit' style=\"background-color:white\" size='1000' value=\"Enviar Archivo\" id='btnEnviarArchObjetivo' /></p></form><br />");
                            out.println("<div class=\"line\"></div><br/>");
                            
                            
                        }
                        sentencia_sql.close();
                        sentencia.close();

                    } catch (ClassNotFoundException error1) {
                        out.println("ClassNotFoundException: No se puede localizar el controlador");
                    } catch (SQLException error2) {
                        out.println("Error SQL" + error2.getMessage());
                    } catch (Exception error3) {
                        out.println("Se ha producido una excepción try " + error3.getMessage());
                    } finally {
                        try {
                            if (conexion != null) {
                                conexion.close();
                            }
                        } catch (Exception error3) {
                            out.println("Se ha producido una excepción finally " + error3.getMessage());
                        }
                    }

                %>

            </div>
            <div class="line"></div>
            <div id="footer"></div>	
        </div>
    </body>
</html>