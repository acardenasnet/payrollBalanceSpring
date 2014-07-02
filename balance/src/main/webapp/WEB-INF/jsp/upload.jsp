<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title>IPS CFDI to CSV</title>
    <script src="<c:url value="/resources/js/jquery.1.9.1.min.js"/>"></script>

    <script src="<c:url value="/resources/js/vendor/jquery.ui.widget.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery.iframe-transport.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery.fileupload.js"/>"></script>

    <!-- bootstrap just to have good looking page -->
    <script src="<c:url value="/resources//bootstrap/js/bootstrap.min.js"/>"></script>
    <link href="<c:url value="/resources/bootstrap/css/bootstrap.css"/>" type="text/css" rel="stylesheet"/>

    <!-- we code these -->
    <link href="<c:url value="/resources/css/dropzone.css"/>" type="text/css" rel="stylesheet"/>
    <script src="<c:url value="/resources/js/myuploadfunction.js"/>"></script>
</head>

<body>
<h1>Convertir Nomina CFDI a CSV</h1>

<div style="width:500px;padding:20px">
    <input id="fileupload" type="file" name="files[]" data-url="<c:url value="/controller/upload"/>" multiple>

    <div id="dropzone" class="fade well">Drop files here</div>

    <div id="buttons">
        <form name="frm_process" action="<c:url value="/controller/process"/>" method="post">
            <input id="process" type="submit" value="Generate CSV">
        </form>
        <form name="frm_reset" action="<c:url value="/controller/get/reset"/>" method="get">
            <input id="reset" type="submit" value="Reset">
        </form>

    </div>

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
<script type="text/javascript">
    var contextPath = '<%=request.getContextPath()%>';
</script>

