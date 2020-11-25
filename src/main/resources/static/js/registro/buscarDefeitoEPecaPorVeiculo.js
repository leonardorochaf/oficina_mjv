$("#selectVeiculos").change(function () {
    buscarDefeitoePecaPorVeiculo()
})

function buscarDefeitoePecaPorVeiculo() {
    var url = "/veiculo/" + $("#selectVeiculos").val() + "/buscar_defeitos_e_pecas"
    $.ajax({
        url: url,
        type: "GET",
        beforeSend: function () {
            $("#loader").show()
        },
        success: function (resultado) {
            montaHtml(resultado)
        },
        error: function (jqXhr, textStatus, errorMessage) {
            alert(textStatus)
        },
        complete: function () {
            $("#loader").hide()
        }
    })
}