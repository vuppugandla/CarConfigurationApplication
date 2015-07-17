/*
 * Initial load Servlet to get the model details from client-server and rendering them to models.jsp
 * Author: Vignan Uppugandla
 */

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


import javax.servlet.ServletConfig;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import client.ServletClient;

@WebServlet("/models")
public class ModelsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ServletClient client;
	
	/*Default constructor*/
    public ModelsServlet() {
        super();
    }
    
	/*Starting the client by overriding the init method of the servlet*/
	@Override
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		client = ServletClient.getInstance();
	}
	
    /*do get method for receiving the request and request parameters and then sending the response to models.jsp*/
	public void doGet(HttpServletRequest request, HttpServletResponse response)
											throws ServletException, IOException{
		ArrayList<String> models = client.getModels();
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\n";
		pw.println(docType + "<html>");
		pw.println("<head>");
		pw.println("<title>Car Configurator - Models</title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<h3>Welcome!</h3>");
		pw.println("<h2>Select a Car Model of your choice</h2>");
		pw.println("<form method  = \"post\" action=\"getoptions\">");
		if(models!=null) {
			for(String model : models){
				pw.println("<input type=\"radio\" name=\"modelName\"  value=\""+model+"\">"+model);
				pw.println("<br>");
			}
		}
		pw.println("<br>");
		pw.println("<input type=\"submit\" value=\"Select\">");
		pw.println("</form>");
		pw.println("</body>");
		pw.println("</html>");
		
	}
	//do post method which initiates the request
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
