<%
   String error = (String) request.getAttribute("error");
   if(error == null) error = "";
%>

<html dir="ltr"><head>
    <title>Formulaire d'inscription</title>
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
            <h1><a href="#">Formulaire D'inscription</a></h1>
            <nav id="nav">
                <ul>
                    <li class="special">
                        <a href="#menu" class="menuToggle"><span>Menu</span></a>
                        <div id="menu">
                            <ul>
                                <li><a href="index.jsp">Acceuil</a></li>
                                <li><a href="seconnecter.jsp">Connexion</a></li>
                            
                            </ul>
                        </div>
                    </li>
                </ul>
            </nav>
        </header>

        <!-- Main -->
        <article id="main">
            <header >
                <h2>Inscription</h2>
                <p>veuillez remplir le formulaire d inscription</p>
                <p><%=error%></p>
            </header>
            <section class="wrapper style5">
                <div class="inner">
                    <section>
                        <h4>Formulaire</h4>
                        <form method="post" action="./UserRegister">
                            <div class="row uniform align-center">
                                <div class="12u$" >
                                    <input type="text" name="nom" id="demo-name" value="" placeholder="Nom" required="">
                                </div>
                                <div class="12u$" >
                                    <input type="text" name="prenom" id="demo-name" value="" placeholder="Prénom" required="">
                                </div>
                                <div class="4u 12u$(mediun)">
                                    <input type="radio" id="Homme" name="sexe" value="on">
                                    <label for="Homme">Homme</label>
                                </div>
                                <div class="4u 12u$(medium)">
                                    <input type="radio" id="Femme" name="sexe" value="off">
                                    <label for="Femme">Femme</label>
                                </div>
                                <div class="12u$" >
                                    <input type="date" name="dt_naissance" id="demo-name" value="" placeholder="Date de naissance" required="">
                                </div>
                                <div class="12u$" >
                                    <input type="text" name="adresse" id="demo-name" value="" placeholder="Adresse" required="">
                                </div>
                                <div class="12u$" >
                                    <input type="text" name="telephone" id="demo-name" value="" placeholder="Téléphone" required="">
                                </div>

                                <div class="12u$" >
                                    <input type="text" name="username" id="demo-name" value="" placeholder="Nom Utilisateur" required="">
                                </div>
                                <div class="12u$">
                                    <input type="email" name="email" id="demo-name" value="" placeholder="Email" required>
                                </div>
                                <div class="12u$">
                                    <input pattern="^\S{6,}$" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Must have at least 6 characters' : '');
                                            if (this.checkValidity())
                                                form.password_two.pattern = this.value;" type="password" name="password" id="demo-name" value="" placeholder="mot de passe" required="" >
                                </div>
                                <div class=" 12u$">
                                    <input type="password" name="password" id="password_two" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Please enter the same Password as above' : '');" value="" placeholder="confirmer mot de passe" required="">
                                </div>

                                <div class="12u$">
                                    <ul class="actions">
                                        <li><input type="submit" value="Créer Compte" class="special"></li>
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