package com.flyingone.custome_rise.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.flyingone.custome_rise.common.MessageString;
import com.flyingone.custome_rise.common.SystemException;
import com.flyingone.custome_rise.dao.GoodsMstDAO;

/**
 * Servlet implementation class GoodsRegisterServlet
 */
@WebServlet("/GoodsRegisterServlet")
public class GoodsRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodsRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * データの受け取り
		 */
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("itemID");
		String name = request.getParameter("itemName");
		
		/*
		 * 商品IDを4桁で入力していない場合
		 */
		if(id.length() > 4) {
			request.setAttribute("message", MessageString.errMsg8);
		}else {
		
			try {
				int price = Integer.parseInt(request.getParameter("Price"));
				int cost = Integer.parseInt(request.getParameter("cost"));
				int chackPrice = String.valueOf(price).length();
				int chackCost = String.valueOf(cost).length();
				
				if(chackPrice > 9 || chackCost > 9) {
					/*
					 * 単価・原価のどちらかが9桁より大きい場合
					 */
					request.setAttribute("message", MessageString.errMsg12);
				}else {
					/*
					 * 単価・原価のどちらも9桁以内の場合
					 */
					GoodsMstDAO insert = new GoodsMstDAO();
					try {
						insert.Insert(id, name, price, cost);
						request.setAttribute("message", MessageString.MSG3);
					} catch (SystemException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
						request.setAttribute("message", MessageString.errMsg10);
					} catch (SQLException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
						request.setAttribute("message", MessageString.errMsg10);
					}
					
				}
				
			}catch (NumberFormatException e) {
				request.setAttribute("message", MessageString.errMsg13);
			}
		}
		/*
		 * 送信先URL
		 */
		String url = "/view/goodsMenu.jsp";
		
		
		
		
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
