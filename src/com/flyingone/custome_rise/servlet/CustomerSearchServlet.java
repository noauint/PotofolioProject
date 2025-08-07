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
 * Servlet implementation class CustomerSearchServlet
 */
@WebServlet("/CustomerSearchServlet")
public class CustomerSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Customer> CustomerList = new ArrayList<Customer>();
		
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("customerId");
		String name = request.getParameter("customerName");
		String url = "/view/customerMenu.jsp";
		
		System.out.println();
		boolean isId = id != null && !id.trim().isEmpty();
		boolean isName = name != null && !name.trim().isEmpty();
		
		CustomerMstDAO dao = new CustomerMstDAO();
		CustomerList.clear();
		
		if(!isId && !isName) {
			//顧客IDと顧客名が空欄の場合
			request.setAttribute("message", MessageString.errMsg4);
		}else if(isId && isName) {
			//顧客IDと顧客名で検索
			try {
				CustomerList = dao.readIdName(id, name);
				request.setAttribute("CustomerList", CustomerList);
			}catch (SQLException | SystemException e) {
				e.printStackTrace();
				request.setAttribute("message", MessageString.errMsg4);
			}
			
		}else if(isId) {
			//顧客IDで検索
			
			try {
				CustomerList = dao.readIdCustomer(id);
				request.setAttribute("CustomerList", CustomerList);
			}catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("message", MessageString.errMsg4);
			}
			
		}else {
			//顧客名で検索
			try {
				CustomerList = dao.readNameCustomer(name);
				request.setAttribute("CustomerList", CustomerList);
			} catch (SystemException | SQLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				request.setAttribute("message", MessageString.errMsg4);
			}
			
		}
		
		request.setAttribute("CustomerList", CustomerList);
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
