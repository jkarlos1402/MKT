/********************* SHARE - CREACI�N DE TABLAS TEMPORALES Y STAGING **************************/

INSERT INTO TBLLOGREGIST 
VALUES (seq_TBLLOGREGIST.NEXTVAL,(SELECT ID_SCRIPT FROM TBLLOGSCRIPT WHERE DESC_SCRIPT='PROMOCIONES_SAMPLINGS_FILTRO_ID'),current_date,null,null,null,null,null,null,0);
COMMIT;

INSERT INTO TBLLOGREGEST SELECT MAX(ID_REGISTRO), 1 FROM TBLLOGREGIST WHERE ID_SCRIPT=(SELECT ID_SCRIPT FROM TBLLOGSCRIPT WHERE DESC_SCRIPT='PROMOCIONES_SAMPLINGS_FILTRO_ID'); 
COMMIT;

/****** BORRA TABLAS TEMPORALES **********/


DROP TABLE MKT_TMP_PS_FILTRO_ID;

DROP SEQUENCE MKT_SEQ_PS_FILTRO_ID;

/****** CREA TABLAS TEMPORALES **********/
CREATE SEQUENCE MKT_SEQ_PS_FILTRO_ID;

CREATE TABLE MKT_TMP_PS_FILTRO_ID(
ID_PS_FILTRO_ID NUMBER PRIMARY KEY,
CLAVE_PROMOCION NVARCHAR2(4),
NOMBRE_PROMOCION NVARCHAR2(100),
TIPO_ENTRADA NVARCHAR2(4),
ID NVARCHAR2(30),
CF NUMBER(10,2),
PIEZAS NUMBER(10,2),
FECHA_INICIO NVARCHAR2(20),
FECHA_FIN NVARCHAR2(20),
UO NVARCHAR2(6)
);


 