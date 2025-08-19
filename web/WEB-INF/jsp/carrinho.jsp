
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="modelo.compra.ItemCarrinho" %>

<% request.setAttribute("paginaAtual", "carrinho"); %>
<%@include file="/cabecalho.jsp" %>

<div class="container mt-4">
    <h3 class="mb-4">Meu Carrinho de Compras</h3>

    <%-- Lógica para tratar o carrinho vazio --%>
    <% if (carrinho == null || carrinho.isEmpty()) { %>

        <div class="alert alert-info" role="alert">
            Seu carrinho está vazio. <a href="Inicio" class="alert-link">Continue comprando!</a>
        </div>

    <% } else { %>
        <%-- Se não estiver vazio, calcula o total e mostra a tabela --%>
        <%
            double total = 0;
            for (ItemCarrinho item : carrinho) {
                total += item.getSubtotal();
            }
        %>

        <table class="table table-hover align-middle">
            <thead class="table-light">
                <tr>
                    <th style="width: 10%;">Imagem</th>
                    <th>Produto</th>
                    <th class="text-center">Preço Unitário</th>
                    <th class="text-center" style="width: 20%;">Quantidade</th>
                    <th class="text-center">Subtotal</th>
                    <th class="text-center">Ação</th>
                </tr>
            </thead>
            <tbody>
                <% for (ItemCarrinho item : carrinho) { %>
                    <tr>
                        <td>
                            <img src="VerProdutoFoto?id=<%= item.getProduto().getId()%>" class="img-fluid rounded" style="max-height: 75px;" alt="<%= item.getProduto().getTitulo() %>">
                        </td>
                        <td><%= item.getProduto().getTitulo() %></td>
                        <td class="text-center">R$ <%= String.format("%.2f", item.getProduto().getPreco()) %></td>
                        <td class="text-center">
                            <form action="carrinho" method="POST" class="d-flex justify-content-center align-items-center">
                                <input type="hidden" name="acao" value="atualizar" />
                                <input type="hidden" name="id" value="<%= item.getProduto().getId() %>" />
                                <input type="number" name="quantidade" class="form-control form-control-sm" style="width: 70px;" value="<%= item.getQuantidade() %>" min="0" max="<%= item.getProduto().getQuantidade() %>">
                                <button type="submit" class="btn btn-secondary btn-sm ms-1" title="Atualizar quantidade">
                                    <i class="bi bi-arrow-repeat"></i>
                                </button>
                            </form>
                        </td>
                        <td class="text-center">R$ <%= String.format("%.2f", item.getSubtotal()) %></td>
                        <td class="text-center">
                            <form action="carrinho" method="POST" class="d-inline">
                                <input type="hidden" name="acao" value="remover" />
                                <input type="hidden" name="id" value="<%= item.getProduto().getId() %>" />
                                <button type="submit" class="btn btn-danger btn-sm" title="Remover item">
                                    <i class="bi bi-trash"></i>
                                </button>
                            </form>
                        </td>
                    </tr>
                <% } %>
            </tbody>
            <tfoot class="table-light">
                <tr>
                    <td colspan="4" class="fw-bold text-end">Total da Compra</td>
                    <td colspan="2" class="text-center fw-bold fs-5">R$ <%= String.format("%.2f", total) %></td>
                </tr>
            </tfoot>
        </table>

        <div class="d-flex justify-content-between mt-4 mb-5">
            <a href="Inicio" class="btn btn-outline-secondary btn-lg">Continuar Comprando</a>
            <form action="SalvarCompra" method="POST">
                <button type="submit" class="btn btn-success btn-lg">
                    Finalizar Compra
                </button>
            </form>
        </div>

    <% } %> 
</div>

<%@include file="rodape.jsp" %>