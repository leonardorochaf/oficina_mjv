$(function () {
    var url = "/registro/todos"
    $.ajax({
        url: url,
        type: "GET",
        success: function (resultado) {
            montaHtml(resultado)
        },
        error: function (jqXhr, textStatus, errorMessage) {
            alert(textStatus)
        },
    })
})