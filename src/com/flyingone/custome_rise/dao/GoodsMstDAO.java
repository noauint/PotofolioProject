package com.flyingone.custome_rise.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flyingone.custome_rise.common.MessageString;
import com.flyingone.custome_rise.common.SystemException;
import com.flyingone.custome_rise.dto.Goods;

public class GoodsMstDAO extends DBAccessBase{
	/*
	 * 商品マスタより、IDで検索
	 */
	private static final String QUERY_SELECT_GOODS_ID = "SELECT goods_id, goods_name, goods_price, goods_cost, delete_at FROM goods_mst WHERE goods_id LIKE ? AND delete_at = ''";
	
	/*
	 * 商品マスタより、商品名で検索
	 */
	private static final String QUERY_SELECT_GOODS_NAME = "SELECT goods_id, goods_name, goods_price, goods_cost, delete_at FROM goods_mst WHERE goods_name LIKE ? AND delete_at = ''";
	
	/*
	 * 商品マスタより、ID、商品名で検索
	 */
	private static final String QUERY_SELECT_GOODS_ID_NAME = "SELECT goods_id, goods_name, goods_price, goods_cost, delete_at FROM goods_mst WHERE goods_id like ? AND goods_name like ? AND delete_at = ''";
	
	/*
	 * 商品情報の論理削除
	 */
	private static final String QUERY_DELET_GOODS_ID = "UPDATE goods_mst SET delete_at= '×' WHERE goods_id = ?";
	
	/*
	 * 商品情報の登録
	 */
	private static final String QUERY_INSERT_GOODS_ID = "INSERT INTO goods_mst (goods_id, goods_name, goods_price, goods_cost, delete_at) VALUES(?,?,?,?,'')";
	
	/*
	 * 商品情報の更新
	 */
	private static final String QUERY_UPDATA_GOODS_ID = "UPDATE goods_mst SET goods_name = ?, goods_price = ?, goods_cost = ? WHERE goods_id = ?";
	
	
	
	
	public List<Goods> readID(String ID) throws SystemException{
		
		List<Goods> goodsList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;

		/*
		 * データベース接続（商品ID検索） 
		 */
		try {
			con = getConnection();
			pstmt = con.prepareStatement(QUERY_SELECT_GOODS_ID);
			pstmt.setString(1,"%" + ID + "%");
			
			result = pstmt.executeQuery();
			
			while(result.next()) {
				Goods goods = new Goods();
				goods.setGoodsId(result.getString("goods_id"));
				goods.setGoodsName(result.getString("goods_name"));
				goods.setGoodsPrice(result.getInt("goods_price"));
				goods.setGoodsCost(result.getInt("goods_cost"));
				goodsList.add(goods);
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
			
			throw new SystemException(MessageString.MSG12, e.getMessage());
		} finally {
			
			if(result != null) {
				try {
					result.close();
				} catch (SQLException e) {
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
			
		}
		
		
		return goodsList;
	}
	
	/*
	 * データベース接続（商品名検索）
	 */
	public List<Goods> readName(String name) throws SystemException{
		List<Goods> goodsList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(QUERY_SELECT_GOODS_NAME);
			pstmt.setString(1,"%" + name + "%");
			result = pstmt.executeQuery();
			
			while(result.next()) {
				Goods goods = new Goods();
				goods = new Goods();
				goods.setGoodsId(result.getString("goods_id"));
				goods.setGoodsName(result.getString("goods_name"));
				goods.setGoodsPrice(result.getInt("goods_price"));
				goods.setGoodsCost(result.getInt("goods_cost"));
				goodsList.add(goods);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			throw new SystemException(MessageString.MSG12, e.getMessage());
		} finally {
			if(result != null) {
				try {
					result.close();
				} catch (SQLException e) {
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (Exception e) {
					
				}
			}
		}
		
		
		return goodsList;
	}
	
	
	public List<Goods> read(String id, String name) throws SystemException{
		List<Goods> goodsList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(QUERY_SELECT_GOODS_ID_NAME);
			pstmt.setString(1,"%" + id + "%");
			pstmt.setString(2, "%" + name + "%");
			result = pstmt.executeQuery();
			
			while(result.next()) {
				Goods goods = new Goods();
				goods = new Goods();
				goods.setGoodsId(result.getString("goods_id"));
				goods.setGoodsName(result.getString("goods_name"));
				goods.setGoodsPrice(result.getInt("goods_price"));
				goods.setGoodsCost(result.getInt("goods_cost"));
				
				goodsList.add(goods);
			}
			
		}catch (SQLException e) {
			
		}finally {
			if(result != null) {
				try {
					result.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				}catch (Exception e) {
					
				}
			}
		}
		
		
		return goodsList;
	}
	
	public int delete(String id) throws SystemException, SQLException{

		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(QUERY_DELET_GOODS_ID);
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
		} catch (SystemException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			if(con != null) {
				con.close();
			}
			
		}
		
		return result;
	}
	
	public int Insert(String id, String name, int price, int cost) throws SystemException, SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		int result = 0;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(QUERY_INSERT_GOODS_ID);
			pstmt.setString(1,id);
			pstmt.setString(2,name);
			pstmt.setInt(3, price);
			pstmt.setInt(4, cost);
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			if(con != null) {
				con.close();
			}
		}
		return result;
		
	}
	
	/*
	 * 商品データの更新
	 */
	
	public int Update(String id,String name, int price, int cost) throws SystemException, SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(QUERY_UPDATA_GOODS_ID);
			pstmt.setString(1, name);
			pstmt.setInt(2, price);
			pstmt.setInt(3, cost);
			pstmt.setString(4, id);
			
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(con != null) {
				con.close();
			}
		}
		return result;
	}
}
