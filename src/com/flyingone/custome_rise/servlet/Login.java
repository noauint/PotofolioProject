/**
 * ログイン処理
 *
 * @author senior@example.com
 */
package com.flyingone.custome_rise.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flyingone.custome_rise.common.MessageString;
import com.flyingone.custome_rise.common.SystemException;
import com.flyingone.custome_rise.dto.User;

/**
 * ログイン処理サーブレット
 *
 * @author senior@example.com
 * @version 0.0.1
 */
@WebServlet("/Login")
public class Login extends HttpServletForCustomer {
	private static final long serialVersionUID = 1L;

	/**
	 * GETメソッド：ブラウザからURLを指定されて直接呼び出された ＝ ログイン画面の表示を指示されたものと解釈する
	 *
	 * @param request HttpServletRequestオブジェクト
	 * @param response HttpServletResponseオブジェクト
	 * @throws ServletException
	 * @throws IOException
	 * @author senior@example.com
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// セッション情報（格納されている前回のログインユーザーの情報）があれば破棄する
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}

		// ログイン画面表示JSPに処理をフォワードする
		//画面遷移時のパスの混乱を緩和するためあえて絶対パス指定(JSPでは相対パスを使用(使用法簡単なため))
		forward("/view/login.jsp", request, response);
	}

	/**
	 * POSTメソッド：ログイン画面（login.jsp）内のformが送信されたものと解釈する
	 *
	 * @param request HttpServletRequestオブジェクト
	 * @param response HttpServletResponseオブジェクト
	 * @throws ServletException
	 * @throws IOException
	 * @author senior@example.com
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 送信されてきたリクエストパラメータの取得を行う
			request.setCharacterEncoding("UTF-8");
			String userId = request.getParameter("user_id");
			String password = request.getParameter("login_password");

			// パラメータ名「user_id」及び「login_password」でパラメータが送られてきていない
			if (userId == null || password == null) {
				// ログイン画面表示JSPに処理をフォワードする
				// ・errorMsg：ログインエラー
				request.setAttribute("errorMsg", MessageString.errMsg1);
				forward("/view/login.jsp", request, response);
				return;
			}

			// ログインしようとしたユーザーのユーザー情報を取得する
			User loginUserInfo = getUserInfo(userId);

			// 指定されたユーザーIDを持つユーザー情報は存在していない
			if (loginUserInfo == null) {
				// ログイン画面表示JSPに処理をフォワードする
				// ・errorMsg：ログインエラー
				// ・userId：入力されたユーザーID
				request.setAttribute("errorMsg",MessageString.errMsg1);
				request.setAttribute("userId", userId);
				forward("/view/login.jsp", request, response);
				return;
			}
			// 指定されたユーザーIDを持つユーザー情報を見つけることができた
			else {
				// ***** ユーザー情報に登録されたパスワードと入力されたパスワードが一致することを確認する *****
				// ユーザー情報に登録されたパスワードと入力されたパスワードが一致しなかった
				if (loginUserInfo.getLoginPassword().equals(password) != true) {
					// ログイン画面表示JSPに処理をフォワードする
					// ・errorMsg：ログインエラー
					// ・userId：入力されたユーザーID
					request.setAttribute("errorMsg",MessageString.errMsg1);
					request.setAttribute("userId", userId);
					forward("/view/login.jsp", request, response);
					return;
				}
				// ユーザー情報に登録されたパスワードと入力されたパスワードが一致した → ログイン成功！
				else {
					// セッションアトリビュートにログインユーザーのユーザーIDを保持する
					HttpSession session = request.getSession(true);
					session.setAttribute(SESSION_LOGIN_USER_ID, loginUserInfo.getUserId());
					
					//ユーザー情報を保持する
					session.setAttribute("loginUser", loginUserInfo);

					// トップメニュー画面にリダイレクトする
					response.sendRedirect(request.getContextPath() + "/TopMenu");
				}
			}
		} catch (SystemException e) {
			e.printStackTrace();

			// システムエラー表示JSPに処理をフォワードする（例外メッセージを表示させる）
			systemError(e.getUserMsg(), request, response);
		}
	}
}
