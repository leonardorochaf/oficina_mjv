$("#selectVeiculos").change(function () {
    var url = "/veiculo/" + $("#selectVeiculos").val() + "/buscar_defeitos_e_pecas"
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