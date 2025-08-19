<%@page import="modelo.categoria.Categoria"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("paginaAtual", "cadastro"); %>
<%@include file="/cabecalho.jsp" %>

<div class="container py-4">
    <h3 class="mb-4">Cadastro de Categorias</h3>

    <form action="<%= request.getContextPath()%>/admin/ListarCategorias" method="post" class="mb-4">
        <div class="mb-3">
            <label for="formControlDescricao" class="form-label">Descrição</label>
            <input type="text" name="descricao" class="form-control" id="formControlDescricao" placeholder="Descrição">
        </div>
        <div class="d-flex gap-2">
        <input class="btn btn-success" type="submit" value="Procurar" />
        <a role="button" class="btn btn-outline-primary" href="<%= request.getContextPath()%>/admin/FormCategoria">Inserir novo</a>
        </div>
    </form>

    <%
        List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
        if (categorias != null && !categorias.isEmpty()) {
    %>
    <div class="table-responsive">
    <table class="table table-bordered table-hover align-middle">
        <thead class="table-light">
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Descrição</th>
                <th scope="col" class="text-end">Ações</th>
            </tr>
        </thead>
        <tbody>
            <%
                for (Categoria c : categorias) {
            %>
            <tr>
                <td class="align-middle"><%= c.getId()%></td>
                <td class="align-middle"><%= c.getDescricao()%></td>
                <td class="text-end">
                    <a role="button" class="btn btn-sm btn-warning me-1" href="<%= request.getContextPath()%>/admin/FormCategoria?id=<%= c.getId()%>">Atualizar</a>
                    <a role="button" class="btn btn-sm btn-danger" href="<%= request.getContextPath()%>/admin/RemoverCategoria?id=<%= c.getId()%>">Remover</a>
                </td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
    <%
        }
    %>
</div>

<%@include file="../rodape.jsp" %>

