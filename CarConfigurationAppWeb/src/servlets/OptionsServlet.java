/*
 * Servlet to get the options and details for the selected model from client-server and rendering them to options.jsp
 * Author: Vignan Uppugandla
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import client.ServletClient;
import model.Automobile;

@WebServlet("/getoptions")
public class OptionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletClient client;
	private HttpSession session;
	
	private Automobile auto;
	
	/*Default constructor*/
    public OptionsServlet() {
        super();
    }
    
    @Override
	public void init(){
		client = ServletClient.getInstance();
	}
    
    /*do Get method for receiving the request and request parameters and then sending the response to options.jsp*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession(true);
		String model = request.getParameter("modelName");
		model = model.trim();
		auto = client.getModelObject(model.split(" ",2)[1]);//Separating make and model and getting model name
//		auto = client.getAutoObject();
		session.setAttribute("Auto", auto);
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String docType = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\n";
		pw.println(docType + "<html>");
		pw.println("<head>");
		pw.println("<style>"
								+ "table, th, td { border: 1px solid black; text-align: left }"
								+ " th	{ background-color: #FF8C00; }"
								+ " </style>");
		pw.println("<title>Model Options</title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<h3>Basic Car Choice</h3>");
		pw.println("<form method = \"post\" action=\"finalSelection.jsp\">");
		pw.println("<table style=\"width:60%\">"
								+ "<tr>"
									+ "<th style =\"width:40%\">Option</th>"
									+ "<th style =\"width:60%\">Values</th>"
								+ "</tr>");
		pw.println("<tr>"
									+ "<td>Make</td>"
									+ "<td>"+auto.getMake()+"</td>"
								+ "</tr>"
								+ "<tr>"
									+ "<td>Model</td>"
									+ "<td>"+auto.getModel()+"</td>"
								+ "</tr>"
								+ "<tr>"
									+ "<td>Base Price</td>"
									+ "<td>"+auto.getBasePrice()+"</td>"
								+ "</tr>");
		for(int i=0;i<auto.getOptionSet().size();i++) {
			pw.println("<tr>"
									+ "<td>"+auto.getOptSetName(i)+"</td>"
									+ "<td>"
									+ "<select name =\""+auto.getOptSetName(i)+"\">");
			for(int j =0; j<auto.getOptionList(i).size();j++) {
				pw.println("<option value=\""+auto.getOptionName(i,j)+"\">"
										+ auto.getOptionName(i, j)
										+"</option>");
			}
			pw.println("</select>");
			pw.println("</td>");
			pw.println("</tr>");
		}
		pw.println("</table>");
		pw.println("<input type=\"submit\" value=\"Submit\">");
		pw.println("</form>");
		pw.println("</body>");
		pw.println("</html>");
	}
	
	//do post method which initiates the request
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}

}
