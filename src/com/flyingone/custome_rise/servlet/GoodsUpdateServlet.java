package com.flyingone.custome_rise.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
import com.flyingone.custome_rise.dto.Goods;

/**
 * Servlet implementation class GoodsUpdateServlet
 */
@WebServlet("/GoodsUpdateServlet")
public class GoodsUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodsUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * パラメター情報の取得
		 */
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		int price = Integer.parseInt(request.getParameter("price"));
		int cost = Integer.parseInt(request.getParameter("cost"));
		
		/*
		 *宛先url 
		 */
		String url = "/view/goodsUpdate.jsp";
		
		/*
		 * 情報更新
		 */
		GoodsMstDAO dao = new GoodsMstDAO();
		
		/*
		 * 商品登録時、商品ＩＤが重複している時
		 */
		try {
			List<Goods> goodsList = dao.readID(id);
			if(goodsList != null && !goodsList.isEmpty()) {

				try {
					dao.Update(id,name,price,cost);
					goodsList = dao.readID(id);
					request.setAttribute("goodsList", goodsList);
					request.setAttribute("message", MessageString.MSG10);
					
				} catch (SystemException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
					request.setAttribute("message", MessageString.errMsg10);
				} catch (SQLException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
					request.setAttribute("message", MessageString.errMsg10);
				}
		
			}else {
				request.setAttribute("message", MessageString.errMsg3);
			}

		} catch (SystemException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			request.setAttribute("message", MessageString.errMsg3);
		}

		
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(url);
		rd.forward(request, response);
		
	}
		
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * データの取得
		 */
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("goodsId");
		
		/*
		 * 宛先URL
		 */
		String url = "/view/goodsUpdate.jsp";
		
		GoodsMstDAO dao = new GoodsMstDAO();
		try {
			List<Goods> goodsList = dao.readID(id);
			
			if(!goodsList.isEmpty()) {
				Goods g = goodsList.get(0);
				request.setAttribute("goods", g);
			}
			
			
		} catch (SystemException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(url);
		rd.forward(request, response);
		
	}

	}


