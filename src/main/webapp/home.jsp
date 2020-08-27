<%--
    Document   : navbar
    Created on : Aug 25, 2020, 9:06:32 PM
    Author     : Aviator
--%>

<%@page import="dev.yonathaniel.mvntodoapp.models.UserModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!UserModel userModel;%>

<%
    if (session.getAttribute("user") != null) {
        userModel = (UserModel) session.getAttribute("user");
    } else {
        response.sendRedirect("index.html");
        return;
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <%@include file="styles.jsp" %>%>
    </head>
    <body>

        <!-- Sidebar/menu -->
        <%@include file="navbar.jsp" %>%>

        <!-- Top menu on small screens -->
        <header class="w3-container w3-top w3-hide-large w3-red w3-xlarge w3-padding">
            <a href="javascript:void(0)" class="w3-button w3-red w3-margin-right" onclick="w3_open()">☰</a>
            <span>Amazing TODO</span>
        </header>

        <!-- Overlay effect when opening sidebar on small screens -->
        <div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

        <!-- !PAGE CONTENT! -->
        <div class="w3-main" style="margin-left:340px;margin-right:40px">

            <div class="w3-container" id="designers" style="margin-top:75px">
                <h1 class="w3-xxxlarge w3-text-red"><b>My TODOs.</b></h1>
                <hr style="width:50px;border:5px solid red" class="w3-round">
            </div>

            <div id="mytodos" class="w3-row-padding w3-grayscale"></div>
            <!-- End page content -->
        </div>

        <%@include  file="create_todo_modal.jsp" %>
        <%@include  file="create_todo_item_modal.jsp" %>
        <!-- W3.CSS Container -->
        <div class="w3-light-grey w3-container w3-padding-32" style="margin-top:75px;padding-right:58px"><p class="w3-right">Powered by <a href="#" target="_blank" class="w3-hover-opacity">AmazingTODO</a></p></div>

        <!--===============================================================================================-->
        <script src="assets/vendor/jquery/jquery-3.2.1.min.js"></script>
        <script src="assets/js/app.js"></script>
        <script>
            // Script to open and close sidebar
            function w3_open() {
                document.getElementById("mySidebar").style.display = "block";
                document.getElementById("myOverlay").style.display = "block";
            }

            function w3_close() {
                document.getElementById("mySidebar").style.display = "none";
                document.getElementById("myOverlay").style.display = "none";
            }

            app.homeIndex();

        </script>
    </body>
</html>