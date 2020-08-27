<%--
    Document   : home_template
    Created on : Aug 26, 2020, 7:30:55 AM
    Author     : Aviator
--%>

<%@page import="dev.yonathaniel.mvntodoapp.models.Todoitems"%>
<%@page import="dev.yonathaniel.mvntodoapp.models.Todo"%>
<%@page import="dev.yonathaniel.mvntodoapp.db.DbConnection"%>
<%@page import="dev.yonathaniel.mvntodoapp.App"%>
<%@page import="java.util.List"%>
<%@page import="dev.yonathaniel.mvntodoapp.models.TodoAll"%>
<%@page import="dev.yonathaniel.mvntodoapp.models.UserModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!UserModel userModel;%>

<%
    if (session.getAttribute("user") != null) {
        userModel = (UserModel) session.getAttribute("user");
    }
    if (userModel != null) {
        DbConnection dbConnection = (DbConnection) getServletContext().getAttribute("dbConnection");
        List<TodoAll> todoAlls = App.getInstance(dbConnection).getTodoAll(userModel.getUsr_id());
        if (!todoAlls.isEmpty()) {
            for (TodoAll todoAll : todoAlls) {
                Todo todo = todoAll.getTodo();
                if (todo == null) {
                    continue;
                }
                List<Todoitems> todoitemses
                        = todoAll.getTodoitemses();
%>
<div class="w3-col m4 w3-margin-bottom w3-animate-zoom">
    <div class="w3-card-4 w3-round w3-round-xlarge">
        <header class="w3-container <%= todo.getNtColor()%> text-center">
            <h3><%= todo.getNtTitle()%></h3>
            <span class="w3-tiny">Created: <%= todo.getNtDate()%></span>
            <div class="w3-margin-right">
                <span class="w3-tiny">Due: <%= todo.getNtDuedate()%></span></div>
        </header>
        <div class="w3-container w3-padding-24">
            <ul class="w3-ul w3-hoverable">
                <%
                    if (todoitemses != null || !todoitemses.isEmpty()) {
                        for (Todoitems todoitemse : todoitemses) {
                %>
                <li id="<%= todoitemse.getNiId()%>"><span class="w3-small w3-text-black"><%= todoitemse.getNiText()%></span>
                    <span class="w3-tiny w3-text-red"><%= todoitemse.getNtDate()%></span>
                    <span onclick="deleteTodoItem(<%= todoitemse.getNiId()%>)"
                          class="w3-closebtn w3-margin-right w3-medium">x</span>
                </li>
                <%}
                } else {%>
                <li><a href="">Create Item</a></li>
                    <%}%>
            </ul>
            <span class="w3-tiny"><%= todo.getNtDesc()%></span>
        </div>

        <footer class="w3-container <%= todo.getNtColor()%> w3-center">
            <button class="w3-btn w3-ripple w3-blue w3-tiny" onclick="showCreateTodoItemModal(<%= todo.getNtId()%>)">Add Item</button>
            <button class="w3-btn w3-ripple w3-green w3-tiny" onclick="updateTodoShowModal(<%= todo.getNtId()%>, '<%= todo.getNtTitle()%>', '<%= todo.getNtDesc()%>', '<%= todo.getNtDuedate()%>')">Update</button>
            <button class="w3-btn w3-ripple w3-red w3-tiny" onclick="deleteTodo(<%= todo.getNtId()%>)">Delete</button>
        </footer>
    </div>
</div>
<%
    }
} else {
%>
<h3 class="w3-text-teal w3-animate-zoom">You do not have todos, <button class="w3-btn w3-small w3-amber w3-round w3-round-small w3-ripple" onclick="showCreateTodoModal()">Create TODO</button></h3>
<%
    }
} else {
%>
<h3 class="w3-text-red w3-animate-fading">Session Time Out</h3>
<%
    }
%>
