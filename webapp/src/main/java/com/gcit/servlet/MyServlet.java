package com.gcit.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.dto.Credentials;
import com.google.gson.Gson;

/**
 * Validates login credentials sent via HTTP POST
 * 
 * @author Justin O'Brien
 */
@WebServlet("/login")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MyServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<html><body>Hello there </body></html>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> goodCredentials = new HashMap<String, String>();
		Gson gson = new Gson();
		goodCredentials.put("ThisIsNotMyPassword", "ThisIsMyUsername");
		Credentials credentials = gson.fromJson(request.getReader(), Credentials.class);
		if (goodCredentials.containsKey(credentials.getPassword())
				&& goodCredentials.get(credentials.getPassword()).equals(credentials.getUsername()))
			response.setStatus(202);
		else
			response.sendError(401);
	}

}
