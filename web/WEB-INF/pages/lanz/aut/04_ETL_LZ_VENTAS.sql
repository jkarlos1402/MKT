---- ETL LANZAMIENTOS VENTAS

-- TABLAS DE HECHOS SKU


-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------
----------------------- LLENADO DE TABLA DE HECHOS PARA OBJETIVOS DE VENTAS POR SKU-------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------------------------

--TABLA DE HECHOS A CENTRO

INSERT INTO MKT_LZ_FACT_OBJ_SKU_VT(
SELECT DISTINCT (TO_CHAR(TO_DATE(OB.FECHA_INI_TOTAL),'YYYY-MM-DD')) AS PERIODO_INICIO,
(TO_CHAR(TO_DATE(OB.FECHA_FIN_TOTAL),'YYYY-MM-DD')) AS PERIODO_FIN,
G.ESTADO,
G.REGION_ZONA,
OB.CLAVE_CORTE,
NVL(OB.CANAL,'#') AS CANAL,
L.PK_LANZAMIENTO,
LPAD(OB.ID_ENTRADA,18,'0') AS SKU,
(TO_CHAR(TO_DATE(OB.FEHCA_INI_REAL),'YYYY-MM-DD')) AS PIR,
(TO_CHAR(TO_DATE(OB.FECHA_FIN_REAL),'YYYY-MM-DD')) AS PIF,
OB.MES,
OB.CF,
OB.CFC,
OB.CU,
OB.PIEZAS,
OB.GENERICO_1,
OB.GENERICO_2,
OB.GENERICO_3
FROM MKT_ST_LZ_OBJETIVO OB, MKT_GEOGRAFIA G, MKT_LZ_DIM_LANZAMIENTO L
WHERE NVL(G.CENTRO,'#')=NVL(OB.CLAVE_CORTE,'#')
AND NVL(L.GV_CONSECUTIVO,'#')=NVL(OB.CONSECUTIVO,'#')
AND NVL(L.GV_NOMBRE_LANZAMIENTO,'#')=NVL(OB.NOMBRE_LANZAMIENTO,'#')
AND NVL(L.GV_CLAVE_LANZAMIENTO,'#')=NVL(OB.CLAVE_LANZAMIENTO,'#')
AND OB.CORTE='UNIDAD OPERATIVA'
AND OB.TIPO_ENTRADA='SKU');

COMMIT;

--SELECT * FROM MKT_GEOGRAFIA;

--SELECT * FROM MKT_ST_GEOGRAFIA;

--UPDATE MKT_ST_LZ_OBJETIVO SET ='SKU';
--SELECT * FROM MKT_ST_LZ_OBJETIVO;
--SELECT * FROM MKT_LZ_DIM_LANZAMIENTO;

-- TABLA AGGREGADA A NIVEL REGION POR SKU

--INSERT INTO MKT_GEOGRAFIA VALUES('9','ZTN', 'MR0084',NULL);
--INSERT INTO MKT_ST_GEOGRAFIA VALUES('9','ZTN', 'MR0084',NULL);

INSERT INTO MKT_LZ_AGG_OBJ_SKU_VT_REG(
SELECT DISTINCT (TO_CHAR(TO_DATE(OB.FECHA_INI_TOTAL),'YYYY-MM-DD')) AS PERIODO_INICIO,
(TO_CHAR(TO_DATE(OB.FECHA_FIN_TOTAL),'YYYY-MM-DD')) AS PERIODO_FIN,
G.ESTADO,
OB.CLAVE_CORTE,
NVL(OB.CANAL,'#') AS CANAL,
L.PK_LANZAMIENTO,
LPAD(OB.ID_ENTRADA,18,'0') AS SKU,
(TO_CHAR(TO_DATE(OB.FEHCA_INI_REAL),'YYYY-MM-DD')) AS PIR,
(TO_CHAR(TO_DATE(OB.FECHA_FIN_REAL),'YYYY-MM-DD')) AS PIF,
OB.MES,
OB.CF,
OB.CFC,
OB.CU,
OB.PIEZAS,
OB.GENERICO_1,
OB.GENERICO_2,
OB.GENERICO_3
FROM MKT_ST_LZ_OBJETIVO OB, MKT_GEOGRAFIA G, MKT_LZ_DIM_LANZAMIENTO L
WHERE NVL(G.REGION_ZONA,'#')=NVL(OB.CLAVE_CORTE,'#')
AND NVL(L.GV_CONSECUTIVO,'#')=NVL(OB.CONSECUTIVO,'#')
AND NVL(L.GV_NOMBRE_LANZAMIENTO,'#')=NVL(OB.NOMBRE_LANZAMIENTO,'#')
AND NVL(L.GV_CLAVE_LANZAMIENTO,'#')=NVL(OB.CLAVE_LANZAMIENTO,'#')
AND OB.CORTE='REGION'
AND OB.TIPO_ENTRADA='SKU');

