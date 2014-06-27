$(function () {
    $('#fileupload').fileupload({
        dataType: 'json',

        done: function (e, data) {
            $("tr:has(td)").remove();
            this.downloadButton = $('<button/>');

            $('#download').append($('<a href="rest/controller/get/' + data.result.downloadName + '">Download</a>'));
            $.each(data.result.fileMetas, function (index, file) {


                $("#uploaded-files").append(
                    $('<tr/>')
                        .append($('<td/>').text(file.fileName))
                        .append($('<td/>').text(file.fileSize))
                        .append($('<td/>').text(file.fileType))
                        .append($('<td/>').text(file.success))
                )//end $("#uploaded-files").append()
            });

            $('#fileupload').remove();
            $('#dropzone').remove();
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

});