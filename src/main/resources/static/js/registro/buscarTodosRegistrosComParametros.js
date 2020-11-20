$("#btnEnviar").click(function (){
    var url = "/registro/todos"
    $.ajax({
        url: url,
        type: "GET",
        data: {
            "veiculoId": $("#selectVeiculos").val(),
            "dataInicio": $("#inputDataInicio").val(),
            "dataFim": $("#inputDataFim").val()
        },
        success: function (resultado) {
            montaHtml(resultado)
        },
        error: function (jqXhr, textStatus, errorMessage) {
            alert(textStatus)
        },
    })
})