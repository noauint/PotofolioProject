/**
 * データアクセス：ユーザーマスター（user_mst）
 *
 * @author senior@example.com
 */
package com.flyingone.custome_rise.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flyingone.custome_rise.common.MessageString;
import com.flyingone.custome_rise.common.SystemException;
import com.flyingone.custome_rise.dto.User;

/**
 * ユーザーマスター（user_mst）データアクセスクラス
 *
 * @author senior@example.com
 * @version 0.0.1
 */
public class UserMstDBAccess extends DBAccessBase {
	/**
	 * ユーザーマスタ情報を参照するクエリ（user_mstから取得）
	 * 2025/07/23に更新
	 */
	private static final String QUERY_SELECT_BY_USER_ID =
		    "SELECT user_id, user_password, user_name, mail_address FROM user_mst WHERE user_id = ?";


	/**
	 * ユーザー情報のリストを参照するクエリ（各口座の支店名を結合して取得する）
	 */
	private static final String QUERY_SELECT_LIST = "SELECT user_id, login_password, user_name, mail_address, telephone, zip_code, address, account1_branch_code, BR1.branch_name AS account1_branch_name, account1_number, account1_name, account1_type, account2_branch_code, BR2.branch_name AS account2_branch_name, account2_number, account2_name, account2_type, account3_branch_code, BR3.branch_name AS account3_branch_name, account3_number, account3_name, account3_type FROM user_mst LEFT OUTER JOIN branch_mst BR1 ON BR1.bank_code = ? AND BR1.branch_code = account1_branch_code LEFT OUTER JOIN branch_mst BR2 ON BR2.bank_code = ? AND BR2.branch_code = account2_branch_code LEFT OUTER JOIN branch_mst BR3 ON BR3.bank_code = ? AND BR3.branch_code = account3_branch_code ORDER BY user_id ASC";

	/**
	 * 指定されたユーザーIDを持つレコードを更新するクエリ
	 */
	private static final String QUERY_UPDATE_BY_USER_ID = "UPDATE user_mst SET login_password = ?, user_name = ?, mail_address = ?, telephone = ?, zip_code = ?, address = ?, account1_branch_code = ?, account1_number = ?, account1_name = ?, account1_type = ?, account2_branch_code = ?, account2_number = ?, account2_name = ?, account2_type = ?, account3_branch_code = ?, account3_number = ?, account3_name = ?, account3_type = ? WHERE user_id = ?";

	/**
	 * ユーザー情報（user_mst）を読み込む
	 *
	 * @param userId 読み込み対象のユーザー情報を特定するユーザーID
	 * @param bankCode 各口座情報の支店コードから「支店名」を取得するための銀行コード
	 * @return 読み込まれたユーザー情報を保持する{@code UserMst}オブジェクト、または指定されたユーザーIDを持つデータが存在しない場合にはnull
	 * @throws SystemException データベースに接続できなかった
	 * @throws SystemException データベースアクセス中にエラーが発生した
	 * @author senior@example.com
	 */
	public User read(String userId) throws SystemException {
		User user_mst = null;
		Connection conn = null;
		PreparedStatement pstmtSelect = null;
		ResultSet resultSet = null;

		try {
			// データベースに接続する
			conn = getConnection();

			// 使用するSQL文（SELECT文）を準備する
			pstmtSelect = conn.prepareStatement(QUERY_SELECT_BY_USER_ID);

			// SQL文のパラメータに支店名のJOIN取得に使用する銀行コードとユーザー情報を特定するためのユーザーIDを設定する
			pstmtSelect.setString(1, userId);
			
			// SQL文を実行して結果セットを取得する
			resultSet = pstmtSelect.executeQuery();

			// 結果セットの1行目に移動できた
			if (resultSet.next()) {
				// 戻り値用のUserMstオブジェクトを生成する
				user_mst = new User();

				// 結果セットの内容を取得して戻り値用のUserMstオブジェクトに設定する
				user_mst.setUserId(resultSet.getString("user_id"));
				user_mst.setLoginPassword(resultSet.getString("user_password"));
				user_mst.setName(resultSet.getString("user_name"));
				user_mst.setMailAddress(resultSet.getString("mail_address"));
				}
		} catch (SQLException e) {
			e.printStackTrace();

			// システム例外：データベースアクセス中にエラーが発生した
			throw new SystemException(MessageString.MSG12, e.getMessage());
		} finally {
			// 取得した結果セットの後片付けを行う
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
				}
			}

