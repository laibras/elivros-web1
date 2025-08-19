<%-- %>

    Document   : atualizarDados
    Created on : 3 de jun. de 2025, 18:49:36
    Author     : Leonardo Oliveira Moreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("paginaAtual", "atualizar"); %>
<%@include file="/cabecalho.jsp" %>
<body class="bg-light">
        <div class="container mt-5">
            <div class="card shadow card-custom">
                <div class="card-body">
                    <h2 class="card-title mb-4">Atualizar Dados Pessoais</h2>

                    <% if (mensagem != null) { %>
                        <div class="alert alert-success" role="alert">
                            <%= mensagem %>
                        </div>
                    <% } %>

                    <form action="<%= request.getContextPath() %>/AtualizarUsuario" method="post">
                        <div class="mb-3">
                            <label class="form-label">ID</label>
                            <input type="text" class="form-control" name="id" value="<%= usuario.getId() %>" readonly>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Nome</label>
                            <input type="text" class="form-control" name="nome" value="<%= usuario.getNome() %>">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Cpf</label>
                            <input type="text" class="form-control" name="cpf" value="<%= usuario.getCpf() %>">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Endereço</label>
                            <input type="text" class="form-control" name="endereco" value="<%= usuario.getEndereco() %>">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Email</label>
                            <input type="email" class="form-control" name="email" value="<%= usuario.getEmail() %>">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Login</label>
                            <input type="text" class="form-control" name="login" value="<%= usuario.getLogin() %>">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Senha</label>
                            <input type="password" class="form-control" name="senha" value="<%= usuario.getSenha() %>">
                        </div>
                        <div class="d-flex justify-content-between">
                            <a href="Principal" class="btn btn-secondary">Cancelar</a>
                            <button type="submit" class="btn btn-warning">Salvar Alterações</button>
                        </div>
                    </form>
                    
                    <hr class="my-4">
                <div class="text-center">
                    <form id="formExclusao" action="<%= request.getContextPath() %>/RemoverUsuario" method="post" style="display: none;">
                        <input type="hidden" name="id" value="<%= usuario.getId() %>">
                    </form>
                    <button type="button" class="btn btn-danger" onclick="confirmarExclusao()">Excluir minha conta</button>
                </div>
            </div>
        </div>
    </div>
    
    <script>
        function confirmarExclusao() {
            if (confirm("Tem certeza que deseja excluir sua conta? Esta ação não pode ser desfeita.")) {
                document.getElementById('formExclusao').submit();
            }
        }
    </script>
               
    </body>
<%@include file="rodape.jsp" %>