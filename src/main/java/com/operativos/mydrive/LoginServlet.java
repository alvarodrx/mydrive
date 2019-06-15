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
        //session.removeAttribute("errorTxt");

        if (type != null && !type.equals("")){
            session.setAttribute("errorTxt", "");
            session.removeAttribute("userId");
            session.removeAttribute("millis");
            resp.sendRedirect("login.jsp");

            return;
        }



        JSONUtility jsu = new JSONUtility();
        //jsu.createNewUser("alvaro","asdasd");

        //JSONUtility jsu = new JSONUtility();
        JSONObject user = jsu.createUser(username,password);
        JSONArray userList = jsu.readJSONFile();

        out.print(userList.toJSONString() + "\n");

        out.print(user.toJSONString() + "\n");

        if (userList.contains(user)){
            session.setAttribute("userId", username);
            session.setAttribute("millis", Calendar.getInstance().getTimeInMillis());
            session.setMaxInactiveInterval(3600);
            resp.sendRedirect("/");
            return;
        } else {
            session.setAttribute("errorTxt", "Usuario o Contrase&ntilde;a incorrecto.");
            resp.sendRedirect("login.jsp");
        }

    }



}
