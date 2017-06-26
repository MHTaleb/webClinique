
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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ConsultationFacadeLocal;
import beans.InvestigationFacadeLocal;
import beans.MedecinFacadeLocal;
import beans.MedicamentFacadeLocal;
import beans.PatientFacadeLocal;
import beans.ServiceFacadeLocal;

import entity.Consultation;
import entity.ExamenPreClinique;
import entity.Investigation;
import entity.Medecin;
import entity.Medicament;
import entity.Patient;
import entity.Service;

/**
 *
 * @author Taleb
 */
public class AddConsultation extends HttpServlet {
    @EJB
    private InvestigationFacadeLocal investigationFacade;
    @EJB
    private MedicamentFacadeLocal    medicamentFacade;
    @EJB
    private MedecinFacadeLocal       medecinFacade;
    @EJB
    private PatientFacadeLocal       patientFacade;
    @EJB
    private ServiceFacadeLocal       serviceFacade;
    @EJB
    private ConsultationFacadeLocal  consultationFacade;

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
        Consultation consultation = new Consultation();
        Long         id           = Long.valueOf(request.getParameter("idService"));
        Service      service      = serviceFacade.find(id);

        consultation.setService(service);
        id = Long.valueOf(request.getParameter("idPatient"));

        Patient patient = patientFacade.find(id);

        consultation.setPatient(patient);

        Medecin medecin = medecinFacade.find(Long.valueOf(request.getServletContext()
                                                                 .getAttribute("current-secretaire-id")
                                                                 .toString()));

        consultation.setMedecin(medecin);

        ExamenPreClinique examenPreClinique = new ExamenPreClinique();

        examenPreClinique.setGlycemie(request.getParameter("glycemie"));
        examenPreClinique.setPoids(request.getParameter("poids"));
        examenPreClinique.setTaille(request.getParameter("taille"));
        examenPreClinique.setTension(request.getParameter("tension"));
        consultation.setExamenPreClinique(examenPreClinique);
        consultationFacade.create(consultation);
        request.setAttribute("currentConsultation", consultation);

        List<Medicament> allMedicaments = medicamentFacade.findAll();

        request.setAttribute("allMedicaments", allMedicaments);

        List<Investigation> allInvestigations = investigationFacade.findAll();

        request.setAttribute("allInvestigations", allInvestigations);
        request.getRequestDispatcher("/administration/consultation/consultationEtape2.jsp").forward(request, response);
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
