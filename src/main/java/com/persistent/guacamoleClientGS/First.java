package com.persistent.guacamoleClientGS;

import java.io.*;
import java.net.URLEncoder;
import javax.servlet.*;
import javax.servlet.http.*;

public class First extends HttpServlet {

  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
            throws ServletException, IOException {

      System.out.println("\n\n Host : " + request.getParameter("hostname")
                + "\t VM Connect : " + request.getParameter("vmconnect"));
      // Create query string
      StringBuilder string = new StringBuilder();
      string.append("hostname=" + request.getParameter("hostname"));
      string.append("&vmconnect=" + request.getParameter("vmconnect"));
      string.append("&domain=" + "CONVIRTURE");
      string.append("&username=" + "Adwait_Patankar");
      string.append("&password=" + "Test@123");
      string.append("&port=" + "2179");
      string.append("&security=" + "nla");
      string.append("&ignore-cert=" + "true");

      System.out.println("Query string is : " + string);
      RequestDispatcher rd = request.getRequestDispatcher("/First.jsp");;
      request.setAttribute("string", string.toString());
      System.out.println("get attribute : " + request.getAttribute("string"));
      rd.forward(request, response);
  }
}
