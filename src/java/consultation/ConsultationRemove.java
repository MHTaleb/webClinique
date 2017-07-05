
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package consultation;

import java.io.IOException;

import java.util.List;

import javax.ejb.EJB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ConsultationFacadeLocal;
import beans.InvestigationFacadeLocal;
import beans.MedicamentFacadeLocal;
import beans.OrdonnanceFacadeLocal;

import entity.Consultation;
import entity.Investigation;
import entity.Medicament;
import entity.Ordonnance;

/**
 *
 * @author Taleb
 */
@WebServlet(
    name        = "ConsultationRemove",
    urlPatterns = "/ConsultationRemove"
)
public class ConsultationRemove extends HttpServlet {

    @EJB
    private OrdonnanceFacadeLocal ordonnanceFacade;
    @EJB
    private InvestigationFacadeLocal investigationFacade;
    @EJB
    private ConsultationFacadeLocal  consultationFacade;
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long               id           = Long.valueOf(request.getParameter("id"));
        String             option       = request.getParameter("todo");
        final Consultation consultation = consultationFacade.find(id);

        if (option.equalsIgnoreCase("delete")) {
            consultationFacade.remove(consultation);
            for (Ordonnance ordonnance : consultation.getOrdonnances()) {
                ordonnanceFacade.remove(ordonnance);
            }

            request.getRequestDispatcher("/Consultation").forward(request, response);
        } else {
            List<Medicament> allMedicaments = medicamentFacade.findAll();

            request.getServletContext().setAttribute("allMedicaments", allMedicaments);

            List<Investigation> allInvestigations = investigationFacade.findAll();

            request.getServletContext().setAttribute("allInvestigations", allInvestigations);
            request.getServletContext().setAttribute("consultation", consultation);
            request.getRequestDispatcher("/administration/consultation/consultationEdit.jsp")
                   .forward(request, response);
        }
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
