/**
 * ログアウト処理
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

/**
 * ログアウト処理サーブレット
 *
 * @author senior@example.com
 * @version 0.0.1
 */
@WebServlet("/Logout")
public class Logout extends HttpServletForCustomer {
	private static final long serialVersionUID = 1L;

	/**
	 * GETメソッド：ログアウト処理（セッション情報の破棄）を行う
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

		// ログアウト完了表示JSPに処理をフォワードする
		forward("/view/logout.jsp", request, response);
	}

	/**
	 * POSTメソッド：ログアウト処理（セッション情報の破棄）を行う
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
		// GETメソッド呼び出し時と同じ処理を行う
		doGet(request, response);
	}
}
