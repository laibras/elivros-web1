<%-- 
    Document   : cabecalho
    Created on : 10 de jun. de 2025, 18:23:03
    Author     : Leonardo Oliveira Moreira
--%>

<%@page import="modelo.usuario.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        request.setAttribute("mensagem", "Você não tem permissão para acessar esta página");
        RequestDispatcher rd = request.getRequestDispatcher("Inicio");
        rd.forward(request, response);
    }
%>
<!DOCTYPE html>
<html lang="pt">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>eLivros</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.6/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4Q6Gf2aSP4eDXB8Miphtr37CMZZQ5oXLH2yaXMJ2w8e2ZtHTl7GptT4jmndRuHDT" crossorigin="anonymous">
        <link rel="stylesheet" href="css/styles.css"> <!-- laranja -->
       <link rel="icon" type="image/png" href="imagens/elivros.ico">
    </head>
    <body>
        <%
            String paginaAtual = (String) request.getAttribute("paginaAtual");
        %>
        <header>
            <nav class="navbar navbar-expand-lg bg-body-tertiary">
                <div class="container-fluid">
                    <a class="navbar-brand elivros-title" href="Inicio">                        
                        <img src="imagens/elivros.png" alt="Imagem do site">
                        eLivros
                    </a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item">
                                <a class="nav-link <%= "inicio".equals(paginaAtual) ? "active" : ""%>" href="Inicio">Início</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link <%= "perfil".equals(paginaAtual) ? "active" : ""%>" href="Principal">Perfil</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link <%= "atualizar".equals(paginaAtual) ? "active" : ""%>" href="<%= request.getContextPath()%>/VerUsuario">Atualizar Dados</a>
                            </li>
                            <%
                                if (usuario.isAdministrador()) {
                            %>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle <%= "cadastro".equals(paginaAtual) ? "active" : ""%>" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Cadastros
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="<%= request.getContextPath()%>/admin/ListarProdutos">Produtos</a></li>
                                    <li><a class="dropdown-item" href="<%= request.getContextPath()%>/admin/ListarCategorias">Categorias</a></li>
                                    <li><a class="dropdown-item" href="<%= request.getContextPath()%>/admin/AtualizarProdutoFoto">Atualizar Foto do Produto</a></li>
                                </ul>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="<%= request.getContextPath()%>/admin/ListarProdutosFaltantes">Relatório de Produtos Faltantes</a>
                            </li>
                            <%
                                }
                            %>
                            <li class="nav-item">
                                <a class="nav-link" href="<%= request.getContextPath()%>/Logout">Sair</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>
        <main role="main" class="container">
            <%
                String mensagem = (String) request.getAttribute("mensagem");
                if (mensagem != null) {
            %>
            <div class="alert alert-warning alert-dismissible fade show" role="alert">
                <%= mensagem%>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <%
                }
            %>