<%@page import="java.time.LocalDate"%>
<%

    boolean isMedecin = ((String) (request.getServletContext().getAttribute("isMedecin"))).equals("medecin");
    String name = (String) request.getServletContext().getAttribute("current-secretaire-name");
    String lastName = (String) request.getServletContext().getAttribute("current-secretaire-lastName");
    if (name == null) {
        request.getRequestDispatcher("http://localhost:9090/clinique_web/faces/administration/Login/index.jsp").forward(request, response);
    }
    String sizeRDVJour = (String) request.getServletContext().getAttribute("RDV-Size");
    String clientCount = (String) request.getServletContext().getAttribute("clients-Size");
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
            <button class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey" onclick="w3_open();"><i class="fa fa-bars"></i>  Menu</button>
            <span class="w3-bar-item w3-right">Clinique Manager V 0.1</span>
        </div>

        <!-- Sidebar/menu -->
        <nav class="w3-sidebar w3-collapse w3-white w3-animate-left" style="z-index:3;width:300px;" id="mySidebar"><br>
            <div class="w3-container w3-row">
                <div class="w3-col s4">
                    <%if (isMedecin) {%>
                    <img src="../images/doctorMan.png" class="w3-circle w3-margin-right" style="width:46px">
                    <%} else {%>
                    <img src="../images/stewardess.png" class="w3-circle w3-margin-right" style="width:46px">
                    <%}%>
                </div>
                <div class="w3-col s8 w3-bar">
                    <span>Bonjour, <strong> <%=name%> <%=lastName%></strong></span><br>
                    <a href="./LogoutAdmin" class="w3-bar-item w3-button"><i class="fa fa-sign-out"></i> se déconnecter</a>
                </div>

            </div>
            <hr>
            <div class="w3-container">
                <h5>Menu</h5>
            </div>
            <%if (isMedecin) {%>
            <div class="w3-bar-block">
                <a href="#" class="w3-bar-item w3-button w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black" onclick="w3_close()" title="close menu"><i class="fa fa-remove fa-fw"></i>  Close Menu</a>
                <a href="./Board" class="w3-bar-item w3-button w3-padding w3-blue"><i class="fa fa-home fa-fw"></i>  Prinipal</a>
                <a href="./Consultation" class="w3-bar-item w3-button w3-padding"><i class="fa fa-stethoscope fa-fw"></i>  Consultation</a>
                <a href="./Medicaments" class="w3-bar-item w3-button w3-padding"><i class="fa fa-medkit fa-fw"></i>  Medicaments et Investigation</a>
                <a href="./Personnel" class="w3-bar-item w3-button w3-padding"><i class="fa fa-user-md fa-fw"></i>  Personnel </a>
                <a href="#" class="w3-bar-item w3-button w3-padding"><i class="fa fa-cog fa-fw"></i>  Settings</a><br><br>
            </div>
            <%} else {%>
            <div class="w3-bar-block">
                <a href="#" class="w3-bar-item w3-button w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black" onclick="w3_close()" title="close menu"><i class="fa fa-remove fa-fw"></i>  Close Menu</a>
                <a href="./Board" class="w3-bar-item w3-button w3-padding w3-blue"><i class="fa fa-home fa-fw"></i>  Prinipal</a>
                <a href="./ShowAllClient" class="w3-bar-item w3-button w3-padding "><i class="fa fa-users fa-fw"></i>  Liste des clients</a>
                <a href="./AllRDV" class="w3-bar-item w3-button w3-padding"><i class="fa fa-calendar fa-fw"></i>  Rendez-Vous</a>
                <a href="./Medicaments" class="w3-bar-item w3-button w3-padding"><i class="fa fa-medkit fa-fw"></i>  Medicaments et Investigation</a>
                <a href="./Personnel" class="w3-bar-item w3-button w3-padding"><i class="fa fa-user-md fa-fw"></i>  Personnel </a>
                <a href="./ServiceRedirect" class="w3-bar-item w3-button w3-padding"><i class="fa fa-bank fa-fw"></i>  Service</a>
                <a href="#" class="w3-bar-item w3-button w3-padding"><i class="fa fa-history fa-fw"></i>  History</a>
                <a href="#" class="w3-bar-item w3-button w3-padding"><i class="fa fa-cog fa-fw"></i>  Settings</a><br><br>
            </div>
            <%}%>
        </nav>


        <!-- Overlay effect when opening sidebar on small screens -->
        <div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

        <!-- !PAGE CONTENT! -->
        <div class="w3-main" style="margin-left:300px;margin-top:43px;">

            <!-- Header -->
            <header class="w3-container" style="padding-top:22px">
                <h5><b><i class="fa fa-dashboard"></i> Menu Raccoursi</b></h5>
            </header>
            <!-- Top Menu-->
            <div class="w3-row-padding w3-margin-bottom">
              <%if (isMedecin) {%>
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
                <%}%>
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
            <!-- Footer -->
            <!-- Footer -->
            <footer class="w3-container w3-padding-16 w3-light-grey">
                <h4>Clinique v 0.1</h4>
                <p>Développer par :</p>
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