/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import data.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 715583
 */
public class LoginServlet extends HttpServlet {

    @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      //TODO if the checkbox is unchecked, 
      String action = request.getParameter("action");
      System.out.println("action"+action);
      
      if(action == null || action.isEmpty())
      {
        Cookie[] cookies =request.getCookies();
        String cookiename = "username";
        String username =null;
        User user = null;
        for(Cookie cookie : cookies)
        {
            if(cookie.getName().equals(cookiename))
            {
                username = cookie.getValue();
                System.out.println("username :"+username);
            }
        }
        if(username != null)
        {
            user = new User(username,null);
            request.setAttribute("user", user);
           // request.setAttribute("user", user);
        request.setAttribute("rememberme", "on");
        
        }
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        return ;
      }
      else if(action.equals("logout"))
      {
          HttpSession session=request.getSession();
          session.removeAttribute("userlogin");
          request.setAttribute("display", "Logged out successfully!");
         getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
         return;
      }
      
     }
 
     @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
             throws ServletException, IOException {
         
         String username = request.getParameter("Uname");
         String password = request.getParameter("Pword");
         User user1 = new User(username,password);
         
         
      //  String rememberme=request.getParameter("rememberme");
         boolean rememberme = request.getParameter("rememberme")!=null;
         
         
         if( username==null || password == null || username.trim().isEmpty() ||password.trim().isEmpty())
         {
             request.setAttribute("display","Both values are required!");
             request.setAttribute("user", user1);
             getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
             return;
         }
         
         //System.out.println(rememberme +"  "+test);
        // System.console(rememberme +"  "+test);
        //  getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);

           UserService us = new UserService();
          User user = us.login(username, password);
           if( user == null)
           {
             request.setAttribute("display","Invalid username or password!");
             request.setAttribute("user", user1);
             getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);  
             return;
           }
           else
           {
               //String path = getServletContext().getRealPath("/WEB-INF/home.jsp");
               if(rememberme==true)
               {
                   Cookie cookie = new Cookie("username", username);
                   cookie.setMaxAge(3600);
                   cookie.setPath("/");
                   response.addCookie(cookie);
               }
               else
               {
                    Cookie[] cookies =request.getCookies();
                    String cookiename = "username";
                    for(Cookie cookie : cookies)
                        {
                            if(cookie.getName().equals(cookiename))
                            {
                                cookie.setMaxAge(0);
                                response.addCookie(cookie);
                            }
                        }
               }
               HttpSession session = request.getSession();
               session.setAttribute("userlogin",user);   
               response.sendRedirect("home");
               return;
           }
//         else
//         {
//             request.setAttribute("username", username);
//             getServletContext().getRequestDispatcher("/WEB-INF/mainPage.jsp").forward(request, response);
//
//         }
     }

   

}