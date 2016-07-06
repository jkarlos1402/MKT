/********************* SHARE - CREACI�N DE TABLAS TEMPORALES Y STAGING **************************/

UPDATE TBLLOGREGIST SET CANTIDAD_REG=(SELECT COUNT(*) FROM MKT_ST_PS_FILTRO_ID) WHERE ID_REGISTRO=(SELECT MAX(ID_REGISTRO) FROM TBLLOGREGIST WHERE ID_SCRIPT=(SELECT ID_SCRIPT FROM TBLLOGSCRIPT WHERE DESC_SCRIPT='PROMOCIONES_SAMPLINGS_FILTRO_ID'));
COMMIT;


UPDATE TBLLOGREGIST SET AVANCE=100 WHERE ID_REGISTRO=(SELECT MAX(ID_REGISTRO) FROM TBLLOGREGIST WHERE ID_SCRIPT=(SELECT ID_SCRIPT FROM TBLLOGSCRIPT WHERE DESC_SCRIPT='PROMOCIONES_SAMPLINGS_FILTRO_ID'));
COMMIT;


/****** CARGA DE TABLAS STAGIN ******/
TRUNCATE TABLE MKT_ST_PS_FILTRO_ID;
COMMIT;
INSERT INTO MKT_ST_PS_FILTRO_ID
SELECT CLAVE_PROMOCION,
  NOMBRE_PROMOCION,
  TIPO_ENTRADA,
  ID,
  CF,
  PIEZAS,
  TO_DATE(FECHA_INICIO,'DD/MM/YYYY') AS FECHA_INICIO,
  TO_DATE(FECHA_FIN,'DD/MM/YYYY') AS FECHA_FIN,
  UO,
  SYSDATE
FROM MKT_TMP_PS_FILTRO_ID ;
COMMIT;

/****** REGISTRO DE CONTROL ******/
UPDATE  TBLLOGREGEST SET ID_ESTATUS=2 WHERE ID_REGISTRO=(SELECT MAX(ID_REGISTRO) FROM TBLLOGREGIST WHERE ID_SCRIPT=(SELECT ID_SCRIPT FROM TBLLOGSCRIPT WHERE DESC_SCRIPT='PROMOCIONES_SAMPLINGS_FILTRO_ID'));
COMMIT;

UPDATE TBLLOGREGIST SET FEC_FIN=current_date WHERE ID_REGISTRO=(SELECT MAX(ID_REGISTRO) FROM TBLLOGREGIST WHERE ID_SCRIPT=(SELECT ID_SCRIPT FROM TBLLOGSCRIPT WHERE DESC_SCRIPT='PROMOCIONES_SAMPLINGS_FILTRO_ID'));
COMMIT;

UPDATE  TBLLOGREGEST SET ID_ESTATUS=2;
COMMIT;

 