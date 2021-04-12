/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.controllers;

import huylng.foods.FoodDAO;
import huylng.foods.FoodDTO;
import huylng.searcherror.SearchErrorsDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
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
public class SearchController extends HttpServlet {

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
        String name = request.getParameter("txtName");
        String beginMoney = request.getParameter("txtBegin");
        String endMoney = request.getParameter("txtEnd");
        String category = request.getParameter("cbCategory");
        float begin = -1;
        float end = -1;
        int noOfRecord = 0;
        boolean error = false;
        int currentPage = 1;
        try {
            SearchErrorsDTO errors = new SearchErrorsDTO();
            if (name == null || name.isEmpty()) {
                name = "";
            }
            if (beginMoney == null || beginMoney.isEmpty()) {
                beginMoney = "";
            }
            if (endMoney == null || endMoney.isEmpty()) {
                endMoney = "";
            }
            if (category == null || category.isEmpty()) {
                category = "";
            }

            if (!beginMoney.matches("[\\d]{0,1000000}")) {
                error = true;
            }
            if (!endMoney.matches("[\\d]{0,1000000}")) {
                error = true;
            }
            if (error == true) {
                request.setAttribute("ERROR", errors);
            } else {
                if (!beginMoney.equals("")) {
                    begin = Float.parseFloat(beginMoney);
                }
                if (!endMoney.equals("")) {
                    end = Float.parseFloat(endMoney);
                }
                HttpSession session = request.getSession();
                if (session != null) {
                    if (begin > end) {
                        error = true;
                        errors.setEndNumberAvailableError("Must bigger than the begin number");
                    }
                    if (error == true) {
                        request.setAttribute("ERROR", errors);
                    } else {
                        String searchValue = request.getParameter("txtName");
                        if (searchValue != null) {
                            String currentpage = request.getParameter("page");
                            if (currentpage != null) {
                                currentPage = Integer.parseInt(currentpage);
                            }
                        }
                        FoodDAO dao = new FoodDAO();
                        if (session.getAttribute("ADMIN") == null) {
                            if (!name.isEmpty() && begin < 0 && end < 0 && "".equals(category)) {
                                dao.searchFoods(name, currentPage);
                                noOfRecord = dao.getNoOfRecords(name);
                                request.setAttribute("SEARCHVALUE", 1);
                                session.setAttribute("CURRENTPAGEOFSEARCH", currentPage);
                            }
                            if (name.isEmpty() && begin >= 0 && end >= 0 && "".equals(category)) {
                                dao.searchFoods(begin, end, currentPage);
                                noOfRecord = dao.getNoOfRecords(begin, end);
                                request.setAttribute("SEARCHVALUE", 1);
                                session.setAttribute("CURRENTPAGEOFSEARCH", currentPage);
                            }
                            if (name.isEmpty() && begin < 0 && end < 0 && !"".equals(category)) {
                                dao.searchFoods(category, currentPage, 1);
                                noOfRecord = dao.getNoOfRecords(category, 1);
                                request.setAttribute("SEARCHVALUE", 1);
                                session.setAttribute("CURRENTPAGEOFSEARCH", currentPage);
                            }
                            if (!name.isEmpty() && begin >= 0 && end >= 0 && "".equals(category)) {
                                dao.searchFoods(name, begin, end, currentPage);
                                noOfRecord = dao.getNoOfRecords(name, begin, end);
                                request.setAttribute("SEARCHVALUE", 1);
                                session.setAttribute("CURRENTPAGEOFSEARCH", currentPage);
                            }
                            if (name.isEmpty() && begin >= 0 && end >= 0 && !"".equals(category)) {
                                dao.searchFoods(begin, end, category, currentPage);
                                noOfRecord = dao.getNoOfRecords(category, begin, end, 1);
                                request.setAttribute("SEARCHVALUE", 1);
                                session.setAttribute("CURRENTPAGEOFSEARCH", currentPage);
                            }
                            if (!name.isEmpty() && begin < 0 && end < 0 && !"".equals(category)) {
                                dao.searchFoods(name, category, currentPage);
                                noOfRecord = dao.getNoOfRecords(name, category);
                                request.setAttribute("SEARCHVALUE", 1);
                                session.setAttribute("CURRENTPAGEOFSEARCH", currentPage);
                            }
                            if (!name.isEmpty() && begin >= 0 && end >= 0 && !"".equals(category)) {
                                dao.searchFoods(name, begin, end, category, currentPage);
                                noOfRecord = dao.getNoOfRecords(name, category, begin, end);
                                request.setAttribute("SEARCHVALUE", 1);
                                session.setAttribute("CURRENTPAGEOFSEARCH", currentPage);
                            }
                            if (name.isEmpty() && begin < 0 && end < 0 && "".equals(category)) {
                                dao.loadFoods(currentPage);
                                noOfRecord = dao.getNoOfRecords();
//                            request.setAttribute("SEARCHVALUE", "");
                                session.setAttribute("CURRENTPAGE", currentPage);
                            }
                        } else {
                            if (!name.isEmpty() && begin < 0 && end < 0 && "".equals(category)) {
                                dao.searchFoodsForAdmin(name, currentPage);
                                noOfRecord = dao.getNoOfRecordsOfAdmin(name);
                                request.setAttribute("SEARCHVALUE", 1);
                                session.setAttribute("CURRENTPAGEOFSEARCH", currentPage);
                            }
                            if (name.isEmpty() && begin >= 0 && end >= 0 && "".equals(category)) {
                                dao.searchFoodsForAdmin(begin, end, currentPage);
                                noOfRecord = dao.getNoOfRecordsOfAdmin(begin, end);
                                request.setAttribute("SEARCHVALUE", 1);
                                session.setAttribute("CURRENTPAGEOFSEARCH", currentPage);
                            }
                            if (name.isEmpty() && begin < 0 && end < 0 && !"".equals(category)) {
                                dao.searchFoodsForAdmin(category, currentPage, 1);
                                noOfRecord = dao.getNoOfRecordsOfAdmin(category, 1);
                                request.setAttribute("SEARCHVALUE", 1);
                                session.setAttribute("CURRENTPAGEOFSEARCH", currentPage);
                            }
                            if (!name.isEmpty() && begin >= 0 && end >= 0 && "".equals(category)) {
                                dao.searchFoodsForAdmin(name, begin, end, currentPage);
                                noOfRecord = dao.getNoOfRecordsOfAdmin(name, begin, end);
                                request.setAttribute("SEARCHVALUE", 1);
                                session.setAttribute("CURRENTPAGEOFSEARCH", currentPage);
                            }
                            if (name.isEmpty() && begin >= 0 && end >= 0 && !"".equals(category)) {
                                dao.searchFoodsForAdmin(begin, end, category, currentPage);
                                noOfRecord = dao.getNoOfRecordsOfAdmin(category, begin, end, 1);
                                request.setAttribute("SEARCHVALUE", 1);
                                session.setAttribute("CURRENTPAGEOFSEARCH", currentPage);
                            }
                            if (!name.isEmpty() && begin < 0 && end < 0 && !"".equals(category)) {
                                dao.searchFoodsForAdmin(name, category, currentPage);
                                noOfRecord = dao.getNoOfRecordsOfAdmin(name, category);
                                request.setAttribute("SEARCHVALUE", 1);
                                session.setAttribute("CURRENTPAGEOFSEARCH", currentPage);
                            }
                            if (!name.isEmpty() && begin >= 0 && end >= 0 && !"".equals(category)) {
                                dao.searchFoodsForAdmin(name, begin, end, category, currentPage);
                                noOfRecord = dao.getNoOfRecordsOfAdmin(name, category, begin, end);
                                request.setAttribute("SEARCHVALUE", 1);
                                session.setAttribute("CURRENTPAGEOFSEARCH", currentPage);
                            }
                            if (name.isEmpty() && begin < 0 && end < 0 && "".equals(category)) {
                                dao.loadFoodsForAdmin(currentPage);
                                noOfRecord = dao.getNoOfRecordsOfAdmin();
                                session.setAttribute("CURRENTPAGE", currentPage);
                            }
                        }
                        int noOfPages = (int) Math.ceil(noOfRecord * 1.0 / 20);
                        List<FoodDTO> food = dao.getFoods();
                        request.setAttribute("FOOD", food);
                        session.setAttribute("NOOFPAGE", noOfPages);
                        request.setAttribute("NAME", name);
                        request.setAttribute("BEGIN", beginMoney);
                        request.setAttribute("END", endMoney);
                        request.setAttribute("CATEGORY", category);
                    }
                }
            }
        } catch (SQLException e) {
            log("Error at SearchServlet_SQL: " + e.getMessage());
        } catch (NamingException e) {
            log("Error at SearchServlet_Naming: " + e.getMessage());
        } catch (NumberFormatException e) {
            log("Error at SearchServlet_NumberFormat: " + e.getMessage());
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
