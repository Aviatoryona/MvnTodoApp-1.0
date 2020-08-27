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
package dev.yonathaniel.mvntodoapp.models;

import java.util.List;

/**
 *
 * @author Aviator
 */
public class TodoAll {

    private Todo todo;
    private List<Todoitems> todoitemses;

    public TodoAll(Todo todo, List<Todoitems> todoitemses) {
        this.todo = todo;
        this.todoitemses = todoitemses;
    }

    /**
     * @return the todo
     */
    public Todo getTodo() {
        return todo;
    }

    /**
     * @param todo the todo to set
     */
    public void setTodo(Todo todo) {
        this.todo = todo;
    }

    /**
     * @return the todoitemses
     */
    public List<Todoitems> getTodoitemses() {
        return todoitemses;
    }

    /**
     * @param todoitemses the todoitemses to set
     */
    public void setTodoitemses(List<Todoitems> todoitemses) {
        this.todoitemses = todoitemses;
    }

}
