<!DOCTYPE html>
<html>
<title>W3.CSS</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://127.0.0.1:9090/clinique_web/faces/administration/css/cdnjs.cloudflare.com_ajax_libs_font-awesome_4.7.0_css_font-awesome.min.css">
    <link rel="stylesheet" href="http://127.0.0.1:9090/clinique_web/faces/administration/css/w3css.css">
<head>
    <style>
        body{
            background: url("css/background.jpg") 50% fixed;
  background-size: cover;
        }
      
        .w3-opacity{
            opacity: 0.95;
        }
    </style>
</head>
<body>

<div class="w3-container" >
  

    <div >
    <div class="w3-modal-content w3-margin-top w3-card-4 w3-opacity w3-hover-opacity-off  w3-animate-zoom" style="max-width:400px">
  
      <div class="w3-center"><br>
          <img src="avatar.jpg" alt="Avatar" style="width:50%" class="w3-circle w3-margin-top">
      </div>

        <form class="w3-container" action="../../LoginAdmin" method="POST">
        <div class="w3-section">
          <input class="w3-input w3-hover-sand w3-margin-bottom" type="text" placeholder="Veuillez Saisir Le Nom D'Utilisateur" name="usrname" required>
          <input class="w3-input w3-hover-sand w3-margin-bottom" type="password" placeholder="Veuillez Saisir LE Mot de Passe" name="psw" required>
          <button class="w3-button w3-block w3-blue-gray w3-section w3-padding" type="submit">Se Connecter</button>
          
        </div>
      </form>


    </div>
  </div>
</div>
            
</body>
</html>
