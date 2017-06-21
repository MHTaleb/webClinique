/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redirrect;

import beans.ConsultationFacadeLocal;
import beans.PatientFacadeLocal;
import beans.ServiceFacadeLocal;
import entity.Patient;
import entity.Service;
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
@WebServlet(name ="Consultation",urlPatterns = "./Consultation")
public class Consultation extends HttpServlet {

    @EJB
    private ServiceFacadeLocal serviceFacade;

    @EJB
    private PatientFacadeLocal patientFacade;

    @EJB
    private ConsultationFacadeLocal consultationFacade;
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        List<entity.Consultation> allConsultations = consultationFacade.findAll();
        System.out.println("size of consult :"+allConsultations.size());
        request.getServletContext().setAttribute("allConsultations", allConsultations);
    
        List<Patient> allPatients = patientFacade.findAll();
        request.getServletContext().setAttribute("allPatients", allPatients);
        
        List<Service> allServices = serviceFacade.findAll();
        request.getServletContext().setAttribute("allServices", allServices);
        
        request.getRequestDispatcher("/administration/consultation/consultation.jsp").forward(request, response);
        
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
