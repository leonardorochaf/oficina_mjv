$(function () {
    buscarRegistros()
})

$("#btnEnviar").click(function (){
    buscarRegistros()
})

function buscarRegistros() {
    var url = "/registro/todos"
    $.ajax({
        url: url,
        type: "GET",
        data: {
            "veiculoId": $("#selectVeiculos").val(),
            "dataInicio": $("#inputDataInicio").val(),
            "dataFim": $("#inputDataFim").val()
        },
        beforeSend: function () {
            $("#loader").show()
        },
        success: function (resultado) {
            montaTabelaListaRegistros(resultado)
        },
        error: function (jqXhr, textStatus, errorMessage) {
            alert(textStatus)
        },
        complete: function () {
            $("#loader").hide()
        }
    })
}