COMMIT;

-- TABLA TEMPORAL DE LLAVES DUPLICADAS PARA INSERCION DE AGREGADO

CREATE TABLE MKT_LZ_COB_ETL_AGG1 AS(
SELECT FK_PERIODO_INICIO,
  FK_PERIODO_FIN,
  FK_ESTADO,
  FK_REGION,
  FK_CANAL_RM1,
  FK_LANZAMIENTO,
  FK_SKU,
  MV_PERIODO_INICIO_R,
  MV_PERIODO_FIN_R,
  MV_MES
 FROM MKT_LZ_FACT_OBJ_SKU_VT 
  MINUS
SELECT FK_PERIODO_INICIO,
  FK_PERIODO_FIN,
  FK_ESTADO,
  FK_REGION,
  FK_CANAL_RM1,
  FK_LANZAMIENTO,
  FK_SKU,
  MV_PERIODO_INICIO_R,
  MV_PERIODO_FIN_R,
  MV_MES
 FROM MKT_LZ_AGG_OBJ_SKU_VT_REG) ;

 COMMIT;
 
 
 
 -- COMPARANDO E INSERTANDO REGISTROS 
INSERT INTO MKT_LZ_AGG_OBJ_SKU_VT_REG(
 SELECT A.FK_PERIODO_INICIO,
  A.FK_PERIODO_FIN,
  A.FK_ESTADO,
  A.FK_REGION,
  A.FK_CANAL_RM1,
  A.FK_LANZAMIENTO,
  A.FK_SKU,
  A.MV_PERIODO_INICIO_R,
  A.MV_PERIODO_FIN_R,
  A.MV_MES,
  SUM(A.MD_CF),
  SUM(A.MD_CFC),
  SUM(A.MD_CU),
  SUM(A.MD_PIEZAS),
  SUM(A.MD_GEN1),
  SUM(A.MD_GEN2),
  SUM(A.MD_GEN3)
FROM MKT_LZ_FACT_OBJ_SKU_VT A, MKT_LZ_COB_ETL_AGG1 B
WHERE
NVL(A.FK_PERIODO_FIN,'#')=NVL(B.FK_PERIODO_FIN,'#')
AND NVL(A.FK_ESTADO,'#')=NVL(B.FK_ESTADO,'#')
AND NVL(A.FK_REGION,'#')=NVL(B.FK_REGION,'#')
AND NVL(A.FK_CANAL_RM1,'#')=NVL(B.FK_CANAL_RM1,'#')
AND NVL(A.FK_LANZAMIENTO,'#')=NVL(B.FK_LANZAMIENTO,'#')
AND NVL(A.FK_SKU,'#')=NVL(B.FK_SKU,'#')
AND NVL(A.MV_PERIODO_INICIO_R,'0')=NVL(B.MV_PERIODO_INICIO_R,'0')
AND NVL(A.MV_PERIODO_FIN_R,'0')=NVL(B.MV_PERIODO_FIN_R,'0')
AND NVL(A.MV_MES,'0')=NVL(B.MV_MES,'0')
group by
A.FK_PERIODO_FIN, A.FK_ESTADO, A.FK_REGION, A.FK_CANAL_RM1, A.FK_LANZAMIENTO, 
A.FK_SKU, A.MV_PERIODO_INICIO_R, A.MV_PERIODO_FIN_R, A.MV_MES, A.FK_PERIODO_INICIO);

DROP TABLE MKT_LZ_COB_ETL_AGG1;
COMMIT;
--SELECT * FROM MKT_LZ_AGG_OBJ_SKU_VT_REG;
-------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------


