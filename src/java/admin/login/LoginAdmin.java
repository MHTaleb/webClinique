
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package admin.login;

import java.io.IOException;

import java.time.LocalDate;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.ejb.EJB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.MedecinFacadeLocal;
import beans.PatientFacadeLocal;
import beans.RDVFacadeLocal;
import beans.SecretaireFacadeLocal;
import beans.UtilisateurFacadeLocal;

import entity.Medecin;
import entity.RDV;
import entity.Secretaire;
import entity.Utilisateur;

/**
 *
 * @author Taleb
 */
@WebServlet(
    name        = "LoginAdmin",
    urlPatterns = { "/LoginAdmin" }
)
public class LoginAdmin extends HttpServlet {
    @EJB
    private MedecinFacadeLocal     medecinFacade;
    @EJB
    private PatientFacadeLocal     patientFacade;
    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;
    @EJB
    private RDVFacadeLocal         rDVFacade;
    @EJB
    private SecretaireFacadeLocal  secretaireFacade;

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
        String            userName     = request.getParameter("usrname");
        String            password     = request.getParameter("psw");
        Utilisateur       utilisateur  = null;
        List<Utilisateur> utilisateurs = utilisateurFacade.findAll();

        for (Iterator<Utilisateur> it = utilisateurs.iterator(); it.hasNext(); ) {
            Utilisateur u = it.next();

            if ((u.getEmail().equalsIgnoreCase(userName) || u.getUsername().equalsIgnoreCase(userName))
                    && u.getPassword().equals(password)) {
                utilisateur = u;

                break;
            }
        }

        if (utilisateur != null) {
            try {
                Secretaire secretaire = (utilisateur.getPersonne() instanceof Secretaire)
                                        ? (Secretaire) utilisateur.getPersonne()
                                        : null;
                Medecin    medecin    = (utilisateur.getPersonne() instanceof Medecin)
                                        ? (Medecin) utilisateur.getPersonne()
                                        : null;

                if (secretaire != null) {
                    request.getServletContext().setAttribute("current-secretaire-id", secretaire.getId());
                    request.getServletContext()
                           .setAttribute("current-secretaire-name", secretaire.getInfoPersonnel().getNom());
                    request.getServletContext()
                           .setAttribute("current-secretaire-lastName", secretaire.getInfoPersonnel().getPrenom());

                    int       count   = patientFacade.count();
                    List<RDV> allRdvs = rDVFacade.findByDate(LocalDate.now());

                    System.out.println("allrdvvs : " + allRdvs);
                    request.getServletContext().setAttribute("RDV-Size", "" + allRdvs.size());
                    request.getServletContext().setAttribute("clients-Size", "" + count);
                    request.getServletContext().setAttribute("isMedecin", "secretaire");
                    request.getRequestDispatcher("/administration/Board/board.jsp").forward(request, response);
                } else {
                    if (medecin != null) {
                        request.getServletContext().setAttribute("isMedecin", "medecin");
                        request.getServletContext().setAttribute("current-secretaire-id", medecin.getId());
                        request.getServletContext()
                               .setAttribute("current-secretaire-name", medecin.getInfoPersonnel().getNom());
                        request.getServletContext()
                               .setAttribute("current-secretaire-lastName", medecin.getInfoPersonnel().getPrenom());

                        int       count   = patientFacade.count();
                        List<RDV> allRdvs = rDVFacade.findByDate(LocalDate.now());

                        System.out.println("allrdvvs : " + allRdvs);
                        request.getServletContext().setAttribute("RDV-Size", "" + allRdvs.size());
                        request.getServletContext().setAttribute("clients-Size", "" + count);
                        request.getRequestDispatcher("/administration/Board/board.jsp").forward(request, response);
                    } else {
                        response.sendRedirect(
                            "http://localhost:9090/clinique_web/faces/administration/Login/index.jsp");
                    }
                }
            } catch (Exception e) {
                response.sendRedirect("http://localhost:9090/clinique_web/faces/administration/Login/index.jsp");
            }
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
