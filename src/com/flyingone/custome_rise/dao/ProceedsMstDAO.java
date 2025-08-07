package com.flyingone.custome_rise.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flyingone.custome_rise.common.SystemException;
import com.flyingone.custome_rise.dto.CustomerRegister;

public class ProceedsMstDAO extends DBAccessBase{
	
	private List<CustomerRegister> CustomerRegisterList = new ArrayList<>();
	/*
	 * 売上IDでの検索
	 */
	private static final String QUERY_SELECT_SALES_ID = "SELECT si.proceeds_id, si.person, si.goods_id, si.customer_id, si.pcs, si.sales_date, gm.goods_name, cm.customer_name FROM sales_info si\n"
			+ "JOIN goods_mst gm ON si.goods_id = gm.goods_id\n"
			+ "JOIN customer_mst cm ON si.customer_id = cm.customer_id\n"
			+ "WHERE si.proceeds_id = ? AND si.delete_at = ''";

	/*
	 * 売上日検索
	 */
	private static final String QUERY_SELECT_SALES_DATE = "SELECT si.proceeds_id, si.person, si.pcs, si.sales_date, gm.goods_name, cm.customer_name FROM sales_info si\n"
			+ "JOIN goods_mst gm ON si.goods_id = gm.goods_id\n"
			+ "JOIN customer_mst cm ON si.customer_id = cm.customer_id\n"
			+ "WHERE si.sales_date = ? AND si.delete_at = ''";
	
	/*
	 * 売上IDと売上日で検索
	 */
	private static final String QUERY_SELECT_SALES_ID_DATE = "SELECT si.proceeds_id, si.person, si.pcs, si.sales_date, gm.goods_name, cm.customer_name FROM sales_info si\n"
			+ "JOIN goods_mst gm ON si.goods_id = gm.goods_id\n"
			+ "JOIN customer_mst cm ON si.customer_id = cm.customer_id\n"
			+ "WHERE si.proceeds_id = ? AND si.sales_date = ? AND si.delete_at = ''";
	
	/*
	 * 売上情報の削除
	 */
	private static final String QUERY_DELETE_SALES_ID = "UPDATE sales_info SET delete_at = '×' WHERE proceeds_id = ?";
	
	/*
	 * 売上情報の新規登録
	 */
	private static final String QUERY_INSERT_SALES = "INSERT INTO sales_info (person, pcs, goods_id, sales_date, customer_id, delete_at) VALUES(?,?,?,?,?,'')";
	
	/*
	 * 売上情報の更新
	 */
	private static final String QUERY_UPDATE_SALES = "UPDATE sales_info SET person = ?, pcs = ?, goods_id = ?, sales_date = ?, customer_id = ? WHERE proceeds_id = ?";
	
	public List<CustomerRegister> readId(int id) throws SystemException, SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(QUERY_SELECT_SALES_ID);
			pstmt.setInt(1, id);
			result = pstmt.executeQuery();
			
			while(result.next()) {
				CustomerRegister cr = new CustomerRegister();
				cr.setProceedsId(result.getInt("si.proceeds_id"));
				cr.setCustomerId(result.getInt("si.customer_id"));
				cr.setGoodsId(result.getString("si.goods_id"));
				cr.setPerson(result.getString("si.PERSON"));
				cr.setPcs(result.getString("si.pcs"));
				cr.setGoodsName(result.getString("gm.goods_name"));
				cr.setSalesDate(result.getString("si.sales_date"));
				cr.setCustomerName(result.getString("cm.customer_name"));
				
				CustomerRegisterList.add(cr);
			}
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(result != null) {
				result.close();
			}
			
			if(con != null) {
				con.close();
			}
		}
		return CustomerRegisterList;
		
	}
	
	public List<CustomerRegister> readDate(String date) throws SystemException, SQLException{ 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(QUERY_SELECT_SALES_DATE);
			pstmt.setString(1, date);
			result = pstmt.executeQuery();
			
			while(result.next()) {
				CustomerRegister cr = new CustomerRegister();
				cr.setProceedsId(result.getInt("si.proceeds_id"));
				cr.setPerson(result.getString("si.PERSON"));
				cr.setPcs(result.getString("si.pcs"));
				cr.setGoodsName(result.getString("gm.goods_name"));
				cr.setSalesDate(result.getString("si.sales_date"));
				cr.setCustomerName(result.getString("cm.customer_name"));
				
				CustomerRegisterList.add(cr);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(result != null) {
				result.close();
			}
			if(con != null) {
				con.close();
			}
		}
		
		return CustomerRegisterList;
	}
	
	public List<CustomerRegister> readIdName(int id, String date) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(QUERY_SELECT_SALES_ID_DATE);
			pstmt.setInt(1, id);
			pstmt.setString(2, date);
			result = pstmt.executeQuery();
			
			while(result.next()) {
				CustomerRegister cr = new CustomerRegister();
				cr.setProceedsId(result.getInt("si.proceeds_id"));
				cr.setPerson(result.getString("si.PERSON"));
				cr.setPcs(result.getString("si.pcs"));
				cr.setGoodsName(result.getString("gm.goods_name"));
				cr.setSalesDate(result.getString("si.sales_date"));
				cr.setCustomerName(result.getString("cm.customer_name"));
				
				CustomerRegisterList.add(cr);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}finally {
			if(result != null) {
				result.close();
			}
			if(con != null) {
				con.close();
			}
		}
		return CustomerRegisterList;
	}
	
	public int Delete(int id) throws SystemException {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(QUERY_DELETE_SALES_ID);
			pstmt.setInt(1, id);
			result = pstmt.executeUpdate();

		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int Insert(String person ,int pcs, String goods_id, String sales_date, int customer_id) throws SystemException {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(QUERY_INSERT_SALES);
			pstmt.setString(1,person);
			pstmt.setInt(2, pcs);
			pstmt.setString(3, goods_id);
			pstmt.setString(4, sales_date);
			pstmt.setInt(5, customer_id);
			
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public int Update(String date, int pcs, int customerId, String goodsId, String person, int id) throws SystemException, SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(QUERY_UPDATE_SALES);
			pstmt.setString(1, person);
			pstmt.setInt(2, pcs);
			pstmt.setString(3, goodsId);
			pstmt.setString(4, date);
			pstmt.setInt(5, customerId);
			pstmt.setInt(6, id);
			
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
