<%
    String currentUser = (String) request.getServletContext().getAttribute("current-user");
%>

<html dir="ltr"><head>
        <%
            if (currentUser != null) {
        %>
        <title>client conecter : <%= currentUser%></title>
        <%} else {%>
        <title>Clinique</title>
        <%}%>

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
        <link rel="stylesheet" href="assets/css/main.css">
        <!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
        <!--[if lte IE 9]><link rel="stylesheet" href="assets/css/ie9.css" /><![endif]-->
    </head>
    <body class="landing" contenteditable="false">

        <!-- Page Wrapper -->
        <div id="page-wrapper">

            <!-- Header -->
            <header id="header" class="alt">
                <h1><a href="index.html">Clinique</a></h1>
                <nav id="nav">
                    <ul>
                        <li class="special">
                            <a href="#menu" class="menuToggle"><span>Menu</span></a>
                            <div id="menu">
                                <ul>
                                    <li><a href="index.jsp">Acceuil</a></li>

                                    <%

                                        if (currentUser == null) {
                                    %>
                                    <li><a href="enregistrer.jsp">S&apos;Enregister</a></li>
                                    <li><a href="seconnecter.jsp">Se Connecter</a></li>
                                        <%
                                        } else {
                                        %>
                                    <li><a href="sedeconnecter.jsp">Se Deconnecter</a></li>
                                        <%}%>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </nav>
            </header>

            <!-- Banner -->
            <section id="banner">
                <div class="inner">
                    <h2>Clinique</h2>
                    <p>Service M&eacute;dical De Qualit&eacute;<br>
                        Des Proffessionel à votre &eacute;coute<br>
                        realis&eacute; par Imane et Bouchera.</p>
                    <ul class="actions">
                        <%
                            if (currentUser != null) {
                        %>
                        <li><a href="rendezvous.jsp#" class="button special">Rendez-vous</a></li>
                            <%} else {%>
                        <li><a href="seconnecter.jsp#" class="button special">Se Conencter</a></li>
                            <%}%>
                    </ul>
                </div>
                <a href="#one" class="more scrolly">Plus de d&eacute;tail</a>
            </section>

            <!-- One -->
            <section id="one" class="wrapper style1 special">
                <div class="inner">
                    <header class="major">
                        <h2>Finies les files d&apos;attente!<br>
                            Une meilleure r�tention du personnel � l&apos;accueil</h2>
                        <p>Oubliez les files interminables le matin. Dites oui � une arriv�e progressive des patients et au calme dans la salle d&apos;attente! <br>
                            Puisqu&apos;ils peuvent s&apos;inscrire par t�l�phone, sans avoir � quitter le confort de leur foyer, les patients demeurent calmes et sereins. Ils peuvent se reposer la t�te tranquille jusqu&apos;� la date du Rendez-vous.</p>
                    </header>
                </div>

            </section>

            <!-- Two -->
            <section id="two" class="wrapper alt style2">
                <section class="spotlight">
                    <div class="image"><img src="images/gallery/churdent.jpg" alt=""></div>
                    <div class="content">
                        <h2>Laboratoire de Chirurgie Dentaire<br>
                            Un sourire radieux</h2>
                        <p>Lors de la consultation d&apos;admission, vous aborderez tous les sujets concernant le traitement. Nous vous proposons une esth�tique dentaire sur mesure..</p>
                    </div>
                </section>
                <section class="spotlight">
                    <div class="image"><img src="images/gallery/analyse.jpg" alt=""></div><div class="content">
                         <h2>Laboratoire d&apos;Analyse Pathologique<br>
                            La pr&eacute;cision est une priorit&eacute;</h2>
                        <p>Nos m�decins sp�cialistes analysent�au microscope la composition de pr�l�vements tissulaires, liquidiens ou cellulaires r�alis�s par des chirurgiens, des gyn�cologues, des sages-femmes, des m�decins g�n�ralistes ou sp�cialistes (dermatologues, pneumologues, h�pato-gastro-ent�rologues, radiologues etc.).</p>
                    </div>
                </section>
                <section class="spotlight">
                    <div class="image"><img src="images/gallery/Hospitalisation.jpg"  alt=""></div><div class="content">
                        <h2>HOPITALISATION<br/></h2>
                            <h2>S�jour en service d&apos;hospitalisation</h2> 
                        <p>Le service d&apos;hospitalisation compl�te dispose de�76�lits r�partis en�chambres doubles et particuli�res.
Vous pouvez recevoir des visites de vos proches tous les jours de 10h00 � 20h00, la matin�e �tant consacr�e aux soins infirmiers. Pour la tranquillit� des patients, nous demandons de limiter le nombre de visiteurs � deux par patient.� La pr�sence d&apos;enfants en bas �ge est fortement d�conseill�e.</p>
                    </div>
                </section>
            </section>

            <!-- Three -->
            <section id="three" class="wrapper style3 special">
                <br>
                <div class="inner">
                    <header class="major">
                        <h3>Qui sommes nous ?</h3>
                        <div class="image"><img src="images/gallery/final.jpg" style=" max-width: 100%;  margin-bottom:  2%;margin-top:  2%; "  alt=""></div>
                        <p>Une �quipe pluri-dusciplinaire: cardiologue, st�matologues, chirurgient, psychologue et autres ... sont � votre disposition <br>
                        � fin de r�pondre � tout vos besoin, quelque sois votre inqui�tude leur objectif et la joie de vous sentir bien</p>
                    </header>
                </div>
                <a href="#cta" class="more scrolly">Plus de d&eacute;tail</a>
            </section>

            <!-- CTA -->
            <section id="cta" class="wrapper style4">
                <div class="inner">
                    <%
                        if (currentUser != null) {
                    %>
                    <header>
                        <h2><%=currentUser%></h2>
                        <p>Pour prondre un rendez vous medical avec l&apos;un de nos expert <br> veuillez cliquer sur le bouton rendez vous </p>
                    </header>
                    <ul class="actions vertical">
                        <br><br>
                        <li><a href="rendezvous.jsp" class="button fit special">Rendez vous </a></li>
                    </ul>
                    <%
                    } else {
                    %>
                    <header>
                        <h2>Bonjour chere patient</h2>
                        <p>connectez vous afin de pouvoir <br> prendre un rendez vous </p>
                    </header>
                    <ul class="actions vertical">

                        <li><a href="enregistrer.jsp" class="button fit special">S&apos;Inscrire</a></li>
                        <li><a href="seconnecter.jsp" class="button fit special">se connecter </a></li>
                    </ul>
                    <%}%>
                </div>
            </section>

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
                    <li>Â© Untitled</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
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


    </body></html>