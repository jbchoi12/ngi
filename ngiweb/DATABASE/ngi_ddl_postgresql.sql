CREATE TABLE TN_CHANGE_INFO (
	CHANGE_INFO_ID numeric(30,0) DEFAULT 0 NOT NULL, 
	REGISTER_ID character varying(15),
	CHANGE_CL char(2),
	PROCESS_STTUS_SE char(2),
	BSNS_DSTRC character varying(15),
	CHANGE_LNM_ADRES_CN character varying(100),
	CHANGE_LNM_ADRES_DETAIL_CN character varying(100),
	CHANGE_RN_ADRES_CN character varying(100),
	CHANGE_RN_ADRES_DETAIL_CN character varying(100),
	X_CRDNT_LO character varying(100),
	Y_CRDNT_LA character varying(100),
	REGIST_DE DATE,
	REGISTER_NM character varying(10),
	CHANGE_SJ character varying(30),
	CONSTRAINT TN_CHANGE_INFO_PK PRIMARY KEY (CHANGE_INFO_ID)
);

CREATE TABLE TN_CNTRWK_INFO (
	CHANGE_INFO_ID numeric(30,0) DEFAULT 0 NOT NULL, 
	ENGN_CODE char(18),
	CNTRWK_PNTTM character varying(50),
	CNTRWK_TMNL character varying(50),
	AR numeric,
	EXTN numeric,
	CHANGE_TRGET_NM character varying(30),
	TRGET_BFCHG_CN character varying(50),
	TRGET_AFTCH_CN character varying(50),
	STRWRK_DE DATE,
	COMPET_PREARNGE_DE DATE,
	LAST_COMPET_DE DATE,
	COMPET_DRW_FILE_STLE_TY char(2),
	CNTM character varying(20),
	RM character varying(100),
	PSITN_ENGN_NO character varying(10),
	PLAN_ENGN_NO character varying(10),
	MNG_ENGN_NO character varying(10),
	CHARGER_NM character varying(10),
	CHRG_DEPT_NM character varying(30),
	CHARGER_TLPHON_NO character varying(15),
	CHARGER_EMAIL character varying(30),
	ACQS_MTH_TY char(2),
	STTUS_TY char(2),
	CHANGE_TY char(2),
	CONSTRAINT TN_CNTRWK_INFO_PK PRIMARY KEY (CHANGE_INFO_ID),
	CONSTRAINT TN_CNTRWK_INFO_ibfk_1 FOREIGN KEY (CHANGE_INFO_ID) REFERENCES TN_CHANGE_INFO (CHANGE_INFO_ID)
);

CREATE TABLE TN_STTEMNT_INFO (
	CHANGE_INFO_ID numeric(30,0) DEFAULT 0 NOT NULL, 
	STTEMNT_CL char(2),
	TELNO character varying(15),
	EMAIL character varying(30),
	STTEMNT_CN character varying(2000),
	CRTFC_PASSWORD character varying(50),
	STTEMNT_TY char(2),
	BFCHG character varying(50),
	AFTCH character varying(50),
	CONSTRAINT TN_STTEMNT_INFO_PK PRIMARY KEY (CHANGE_INFO_ID),
	CONSTRAINT TN_STTEMNT_INFO_ibfk_1 FOREIGN KEY (CHANGE_INFO_ID) REFERENCES TN_CHANGE_INFO (CHANGE_INFO_ID)
);




CREATE TABLE TN_ATCHMNFL (
	ATCHMNFL_ID numeric(30,0) DEFAULT 0 NOT NULL,
	CHANGE_INFO_ID numeric(30,0),
	FILE_NM character varying(50),
	FILE_MG numeric,
	FLPTH_NM character varying(100),
	FILE_LOCK_ENNC_AT char(1),
	FILE_FOM_CODE_TY char(2),
	CONSTRAINT TN_ATCHMNFL_PK PRIMARY KEY (ATCHMNFL_ID),
	CONSTRAINT TN_ATCHMNFL_ibfk_1 FOREIGN KEY (CHANGE_INFO_ID) REFERENCES TN_CHANGE_INFO (CHANGE_INFO_ID)
);


CREATE TABLE TN_CHANGE_LC_POINT (
	LC_POINT_INFO_ID numeric(30,0) DEFAULT 0 NOT NULL,
	CHANGE_INFO_ID numeric(30,0),
	CONSTRAINT TN_CHANGE_LC_POINT_PK PRIMARY KEY (LC_POINT_INFO_ID),
	CONSTRAINT TN_CHANGE_LC_POINT_ibfk_1 FOREIGN KEY (CHANGE_INFO_ID) REFERENCES TN_CHANGE_INFO (CHANGE_INFO_ID)
);

CREATE TABLE TN_CHANGE_LC_LN (
	LC_LN_INFO_ID numeric(30,0) DEFAULT 0 NOT NULL,
	CHANGE_INFO_ID numeric(30,0),
	CONSTRAINT TN_CHANGE_LC_LN_PK PRIMARY KEY (LC_LN_INFO_ID),
	CONSTRAINT TN_CHANGE_LC_LN_ibfk_1 FOREIGN KEY (CHANGE_INFO_ID) REFERENCES TN_CHANGE_INFO (CHANGE_INFO_ID)
);

