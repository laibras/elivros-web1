<%@page import="java.text.SimpleDateFormat"%>
<%@page import="modelo.compra.ItemCompra"%>
<%@page import="modelo.compra.Compra"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% request.setAttribute("paginaAtual", "minhasCompras"); %>
<%@include file="/cabecalho.jsp" %>

<div class="container mt-4">
    <h3 class="mb-4">Minhas Compras</h3>

    <%
        List<Compra> compras = (List<Compra>) request.getAttribute("minhasCompras");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy 'às' HH:mm");
    %>

    <% if (compras == null || compras.isEmpty()) { %>
        <div class="alert alert-info" role="alert">
            Você ainda não realizou nenhuma compra. <a href="Inicio" class="alert-link">Que tal começar agora?</a>
        </div>
    <% } else { %>
        <div class="table-responsive">
            <table class="table table-striped table-hover">
                <thead class="table-light">
                    <tr>
                        <th>Pedido #</th>
                        <th>Data</th>
                        <th>Status</th>
                        <%-- A NOVA COLUNA DE ITENS --%>
                        <th>Itens Comprados</th>
                        <th class="text-end">Valor Total</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Compra compra : compras) { %>
                        <tr>
                            <td><%= String.format("%06d", compra.getId()) %></td>
                            <td><%= sdf.format(compra.getData()) %></td>
                            <td><span class="badge bg-primary"><%= compra.getStatus() %></span></td>
                            
                            <%-- INÍCIO DA LÓGICA PARA EXIBIR OS ITENS --%>
                            <td>
                                <ul class="list-unstyled mb-0" style="font-size: 0.85rem;">
                                    <% for (ItemCompra item : compra.getItens()) { %>
                                        <li>
                                            <%= item.getQuantidade() %>x <%= item.getProduto().getTitulo() %> 
                                            <em>(R$ <%= String.format("%.2f", item.getPrecoUnitario()) %> cada)</em>
                                        </li>
                                    <% } %>
                                </ul>
                            </td>
                            <%-- FIM DA LÓGICA PARA EXIBIR OS ITENS --%>

                            <td class="text-end">R$ <%= String.format("%.2f", compra.getTotal()) %></td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    <% } %>
</div>

<%@include file="/rodape.jsp" %>