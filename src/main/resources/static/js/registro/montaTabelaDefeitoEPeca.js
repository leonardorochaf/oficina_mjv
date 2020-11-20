function montaHtml (resultado) {
    var html = [];
    for(i = 0; i < resultado.length; i++) {
        html.push('<tr>')
        html.push('<th><input type="checkbox" name="ids" value="'+ resultado[i].id +'"></th>')
        html.push('<td>' + resultado[i].nomeDefeito + '</td>')
        html.push('<td>' + resultado[i].nomePeca + '</td>')
        html.push('</tr>')
    }
    $("#tableBody").html(html)
}