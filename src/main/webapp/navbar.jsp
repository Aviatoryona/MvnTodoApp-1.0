<%--
    Document   : navbar
    Created on : Aug 25, 2020, 9:06:32 PM
    Author     : Aviator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="w3-sidebar w3-red w3-collapse w3-top w3-large w3-padding" style="z-index:3;width:300px;font-weight:bold;" id="mySidebar"><br>
    <a href="javascript:void(0)" onclick="w3_close()" class="w3-button w3-hide-large w3-display-topleft" style="width:100%;font-size:22px">Close Menu</a>
    <div class="w3-container">
        <h3 class="w3-padding-64"><b><%=  userModel.getUsr_username()%></b></h3>
    </div>
    <div class="w3-bar-block">
        <a href="#" onclick="w3_close()" class="w3-bar-item w3-button w3-hover-white">Home</a>
        <a href="javascript:void(0)" onclick="showCreateTodoModal()" class="w3-bar-item w3-button w3-hover-white">Create New</a>
        <a href="TodoApp"  class="w3-bar-item w3-button w3-hover-white">Log Out</a>
    </div>
</nav>
