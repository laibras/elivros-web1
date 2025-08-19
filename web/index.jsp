<%@page import="modelo.produto.Produto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("paginaAtual", "inicio"); %>
<%@include file="cabecalho.jsp" %>

<div class="container mt-4">
    <h3 class="mb-4">Produtos em Estoque</h3>

    <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-4">
        <%  
            List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");
            if (produtos != null) {
                for (Produto produto : produtos) {
        %>
        <div class="col">
            <div class="card h-100 shadow-sm border-0 hover-card">
                <img src="VerProdutoFoto?id=<%= produto.getId()%>" class="card-img-top" alt="<%= produto.getTitulo() %>" style="height: 400px; object-fit: cover;">                
                <div class="card-body d-flex flex-column">
                    <h5 class="card-title"><%= produto.getTitulo() %> </h5>
                    <p class="card-text">
                        <strong>Ano:</strong> <%= produto.getAno() %><br>
                        <strong>Preço:</strong> R$ <%= String.format("%.2f", produto.getPreco()) %><br>
                        <strong>Estoque:</strong> <%= produto.getQuantidade() %> unidades
                    </p>
                    
                    <div class="mt-auto">
                        <form action="carrinho" method="POST">
                            <input type="hidden" name="acao" value="add" />
                            <input type="hidden" name="id" value="<%= produto.getId() %>" />
                            
                            <div class="input-group">
                                <input type="number" name="quantidade" class="form-control" value="1" min="1" max="<%= produto.getQuantidade() %>" aria-label="Quantidade">
                                <button type="submit" class="btn btn-primary">
                                    <i class="bi bi-cart-plus"></i>
                                </button>
                            </div>
                            </form>
                    </div>
                </div>
            </div>
        </div>
        <% } } %>
    </div>
</div>

<%@include file="rodape.jsp" %>