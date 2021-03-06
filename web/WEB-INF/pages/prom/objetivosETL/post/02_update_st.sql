/********************* SHARE - CREACI�N DE TABLAS TEMPORALES Y STAGING **************************/

UPDATE TBLLOGREGIST SET CANTIDAD_REG=(SELECT COUNT(*) FROM MKT_ST_PS_OBJETIVO) WHERE ID_REGISTRO=(SELECT MAX(ID_REGISTRO) FROM TBLLOGREGIST WHERE ID_SCRIPT=(SELECT ID_SCRIPT FROM TBLLOGSCRIPT WHERE DESC_SCRIPT='PROMOCIONES_SAMPLINGS_OBJETIVOS'));
COMMIT;

UPDATE TBLLOGREGIST SET AVANCE=100 WHERE ID_REGISTRO=(SELECT MAX(ID_REGISTRO) FROM TBLLOGREGIST WHERE ID_SCRIPT=(SELECT ID_SCRIPT FROM TBLLOGSCRIPT WHERE DESC_SCRIPT='PROMOCIONES_SAMPLINGS_OBJETIVOS'));
COMMIT;

/****** CARGA DE TABLAS STAGIN ******/
TRUNCATE TABLE MKT_ST_PS_OBJETIVO;
INSERT INTO MKT_ST_PS_OBJETIVO
SELECT CLAVE_PROMOCION,
  NOMBRE_PROMOCION,
  CORTE,
  CLAVE_CORTE,
  TM_CATEGORIA,
  TM_RETORNABILIDAD,
  TM_EMPAQUE,
  TM_TAMANO,
  TM_TPV,
  TO_DATE(FECHA_INICIO,'DD/MM/YYYY'),
  TO_DATE(FECHA_FIN,'DD/MM/YYYY'),
  SKU_VENTA,
  CANAL,
  CF,
  CU,
  CFC,
  PIEZAS,
  C_ORO_TRA,
  C_PLATA_TRA,
  C_BRONCE_TRA,
  C_TRA,
  COB_ORO_TRA,
  COB_PLATA_TRA,
  COB_BRONCE_TRA,
  COB_TRA,
  C_ORO_ESP,
  C_PLATA_ESP,
  C_BRONCE_ESP,
  C_ESP,
  COB_ORO_ESP,
  COB_PLATA_ESP,
  COB_BRONCE_ESP,
  COB_ESP,
  REDENCION,
  DESCUENTOS,
  GEN_1,
  GEN_2,
  GEN_3,
  GEN_4,
  GEN_5,
  GEN_6,
  GEN_7,
  GEN_8,
  GEN_9,
  GEN_10,
  SYSDATE
FROM MKT_TMP_PS_OBJETIVO ;

COMMIT;
/****** REGISTRO DE CONTROL ******/
UPDATE  TBLLOGREGEST SET ID_ESTATUS=2 WHERE ID_REGISTRO=(SELECT MAX(ID_REGISTRO) FROM TBLLOGREGIST WHERE ID_SCRIPT=(SELECT ID_SCRIPT FROM TBLLOGSCRIPT WHERE DESC_SCRIPT='PROMOCIONES_SAMPLINGS_OBJETIVOS'));
COMMIT;

UPDATE TBLLOGREGIST SET FEC_FIN=current_date WHERE ID_REGISTRO=(SELECT MAX(ID_REGISTRO) FROM TBLLOGREGIST WHERE ID_SCRIPT=(SELECT ID_SCRIPT FROM TBLLOGSCRIPT WHERE DESC_SCRIPT='PROMOCIONES_SAMPLINGS_OBJETIVOS'));
COMMIT;

UPDATE  TBLLOGREGEST SET ID_ESTATUS=2;
COMMIT;

 