/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.controllers;

import huylng.foods.FoodDAO;
import huylng.foods.FoodDTO;
import huylng.user.UserDAO;
import huylng.user.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Shi
 */
public class StartUpController extends HttpServlet {

    public static final String LOGIN = "login.jsp";
    public static final String SEARCH = "search.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = SEARCH;
        int noOfRecords = 1;
        String currentpage = request.getParameter("page");
        int currentPage = 1;
        try {
            Cookie[] cookie = request.getCookies();
            HttpSession session = request.getSession();
            UserDAO logindao = new UserDAO();
            FoodDAO fooddao = new FoodDAO();
            if (cookie != null) {
                Cookie lastCookie = cookie[cookie.length - 1];
                if (lastCookie.getName().equals("JSESSIONID") && cookie.length > 1) {
                    lastCookie = cookie[cookie.length - 2];
                }
                String username = lastCookie.getName();
                String password = lastCookie.getValue();
                UserDTO logindto = logindao.checkLogin(username, password);

                if (session != null) {
                    if (currentpage != null) {
                        currentPage = Integer.parseInt(currentpage);
                    }
                    if (logindto != null) {
                        session.setAttribute("FULLNAME", logindto.getFullname());
                        if (logindto.getRole() == true) {
                            session.setAttribute("ADMIN", logindto.getRole());
                            session.setAttribute("USERNAME", username);
                            fooddao.loadFoodsForAdmin(currentPage);
                            noOfRecords = fooddao.getNoOfRecordsOfAdmin();
                        } else {
                            fooddao.loadFoods(currentPage);
                            noOfRecords = fooddao.getNoOfRecords();
                            session.setAttribute("USER", logindto);
                        }
                        url = SEARCH;
                    } else {
                        fooddao.loadFoods(currentPage);
                        noOfRecords = fooddao.getNoOfRecords();
                    }
                }
            }
            if (cookie == null) {
                fooddao.loadFoods(currentPage);
                noOfRecords = fooddao.getNoOfRecords();
            }
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / 20);
            List<FoodDTO> fooddto = fooddao.getFoodList();
            session.setAttribute("FOODLIST", fooddto);
            session.setAttribute("CURRENTPAGE", currentPage);
            request.setAttribute("SEARCHVALUE", "");
            session.setAttribute("NOOFPAGE", noOfPages);
        } catch (SQLException | NamingException e) {
            log("Error at StartUpServlet " + e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
