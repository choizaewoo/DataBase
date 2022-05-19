package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class fullStock {

   public static void main(String[] args) throws ClassNotFoundException, SQLException {
	   Class.forName("com.mysql.cj.jdbc.Driver");// class.forName()메소드를 호출하여 mysql에서 제공하는 driver 클래스를 
		//JVM method area에 로딩시킨다.
		Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.23.110:33060/kopoctc", "root", "kopo42");//데이터베이스 연결
		//localhost:3306와 스키마 이름을 지정한다.
	    //connection 객체를 만들어사용한다. 방법은 DriverManager클래스의 static 메소드인 getConnetion()메소드를 호출해서, mysql에 연결하기 위한
		//커넥션정보(url,user,passwd)를 입력한다. getConnection()메소드 수행 결과로 connection객체를 반환하는데 , 이객체를 통해 
		//쿼리를 날리는 statement를 작성할 수 있다. 
		Statement stmt = conn.createStatement();// SQL를 위한 객체 생성   
    //sql의 명령을 실행하게 만들어주는 Statement
      //단축코드, 일자, 시가,고가,저가,종가, 거래량, 거래대금 만으로 테이블 생성
      
      stmt.execute("create table fullStock("   //stocktable 라는 테이블을 생성한다 
              +"stnd_iscd varchar(50) , "+      //NOT_NULL 표준 종목코드
              "bsop_date int , "+      //NOT_NULL 주식 영업 일자
              "shrn_iscd varchar(50) , "+      //NOT_NULL 유가증권 단축 종목코드
              "stck_prpr int , "+      //주식 종가
              "stck_oprc int , "+      //주식 시가
              "stck_hgpr int , "+      //주식 최고가
              "stck_lwpr int , "+      //주식 최저가
              "prdy_vrss_sign varchar(50) , "+      //전일 대비 부호
              "prdy_vrss int , "+      //전일 대비
              "prdy_ctrt float , "+      //전일 대비율
              "prdy_vol long , "+      //전일 거래량
              "acml_vol long , "+      //누적 거래량
              "acml_tr_pbmn long , "+      //누적 거래 대금
              "askp1 int , "+      //매도호가1
              "bidp1 int , "+      //매수호가1
              "total_askp_rsqn long , "+      //총 매도호가 잔량
              "total_bidp_rsqn long , "+      //총 매수호가 잔량
              "seln_cntg_smtn long , "+      //매도 체결 합계
              "shnu_cntg_smtn long , "+      //매수 체결 합계
              "seln_tr_pbmn long , "+      //매도 거래 대금(누적)
              "shnu_tr_pbmn long , "+      //매수 거래 대금(누적)
              "seln_cntg_csnu int , "+      //매도 체결 건수
              "shnu_cntg_csnu int , "+      //매수 체결 건수
              "w52_hgpr varchar(50) , "+      //52주일 최고가
              "w52_lwpr varchar(50) , "+      //52주일 최저가
              "w52_hgpr_date varchar(50) , "+      //52주일 최고가 일자
              "w52_lwpr_date varchar(50) , "+      //52주일 최저가 일자
              "ovtm_untp_bsop_hour int , "+      //시간외 단일가 최종 시간
              "ovtm_untp_prpr int , "+      //시간외 단일가 현재가
              "ovtm_untp_prdy_vrss int , "+      //시간외 단일가 전일 대비
              "ovtm_untp_prdy_vrss_sign varchar(50) , "+      //시간외 단일가 전일 대비 부호
              "ovtm_untp_askp1 int , "+      //시간외 단일가 매도호가1
              "ovtm_untp_bidp1 int , "+      //시간외 단일가 매수호가1
              "ovtm_untp_vol long , "+      //시간외 단일가 거래량
              "ovtm_untp_tr_pbmn long , "+      //시간외 단일가 거래 대금
              "ovtm_untp_oprc int , "+      //시간외 단일가 시가
              "ovtm_untp_hgpr  int      , "+      //시간외 단일가 최고가 
              "ovtm_untp_lwpr int , "+      //시간외 단일가 최저가
              "mkob_otcp_vol long , "+      //장개시전 시간외종가 거래량
              "mkob_otcp_tr_pbmn long , "+      //장개시전 시간외종가 거래 대금
              "mkfa_otcp_vol long , "+      //장종료후 시간외종가 거래량
              "mkfa_otcp_tr_pbmn long , "+      //장종료후 시간외종가 거래 대금
              "mrkt_div_cls_code varchar(50) , "+      //시장 분류 구분 코드
              "pstc_dvdn_amt long , "+      //주당 배당 금액
              "lstn_stcn long , "+      //상장 주수
              "stck_sdpr int , "+      //주식 기준가
              "stck_fcam float , "+      //주식 액면가
              "wghn_avrg_stck_prc double , "+      //가중 평균 주식 가격
              "issu_limt_rate float , "+      //종목 한도 비율
              "frgn_limt_qty long , "+      //외국인 한도 수량
              "oder_able_qty long , "+      //주문 가능 수량
              "frgn_limt_exhs_cls_code varchar(50) , "+      //외국인 한도 소진 구분 코드
              "frgn_hldn_qty long , "+      //외국인 보유 수량
              "frgn_hldn_rate float , "+      //외국인 보유 비율
              "hts_frgn_ehrt float , "+      //HTS 외국인 소진율
              "itmt_last_nav float , "+      //장중 최종 NAV
              "prdy_last_nav float , "+      //전일 최종 NAV
              "trc_errt float , "+      //추적 오차율
              "dprt float , "+      //괴리율
              "ssts_cntg_qty long , "+      //공매도차입증권매도체결수량
              "ssts_tr_pbmn long , "+      //공매도차입증권매도거래대금
              "frgn_ntby_qty long , "+      //외국인 순매수
              "flng_cls_code varchar(50) , "+      //락구분 코드
              "prtt_rate float , "+      //분할 비율
              "acml_prtt_rate float , "+      //누적 분할 비율
              "stdv float , "+      //전체융자잔고비율
              "beta_cfcn float , "+      //베타 계수(90일)
              "crlt_cfcn  varchar(50)  , "+      //DEL 상관 계수
              "bull_beta  varchar(50)  , "+      //DEL 강세 계수
              "bear_beta  varchar(50)  , "+      //DEL 약세 계수
              "bull_dvtn   varchar(50)  , "+      //*  DEL 강세 편차
              "bear_dvtn   varchar(50)  , "+      //*  DEL 약세 편차
              "bull_crlt   varchar(50)  , "+      //*  DEL 강세 상관계수
              "bear_crlt   varchar(50)  , "+      //*  DEL 약세 상관계수
              "stck_mxpr  int , "+      //*  주식 상한가
              "stck_llam  int , "+      //*  주식 하한가
              "icic_cls_code varchar(50) , "+      //*  증자 구분 코드
              "itmt_vol  long , "+      //*  장중 거래량
              "itmt_tr_pbmn  long , "+      ///*  장중 거래대금
              "fcam_mod_cls_code varchar(50) , "+      //액면가 변경 구분 코드
              "revl_issu_reas_code varchar(50)     , "+      //재평가 종목 사유 코드
              "orgn_ntby_qty long , "+      //기관계 순매수
              "adj_prpr int , "+      //수정주가 (교보:fnguide때문에 추가)
              "fn_oprc int , "+      //주식 시가
              "fn_hgpr int , "+      //주식 최고가
              "fn_lwpr int , "+      //주식 최저가
              "fn_prpr int , "+      //주식 종가
              "fn_acml_vol long , "+      //누적 거래량
              "fn_acml_tr_pbmn long , "+      //누적 거래 대금
              "fn_prtt_rate float , "+      //분할 비율
              "fn_flng_cls_code varchar(50) , "+      //락구분 코드
              "buyin_nor_prpr  varchar(50) , "+      //Buy-in 일반 체결가///////////////////
              "buyin_nor_prdy_vrss varchar(50) , "+      //Buy-in 일반 종가 대비
              "buyin_nor_vol varchar(50) , "+      //Buy-in 일반 체결량
              "buyin_nor_tr_pbmn varchar(50) , "+      //Buy-in 일반 체결 대금
              "buyin_tod_prpr varchar(50) , "+      //Buy-in 당일 체결가
              "buyin_tod_prdy_vrss varchar(50) , "+      //Buy-in 당일 종가 대비
              "buyin_tod_vol varchar(50) , "+      //Buy-in 당일 체결량
              "buyin_tod_tr_pbmn varchar(50) , "+      //Buy-in 당일 체결 대금
              "constraint stocktable_PK primary key(shrn_iscd, bsop_date))" +
              "DEFAULT CHARSET=   utf8;"
              );   //설치시설구분

      
      stmt.close(); //Statement 종료
      conn.close(); //Connection 종료
   }

}

