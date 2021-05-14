package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.FundingServlet;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


@WebServlet("/FundingAPI")
public class FundingAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	FundingServlet postObj = new FundingServlet();
	
	public FundingAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		String outputString = postObj.insertPost( 
				request.getParameter("postTitle"),
				request.getParameter("content"));
		        
			
				
				

		response.getWriter().write(outputString);
	}	

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Map paras = getParasMap(request);

		String outputString = postObj.updatePost(
				paras.get("postID").toString(),
				paras.get("postTitle").toString(),
				paras.get("content").toString());
				

		response.getWriter().write(outputString);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		String output = postObj.deletePost(paras.get("postID").toString());
		response.getWriter().write(output); 		
	}

	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request) {
		
		Map<String, String> map = new HashMap<String, String>();
		
		try {			
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			
			String[] params = queryString.split("&");
			for (String param : params) {
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
			
		} catch (Exception e) {
		  }
		
		return map;
	}
}