-- TABLA AGGREGADA A NIVEL REGION POR SKU


INSERT INTO MKT_LZ_AGG_OBJ_SKU_VT_EDO(
SELECT DISTINCT (TO_CHAR(OB.FECHA_INI_TOTAL,'YYYY-MM-DD')) AS PERIODO_INICIO,
(TO_CHAR(OB.FECHA_FIN_TOTAL,'YYYY-MM-DD')) AS PERIODO_FIN,
OB.CLAVE_CORTE,
OB.CANAL,
L.PK_LANZAMIENTO,
LPAD(OB.ID_ENTRADA,18,'0') AS SKU,
(TO_CHAR(OB.FEHCA_INI_REAL,'YYYY-MM-DD')) AS PIR,
(TO_CHAR(OB.FECHA_FIN_REAL,'YYYY-MM-DD')) AS PIF,
OB.MES,
OB.CF,
OB.CFC,
OB.CU,
OB.PIEZAS,
OB.GENERICO_1,
OB.GENERICO_2,
OB.GENERICO_3
FROM MKT_ST_LZ_OBJETIVO OB, MKT_GEOGRAFIA G, MKT_LZ_DIM_LANZAMIENTO L
WHERE NVL(G.ESTADO,'#')=NVL(OB.CLAVE_CORTE,'#')
AND NVL(L.GV_CONSECUTIVO,'#')=NVL(OB.CONSECUTIVO,'#')
AND NVL(L.GV_NOMBRE_LANZAMIENTO,'#')=NVL(OB.NOMBRE_LANZAMIENTO,'#')
AND NVL(L.GV_CLAVE_LANZAMIENTO,'#')=NVL(OB.CLAVE_LANZAMIENTO,'#')
AND OB.CORTE='ESTADO'
AND OB.TIPO_ENTRADA='SKU');

COMMIT;

-- TABLA TEMPORAL DE LLAVES DUPLICADAS PARA INSERCION DE AGREGADO

CREATE TABLE MKT_LZ_COB_ETL_AGG1 AS(
SELECT FK_PERIODO_INICIO,
  FK_PERIODO_FIN,
  FK_ESTADO,
  FK_CANAL_RM1,
  FK_LANZAMIENTO,
  FK_SKU,
  MV_PERIODO_INICIO_R,
  MV_PERIODO_FIN_R,
  MV_MES
 FROM MKT_LZ_AGG_OBJ_SKU_VT_REG 
  MINUS
SELECT FK_PERIODO_INICIO,
  FK_PERIODO_FIN,
  FK_ESTADO,
  FK_CANAL_RM1,
  FK_LANZAMIENTO,
  FK_SKU,
  MV_PERIODO_INICIO_R,
  MV_PERIODO_FIN_R,
  MV_MES
 FROM MKT_LZ_AGG_OBJ_SKU_VT_EDO) ;
 COMMIT;
 
 
 --HOLA
 -- COMPARANDO E INSERTANDO REGISTROS 
INSERT INTO MKT_LZ_AGG_OBJ_SKU_VT_EDO(
 SELECT A.FK_PERIODO_INICIO,
  A.FK_PERIODO_FIN,
  A.FK_ESTADO,
  A.FK_CANAL_RM1,
  A.FK_LANZAMIENTO,
  A.FK_SKU,
  A.MV_PERIODO_INICIO_R,
  A.MV_PERIODO_FIN_R,
  A.MV_MES,
  SUM(A.MD_CF),
  SUM(A.MD_CFC),
  SUM(A.MD_CU),
  SUM(A.MD_PIEZAS),
  SUM(A.MD_GEN1),
  SUM(A.MD_GEN2),
  SUM(A.MD_GEN3)
FROM MKT_LZ_AGG_OBJ_SKU_VT_REG A, MKT_LZ_COB_ETL_AGG1 B
WHERE
NVL(A.FK_PERIODO_FIN,'#')=NVL(B.FK_PERIODO_FIN,'#')
AND NVL(A.FK_ESTADO,'#')=NVL(B.FK_ESTADO,'#')
AND NVL(A.FK_CANAL_RM1,'#')=NVL(B.FK_CANAL_RM1,'#')
AND NVL(A.FK_LANZAMIENTO,'#')=NVL(B.FK_LANZAMIENTO,'#')
AND NVL(A.FK_SKU,'#')=NVL(B.FK_SKU,'#')
AND NVL(A.MV_PERIODO_INICIO_R,'0')=NVL(B.MV_PERIODO_INICIO_R,'0')
AND NVL(A.MV_PERIODO_FIN_R,'0')=NVL(B.MV_PERIODO_FIN_R,'0')
AND NVL(A.MV_MES,'0')=NVL(B.MV_MES,'0')
group by
A.FK_PERIODO_FIN, A.FK_ESTADO, A.FK_CANAL_RM1, A.FK_LANZAMIENTO, 
A.FK_SKU, A.MV_PERIODO_INICIO_R, A.MV_PERIODO_FIN_R, A.MV_MES, A.FK_PERIODO_INICIO);
DROP TABLE MKT_LZ_COB_ETL_AGG1;
COMMIT;




