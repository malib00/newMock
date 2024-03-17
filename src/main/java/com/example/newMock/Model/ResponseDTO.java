package com.example.newMock.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseDTO {

	private String rqUID;
	private String clientId;
	private String account;
	private String currency;
	private String balance;
	private String maxLimit;

}
