/**
 * ユーザーマスター（user_mst）
 *
 * @author old-timer@example.com
 */
package com.flyingone.custome_rise.dto;

import java.io.Serializable;

/**
 * ユーザーマスター（user_mst）データクラス
 *
 * @author old-timer@example.com
 * @version 0.0.1
 */
public class User implements Serializable {
	/**
	 * ユーザーID
	 */
	String userId;

	/**
	 * ログインパスワード
	 */
	String loginPassword;

	/**
	 * ユーザー名
	 */
	String name;

	/**
	 * メールアドレス
	 */
	String mailAddress;

	/**
	 * デフォルトコンストラクタ
	 *
	 */
	public User() {
		super();
	}

	/**
	 * コピーコンストラクタ
	 *
	 * @param user_mst コピー元の{@code UserMst}オブジェクト
	 */
	public User(User user_mst) {
		this();

		// コピー元オブジェクトの内容をコピーする
		if (null != user_mst) {
			userId = user_mst.getUserId();
			loginPassword = user_mst.getLoginPassword();
			name = user_mst.getName();
			mailAddress = user_mst.getMailAddress();
		}
	}

	/*
	 * ユーザーID Getteer
	 */
	public String getUserId() {
		return userId;
	}

	/*
	 * ユーザーID Setter
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/*
	 * パスワード　getter
	 */
	public String getLoginPassword() {
		return loginPassword;
	}

	/*
	 * パスワード　setter
	 */
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	/*
	 * 名前　getter
	 */
	public String getName() {
		return name;
	}

	/*
	 * 名前　setter
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * メールアドレス getter
	 */
	public String getMailAddress() {
		return mailAddress;
	}

	/*
	 * メールアドレス　setter
	 */
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	
}
