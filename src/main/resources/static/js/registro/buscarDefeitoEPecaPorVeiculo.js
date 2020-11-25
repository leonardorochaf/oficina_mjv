$("#selectVeiculos").change(function () {
    buscarDefeitoePecaPorVeiculo()
})

$(".btn-tryagain").click(function () {
    buscarDefeitoePecaPorVeiculo()
})

function buscarDefeitoePecaPorVeiculo() {
    $(".tryagain").addClass("hide-div")
    var url = "/veiculo/" + $("#selectVeiculos").val() + "/buscar_defeitos_e_pecas"
    $.ajax({
        url: url,
        type: "GET",
        beforeSend: function () {
            $("#loader").show()
        },
        success: function (resultado) {
            $(".table").removeClass("hide-table")
            montaHtml(resultado)
        },
        error: function () {
            $(".tryagain").removeClass("hide-div")
        },
        complete: function () {
            $("#loader").hide()
        }
    })
}