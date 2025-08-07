package com.flyingone.custome_rise.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flyingone.custome_rise.common.SystemException;
import com.flyingone.custome_rise.dto.Customer;

public class CustomerMstDAO extends DBAccessBase{
	
	/*
	 * 顧客マスタより、顧客IDで検索
	 */
	private static final String QUERY_SELECT_CUSTOMER_ID = "SELECT customer_id, customer_name, delete_at FROM customer_mst WHERE customer_id like ? AND delete_at = ''";
	
	
	/*
	 * 顧客マスタより、顧客名で検索
	 */
	private static final String QUERY_SELECT_CUSTOMER_NAME = "SELECT customer_id, customer_name, delete_at FROM customer_mst WHERE customer_name LIKE ? AND delete_at = ''";
	
	
	/*
	 * 顧客マスタより、顧客IDと顧客名で検索
	 */
	private static final String QUERT_SELECT_CUSTOMER_ID_NAME = "SELECT customer_id, customer_name, delete_at FROM customer_mst WHERE customer_id like ? AND customer_name LIKE ? AND delete_at = ''";
	
	/*
	 * 顧客マスタにデータの登録
	 */
	private static final String QUERT_INSERT_CUSTOMER_NAME = "INSERT INTO customer_mst (customer_name) values(?)";
	
	/*
	 *顧客マスタの論理削除 
	 */
	private static final String QUERY_DELETE_CUSTOMER_ID = "UPDATE customer_mst SET delete_at = '×' WHERE customer_id = ?";
	
	/*
	 * 
	 */
	private static final String QUERY_UPDATE_CUSTOMER_ID = "UPDATE customer_mst SET customer_name = ? WHERE customer_id = ?";
	private List<Customer> CustomerList = new ArrayList<Customer>();
	
	/*
	 * 顧客IDで検索
	 */
	public List<Customer> readIdCustomer(String id) throws SystemException, SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		
		int ID = Integer.parseInt(id);
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(QUERY_SELECT_CUSTOMER_ID);
			pstmt.setInt(1,ID);
			result = pstmt.executeQuery();
			
			while(result.next()) {
				Customer cs = new Customer();
				cs.setCustomerId(result.getString("customer_id"));
				cs.setCustomerName(result.getString("customer_name"));
				CustomerList.add(cs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(con != null) {
				con.close();
			}
			
			if(result != null) {
				result.close();
			}
		}
		
		return CustomerList;
	}
	
	/*
	 * 顧客名で検索
	 */
	public List<Customer> readNameCustomer(String name) throws SystemException, SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(QUERY_SELECT_CUSTOMER_NAME);
			pstmt.setString(1,"%" + name + "%");
			result = pstmt.executeQuery();
			
			while(result.next()) {
				Customer cs = new Customer();
				cs.setCustomerId(result.getString("customer_id"));
				cs.setCustomerName(result.getString("customer_name"));
				
				CustomerList.add(cs);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
			if(con != null) {
				con.close();
			}
			
			if(result != null) {
				result.close();
			}
		}
		
		
		
		return CustomerList;
	}
	/*
	 * 顧客IDと顧客名で検索
	 */
	public List<Customer> readIdName(String id, String name) throws SystemException, SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		
		int ID = Integer.parseInt(id);
		try {
			con = getConnection();
			pstmt = con.prepareStatement(QUERT_SELECT_CUSTOMER_ID_NAME);
			pstmt.setInt(1, ID);
			pstmt.setString(2, "%" + name + "%");
			result = pstmt.executeQuery();
			
			while(result.next()) {
				Customer cs = new Customer();
				cs.setCustomerId(result.getString("customer_id"));
				cs.setCustomerName(result.getString("customer_name"));
				CustomerList.add(cs);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			if(result != null) {
				result.close();
			}
			
			if(con != null) {
				con.close();
			}
		}
		
		return CustomerList;
	}
	
	/*
	 * 新規顧客情報を登録
	 */
	public int Insert(String name) throws SQLException {
		int result = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = getConnection();
			pstmt = con.prepareStatement(QUERT_INSERT_CUSTOMER_NAME);
			pstmt.setString(1, name);
			
			result = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
			
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
	
	public int Delete(String id) throws SystemException, SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		int ID = Integer.parseInt(id);
		try {
			con = getConnection();
			pstmt = con.prepareStatement(QUERY_DELETE_CUSTOMER_ID);
			pstmt.setInt(1, ID);
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
	
	public int Update(String id,String name) throws SystemException, SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		int ID = Integer.parseInt(id);
		try {
			con = getConnection();
			pstmt = con.prepareStatement(QUERY_UPDATE_CUSTOMER_ID);
			pstmt.setString(1, name);
			pstmt.setInt(2,ID);
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
