package com.femsa.mkt.analizer;

import com.femsa.mkt.dao.GenericDAO;
import com.femsa.mkt.exception.DAOException;
import com.femsa.mkt.exception.DataBaseException;
import com.femsa.mkt.exception.MKTException;
import com.femsa.mkt.pojos.MktAdmin_Usuario;
import com.femsa.mkt.pojos.MktCobhDimAgrupador;
import com.femsa.mkt.pojos.MktCobhDimCategoria;
import com.femsa.mkt.pojos.MktCobhDimEstado;
import com.femsa.mkt.pojos.MktCobhDimGec;
import com.femsa.mkt.pojos.MktCobhDimGrupoRm1;
import com.femsa.mkt.pojos.MktCobhDimMarca;
import com.femsa.mkt.pojos.MktCobhDimRegion;
import com.femsa.mkt.pojos.MktCobhDimRetornable;
import com.femsa.mkt.pojos.MktCobhDimSegcalorico;
import com.femsa.mkt.pojos.MktCobhDimTamanio;
import com.femsa.mkt.pojos.MktCobhDimTerritorio;
import com.femsa.mkt.pojos.MktCobhDimUo;
import com.femsa.mkt.pojos.MktCobhRelarchivotabla;
import com.femsa.mkt.pojos.MktCobhStHisDatos;
import com.femsa.mkt.pojos.MktCobhTemp;
import com.femsa.mkt.util.Encabezado;
import com.femsa.mkt.util.EncabezadoIndex;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.servlet.ServletContext;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.hibernate.Session;
import org.primefaces.model.UploadedFile;
import org.xml.sax.SAXException;

public class XlsAnalizerDatos {

    private List<String> errorsScript = new ArrayList<String>();
    private List<String> omittedSheets;
    private List<String> loadedSheets;
    private List<String> errors;
    private List<MktCobhStHisDatos> cargasInfoDatos = new ArrayList<MktCobhStHisDatos>();
    private long numRegistros = 0L;
    private long rowNum = 0;
    private ReadOnlySharedStringsTable stringsTable;
    private XMLStreamReader xmlReader;
    private String sheetName;
    private List<String> nombresClave;
    private List<EncabezadoIndex> encabezadosExcel;
    private List<MktCobhRelarchivotabla> encabezadosTodos;

