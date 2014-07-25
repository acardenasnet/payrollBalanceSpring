$(function () {
    $(document).ready(function () {
        $.ajax({
            dataType: "json",
            url: contextPath + "/controller/get/list",
            success: function (data) {
                showList(data, null);
            }
        });
    });

    $('#fileupload').fileupload({
        dataType: 'json',

        done: function (e, data) {
            showList(data.result, e);
        },

        progressall: function (e, data) {
            var progress = parseInt(data.loaded / data.total * 100, 10);
            $('#progress .bar').css(
                'width',
                    progress + '%'
            );
        },

        dropZone: $('#dropzone')
    });

    function showList(data, e) {
        if ($.isEmptyObject(data)) {
            $('#process').attr('disabled', true);
        } else {
            $('#process').removeAttr('disabled');
        }
        $("tr:has(td)").remove();
        $(data).each(function (index, file) {

            $("#uploaded-files").append(
                $('<tr/>')
                    .append($('<td/>').text(file.fileName))
                    .append($('<td/>').text(file.fileSize))
                    .append($('<td/>').text(file.fileType))
                    .append($('<td/>').addClass('status').addClass(file.success.toString()))
            )//end $("#uploaded-files").append()

        });

    }

});