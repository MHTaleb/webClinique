<%-- 
    Document   : rendezvous
    Created on : 31 mai 2017, 10:59:25
    Author     : Taleb
--%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%
    String nom = (String) request.getServletContext().getAttribute("nom");
    String prenom = (String) request.getServletContext().getAttribute("prenom");
    String email = (String) request.getServletContext().getAttribute("email");
    LocalDate now = LocalDate.now();

    String minDate = now.toString();
    String maxDate = LocalDate.parse(minDate).plusDays(14).toString();

    String naissance = (String) request.getServletContext().getAttribute("naissance");
   // if (nom == null && prenom == null) {
   //     request.getRequestDispatcher("index.jsp").forward(request, response);
   // }
    ArrayList<String> listeServices = (ArrayList<String>) request.getServletContext().getAttribute("listeServices");
    ArrayList<Long> idServices = (ArrayList<Long>) request.getServletContext().getAttribute("idServices");
    
    String errorMaxRDVsrv =(String)request.getAttribute("error max rdv by service");
    String errorMaxRDV =(String)request.getAttribute("error max rdv");
    String success =(String)request.getAttribute("success");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Formulaire de Rendez-vous - SG Clinique : Bouchera ......</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
        <link rel="stylesheet" href="assets/css/main.css" />
        <!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
        <!--[if lte IE 9]><link rel="stylesheet" href="assets/css/ie9.css" /><![endif]-->
    </head>
    <body>

        <!-- Page Wrapper -->
        <div id="page-wrapper">

            <!-- Header -->
            <header id="header">
                <h1><a href="index.html">Spectral</a></h1>
                <nav id="nav">
                    <ul>
                        <li class="special">
                            <a href="#menu" class="menuToggle"><span>Menu</span></a>
                            <div id="menu">
                                <ul>
                                    <li><a href="index.jsp">Acceuil</a></li>
                                    <li><a href="sedeconnecter.jsp">Se D&eacute;connecter</a></li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </nav>
            </header>
            <article id="main">
                <header>
                    <h2> Formulaire de Rendez vous pour : <%=nom%>  <%=prenom%> </h2>
                    <p>    veuillez remlir les champs en dessous pour effectuer un rendez vous </p>
                    <p>    <% if(success != null) out.print(success); %>   </p>
                </header>
                <section class="wrapper style5">
                    <div class="inner">
                        <!-- Main -->

                        <section>
                            <h4>Formulaire de Rendez-Vous</h4>
                            <form method="POST" action="./RendezVous">
                                <div class="row uniform">
                                    <div class="2u 4u$(xsmall)">
                                        <input disabled="true" type="text"   value="<%=nom%>" placeholder="Nom">
                                    </div>
                                    <div class="4u 4u$(xsmall)">
                                        <input disabled="true" type="text"   value="<%=prenom%>" placeholder="Prénom">
                                    </div>
                                    <div class="3u 4u$(xsmall)">
                                        <input disabled="true" type="text"   value="<%=naissance%>" placeholder="Date de naissance">
                                    </div>
                                    <div class="3u$ 12u$(xsmall)">
                                        <input disabled="true" type="email"  value="<%=email%>" placeholder="Email">
                                    </div>
                                    <%
                                        if(errorMaxRDV != null){
                                            out.print("<div class=' 12u$'>"
                                                    + " <p>"+errorMaxRDV+" </p>"
                                                    + "</div>");
                                        }
                                    %>
                                    <%
                                        if(errorMaxRDVsrv != null){
                                            out.print("<div class=' 12u$'>"
                                                    + " <p>"+errorMaxRDVsrv+" </p>"
                                                    + "</div>");
                                        }
                                    %>
                                    <div class="12u$">
                                        <div class="select-wrapper">
                                            <select required="" name="service" id="demo-category">
                                                <option value="-1">- Service -</option>
                                                <%
                                                    for (int i = 0; i < listeServices.size(); i++) {
                                                        out.print("<option value='" + idServices.get(i) + "'> " + listeServices.get(i) + "</option>");
                                                    }
                                                %>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="12u$">
                                        <input required="" type="date" min="<%=minDate%>" max="<%=maxDate%>" name="dateChoisi"  value="" placeholder="date du rendez vous souhaité">

                                    </div>
                                    <div class="12u$">
                                        <textarea name="message-patient" id="demo-message" placeholder="Enter your message" rows="6"></textarea>
                                    </div>
                                    <div class="12u$">
                                        <ul class="actions">
                                            <li><input type="submit" value="Send Message" class="special"></li>
                                            <li><input type="reset" value="Reset"></li>
                                        </ul>
                                    </div>
                                </div>
                            </form>
                        </section>



                    </div>
                    <!-- Footer -->
                    <footer id="footer">
                        <ul class="icons">
                            <li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
                            <li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
                            <li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
                            <li><a href="#" class="icon fa-dribbble"><span class="label">Dribbble</span></a></li>
                            <li><a href="#" class="icon fa-envelope-o"><span class="label">Email</span></a></li>
                        </ul>
                        <ul class="copyright">
                            <li>&copy; Untitled</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
                        </ul>
                    </footer>

                    </div>

                    <!-- Scripts -->
                    <script src="assets/js/jquery.min.js"></script>
                    <script src="assets/js/jquery.scrollex.min.js"></script>
                    <script src="assets/js/jquery.scrolly.min.js"></script>
                    <script src="assets/js/skel.min.js"></script>
                    <script src="assets/js/util.js"></script>
                    <!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
                    <script src="assets/js/main.js"></script>

                    </body>
                    </html>