    public XlsAnalizerDatos() {
        omittedSheets = new ArrayList<String>();
        loadedSheets = new ArrayList<String>();
        errors = new ArrayList<String>();
        nombresClave = new ArrayList<String>();
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

    public List<String> getNombresClave() {
        return nombresClave;
    }

    public void setNombresClave(List<String> nombresClave) {
        this.nombresClave = nombresClave;
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

    public List<MktCobhStHisDatos> getCargasInfoDatos() {
        return cargasInfoDatos;
    }

    public void setCargasInfoDatos(List<MktCobhStHisDatos> cargasInfoDatos) {
        this.cargasInfoDatos = cargasInfoDatos;
    }

    public void analizeXls(UploadedFile file,
            List<MktCobhDimEstado> catEstado,
            List<MktCobhDimRegion> catRegion,
            List<MktCobhDimUo> catUo,
            List<MktCobhDimMarca> catMarca,
            List<MktCobhDimTamanio> catTamanio,
            List<MktCobhDimRetornable> catRetornable,
            List<MktCobhDimCategoria> catCategoria,
            List<MktCobhDimGrupoRm1> catRM1,
            List<MktCobhDimSegcalorico> catSegmentoCalorico,
            List<MktCobhDimTerritorio> catTerritorio,
            List<MktCobhDimAgrupador> catAgrupador,
            List<MktCobhDimGec> catGec) {
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
                                break;
                            }
                        }
                    }
                    readRows(file.getFileName(),
                            catEstado,
                            catRegion,
                            catUo,
                            catMarca,
                            catTamanio,
                            catRetornable,
                            catCategoria,
                            catRM1,
                            catSegmentoCalorico,
                            catTerritorio,
                            catAgrupador,
                            catGec);
                    if (rowNum == 0) {
                        omittedSheets.clear();
                        omittedSheets.add(sheetName.trim().toUpperCase() + " [..." + file.getFileName().substring(file.getFileName().length() > 30 ? file.getFileName().length() - 30 : 0, file.getFileName().length()) + "], Una o más celdas son incorrectas");
                    } else {
                        numRegistros = rowNum;
                        loadedSheets.clear();
                        loadedSheets.add(sheetName + " [..." + file.getFileName().substring(file.getFileName().length() > 30 ? file.getFileName().length() - 30 : 0, file.getFileName().length()) + "]");
                    }
                } else {
                    omittedSheets.clear();
                    omittedSheets.add(sheetName.trim().toUpperCase() + " [..." + file.getFileName().substring(file.getFileName().length() > 30 ? file.getFileName().length() - 30 : 0, file.getFileName().length()) + "], hoja no válida.");
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

    public void analizeXls(UploadedFile file, List<MktCobhRelarchivotabla> camposCobH, ServletContext context) throws MKTException {
        int numSheet = 0;
        OPCPackage oPCPackage = null;
        try {
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
                                break;
                            }
                        }
                    }
                    readRows(file.getFileName(), camposCobH, context);
                    if (rowNum == 0) {
                        omittedSheets.clear();
                        omittedSheets.add(sheetName.trim().toUpperCase() + " [..." + file.getFileName().substring(file.getFileName().length() > 30 ? file.getFileName().length() - 30 : 0, file.getFileName().length()) + "], Una o más celdas son incorrectas");
                    } else {
                        numRegistros = rowNum;
                        loadedSheets.clear();
                        loadedSheets.add(sheetName + " [..." + file.getFileName().substring(file.getFileName().length() > 30 ? file.getFileName().length() - 30 : 0, file.getFileName().length()) + "]");
                    }
                } else {
                    omittedSheets.clear();
                    omittedSheets.add(sheetName.trim().toUpperCase() + " [..." + file.getFileName().substring(file.getFileName().length() > 30 ? file.getFileName().length() - 30 : 0, file.getFileName().length()) + "], hoja no válida.");
                }
                numSheet++;
            }
        } catch (IOException ex) {
            throw new MKTException(ex.getMessage());
        } catch (Exception ex) {
//            ex.printStackTrace();
            throw new MKTException(ex.getMessage());
        } finally {
            if (oPCPackage != null) {
                try {
                    oPCPackage.close();
                } catch (IOException ex) {
                    throw new MKTException(ex.getMessage());
                }
            }
        }
    }

    public void saveSheetInfoDatos(UploadedFile file,
            InputStream stream,
            MktAdmin_Usuario usuario,
            List<String> nombresClave,
            Integer fkidregistrodet) throws MKTException, IOException {
        int numSheet = 0;
        OPCPackage oPCPackage = null;
        try {
            oPCPackage = OPCPackage.open(stream);
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
                    saveRows(usuario, nombresClave, fkidregistrodet);
                    numSheet++;
                }
            }
        } catch (InvalidFormatException ex) {
            if (oPCPackage != null) {
                oPCPackage.close();
            }
            throw new MKTException(ex.getMessage());
        } catch (MKTException ex) {
            if (oPCPackage != null) {
                oPCPackage.close();
            }
            throw new MKTException(ex.getMessage());
        } catch (XMLStreamException ex) {
            if (oPCPackage != null) {
                oPCPackage.close();
            }
            throw new MKTException(ex.getMessage());
        } catch (SAXException ex) {
            if (oPCPackage != null) {
                oPCPackage.close();
            }
            throw new MKTException(ex.getMessage());
        } catch (OpenXML4JException ex) {
            if (oPCPackage != null) {
                oPCPackage.close();
            }
            throw new MKTException(ex.getMessage());
        }
    }

    public void saveSheetInfoDatosDinamico(UploadedFile file,
            InputStream stream,
            MktAdmin_Usuario usuario,
            List<String> nombresClave,
            Integer fkidregistrodet) throws MKTException, IOException {
        int numSheet = 0;
        OPCPackage oPCPackage = null;
        try {
            oPCPackage = OPCPackage.open(stream);
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
                    saveRows(usuario, nombresClave, fkidregistrodet, encabezadosExcel, encabezadosTodos);
                    numSheet++;
                }
            }
        } catch (InvalidFormatException ex) {
            if (oPCPackage != null) {
                oPCPackage.close();
            }
            throw new MKTException(ex.getMessage());
        } catch (MKTException ex) {
            if (oPCPackage != null) {
                oPCPackage.close();
            }
            throw new MKTException(ex.getMessage());
        } catch (XMLStreamException ex) {
            if (oPCPackage != null) {
                oPCPackage.close();
            }
            throw new MKTException(ex.getMessage());
        } catch (SAXException ex) {
            if (oPCPackage != null) {
                oPCPackage.close();
            }
            throw new MKTException(ex.getMessage());
        } catch (OpenXML4JException ex) {
            if (oPCPackage != null) {
                oPCPackage.close();
            }
            throw new MKTException(ex.getMessage());
        }
    }

    private static String getExtension(String filename) {
        int index = filename.lastIndexOf('.');
        if (index == -1) {
            return "";
        } else {
            return filename.substring(index + 1);
        }
    }

    public void readRows(String fileName,
            List<MktCobhDimEstado> catEstado,
            List<MktCobhDimRegion> catRegion,
            List<MktCobhDimUo> catUo,
            List<MktCobhDimMarca> catMarca,
            List<MktCobhDimTamanio> catTamanio,
            List<MktCobhDimRetornable> catRetornable,
            List<MktCobhDimCategoria> catCategoria,
            List<MktCobhDimGrupoRm1> catRM1,
            List<MktCobhDimSegcalorico> catSegmentoCalorico,
            List<MktCobhDimTerritorio> catTerritorio,
            List<MktCobhDimAgrupador> catAgrupador,
            List<MktCobhDimGec> catGec) throws XMLStreamException {
        errors.clear();
        String elementName = "row";
        String[] data;
        rowNum = 0L;
        long rowExcel = 0L;
        String estado;
        String region;
        String uo;
        String marca;
        String tamanio;
        String retornable;
        String categoria;
        String rm1;
        while (xmlReader.hasNext()) {
            xmlReader.next();
            if (xmlReader.isStartElement()) {
                if (xmlReader.getLocalName().equals(elementName)) {
                    rowNum++;
                    rowExcel++;
                    data = getDataRow();
                    /////////////
                    if (data.length > 0 && data.length < 21) {
                        errors.add("Aproximadamente en la linea " + (rowExcel) + " de la hoja [" + sheetName + "] del archivo [..." + fileName.substring(fileName.length() > 30 ? fileName.length() - 30 : 0, fileName.length()) + "] tiene un numero inválido de columnas.");
                        rowNum = 0L;
                        break;
                    } else if (data.length == 0) {
                        --rowNum;
                    } else if (data.length == 21 && rowNum > 1) {
                        if (data[1] == null || "".equals(data[1].trim())) {
                            --rowNum;
                            continue;
                        }
                        if (!nombresClave.contains(data[1] != null ? data[1].trim() : "")) {
                            nombresClave.add(data[1] != null ? data[1].trim() : "");
                        }
                        try {
                            Integer.parseInt(data[19].trim());
                        } catch (NumberFormatException e) {
                            errors.add("Aproximadamente en la celda " + Character.toString((char) (65 + 19)) + "" + (rowExcel) + " de la hoja [" + sheetName + "] del archivo [..." + fileName.substring(fileName.length() > 30 ? fileName.length() - 30 : 0, fileName.length()) + "] tiene un valor inválido [" + data[19] + "] se esperaba un valor numérico, la hoja ha sido omitida.");
                            rowNum = 0L;
                            break;
                        }
                        try {
                            Integer.parseInt(data[20].trim());
                        } catch (NumberFormatException e) {
                            errors.add("Aproximadamente en la celda " + Character.toString((char) (65 + 20)) + "" + (rowExcel) + " de la hoja [" + sheetName + "] del archivo [..." + fileName.substring(fileName.length() > 30 ? fileName.length() - 30 : 0, fileName.length()) + "] tiene un valor inválido [" + data[20] + "] se esperaba un valor numérico, la hoja ha sido omitida.");
                            rowNum = 0L;
                            break;
                        }
                        estado = data[5].trim().toUpperCase();
                        region = data[7].trim().toUpperCase();
                        uo = data[9].trim().toUpperCase();
                        marca = data[14].trim().toUpperCase();
                        tamanio = data[15].trim().toUpperCase();
                        retornable = data[16].trim().toUpperCase();
                        categoria = data[17].trim().toUpperCase();
                        rm1 = data[18].trim().toUpperCase();
                        MktCobhDimEstado estadoTmp = new MktCobhDimEstado(estado);
                        estadoTmp.setGvEstado(data[4].trim().toUpperCase());
                        MktCobhDimRegion regionTmp = new MktCobhDimRegion(region);
                        regionTmp.setGvRegion(data[6].trim().toUpperCase());
                        MktCobhDimUo uOTmp = new MktCobhDimUo(uo);
                        uOTmp.setGvUo(data[8].trim().toUpperCase());
                        MktCobhDimMarca marcaTmp = new MktCobhDimMarca(marca);
                        MktCobhDimTamanio tamanioTmp = new MktCobhDimTamanio(tamanio);
                        MktCobhDimRetornable retornableTmp = new MktCobhDimRetornable(retornable);
                        MktCobhDimCategoria categoriaTmp = new MktCobhDimCategoria(categoria);
                        MktCobhDimGrupoRm1 rm1Tmp = new MktCobhDimGrupoRm1(rm1);
                        MktCobhDimSegcalorico segmentoCaloricoTmp = new MktCobhDimSegcalorico(data[10].trim().toUpperCase());
                        MktCobhDimTerritorio territorioTmp = new MktCobhDimTerritorio(data[3].trim().toUpperCase());
                        MktCobhDimAgrupador agrupadorTmp = new MktCobhDimAgrupador(data[12].trim().toUpperCase());
                        agrupadorTmp.setGvIdagrupadorBw(data[11].trim().toUpperCase());
                        MktCobhDimGec gecTmp = new MktCobhDimGec(data[13].trim().toUpperCase());
                        if (!catEstado.contains(estadoTmp)) {
                            catEstado.add(estadoTmp);
                        }
                        estadoTmp = catEstado.get(catEstado.indexOf(estadoTmp));
                        if (estadoTmp.getMktCobhDimRegionList() == null) {
                            estadoTmp.setMktCobhDimRegionList(new ArrayList<MktCobhDimRegion>());
                        }
                        if (!estadoTmp.getMktCobhDimRegionList().contains(regionTmp)) {
                            regionTmp.setFkEstado(estadoTmp);
                            estadoTmp.getMktCobhDimRegionList().add(regionTmp);
                            catRegion.add(regionTmp);
                        }
                        regionTmp = catRegion.get(catRegion.indexOf(regionTmp));
                        if (regionTmp.getMktCobhDimUoList() == null) {
                            regionTmp.setMktCobhDimUoList(new ArrayList<MktCobhDimUo>());
                        }
                        if (!regionTmp.getMktCobhDimUoList().contains(uOTmp)) {
                            uOTmp.setFkRegion(regionTmp);
                            uOTmp.setFkEstado(regionTmp.getFkEstado());
                            regionTmp.getMktCobhDimUoList().add(uOTmp);
                            catUo.add(uOTmp);
                        }
                        if (!catMarca.contains(marcaTmp)) {
                            catMarca.add(marcaTmp);
                        }
                        if (!catTamanio.contains(tamanioTmp)) {
                            catTamanio.add(tamanioTmp);
                        }
                        if (!catRetornable.contains(retornableTmp)) {
                            catRetornable.add(retornableTmp);
                        }
                        if (!catCategoria.contains(categoriaTmp)) {
                            catCategoria.add(categoriaTmp);
                        }
                        if (!catRM1.contains(rm1Tmp)) {
                            catRM1.add(rm1Tmp);
                        }
                        if (!catSegmentoCalorico.contains(segmentoCaloricoTmp)) {
                            catSegmentoCalorico.add(segmentoCaloricoTmp);
                        }
                        if (!catTerritorio.contains(territorioTmp)) {
                            catTerritorio.add(territorioTmp);
                        }
                        if (!catAgrupador.contains(agrupadorTmp)) {
                            catAgrupador.add(agrupadorTmp);
                        }
                        if (!catGec.contains(gecTmp)) {
                            catGec.add(gecTmp);
                        }
                    }
                    /////////////
                }
            }
        }
        if (rowNum > 0) {
            rowNum -= 1;
        }
    }

    public void readRows(String fileName, List<MktCobhRelarchivotabla> camposCobH, ServletContext context) throws XMLStreamException, MKTException, InstantiationException, IllegalAccessException {
        errors.clear();
        String elementName = "row";
        String[] data;
        rowNum = 0L;
        long rowExcel = 0L;
        List<Encabezado> encabezados = new ArrayList<Encabezado>();
        encabezadosExcel = new ArrayList<EncabezadoIndex>();
        encabezadosTodos = (List<MktCobhRelarchivotabla>) context.getAttribute("camposCobH");
        List<Object> registrosCatalogosRenglon;
        List<Integer> encabezadosIndiceCat;
        List<EncabezadoIndex> encabezadosCat = new ArrayList<EncabezadoIndex>();
        Field[] camposClase;
        Class claseCatTmp = null;
        Object elementoCat;
        boolean bndExists = false;
        int indexClave = 0;
        while (xmlReader.hasNext()) {
            xmlReader.next();
            if (xmlReader.isStartElement()) {
                if (xmlReader.getLocalName().equals(elementName)) {
                    rowNum++;
                    rowExcel++;
                    data = getDataRow();
                    if (data == null || data.length <= 1 || (data.length > 1 && (data[1] == null || "".equals(data[1].trim())))) {
                        //System.out.println("resta en " + rowNum);
                        --rowNum;
                        continue;
                    } else if (rowNum == 1 && data.length > 1 && data[1] != null && !"".equals(data[1].trim())) {
                        for (int i = 1; i < data.length; i++) {
                            if (!"".equals(data[i].trim())) {
                                encabezados.add(new Encabezado(data[i], i));
                            }
                        }
                        for (MktCobhRelarchivotabla campoCobH : camposCobH) {
                            if (campoCobH.getRequerido() && !encabezados.contains(new Encabezado(campoCobH.getCampoArchivo(), 0))) {
                                throw new MKTException("Columna [ " + campoCobH.getCampoArchivo() + " ] requerida [ " + fileName + " ]");
                            }
                        }
                        for (Encabezado encabezado : encabezados) {
                            if (!"".equals(encabezado.getNombreEncabezado().trim()) && !camposCobH.contains(new MktCobhRelarchivotabla(encabezado.getNombreEncabezado()))) {
                                throw new MKTException("Columna " + encabezado.getNombreEncabezado() + " inválida [ " + fileName + " ]");
                            }
                            MktCobhRelarchivotabla campoCobH = camposCobH.get(camposCobH.indexOf(new MktCobhRelarchivotabla(encabezado.getNombreEncabezado())));
                            if (campoCobH.getNombreTablaCatalogo() != null) {
                                Object objeto = context.getAttribute("CAT_" + campoCobH.getNombreTablaCatalogo());
                                List<Object> listaCatalogo = (List<Object>) objeto;
                                Class claseCatalogo = (Class) context.getAttribute("CLASS_CAT_" + campoCobH.getNombreTablaCatalogo());
                                encabezadosCat.add(new EncabezadoIndex(encabezado.getNombreEncabezado(), encabezado.getIndex(), listaCatalogo, claseCatalogo, campoCobH));
                                encabezadosExcel.add(new EncabezadoIndex(encabezado.getNombreEncabezado(), encabezado.getIndex(), listaCatalogo, claseCatalogo, campoCobH));
                            } else {
                                encabezadosExcel.add(new EncabezadoIndex(encabezado.getNombreEncabezado(), encabezado.getIndex(), null, null, campoCobH));
                            }
                        }
                        Collections.sort(encabezadosCat);
                        indexClave = encabezados.get(encabezados.indexOf(new Encabezado("CLAVE", 0))).getIndex();
                    } else if (rowNum > 1) {
                        if (!nombresClave.contains(data[indexClave] != null ? data[indexClave].trim() : "")) {
                            nombresClave.add(data[indexClave] != null ? data[indexClave].trim() : "");
                        }
                        registrosCatalogosRenglon = new ArrayList<Object>();
                        encabezadosIndiceCat = new ArrayList<Integer>();
                        int index = 0;
                        for (EncabezadoIndex encabezadoIndex : encabezadosCat) {
                            claseCatTmp = encabezadoIndex.getClaseCatalogo();
                            //System.out.println("clase cat "+claseCatTmp.getName());
                            elementoCat = claseCatTmp.newInstance();
                            bndExists = false;
                            for (Object registroRenglon : registrosCatalogosRenglon) {
                                if (registroRenglon.getClass().getName().equals(claseCatTmp.getName())) {
                                    elementoCat = registroRenglon;
                                    bndExists = true;
                                    break;
                                }
                            }
                            camposClase = claseCatTmp.getDeclaredFields();
                            for (Field field : camposClase) {
                                field.setAccessible(true);
                                if (field.isAnnotationPresent(Column.class) && field.getAnnotation(Column.class).name().equals(encabezadoIndex.getCampoTabla().getNombreCampoCatalogo())) {
                                    field.set(elementoCat, data[encabezadoIndex.getIndex()] != null ? data[encabezadoIndex.getIndex()].toUpperCase() : data[encabezadoIndex.getIndex()]);
                                }
                            }
                            if (!bndExists) {
                                registrosCatalogosRenglon.add(elementoCat);
                                encabezadosIndiceCat.add(index);
                            }
                            index++;
                        }
//                        for (MktCobhRelarchivotabla campoCobh : camposCobH) {
//                            if (campoCobh.getNombreTablaCatalogo() != null && !campoCobh.getRequerido()) {
//                                Object catalogoSN = generaFaltanteCampos((Class) context.getAttribute("CLASS_CAT_" + campoCobh.getNombreTablaCatalogo()));
//                                if (!((List<Object>) context.getAttribute("CAT_" + campoCobh.getNombreTablaCatalogo())).contains(catalogoSN)) {
//                                    ((List<Object>) context.getAttribute("CAT_" + campoCobh.getNombreTablaCatalogo())).add(catalogoSN);
//                                }
//                            }
//                        }
                        for (int i = 0; i < encabezadosIndiceCat.size(); i++) {
                            //  System.out.println("elemento cat "+registrosCatalogosRenglon.get(i));
                            if (!encabezadosCat.get(encabezadosIndiceCat.get(i)).getCatalogo().contains(registrosCatalogosRenglon.get(i))) {
//                                System.out.println("se agrega "+registrosCatalogosRenglon.get(i));
                                encabezadosCat.get(encabezadosIndiceCat.get(i)).getCatalogo().add(registrosCatalogosRenglon.get(i));
                            } else {
                                //  System.out.println("ya existe se reemplaza "+registrosCatalogosRenglon.get(i));
                                int indexTmp = encabezadosCat.get(encabezadosIndiceCat.get(i)).getCatalogo().indexOf(registrosCatalogosRenglon.get(i));
                                registrosCatalogosRenglon.set(i, encabezadosCat.get(encabezadosIndiceCat.get(i)).getCatalogo().get(indexTmp));
                            }
                        }
                        for (Object registroRenglon : registrosCatalogosRenglon) {
                            claseCatTmp = registroRenglon.getClass();
                            for (Object registroRenglonInterno : registrosCatalogosRenglon) {
                                camposClase = registroRenglonInterno.getClass().getDeclaredFields();
                                for (Field field : camposClase) {
                                    if (field.getType().getName().equals(claseCatTmp.getName())) {
                                        field.setAccessible(true);
                                        field.set(registroRenglonInterno, registroRenglon);
                                        break;
                                    }
                                }
                            }
                        }
//                        System.out.println("elementos del renglon " + registrosCatalogosRenglon.size());
//                        for (int i = 0; i < registrosCatalogosRenglon.size(); i++) {
//                            System.out.println("elemento del renglon " + registrosCatalogosRenglon.get(i));
//                        }                        
                    }
                }
            }
        }
        if (rowNum > 0) {
            rowNum -= 1;
        }
    }

    public void saveRows(MktAdmin_Usuario usuario, List<String> nombresClave, Integer fkidregistrodet) throws XMLStreamException, MKTException {
        errors.clear();
        Date fechaInicio;
        Date fechaFinal;
        int tiempo = 0;
        SimpleDateFormat formatofecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        GenericDAO genericDAO = null;
        Session session = null;
        MktCobhTemp cobhTemp;
        String elementName = "row";
        String[] data;
        rowNum = 0L;
        long cont = 0L;
        fechaInicio = new Date();
        String claves = "";
        try {
            genericDAO = new GenericDAO();
            session = genericDAO.getSession();
            for (String clave : nombresClave) {
                System.out.println("delete from MKT_COBH_TEMP2016 where CLAVE = '" + clave + "' " + this);
                genericDAO.excecuteNativeDDLSQL("delete from MKT_COBH_TEMP2016 where CLAVE = '" + clave + "'");
                genericDAO.excecuteNativeDDLSQL("commit");
                claves += ",'" + clave + "'";
            }
            claves = claves.replaceFirst(",", "");
            session.beginTransaction();
//            session.createSQLQuery("delete FROM MKT_COBH_TMP_HIS_DATOS").executeUpdate();
            siga:
            while (xmlReader.hasNext()) {
                xmlReader.next();
                if (xmlReader.isStartElement()) {
                    if (xmlReader.getLocalName().equals(elementName)) {
                        rowNum++;
                        data = getDataRow();
                        /////////////                       
                        if (data.length == 21) {
                            if (rowNum > 1) {
                                if (data[1] == null || "".equals(data[1].trim())) {
                                    --rowNum;
                                    continue;
                                }
                                cobhTemp = new MktCobhTemp();

                                //Clave
                                cobhTemp.setClave(data[1] != null && !"".equals(data[1].trim()) ? data[1].trim().toUpperCase() : "S/N");

                                //Año natural/Mes - Clave
                                cobhTemp.setAnioMes(data[2] != null && !"".equals(data[2].trim()) ? data[2].trim().toUpperCase() : "S/N");

                                //Territorio
                                cobhTemp.setTerritorio(data[3] != null && !"".equals(data[3].trim()) ? data[3].trim().toUpperCase() : "S/N");

                                //Estado -clave         
                                cobhTemp.setClaveEstado(data[4] != null && !"".equals(data[4].trim()) ? data[4].trim().toUpperCase() : "S/N");

                                //Estado         
                                cobhTemp.setEstado(data[5] != null && !"".equals(data[5].trim()) ? data[5].trim().toUpperCase() : "S/N");

                                //Región Comercial - Clave            
                                cobhTemp.setRegionClave(data[6] != null && !"".equals(data[6].trim()) ? data[6].trim().toUpperCase() : "S/N");

                                //Región Comercial            
                                cobhTemp.setRegion(data[7] != null && !"".equals(data[7].trim()) ? data[7].trim().toUpperCase() : "S/N");

                                //Centro - clave       
                                cobhTemp.setClaveCentro(data[8] != null && !"".equals(data[8].trim()) ? data[8].trim().toUpperCase() : "S/N");

                                //Centro         
                                cobhTemp.setCentro(data[9] != null && !"".equals(data[9].trim()) ? data[9].trim().toUpperCase() : "S/N");

                                //Segmento Calórico
                                cobhTemp.setSegcalorico(data[10] != null && !"".equals(data[10].trim()) ? data[10].trim().toUpperCase() : "S/N");

                                //IDs Agrupador - Clave           
                                cobhTemp.setIdagrupadorBw(data[11] != null && !"".equals(data[11].trim()) ? data[11].trim().toUpperCase() : "S/N");

                                //IDs Agrupador                                
                                cobhTemp.setIdagrupadorMkt(data[12] != null && !"".equals(data[12].trim()) ? Normalizer.normalize(data[12].trim(), Normalizer.Form.NFD).replaceAll("\'|\"|,|\\p{InCombiningDiacriticalMarks}+", "").toUpperCase() : "S/N");

                                //GEC            
                                cobhTemp.setGec(data[13] != null && !"".equals(data[13].trim()) ? data[13].trim().toUpperCase() : "S/N");

                                //Marca            
                                cobhTemp.setMarca(data[14] != null && !"".equals(data[14].trim()) ? data[14].trim().toUpperCase() : "S/N");

                                //tamaño
                                cobhTemp.setTamanio(data[15] != null && !"".equals(data[15].trim()) ? data[15].trim().toUpperCase() : "S/N");

                                //retornable   
                                cobhTemp.setRetornable(data[16] != null && !"".equals(data[16].trim()) ? data[16].trim().toUpperCase() : "S/N");

                                //Categoria       
                                cobhTemp.setCategoria(data[17] != null && !"".equals(data[17].trim()) ? data[17].trim().toUpperCase() : "S/N");

                                //grupo rm1      
                                cobhTemp.setGrupoRm1(data[18] != null && !"".equals(data[18].trim()) ? data[18].trim().toUpperCase() : "S/N");

                                //clientes activos    
                                cobhTemp.setClientesActivos(data[19] != null && !"".equals(data[19]) ? new Integer(data[19].trim()) : 0);

                                //clientes activos    
                                cobhTemp.setClientesVenta(data[20] != null && !"".equals(data[20]) ? new Integer(data[20].trim()) : 0);

                                cobhTemp.setFechaCarga(fechaInicio);
                                cobhTemp.setFkidregistrodet(fkidregistrodet);
                                genericDAO.saveOrUpdate(cobhTemp, false);
                                if (cont % 100 == 0) {
                                    session.flush();
                                    session.clear();
                                    cont = 0L;
                                }
                                cont++;
                            }
                        } else if (data.length == 0) {
                            --rowNum;
                        }
                    }
                }
            }
            if (rowNum > 0) {
                rowNum -= 1;
            }
            fechaFinal = new Date();
            tiempo = ((int) (fechaFinal.getTime() - fechaInicio.getTime())) / 1000;
            System.out.println("UPDATE mkt_cobh_temp2016 a  SET a.FK_ESTADO = (SELECT PK_ESTADO FROM mkt_cobh_dim_ESTADO b WHERE a.ESTADO = b.ESTADO) where a.clave in (" + claves + ")");
            session.createSQLQuery("UPDATE mkt_cobh_temp2016 a  SET a.FK_ESTADO = (SELECT PK_ESTADO FROM mkt_cobh_dim_ESTADO b WHERE a.ESTADO = b.ESTADO) where a.clave in (" + claves + ")").executeUpdate();
            session.createSQLQuery("UPDATE mkt_cobh_temp2016 a  SET a.FK_REGION = (SELECT PK_REGION FROM mkt_cobh_dim_REGION b WHERE a.FK_ESTADO = b.FK_ESTADO AND a.REGION = b.REGION) where a.clave in (" + claves + ")").executeUpdate();
            session.createSQLQuery("UPDATE mkt_cobh_temp2016 a  SET a.FK_UO = (SELECT PK_UO FROM mkt_cobh_dim_UO b WHERE a.FK_ESTADO = b.FK_ESTADO AND a.FK_REGION = b.FK_REGION AND a.CENTRO = b.UO) where a.clave in (" + claves + ")").executeUpdate();
            session.createSQLQuery("UPDATE mkt_cobh_temp2016 a  SET a.fk_MARCA = (SELECT PK_MARCA FROM mkt_cobh_dim_MARCA b WHERE a.MARCA = b.MARCATEXTO) where a.clave in (" + claves + ")").executeUpdate();
            session.createSQLQuery("UPDATE mkt_cobh_temp2016 a  SET a.fk_TAMANIO = (SELECT PK_TAMANIO FROM mkt_cobh_dim_TAMANIO b WHERE a.TAMANIO = b.TAMANIOTEXTO) where a.clave in (" + claves + ")").executeUpdate();
            session.createSQLQuery("UPDATE mkt_cobh_temp2016 a  SET a.fk_RETORNABLE = (SELECT PK_RETORNABLE FROM mkt_cobh_dim_RETORNABLE b WHERE a.RETORNABLE = b.RETORNABLETEXTO) where a.clave in (" + claves + ")").executeUpdate();
            session.createSQLQuery("UPDATE mkt_cobh_temp2016 a  SET a.fk_CATEGORIA = (SELECT PK_CATEGORIA FROM mkt_cobh_dim_CATEGORIA b WHERE a.CATEGORIA = b.CATEGORIATEXTO) where a.clave in (" + claves + ")").executeUpdate();
            session.createSQLQuery("UPDATE mkt_cobh_temp2016 a  SET a.fk_RM1 = (SELECT PK_RM1 FROM mkt_cobh_dim_GRUPO_RM1 b WHERE a.GRUPO_RM1 = b.RM1TEXTO) where a.clave in (" + claves + ")").executeUpdate();
            session.createSQLQuery("UPDATE mkt_cobh_temp2016 a  SET a.fk_SEGCALORICO = (SELECT PK_SEGCALORICO FROM mkt_cobh_dim_SEGCALORICO b WHERE a.SEGCALORICO = b.SEGCALORICOTEXTO) where a.clave in (" + claves + ")").executeUpdate();
            session.createSQLQuery("UPDATE mkt_cobh_temp2016 a  SET a.FK_TERRITORIO = (SELECT PK_TERRITORIO FROM mkt_cobh_dim_TERRITORIO b WHERE a.TERRITORIO = b.TERRITORIOTEXTO) where a.clave in (" + claves + ")").executeUpdate();
            session.createSQLQuery("UPDATE mkt_cobh_temp2016 a  SET a.fk_GEC = (SELECT PK_GEC FROM mkt_cobh_dim_GEC b WHERE a.GEC = b.GECTEXTO) where a.clave in (" + claves + ")").executeUpdate();
            session.createSQLQuery("UPDATE mkt_cobh_temp2016 a  SET a.fk_idagrupador_mkt = (SELECT PK_IDAGRUPADOR FROM mkt_cobh_dim_AGRUPADOR b WHERE a.IDAGRUPADOR_MKT = b.IDAGRUPADORTEXTO) where a.clave in (" + claves + ")").executeUpdate();
            session.createSQLQuery("COMMIT").executeUpdate();
//
//            //session.createSQLQuery("INSERT INTO MKT_COBH_LOG (NOMBRE,TOTALREGISTROS,TIEMPO) VALUES('CARGA HISTORICA'," + numRegistros + "," + tiempo + ")").executeUpdate();
//            System.out.println("INSERT INTO MKT_COBH_LOG (NOMBRE,TOTALREGISTROS,INICIO,FINAL,TIEMPO) VALUES('CARGA HISTORICA'," + numRegistros + ",TO_DATE('" + formatofecha.format(fechaInicio) + "', 'dd/mm/yyyy hh24:mi:ss'),TO_DATE('" + formatofecha.format(fechaFinal) + "', 'dd/mm/yyyy hh24:mi:ss')," + tiempo + ")");

            session.getTransaction().commit();
        } catch (DataBaseException ex) {
            if (session != null && session.getTransaction().isActive()) {
                session.createSQLQuery("ROLLBACK").executeUpdate();
                session.getTransaction().rollback();
            }
            throw new MKTException(ex.getMessage());
        } catch (DAOException ex) {
            if (session != null && session.getTransaction().isActive()) {
                session.createSQLQuery("ROLLBACK").executeUpdate();
                session.getTransaction().rollback();
            }
            throw new MKTException(ex.getMessage());
        } catch (NumberFormatException ex) {
            if (session != null && session.getTransaction().isActive()) {
                session.createSQLQuery("ROLLBACK").executeUpdate();
                session.getTransaction().rollback();
            }
            throw new MKTException(ex.getMessage());
        } finally {
            if (genericDAO != null) {
                genericDAO.closeDAO();
            }
        }
        /////////////        
    }

    public void saveRows(MktAdmin_Usuario usuario, List<String> nombresClave, Integer fkidregistrodet, List<EncabezadoIndex> encabezados, List<MktCobhRelarchivotabla> encabezadosTodos) throws XMLStreamException, MKTException {
        errors.clear();
        Date fechaInicio;
        GenericDAO genericDAO = null;
        Session session = null;
        MktCobhTemp cobhTemp;
        String elementName = "row";
        String[] data;
        rowNum = 0L;
        long cont = 0L;
        fechaInicio = new Date();
        String claves = "";
        try {
            Field[] camposCobh = MktCobhTemp.class.getDeclaredFields();
            genericDAO = new GenericDAO();
            session = genericDAO.getSession();
            for (String clave : nombresClave) {
                System.out.println("delete from MKT_COBH_TEMP2016 where CLAVE = '" + clave + "' " + this);
                genericDAO.excecuteNativeDDLSQL("delete from MKT_COBH_TEMP2016 where CLAVE = '" + clave + "'");
                genericDAO.excecuteNativeDDLSQL("commit");
                claves += ",'" + clave + "'";
            }
            claves = claves.replaceFirst(",", "");
            session.beginTransaction();
//            session.createSQLQuery("delete FROM MKT_COBH_TMP_HIS_DATOS").executeUpdate();
            siga:
            while (xmlReader.hasNext()) {
                xmlReader.next();
                if (xmlReader.isStartElement()) {
                    if (xmlReader.getLocalName().equals(elementName)) {
                        rowNum++;
                        data = getDataRow();
                        /////////////        
                        if (data == null || data.length <= 1 || data[1] == null || "".equals(data[1].trim())) {
                            --rowNum;
                            continue;
                        } else if (rowNum > 1) {
                            cobhTemp = new MktCobhTemp();
                            for (EncabezadoIndex encabezado : encabezados) {
                                for (Field field : camposCobh) {
                                    if (field.isAnnotationPresent(Column.class) && field.getAnnotation(Column.class).name().equals(encabezado.getCampoTabla().getCampoTabla())) {
                                        field.setAccessible(true);
                                        if (field.getType().getName().equals(Integer.class.getName())) {
                                            field.set(cobhTemp, data[encabezado.getIndex()] != null ? Integer.parseInt(data[encabezado.getIndex()]) : null);
                                        } else {
                                            field.set(cobhTemp, data[encabezado.getIndex()] != null ? data[encabezado.getIndex()].toUpperCase() : "S/N");
                                        }
                                    }
                                }
                            }
                            cobhTemp.setFechaCarga(fechaInicio);
                            cobhTemp.setFkidregistrodet(fkidregistrodet);
                            genericDAO.saveOrUpdate(cobhTemp, false);
                            if (cont % 100 == 0) {
                                session.flush();
                                session.clear();
                                cont = 0L;
                            }
                            cont++;
                        }
                    }
                }
            }
            if (rowNum > 0) {
                rowNum -= 1;
            }
            session.getTransaction().commit();
            List<String> catalogosActualizados = new ArrayList<String>();
            for (MktCobhRelarchivotabla encabezadoT : encabezadosTodos) {
                if (encabezadoT.getNombreTablaCatalogo() != null && !catalogosActualizados.contains(encabezadoT.getNombreTablaCatalogo())) {
                    System.out.println("UPDATE mkt_cobh_temp2016 a  SET a." + encabezadoT.getNombreCampoRegreso().replaceFirst("PK", "FK") + " = (SELECT " + encabezadoT.getNombreCampoRegreso() + " FROM " + encabezadoT.getNombreTablaCatalogo() + " b WHERE a." + encabezadoT.getCampoTabla() + " = b." + encabezadoT.getNombreCampoCatalogo() + ") where a.clave in (" + claves + ")");
                    session.createSQLQuery("UPDATE mkt_cobh_temp2016 a  SET a." + encabezadoT.getNombreCampoRegreso().replaceFirst("PK", "FK") + " = (SELECT " + encabezadoT.getNombreCampoRegreso() + " FROM " + encabezadoT.getNombreTablaCatalogo() + " b WHERE a." + encabezadoT.getCampoTabla() + " = b." + encabezadoT.getNombreCampoCatalogo() + ") where a.clave in (" + claves + ")").executeUpdate();
                    catalogosActualizados.add(encabezadoT.getNombreTablaCatalogo());
                }
            }
            session.createSQLQuery("COMMIT").executeUpdate();
        } catch (DataBaseException ex) {
            if (session != null && session.getTransaction().isActive()) {
                session.createSQLQuery("ROLLBACK").executeUpdate();
                session.getTransaction().rollback();
            }
            throw new MKTException(ex.getMessage());
        } catch (DAOException ex) {
            if (session != null && session.getTransaction().isActive()) {
                session.createSQLQuery("ROLLBACK").executeUpdate();
                session.getTransaction().rollback();
            }
            throw new MKTException(ex.getMessage());
        } catch (NumberFormatException ex) {
            if (session != null && session.getTransaction().isActive()) {
                session.createSQLQuery("ROLLBACK").executeUpdate();
                session.getTransaction().rollback();
            }
            throw new MKTException(ex.getMessage());
        } catch (IllegalArgumentException ex) {
            if (session != null && session.getTransaction().isActive()) {
                session.createSQLQuery("ROLLBACK").executeUpdate();
                session.getTransaction().rollback();
            }
            throw new MKTException(ex.getMessage());
        } catch (IllegalAccessException ex) {
            if (session != null && session.getTransaction().isActive()) {
                session.createSQLQuery("ROLLBACK").executeUpdate();
                session.getTransaction().rollback();
            }
            throw new MKTException(ex.getMessage());
        } finally {
            if (genericDAO != null) {
                genericDAO.closeDAO();
            }
        }
        /////////////        
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
