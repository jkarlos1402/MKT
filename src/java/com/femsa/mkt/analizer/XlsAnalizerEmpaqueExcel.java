package com.femsa.mkt.analizer;

import com.femsa.mkt.dao.MktCobhDimEmpaqueDao;
import com.femsa.mkt.dao.MktCobhDimMarcaDao;
import com.femsa.mkt.dao.MktCobhDimSegmentoDao;
import com.femsa.mkt.pojos.MktCobhDimEmpaque;
import com.femsa.mkt.pojos.MktCobhDimMarca;
import com.femsa.mkt.pojos.MktCobhDimSegmento;
import com.femsa.mkt.util.ScriptAnalizer;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.hibernate.Session;
import org.primefaces.model.UploadedFile;
import org.xml.sax.SAXException;

public class XlsAnalizerEmpaqueExcel {

    private List<String> errorsScript = new ArrayList<String>();
    private List<String> omittedSheets;
    private List<String> loadedSheets;
    private List<String> errors;
    private List<MktCobhDimEmpaque> cargasInfoDatos = new ArrayList<MktCobhDimEmpaque>();
    private long numRegistros = 0L;
    private long rowNum = 0;
    private ReadOnlySharedStringsTable stringsTable;
    private XMLStreamReader xmlReader;
    private String sheetName;

    public XlsAnalizerEmpaqueExcel() {
        omittedSheets = new ArrayList<String>();
        loadedSheets = new ArrayList<String>();
        errors = new ArrayList<String>();
    }

    public List<String> getErrorsScript() {
        return errorsScript;
    }

    public void setErrorsScript(List<String> errorsScript) {
        this.errorsScript = errorsScript;
    }

    public long getNumRegistros() {
        return numRegistros;
    }

    public void setNumRegistros(long numRegistros) {
        this.numRegistros = numRegistros;
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

    public List<MktCobhDimEmpaque> getCargasInfoDatos() {
        return cargasInfoDatos;
    }

    public void setCargasInfoDatos(List<MktCobhDimEmpaque> cargasInfoDatos) {
        this.cargasInfoDatos = cargasInfoDatos;
    }

    public void analizeXls(UploadedFile file) {
        Workbook excelXLS = null;
        int numSheet = 0;
        try {
            String extension = getExtension(file.getFileName());
            OPCPackage oPCPackage = null;
            oPCPackage = OPCPackage.open(file.getInputstream());
            stringsTable = new ReadOnlySharedStringsTable(oPCPackage);

            XSSFReader xssfReader = new XSSFReader(oPCPackage);
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator) xssfReader.getSheetsData();
            while (iter.hasNext()) {
                InputStream inputStream = iter.next();
                sheetName = iter.getSheetName();
                if (numSheet == 0) {
                    xmlReader = factory.createXMLStreamReader(inputStream);
                    while (xmlReader.hasNext()) {
                        xmlReader.next();
                        if (xmlReader.isStartElement()) {
                            if (xmlReader.getLocalName().equals("sheetData")) {
                                int attrs = xmlReader.getAttributeCount();
//                                for (int i = 0; i < attrs; i++) {
//                                    System.out.println(xmlReader.getAttributeName(i));
//                                    System.out.println(xmlReader.getAttributeValue(i));
//                                }
                                break;
                            }
                        }
                    }
                    readRows();
                    if (rowNum == 0) {
                        omittedSheets.clear();
                        omittedSheets.add(sheetName.trim().toUpperCase() + ", One or more cells are incorrect");
                    } else {
                        numRegistros = rowNum;
                        loadedSheets.clear();
                        loadedSheets.add(sheetName);
                    }
                } else {
                    omittedSheets.clear();
                    omittedSheets.add(sheetName.trim().toUpperCase() + ", not valid.");
                }
            }

            if (oPCPackage != null) {
                oPCPackage.close();
            }
        } catch (IOException ex) {
//            ex.printStackTrace();
        } catch (Exception ex) {
//            ex.printStackTrace();
        }
    }

