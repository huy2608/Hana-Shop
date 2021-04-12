/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.controllers;

import huylng.bill.BillDAO;
import huylng.cart.FoodsCart;
import huylng.foods.FoodDAO;
import huylng.foods.FoodDTO;
import huylng.user.UserDAO;
import huylng.user.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class ConfirmController extends HttpServlet {

    public static final String SEARCH = "search.jsp";
    public static final String CHECKOUT = "checkOut.jsp";

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
        String fullname = request.getParameter("txtFullName");
        String phonenumber = request.getParameter("txtPhoneNumber");
        String address = request.getParameter("txtAddress");
        String url = SEARCH;
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String error = "";
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                FoodsCart cart = (FoodsCart) session.getAttribute("FOODCART");
                if (cart != null) {
                    Map<String, FoodDTO> foodcart = cart.getFoodCart();
                    UserDAO userdao = new UserDAO();
                    UserDTO userdto = (UserDTO) session.getAttribute("USER");
                    BillDAO billdao = new BillDAO();
                    FoodDAO fooddao = new FoodDAO();
                    int foodQuantity = 0;
                    if (userdto != null) {
                        String username = userdto.getUsername();
                        userdao.updateLogin(fullname, address, phonenumber, username);
                        List<String> foodlist;
                        foodlist = new ArrayList<>();
                        String list = "";
                        float total = 0;
                        for (Map.Entry<String, FoodDTO> object : foodcart.entrySet()) {
                            FoodDTO value = object.getValue();
                            foodQuantity = fooddao.getFoodById(value.getId()).getQuantity();
                            if (value.getQuantity() >= foodQuantity) {
                                error = error + ", " + value.getName();
                            }
                        }
                        foodQuantity = 0;
                        if ("".equals(error)) {
                            for (Map.Entry<String, FoodDTO> object : foodcart.entrySet()) {
                                FoodDTO value = object.getValue();
                                foodQuantity = fooddao.getFoodById(value.getId()).getQuantity();
                                foodQuantity = foodQuantity - value.getQuantity();
                                fooddao.updateFoodQuantityById(value.getId(), foodQuantity);
                                foodQuantity = 0;
                                foodlist.add(value.getName() + " x" + value.getQuantity());
                                list = foodlist.toString();
                                total = total + value.getPrice() * value.getQuantity();
                            }
                            billdao.insert(df.format(new Date()), username, new Date(), list, total);
                            session.removeAttribute("FOODCART");
                        } else {
                            request.setAttribute("ERROR", error + " out of stock");
                            url = CHECKOUT;
                        }
                    }
                }
            }
        } catch (SQLException e) {
            log("Error at ConfirmController_SQL: " + e.getMessage());
        } catch (NamingException e) {
            log("Error at ConfirmController_Naming: " + e.getMessage());

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
