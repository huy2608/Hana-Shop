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
public class LoginController extends HttpServlet {

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
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String url = LOGIN;
        int page = 1;
        int noOfRecords = 1;
        try {
            UserDAO dao = new UserDAO();
            UserDTO dto = dao.checkLogin(username, password);
            HttpSession session = request.getSession();
            if (dto != null) {
                if (session != null) {
                    boolean role = dto.getRole();
                    FoodDAO fooddao = new FoodDAO();
                    if (role == true) {
                        session.setAttribute("ADMIN", role);
                        session.setAttribute("USERNAME", username);
                        fooddao.loadFoodsForAdmin(page);
                        noOfRecords = fooddao.getNoOfRecordsOfAdmin();
                    } else {
                        fooddao.loadFoods(page);
                        session.setAttribute("USER", dto);
                        noOfRecords = fooddao.getNoOfRecords();
                    }
                    session.setAttribute("FULLNAME", dto.getFullname());
                    Cookie cookie = new Cookie(username, password);
                    cookie.setMaxAge(60 * 5);
                    response.addCookie(cookie);
                    url = SEARCH;
                    List<FoodDTO> fooddto = fooddao.getFoodList();
                    int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / 20);
                    session.setAttribute("CURRENTPAGE", page);
                    session.setAttribute("NOOFPAGE", noOfPages);
                    session.setAttribute("FOODLIST", fooddto);
                }
            } else {
                request.setAttribute("ERR", "Username or Password is error");
            }
        } catch (SQLException | NamingException e) {
            log("Error at LoginController: " + e.getMessage());
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
