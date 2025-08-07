package com.flyingone.custome_rise.servlet;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class GoodsSearchServlet
 */
@WebServlet("/GoodsSearchServlet")
public class GoodsSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodsSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private ArrayList<Goods> goodsList = new ArrayList<>();
    
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		/*
		 * 商品Dと、商品名を取得
		 */
		String id = request.getParameter("goodsId");
		String name = request.getParameter("goodsName");
		
		//空文字対策		
		boolean isId = id != null && !id.trim().isEmpty();
		boolean isName = name != null && !name.trim().isEmpty();
		
		
		//送信先URL
		String url = "/view/goodsMenu.jsp";
				
		GoodsMstDAO searchGoods = new GoodsMstDAO();
		goodsList.clear();
	

		if (!isId && !isName) {
		    // 両方空はエラーメッセージ表示
		    request.setAttribute("message", MessageString.errMsg2);
		} else if (isId && isName) {
		    // 両方入力されていたら両方条件で検索
		    try {
		        goodsList = (ArrayList<Goods>) searchGoods.read(id, name);
		    } catch (SystemException e) {
		        e.printStackTrace();
		        request.setAttribute("message", MessageString.errMsg2);
		    }
		} else if (isId) {
		    // IDのみ
		    try {
		        goodsList = (ArrayList<Goods>) searchGoods.readID(id);
		    } catch (SystemException e) {
		        e.printStackTrace();
		        request.setAttribute("message", MessageString.errMsg2);
		    }
		} else if (isName) {
		    // 名前のみ
		    try {
		        goodsList = (ArrayList<Goods>) searchGoods.readName(name);
		    } catch (SystemException e) {
		        e.printStackTrace();
		        request.setAttribute("message", MessageString.errMsg2);
		    }
		}

		request.setAttribute("goodsList", goodsList);
		
		
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
