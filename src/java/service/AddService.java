
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package service;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.MedecinFacadeLocal;
import beans.MedicamentFacadeLocal;
import beans.ServiceFacadeLocal;

import entity.Medecin;
import entity.Service;

/**
 *
 * @author Taleb
 */
@WebServlet(
    name        = "AddService",
    urlPatterns = "/AddService"
)
public class AddService extends HttpServlet {
    @EJB
    private MedecinFacadeLocal medecinFacade;
    @EJB
    private ServiceFacadeLocal serviceFacade;

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
        String   chefServiceIDString = request.getParameter("chefID");
        String   titreService        = request.getParameter("titre");
        String[] medecinAffecterIDS  = request.getParameterValues("listMedecins");
        Service  service             = new Service();
        
        Medecin  chef_service        = medecinFacade.find(Long.valueOf(chefServiceIDString));

        service.setChef_service(chef_service);
        service.setTitre(titreService);

        List<Medecin> medecins = new ArrayList<>();

        for (String id : medecinAffecterIDS) {
            medecins.add(medecinFacade.find(Long.valueOf(id)));
        }

        service.setMedecins(medecins);
        serviceFacade.create(service);
        request.getRequestDispatcher("/ServiceRedirect").forward(request, response);
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
