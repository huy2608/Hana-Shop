/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huylng.controllers;

import huylng.foods.FoodDAO;
import huylng.foods.FoodDTO;
import huylng.history.HistoryDAO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
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
public class CreateController extends HttpServlet {

    public static final String SEARCH = "search.jsp";
    public static final String CREATE = "create.jsp";

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
        try {
            boolean isMultiPart = ServletFileUpload.isMultipartContent(request);
            if (isMultiPart) {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = null;
                try {
                    items = upload.parseRequest(request);
                } catch (FileUploadException e) {
                    log("Error at CreateController: " + e.getMessage());
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
                String description = (String) params.get("txtDescription");
                String Price = (String) params.get("txtPrice");
                String category = (String) params.get("cbCategory");
                String Quantity = (String) params.get("txtQuantity");
                int quantity = 0;
                float price = 0;
                FoodDAO dao = new FoodDAO();
                boolean exist = dao.checkExistedId(id);
                HttpSession session = request.getSession(false);
                FoodDAO fooddao = new FoodDAO();
                if (session != null) {
                    if (filename != null) {
                        if ((filename.substring(filename.lastIndexOf(".") + 1).equals("png"))) {
                            if (exist) {
                                dao.updateFoodStatusById(id);
                            } else {
                                price = Float.parseFloat(Price);
                                quantity = Integer.parseInt(Quantity);
                                dao.createFood(id, name, "image/" + filename, description, price, new Date(), category, true, quantity);
                                fooddao.loadFoodsForAdmin(1);
                                List<FoodDTO> fooddto = fooddao.getFoodList();
                                session.setAttribute("FOODLIST", fooddto);
                            }
                        } else {
                            request.setAttribute("ERRORFILE", "Not a picture, Please choose another file");
                            url = CREATE;
                            request.setAttribute("FOODID", id);
                            request.setAttribute("FOODNAME", name);
                            request.setAttribute("FOODDESCRIPTION", description);
                            request.setAttribute("FOODPRICE", price);
                            request.setAttribute("FOODCATEGORY", category);
                            request.setAttribute("FOODQUANTITY", Quantity);
                        }
                    } else {
                        request.setAttribute("ERRORFILE", "Not a picture, Please choose another file");
                        url = CREATE;
                        request.setAttribute("FOODID", id);
                        request.setAttribute("FOODNAME", name);
                        request.setAttribute("FOODDESCRIPTION", description);
                        request.setAttribute("FOODPRICE", price);
                        request.setAttribute("FOODCATEGORY", category);
                        request.setAttribute("FOODQUANTITY", Quantity);
                    }
                }
            }
        } catch (SQLException e) {
            log("Error at CreateController_SQL: " + e.getMessage());
        } catch (NamingException e) {
            log("Error at CreateController_Naming: " + e.getMessage());
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
