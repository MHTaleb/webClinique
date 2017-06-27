
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package redirrect;

import java.io.IOException;

import java.util.List;

import javax.ejb.EJB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.InvestigationFacadeLocal;
import beans.MedicamentFacadeLocal;

import entity.Investigation;
import entity.Medicament;

/**
 *
 * @author Taleb
 */
@WebServlet(
    name        = "Medicaments",
    urlPatterns = "/Medicaments"
)
public class Medicaments extends HttpServlet {
    @EJB
    private InvestigationFacadeLocal investigationFacade;
    @EJB
    private MedicamentFacadeLocal    medicamentFacade;

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
        List<Investigation> investigations = investigationFacade.findAll();

        request.getServletContext().setAttribute("allInvestigation", investigations);

        List<Medicament> medicaments = medicamentFacade.findAll();

        request.getServletContext().setAttribute("allMedicament", medicaments);
        request.getRequestDispatcher("/administration/Medicament/medicament.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }    // </editor-fold>
}


//~ Formatted by Jindent --- http://www.jindent.com
