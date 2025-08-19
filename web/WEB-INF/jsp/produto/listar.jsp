<%@page import="modelo.produto.Produto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("paginaAtual", "cadastro"); %>
<%@include file="/cabecalho.jsp" %>

<div class="container mt-4 mb-5">
    <h3 class="mb-4">Cadastro de Produtos</h3>
    
    <form action="<%= request.getContextPath()%>/admin/ListarProdutos" method="post" class="mb-4">
        <div class="mb-3">
            <label for="formControlTitulo" class="form-label">Título</label>
            <input type="text" name="titulo" class="form-control" id="formControlTitulo" placeholder="Digite o título do produto">
        </div>
        <div class="d-flex gap-2">
            <input class="btn btn-success" type="submit" value="Procurar" />
            <a role="button" class="btn btn-outline-primary" href="<%= request.getContextPath()%>/admin/FormProduto">Inserir Novo</a>
        </div>
    </form>

    <%
        List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");
        if (produtos != null && !produtos.isEmpty()) {
    %>
    <div class="table-responsive">
        <table class="table table-bordered table-hover align-middle">
            <thead class="table-light">
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Título</th>
                    <th scope="col">Preço</th>
                    <th scope="col">Quantidade</th>
                    <th scope="col">Categoria</th>
                    <th scope="col" class="text-end">Ações</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Produto p : produtos) {
                %>
                <tr>
                    <td><%= p.getId() %></td>
                    <td><%= p.getTitulo() %></td>
                    <td>R$ <%= String.format("%.2f", p.getPreco()) %></td>
                    <td><%= p.getQuantidade() %></td>
                    <td><%= p.getCategoria().getDescricao() %></td>
                    <td class="text-end">
                        <a role="button" class="btn btn-sm btn-warning me-1" href="<%= request.getContextPath()%>/admin/FormProduto?id=<%= p.getId() %>">Atualizar</a>
                        <a role="button" class="btn btn-sm btn-danger" href="<%= request.getContextPath()%>/admin/RemoverProduto?id=<%= p.getId() %>">Remover</a>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
    <% } %>
</div>

<%@include file="../rodape.jsp" %>
