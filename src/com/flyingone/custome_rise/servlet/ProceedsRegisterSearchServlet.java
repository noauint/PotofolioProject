package com.flyingone.custome_rise.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
 * Servlet implementation class ProceedsRegisterSearchServlet
 */
@WebServlet("/ProceedsRegisterSearchServlet")
public class ProceedsRegisterSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProceedsRegisterSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("searchID");
		String date = request.getParameter("searchData");
		String url = "/view/proceedsMenu.jsp";
		int ID = 0;
		
		ProceedsMstDAO dao = new ProceedsMstDAO();
		/*
		 * 空文字確認
		 */
		boolean isId = id != null && !id.trim().isEmpty();
		boolean isDate = date != null && !date.trim().isEmpty();
		
		System.out.println(date);
		List<CustomerRegister> CustomerRegisterList = new ArrayList<>();
		
		if(!isId && !isDate) {
			request.setAttribute("message", MessageString.errMsg7);
			
			
		}else if(isId && isDate) {
			/*
			 * 売上IDと売上日の両方を入力した場合
			 */
			try {
				ID = Integer.parseInt(id.trim());
			}catch (NumberFormatException e){
				request.setAttribute("message", MessageString.errMsg7);
				return;
			}
			
			try {
				CustomerRegisterList = dao.readIdName(ID, date);
				request.setAttribute("CustomerRegisterList", CustomerRegisterList);
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				request.setAttribute("message", MessageString.errMsg7);
			}
			
		}else if(isId) {
			/*
			 * 売上IDで検索
			 */
			try {
				ID = Integer.parseInt(id.trim());
			}catch (NumberFormatException e){
				request.setAttribute("message", MessageString.errMsg7);
				return;
			}
			
			try {
				CustomerRegisterList = dao.readId(ID);
				request.setAttribute("CustomerRegisterList", CustomerRegisterList);
			} catch (SystemException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			
		}else {
			/*
			 * 売上日で検索
			 */
			try {
				CustomerRegisterList = dao.readDate(date);
				request.setAttribute("CustomerRegisterList", CustomerRegisterList);
			}catch (SystemException | SQLException e) {
				e.printStackTrace();
				request.setAttribute("message", MessageString.errMsg7);
			}
			
		}
		
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
