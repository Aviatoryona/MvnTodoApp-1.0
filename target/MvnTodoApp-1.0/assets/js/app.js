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

var app = {};

/*
 *
 * @returns {undefined}
 */
app.http = function () {
    var me = this;
    var xhr;
    if (window.XMLHttpRequest) {
        xhr = new XMLHttpRequest();
    } else {
        // code for IE6, IE5
        xhr = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xhr.onreadystatechange = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            if (xhr.status == 200) {
//                console.log(xhr.responseText);
//                alert(xhr.responseText);
                me.data = me.isJson ? eval('(' + xhr.responseText + ')') : xhr.responseText;
                me.callBack(me.data);
            }
        }
    };
    xhr.open(me.method, me.dataUrl, true);
    if (me.params != null) {
        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhr.send(me.params);
    } else {
        xhr.send();
    }
};

/*
 *
 * @returns {undefined}
 */
app.loadTemplate = function () {
    var me = this;
    $.ajax({
        url: this.dataUrl,
        type: this.method,
        success: function (data, textStatus, jqXHR) {
//            console.log(data);
            me.callBack(data);
        },
        error: function (jqXHR, textStatus, errorThrown) {
//            console.log(jqXHR);
            this.callBack("Error encountered");
        }
    });
};

/*
 *  Create user id db and start session
 */
app.auth = function (username) {
    var params = {
        dataUrl: "TodoApp",
        method: 'POST',
        isJson: true,
        params: `q=auth&username=` + username,
        callBack: function (data) {
            if (data.success) {
                window.location.href = "home.jsp";
            } else {
                document.getElementById('msg').innerHTML = data.message;
                setTimeout(function () {
                    document.getElementById('msg').innerHTML = "";
                }, 5000);
            }
        }
    };
    app.http.call(params);
};


/*
 *
 */
app.homeIndex = function () {
    app.loadTemplate.call({
        dataUrl: "home_template.jsp",
        method: 'GET',
        isJson: false,
        params: null,
        callBack: function (data) {
//            console.log(data);
            $('#mytodos').html(data);
        }
    });
};

/*
 * show create todo modal
 */
function showCreateTodoModal() {
    w3_close();
    document.getElementById('create_todo').style.display = 'block';
    document.getElementById('todostatus').style.display = 'none';
    $('#btnCreateTodo').text("Create");
    $('#btnCreateTodo').click(function () {
        createTodo();
    });
}

/*
 * show create todo item modal
 */
function showCreateTodoItemModal(todoId) {
    $('input[name="txtItemId"]').val(todoId);
    document.getElementById('create_todo_item').style.display = 'block';
}

/*
 *
 */
function updateTodoShowModal(id, title, desc, date1) {
    $('input[name="txtTodoId"]').val(id);
    $('input[name="txtTitle"]').val(title);
    $('input[name="txtDate"]').val(date1);
    $('textarea[name="txtDesc"]').val(desc);
    document.getElementById('todostatus').style.display = 'block';
    $('#btnCreateTodo').text("Update");
    $('#btnCreateTodo').click(function () {
        updateTodo();
    });
    document.getElementById('create_todo').style.display = 'block';
}

/*
 *
 */
function createTodo() {
    var title = $('input[name="txtTitle"]').val();
    if (title == '') {
        $('input[name="txtTitle"]').css('border', '1px solid red');
        return;
    }
    var duedate = $('input[name="txtDate"]').val();
    if (duedate == '') {
        $('input[name="txtDate"]').css('border', '1px solid red');
        return;
    }
    var txtDesc = $('textarea[name="txtDesc"]').val();
    if (txtDesc == '') {
        $('textarea[name="txtDesc"]').css('border', '1px solid red');
        return;
    }

    var urlData = `q=createtodo`
            + `&title=` + title
            + `&duedate=` + duedate
            + `&desc=` + txtDesc;

    var params = {
        dataUrl: "TodoApp",
        method: 'POST',
        isJson: true,
        params: urlData,
        callBack: function (data) {
            if (data.success) {
                document.getElementById('create_todo_msg').innerHTML = data.message;
                app.homeIndex();
                setTimeout(function () {
                    document.getElementById('create_todo_msg').innerHTML = "";
                }, 5000);
            } else {
                document.getElementById('create_todo_msg').innerHTML = data.message;
                setTimeout(function () {
                    document.getElementById('create_todo_msg').innerHTML = "";
                }, 5000);
            }
        }
    };

    console.log(params);

    app.http.call(params);

}

