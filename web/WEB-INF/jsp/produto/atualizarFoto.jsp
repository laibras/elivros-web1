<%-- 
    Document   : atualizarFoto
    Created on : 23 de jul. de 2025, 13:00:06
    Author     : Julião Chaves
--%>

<%@page import="modelo.produto.Produto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/cabecalho.jsp" %>
<h3>Atualizar Foto do Produto</h3>
<%
    List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");
%>
<form method="post" enctype="multipart/form-data" action="<%= request.getContextPath()%>/admin/UploadProdutoFoto">
    <div class="mb-3">
        <label for="formControlProdutoId" class="form-label">Produto</label>
        <select name="produtoId" class="form-control" id="formControlProdutoId">
        <%
        for (Produto p : produtos) {
        %>
        <option value="<%= p.getId() %>"><%= p.getTitulo() %></option>
        <%
        }
        %>
        </select>
    </div>
   <div class="mb-3">
        <label for="formControlFoto" class="form-label">Foto</label>
        <input type="file" name="foto" class="form-control" id="formControlFoto" placeholder="Foto" />
   </div>
    <input class="btn btn-primary" type="submit" value="Salvar" />
</form>
<%@include file="../rodape.jsp" %>