package com.operativos.mydrive;

import org.apache.commons.lang3.StringEscapeUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Calendar;

@WebServlet(name = "BaseServlet", value = "/base")

public abstract class BaseServlet extends HttpServlet {
    
    private static final long MAX_SESSION = 3600000; //3,600,000 1h
    
    protected Connection connection;
    
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        try {
            doRequest(req, resp);
        } catch (Exception ex) {
            out.println("1");
            out.println(ex.toString());
        }
    }
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        try {
            doRequest(req, resp);
        } catch (Exception ex) {
            out.println("1");
            out.println(ex.toString());
        }
    }
    
    public abstract void doRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception;

    
    protected String escapeString(String input) {
        if (input == null) {
            return "";
        }
        return StringEscapeUtils.escapeHtml4(input);
    }
    
    protected String checkNull(String value) {
        if (value == null || value.equals("null")) {
            return "";
        }
        return value;
    }
    
    public String validateSession (HttpServletRequest req) {
        HttpSession session = req.getSession(true);
        String userId = (String)session.getAttribute("userId");
        Long milis = (Long)session.getAttribute("millis");
        
        if (userId != null && !userId.isEmpty() && milis != null && milis > 0) {
            long currentMilis = Calendar.getInstance().getTimeInMillis();
            //saveError("TIMEOUT Current:"+ currentMilis  + " Milis:" + milis + " DIF:" + (currentMilis - milis));
            if ((currentMilis - milis) < MAX_SESSION) {
                session.setAttribute("millis", currentMilis);
                return userId;
            }
        }
        return null;
    }
    
}


