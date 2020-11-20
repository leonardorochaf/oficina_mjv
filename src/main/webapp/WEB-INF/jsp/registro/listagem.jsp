<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Oficina MJV - Listagem de registro</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />
    <link href="/css/styles.css" rel="stylesheet">
    <link href="/css/registro/registro.css" rel="stylesheet">
</head>
<body>

    <main class="container">

        <div class="card main-card d-flex mx-auto col-12 col-md-7 pt-4 pb-5 px-5">
            <h3>Registro de defeitos veiculares</h3>
            <form>
                <div class="form-row mt-3 mb-1">
                    <div class="form-group col-md-5">
                        <label for="selectVeiculos"><strong>Tipo de veículo</strong></label>
                        <select class="form-control" id="selectVeiculos" name="veiculoId">
                            <c:if test="${empty veiculos}">
                                <option selected disabled>Nenhum veículo cadastrado</option>
                            </c:if>
                            <c:if test="${!empty veiculos}">
                                <option selected value>Selecione</option>
                            </c:if>
                            <c:forEach items="${veiculos}" var="veiculo">
                                <option value="${veiculo.id}"><c:out value="${veiculo.nome}"></c:out></option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group col-md-5">
                        <label for="inputDataInicio"><strong>Data início</strong></label>
                        <input type="date" class="form-control" id="inputDataInicio" name="dataInicio">
                    </div>
                    <div class="form-group col-md-5">
                        <label for="inputDataFim"><strong>Data Fim</strong></label>
                        <input type="date" class="form-control" id="inputDataFim" name="dataFim">
                    </div>
                </div>

                <div class="my-custom-scrollbar my-4">
                    <table class="table hide-table">
                        <thead>
                        <tr>
                            <th scope="col">Cliente</th>
                            <th scope="col">Data</th>
                            <th scope="col">Veiculo</th>
                            <th scope="col"></th>
                        </tr>
                        </thead>
                        <tbody id="tableBody">

                        </tbody>
                    </table>
                </div>

                <a href="/" class="btn-voltar"><i class="fas fa-chevron-left"></i><strong>Voltar ao menu</strong></a>
                <button class="btn btn-salvar float-right" id="btnEnviar">Pesquisar</button></form>
        </div>

    </main>

    <script type="text/javascript" src="/js/registro/buscaTodosRegistros.js"></script>
    <script type="text/javascript" src="/js/registro/buscarTodosRegistrosComParametros.js"></script>
    <script type="text/javascript" src="/js/registro/montaTabelaListaRegistros.js"></script>

</body>
</html>