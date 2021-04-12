/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.controllers;

import huylng.foods.FoodDAO;
import huylng.foods.FoodDTO;
import huylng.history.HistoryDAO;
import huylng.updateerror.UpdateErrorDTO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Shi
 */
public class UpdateController extends HttpServlet {

    public static final String SEARCH = "search.jsp";
    public static final String UPDATE = "update.jsp";

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
        boolean error = false;
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
            if (isMultiPart) {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = null;
                try {
                    items = upload.parseRequest(request);
                } catch (FileUploadException e) {
                    e.printStackTrace();
                }
                Iterator iter = items.iterator();
                Hashtable params = new Hashtable();
                String filename = null;
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (item.isFormField()) {
                        params.put(item.getFieldName(), item.getString());
                    } else {
                        try {
                            String itemName = item.getName();
                            filename = itemName.substring(itemName.lastIndexOf("\\") + 1);
                            String realPath = getServletContext().getRealPath("/") + "image\\" + filename;
                            File saveFile = new File(realPath);
                            item.write(saveFile);
                        } catch (Exception e) {
                            log("Errors at UpdateController: " + e.getMessage());
                        }
                    }
                }
                String id = (String) params.get("txtId");
                String name = (String) params.get("txtName");
                String image = (String) params.get("image");
                String description = (String) params.get("txtDescription");
                String Price = (String) params.get("txtPrice");
                String Quantity = (String) params.get("txtQuantity");
                String category = (String) params.get("cbCategory");
                String status = (String) params.get("cbStatus");
                String searchValue = (String) params.get("searchValue");
                String searchName = (String) params.get("searchName");
                String searchBeginMoney = (String) params.get("searchBeginMoney");
                String searchEndMoney = (String) params.get("searchEndMoney");
                String searchCategory = (String) params.get("searchCategory");
                if (status.equals("Active")) {
                    status = "1";
                }
                if (status.equals("Inactive")) {
                    status = "0";
                }
                UpdateErrorDTO errors = new UpdateErrorDTO();
                if (!name.matches("[a-zA-Z 0-9]{1,500}")) {
                    error = true;
                    errors.setNameLengthError("Invalid Name, limit length from 1 - 500 characters");
                }
                if (!description.matches("[a-zA-Z 0-9(),./& ]{1,500}")) {
                    error = true;
                    errors.setDescriptionLengthError("Invalid description, limit length from 1 - 500 characters");
                }
                if (Price.isEmpty()) {
                    Price = "";
                    if (!Price.matches("[\\d]{0,1000}")) {
                        error = true;
                        errors.setPriceFormatError("Please enter a valid number, maximum 1000$");
                    }
                }
                if (error == true) {
                    request.setAttribute("ERROR", errors);
                } else {
                    Float price = Float.parseFloat(Price);
                    int quantity = Integer.parseInt(Quantity);
                    FoodDAO dao = new FoodDAO();
                    if (!"".equals(filename)) {
                        if ((filename.substring(filename.lastIndexOf(".") + 1).equals("png"))) {
                            dao.updateFood(id, name, "image/" + filename, description, price, new Date(), category, status, quantity);
                        } else {
                            dao.updateFood(id, name, image, description, price, new Date(), category, status, quantity);
                            request.setAttribute("ERRORFILE", "Update fail. Please try again!");
                        }
                    } else {
                        dao.updateFood(id, name, image, description, price, new Date(), category, status, quantity);
                    }
                    HttpSession session = request.getSession();
                    if (session != null) {
                        String username = (String) session.getAttribute("USERNAME");
                        HistoryDAO historydao = new HistoryDAO();
                        historydao.insert(df.format(new Date()), new Date(), username + " updated a new object at " + new Date());
                        if (searchValue.equals("")) {
                            dao.loadFoods(1);
                            List<FoodDTO> fooddto = dao.getFoodList();
                            session.setAttribute("FOODLIST", fooddto);
                            int currentPage = (int) session.getAttribute("CURRENTPAGE");
                            urlRewriting = "MainController?page=" + currentPage + "&btAction=";
                        } else {
                            int currentPageOfSearch = (int) session.getAttribute("CURRENTPAGEOFSEARCH");
                            urlRewriting = "MainController?txtName=" + searchName + "&txtBegin="
                                    + searchBeginMoney + "&txtEnd=" + searchEndMoney + "&cbCategory="
                                    + searchCategory + "&page=" + currentPageOfSearch + "&btAction=Search";
                            dao.loadFoods(1);
                            List<FoodDTO> fooddto = dao.getFoodList();
                            session.setAttribute("FOODLIST", fooddto);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            log("Error at UpdateController_SQL: " + e.getMessage());
        } catch (NamingException e) {
            log("Error at UpdateController_Naming: " + e.getMessage());
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
