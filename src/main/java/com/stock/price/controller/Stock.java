package com.stock.price.controller;

import java.math.BigDecimal;


public class Stock{
	
    String symbol;
    String name;
    BigDecimal currentPrice;
    long lastUpdated;
	
    public Stock(){
    	
    }
    
    public Stock(String symbol, String name, BigDecimal currentPrice,
			long lastUpdated) {
		super();
		this.symbol = symbol;
		this.name = name;
		this.currentPrice = currentPrice;
		this.lastUpdated = lastUpdated;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
	}

	public long getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(long lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Override
	public String toString() {
		return "Stock [symbol=" + symbol + ", name=" + name + ", currentPrice="
				+ currentPrice + ", lastUpdated=" + lastUpdated + "]";
	}
    
    
    
}