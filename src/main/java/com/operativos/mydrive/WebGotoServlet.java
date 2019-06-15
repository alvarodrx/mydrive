package com.operativos.mydrive;

import java.io.*;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "GoToServlet", value = "/goto")

public class WebGotoServlet extends BaseServlet {

    public void doRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        PrintWriter out = resp.getWriter();

        String where = req.getParameter("where");

        if (validateSession(req) != null) {
            resp.sendRedirect(where);
        } else {
            HttpSession session = req.getSession(true);
            session.setAttribute("errorTxt", "Sesion terminada.");
            session.removeAttribute("millis");
            resp.sendRedirect("login.jsp");
        }
    }

}