------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------
------------ TABLAS DE HECHOS PARA OBJETITOS PREVENTA-------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------




--TABLA DE HECHOS A CENTRO

INSERT INTO MKT_LZ_FACT_OBJ_PEP_VT(
SELECT DISTINCT (TO_CHAR(TO_DATE(OB.FECHA_INI_TOTAL),'YYYY-MM-DD')) AS PERIODO_INICIO,
(TO_CHAR(TO_DATE(OB.FECHA_FIN_TOTAL),'YYYY-MM-DD')) AS PERIODO_FIN,
G.ESTADO,
G.REGION_ZONA,
OB.CLAVE_CORTE,
OB.CANAL,
L.PK_LANZAMIENTO,
OB.ID_ENTRADA AS PEP,
(TO_CHAR(TO_DATE(OB.FEHCA_INI_REAL),'YYYY-MM-DD')) AS PIR,
(TO_CHAR(TO_DATE(OB.FECHA_FIN_REAL),'YYYY-MM-DD')) AS PIF,
OB.MES,
OB.CF,
OB.CFC,
OB.CU,
OB.PIEZAS,
OB.GENERICO_1,
OB.GENERICO_2,
OB.GENERICO_3
FROM MKT_ST_LZ_OBJETIVO OB, MKT_GEOGRAFIA G, MKT_LZ_DIM_LANZAMIENTO L
WHERE NVL(G.CENTRO,'#')=NVL(OB.CLAVE_CORTE,'#')
AND NVL(L.GV_CONSECUTIVO,'#')=NVL(OB.CONSECUTIVO,'#')
AND NVL(L.GV_NOMBRE_LANZAMIENTO,'#')=NVL(OB.NOMBRE_LANZAMIENTO,'#')
AND NVL(L.GV_CLAVE_LANZAMIENTO,'#')=NVL(OB.CLAVE_LANZAMIENTO,'#')
AND OB.CORTE='UNIDAD OPERATIVA'
AND OB.TIPO_ENTRADA='ID');

COMMIT;


-- TABLA AGGREGADA A NIVEL REGION POR SKU


INSERT INTO MKT_LZ_AGG_OBJ_PEP_VT_REG(
SELECT DISTINCT (TO_CHAR(TO_DATE(OB.FECHA_INI_TOTAL),'YYYY-MM-DD')) AS PERIODO_INICIO,
(TO_CHAR(TO_DATE(OB.FECHA_FIN_TOTAL),'YYYY-MM-DD')) AS PERIODO_FIN,
G.ESTADO,
OB.CLAVE_CORTE,
OB.CANAL,
L.PK_LANZAMIENTO,
OB.ID_ENTRADA AS PEP,
(TO_CHAR(TO_DATE(OB.FEHCA_INI_REAL),'YYYY-MM-DD')) AS PIR,
(TO_CHAR(TO_DATE(OB.FECHA_FIN_REAL),'YYYY-MM-DD')) AS PIF,
OB.MES,
OB.CF,
OB.CFC,
OB.CU,
OB.PIEZAS,
OB.GENERICO_1,
OB.GENERICO_2,
OB.GENERICO_3
FROM MKT_ST_LZ_OBJETIVO OB, MKT_GEOGRAFIA G, MKT_LZ_DIM_LANZAMIENTO L
WHERE NVL(G.REGION_ZONA,'#')=NVL(OB.CLAVE_CORTE,'#')
AND NVL(L.GV_CONSECUTIVO,'#')=NVL(OB.CONSECUTIVO,'#')
AND NVL(L.GV_NOMBRE_LANZAMIENTO,'#')=NVL(OB.NOMBRE_LANZAMIENTO,'#')
AND NVL(L.GV_CLAVE_LANZAMIENTO,'#')=NVL(OB.CLAVE_LANZAMIENTO,'#')
AND OB.CORTE='REGION'
AND OB.TIPO_ENTRADA='ID');

