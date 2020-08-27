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

import dev.yonathaniel.mvntodoapp.models.MessageModel;
import dev.yonathaniel.mvntodoapp.models.Todo;
import dev.yonathaniel.mvntodoapp.models.TodoAll;
import dev.yonathaniel.mvntodoapp.models.Todoitems;
import dev.yonathaniel.mvntodoapp.models.UserModel;
import java.util.List;

/**
 *
 * @author Aviator
 */
public interface AppI {

    UserModel createUser(String userName);

    Todo createTodo(Todo todo);

    Todo getTodo(int ntId);

    Todoitems createTodoitems(Todoitems todoitems);

    Todoitems getTodoitems(int ni_id);

    List<Todo> getTodos(int usr_id);

    List<Todoitems> getTodoitemses(int ntid);

    TodoAll getTodoAlls(int ntid);

    List<TodoAll> getTodoAll(int usr_id);

    Todo updateTodo(Todo todo);

    Todoitems updateTodoitems(Todoitems todoitems);

    boolean deleteTodo(Todo todo);

    boolean deleteTodoitems(Todoitems todoitems);

    MessageModel getResponse(Object object);

}
