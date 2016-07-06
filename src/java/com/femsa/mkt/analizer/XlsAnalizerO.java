package com.femsa.mkt.analizer;

import com.femsa.mkt.pojos.MktCobStHisObjetivos;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlsAnalizerO{

    private List<String> omittedSheets;
    private List<String> loadedSheets;
    private List<String> errors;

    public XlsAnalizerO() {
        omittedSheets = new ArrayList<String>();
        loadedSheets = new ArrayList<String>();
        errors = new ArrayList<String>();
    }

    public List<String> getLoadedSheets() {
        return loadedSheets;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public void setLoadedSheets(List<String> loadedSheets) {
        this.loadedSheets = loadedSheets;
    }

    public List<String> getOmittedSheets() {
        return omittedSheets;
    }

    public void setOmittedSheets(List<String> omittedSheets) {
        this.omittedSheets = omittedSheets;
    }

    public List<MktCobStHisObjetivos> analizeXls(InputStream file, String fileName) {
        List<MktCobStHisObjetivos> cargas = new ArrayList<MktCobStHisObjetivos>();
        Workbook excelXLS = null;
        try {
            String extension = getExtension(fileName);
            Iterator<Row> rowIterator = null;

            Iterator<Sheet> sheets = null;
            if (extension.equalsIgnoreCase("xlsx")) {
                excelXLS = new XSSFWorkbook(file);
            } else if (extension.equalsIgnoreCase("xls")) {
                excelXLS = new HSSFWorkbook(file);
            }
            int numberOfSheets = excelXLS.getNumberOfSheets();
//            for (int i = 0; i < numberOfSheets; i++) {
            Sheet sheet = excelXLS.getSheetAt(0);
            rowIterator = sheet.iterator();

            cargas = this.analizeSheet(rowIterator, sheet.getSheetName());
            if (cargas != null) {
                loadedSheets.add(sheet.getSheetName().trim().toUpperCase());
            } else {
                omittedSheets.add(sheet.getSheetName().trim().toUpperCase() + ", One or more cells are incorrect");
            }

//            }
        } catch (IOException ex) {
        } finally {
            try {
                file.close();
                file = null;
            } catch (IOException ex) {
            }
        }
        return cargas;
    }

    public List<MktCobStHisObjetivos> analizeSheet(Iterator<Row> rowIterator, String sheetName) {
        int numRow = 0;
        String year = "";
        List<MktCobStHisObjetivos> cargas = new ArrayList<MktCobStHisObjetivos>();
        Cell cell = null;
        String[] encabezados = {"CONCEPTO", "C_CENTRO", "OBJETIVO"};
        String[] encabezadosArchivo = new String[3];
        MktCobStHisObjetivos agrupacion = null;
        while (rowIterator != null && rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (numRow == 0) {
                encabezadosArchivo[0] = row.getCell(0).getStringCellValue();
                encabezadosArchivo[1] = row.getCell(1).getStringCellValue();
                encabezadosArchivo[2] = row.getCell(2).getStringCellValue();
            }
            if (numRow > 0) {
                cell = row.getCell(0);//CONCEPTO
                if (encabezados[0].equalsIgnoreCase(encabezadosArchivo[0])) {
                    agrupacion = new MktCobStHisObjetivos();
                    if (cell == null || cell.getCellType() == Cell.CELL_TYPE_STRING) {
                        agrupacion.setConcepto(cell == null ? null : cell.getStringCellValue());
                    }else{
                        cargas = null;
                        errors.add("El valor de la celda no es valido, se esperaba una cadena");
                        break;
                    }
                } else {
                    cargas = null;
                    errors.add("La columna " + encabezadosArchivo[0] + " no es valida");
                    break;
                }
                cell = row.getCell(1);//c_centro
                if (encabezados[1].equalsIgnoreCase(encabezadosArchivo[1])) {                    
                    if (cell != null && cell.getCellType() == Cell.CELL_TYPE_STRING) {
                        agrupacion.setCCentro(cell.getStringCellValue());
                    }else{
                        cargas = null;
                        errors.add("El valor de la celda no es valido, se esperaba una cadena");
                        break;
                    }
                } else {
                    cargas = null;
                    errors.add("La columna " + encabezadosArchivo[1] + " no es valida");
                    break;
                }
                cell = row.getCell(2);//objetivo
                if (encabezados[2].equalsIgnoreCase(encabezadosArchivo[2])) {                    
                    if (cell == null || cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        agrupacion.setObjetivo(cell == null ? null : cell.getNumericCellValue());
                    }else{
                        cargas = null;
                        errors.add("El valor de la celda no es valido, se esperaba un valor numerico");
                        break;
                    }
                } else {
                    cargas = null;
                    errors.add("La columna " + encabezadosArchivo[2] + " no es valida");
                    break;
                }
                cargas.add(agrupacion);
            }
            numRow++;
        }
        return cargas;
    }

    private static String getExtension(String filename) {
        int index = filename.lastIndexOf('.');
        if (index == -1) {
            return "";
        } else {
            return filename.substring(index + 1);
        }
    }
}