--DROP TABLE MKT_LZ_COB_ETL_AGG1;
COMMIT;

-- TABLA TEMPORAL DE LLAVES DUPLICADAS PARA INSERCION DE AGREGADO

CREATE TABLE MKT_LZ_COB_ETL_AGG1 AS(
SELECT FK_PERIODO_INICIO,
  FK_PERIODO_FIN,
  FK_ESTADO,
  FK_REGION,
  FK_CANAL_RM1,
  FK_LANZAMIENTO,
  FK_PEP,
  MV_PERIODO_INICIO_R,
  MV_PERIODO_FIN_R,
  MV_MES
 FROM MKT_LZ_FACT_OBJ_PEP_VT 
  MINUS
SELECT FK_PERIODO_INICIO,
  FK_PERIODO_FIN,
  FK_ESTADO,
  FK_REGION,
  FK_CANAL_RM1,
  FK_LANZAMIENTO,
  FK_PEP,
  MV_PERIODO_INICIO_R,
  MV_PERIODO_FIN_R,
  MV_MES
 FROM MKT_LZ_AGG_OBJ_PEP_VT_REG) ;
 COMMIT;
 
 
 
 -- COMPARANDO E INSERTANDO REGISTROS 
INSERT INTO MKT_LZ_AGG_OBJ_PEP_VT_REG(
 SELECT A.FK_PERIODO_INICIO,
  A.FK_PERIODO_FIN,
  A.FK_ESTADO,
  A.FK_REGION,
  A.FK_CANAL_RM1,
  A.FK_LANZAMIENTO,
  A.FK_PEP,
  A.MV_PERIODO_INICIO_R,
  A.MV_PERIODO_FIN_R,
  A.MV_MES,
  SUM(A.MD_CF),
  SUM(A.MD_CFC),
  SUM(A.MD_CU),
  SUM(A.MD_PIEZAS),
  SUM(A.MD_GEN1),
  SUM(A.MD_GEN2),
  SUM(A.MD_GEN3)
FROM MKT_LZ_FACT_OBJ_PEP_VT A, MKT_LZ_COB_ETL_AGG1 B
WHERE
NVL(A.FK_PERIODO_FIN,'#')=NVL(B.FK_PERIODO_FIN,'#')
AND NVL(A.FK_ESTADO,'#')=NVL(B.FK_ESTADO,'#')
AND NVL(A.FK_REGION,'#')=NVL(B.FK_REGION,'#')
AND NVL(A.FK_CANAL_RM1,'#')=NVL(B.FK_CANAL_RM1,'#')
AND NVL(A.FK_LANZAMIENTO,'#')=NVL(B.FK_LANZAMIENTO,'#')
AND NVL(A.FK_PEP,'#')=NVL(B.FK_PEP,'#')
AND NVL(A.MV_PERIODO_INICIO_R,'0')=NVL(B.MV_PERIODO_INICIO_R,'0')
AND NVL(A.MV_PERIODO_FIN_R,'0')=NVL(B.MV_PERIODO_FIN_R,'0')
AND NVL(A.MV_MES,'0')=NVL(B.MV_MES,'0')
group by
A.FK_PERIODO_FIN, A.FK_ESTADO, A.FK_REGION, A.FK_CANAL_RM1, A.FK_LANZAMIENTO, 
A.FK_PEP, A.MV_PERIODO_INICIO_R, A.MV_PERIODO_FIN_R, A.MV_MES, A.FK_PERIODO_INICIO);

DROP TABLE MKT_LZ_COB_ETL_AGG1;
COMMIT;
--SELECT * FROM MKT_LZ_AGG_OBJ_SKU_VT_REG;
-------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------


