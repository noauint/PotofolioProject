package com.flyingone.custome_rise.dto;

import java.io.Serializable;

public class Goods implements Serializable{
	//商品ID
	private String goodsId;
	
	//商品名
	private String goodsName;
	
	//商品単価
	private int goodsPrice;
	
	//商品原価
	private int goodsCost;
	
	public Goods() {
		
	}
	
	public Goods(String goodsId, String goodsName, int goodsPrice, int goodsCost) {
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodsPrice = goodsPrice;
		this.goodsCost = goodsCost;
	}
	
	/*
	 * 商品IDゲッター
	 */
	public String getGoodsId() {
		return goodsId;
	}
	
	/*
	 * 商品IDセッター
	 */
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	/*
	 * 商品名ゲッター
	 */
	public String getGoodsName() {
		return goodsName;
	}

	/*
	 * 商品名セッター
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	/*
	 * 商品単価ゲッター
	 */
	public int getGoodsPrice() {
		return goodsPrice;
	}

	/*
	 * 商品単価セッター
	 */
	public void setGoodsPrice(int goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	/*
	 * 商品原価ゲッター
	 */
	public int getGoodsCost() {
		return goodsCost;
	}

	/*
	 * 商品原価セッター
	 */
	public void setGoodsCost(int goodsCost) {
		this.goodsCost = goodsCost;
	}
	
	
	
}
