<%-- 
    Document   : form
    Created on : 17 de jun. de 2025, 18:23:06
    Author     : Leonardo Oliveira Moreira
--%>

<%@page import="modelo.categoria.Categoria"%>
<%@page import="modelo.produto.Produto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/cabecalho.jsp" %>

<div class="card shadow card-custom">
    <div class="card-header bg-white">
        <h2 class="mb-0">Cadastro de Produtos</h2>
    </div>
<%    
    Produto produto = (Produto) request.getAttribute("produto");
    List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
%>
<div class="card-body">
<form action="<%= request.getContextPath()%>/admin/SalvarProduto" method="post">
    <% if (produto != null) { %>
    <div class="mb-3">
        <label for="formControlId" class="form-label">Id</label>
        <input type="text" name="id" class="form-control" id="formControlId" placeholder="Id" value="<%= (produto != null ? produto.getId() : "")%>" readonly>
    </div>
    <% } %>
    <div class="mb-3">
        <label for="formControlTitulo" class="form-label">Titulo</label>
        <input type="text" name="titulo" class="form-control" id="formControlTitulo" placeholder="Titulo" value="<%= (produto != null ? produto.getTitulo() : "")%>">
    </div>
    <div class="mb-3">
        <label for="formControlAutor" class="form-label">Autor</label>
        <input type="text" name="autor" class="form-control" id="formControlAutor" placeholder="Autor" value="<%= (produto != null ? produto.getAutor() : "")%>">
    </div>
    <div class="mb-3">
        <label for="formControlAno" class="form-label">Ano</label>
        <input type="text" name="ano" class="form-control" id="formControlAno" placeholder="Ano" value="<%= (produto != null ? produto.getAno() : "")%>">
    </div>
    <div class="mb-3">
        <label for="formControlPreco" class="form-label">Preço</label>
        <input type="text" name="preco" class="form-control" id="formControlPreco" placeholder="Preço" value="<%= (produto != null ? produto.getPreco(): "")%>">
    </div>
    <div class="mb-3">
        <label for="formControlQuantidade" class="form-label">Quantidade</label>
        <input type="text" name="quantidade" class="form-control" id="formControlQuantidade" placeholder="Quantidade" value="<%= (produto != null ? produto.getQuantidade(): "")%>">
    </div>
    <div class="mb-3">
        <label for="formControlCategoria" class="form-label">Categoria</label>
        <select name="categoriaId" class="form-control" id="formControlCategoria">
            <option value="">Selecione ...</option>
            <%
                for (int i = 0; categorias != null && i < categorias.size(); i++) {
                    Categoria c = categorias.get(i);
                    if (produto != null && c.getId() == produto.getCategoria().getId()) {
            %>
            <option value="<%= c.getId() %>" selected><%= c.getDescricao() %></option>
            <%
                    } else {
            %>
            <option value="<%= c.getId() %>"><%= c.getDescricao() %></option>
            <%
                    }
                }
            %>
        </select>
    </div>
    <input class="btn btn-custom" type="submit" value="Salvar" />
</form>
</div>
</div><!-- comment -->
<%@include file="../rodape.jsp" %>
