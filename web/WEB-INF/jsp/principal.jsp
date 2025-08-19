<%-- 
    Document   : principal
    Created on : 27 de mai. de 2025, 19:34:15
    Author     : Leonardo Oliveira Moreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("paginaAtual", "perfil"); %>
<%@include file="/cabecalho.jsp" %>

<h1>Olá, <%= usuario.getNome()%></h1>
<h3>Perfil de: <%=(usuario.isAdministrador() ? "Administrador" : "Cliente") %></h3>

<%@include file="rodape.jsp" %>