/*
 *
 * @param {type} todoId
 * @returns {undefined}
 */
function updateTodo() {
    var id = $('input[name="txtTodoId"]').val();
    if (id == '') {
        document.getElementById('create_todo_msg').innerHTML = "Please try again";
        setTimeout(function () {
            document.getElementById('create_todo_msg').innerHTML = "";
        }, 5000);
        return;
    }
    var title = $('input[name="txtTitle"]').val();
    if (title == '') {
        $('input[name="txtTitle"]').css('border', '1px solid red');
        return;
    }
    var duedate = $('input[name="txtDate"]').val();
    if (duedate == '') {
        $('input[name="txtDate"]').css('border', '1px solid red');
        return;
    }
    var todostatus = $('select[name="txtStatus"]').val();
    var txtDesc = $('textarea[name="txtDesc"]').val();
    if (txtDesc == '') {
        $('textarea[name="txtDesc"]').css('border', '1px solid red');
        return;
    }

    var urlData = `q=updatetodo`
            + `&id=` + id
            + `&title=` + title
            + `&duedate=` + duedate
            + `&status=` + todostatus
            + `&desc=` + txtDesc;
    var params = {
        dataUrl: "TodoApp",
        method: 'POST',
        isJson: true,
        params: urlData,
        callBack: function (data) {
            if (data.success) {
                document.getElementById('create_todo_msg').innerHTML = data.message;
                $('#btnCreateTodo').text("Create");
                $('#btnCreateTodo').click(function () {
                    createTodo();
                });
                app.homeIndex();
                setTimeout(function () {
                    document.getElementById('create_todo_msg').innerHTML = "";
                }, 5000);
            } else {
                document.getElementById('create_todo_msg').innerHTML = data.message;
                setTimeout(function () {
                    document.getElementById('create_todo_msg').innerHTML = "";
                }, 5000);
            }
        }
    };
    app.http.call(params);
}

/*
 *
 */
function createTodoItem() {
    var id = $('input[name="txtItemId"]').val();
    if (id == '') {
        document.getElementById('create_todo_item_msg').innerHTML = "Error encountered, please try again";
        setTimeout(function () {
            document.getElementById('create_todo_item_msg').innerHTML = "";
        }, 5000);
        return;
    }
    var desc = $('textarea[name="txtItemDesc"]').val();
    if (desc == '') {
        $('textarea[name="txtItemDesc"]').css('border', '1px solid red');
        return;
    }
    var urlData = `q=createtodoitem`
            + `&id=` + id
            + `&desc=` + desc;
    var params = {
        dataUrl: "TodoApp",
        method: 'POST',
        isJson: true,
        params: urlData,
        callBack: function (data) {
            if (data.success) {
                document.getElementById('create_todo_item_msg').innerHTML = data.message;
                app.homeIndex();
                setTimeout(function () {
                    document.getElementById('create_todo_item_msg').innerHTML = "";
                }, 5000);
            } else {
                document.getElementById('create_todo_item_msg').innerHTML = data.message;
                setTimeout(function () {
                    document.getElementById('create_todo_item_msg').innerHTML = "";
                }, 5000);
            }
        }
    };
    app.http.call(params);

}

/*
 *
 */
function deleteTodo(todoId) {
    var x = confirm("Sure to delete this item");
    if (x) {
        var urlData = `q=deletetodo`
                + `&id=` + todoId;
        var params = {
            dataUrl: "TodoApp",
            method: 'POST',
            isJson: true,
            params: urlData,
            callBack: function (data) {
                if (data.success) {
                    app.homeIndex();
                } else {
                    alert(data.message);
                }
            }
        };
        app.http.call(params);
    }
}

/*
 *
 */
function deleteTodoItem(todoItemId) {
    $('#' + todoItemId).css('display', 'none');
    var urlData = `q=deletetodoitem`
            + `&id=` + todoItemId;
    var params = {
        dataUrl: "TodoApp",
        method: 'POST',
        isJson: true,
        params: urlData,
        callBack: function (data) {
            if (data.success) {
                console.log(data.message);
            } else {
                console.log(data.message);
            }
        }
    };
    app.http.call(params);
}