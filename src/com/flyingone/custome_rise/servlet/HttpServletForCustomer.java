/**
 * Webサイトのサーブレット基底クラス
 *
 * @author elder@example.com
 */
package com.flyingone.custome_rise.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flyingone.custome_rise.common.MessageString;
import com.flyingone.custome_rise.common.SystemException;
import com.flyingone.custome_rise.dao.UserMstDBAccess;
import com.flyingone.custome_rise.dto.User;

/**
 * Webサイトのサーブレット基底クラス
 *
 * @author elder@example.com
 * @version 0.0.1
 */
public class HttpServletForCustomer extends HttpServlet {
	/**
	 * セッションアトリビュートのアトリビュート名：ログインユーザーのユーザーID
	 */
	public static String SESSION_LOGIN_USER_ID = "login_user_id";

	/**
	 * システムエラー表示JSPのパス
	 */
	public static String SYSTEM_ERROR_JSP = "/common/systemError.jsp";

	/**
	 * 銀行コード：ITA銀行
	 */
	public static String BANK_CODE = "9999";

	/**
	 * 共通：セッションチェックを行う
	 *
	 * @param request HttpServletRequestオブジェクト
	 * @return String ログインユーザーのユーザーID
	 * @throws SystemException セッションエラーが発生した
	 * @author elder@example.com
	 */
	public String checkSession(HttpServletRequest request) throws SystemException {
		// セッション情報から「ログインユーザー」の情報を取得する
		HttpSession session = request.getSession();
		String loginUserId = (String) session.getAttribute(SESSION_LOGIN_USER_ID);
		if (loginUserId == null || loginUserId.isEmpty() == true) {
			// 例外を発生させる：セッションエラー
			throw new SystemException(MessageString.errMsg1);
		}

		// ログインユーザーのユーザーIDを返す
		return loginUserId;
	}

	/**
	 * 処理をフォワードする
	 *
	 * @param url フォワード先の処理を示すURL
	 * @param request HttpServletRequestオブジェクト
	 * @param response HttpServletResponseオブジェクト
	 * @throws ServletException
	 * @throws IOException
	 * @author elder@example.com
	 */
	public void forward(String url, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = null;

		// フォワード先のURLが「/」で始まっている場合
		if (url.startsWith("/") == true) {
			// フォワード要求を処理するオブジェクトを準備する
			ServletContext context = getServletContext();
			dispatcher = context.getRequestDispatcher(url);
			
		} else { // if (true != url.startsWith("/"))
		// フォワード先のURLが「/」以外の文字で始まっている場合
		
			// フォワード要求を処理するオブジェクトを準備する
			dispatcher = request.getRequestDispatcher(url);
		}
		// 処理を指定されたURLに転送する
		dispatcher.forward(request, response);
	}

	/**
	 * 共通：ユーザー情報の取得を行う
	 *
	 * @param userId ユーザーのユーザーID
	 * @return 取得したユーザー情報{@code UserMst}オブジェクト
	 * @throws SystemException データベースに接続できなかった
	 * @throws SystemException データベースアクセス中にエラーが発生した
	 * @author elder@example.com
	 */
	public User getUserInfo(String userId) throws SystemException {
		// 指定されたユーザーIDを持つユーザー情報を取得する
		UserMstDBAccess userMstDBA = new UserMstDBAccess();
		return userMstDBA.read(userId);
	}

	/**
	 * システムエラー表示JSPに処理をフォワードする
	 *
	 * @param msg システムエラー表示JSPに引き渡して表示させるメッセージ文字列
	 * @param request HttpServletRequestオブジェクト
	 * @param response HttpServletResponseオブジェクト
	 * @throws ServletException
	 * @throws IOException
	 * @author elder@example.com
	 */
	public void systemError(String msg, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("errorMsg", msg);
		forward(SYSTEM_ERROR_JSP, request, response);
	}
}