			// 準備したSQL文の後片付けを行う
			if (pstmtSelect != null) {
				try {
					pstmtSelect.close();
				} catch (SQLException e) {
				}
			}

			// データベース接続の後片付けを行う
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

		// 読み込んだユーザー情報を保持するUserMstオブジェクトを返す
		return user_mst;
	}

	/**
	 * ユーザー情報（user_mst）のリストを読み込む
	 *
	 * @param bankCode 各口座情報の支店コードから「支店名」を取得するための銀行コード
	 * @return 読み込まれたユーザー情報を保持する{@code UserMst}オブジェクトのリスト
	 * @throws SystemException データベースに接続できなかった
	 * @throws SystemException データベースアクセス中にエラーが発生した
	 * @author senior@example.com
	 */
	public List<User> readList(String bankCode) throws SystemException {
		List<User> userMstList = new ArrayList<User>();
		Connection conn = null;
		PreparedStatement pstmtSelect = null;
		ResultSet resultSet = null;

		try {
			// データベースに接続する
			conn = getConnection();

			// 使用するSQL文（SELECT文）を準備する
			pstmtSelect = conn.prepareStatement(QUERY_SELECT_LIST);

			// SQL文のパラメータに支店名のJOIN取得に使用する銀行コードを設定する
			pstmtSelect.setString(1, bankCode);
			pstmtSelect.setString(2, bankCode);
			pstmtSelect.setString(3, bankCode);

			// SQL文を実行して結果セットを取得する
			resultSet = pstmtSelect.executeQuery();

			// 結果セットの終わりまで1行ずつ読み込む
			while (resultSet.next()) {
				// 各行のユーザー情報を保持するUserMstオブジェクトを生成する
				User user_mst = new User();

				// 結果セットの内容を取得してUserMstオブジェクトに設定する
				user_mst.setUserId(resultSet.getString("user_id"));
				user_mst.setLoginPassword(resultSet.getString("login_password"));
				user_mst.setName(resultSet.getString("user_name"));
				user_mst.setMailAddress(resultSet.getString("mail_address"));
				
				// ユーザー情報を設定したUserMstオブジェクトを戻り値用のリストに追加する
				userMstList.add(user_mst);
			}
		} catch (SQLException e) {
			e.printStackTrace();

			// システム例外：データベースアクセス中にエラーが発生した
			throw new SystemException(MessageString.MSG12, e.getMessage());
		} finally {
			// 取得した結果セットの後片付けを行う
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
				}
			}

			// 準備したSQL文の後片付けを行う
			if (pstmtSelect != null) {
				try {
					pstmtSelect.close();
				} catch (SQLException e) {
				}
			}

			// データベース接続の後片付けを行う
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

		// 読み込まれたユーザー情報のリストを返す
		return userMstList;
	}

	/**
	 * ユーザー情報（user_mst）を更新する
	 *
	 * @param user_mst ユーザー情報を保持しているUserMstオブジェクト
	 * @param userId 更新対象のユーザー情報を特定するユーザーID
	 * @return 更新されたレコードの数
	 * @throws SystemException データベースに接続できなかった
	 * @throws SystemException データベースアクセス中にエラーが発生した
	 * @author senior@example.com
	 */
	public int update(User user_mst, String userId) throws SystemException {
		int retValue = 0;
		Connection conn = null;
		PreparedStatement pstmtSelect = null;

		try {
			// データベースに接続する
			conn = getConnection();

			// 使用するSQL文（UPDATE文）を準備する
			pstmtSelect = conn.prepareStatement(QUERY_UPDATE_BY_USER_ID);

			// SQL文のパラメータにユーザー情報を設定する
			pstmtSelect.setString(1, user_mst.getLoginPassword()); // login_password
			pstmtSelect.setString(2, user_mst.getName()); // user_name
			pstmtSelect.setString(3, user_mst.getMailAddress()); // mail_addres
			
			//SQL文のパラメータに更新対象のユーザー情報を特定するユーザーIDを設定する
			pstmtSelect.setString(19, userId); // user_id

			// SQL文を実行する
			retValue = pstmtSelect.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();

			// システム例外：データベースアクセス中にエラーが発生した
			throw new SystemException(MessageString.MSG12, e.getMessage());
		} finally {
			// 準備したSQL文の後片付けを行う
			if (pstmtSelect != null) {
				try {
					pstmtSelect.close();
				} catch (SQLException e) {
				}
			}

			// データベース接続の後片付けを行う
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

		// 更新されたレコードの数を返す
		return retValue;
	}
}
