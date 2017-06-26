package client;

import java.io.IOException;

import java.util.List;

import javax.ejb.EJB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.PatientFacadeLocal;
import beans.RDVFacadeLocal;
import beans.UtilisateurFacadeLocal;

import entity.Patient;
import entity.RDV;

@WebServlet(
    name        = "RemoveClient",
    urlPatterns = "/RemoveClient"
)
public class RemoveClient extends HttpServlet {
    @EJB
    private UtilisateurFacadeLocal utilisateurFacade;
    @EJB
    private RDVFacadeLocal         rDVFacade;
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long    id   = Long.parseLong(request.getParameter("id"));
        String  todo = request.getParameter("todo");
        Patient p    = patientFacade.find(id);

        System.out.println("" + p.getInfoPersonnel().getNom());

        if (todo.toLowerCase().equals("delete")) {
            List<RDV> rdvs = rDVFacade.findAll();

            for (RDV rdv : rdvs) {
                if (rdv.getPatient().getId().longValue() == p.getId().longValue()) {
                    rDVFacade.remove(rdv);
                }
            }

            utilisateurFacade.remove(p.getUtilisateur());
            patientFacade.remove(p);

            List<Patient> patients = patientFacade.findAll();

            request.getServletContext().setAttribute("allClient", patients);
            request.getRequestDispatcher("/administration/client/client.jsp").forward(request, response);
        } else {
            request.getServletContext().setAttribute("Client", p);
            request.getRequestDispatcher("/administration/client/clientUpdate.jsp").forward(request, response);
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
