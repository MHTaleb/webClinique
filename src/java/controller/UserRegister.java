/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.CategorieFacadeLocal;
import beans.PatientFacadeLocal;
import beans.ServiceFacadeLocal;
import beans.UtilisateurFacadeLocal;
import static controller.Constants.EMAIL;
import static controller.Constants.PASSWORD;
import static controller.Constants.USERNAME;
import entity.Categorie;
import entity.InfoCivil;
import entity.InfoPersonnel;
import entity.Patient;
import entity.Service;
import entity.Utilisateur;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Taleb
 */
@WebServlet(name = "UserRegister", urlPatterns = {"/UserRegister"})
public class UserRegister extends HttpServlet {

    @EJB
    private ServiceFacadeLocal serviceFacade;

    @EJB
    private PatientFacadeLocal patientFacade;

    @EJB
    private CategorieFacadeLocal categorieFacade;

    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;

    public UserRegister() {

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
            throws ServletException, IOException, ParseException {

        
        
        String username = request.getParameter(USERNAME);
        String email = request.getParameter(EMAIL);
        
        List<Utilisateur> utilisateurs = utilisateurFacade.findAll();
        for (Utilisateur utilisateur : utilisateurs) {
            if (utilisateur.getUsername().equals(username) || utilisateur.getEmail().equals(email)) {
                request.setAttribute("error", "cette utilisateur existe deja");
                request.getRequestDispatcher("enregistrer.jsp").forward(request, response);
                return;
            }
        }
        
        
        
        String password = request.getParameter(PASSWORD);
        System.out.println("username = " + username);
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setUsername(username);
        utilisateur.setPassword(password);
        List<Categorie> allCategories = categorieFacade.findAll();

        for (Categorie categorie : allCategories) {
            if (categorie.getTitre().toLowerCase().equals("patient")) {
                utilisateur.setCategorie(categorie);
                break;
            }
        }

        utilisateur.setEmail(email);

        
            utilisateurFacade.create(utilisateur);
         

        Patient patient = new Patient();

        InfoCivil infoCivil = new InfoCivil();
        infoCivil.setAssurance("non definie");
        infoCivil.setCdn("non definie");
        infoCivil.setEtatCivil("non definie");
        infoCivil.setProfession("non definie");
        patient.setInfoCivil(infoCivil);

        InfoPersonnel infoPersonnel = new InfoPersonnel();

        infoPersonnel.setNom(request.getParameter("nom"));
        infoPersonnel.setPrenom(request.getParameter("prenom"));

        String date = request.getParameter("dt_naissance");
        LocalDate localDate = LocalDate.parse(date);
        infoPersonnel.setDate_naissance(localDate);

        boolean sexe;
        System.out.println(" request.getParameter(\"sexe\") "+ request.getParameter("sexe"));
        sexe = request.getParameter("sexe").equalsIgnoreCase("on");
        infoPersonnel.setSexe(sexe);

        infoPersonnel.setTel(request.getParameter("telephone"));
        infoPersonnel.setAdresse(request.getParameter("adresse"));
        patient.setInfoPersonnel(infoPersonnel);

        patient.setUtilisateur(utilisateur);

        patientFacade.create(patient);
        request.getServletContext().setAttribute("current-user-ID", patient.getUtilisateur().getId());
        request.getServletContext().setAttribute("nom", patient.getInfoPersonnel().getNom());
        request.getServletContext().setAttribute("prenom", patient.getInfoPersonnel().getPrenom());
        request.getServletContext().setAttribute("naissance", patient.getInfoPersonnel().getDate_naissance().format(DateTimeFormatter.ISO_LOCAL_DATE));
        request.getServletContext().setAttribute("email", patient.getUtilisateur().getEmail());
        request.getServletContext().setAttribute("current-personne-ID", patient.getId());
        request.getServletContext().setAttribute("current-user", patient.getUtilisateur().getUsername());
        List<Service> services = serviceFacade.findAll();
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
        System.out.println("id du patient" + patient.getId());
        
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(request, response);

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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(UserRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(UserRegister.class.getName()).log(Level.SEVERE, null, ex);
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
    }// </editor-fold>

}
