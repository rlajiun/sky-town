package com.ssafy.happyhouse.email.model.service;

import org.springframework.messaging.handler.annotation.MessageMapping;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@MessageMapping
@NoArgsConstructor
public class AptDetail {
	private String aptCode;
	private String aptAddr;
	private String codeSaleNm;
	private String codeHeatNm;
	private String aptTarea;
	private String aptTel;
	private String aptFax;
	private String welfareFacility;
	private String aptdWtimebus;
	private String subwayLine;
	private String aptdWtimesub;
	private String convenientFacility;
	private String educationFacility;
}
