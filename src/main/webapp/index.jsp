<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.operativos.mydrive.HelloAppEngine" %>
<%@ page import="com.operativos.mydrive.LoginServlet" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://bootswatch.com/4/slate/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <title>My Drive</title>

    <%
        String userId = request.getParameter("userId");
        pageContext.setAttribute("userId", userId);

        String file = request.getParameter("file");
        pageContext.setAttribute("file", file);

        String path = request.getParameter("path");
        pageContext.setAttribute("path", path);
    %>

    <script>
        function uploadFileFunc(){

            var x = document.forms["uplFile"];
            x.submit();

        }

        function validate() {
            var y = $('#file').value;
            if (!y)
                return false;
            $('#fileSelector').css('display', 'none');
            return true;

        }
    </script>

</head>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <a class="navbar-brand" href="#">MyDrive (${userId})</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarColor01">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="#"><i class="material-icons">note_add</i><span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#"><i class="material-icons">create_new_folder</i></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#"><i class="material-icons">share</i></a>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="text" placeholder="Search">
            <button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
        </form>
        <ul class="navbar-nav mr-rigth">
            <li class="nav-item">
                <a class="nav-link" href="/login?type=logout">LogOut</a>
            </li>
        </ul>
    </div>
</nav>
<body>

<div id="fileSelector" class="input-group mb-3" style="display: none;">
    <form name="uplFile" action="/uploadFile" onsubmit="return(validate());" accept-charset="utf-8" enctype="multipart/form-data" method="POST">
        <div class="custom-file">
            <input type="file" name="fileToUpload" class="custom-file-input" id="inputGroupFile02">
            <label class="custom-file-label" for="inputGroupFile02">Choose file</label>
        </div>
        <div class="input-group-append">
            <span class="input-group-text" id="" onclick="uploadFileFunc()" >Upload</span>
        </div>
    </form>
</div>

<p>${path}</p>

<div style="width: auto; text-align: center;">


    <div class="jumbotron" style="text-align: center;margin: 2rem 5%;width: 90%;position: absolute;">
        <jsp:include page="/getFiles?file=${file}"/>

    </div>

</div>

</body>
</html>
