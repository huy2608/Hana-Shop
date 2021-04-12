/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.controllers;

import huylng.foods.FoodDAO;
import huylng.history.HistoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Shi
 */
public class DeleteController extends HttpServlet {

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
        String id = request.getParameter("foodId");
        String searchValue = request.getParameter("searchValue");
        String name = request.getParameter("searchValue");
        String beginMoney = request.getParameter("txtBeginMoney");
        String endMoney = request.getParameter("txtEndMoney");
        String category = request.getParameter("foodCategory");
        String urlRewriting = SEARCH;
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            FoodDAO dao = new FoodDAO();
            dao.deleteFood(id);
            HttpSession session = request.getSession();
            if (session != null) {
                String username = (String) session.getAttribute("USERNAME");
                HistoryDAO historydao = new HistoryDAO();
                historydao.insert(df.format(new Date()), new Date(), username + " delete an object at " + new Date());
                if (searchValue == null) {
                    int currentPage = (int) session.getAttribute("CURRENTPAGE");
                    urlRewriting = "MainController?btAction=&page=" + currentPage;
                } else {
                    int currentPageOfSearch = (int) session.getAttribute("CURRENTPAGEOFSEARCH");
                    urlRewriting = "MainController?btAction=Search&page=" + currentPageOfSearch
                            + "&txtName=" + name + "&txtBegin=" + beginMoney + "&txtEnd=" + endMoney
                            + "&cbCategory=" + category;
                }
            }
        } catch (SQLException | NamingException e) {
            log("Error at DeleteController " + e.getMessage());
        } finally {
            response.sendRedirect(urlRewriting);
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