    public boolean saveSheetInfoDatos(UploadedFile file, InputStream stream, long numRegistros, Long numProcesados, Long numFaltantes) {
        boolean flagOk = true;
        try {
            int numSheet = 0;
            OPCPackage oPCPackage = null;
            oPCPackage = OPCPackage.open(file.getInputstream());
            stringsTable = new ReadOnlySharedStringsTable(oPCPackage);

            XSSFReader xssfReader = new XSSFReader(oPCPackage);
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator) xssfReader.getSheetsData();
            while (iter.hasNext()) {
                InputStream inputStream = iter.next();
                sheetName = iter.getSheetName();
                if (numSheet == 0) {
                    xmlReader = factory.createXMLStreamReader(inputStream);
                    while (xmlReader.hasNext()) {
                        xmlReader.next();
                        if (xmlReader.isStartElement()) {
                            if (xmlReader.getLocalName().equals("sheetData")) {
                                int attrs = xmlReader.getAttributeCount();
//                                for (int i = 0; i < attrs; i++) {
//                                    System.out.println(xmlReader.getAttributeName(i));
//                                    System.out.println(xmlReader.getAttributeValue(i));
//                                }
                                break;
                            }
                        }
                    }
                    if (saveRows(numRegistros, numProcesados, numFaltantes)) {
                        if (oPCPackage != null) {
                            oPCPackage.close();
                        }
                    } else {
                        if (oPCPackage != null) {
                            oPCPackage.close();
                        }
                        flagOk = false;
                    }
                }
            }
        } catch (InvalidFormatException ex) {
//            ex.printStackTrace();
            flagOk = false;
        } catch (IOException ex) {
//            ex.printStackTrace();
            flagOk = false;
        } catch (SAXException ex) {
//            ex.printStackTrace();
            flagOk = false;
        } catch (OpenXML4JException ex) {
//            ex.printStackTrace();
            flagOk = false;
        } catch (XMLStreamException ex) {
//            ex.printStackTrace();
            flagOk = false;
        }
        return flagOk;
    }

    private static String getExtension(String filename) {
        int index = filename.lastIndexOf('.');
        if (index == -1) {
            return "";
        } else {
            return filename.substring(index + 1);
        }
    }

    public void readRows() throws XMLStreamException {
        errors.clear();
        String elementName = "row";
        String[] data;
        rowNum = 0L;
        end:
        while (xmlReader.hasNext()) {
            xmlReader.next();
            if (xmlReader.isStartElement()) {
                if (xmlReader.getLocalName().equals(elementName)) {
                    rowNum++;
                    if (rowNum > 1) {
                        data = getDataRow();
                        /////////////
//                        System.out.println("ya entre ja"+ data.length);
                        if (data.length < 3) {
                            errors.add("Aproximadamente en la linea " + (rowNum) + " de la hoja " + sheetName + " tiene un numero invalido de columnas .");
                            rowNum = 0L;
                            break end;
                        } else if (data[0] == null) {
                            data[0] = "0";
                        } else {
                            try {
                                Double.parseDouble(data[0].trim().equalsIgnoreCase("") ? "0" : data[0]);
                            } catch (NumberFormatException e) {
                                errors.add("aproximadamente en la celda " + Character.toString((char) (65 + 0)) + "" + (rowNum) + " en la hoja " + sheetName + " Tiene un valor invalido [" + data[0] + "], la hoja sera omitida.");
                                rowNum = 0L;
                                //System.out.println("Entro al Catch " + rowNum);
                                break end;
                            }
                        }

                        /////////////                    
                    }
                }
            }
        }
        if (rowNum > 0) {
            rowNum -= 1;
        }
    }

    public boolean saveRows(long numRegistros, Long numProcesados, Long numFaltantes) throws XMLStreamException {

        errors.clear();
        Date fechaInicio;
        Date fechaFinal;
        int tiempo = 0;
        SimpleDateFormat formatofecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        //ScriptAnalizer analizer = new ScriptAnalizer();

        //TO_DATE(:3, 'DD/MM/YYYY HH24:MI:SS')
        boolean flagOk = true;
        MktCobhDimEmpaqueDao infoDatosDAO = new MktCobhDimEmpaqueDao();

        Session session = infoDatosDAO.getSession();
        List<MktCobhDimEmpaque> cargas = new ArrayList<MktCobhDimEmpaque>();
        MktCobhDimEmpaque infoTemp = null;
        String elementName = "row";
        String[] data = null;
        rowNum = 0L;
        long cont = 0L;
        boolean paso = false;
        fechaInicio = new Date();

        MktCobhDimSegmentoDao dimSegmentoDao = new MktCobhDimSegmentoDao();
        MktCobhDimSegmento segmento;

        MktCobhDimMarcaDao dimMarcaDao = new MktCobhDimMarcaDao();
        MktCobhDimMarca marca;

        try {
            session.beginTransaction();
            //session.createSQLQuery("delete FROM MKT_COBH_TMP_HIS_DATOS").executeUpdate();
            siga:
            while (xmlReader.hasNext()) {
                xmlReader.next();
                if (xmlReader.isStartElement()) {
                    if (xmlReader.getLocalName().equals(elementName)) {
                        if (!paso) {
                            paso = true;
                            continue siga;
                        }
                        data = getDataRow();
                        /////////////
                        //primera columna FECHA (STRING O numeric)
                        infoTemp = new MktCobhDimEmpaque();
                        try {
                            //Primera columna, string o nulo
                            infoTemp.setPkEmpaque(data[0] != null && !data[0].equals("0") && !"".equalsIgnoreCase(data[0]) ? Long.parseLong(data[0]) : null);

                            //Segunda columna, string o nulo
                            infoTemp.setGvEmpaque(data[1] != null ? data[1].trim().toUpperCase() : null);

                            //tercera columna, string o nulo
                            segmento = dimSegmentoDao.getSegmento(data[2] != null && !data[2].equals("0") ? Integer.parseInt(data[2]) : null);

//                            infoTemp.setFkseSegmento(segmento);

                            System.out.println(infoTemp);

                            //Quinto columna, string o nulo
                            marca = dimMarcaDao.getMarca(data[4] != null && !data[4].equals("0") ? Integer.parseInt(data[4]) : null);
//                            infoTemp.setFkMarca(marca);

                            //quinta columna, string            
//                            infoTemp.setEmpaque(data[6] != null ? data[6].trim().toUpperCase() : null);

                            cargas.add(infoTemp);
                            //cargas.add(infoTemp);
                            //System.out.println(rowNum);
                            if (rowNum % 10000 == 0 || rowNum == (numRegistros - 1)) {
                                cont = 0L;
//                                System.out.println("entro a guardar en " + rowNum);
                                for (MktCobhDimEmpaque carga : cargas) {
                                    System.out.println(carga);
                                    //session.save(carga);
                                    session.saveOrUpdate(carga);
                                    if (cont % 100 == 0) {
                                        session.flush();
                                        session.clear();
                                    }
                                    cont++;
                                }
                                cargas.clear();
                                numProcesados = rowNum;
                                numFaltantes = numRegistros - numProcesados;
                            }
                        } catch (Exception ex) {
//                            ex.printStackTrace();
                        }
                        rowNum++;
                    }
                }
            }
            fechaFinal = new Date();
            tiempo = (int) ((fechaFinal.getTime() - fechaInicio.getTime() / 1000) / 60);
            session.getTransaction().commit();
            session.createSQLQuery("INSERT INTO MKT_COBH_LOG (NOMBRE,TOTALREGISTROS,INICIO,FINAL,TIEMPO) VALUES('CARGA EMPAQUE'," + numRegistros + ",TO_DATE('" + formatofecha.format(fechaInicio) + "', 'dd/mm/yyyy hh24:mi:ss'),TO_DATE('" + formatofecha.format(fechaFinal) + "', 'dd/mm/yyyy hh24:mi:ss')," + tiempo + ")").executeUpdate();
            session.createSQLQuery("COMMIT").executeUpdate();

            //session.createSQLQuery("INSERT INTO MKT_COBH_LOG (NOMBRE,TOTALREGISTROS,TIEMPO) VALUES('CARGA HISTORICA'," + numRegistros + "," + tiempo + ")").executeUpdate();
            //System.out.println("INSERT INTO MKT_COBH_LOG (NOMBRE,TOTALREGISTROS,INICIO,FINAL,TIEMPO) VALUES('CARGA HISTORICA'," + numRegistros + ",TO_DATE('" + formatofecha.format(fechaInicio) +  "', 'dd/mm/yyyy hh24:mi:ss'),TO_DATE('" + formatofecha.format(fechaFinal) + "', 'dd/mm/yyyy hh24:mi:ss')," + tiempo + ")");
            //System.out.println(errorsScript);
            if (ScriptAnalizer.executeScrits(errorsScript, "MKT_EMPAQUE.sql")) {
                flagOk = true;
            } else {
                flagOk = false;
            }

        } catch (Exception ex) {
//            ex.printStackTrace();

            if (session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            flagOk = false;
        } finally {
            session.flush();
            session.clear();
            session.close();
            infoDatosDAO.getHibernateUtil().closeSessionFactory();
        }
        /////////////
        return flagOk;
    }

    private String[] getDataRow() throws XMLStreamException {
        List<String> rowValues = new ArrayList<String>();
        while (xmlReader.hasNext()) {
            xmlReader.next();
            if (xmlReader.isStartElement()) {
                if (xmlReader.getLocalName().equals("c")) {
                    CellReference cellReference = new CellReference(xmlReader.getAttributeValue(null, "r"));
                    // Fill in the possible blank cells!
                    while (rowValues.size() < cellReference.getCol()) {
                        rowValues.add("");
                    }
                    String cellType = xmlReader.getAttributeValue(null, "t");
                    rowValues.add(getCellValue(cellType));
                }
            } else if (xmlReader.isEndElement() && xmlReader.getLocalName().equals("row")) {
                break;
            }
        }
        return rowValues.toArray(new String[rowValues.size()]);
    }

    private String getCellValue(String cellType) throws XMLStreamException {
        String value = ""; // by default
        while (xmlReader.hasNext()) {
            xmlReader.next();
            if (xmlReader.isStartElement()) {
                if (xmlReader.getLocalName().equals("v")) {
                    if (cellType != null && cellType.equals("s")) {
                        int idx = Integer.parseInt(xmlReader.getElementText());
                        return new XSSFRichTextString(stringsTable.getEntryAt(idx)).toString();
                    } else {
                        return xmlReader.getElementText();
                    }
                }
            } else if (xmlReader.isEndElement() && xmlReader.getLocalName().equals("c")) {
                break;
            }
        }
        return value;
    }
}
