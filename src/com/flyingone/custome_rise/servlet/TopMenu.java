/**
 * トップメニュー
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

import com.flyingone.custome_rise.common.MessageString;
import com.flyingone.custome_rise.common.SystemException;
import com.flyingone.custome_rise.dto.User;

/**
 * トップメニュー処理サーブレット
 *
 * @author senior@example.com
 * @version 0.0.1
 */
@WebServlet("/TopMenu")
public class TopMenu extends HttpServletForCustomer {
	private static final long serialVersionUID = 1L;

	/**
	 * GETメソッド：トップメニューの表示処理を行う
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
		try {
			// セッション情報をチェックしてログインしているユーザーのユーザーIDを取得する
			String loginUserId = checkSession(request);

			// ログインユーザーのユーザー情報を取得する
			User loginUserInfo = getUserInfo(loginUserId);
			if (loginUserInfo == null) {
				// 例外を発生させる：セッションエラー（ログインユーザーのユーザー情報を取得できない）
				throw new SystemException(MessageString.errMsg1);
			}

			// トップメニュー表示JSPに処理を遷移する
			// ・loginUserName：ログインユーザーのユーザー名
			request.setAttribute("loginUserName", loginUserInfo.getName());
			forward("/view/memberMenu.jsp", request, response);
		} catch (SystemException e) {
			e.printStackTrace();

			// システムエラー表示JSPに処理をフォワードする（例外メッセージを表示させる）
			systemError(e.getUserMsg(), request, response);
		}
	}

	/**
	 * POSTメソッド：トップメニューの表示処理を行う
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
