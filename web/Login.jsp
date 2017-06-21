
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="controller.Constants" %>


<!DOCTYPE html>

<html><head>

        <link rel="stylesheet" href="style.css" type="text/css">

        <title>medical care login manager</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body contenteditable="false">
        <!-- Static container -->
        <div class="login-wrap">
            <div class="login-html">
                <input id="tab-1" type="radio" name="tab" class="sign-in" checked="" contenteditable="false"><label contenteditable="false" for="tab-1" class="tab">Se connecter </label>
                <input id="tab-2" type="radio" name="tab" class="sign-up" contenteditable="false" ><label contenteditable="false" for="tab-2" class="tab">S'inscrire</label>
                <div class="login-form">

                    <div class="sign-in-htm">
                        <form action="./UserConnect" name="login" method="POST">
                            <div class="group">
                                <label contenteditable="false" for="user" class="label">NOM D'UTILISATEUR</label>
                                <input required="" id="user" type="text" name="<%=Constants.USERNAME%>" class="input" value="${username}">
                            </div>
                            <div class="group">
                                <label contenteditable="false" for="pass" class="label">MOT DE PASSE</label>
                                <input required="" id="pass" type="password" class="input" name="<%=Constants.PASSWORD%>" data-type="password" value="${password}">
                            </div>
                            <div class="group">
                                <input contenteditable="false" type="submit" class="button" value="Se connecter">
                            </div>

                            <div class="hra"></div>
                            <%
                                String message;
                                try {

                                    message = (String) request.getAttribute(Constants.ERROR);
                                } catch (Exception e) {
                                    message = "";
                                }
                                if (message != null) {
                            %>
                            <p style="color: white">
                                &nbsp;&nbsp;&nbsp;&nbsp; <%=message%>
                            </p>
                            <% }%>
                            <div class="hr"></div>

                            <div class="foot-lnk">
                                <a href="#forgot">Mot de passe oublié?</a>
                            </div>
                        </form>
                    </div>
                    <div class="sign-up-htm">
                        <form action="./UserRegister" name="signup" method="POST">
                            <div class="group">
                                <label contenteditable="false"  for="user" class="label">NOM D'UTILISATEUR</label>
                                <input required="" id="user" type="text" name="<%=Constants.USERNAME%>" class="input" value="${username}">
                            </div>
                            <div class="group">
                                <label contenteditable="false" for="pass" class="label">MOT DE PASSE</label>
                                <input required pattern="^\S{6,}$" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Must have at least 6 characters' : ''); if (this.checkValidity())
                                            form.password_two.pattern = this.value;" id="pass" type="password" class="input" name="<%=Constants.PASSWORD%>" data-type="password" >
                            </div>
                            <div class="group">
                                <label contenteditable="false" for="confirmPass" class="label">RÉPÉTER MOT DE PASSE</label>
                                <input class="input" id="password_two" name="password_two" type="password" pattern="^\S{6,}$" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Please enter the same Password as above' : '');" >

                            </div>
                            <div class="group">
                                <label contenteditable="false" for="email" class="label">ADRESSE E-MAIL</label>
                                <input required="" id="email" type="text" name="<%=Constants.EMAIL%>" class="input" value="${email}">
                            </div>
                            <div class="group">
                                <input type="submit" class="button" value="S'inscrire">
                            </div>
                            <div class="hr"></div>
                        </form>



                    </div>
                </div>
            </div>
        </div>

    </body></html>
