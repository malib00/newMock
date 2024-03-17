package com.example.newMock.Controller;


import com.example.newMock.Model.RequestDTO;
import com.example.newMock.Model.ResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

@RestController
public class MainController {

	private Logger log = LoggerFactory.getLogger(MainController.class);
	ObjectMapper mapper = new ObjectMapper();

	@PostMapping(value = "/info/postBalances", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Object postBalances(@RequestBody RequestDTO requestDTO) {
		try {
			ResponseDTO responseDTO = new ResponseDTO();

			String clientId = requestDTO.getClientId();
			char firstCharClientId = clientId.charAt(0);

			DecimalFormat decimalFormat = new DecimalFormat("0.00");
			decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
			String maxLimit;
			String balance;
			BigDecimal randomFromDouble = new BigDecimal(Math.random());

			if (firstCharClientId == '8') {
				BigDecimal maxLimitDecimal = new BigDecimal(2000);
				BigDecimal randomBalance = randomFromDouble.multiply(maxLimitDecimal);
				responseDTO.setCurrency("US");
				maxLimit = decimalFormat.format(maxLimitDecimal);
				balance = decimalFormat.format(randomBalance);
			} else if (firstCharClientId == '9') {
				BigDecimal maxLimitDecimal = new BigDecimal(1000);
				BigDecimal randomBalance = randomFromDouble.multiply(maxLimitDecimal);
				responseDTO.setCurrency("EU");
				maxLimit = decimalFormat.format(maxLimitDecimal);
				balance = decimalFormat.format(randomBalance);
			} else {
				BigDecimal maxLimitDecimal = new BigDecimal(10000);
				BigDecimal randomBalance = randomFromDouble.multiply(maxLimitDecimal);
				responseDTO.setCurrency("RUB");
				maxLimit = decimalFormat.format(maxLimitDecimal);
				balance = decimalFormat.format(randomBalance);
			}

			responseDTO.setRqUID(requestDTO.getRqUID());
			responseDTO.setClientId(clientId);
			responseDTO.setAccount(requestDTO.getAccount());
			responseDTO.setBalance(balance);
			responseDTO.setMaxLimit(maxLimit);

			log.info("RequestDTO received: \n" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestDTO));
			log.info("ResponseDTO prepared: \n" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseDTO));

			return responseDTO;
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
