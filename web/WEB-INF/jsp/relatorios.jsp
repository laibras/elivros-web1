<%-- 
    Document   : relatorios
    Created on : Jul 28, 2025, 9:36:06 AM
    Author     : jsrob
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("paginaAtual", "relatorios"); %>
<%@include file="/cabecalho.jsp" %>

<div class="container mt-4">
    <h3 class="mb-4">Painel de Relatórios</h3>

    <div class="row">
        <div class="col-md-6 mb-4">
            <div class="card h-100">
                <div class="card-body">
                    <h5 class="card-title">Produtos Faltantes em Estoque</h5>
                    <p class="card-text">Gera um PDF com todos os produtos cujo estoque é zero ou negativo, ordenados por nome.</p>
                    <form action="<%= request.getContextPath() %>/admin/ListarProdutosFaltantes" method="get" target="_blank">
                        <button type="submit" class="btn btn-primary">Gerar Relatório</button>
                    </form>
                </div>
            </div>
        </div>

        <div class="col-md-6 mb-4">
            <div class="card h-100">
                <div class="card-body">
                    <h5 class="card-title">Total de Compras por Cliente</h5>
                    <p class="card-text">Gera um PDF com o total de compras por cliente em um período, ordenado por quem mais comprou.</p>
                    <form action="<%= request.getContextPath() %>/admin/RelatorioComprasCliente" method="get" target="_blank">
                        <div class="row">
                            <div class="col">
                                <label for="dataInicioCliente" class="form-label">Data Início</label>
                                <input type="date" class="form-control" id="dataInicioCliente" name="dataInicio" required>
                            </div>
                            <div class="col">
                                <label for="dataFimCliente" class="form-label">Data Fim</label>
                                <input type="date" class="form-control" id="dataFimCliente" name="dataFim" required>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary mt-3">Gerar Relatório</button>
                    </form>
                </div>
            </div>
        </div>
        
        <div class="col-md-6 mb-4">
            <div class="card h-100">
                <div class="card-body">
                    <h5 class="card-title">Total de Vendas por Dia</h5>
                    <p class="card-text">Gera um PDF com o valor monetário total recebido por dia em um período, ordenado por data.</p>
                    <form action="<%= request.getContextPath() %>/admin/RelatorioVendasDiarias" method="get" target="_blank">
                        <div class="row">
                            <div class="col">
                                <label for="dataInicioVendas" class="form-label">Data Início</label>
                                <input type="date" class="form-control" id="dataInicioVendas" name="dataInicio" required>
                            </div>
                            <div class="col">
                                <label for="dataFimVendas" class="form-label">Data Fim</label>
                                <input type="date" class="form-control" id="dataFimVendas" name="dataFim" required>
                            </div>
                        </div>
                        <button type="submit" class="btn btn-primary mt-3">Gerar Relatório</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="/rodape.jsp" %>