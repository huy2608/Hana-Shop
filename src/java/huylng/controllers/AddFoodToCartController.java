/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.controllers;

import huylng.cart.FoodsCart;
import huylng.foods.FoodDAO;
import huylng.foods.FoodDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Shi
 */
public class AddFoodToCartController extends HttpServlet {

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
        String urlRewriting = SEARCH;
        String id = request.getParameter("foodId");
        String searchValue = request.getParameter("searchValue");
        String beginMoney = request.getParameter("txtBeginMoney");
        String endMoney = request.getParameter("txtEndMoney");
        String category = request.getParameter("foodCategory");
        try {
            HttpSession session = request.getSession(true);
            if (session != null) {
                FoodsCart cart = (FoodsCart) session.getAttribute("FOODCART");
                if (cart == null) {
                    cart = new FoodsCart();
                }
                Map<String, FoodDTO> foods = cart.getFoodCart();
                FoodDAO dao = new FoodDAO();
                FoodDTO dto = dao.getFoodByIdAndSetQuantity(id, 1);
                if (foods == null) {
                    foods = new HashMap<>();
                }
                if (foods.containsKey(id)) {
                    dto.setQuantity(foods.get(id).getQuantity());
                    cart.addFoodToCart(dto);
                } else {
                    cart.addFoodToCart(dto);
                }
                session.setAttribute("FOODCART", cart);
                if (!searchValue.isEmpty()) {
                    int currentPageOfSearch = (int) session.getAttribute("CURRENTPAGEOFSEARCH");
                    urlRewriting = "MainController?txtName=" + searchValue
                            + "&txtBegin=" + beginMoney + "&txtEnd=" + endMoney
                            + "&cbCategory=" + category + "&page=" + currentPageOfSearch
                            + "&btAction=Search";
                } else {
                    int currentPage = (int) session.getAttribute("CURRENTPAGE");
                    urlRewriting = "MainController?page=" + currentPage + "&btAction=";
                }
            }
        } catch (SQLException e) {
            log("Error at AddFoodToCartController_SQL: " + e.getMessage());
        } catch (NamingException e) {
            log("Error at AddFoodToCartController_Naming: " + e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(urlRewriting);
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
