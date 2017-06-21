<%@page import="entity.Service"%>
<%@page import="entity.Patient"%>
<%@page import="entity.Consultation"%>
<%@page import="entity.RDV"%>
<%@page import="java.util.List"%>
<%@page import="java.time.LocalDate"%>
<%
    
boolean isMedecin = ((String) (request.getServletContext().getAttribute("isMedecin"))).equals("medecin");
    String name =""+ (String) request.getServletContext().getAttribute("current-secretaire-name");
    String lastName = ""+ (String) request.getServletContext().getAttribute("current-secretaire-lastName");
    if (name == null) {
        request.getRequestDispatcher("http://localhost:9090/clinique_web/faces/administration/Login/index.jsp").forward(request, response);
    }
    String sizeRDVJour =""+ (String) request.getServletContext().getAttribute("RDV-Size");
    String clientCount =""+ (String) request.getServletContext().getAttribute("clients-Size");

   
    List<Service> allServices = (List<Service>) request.getServletContext().getAttribute("allServices");
    List<Patient> allPatients = (List<Patient>) request.getServletContext().getAttribute("allPatients");
    List<Consultation> allConsultations = (List<Consultation>) request.getServletContext().getAttribute("allConsultations");
    int size = 0;
    if (allConsultations != null) {
        size = allConsultations.size();
    }
%>

