/********************* MKT - CREACI�N DE TABLAS TEMPORALES Y STAGING **************************/

UPDATE TBLLOGREGIST SET CANTIDAD_REG=(SELECT COUNT(*) FROM MKT_TMP_LZ_OBJETIVO) WHERE ID_REGISTRO=(SELECT MAX(ID_REGISTRO) FROM TBLLOGREGIST WHERE ID_SCRIPT=(SELECT ID_SCRIPT FROM TBLLOGSCRIPT WHERE DESC_SCRIPT='LANZAMIENTOS_OBJETIVOS'));
COMMIT;

UPDATE TBLLOGREGIST SET AVANCE=100 WHERE ID_REGISTRO=(SELECT MAX(ID_REGISTRO) FROM TBLLOGREGIST WHERE ID_SCRIPT=(SELECT ID_SCRIPT FROM TBLLOGSCRIPT WHERE DESC_SCRIPT='LANZAMIENTOS_OBJETIVOS'));
COMMIT;





/********************* CARGA A TABLAS STAGIN **************************/
TRUNCATE TABLE MKT_ST_LZ_OBJETIVO;
COMMIT;

INSERT INTO MKT_ST_LZ_OBJETIVO
SELECT CONSECUTIVO,
  CLAVE_LANZAMIENTO,
  NOMBRE_LANZAMIENTO,
  CORTE,
  CLAVE_CORTE,
  TO_DATE(FECHA_INI_TOTAL,'DD/MM/YYYY'),
  TO_DATE(FECHA_FIN_TOTAL,'DD/MM/YYYY'),
  TO_DATE(FEHCA_INI_REAL,'DD/MM/YYYY'),
  TO_DATE(FECHA_FIN_REAL,'DD/MM/YYYY'),
  TIPO_ENTRADA,
  ID_ENTRADA,
  MES,
  CANAL,
  CF,
  CU,
  CFC,
  PIEZAS,
  CLIENTES,
  COBERUTRA_TRADICIONAL,
  COBERTURA_ESPECIALIZADA,
  COBERTURA_ORO_TRA,
  COBERTURA_PLATA_TRA,
  COBERTURA_BRONCE_TRA,
  COBERTURA_ORO_ESP,
  COBERTURA_PLATA_ESP,
  COBERTURA_BRONCE_ESP,
  RECOMPRA_TRA,
  RECOMPRA_ESP,
  GENERICO_1,
  GENERICO_2,
  GENERICO_3,
  SYSDATE
FROM MKT_TMP_LZ_OBJETIVO ;
COMMIT;

/****** REGISTRO DE CONTROL ******/
UPDATE  TBLLOGREGEST SET ID_ESTATUS=2 WHERE ID_REGISTRO=(SELECT MAX(ID_REGISTRO) FROM TBLLOGREGIST WHERE ID_SCRIPT=(SELECT ID_SCRIPT FROM TBLLOGSCRIPT WHERE DESC_SCRIPT='LANZAMIENTOS_OBJETIVOS'));
COMMIT;

UPDATE TBLLOGREGIST SET FEC_FIN=current_date WHERE ID_REGISTRO=(SELECT MAX(ID_REGISTRO) FROM TBLLOGREGIST WHERE ID_SCRIPT=(SELECT ID_SCRIPT FROM TBLLOGSCRIPT WHERE DESC_SCRIPT='LANZAMIENTOS_OBJETIVOS'));
COMMIT;


UPDATE  TBLLOGREGEST SET ID_ESTATUS=2;
COMMIT;