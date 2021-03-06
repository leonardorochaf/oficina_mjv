function montaTabelaListaRegistros (resultado) {
    var html = [];
    if(resultado.length > 0 ) {
        $(".my-custom-scrollbar").show()
        $(".not-found").hide()

        for(i = 0; i < resultado.length; i++) {
            var data = resultado[i].data.split("-");
            var dataFormatada = data[2] + "/" + data[1] + "/" + data[0]

            html.push('<tr>')
            html.push('<td>' + resultado[i].nomeCliente + '</td>')
            html.push('<td>' + dataFormatada + '</td>')
            html.push('<td>' + resultado[i].veiculo.nome + '</td>')
            html.push('<td><a href="/registro/' + resultado[i].id + '" class="float-right" style="color: #FF7800">Ver detalhes</a></td>')
            html.push('</tr>')
            $("#tableBody").html(html)
        }
    } else {
        $(".my-custom-scrollbar").hide()
        $(".not-found").show()
    }

}