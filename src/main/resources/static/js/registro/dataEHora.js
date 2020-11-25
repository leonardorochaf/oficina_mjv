$(function dataEHora() {
    var data = new Date();

    var dia = data.getDate();
    var mes = data.getMonth();
    var ano4 = data.getFullYear();
    var hora = data.getHours();
    var min = data.getMinutes();
    var seg = data.getSeconds();

    if(dia < 10) dia = "0" + dia
    if(mes < 10) mes = "0" + mes
    if(hora < 10) hora = "0" + hora
    if(min < 10) min = "0" + min
    if(seg < 10) seg = "0" + seg
    var str_data = dia + '/' + (mes+1) + '/' + ano4;
    var str_hora = hora + ':' + min + ':' + seg;

    $("#data").html("Acesso: " + str_data);
    $("#hora").html("Horário: " + str_hora);

    setTimeout(dataEHora, 1000)
})