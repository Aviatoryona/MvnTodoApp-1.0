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

import java.io.Serializable;

/**
 *
 * @author Aviator
 */
public class Todoitems implements Serializable {

    private Integer niId;
    private int niNoteid;
    private String niText;
    private int ntStatus;
    private String ntDate;

    public Todoitems() {
    }

    public Todoitems(Integer niId) {
        this.niId = niId;
    }

    public Todoitems(int niNoteid, String niText) {
        this.niNoteid = niNoteid;
        this.niText = niText;
    }

    public Todoitems(Integer niId, int niNoteid, String niText, int ntStatus, String ntDate) {
        this.niId = niId;
        this.niNoteid = niNoteid;
        this.niText = niText;
        this.ntStatus = ntStatus;
        this.ntDate = ntDate;
    }

    public Integer getNiId() {
        return niId;
    }

    public void setNiId(Integer niId) {
        this.niId = niId;
    }

    public int getNiNoteid() {
        return niNoteid;
    }

    public void setNiNoteid(int niNoteid) {
        this.niNoteid = niNoteid;
    }

    public String getNiText() {
        return niText;
    }

    public void setNiText(String niText) {
        this.niText = niText;
    }

    public int getNtStatus() {
        return ntStatus;
    }

    public void setNtStatus(int ntStatus) {
        this.ntStatus = ntStatus;
    }

    public String getNtDate() {
        return ntDate;
    }

    public void setNtDate(String ntDate) {
        this.ntDate = ntDate;
    }

    @Override
    public String toString() {
        return "dev.yonathaniel.mvntodoapp.models.Todoitems[ niId=" + niId + " ]";
    }

}
