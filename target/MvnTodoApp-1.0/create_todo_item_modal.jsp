<%--
    Document   : create_todo_modal
    Created on : Aug 26, 2020, 9:16:15 AM
    Author     : Aviator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="create_todo_item" class="w3-modal">
    <span onclick="document.getElementById('create_todo_item').style.display = 'none'" class="w3-closebtn w3-hover-red w3-container w3-padding-16 w3-display-topright">×</span>
    <div class="w3-modal-content w3-card-8 w3-animate-zoom" style="max-width:400px">

        <div class="w3-center">
                   <h1 class="w3-xlarge w3-text-red"><b>Create TODO</b></h1>
            <hr style="width:50px;border:5px solid red" class="w3-round">
        </div>

        <div class="w3-container">
            <span class="w3-text-red" id="create_todo_item_msg"></span>
            <div class="w3-section">
                <input type="text" name="txtItemId" hidden/>
                <label><b>ToDo Item Description</b></label>
                <textarea class="w3-input w3-border w3-margin-bottom" rows="4" name="txtItemDesc"></textarea>

                <button class="w3-btn w3-btn-block w3-green" style="margin-top: 30px" onclick="createTodoItem()">Save</button>
            </div>
        </div>

    </div>
</div>