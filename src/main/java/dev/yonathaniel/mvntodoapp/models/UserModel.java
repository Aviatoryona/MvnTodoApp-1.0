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

/**
 *
 * @author Aviator
 */
public class UserModel {

    private int usr_id;
    private String usr_username;
    private String usr_date;

    public UserModel(int usr_id, String usr_username, String usr_date) {
        this.usr_id = usr_id;
        this.usr_username = usr_username;
        this.usr_date = usr_date;
    }

    /**
     * @return the usr_id
     */
    public int getUsr_id() {
        return usr_id;
    }

    /**
     * @param usr_id the usr_id to set
     */
    public void setUsr_id(int usr_id) {
        this.usr_id = usr_id;
    }

    /**
     * @return the usr_username
     */
    public String getUsr_username() {
        return usr_username;
    }

    /**
     * @param usr_username the usr_username to set
     */
    public void setUsr_username(String usr_username) {
        this.usr_username = usr_username;
    }

    /**
     * @return the usr_date
     */
    public String getUsr_date() {
        return usr_date;
    }

    /**
     * @param usr_date the usr_date to set
     */
    public void setUsr_date(String usr_date) {
        this.usr_date = usr_date;
    }

}