<!DOCTYPE html>
<html>
    <title>Clinique Manager v 0.1</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="http://127.0.0.1:9090/clinique_web/faces/administration/css/cdnjs.cloudflare.com_ajax_libs_font-awesome_4.7.0_css_font-awesome.min.css">
    <link rel="stylesheet" href="http://127.0.0.1:9090/clinique_web/faces/administration/css/w3css.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
    </style>
    <body class="w3-light-grey">

        <!-- Top container -->
        <div class="w3-bar w3-top w3-black w3-large" style="z-index:4">
            <button class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey" onclick="w3_open();"><i class="fa fa-bars"></i> �Menu</button>
            <span class="w3-bar-item w3-right">Clinique Manager V 0.1</span>
        </div>

        <!-- Sidebar/menu -->
        <nav class="w3-sidebar w3-collapse w3-white w3-animate-left" style="z-index:3;width:300px;" id="mySidebar"><br>
            <div class="w3-container w3-row">
                <div class="w3-col s4">
                    <img src="../images/stewardess.png" class="w3-circle w3-margin-right" style="width:46px">
                </div>
               <div class="w3-col s8 w3-bar">
                    <span>Bonjour, <strong> <%=name%> <%=lastName%></strong></span><br>
                    <a href="./LogoutAdmin" class="w3-bar-item w3-button"><i class="fa fa-sign-out"></i>se d�connecter</a>
                </div>
            </div>
            <hr>
                        <div class="w3-container">
                <h5>Menu</h5>
            </div>
               </div>
            <%if (isMedecin) {%>
            <div class="w3-bar-block">
                <a href="#" class="w3-bar-item w3-button w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black" onclick="w3_close()" title="close menu"><i class="fa fa-remove fa-fw"></i>� Close Menu</a>
                <a href="./Board" class="w3-bar-item w3-button w3-padding w3-blue"><i class="fa fa-home fa-fw"></i>� Prinipal</a>
                <a href="./Consultation" class="w3-bar-item w3-button w3-padding"><i class="fa fa-stethoscope fa-fw"></i>� Consultation</a>
                <a href="./Medicaments" class="w3-bar-item w3-button w3-padding"><i class="fa fa-medkit fa-fw"></i>� Medicaments et Investigation</a>
                <a href="./Personnel" class="w3-bar-item w3-button w3-padding"><i class="fa fa-user-md fa-fw"></i>� Personnel </a>
                <a href="#" class="w3-bar-item w3-button w3-padding"><i class="fa fa-cog fa-fw"></i>� Settings</a><br><br>
            </div>
            <%} else {%>
            <div class="w3-bar-block">
                <a href="#" class="w3-bar-item w3-button w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black" onclick="w3_close()" title="close menu"><i class="fa fa-remove fa-fw"></i>� Close Menu</a>
                <a href="./Board" class="w3-bar-item w3-button w3-padding w3-blue"><i class="fa fa-home fa-fw"></i>� Prinipal</a>
                <a href="./ShowAllClient" class="w3-bar-item w3-button w3-padding "><i class="fa fa-users fa-fw"></i>� Liste des clients</a>
                <a href="./AllRDV" class="w3-bar-item w3-button w3-padding"><i class="fa fa-calendar fa-fw"></i>� Rendez-Vous</a>
                <a href="./Medicaments" class="w3-bar-item w3-button w3-padding"><i class="fa fa-medkit fa-fw"></i>� Medicaments et Investigation</a>
                <a href="./Personnel" class="w3-bar-item w3-button w3-padding"><i class="fa fa-user-md fa-fw"></i>� Personnel </a>
                <a href="./ServiceRedirect" class="w3-bar-item w3-button w3-padding"><i class="fa fa-bank fa-fw"></i>� Service</a>
                <a href="#" class="w3-bar-item w3-button w3-padding"><i class="fa fa-history fa-fw"></i>� History</a>
                <a href="#" class="w3-bar-item w3-button w3-padding"><i class="fa fa-cog fa-fw"></i>� Settings</a><br><br>
            </div>
            <%}%>
        </nav>


        <!-- Overlay effect when opening sidebar on small screens -->
        <div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

        <!-- !PAGE CONTENT! -->
        <div class="w3-main" style="margin-left:300px;margin-top:43px;">

            <!-- Header -->
            <header class="w3-container" style="padding-top:22px">
                <h5><b><i class="fa fa-dashboard"></i> My Dashboard</b></h5>
            </header>
            <!-- Top Menu-->
            <div class="w3-row-padding w3-margin-bottom">
                <form method="post" action="./Consultation">
                    <button class=" w3-button w3-hover-none w3-quarter" style="min-height: 180px">
                        <div class="w3-container w3-hover-green w3-blue w3-padding-16">
                            <div class="w3-left"><i class="fa fa-stethoscope w3-xxxlarge"></i></div>
                            <div class="w3-right">
                                <h3>99</h3>
                            </div>
                            <div class="w3-clear"></div>
                            <h4>Consultation</h4>
                        </div>
                    </button>
                </form>
                   <%if (!isMedecin) {%>
                <form method="post" action="./AllRDV">
                    <button class=" w3-button w3-hover-none w3-quarter" value="showAllClients" type="submit" style="min-height: 180px">
                        <div class="w3-container w3-hover-aqua w3-teal w3-padding-16">
                            <div class="w3-left"><i class="fa fa-calendar w3-xxxlarge"></i></div>
                            <div class="w3-right">
                                <h3><%=sizeRDVJour%></h3>
                            </div>
                            <div class="w3-clear"></div>
                            <h4> R.D.V: <%=LocalDate.now()%></h4>
                        </div>
                    </button>
                </form>
                <form method="post" action="./ShowAllClient">
                    <button class=" w3-button w3-hover-none w3-quarter" value="showAllClients" type="submit" style="min-height: 180px">
                        <div class=" w3-container w3-hover-yellow w3-orange w3-text-white w3-padding-16">

                            <div class="w3-left"><i class="fa fa-users w3-xxxlarge"></i></div>
                            <div class="w3-right">
                                <h3><%=clientCount%></h3>
                            </div>
                            <div class="w3-clear"></div>
                            <h4 class="w3-left">Clients</h4>

                        </div>
                    </button>
                </form>
                            <%}%>
            </div>
                            
                            
            <div class=" w3-margin w3-card-4" >
                <script>
                    function myFunction() {
                        var input, filter, ul, li, a, i;
                        input = document.getElementById("myInput");
                        filter = input.value.toUpperCase();
                        ul = document.getElementById("myUL");
                        li = ul.getElementsByTagName("li");
                        for (i = 0; i < li.length; i++) {
                            if (li[i].innerHTML.toUpperCase().indexOf(filter) > -1) {
                                li[i].style.display = "";
                            } else {
                                li[i].style.display = "none";
                            }
                        }
                    }
                </script>
                <div class="w3-container w3-green">
                    <h2>Liste des Consultation Effectu�</h2>
                </div>

                <input style="width: 100%" class="w3-input  w3-padding" type="text" placeholder="Recherche" id="myInput" onkeyup="myFunction()">
                <ul class="w3-ul " id="myUL" style="overflow-y: auto;max-height: 457px;">
                    <%
                        if (size > 0) {

                            for (int i = 0; i < size; i++) {
                    %>
                    <li class="w3-padding-16">
                        <form action="./ConsultationRemove" method="post" id="form<%=i%>">
                            <input type="hidden" name="id" value="<%= allConsultations.get(i).getId()%>"/>
                            <button type="submit" style="max-width: 40px" class="w3-button w3-text-black w3-xlarge w3-right w3-hover-red w3-quarter" name="todo" value="info" title="Suprimmer" form="form<%=i%>" >?</button>
                            <button type="submit" style="max-width: 40px" class="w3-button w3-text-black w3-xlarge w3-right w3-hover-indigo w3-quarter" name="todo" value="consult" title="Valider" form="form<%=i%>" >&vrtri;;</button>
                        </form>    
                        <img src="../images/magnifying-glass.png" class="w3-left w3-circle w3-margin-right" style="width:50px">
                        <span class="w3-xlarge"><b><% out.print(allConsultations.get(i).getPatient().getInfoPersonnel().getNom() + " " + allConsultations.get(i).getPatient().getInfoPersonnel().getPrenom() +" n�e en :"+allConsultations.get(i).getPatient().getInfoPersonnel().getDate_naissance());%>  </b></span><br>
                        <span><b>  Medecin : </b><% out.print(allConsultations.get(i).getMedecin().getInfoPersonnel().getNom() +" "+allConsultations.get(i).getMedecin().getInfoPersonnel().getPrenom()); %></span>
                        
                    </li>
                    <%}
                    } else {%>
                    <%}%>
                </ul>
            </div>
            <hr>

            <div class=" w3-margin w3-card-4 w3-white" >
                
                <div class="w3-container w3-lime">
                    <h2>Formulaire d'ajout d'investigation : Etape 1/2</h2>
                </div>
                
                <form action="./AddMedecin" method="post" class="w3-container w3-row-padding w3-margin">
                    
                    <select class="w3-select w3-large w3-margin-bottom" autocomplete="" name ="serviceID">
                        <option value=" " disabled="" selected="">
                            Choisir le Service
                        </option>

                        <%
                            for (int i = 0; i < allServices.size(); i++) {
                        %>
                        <option value="<%out.print(allServices.get(i).getId());%>"> <%out.print(allServices.get(i).getTitre());%> </option>
                        <%}%>
                    </select>
                    
                    <br>
                    <select class="w3-select w3-large w3-margin-bottom" autocomplete="" name ="patientID">
                        <option value=" " disabled="" selected="">
                            Choisir le patient
                        </option>

                        <%
                            for (int i = 0; i < allPatients.size(); i++) {
                        %>
                        <option value="<%out.print(allPatients.get(i).getId());%>"> <%out.print(allPatients.get(i).getInfoPersonnel().getNom() + " " + allPatients.get(i).getInfoPersonnel().getPrenom());%> </option>
                        <%}%>
                    </select>
                    
                    <br>
                    
                    <div class="w3-row">
                        <input class="w3-input w3-large w3-col s3 w3-margin-right w3-border-bottom w3-hover-text-blue w3-hover-border-blue "  required="" name="tension" type="text" placeholder="Mesure de tension " />
                        <input class="w3-input w3-large w3-col s3 w3-margin-right w3-border-bottom w3-hover-text-blue w3-hover-border-blue "  required="" name="taille" type="text" placeholder="Taille " />
                        <input class="w3-input w3-large w3-col s3 w3-margin-right w3-border-bottom w3-hover-text-blue w3-hover-border-blue "  required="" name="poids" type="text" placeholder="Poid " />
                        <input class="w3-input w3-large w3-col s2 w3-border-bottom w3-hover-text-blue w3-hover-border-blue "  required="" name="glecemie" type="text" placeholder="Gl�cemie " />
                    </div>
                    
                    
                    <button class="w3-button w3-indigo w3-margin-bottom w3-margin-top" type="submit"> Confirmer </button>
                    <button class="w3-button w3-teal w3-right w3-margin-bottom w3-margin-top" type="reset"> Annuler </button>
                
                </form>

                
                
            </div>

            <!-- Footer -->
            <!-- Footer -->
            <footer class="w3-container w3-padding-16 w3-light-grey">
                <h4>Clinique v 0.1</h4>
                <p>D�velopper par :</p>
            </footer>

            <!-- End page content -->
            <!-- End page content -->
        </div>

        <script>
            // Get the Sidebar
            var mySidebar = document.getElementById("mySidebar");

            // Get the DIV with overlay effect
            var overlayBg = document.getElementById("myOverlay");

            // Toggle between showing and hiding the sidebar, and add overlay effect
            function w3_open() {
                if (mySidebar.style.display === 'block') {
                    mySidebar.style.display = 'none';
                    overlayBg.style.display = "none";
                } else {
                    mySidebar.style.display = 'block';
                    overlayBg.style.display = "block";
                }
            }

            // Close the sidebar with the close button
            function w3_close() {
                mySidebar.style.display = "none";
                overlayBg.style.display = "none";
            }
        </script>

    </body>
</html>