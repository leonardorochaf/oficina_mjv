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
    <title>Oficina MJV - Cadastro de registro</title>
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
            <c:if test='${not empty mensagensErroFormulario}'>
                <c:forEach items="${mensagensErroFormulario}" var="mensagem">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        <c:out value="${mensagem}" />
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                </c:forEach>
            </c:if>

            <c:if test="${not empty mensagemSucesso}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    <c:out value="${mensagemSucesso}" />
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </c:if>

            <c:if test="${not empty mensagemErro}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    <c:out value="${mensagemErro}" />
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </c:if>

            <h2>Registro de defeitos veiculares</h2>

            <form method="post" action="/registro/cadastrar">
                <div class="form-row mb-3 mt-3">
                    <div class="form-group col-md-7">
                        <label for="inputNomeCliente"><strong>Nome do cliente</strong></label>
                        <input type="text" class="form-control" id="inputNomeCliente" name="nomeCliente" placeholder="Digite aqui o nome do cliente" required>
                    </div>
                    <div class="col-md-5">
                        <div id="data" class="form-row justify-content-end mt-3"></div>
                        <div id="hora" class="form-row justify-content-end"></div>
                    </div>
                </div>

                <div class="form-row mb-4">
                    <div class="form-group col-md-5">
                        <label for="selectVeiculos"><strong>Tipo de veículo</strong></label>
                        <select class="form-control" id="selectVeiculos" name="veiculo.id" required>
                            <c:if test="${empty veiculos}">
                                <option selected disabled>Nenhum veículo cadastrado</option>
                            </c:if>
                            <c:if test="${!empty veiculos}">
                                <option selected disabled hidden value>Selecione</option>
                            </c:if>
                            <c:forEach items="${veiculos}" var="veiculo">
                                <option value="${veiculo.id}"><c:out value="${veiculo.nome}"></c:out></option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="sk-chase mt-5" id="loader" style="display: none">
                    <div class="sk-chase-dot"></div>
                    <div class="sk-chase-dot"></div>
                    <div class="sk-chase-dot"></div>
                    <div class="sk-chase-dot"></div>
                    <div class="sk-chase-dot"></div>
                    <div class="sk-chase-dot"></div>
                </div>

                <div class="my-custom-scrollbar my-4">
                    <table class="table <c:if test="${empty veiculos}">hide-table</c:if>">
                        <thead>
                        <tr>
                            <th scope="col">Itens</th>
                            <th scope="col">Defeito</th>
                            <th scope="col">Peça</th>
                        </tr>
                        </thead>
                        <tbody id="tableBody" class="checkbox-group">

                        </tbody>
                    </table>
                </div>

                <a href="/" class="btn-voltar"><i class="fas fa-chevron-left"></i><strong>Voltar ao menu</strong></a>
                <button type="submit" class="btn btn-salvar" <c:if test="${empty veiculos}">disabled</c:if>>Salvar</button>
            </form>
        </div>

    </main>

    <script type="text/javascript" src="/js/registro/buscarDefeitoEPecaPorVeiculo.js"></script>
    <script type="text/javascript" src="/js/registro/montaTabelaDefeitoEPeca.js"></script>
    <script type="text/javascript" src="/js/registro/dataEHora.js"></script>

</body>
</html>