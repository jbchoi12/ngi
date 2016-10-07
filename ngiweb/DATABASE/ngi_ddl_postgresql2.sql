CREATE TABLE ngiweb.tn_change_info
(
  change_info_id numeric(30,0) NOT NULL DEFAULT 0, -- 변동 정보 아이디
  register_id character varying(15), -- 등록자 아이디
  change_cl character(2), -- 변동 분류
  process_sttus_se character(2), -- 처리 상태
  bsns_dstrc character varying(15), -- 사업 지구
  change_lnm_adres_cn character varying(100), -- 변동 지번 주소
  change_lnm_adres_detail_cn character varying(100), -- 변동 지번 주소 상세
  change_rn_adres_cn character varying(100), -- 변동 도로명 주소
  change_rn_adres_detail_cn character varying(100), -- 변동 도로명 주소 상세
  x_crdnt_lo character varying(100), -- X 좌표
  y_crdnt_la character varying(100), -- Y 좌표
  regist_de date, -- 등록 일자
  register_nm character varying(10), -- 등록자 명
  change_sj character varying(30), -- 변동 제목
  CONSTRAINT tn_change_info_pk PRIMARY KEY (change_info_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ngiweb.tn_change_info
  OWNER TO postgres;
COMMENT ON TABLE ngiweb.tn_change_info
  IS '변동정보';
COMMENT ON COLUMN ngiweb.tn_change_info.change_info_id IS '변동 정보 아이디';
COMMENT ON COLUMN ngiweb.tn_change_info.register_id IS '등록자 아이디';
COMMENT ON COLUMN ngiweb.tn_change_info.change_cl IS '변동 분류';
COMMENT ON COLUMN ngiweb.tn_change_info.process_sttus_se IS '처리 상태';
COMMENT ON COLUMN ngiweb.tn_change_info.bsns_dstrc IS '사업 지구';
COMMENT ON COLUMN ngiweb.tn_change_info.change_lnm_adres_cn IS '변동 지번 주소';
COMMENT ON COLUMN ngiweb.tn_change_info.change_lnm_adres_detail_cn IS '변동 지번 주소 상세';
COMMENT ON COLUMN ngiweb.tn_change_info.change_rn_adres_cn IS '변동 도로명 주소';
COMMENT ON COLUMN ngiweb.tn_change_info.change_rn_adres_detail_cn IS '변동 도로명 주소 상세';
COMMENT ON COLUMN ngiweb.tn_change_info.x_crdnt_lo IS 'X 좌표';
COMMENT ON COLUMN ngiweb.tn_change_info.y_crdnt_la IS 'Y 좌표';
COMMENT ON COLUMN ngiweb.tn_change_info.regist_de IS '등록 일자';
COMMENT ON COLUMN ngiweb.tn_change_info.register_nm IS '등록자 명';
COMMENT ON COLUMN ngiweb.tn_change_info.change_sj IS '변동 제목';



CREATE TABLE ngiweb.tn_cntrwk_info
(
  change_info_id numeric(30,0) NOT NULL DEFAULT 0, -- 변동 정보 아이디
  engn_code character(18), -- 기관 코드
  cntrwk_pnttm character varying(50), -- 공사 시점
  cntrwk_tmnl character varying(50), -- 공사 종점
  ar numeric, -- 면적
  extn numeric, -- 연장
  change_trget_nm character varying(30), -- 변경 대상
  trget_bfchg_cn character varying(50), -- 대상 변경전
  trget_aftch_cn character varying(50), -- 대상 변경후
  strwrk_de date, -- 착공 일자
  compet_prearnge_de date, -- 완공 예정 일자
  last_compet_de date, -- 최종 완공 일자
  compet_drw_file_stle_ty character(2), -- 준공 도면 파일 형태
  cntm character varying(20), -- 좌표계
  rm character varying(100), -- 비고
  psitn_engn_no character varying(10), -- 소속 기관
  plan_engn_no character varying(10), -- 계획 기관
  mng_engn_no character varying(10), -- 감독 기관
  charger_nm character varying(10), -- 담당자 명
  chrg_dept_nm character varying(30), -- 담당 부서
  charger_tlphon_no character varying(15), -- 담당자 전화 번호
  charger_email character varying(30), -- 담당자 이메일
  acqs_mth_ty character(2), -- 취득 방법
  sttus_ty character(2), -- 상태
  change_ty character(2), -- 변동 유형
  CONSTRAINT tn_cntrwk_info_pk PRIMARY KEY (change_info_id),
  CONSTRAINT tn_cntrwk_info_ibfk_1 FOREIGN KEY (change_info_id)
      REFERENCES ngiweb.tn_change_info (change_info_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ngiweb.tn_cntrwk_info
  OWNER TO postgres;
COMMENT ON TABLE ngiweb.tn_cntrwk_info
  IS '공사 정보';
COMMENT ON COLUMN ngiweb.tn_cntrwk_info.change_info_id IS '변동 정보 아이디';
COMMENT ON COLUMN ngiweb.tn_cntrwk_info.engn_code IS '기관 코드';
COMMENT ON COLUMN ngiweb.tn_cntrwk_info.cntrwk_pnttm IS '공사 시점';
COMMENT ON COLUMN ngiweb.tn_cntrwk_info.cntrwk_tmnl IS '공사 종점';
COMMENT ON COLUMN ngiweb.tn_cntrwk_info.ar IS '면적';
COMMENT ON COLUMN ngiweb.tn_cntrwk_info.extn IS '연장';
COMMENT ON COLUMN ngiweb.tn_cntrwk_info.change_trget_nm IS '변경 대상';
COMMENT ON COLUMN ngiweb.tn_cntrwk_info.trget_bfchg_cn IS '대상 변경전';
COMMENT ON COLUMN ngiweb.tn_cntrwk_info.trget_aftch_cn IS '대상 변경후';
COMMENT ON COLUMN ngiweb.tn_cntrwk_info.strwrk_de IS '착공 일자';
COMMENT ON COLUMN ngiweb.tn_cntrwk_info.compet_prearnge_de IS '완공 예정 일자';
COMMENT ON COLUMN ngiweb.tn_cntrwk_info.last_compet_de IS '최종 완공 일자';
COMMENT ON COLUMN ngiweb.tn_cntrwk_info.compet_drw_file_stle_ty IS '준공 도면 파일 형태';
COMMENT ON COLUMN ngiweb.tn_cntrwk_info.cntm IS '좌표계';
COMMENT ON COLUMN ngiweb.tn_cntrwk_info.rm IS '비고';
COMMENT ON COLUMN ngiweb.tn_cntrwk_info.psitn_engn_no IS '소속 기관';
COMMENT ON COLUMN ngiweb.tn_cntrwk_info.plan_engn_no IS '계획 기관';
COMMENT ON COLUMN ngiweb.tn_cntrwk_info.mng_engn_no IS '감독 기관';
COMMENT ON COLUMN ngiweb.tn_cntrwk_info.charger_nm IS '담당자 명';
COMMENT ON COLUMN ngiweb.tn_cntrwk_info.chrg_dept_nm IS '담당 부서';
COMMENT ON COLUMN ngiweb.tn_cntrwk_info.charger_tlphon_no IS '담당자 전화 번호';
COMMENT ON COLUMN ngiweb.tn_cntrwk_info.charger_email IS '담당자 이메일';
COMMENT ON COLUMN ngiweb.tn_cntrwk_info.acqs_mth_ty IS '취득 방법';
COMMENT ON COLUMN ngiweb.tn_cntrwk_info.sttus_ty IS '상태';
COMMENT ON COLUMN ngiweb.tn_cntrwk_info.change_ty IS '변동 유형';




CREATE TABLE ngiweb.tn_sttemnt_info
(
  change_info_id numeric(30,0) NOT NULL DEFAULT 0, -- 변동 정보 아이디
  sttemnt_cl character(2), -- 신고 분류
  telno character varying(15), -- 전화번호
  email character varying(30), -- 이메일
  sttemnt_cn character varying(2000), -- 신고 내용
  crtfc_password character varying(50), -- 인증 비밀번호
  sttemnt_ty character(2), -- 신고 유형
  bfchg character varying(50), -- 변경전
  aftch character varying(50), -- 변경후
  CONSTRAINT tn_sttemnt_info_pk PRIMARY KEY (change_info_id),
  CONSTRAINT tn_sttemnt_info_ibfk_1 FOREIGN KEY (change_info_id)
      REFERENCES ngiweb.tn_change_info (change_info_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ngiweb.tn_sttemnt_info
  OWNER TO postgres;
COMMENT ON TABLE ngiweb.tn_sttemnt_info
  IS '신고 정보';
COMMENT ON COLUMN ngiweb.tn_sttemnt_info.change_info_id IS '변동 정보 아이디';
COMMENT ON COLUMN ngiweb.tn_sttemnt_info.sttemnt_cl IS '신고 분류';
COMMENT ON COLUMN ngiweb.tn_sttemnt_info.telno IS '전화번호';
COMMENT ON COLUMN ngiweb.tn_sttemnt_info.email IS '이메일';
COMMENT ON COLUMN ngiweb.tn_sttemnt_info.sttemnt_cn IS '신고 내용';
COMMENT ON COLUMN ngiweb.tn_sttemnt_info.crtfc_password IS '인증 비밀번호';
COMMENT ON COLUMN ngiweb.tn_sttemnt_info.sttemnt_ty IS '신고 유형';
COMMENT ON COLUMN ngiweb.tn_sttemnt_info.bfchg IS '변경전';
COMMENT ON COLUMN ngiweb.tn_sttemnt_info.aftch IS '변경후';








