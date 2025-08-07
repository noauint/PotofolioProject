/**
 * データアクセスオブジェクト（DAO）の基底クラス
 *
 * @author senior@example.com
 */
package com.flyingone.custome_rise.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.flyingone.custome_rise.common.MessageString;
import com.flyingone.custome_rise.common.SystemException;

/**
 * データアクセスオブジェクト（DAO）の基底クラス
 *
 * @author senior@example.com
 * @version 0.0.1
 */
public class DBAccessBase {
	/**
	 * 接続先データベースのホスト名
	 */
	protected final String DB_HOST_NAME = "localhost";
	
	/*
	 * 接続先データベースのホスト名
	 */
	//protected final String DB_HOST_NAME = "customrise.crsc2yyq2kgr.ap-northeast-1.rds.amazonaws.com";
	
	/*
	 * xserver用DBホスト名
	 */
	//private final String DB_HOST_NAME = "sv16347.xserver.jp";
	
	/**
	 * 接続先データベースのポート番号
	 */
	protected final int DB_PORT_NO = 3306;

	/**
	 * 接続先データベースで最初に接続するデータベース名
	 */
	protected final String DB_SCHEMA = "custome_rise";
	
	/*
	 * xserver用データベース名
	 */
	//protected final String DB_SCHEMA = "xs501579_gogo9700";
	/**
	 * デフォルトのデータベースユーザー名
	 */
	protected final String DEFAULT_DB_USER_NAME = "root";

	/*
	 *xserver用DBユーザー名 
	 */
	//protected final String DEFAULT_DB_USER_NAME = "xs501579_higuchi";
	
	/*
	 * デフォルトのデータベースユーザーの接続パスワード（ローカル用）
	 */
	//protected final String DEFAULT_DB_PASSWORD = "n";
	
	/**
	 * デフォルトのデータベースユーザーの接続パスワード
	 */
	
	protected final String DEFAULT_DB_PASSWORD = "mysql8080";

	/*
	 *xserver用DBパスワード 
	 */
	//protected final String DEFAULT_DB_PASSWORD = "kadora8561";
	
	/**
	 * データベースに接続する
	 *
	 * @param userName データベースのユーザー名
	 * @param password データベースユーザーの接続パスワード
	 * @return {@code Connection}オブジェクト
	 * @throws SystemException データベースに接続できなかった
	 * @author senior@example.com
	 */
	public Connection getConnection(String userName, String password) throws SystemException {
		Connection conn = null;

		try {
			// JDBC経由でMySQLに接続するURLを生成する
			// → jdbc:mysql://{ホスト名}:{ポート番号}/{データベース名}
			StringBuilder url = new StringBuilder("jdbc:mysql://");
			url.append(String.format("%s:%d/%s", DB_HOST_NAME, DB_PORT_NO, DB_SCHEMA));

			// JDBC経由でMySQLに接続する
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url.toString(), userName, password);
		}
		// JDBCドライバクラスを見つけられなかった
		catch (ClassNotFoundException e) {
			e.printStackTrace();

			// システム例外：データベースに接続できなかった
			throw new SystemException(MessageString.errMsg10, e.getMessage());
		}
		// 指定されたデータベースに接続できなかった
		catch (SQLException e) {
			e.printStackTrace();

			// システム例外：データベースに接続できなかった
			throw new SystemException(MessageString.errMsg10, e.getMessage());
		}

		// データベース接続オブジェクトを返す
		return conn;
	}

	/**
	 * デフォルトのユーザー名／パスワードでデータベースに接続する
	 *
	 * @return {@code Connection}オブジェクト
	 * @throws SystemException データベースアクセスに失敗した
	 * @author senior@example.com
	 */
	public Connection getConnection() throws SystemException {
		// デフォルトのユーザー名／パスワードを使用してデータベースに接続する
		return getConnection(DEFAULT_DB_USER_NAME, DEFAULT_DB_PASSWORD);
	}
}
