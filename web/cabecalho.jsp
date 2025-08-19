<%--
    Document   : cabecalho.jsp
    Created on : 27 de jul. de 2025
    Author     : Gemini & Leonardo Oliveira Moreira
--%>
<%@page import="modelo.compra.ItemCarrinho"%>
<%@page import="java.util.List"%>
<%@page import="modelo.usuario.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // Pega o usuário e o carrinho da sessão.
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    List<ItemCarrinho> carrinho = (List<ItemCarrinho>) session.getAttribute("carrinho");

    // Calcula o total de itens no carrinho.
    int totalItensCarrinho = 0;
    if (carrinho != null) {
        for (ItemCarrinho item : carrinho) {
            totalItensCarrinho += item.getQuantidade();
        }
    }
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="pt">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>eLivros</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link rel="stylesheet" href="<%= contextPath %>/css/styles.css">
        <link rel="icon" type="image/png" href="<%= contextPath %>/imagens/elivros.ico">
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-lg bg-body-tertiary">
                <div class="container-fluid">

                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarActions" aria-controls="navbarActions" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>

                    <a class="navbar-brand elivros-title position-absolute top-50 start-50 translate-middle" href="<%= contextPath %>/Inicio">
                        <img src="<%= contextPath %>/imagens/elivros.png" alt="Logo eLivros" style="height: 30px;">
                        eLivros
                    </a>

                    <div class="collapse navbar-collapse" id="navbarActions">
                        <div class="ms-auto d-flex align-items-center">
                            <a href="<%= contextPath %>/carrinho?acao=ver" class="nav-link me-3 position-relative">
                                <i class="bi bi-cart fs-4"></i>
                                <% if (totalItensCarrinho > 0) { %>
                                    <span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
                                        <%= totalItensCarrinho %>
                                    </span>
                                <% } %>
                            </a>

                            <% if (usuario != null) { %>
                                <div class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                        Olá, <%= usuario.getNome() %>
                                    </a>
                                    <ul class="dropdown-menu dropdown-menu-end">
                                        <li><a class="dropdown-item" href="<%= contextPath %>/ListarMinhasCompras">Minhas Compras</a></li>
                                        <li><a class="dropdown-item" href="<%= contextPath %>/VerUsuario">Meu Perfil</a></li>
                                        
                                        <% if (usuario.isAdministrador()) { %>
                                            <li><hr class="dropdown-divider"></li>
                                            <li class="dropdown-header">Administração</li>
                                            <li><a class="dropdown-item" href="<%= contextPath %>/admin/ListarProdutos">Gerenciar Produtos</a></li>
                                            <li><a class="dropdown-item" href="<%= request.getContextPath()%>/admin/AtualizarProdutoFoto">Atualizar Foto do Produto</a></li>

                                            <li><a class="dropdown-item" href="<%= contextPath %>/admin/ListarCategorias">Gerenciar Categorias</a></li>
                                            <li><a class="dropdown-item" href="<%= contextPath %>/admin/AcessarRelatorios">Relatórios</a></li>
                                        <% } %>
                                        
                                        <li><hr class="dropdown-divider"></li>
                                        <li><a class="dropdown-item" href="<%= contextPath %>/Logout">Sair</a></li>
                                    </ul>
                                </div>
                            <% } else { %>
                                <a href="<%= contextPath %>/login.jsp" class="nav-link">Login</a>
                                <a href="<%= contextPath %>/cadastrarCliente.jsp" class="btn btn-custom ms-2">Cadastre-se</a>
                            <% } %>
                        </div>
                    </div>
                </div>
            </nav>
        </header>
        <main role="main" class="container">
            <%
                String mensagem = (String) request.getAttribute("mensagem");
                if (mensagem != null) {
            %>
            <div class="alert alert-warning alert-dismissible fade show mt-3" role="alert">
                <%= mensagem %>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
            <%
                }
                request.removeAttribute("mensagem");
            %>
