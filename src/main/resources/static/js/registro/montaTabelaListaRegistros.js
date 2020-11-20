function montaHtml (resultado) {
    var html = [];
    if(resultado.length > 0 ) {
        $("table").removeClass("hide-table")

        for(i = 0; i < resultado.length; i++) {
            var data = resultado[i].data.split("-");
            var dataFormatada = data[2] + "/" + data[1] + "/" + data[0]

            html.push('<tr>')
            html.push('<td>' + resultado[i].nomeCliente + '</td>')
            html.push('<td>' + dataFormatada + '</td>')
            html.push('<td>' + resultado[i].veiculo.nome + '</td>')
            html.push('<td><a href="#" class="float-right" style="color: #FF7800">Ver detalhes</a></td>')
            html.push('</tr>')
            $("#tableBody").html(html)
        }
    } else {
        html.push('<h5 class="my-4" style="text-align: center">Nenhum registro encontrado</h5>')
        $(".my-custom-scrollbar").html(html)
    }

}