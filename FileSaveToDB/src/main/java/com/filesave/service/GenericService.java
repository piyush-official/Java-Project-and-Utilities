package com.filesave.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.filesave.exception.FSException;
import com.filesave.model.ErrodModel;

/**
 * @author Piyush
 *
 */
@Service
public class GenericService {

	private static final String GENERIC_ERROR_CODE = "GEN0001";

	public ResponseEntity<?> handleException(Exception e) {
		e.printStackTrace();
		if (e instanceof FSException) {
			FSException ex = (FSException) e;
			return new ResponseEntity<>(new ErrodModel(ex.getErrorCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
		} else
			return new ResponseEntity<>(new ErrodModel(GENERIC_ERROR_CODE, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
