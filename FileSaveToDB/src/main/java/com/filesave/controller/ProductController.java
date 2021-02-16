/**
 * 
 */
package com.filesave.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.filesave.common.StringToObjectConverter;
import com.filesave.model.ProductModel;
import com.filesave.service.ProductService;




/**
 * @author Piyush
 *
 */
@RestController
@RequestMapping(value = "api/product", produces = "application/json")
public class ProductController {

	private static Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	ProductService productService;

	@Autowired
	StringToObjectConverter dataConverter;

	@PostMapping
	public ResponseEntity<?> saveProduct(@RequestParam(value = "request", required = true) String request,
			@RequestParam(name = "file", required = true) MultipartFile file) {
		logger.info("ProductController.saveProduct");

//		ProductModel response;
		try {
			ProductModel requestData = dataConverter.convert(request);
			productService.saveProduct(requestData, file);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("ProductController.saveProduct exception is ", e.getMessage());
			return productService.handleException(e);
		}
	}

	@GetMapping("/search-by-filters")
	public ResponseEntity<?> getProductByFilters(
			@RequestParam(value = "product_name", required = false) String productName,
			@RequestParam(name = "product_color", required = false) String productColor,
			@RequestParam(name = "category_name", required = false) String categoryName) {
		logger.info("ProductController.getProductByFilters");
		List<ProductModel> response;
		try {
			response = productService.getProductByFilters(productName, productColor, categoryName);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("ProductController.getProductByFilters exception is ", e.getMessage());
			return productService.handleException(e);
		}
	}

}
