package com.ss.training.postassignment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
* Servlet Validates login credentials sent via HTTP POST
*/
@WebServlet("/postresponse")
public class PostResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
   /**
    * @see HttpServlet#HttpServlet()
    */
   public PostResponse() {
       super();
   }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<html><body>This is postresponse. </body></html>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,String> userCredentials = new HashMap<String, String>();
		String username = request.getParameter("username"), password = request.getParameter("password");
		userCredentials.put("ThisIsNotMyPassword", "ThisIsMyUsername");
		if(userCredentials.containsKey(password) && userCredentials.get(password).equals(username))
			response.setStatus(202);
		else
			response.sendError(401);
	}
}
