<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title>IPS CFDI to CSV</title>
    <script src="<c:url value="/js/jquery.1.9.1.min.js"/>"></script>

    <script src="<c:out value="/js/vendor/jquery.ui.widget.js"/>"></script>
    <script src="<c:out value="/js/jquery.iframe-transport.js"/>"></script>
    <script src="<c:out value="/js/jquery.fileupload.js"/>"></script>

    <!-- bootstrap just to have good looking page -->
    <script src="<c:out value="/bootstrap/js/bootstrap.min.js"/>"></script>
    <link href="bootstrap/css/bootstrap.css" type="text/css" rel="stylesheet"/>

    <!-- we code these -->
    <link href="<c:url value="/css/dropzone.css"/>" type="text/css" rel="stylesheet"/>
    <script src="<c:url value="/js/myuploadfunction.js"/>"></script>
</head>

<body>
<h1>Convertir Nomina CFDI a CSV</h1>

<div style="width:500px;padding:20px">
    <div id="buttons">
        <form name="frm_reset" action="rest/controller/get/reset" method="get">
            <input id="reset" type="submit" value="Reset">
        </form>
        <form name="frm_process" action="rest/controller/process" method="post">
            <input id="process" type="submit" value="Generate CSV">
        </form>
    </div>
    <input id="fileupload" type="file" name="files[]" data-url="rest/controller/upload" multiple>

    <div id="dropzone" class="fade well">Drop files here</div>

    <div id="progress" class="progress">
        <div class="bar" style="width: 0%;"></div>
    </div>

    <table id="uploaded-files" class="table">
        <tr>
            <th>File Name</th>
            <th>File Size</th>
            <th>File Type</th>
            <th>Status</th>
        </tr>
    </table>

</div>
</body>
</html>