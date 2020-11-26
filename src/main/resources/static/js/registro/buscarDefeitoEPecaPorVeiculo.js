$(function () {
    $(".my-custom-scrollbar").hide()
    $(".tryagain").hide()
})

$("#selectVeiculos").change(function () {
    buscarDefeitoePecaPorVeiculo()
})

$(".btn-tryagain").click(function () {
    buscarDefeitoePecaPorVeiculo()
})

function buscarDefeitoePecaPorVeiculo() {
    $(".tryagain").hide()
    $(".my-custom-scrollbar").hide()
    var url = "/veiculo/" + $("#selectVeiculos").val() + "/buscar_defeitos_e_pecas"
    $.ajax({
        url: url,
        type: "GET",
        beforeSend: function () {
            $("#loader").show()
        },
        success: function (resultado) {
            $(".my-custom-scrollbar").show()
            montaTabelaDefeitoEPeca(resultado)
        },
        error: function () {
            $(".tryagain").show()
        },
        complete: function () {
            $("#loader").hide()
        }
    })
}