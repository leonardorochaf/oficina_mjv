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
    <title>Oficina MJV - Detalhes de registro</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />
    <link href="/css/styles.css" rel="stylesheet">
    <link href="/css/registro/registro.css" rel="stylesheet">
</head>
<body>

    <main class="container mt-4">

        <div class="card main-card d-flex mx-auto col-12 col-md-7 pt-4 pb-5 px-5">
            <h2>Detalhes de registro veicular</h2>

            <div class="form-row mb-3 mt-3">
                <div class="form-group col-md-7">
                    <label for="inputNomeCliente"><strong>Nome do cliente</strong></label>
                    <input type="text" class="form-control" id="inputNomeCliente" name="nomeCliente" value="<c:out value="${registro.nomeCliente}"></c:out>" required disabled>
                </div>
                <div class="col-md-5">
                    <div id="data" class="form-row justify-content-end mt-3">Data: <fmt:formatDate value="${registro.data}" pattern="dd/MM/yyyy"></fmt:formatDate></div>
                    <div id="hora" class="form-row justify-content-end">Horário: <c:out value="${registro.hora}"></c:out></div>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="inputTipoVeiculo"><strong>Tipo de veiculo</strong></label>
                    <input type="text" class="form-control" id="inputTipoVeiculo" name="veiculo" value="<c:out value="${registro.veiculo.nome}"></c:out>" required disabled>
                </div>
            </div>

            <div class="my-custom-scrollbar my-4">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Peça</th>
                            <th scope="col">Defeito</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${pecasdefeitos}" var="pecasdefeitos">
                            <tr>
                                <td><c:out value="${pecasdefeitos.defeito.nome}"></c:out></td>
                                <td><c:out value="${pecasdefeitos.peca.nome}"></c:out></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <div>
                <a href="/registro" class="btn-voltar"><i class="fas fa-chevron-left"></i><strong>Voltar</strong></a>
            </div>

        </div>

    </main>

</body>
</html>