-- TABLA AGGREGADA A NIVEL REGION POR SKU


INSERT INTO MKT_LZ_AGG_OBJ_PEP_VT_EDO(
SELECT DISTINCT (TO_CHAR(TO_DATE(OB.FECHA_INI_TOTAL),'YYYY-MM-DD')) AS PERIODO_INICIO,
(TO_CHAR(TO_DATE(OB.FECHA_FIN_TOTAL),'YYYY-MM-DD')) AS PERIODO_FIN,
OB.CLAVE_CORTE,
OB.CANAL,
L.PK_LANZAMIENTO,
OB.ID_ENTRADA AS PEP,
(TO_CHAR(TO_DATE(OB.FEHCA_INI_REAL),'YYYY-MM-DD')) AS PIR,
(TO_CHAR(TO_DATE(OB.FECHA_FIN_REAL),'YYYY-MM-DD')) AS PIF,
OB.MES,
OB.CF,
OB.CFC,
OB.CU,
OB.PIEZAS,
OB.GENERICO_1,
OB.GENERICO_2,
OB.GENERICO_3
FROM MKT_ST_LZ_OBJETIVO OB, MKT_GEOGRAFIA G, MKT_LZ_DIM_LANZAMIENTO L
WHERE NVL(G.ESTADO,'#')=NVL(OB.CLAVE_CORTE,'#')
AND NVL(L.GV_CONSECUTIVO,'#')=NVL(OB.CONSECUTIVO,'#')
AND NVL(L.GV_NOMBRE_LANZAMIENTO,'#')=NVL(OB.NOMBRE_LANZAMIENTO,'#')
AND NVL(L.GV_CLAVE_LANZAMIENTO,'#')=NVL(OB.CLAVE_LANZAMIENTO,'#')
AND OB.CORTE='ESTADO'
AND OB.TIPO_ENTRADA='ID');

COMMIT;

-- TABLA TEMPORAL DE LLAVES DUPLICADAS PARA INSERCION DE AGREGADO
------------------------------- REVISAR
CREATE TABLE MKT_LZ_COB_ETL_AGG1 AS(
SELECT FK_PERIODO_INICIO,
  FK_PERIODO_FIN,
  FK_ESTADO,
  FK_CANAL_RM1,
  FK_LANZAMIENTO,
  FK_PEP,
  MV_PERIODO_INICIO_R,
  MV_PERIODO_FIN_R,
  MV_MES
 FROM MKT_LZ_AGG_OBJ_PEP_VT_REG 
  MINUS
SELECT FK_PERIODO_INICIO,
  FK_PERIODO_FIN,
  FK_ESTADO,
  FK_CANAL_RM1,
  FK_LANZAMIENTO,
  FK_PEP,
  MV_PERIODO_INICIO_R,
  MV_PERIODO_FIN_R,
  MV_MES
 FROM MKT_LZ_AGG_OBJ_PEP_VT_EDO) ;
 COMMIT;
 
 
--DESC MKT_LZ_AGG_OBJ_SKU_VT_REG;
 
 -- COMPARANDO E INSERTANDO REGISTROS 
INSERT INTO MKT_LZ_AGG_OBJ_PEP_VT_EDO(
 SELECT A.FK_PERIODO_INICIO,
  A.FK_PERIODO_FIN,
  A.FK_ESTADO,
  A.FK_CANAL_RM1,
  A.FK_LANZAMIENTO,
  A.FK_PEP,
  A.MV_PERIODO_INICIO_R,
  A.MV_PERIODO_FIN_R,
  A.MV_MES,
  SUM(A.MD_CF),
  SUM(A.MD_CFC),
  SUM(A.MD_CU),
  SUM(A.MD_PIEZAS),
  SUM(A.MD_GEN1),
  SUM(A.MD_GEN2),
  SUM(A.MD_GEN3)
FROM MKT_LZ_AGG_OBJ_PEP_VT_REG A, MKT_LZ_COB_ETL_AGG1 B
WHERE
NVL(A.FK_PERIODO_FIN,'#')=NVL(B.FK_PERIODO_FIN,'#')
AND NVL(A.FK_ESTADO,'#')=NVL(B.FK_ESTADO,'#')
AND NVL(A.FK_CANAL_RM1,'#')=NVL(B.FK_CANAL_RM1,'#')
AND NVL(A.FK_LANZAMIENTO,'#')=NVL(B.FK_LANZAMIENTO,'#')
AND NVL(A.FK_PEP,'#')=NVL(B.FK_PEP,'#')
AND NVL(A.MV_PERIODO_INICIO_R,'0')=NVL(B.MV_PERIODO_INICIO_R,'0')
AND NVL(A.MV_PERIODO_FIN_R,'0')=NVL(B.MV_PERIODO_FIN_R,'0')
AND NVL(A.MV_MES,'0')=NVL(B.MV_MES,'0')
group by
A.FK_PERIODO_FIN, A.FK_ESTADO, A.FK_CANAL_RM1, A.FK_LANZAMIENTO, 
A.FK_PEP, A.MV_PERIODO_INICIO_R, A.MV_PERIODO_FIN_R, A.MV_MES, A.FK_PERIODO_INICIO);

