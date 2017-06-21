/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redirrect;

import beans.InfermiereFacadeLocal;
import beans.MedecinFacadeLocal;
import beans.SecretaireFacadeLocal;
import entity.Infermiere;
import entity.Medecin;
import entity.Secretaire;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Taleb
 */
@WebServlet(name = "Personnel",urlPatterns = "./Personnel")
public class Personnel extends HttpServlet {

    @EJB
    private SecretaireFacadeLocal secretaireFacade;

    @EJB
    private InfermiereFacadeLocal infermiereFacade;

    @EJB
    private MedecinFacadeLocal medecinFacade;

   
    
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
      
        List<Secretaire> allSecretaires = secretaireFacade.findAll();
        List<Medecin> allMedecins = medecinFacade.findAll();
        List<Infermiere> allInfermieres = infermiereFacade.findAll();
        
        request.getServletContext().setAttribute("allSecretaires", allSecretaires);
        request.getServletContext().setAttribute("allMedecins", allMedecins);
        request.getServletContext().setAttribute("allInfermieres", allInfermieres);
        
        request.getRequestDispatcher("/administration/personnel/personnel.jsp").forward(request, response);
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
