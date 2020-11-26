function montaTabelaDefeitoEPeca (resultado) {
    var html = [];
    for(i = 0; i < resultado.length; i++) {
        html.push('<tr>')
        html.push('<th><input type="checkbox" name="ids" value="'+ resultado[i].id +'"></th>')
        html.push('<td>' + resultado[i].defeito.nome + '</td>')
        html.push('<td>' + resultado[i].peca.nome + '</td>')
        html.push('</tr>')
    }
    $("#tableBody").html(html)
}