COMMIT;


---------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------TABLA DE HECHOS FILTRO PEP_VT----------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------


--SELECT * FROM MKT_ST_LZ_FILTRO_ID_CLIENTE;
---SELECT * FROM MKT_LZ_DIM_LANZAMIENTO;
--SELECT * FROM MKT_LZ_DIM_TIPO_PREVENTA;


--SELECT 

INSERT INTO MKT_LZ_FLITRO_PEP_VT
SELECT
TO_CHAR(A.FECHA_INICIO,'YYYY-MM-DD')AS FK_PERIODO_INICIO,
TO_CHAR(A.FECHA_FIN,'YYYY-MM-DD')AS FK_PERIODO_FIN,
NVL(A.GRUPO_CANAL,'#') AS CANAL_RM1,
NVL(A.TIPO_CANAL,'#'),
L.PK_LANZAMIENTO AS FK_LANZAMIENTO,
(SELECT PV.PK_PREVENTA FROM MKT_LZ_DIM_TIPO_PREVENTA PV WHERE ROWNUM<2 AND PV.GV_PREVENTA=A.TIPO_PREVENTA) AS FK_PREVENTA,
A.ID_ENTRADA AS FK_PEP,
NVL(LPAD(A.CLIENTES,10,0),'#') AS FK_CLIENTE,
A.OBJETIVO AS MD_OBJETIVO
FROM MKT_ST_LZ_FILTRO_ID_CLIENTE A, MKT_LZ_DIM_LANZAMIENTO L
WHERE NVL(L.GV_CONSECUTIVO,'#')=NVL(A.CONSECUTIVO,'#') AND
NVL(L.GV_NOMBRE_LANZAMIENTO,'#')=NVL(A.NOMBRE_LANZAMIENTO,'#') AND
NVL(L.GV_CLAVE_LANZAMIENTO,'#')=NVL(A.CLAVE_LANZAMIENTO,'#') AND
A.TIPO_ENTRADA='ID'
;

--select * from MKT_ST_LZ_FILTRO_ID_CLIENTE;
--SELECT PV.* FROM MKT_LZ_DIM_TIPO_PREVENTA PV WHERE ROWNUM<2;
DROP TABLE MKT_LZ_COB_ETL_AGG1;
--SELECT * FROM MKT_ST_LZ_FILTRO_ID_CLIENTE;
COMMIT;


/****** REGISTRO DE CONTROL ******/
UPDATE  TBLLOGREGEST SET ID_ESTATUS=2 WHERE ID_REGISTRO=(SELECT MAX(ID_REGISTRO) FROM TBLLOGREGIST WHERE ID_SCRIPT=(SELECT ID_SCRIPT FROM TBLLOGSCRIPT WHERE DESC_SCRIPT='LANZAMIENTOS_PROCESO'));
COMMIT;

UPDATE TBLLOGREGIST SET FEC_FIN=current_date WHERE ID_REGISTRO=(SELECT MAX(ID_REGISTRO) FROM TBLLOGREGIST WHERE ID_SCRIPT=(SELECT ID_SCRIPT FROM TBLLOGSCRIPT WHERE DESC_SCRIPT='LANZAMIENTOS_PROCESO'));
COMMIT;


