package com.filesave.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.filesave.assembler.ModelToEntityAssembler;
import com.filesave.common.Constants;
import com.filesave.entity.CategoryEntity;
import com.filesave.entity.ProductEntity;
import com.filesave.exception.FSException;
import com.filesave.model.ProductModel;
import com.filesave.repository.CategoryRepository;
import com.filesave.repository.ProductRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class ProductService extends GenericService {

	private static Logger logger = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	ProductRepository repository;

	@Autowired
	CategoryRepository categoryRepo;

	public void saveProduct(ProductModel requestData, MultipartFile file) throws FSException, IOException {
		logger.info("Save Product");
		logger.debug("requestData : ", requestData);

		String fileName = null;
		CategoryEntity category = null;
		if (file != null) {
			fileName = StringUtils.cleanPath(file.getOriginalFilename());
			logger.debug(" fileName : ", fileName);
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FSException(Constants.ErrorCode.FILE_INVALID, Constants.ErrorMessage.FILE_INVALID + fileName);
			}
		}
		if (requestData.getCategoryId() != null) {
			category = categoryRepo.getOne(requestData.getCategoryId());
		} else if (requestData.getCategoryName() != null && !requestData.getCategoryName().equalsIgnoreCase("")) {
			category = categoryRepo.findByCategoryName(requestData.getCategoryName());
		}

		// Dynamic Category Creation, if it does not exists

		if (category == null) {
			if (requestData.getCategoryName() != null && !requestData.getCategoryName().equalsIgnoreCase("")) {
				logger.info(" Creating new category : ", requestData.getCategoryName());
				CategoryEntity newCategory = new CategoryEntity();
				newCategory.setCategoryName(requestData.getCategoryName());
				newCategory.setStatus(1);
				category = categoryRepo.save(newCategory);
				logger.debug(" Created Category is ", category);
			}
		}

		ProductEntity product = repository
				.save(ModelToEntityAssembler.productAssembler(requestData, file, fileName, category));
		logger.info("Saved product successfully with the following values : ", product);
	}

	public List<ProductModel> getProductByFilters(String productName, String productColor, String categoryName) {

		List<ProductEntity> productList = new ArrayList<>();
		List<ProductModel> productModelList = new ArrayList<>();
		Long categoryId = null;
		CategoryEntity category = categoryRepo.findByCategoryName(categoryName);
		if (category != null) {
			categoryId = category.getId();
		}
		productList = repository.findProductByFilters(productName, productColor, categoryId);
		for (ProductEntity product : productList) {
			if (product != null) {
				ProductModel productModel = new ProductModel();
				if (product.getCategory() != null && product.getCategory().getId() != null) {
					productModel.setCategoryId(product.getCategory().getId());
				}
				if (product.getCategory() != null && product.getCategory().getCategoryName() != null
						&& !product.getCategory().getCategoryName().equalsIgnoreCase("")) {
					productModel.setCategoryName(product.getCategory().getCategoryName());
				}
				productModel.setProductId(product.getId());
				Optional.ofNullable(product.getProductName()).ifPresent(productModel::setProductName);
				Optional.ofNullable(product.getProductColor()).ifPresent(productModel::setProductColor);
				Optional.ofNullable(product.getProductPrice()).ifPresent(productModel::setProductPrice);
				Optional.ofNullable(product.getProductImage()).ifPresent(productModel::setProductImage);
				Optional.ofNullable(product.getContentType()).ifPresent(productModel::setContentType);
				Optional.ofNullable(product.getFileName()).ifPresent(productModel::setFileName);

				productModelList.add(productModel);

			}

		}
		return productModelList;

	}

}
