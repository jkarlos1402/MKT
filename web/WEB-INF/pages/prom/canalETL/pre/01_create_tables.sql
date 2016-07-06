/********************* MKT - CREACI�N DE TABLAS TEMPORALES Y STAGING **************************/

INSERT INTO TBLLOGREGIST 
VALUES (seq_TBLLOGREGIST.NEXTVAL,(SELECT ID_SCRIPT FROM TBLLOGSCRIPT WHERE DESC_SCRIPT='PROMOCIONES_SAMPLINGS_RUTA_CANAL'),current_date,null,null,null,null,null,null,0);
COMMIT;

INSERT INTO TBLLOGREGEST SELECT MAX(ID_REGISTRO), 1 FROM TBLLOGREGIST WHERE ID_SCRIPT=(SELECT ID_SCRIPT FROM TBLLOGSCRIPT WHERE DESC_SCRIPT='PROMOCIONES_SAMPLINGS_RUTA_CANAL'); 
COMMIT;

/****** BORRA TABLAS TEMPORALES **********/


DROP TABLE MKT_TMP_PS_RUTA_CANAL;

DROP SEQUENCE MKT_SEQ_PS_RUTA_CANAL;

/****** CREA TABLAS TEMPORALES **********/
CREATE SEQUENCE MKT_SEQ_PS_RUTA_CANAL;

CREATE TABLE MKT_TMP_PS_RUTA_CANAL(
ID_PS_RUTA_CANAL NUMBER PRIMARY KEY,
RUTA_REPARTO NVARCHAR2(10),
CANAL_MKT NVARCHAR2(30)
);
 