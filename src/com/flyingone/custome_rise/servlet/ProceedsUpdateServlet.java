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
import com.flyingone.custome_rise.dao.ProceedsMstDAO;
import com.flyingone.custome_rise.dto.CustomerRegister;

/**
 * Servlet implementation class ProceedsUpdateServlet
 */
@WebServlet("/ProceedsUpdateServlet")
public class ProceedsUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProceedsUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String url = "/view/proceedsUpdate.jsp";
		String date = request.getParameter("date");
		int pcs = Integer.parseInt(request.getParameter("pcs"));
		int customerId = Integer.parseInt(request.getParameter("customerId"));
		String goodsId = request.getParameter("goodsId");
		String person = request.getParameter("person");
		int id = Integer.parseInt(request.getParameter("id"));
		
		ProceedsMstDAO dao = new ProceedsMstDAO();
		try {
			dao.Update(date, pcs, customerId, goodsId, person, id);
			request.setAttribute("message", MessageString.MSG12);
		} catch (SystemException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
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
		int id = Integer.parseInt(request.getParameter("searchID"));
		
		/*
		 * 宛先URL
		 */
		String url = "/view/proceedsUpdate.jsp";
		
		ProceedsMstDAO dao = new ProceedsMstDAO();
		try {
			List<CustomerRegister> customerList = dao.readId(id);
			
			if(!customerList.isEmpty()) {
				CustomerRegister c = customerList.get(0);
				request.setAttribute("customerList", c);
			}
			
			
		} catch (SystemException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(url);
		rd.forward(request, response);
		
	}

}
