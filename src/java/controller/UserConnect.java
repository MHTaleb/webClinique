
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package controller;

import java.io.IOException;

import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.PatientFacadeLocal;
import beans.ServiceFacadeLocal;
import beans.UtilisateurFacadeLocal;

import entity.Patient;
import entity.Service;

/**
 *
 * @author Taleb
 */
@WebServlet(
    name        = "UserConnect",
    urlPatterns = { "/UserConnect" }
)
public class UserConnect extends HttpServlet {
    @EJB
    private ServiceFacadeLocal     serviceFacade;
    @EJB
    private PatientFacadeLocal     patientFacade;
    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;

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
        String username = request.getParameter(Constants.USERNAME);
        String password = request.getParameter(Constants.PASSWORD);
        Long   doLogin  = utilisateurFacade.doLogin(username, password);

        System.out.println("do login " + doLogin);

        if (doLogin != -1) {
            Patient patient = patientFacade.findByUserId(doLogin);

            if (patient != null) {
                if (patient.getUtilisateur().getId().longValue() == doLogin.longValue()) {
                    request.getServletContext().setAttribute("current-user-ID", patient.getUtilisateur().getId());
                    request.getServletContext().setAttribute("nom", patient.getInfoPersonnel().getNom());
                    request.getServletContext().setAttribute("prenom", patient.getInfoPersonnel().getPrenom());
                    request.getServletContext()
                           .setAttribute("naissance",
                                         patient.getInfoPersonnel()
                                                .getDate_naissance()
                                                .format(DateTimeFormatter.ISO_LOCAL_DATE));
                    request.getServletContext().setAttribute("email", patient.getUtilisateur().getEmail());
                    request.getServletContext().setAttribute("current-personne-ID", patient.getId());

                    List<Service>     services       = serviceFacade.findAll();
                    ArrayList<String> titresServices = new ArrayList<>();

                    for (Service service : services) {
                        titresServices.add(service.getTitre());
                    }

                    ArrayList<Long> idServices = new ArrayList<>();

                    for (Service service : services) {
                        idServices.add(service.getId());
                    }

                    request.getServletContext().setAttribute("listeServices", titresServices);
                    request.getServletContext().setAttribute("idServices", idServices);
                    System.out.println("patient id :" + patient.getId());
                }
            }

            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/index.jsp");

            request.getServletContext().setAttribute("current-user", username);
            requestDispatcher.forward(request, response);
        } else {
            request.setAttribute("error", "login ou mot de passe incorrecte");

            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/seconnecter.jsp");

            requestDispatcher.forward(request, response);
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
