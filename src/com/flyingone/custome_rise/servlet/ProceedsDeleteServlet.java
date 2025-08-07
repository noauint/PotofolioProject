package com.flyingone.custome_rise.servlet;

import java.io.IOException;

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

/**
 * Servlet implementation class ProceedsDeleteServlet
 */
@WebServlet("/ProceedsDeleteServlet")
public class ProceedsDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProceedsDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("searchID"));
		
		String url = "/view/proceedsMenu.jsp";
		ProceedsMstDAO dao = new ProceedsMstDAO();
		
		try {
			dao.Delete(id);
			request.setAttribute("message", MessageString.MSG9);
		} catch (SystemException e) {
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
