/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package dev.yonathaniel.mvntodoapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.yonathaniel.mvntodoapp.db.DbConnection;
import dev.yonathaniel.mvntodoapp.models.MessageModel;
import dev.yonathaniel.mvntodoapp.models.Todo;
import dev.yonathaniel.mvntodoapp.models.Todoitems;
import dev.yonathaniel.mvntodoapp.models.UserModel;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Aviator
 */
@WebServlet(name = "TodoApp", urlPatterns = {"/TodoApp"})
public class TodoApp extends HttpServlet {

    ServletContext ctx;
    DbConnection dbConnection;
    UserModel userModel;

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        ctx = getServletContext();
        dbConnection = (DbConnection) ctx.getAttribute("dbConnection");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        String q=request.getParameter()
        request.getSession().removeAttribute("user");
        request.getSession().invalidate();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("./");
        requestDispatcher.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String q = request.getParameter("q");
        switch (q) {
            case "auth":
                doAuth(request, response);
                break;
            case "createtodo":
                createTodo(request, response);
                break;
            case "createtodoitem":
                createTodoItem(request, response);
                break;
            case "updatetodo":
                updateTodo(request, response);
                break;
            case "deletetodo":
                deleteTodo(request, response);
                break;
            case "deletetodoitem":
                deleteTodoItem(request, response);
                break;
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void doAuth(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getParameter("username");
        UserModel model = App.getInstance(dbConnection).createUser(userName);
        if (model != null) {
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("user", model);
            response.getWriter().write(
                    new ObjectMapper().writeValueAsString(
                            new MessageModel(true, "", model)
                    )
            );
            return;
        }
        response.getWriter().write(
                new ObjectMapper().writeValueAsString(
                        new MessageModel(false, "Error encountered, please try again")
                )
        );
    }

    /*

     */
    private void createTodo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        userModel = (UserModel) request.getSession().getAttribute("user");
        if (userModel == null) {
            response.getWriter().write(
                    new ObjectMapper().writeValueAsString(
                            new MessageModel(false, "Session Time Out!")
                    )
            );
            return;
        }
        String title, duedate, desc;
        title = request.getParameter("title");
        duedate = request.getParameter("duedate");
        desc = request.getParameter("desc");

        Todo todo = App.getInstance(dbConnection).createTodo(
                new Todo(userModel.getUsr_id(), title, desc, duedate)
        );
        if (todo != null) {
            response.getWriter().write(
                    new ObjectMapper().writeValueAsString(
                            new MessageModel(true, "Item created successfully", todo)
                    )
            );
            return;
        }
        response.getWriter().write(
                new ObjectMapper().writeValueAsString(
                        new MessageModel(false, "Failed, please try again")
                )
        );
    }

    /*

     */
    private void createTodoItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        userModel = (UserModel) request.getSession().getAttribute("user");
        if (userModel == null) {
            response.getWriter().write(
                    new ObjectMapper().writeValueAsString(
                            new MessageModel(false, "Session Time Out!")
                    )
            );
            return;
        }
        String id;
        String desc;

        id = request.getParameter("id");
        desc = request.getParameter("desc");
        Todoitems todo = App.getInstance(dbConnection).createTodoitems(
                new Todoitems(Integer.parseInt(id), desc)
        );
        if (todo != null) {
            response.getWriter().write(
                    new ObjectMapper().writeValueAsString(
                            new MessageModel(true, "Item created successfully", todo)
                    )
            );
            return;
        }
        response.getWriter().write(
                new ObjectMapper().writeValueAsString(
                        new MessageModel(false, "Failed, please try again")
                )
        );
    }

    /*

     */
    private void updateTodo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        userModel = (UserModel) request.getSession().getAttribute("user");
        if (userModel == null) {
            response.getWriter().write(
                    new ObjectMapper().writeValueAsString(
                            new MessageModel(false, "Session Time Out!")
                    )
            );
            return;
        }
        String id, title, duedate, status, desc;
        id = request.getParameter("id");
        title = request.getParameter("title");
        duedate = request.getParameter("duedate");
        status = request.getParameter("status");
        desc = request.getParameter("desc");
        Todo t = new Todo();
        t.setNtId(Integer.parseInt(id));
        t.setNtTitle(title);
        t.setNtDate(duedate);
        t.setNtDesc(desc);
        t.setNtStatus(Integer.parseInt(status));
        Todo todo = App.getInstance(dbConnection).updateTodo(t);
        if (todo != null) {
            response.getWriter().write(
                    new ObjectMapper().writeValueAsString(
                            new MessageModel(true, "Item updated successfully", todo)
                    )
            );
            return;
        }
        response.getWriter().write(
                new ObjectMapper().writeValueAsString(
                        new MessageModel(false, "Failed, please try again")
                )
        );
    }

    /*

     */
    private void deleteTodo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        userModel = (UserModel) request.getSession().getAttribute("user");
        if (userModel == null) {
            response.getWriter().write(
                    new ObjectMapper().writeValueAsString(
                            new MessageModel(false, "Session Time Out!")
                    )
            );
            return;
        }

        String id = request.getParameter("id");
        if (App.getInstance(dbConnection).deleteTodo(new Todo(Integer.parseInt(id)))) {
            response.getWriter().write(
                    new ObjectMapper().writeValueAsString(
                            new MessageModel(true, "Todo item deleted")
                    )
            );
            return;
        }
        response.getWriter().write(
                new ObjectMapper().writeValueAsString(
                        new MessageModel(false, "Session Time Out!")
                )
        );
    }

    /*

     */
    private void deleteTodoItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        userModel = (UserModel) request.getSession().getAttribute("user");
        if (userModel == null) {
            response.getWriter().write(
                    new ObjectMapper().writeValueAsString(
                            new MessageModel(false, "Session Time Out!")
                    )
            );
            return;
        }

        String id = request.getParameter("id");
        if (App.getInstance(dbConnection).deleteTodoitems(new Todoitems(Integer.parseInt(id)))) {
            response.getWriter().write(
                    new ObjectMapper().writeValueAsString(
                            new MessageModel(true, "Todo item deleted")
                    )
            );
            return;
        }
        response.getWriter().write(
                new ObjectMapper().writeValueAsString(
                        new MessageModel(false, "Session Time Out!")
                )
        );
    }

}
