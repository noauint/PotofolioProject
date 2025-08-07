package com.flyingone.custome_rise.dto;

public class GoodsUpdate {
	//商品ID
		private String goodsId;
		
		//商品名
		private String goodsName;
		
		//商品単価
		private int goodsPrice;
		
		//商品原価
		private int goodsCost;
		
		//論理削除
		private String delete_at;

		public String getGoodsId() {
			return goodsId;
		}

		public void setGoodsId(String goodsId) {
			this.goodsId = goodsId;
		}

		public String getGoodsName() {
			return goodsName;
		}

		public void setGoodsName(String goodsName) {
			this.goodsName = goodsName;
		}

		public int getGoodsPrice() {
			return goodsPrice;
		}

		public void setGoodsPrice(int goodsPrice) {
			this.goodsPrice = goodsPrice;
		}

		public int getGoodsCost() {
			return goodsCost;
		}

		public void setGoodsCost(int goodsCost) {
			this.goodsCost = goodsCost;
		}

		public String getDelete_at() {
			return delete_at;
		}

		public void setDelete_at(String delete_at) {
			this.delete_at = delete_at;
		}
		
		
}
