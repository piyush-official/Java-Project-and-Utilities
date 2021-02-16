package com.filesave.assembler;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.filesave.entity.CategoryEntity;
import com.filesave.entity.ProductEntity;
import com.filesave.exception.FSException;
import com.filesave.model.ProductModel;



public final class ModelToEntityAssembler {
	
	private static Logger logger = LoggerFactory.getLogger(ModelToEntityAssembler.class);
	/**
	 * Instantiated Models to entity assembler.
	 */
	private ModelToEntityAssembler() {

	}

	public static ProductEntity productAssembler(ProductModel requestData, MultipartFile file, String fileName,
			CategoryEntity category) throws FSException, IOException {

		ProductEntity product = new ProductEntity();

		Optional.ofNullable(requestData.getProductColor()).ifPresent(product::setProductColor);
		Optional.ofNullable(requestData.getProductName()).ifPresent(product::setProductName);
		Optional.ofNullable(requestData.getProductPrice()).ifPresent(product::setProductPrice);
		product.setStatus(1);
		if (category != null) {
			product.setCategory(category);
		} else {
			logger.error("Category does not exist with the given category  ");
			throw new FSException("NSP0002", "Category does not exist with the given category");
		}
		try {
			if (file != null) {
				product.setFileName(fileName);
				product.setContentType(file.getContentType());
				try {
					product.setProductImage(file.getBytes());
				} catch (IOException e) {
					e.printStackTrace();
					throw new IOException(e);
				}
			}
		} catch (NullPointerException e) {
			throw new FSException("NSP0003", e.getMessage());
		}

		return product;

	}
}
