
package com.operativos.mydrive;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.Calendar;

@WebServlet(name = "LoginServlet", value = "/login")

public class LoginServlet extends BaseServlet {

    public void doRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        PrintWriter out = resp.getWriter();
        String username = req.getParameter("User");
        String password = req.getParameter("Password");
        String type = req.getParameter("type");

        HttpSession session = req.getSession(true);

        if (type != null && !type.equals("")){
            session.invalidate();
            resp.sendRedirect("login.jsp");

            return;
        }

        try {

            Usuario usuario = Usuario.getUser(username);
            String pass = usuario.getPassword();

            if(pass.equals(password)){
                session.setAttribute("userId", username);
                session.setAttribute("millis", Calendar.getInstance().getTimeInMillis());
                session.setMaxInactiveInterval(3600);
                resp.sendRedirect("/index.jsp?file=MyDrive");
                return;
            } else {
                session.setAttribute("errorTxt", "Contrase&ntilde;a incorrecta.");
                resp.sendRedirect("login.jsp");
            }


        } catch (Exception e){
            out.print(e.getStackTrace());
            session.setAttribute("errorTxt", "Usuario incorrecto.");
        }

    }



}