/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personne;

import beans.PatientFacadeLocal;
import beans.UtilisateurFacadeLocal;
import entity.InfoCivil;
import entity.InfoPersonnel;
import entity.Patient;
import entity.Utilisateur;
import java.io.IOException;
import java.time.LocalDate;
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
@WebServlet(name = "UpdatePatient", urlPatterns = "/UpdatePatient")
public class UpdatePatient extends HttpServlet {

    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;

    @EJB
    private PatientFacadeLocal patientFacade;
    
    

    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        Long idLong = Long.valueOf(id);
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String sexe = request.getParameter("sexe");
        Boolean isHomme = sexe.equalsIgnoreCase("homme");
        String date = request.getParameter("date_naissance");
        LocalDate date_naissance = LocalDate.parse(date);
        String telephone = request.getParameter("tel");
        String adresse = request.getParameter("adresse");
        String civilite = request.getParameter("civilite");
        String profession = request.getParameter("profession");
        String cdn = request.getParameter("cdn");
        String ns = request.getParameter("ns");
        String password = request.getParameter("password");
        
        
        InfoCivil infoCivil= new InfoCivil(profession, ns, civilite, cdn);
        
        InfoPersonnel infoPersonnel = new  InfoPersonnel(nom, prenom, adresse, telephone, isHomme, date_naissance);
        
        Patient p = patientFacade.find(idLong);
        if (p.getInfoCivil().equals(infoCivil) && p.getInfoPersonnel().equals(infoPersonnel ) && p.getUtilisateur().getPassword().equals(password)) {
        } else {
            p.setInfoCivil(infoCivil);
            p.setInfoPersonnel(infoPersonnel);
            Utilisateur utilisateur = p.getUtilisateur();
            utilisateur.setPassword(password);
            utilisateurFacade.edit(utilisateur);
            patientFacade.edit(p);
        }
            request.getRequestDispatcher("./ShowAllClient").forward(request, response);
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
