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
public class Todo implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer ntId;
    private int ntUserid;
    private String ntTitle;
    private String ntDesc;
    private String ntColor;
    private int ntStatus;
    private String ntDuedate;
    private String ntDate;

    public Todo() {
    }

    public Todo(Integer ntId) {
        this.ntId = ntId;
    }

    public Todo(int ntUserid, String ntTitle, String ntDesc, String ntDuedate) {
        this.ntUserid = ntUserid;
        this.ntTitle = ntTitle;
        this.ntDesc = ntDesc;
        this.ntDuedate = ntDuedate;
    }

    public Todo(Integer ntId, int ntUserid, String ntTitle, String ntDesc, String ntColor, int ntStatus, String ntDuedate, String ntDate) {
        this.ntId = ntId;
        this.ntUserid = ntUserid;
        this.ntTitle = ntTitle;
        this.ntDesc = ntDesc;
        this.ntColor = ntColor;
        this.ntStatus = ntStatus;
        this.ntDuedate = ntDuedate;
        this.ntDate = ntDate;
    }

    public Integer getNtId() {
        return ntId;
    }

    public void setNtId(Integer ntId) {
        this.ntId = ntId;
    }

    public int getNtUserid() {
        return ntUserid;
    }

    public void setNtUserid(int ntUserid) {
        this.ntUserid = ntUserid;
    }

    public String getNtTitle() {
        return ntTitle;
    }

    public void setNtTitle(String ntTitle) {
        this.ntTitle = ntTitle;
    }

    public String getNtDesc() {
        return ntDesc;
    }

    public void setNtDesc(String ntDesc) {
        this.ntDesc = ntDesc;
    }

    public String getNtColor() {
        return ntColor;
    }

    public void setNtColor(String ntColor) {
        this.ntColor = ntColor;
    }

    public int getNtStatus() {
        return ntStatus;
    }

    public void setNtStatus(int ntStatus) {
        this.ntStatus = ntStatus;
    }

    public String getNtDuedate() {
        return ntDuedate;
    }

    public void setNtDuedate(String ntDuedate) {
        this.ntDuedate = ntDuedate;
    }

    public String getNtDate() {
        return ntDate;
    }

    public void setNtDate(String ntDate) {
        this.ntDate = ntDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ntId != null ? ntId.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "dev.yonathaniel.mvntodoapp.models.Todo[ ntId=" + ntId + " ]";
    }

}
