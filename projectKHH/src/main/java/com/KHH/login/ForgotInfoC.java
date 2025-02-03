package com.KHH.login;

import org.json.simple.JSONObject;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ForgotInfoC")
public class ForgotInfoC extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/login/forgotInfo.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        InfoDAO.infoSearch(request);

        ResultData rsDt = (ResultData) request.getAttribute("info");
        String resultMessage = rsDt != null ? rsDt.getResult() : "조회 결과 없음";

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("result", resultMessage);

        response.getWriter().write(jsonResponse.toString());
    }
}