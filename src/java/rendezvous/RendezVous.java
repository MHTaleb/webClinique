
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package rendezvous;

import java.io.IOException;

import java.time.LocalDate;

import java.util.List;

import javax.ejb.EJB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.PatientFacadeLocal;
import beans.RDVFacadeLocal;
import beans.ServiceFacadeLocal;

import entity.Patient;
import entity.RDV;
import entity.Service;

/**
 *
 * @author Taleb
 */
@WebServlet(
    name        = "RendezVous",
    urlPatterns = { "/RendezVous" }
)
public class RendezVous extends HttpServlet {
    @EJB
    private ServiceFacadeLocal serviceFacade;
    @EJB
    private PatientFacadeLocal patientFacade;
    @EJB
    private RDVFacadeLocal     rDVFacade;

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
        String serviceID = (String) request.getParameter("service");

        System.out.println("selected service : " + serviceID);

        String dateChoisi = (String) request.getParameter("dateChoisi");

        System.out.println("date choisis : " + dateChoisi);
        System.out.println("selected date : " + dateChoisi);

        String message = (String) request.getParameter("message-patient");

        System.out.println("message : " + message);

        Object patientID = request.getServletContext().getAttribute("current-personne-ID");

        System.out.println("patientID : " + patientID);

        LocalDate dateSelected = LocalDate.parse(dateChoisi);

        // List<RDV> rdvs = rDVFacade.findByDate(Date.from(dateSelected.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
        List<RDV> rdvs        = rDVFacade.findByDate(dateSelected);
        int       found       = 0;
        String    serviceName = "";

        for (RDV rdv : rdvs) {
            if (rdv.getService().getId().intValue() == Long.valueOf(serviceID).intValue()) {
                found++;
                serviceName = rdv.getService().getTitre();
            }
        }

        if (found >= 9) {
            request.setAttribute("error max rdv",
                                 " le service " + serviceName + " a eu un maximum de rendez-vous pour le jour "
                                 + dateChoisi);
            request.getRequestDispatcher("rendezvous.jsp").forward(request, response);

            return;
        }

        Patient patient = patientFacade.find(patientID);

        // List<RDV> findByDateAndPatient = rDVFacade.findByDateAndPatient(Date.from(dateSelected.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), patient);
        List<RDV> findByDateAndPatient = rDVFacade.findByDateAndPatient(dateSelected, patient);

        for (RDV rdv : findByDateAndPatient) {
            if (rdv.getService().getId().longValue() == Long.valueOf(serviceID).longValue()) {
                request.setAttribute("error max rdv by service",
                                     " vous avez deja un rendez-vous pour le service " + serviceName + " le : "
                                     + dateSelected.toString());
                request.getRequestDispatcher("rendezvous.jsp").forward(request, response);

                return;
            }
        }

        final int foundRdv = rDVFacade.getRDVCountByPatient(patient).intValue();

        if (foundRdv >= 3) {
            request.setAttribute("error max rdv by service",
                                 " Vous avez attein la limite de 3 rendez vous par 14 jour: prochain rendez vous possible pour "
                                 + LocalDate.now().plusDays(14).toString());
            request.getRequestDispatcher("rendezvous.jsp").forward(request, response);

            return;
        }

        RDV rDV = new RDV();

        rDV.setDateRDV(dateSelected);
        rDV.setMessage(message);
        rDV.setPatient(patient);

        Service serviceChoisi = serviceFacade.find(Long.valueOf(serviceID));

        rDV.setService(serviceChoisi);
        rDVFacade.create(rDV);
        request.setAttribute("success", "Rendez vous effectuer avec succes");
        request.getRequestDispatcher("rendezvous.jsp").forward(request, response);
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
