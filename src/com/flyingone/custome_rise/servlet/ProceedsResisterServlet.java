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
 * Servlet implementation class ProceedsResisterServlet
 */
@WebServlet("/ProceedsResisterServlet")
public class ProceedsResisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProceedsResisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String url = "/view/proceedsMenu.jsp";
		
		String person = request.getParameter("person");
		String Pcs = request.getParameter("pcs");
		String goods_id = request.getParameter("goods_id");
		String sales_date = request.getParameter("sales_date");
		String Customer_Id = request.getParameter("customer_id");
		
		boolean isPerson = person != null && !person.trim().isEmpty();
		boolean isPcs = Pcs != null && !Pcs.trim().isEmpty();
		boolean isGoods_id = goods_id != null && !goods_id.trim().isEmpty();
		boolean isSales_date = sales_date != null && !sales_date.trim().isEmpty();
		boolean isCustomer_Id = Customer_Id != null && !Customer_Id.trim().isEmpty();
		
		if(isPerson && isPcs && isGoods_id && isSales_date && isCustomer_Id) {
			int pcs = Integer.parseInt(Pcs);
			
			if(pcs > 99 || pcs < 1) {
				request.setAttribute("message", MessageString.errMsg11);
				
			}else {

				int customer_id = Integer.parseInt(Customer_Id);
				ProceedsMstDAO dao = new ProceedsMstDAO();
				
				try {
					dao.Insert(person, pcs, goods_id, sales_date, customer_id);
					request.setAttribute("message", MessageString.MSG6);
				} catch (SystemException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