CREATE TABLE TN_CHANGE_LC_MYEON (
	LC_MYEON_INFO_ID numeric(30,0) DEFAULT 0 NOT NULL,
	CHANGE_INFO_ID numeric(30,0),
	CONSTRAINT TN_CHANGE_LC_MYEON_PK PRIMARY KEY (LC_MYEON_INFO_ID),
	CONSTRAINT TN_CHANGE_LC_MYEON_ibfk_1 FOREIGN KEY (CHANGE_INFO_ID) REFERENCES TN_CHANGE_INFO (CHANGE_INFO_ID)
);

insert into spatial_ref_sys values
('5179',
'EPSG',
'5179',
'PROJCS["ITRF_2000_UTM_K",GEOGCS["GCS_ITRF_2000",DATUM["D_ITRF_2000",SPHEROID["GRS_1980",6378137.0,298.257222101]],PRIMEM["Greenwich",0.0],UNIT["Degree",0.0174532925199433]],PROJECTION["Transverse_Mercator"],PARAMETER["False_Easting",1000000.0],PARAMETER["False_Northing",2000000.0],PARAMETER["Central_Meridian",127.5],PARAMETER["Scale_Factor",0.9996],PARAMETER["Latitude_Of_Origin",38.0],UNIT["Meter",1.0]]',
'+proj=tmerc +lat_0=38 +lon_0=127.5 +k=0.9996 +x_0=1000000 +y_0=2000000 +ellps=GRS80 +units=m +no_defs'
)

SELECT AddGeometryColumn ('tn_change_lc_point', 'location', 5179, 'Geometry', 2);
SELECT AddGeometryColumn ('tn_change_lc_ln', 'location', 5179, 'Geometry', 2);
SELECT AddGeometryColumn ('tn_change_lc_myeon', 'location', 5179, 'Geometry', 2);

CREATE INDEX tn_change_lc_point_gix ON tn_change_lc_point USING GIST (location); 
CREATE INDEX tn_change_lc_ln_gix ON tn_change_lc_ln USING GIST (location); 
CREATE INDEX tn_change_lc_myeon_gix ON tn_change_lc_myeon USING GIST (location); 
  



CREATE TABLE TN_CNTC_INFO (
	CNTC_INFO_ID numeric(30,0) DEFAULT 0 NOT NULL,
	CHARGER_NM character varying(30),
	SYS_NM character varying(30),
	TELNO character varying(15),
	EMAIL character varying(30),
	CNTC_TY char(2),
	APPLC_DOMN_NM character varying(50),
	USEPRPS character varying(50),
	RGSDE DATE,
	CRTFC_CODE_SE char(40),
	ISSU_DE DATE,
	CONFM_AT char(1),
	USER_ID character varying(15),
	HOST character varying(30),
	PORT character varying(5),
	PROTOCOL character varying(6),
	AUTH_KEY character varying(70),
	CONSTRAINT TN_CNTC_INFO_PK PRIMARY KEY (CNTC_INFO_ID)
);

CREATE TABLE TN_NTCN_SRVC (
	NTCN_SRVC_ID numeric(30,0) DEFAULT 0 NOT NULL,
	USER_NM character varying(30),
	TELNO character varying(15),
	EMAIL character varying(30),
	RGSDE DATE,
	CONSTRAINT TN_NTCN_SRVC_PK PRIMARY KEY (NTCN_SRVC_ID)
);

CREATE TABLE TH_SNDNG_LOG (
	SNDNG_LOG_ID numeric(30,0) DEFAULT 0 NOT NULL,
	NTCN_SRVC_ID numeric(30,0),
	SNDNG_SE char(1),
	SNDNG_DE DATE,
	SNDNG_CN_ID numeric(30,0),
	CONSTRAINT TH_SNDNG_LOG_PK PRIMARY KEY (SNDNG_LOG_ID),
	CONSTRAINT TH_SNDNG_LOG_ibfk_1 FOREIGN KEY (NTCN_SRVC_ID) REFERENCES TN_NTCN_SRVC (NTCN_SRVC_ID)
);


CREATE TABLE TH_CNTC_LOG (
	CNTC_LOG_ID numeric(30,0) DEFAULT 0 NOT NULL,
	CNTC_INFO_ID numeric(30,0),
	CALL_DE DATE,
	CALL_STTUS_TY char(1),
	ERROR_CN character varying(50),
	CONSTRAINT TH_CNTC_LOG_PK PRIMARY KEY (CNTC_LOG_ID),
	CONSTRAINT TH_CNTC_LOG_ibfk_1 FOREIGN KEY (CNTC_INFO_ID) REFERENCES TN_CNTC_INFO (CNTC_INFO_ID)
);


CREATE TABLE TH_SCORE_LOG (
	SCORE_ID numeric(30,0) DEFAULT 0 NOT NULL,
	USER_ID character varying(15),
	SCORE numeric(10,0),
	SCORE_OCCRRNC_DE DATE,
	SCORE_CODE_TY char(1),
	CONSTRAINT TH_SCORE_LOG_PK PRIMARY KEY (SCORE_ID)
);
