package com.filesave.common;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.filesave.model.ProductModel;

@Component
public  class StringToObjectConverter implements Converter<String, ProductModel> {

    private static Logger logger = LoggerFactory.getLogger(StringToObjectConverter.class);
    private static Marker marker = MarkerFactory.getMarker("String to Object Converter");
    
    @Autowired
    private ObjectMapper objectMapper;
    
	@Override
	public ProductModel convert(String source) {
		ProductModel response= new ProductModel();
		try {
			response=objectMapper.readValue(source, ProductModel.class);
		    return response;
		} catch (JsonMappingException jme) {
		    logger.error(marker, " error while Mapping Json", jme);
		} catch (JsonProcessingException jpe) {
		    logger.error(marker, " error while Processing Json", jpe);
		} 
		return response;
	}

}
