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
import com.flyingone.custome_rise.dao.CustomerMstDAO;
import com.flyingone.custome_rise.dto.Customer;

/**
 * Servlet implementation class CustomerUpdateServlet
 */
@WebServlet("/CustomerUpdateServlet")
public class CustomerUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("customerId");
		String name = request.getParameter("customerName");
		String url = "/view/customerUpdate.jsp";
		
		boolean isName = name != null && !name.trim().isEmpty();
		
		CustomerMstDAO dao = new CustomerMstDAO();
		if(isName) {
			
			try {
				dao.Update(id, name);
				request.setAttribute("message", MessageString.MSG11);
			} catch (SystemException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			
		}else {
			request.setAttribute("message", MessageString.errMsg6);
		}
		
		ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("customerId");
		String url = "/view/customerUpdate.jsp";
		
		List<Customer> customerList = new ArrayList<Customer>();
		CustomerMstDAO dao = new CustomerMstDAO();
		try {
			customerList = dao.readIdCustomer(id);
			request.setAttribute("customer", customerList.get(0));
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
