/**
 * システム例外クラス
 *
 * @author senior@example.com
 */
package com.flyingone.custome_rise.common;

/**
 * システム例外クラス
 *
 * @author senior@example.com
 * @version 0.0.1
 */
public class SystemException extends Exception {
	/**
	 * ユーザーメッセージ
	 */
	protected String userMsg;

	/**
	 * コンストラクタ
	 *
	 * @param userMsg ユーザーメッセージ
	 * @param msg エラーメッセージ
	 * @author senior@example.com
	 */
	public SystemException(String userMsg) {
		this(userMsg, "*** SystemException ***");
	}

	/**
	 * コンストラクタ
	 *
	 * @param userMsg ユーザーメッセージ
	 * @param msg エラーメッセージ
	 * @author senior@example.com
	 */
	public SystemException(String userMsg, String msg) {
		super(msg);
		this.userMsg = userMsg;
	}

	/**
	 * ユーザーエラーメッセージを取得する
	 *
	 * @return ユーザーエラーメッセージ
	 */
	public String getUserMsg() {
		return this.userMsg;
	}
}
