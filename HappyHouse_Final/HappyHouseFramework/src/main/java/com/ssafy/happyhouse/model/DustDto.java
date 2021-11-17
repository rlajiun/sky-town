package com.ssafy.happyhouse.model;

public class DustDto {
	private String MSRSTE_NM;
	private String PM10;
	private String PM25;
	private String O3;
	private String NO2;
	private String IDEX_NM;
	
	public DustDto() {
		// TODO Auto-generated constructor stub
	}

	public DustDto(String mSRSTE_NM, String pM10, String pM25, String o3, String nO2, String iDEX_NM) {
		super();
		MSRSTE_NM = mSRSTE_NM;
		PM10 = pM10;
		PM25 = pM25;
		O3 = o3;
		NO2 = nO2;
		IDEX_NM = iDEX_NM;
	}
	
	public String getMSRSTE_NM() {
		return MSRSTE_NM;
	}

	public void setMSRSTE_NM(String mSRSTE_NM) {
		MSRSTE_NM = mSRSTE_NM;
	}

	public String getPM10() {
		return PM10;
	}

	public void setPM10(String pM10) {
		PM10 = pM10;
	}

	public String getPM25() {
		return PM25;
	}

	public void setPM25(String pM25) {
		PM25 = pM25;
	}

	public String getO3() {
		return O3;
	}

	public void setO3(String o3) {
		O3 = o3;
	}

	public String getNO2() {
		return NO2;
	}

	public void setNO2(String nO2) {
		NO2 = nO2;
	}

	public String getIDEX_NM() {
		return IDEX_NM;
	}

	public void setIDEX_NM(String iDEX_NM) {
		IDEX_NM = iDEX_NM;
	}

	@Override
	public String toString() {
		return "DustDto [MSRSTE_NM=" + MSRSTE_NM + ", PM10=" + PM10 + ", PM25=" + PM25 + ", O3=" + O3 + ", NO2=" + NO2
				+ ", IDEX_NM=" + IDEX_NM + "]";
	}
	
	

}
