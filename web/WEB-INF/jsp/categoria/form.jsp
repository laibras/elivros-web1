<%-- 
    Document   : form
    Created on : 17 de jun. de 2025, 18:23:06
    Author     : Leonardo Oliveira Moreira
--%>

<%@page import="modelo.categoria.Categoria"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/cabecalho.jsp" %>
<h3>Cadastro de Categorias</h3>
<%    
    Categoria categoria = (Categoria) request.getAttribute("categoria");
%>
<form action="<%= request.getContextPath()%>/admin/SalvarCategoria" method="post">
    <% if (categoria != null) { %>
    <div class="mb-3">
        <label for="formControlId" class="form-label">Id</label>
        <input type="text" name="id" class="form-control" id="formControlId" placeholder="Id" value="<%= (categoria != null ? categoria.getId() : "")%>" readonly>
    </div>
    <% } %>
    <div class="mb-3">
        <label for="formControlDescricao" class="form-label">Descrição</label>
        <input type="text" name="descricao" class="form-control" id="formControlDescricao" placeholder="Descrição" value="<%= (categoria != null ? categoria.getDescricao() : "")%>">
    </div>
    <input class="btn btn-primary" type="submit" value="Salvar" />
</form>
<%@include file="../rodape.jsp" %>
