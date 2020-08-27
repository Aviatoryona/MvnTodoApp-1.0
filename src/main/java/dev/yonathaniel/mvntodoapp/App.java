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

import dev.yonathaniel.mvntodoapp.db.DbConnection;
import dev.yonathaniel.mvntodoapp.models.MessageModel;
import dev.yonathaniel.mvntodoapp.models.Todo;
import dev.yonathaniel.mvntodoapp.models.TodoAll;
import dev.yonathaniel.mvntodoapp.models.Todoitems;
import dev.yonathaniel.mvntodoapp.models.UserModel;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aviator
 */
public class App implements AppI {

    private final DbConnection dbConnection;
    private static App app;
    private final String[] colors = {
        "w3-amber",
        "w3-pink",
        "w3-orange",
        "w3-teal",
        "w3-green",
        "w3-blue",
        "w3-purple",
        "w3-yellow"
    };

    private App(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public static App getInstance(DbConnection dbConnection) {
        if (app == null) {
            app = new App(dbConnection);
        }
        return app;
    }

    @Override
    public UserModel createUser(String userName) {
        try {
            PreparedStatement ps = dbConnection.getPreparedStatement("SELECT * FROM users WHERE usr_username=?");
            ps.setString(1, userName);
            ResultSet rs = dbConnection.executeQuery(ps);
            if (rs.next()) {
                return new UserModel(
                        rs.getInt("usr_id"),
                        rs.getString("usr_username"),
                        rs.getString("usr_date"));

            }
            ps = dbConnection.getPreparedStatement("INSERT INTO users(usr_username) VALUES(?)");
            ps.setString(1, userName);
            dbConnection.execute(ps);
            return createUser(userName);
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Todo createTodo(Todo todo) {
        try {
            PreparedStatement ps = dbConnection.getPreparedStatement(
                    "INSERT INTO todo(`nt_userid`, `nt_title`, `nt_desc`, `nt_color`,`nt_duedate`) VALUES(?,?,?,?,?)");
            ps.setInt(1, todo.getNtUserid());
            ps.setString(2, todo.getNtTitle());
            ps.setString(3, todo.getNtDesc());
            ps.setString(4, colors[new Random().nextInt(colors.length - 1)]);
            ps.setString(5, todo.getNtDuedate());
            if (dbConnection.execute(ps)) {
                ps = dbConnection.getPreparedStatement("SELECT * FROM todo WHERE nt_userid=? ORDER BY nt_id DESC LIMIT 1");
                ps.setInt(1, todo.getNtUserid());
                ResultSet rs = dbConnection.executeQuery(ps);
                if (rs.next()) {
                    return new Todo(
                            rs.getInt("nt_id"),
                            rs.getInt("nt_userid"),
                            rs.getString("nt_title"),
                            rs.getString("nt_desc"),
                            rs.getString("nt_color"),
                            rs.getInt("nt_status"),
                            rs.getString("nt_duedate"),
                            rs.getString("nt_date"));
                }
            }

            return null;

        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Todoitems createTodoitems(Todoitems todoitems) {
        try {
            String sql = "INSERT INTO todoitems( `ni_noteid`, `ni_text`) VALUES(?,?)";
            PreparedStatement ps = dbConnection.getPreparedStatement(sql);
            ps.setInt(1, todoitems.getNiNoteid());
            ps.setString(2, todoitems.getNiText());
            if (dbConnection.execute(ps)) {
                sql = "SELECT * FROM todoitems WHERE ni_noteid=? ORDER BY ni_id DESC LIMIT 1";
                ps = dbConnection.getPreparedStatement(sql);
                ps.setInt(1, todoitems.getNiNoteid());
                ResultSet rs = dbConnection.executeQuery(ps);
                if (rs.next()) {
                    return new Todoitems(
                            rs.getInt("ni_id"),
                            rs.getInt("ni_noteid"),
                            rs.getString("ni_text"),
                            rs.getInt("nt_status"),
                            rs.getString("nt_date")
                    );
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Todo getTodo(int ntId) {
        try {
            PreparedStatement ps = dbConnection.getPreparedStatement("SELECT * FROM todo WHERE nt_id=?;");
            ps.setInt(1, ntId);
            ResultSet rs = dbConnection.executeQuery(ps);
            if (rs.next()) {
                return new Todo(
                        rs.getInt("nt_id"),
                        rs.getInt("nt_userid"),
                        rs.getString("nt_title"),
                        rs.getString("nt_desc"),
                        rs.getString("nt_color"),
                        rs.getInt("nt_status"),
                        rs.getString("nt_duedate"),
                        rs.getString("nt_date"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Todoitems getTodoitems(int ni_id) {
        try {
            String sql = "SELECT * FROM todoitems WHERE ni_id=?;";
            PreparedStatement ps = dbConnection.getPreparedStatement(sql);
            ps.setInt(1, ni_id);
            ResultSet rs = dbConnection.executeQuery(ps);
            if (rs.next()) {
                return new Todoitems(
                        rs.getInt("ni_id"),
                        rs.getInt("ni_noteid"),
                        rs.getString("ni_text"),
                        rs.getInt("nt_status"),
                        rs.getString("nt_date")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Todo> getTodos(int usr_id) {
        try {
            String sql = "SELECT * FROM todo WHERE nt_userid=? ORDER BY nt_id DESC LIMIT 300";
            PreparedStatement ps = dbConnection.getPreparedStatement(sql);
            ps.setInt(1, usr_id);
            ResultSet rs = dbConnection.executeQuery(ps);
            List<Todo> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Todo(
                        rs.getInt("nt_id"),
                        rs.getInt("nt_userid"),
                        rs.getString("nt_title"),
                        rs.getString("nt_desc"),
                        rs.getString("nt_color"),
                        rs.getInt("nt_status"),
                        rs.getString("nt_duedate"),
                        rs.getString("nt_date")));
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Todoitems> getTodoitemses(int ntid) {
        try {
            String sql = "SELECT * FROM todoitems WHERE ni_noteid=? ORDER BY ni_id DESC";
            PreparedStatement ps = dbConnection.getPreparedStatement(sql);
            ps.setInt(1, ntid);
            ResultSet rs = dbConnection.executeQuery(ps);
            List<Todoitems> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Todoitems(
                        rs.getInt("ni_id"),
                        rs.getInt("ni_noteid"),
                        rs.getString("ni_text"),
                        rs.getInt("nt_status"),
                        rs.getString("nt_date")));
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public TodoAll getTodoAlls(int ntid) {
        String sql = "SELECT * FROM todoitems,todo WHERE (todoitems.ni_noteid=todo.nt_id) AND (todo.nt_id=?) ORDER BY todo.nt_id DESC LIMIT 1000";
        try {
            PreparedStatement ps = dbConnection.getPreparedStatement(sql);
            ps.setInt(1, ntid);
            ResultSet rs = dbConnection.executeQuery(ps);
            List<Todoitems> list = new ArrayList<>();
            Todo todo = null;
            while (rs.next()) {
                if (todo == (null)) {
                    todo = new Todo(
                            rs.getInt("nt_id"),
                            rs.getInt("nt_userid"),
                            rs.getString("nt_title"),
                            rs.getString("nt_desc"),
                            rs.getString("nt_color"),
                            rs.getInt("nt_status"),
                            rs.getString("nt_duedate"),
                            rs.getString("nt_date"));
                }
                list.add(new Todoitems(
                        rs.getInt("ni_id"),
                        rs.getInt("ni_noteid"),
                        rs.getString("ni_text"),
                        rs.getInt("nt_status"),
                        rs.getString("nt_date")));
            }
            return new TodoAll(todo, list);
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<TodoAll> getTodoAll(int usr_id) {
        List<TodoAll> todoAlls = new ArrayList<>();
        List<Todo> todos = getTodos(usr_id);
        if (todos != null) {
            todos.forEach(todo -> {
                List<Todoitems> todoitemses = getTodoitemses(todo.getNtId());
                todoAlls.add(new TodoAll(todo, todoitemses));
            });
        }

        return todoAlls;
    }

    @Override
    public Todo updateTodo(Todo todo) {
        try {
            PreparedStatement ps = dbConnection.getPreparedStatement(
                    "UPDATE todo SET `nt_title`=?, `nt_desc`=? ,nt_status=? WHERE nt_id=?");
            ps.setString(1, todo.getNtTitle());
            ps.setString(2, todo.getNtDesc());
            ps.setInt(3, todo.getNtStatus());
            ps.setInt(4, todo.getNtId());
            if (dbConnection.execute(ps)) {
                return getTodo(todo.getNtId());
            }
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Todoitems updateTodoitems(Todoitems todoitems) {
        try {
            String sql = "UPDATE todoitems SET `ni_text`=?,nt_status=? WHERE ni_id=?";
            PreparedStatement ps = dbConnection.getPreparedStatement(sql);
            ps.setString(1, todoitems.getNiText());
            ps.setInt(2, todoitems.getNtStatus());
            ps.setInt(3, todoitems.getNiId());
            if (dbConnection.execute(ps)) {
                return getTodoitems(todoitems.getNiId());
            }
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean deleteTodo(Todo todo) {
        try {
            PreparedStatement preparedStatement = dbConnection.getPreparedStatement("DELETE FROM todo WHERE nt_id=?");
            preparedStatement.setInt(1, todo.getNtId());
            if (dbConnection.execute(preparedStatement)) {
                preparedStatement = dbConnection.
                        getPreparedStatement("DELETE FROM todoitems WHERE ni_noteid=?");
                preparedStatement.setInt(1, todo.getNtId());
                return dbConnection.execute(preparedStatement);
            }

        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean deleteTodoitems(Todoitems todoitems) {
        try {
            PreparedStatement preparedStatement = dbConnection.
                    getPreparedStatement("DELETE FROM todoitems WHERE ni_id=?");
            preparedStatement.setInt(1, todoitems.getNiId());
            return dbConnection.execute(preparedStatement);
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public MessageModel getResponse(Object object) {
        return new MessageModel(true, "", object);
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

}
