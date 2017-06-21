<html dir="ltr"><head>
        <title>Connexion</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
        <link rel="stylesheet" href="assets/css/main.css">
        <!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
        <!--[if lte IE 9]><link rel="stylesheet" href="assets/css/ie9.css" /><![endif]-->
    </head>
    <body contenteditable="false">

        <!-- Page Wrapper -->
        <div id="page-wrapper">

            <!-- Header -->
            <header id="header">
                <h1><a href="#">Se connecter</a></h1>
                <nav id="nav">
                    <ul>
                        <li class="special">
                            <a href="#menu" class="menuToggle"><span>Menu</span></a>
                            <div id="menu">
                                <ul>
                                    <li><a href="index.jsp">Home</a></li>
                                    <li><a href="enregistrer.jsp#">Inscription</a></li>

                                </ul>
                            </div>
                        </li>
                    </ul>
                </nav>
            </header>

            <!-- Main -->
            <article id="main">
                <header >
                    <h2>Connexion</h2>
                    <p>veuillez remplir les champs</p>
                </header>
                <section class="wrapper style5">
                    <div class="inner">
                        <section>
                            <h4>Formulaire</h4>
                            <form method="post" action="./UserConnect">
                                <div class="row uniform align-center">
                                    <%
                                        String errorMSG = (String) request.getAttribute("error");
                                        if (errorMSG != null) {
                                            out.write(""
                                                    + "<div class='12u$' >"
                                                    + "<label class='12u$' >" + errorMSG + "</label>"
                                                    + "</div>");
                                            request.removeAttribute("error");
                                        }

                                    %>

                                    <div class="12u$" >
                                        <input type="text" name="username" id="demo-name" value="" placeholder="Nom Utilisateur" required="">
                                    </div>
                                    <div class="12u$">
                                        <input pattern="^\S{6,}$" type="password" name="password" id="demo-name" value="" placeholder="mot de passe" required="" >
                                    </div>

                                    <div class="12u$">
                                        <ul class="actions">
                                            <li><input type="submit" value="Se Connecter" class="special"></li>
                                            <li><input type="reset" value="Vider Les Champs"></li>
                                        </ul>
                                    </div>
                                </div>
                            </form>
                        </section>
                    </div>
                </section>
            </article>

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
                    <li>© Untitled</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
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