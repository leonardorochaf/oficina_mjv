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
    <title>Oficina MJV - Cadastro defeito</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" integrity="sha512-+4zCK9k+qNFUR5X+cKL9EIR+ZOhtIloNl9GIKS57V1MyNsYpYcUrUeQc9vNfzsWfV28IaLL3i96P9sdNyeRssA==" crossorigin="anonymous" />
    <link href="/css/styles.css" rel="stylesheet">
</head>
<body>

    <main class="container">

        <div class="card main-card d-flex mx-auto col-12 col-md-6 pt-4 pb-5 px-5">
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

            <h2>Cadastro de defeito</h2>

            <form action="/defeito/cadastrar" method="post">
                <div class="form-group mb-5 mt-3">
                    <label for="inputNome"><strong>Nome</strong></label>
                    <input type="text" class="form-control" id="inputNome" name="nome" placeholder="Digite aqui o nome do defeito" required>
                </div>
                <a href="/" class="btn-voltar"><i class="fas fa-chevron-left"></i><strong>Voltar ao menu</strong></a>
                <button type="submit" class="btn btn-salvar">Salvar</button>
            </form>
        </div>

    </main>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
    <script type="text/javascript" src="/js/commom/alertFade.js"></script>

</body>
</html>