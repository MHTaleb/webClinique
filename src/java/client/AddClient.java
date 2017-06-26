
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package client;

import java.io.IOException;

import java.time.LocalDate;

import java.util.List;

import javax.ejb.EJB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.CategorieFacadeLocal;
import beans.PatientFacadeLocal;
import beans.UtilisateurFacadeLocal;

import entity.Categorie;
import entity.InfoCivil;
import entity.InfoPersonnel;
import entity.Patient;
import entity.Utilisateur;

/**
 *
 * @author Taleb
 */
@WebServlet(
    name        = "AddClient",
    urlPatterns = "/AddClient"
)
public class AddClient extends HttpServlet {
    @EJB
    private CategorieFacadeLocal   categorieFacade;
    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;
    @EJB
    private PatientFacadeLocal     patientFacade;

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
        String        nom           = request.getParameter("nom");
        String        prenom        = request.getParameter("prenom");
        String        sexe          = request.getParameter("sexe");
        boolean       sexeState     = sexe.toLowerCase().equals("homme");
        String        adresse       = request.getParameter("adresse");
        String        dateNaissance = request.getParameter("date_naissance");
        String        tel           = request.getParameter("tel");
        String        cevilite      = request.getParameter("civilite");
        String        profession    = request.getParameter("profession");
        String        cdn           = request.getParameter("cdn");
        String        ns            = request.getParameter("ns");
        String        username      = request.getParameter("username");
        String        email         = request.getParameter("email");
        String        password      = request.getParameter("password");
        Patient       p             = new Patient();
        InfoPersonnel infoPersonnel = new InfoPersonnel();

        infoPersonnel.setNom(nom);
        infoPersonnel.setPrenom(prenom);
        infoPersonnel.setSexe(sexeState);
        infoPersonnel.setTel(tel);
        infoPersonnel.setAdresse(adresse);
        infoPersonnel.setDate_naissance(LocalDate.parse(dateNaissance));
        p.setInfoPersonnel(infoPersonnel);

        InfoCivil infoCivil = new InfoCivil();

        infoCivil.setAssurance(ns);
        infoCivil.setCdn(cdn);
        infoCivil.setEtatCivil(cevilite);
        infoCivil.setProfession(profession);
        p.setInfoCivil(infoCivil);

        Utilisateur utilisateur = new Utilisateur();
        Categorie   categorie   = categorieFacade.find(new Long(1));

        utilisateur.setCategorie(categorie);
        utilisateur.setEmail(email);
        utilisateur.setUsername(username);
        utilisateur.setPassword(password);

        try {
            utilisateurFacade.create(utilisateur);
        } catch (Exception e) {}

        p.setUtilisateur(utilisateur);
        patientFacade.create(p);

        List<Patient> patients = patientFacade.findAll();

        System.out.println("end");
        request.getServletContext().setAttribute("allClient", patients);
        request.getRequestDispatcher("/administration/client/client.jsp").forward(request, response);
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
