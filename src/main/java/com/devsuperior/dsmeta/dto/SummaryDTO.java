package com.devsuperior.dsmeta.dto;

public class SummaryDTO {
	
	private String sellerName;
	private Double total;
	
	public SummaryDTO(String sellerName, Double total) {
		this.sellerName = sellerName;
		this.total = total;
	}
	
	public SummaryDTO() {
	}
	
	public String getSellerName() {
		return sellerName;
	}
	
	public Double getTotal() {
		return total;
	}
	
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	
	public void setTotal(Double total) {
		this.total = total;
	}
}
