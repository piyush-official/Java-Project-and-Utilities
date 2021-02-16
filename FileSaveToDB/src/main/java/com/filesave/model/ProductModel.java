package com.filesave.model;

import java.util.Arrays;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
@JsonInclude(Include.NON_NULL)
public class ProductModel {

	@JsonProperty("product_id")
	private Long productId;

	@JsonProperty("product_name")
	private String productName;

	@JsonProperty("product_color")
	private String productColor;

	@JsonProperty("product_price")
	private String productPrice;

	@JsonProperty("category_id")
	private Long categoryId;

	@JsonProperty("category_name")
	private String categoryName;
	
	@JsonProperty("content_type")
	private String contentType;

	@JsonProperty("file_name")
	private String fileName;
	
	@JsonProperty("product_image")
	private byte[] productImage;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public byte[] getProductImage() {
		return productImage;
	}

	public void setProductImage(byte[] productImage) {
		this.productImage = productImage;
	}

	public String getProductColor() {
		return productColor;
	}

	public void setProductColor(String productColor) {
		this.productColor = productColor;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "ProductModel [productId=" + productId + ", productName=" + productName + ", productImage="
				+ Arrays.toString(productImage) + ", productColor=" + productColor + ", productPrice=" + productPrice
				+ ", categoryId=" + categoryId + ", categoryName=" + categoryName + ", contentType=" + contentType
				+ ", fileName=" + fileName + "]";
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
