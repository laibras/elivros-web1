
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("paginaAtual", "cadastro"); %>
<%@include file="cabecalho.jsp" %>


   
        <div class="card shadow rounded card-custom">
            <div class="card-header bg-white">
                <h2 class="mb-0">Cadastro de Cliente</h2>
            </div>
            <div class="card-body">
                <form action="<%= request.getContextPath() %>/InserirCliente" method="post">
                    <div class="mb-3">
                        <label for="formControlNome" class="form-label">Nome</label>
                        <input type="text" name="nome" class="form-control form-control-sm" id="formControlNome" placeholder="Nome completo" required>
                    </div>
                    <div class="mb-3">
                        <label for="formControlCpf" class="form-label">CPF</label>
                        <input type="text" name="cpf" class="form-control form-control-sm" id="formControlCpf" placeholder="000.000.000-00" required>
                    </div>
                    <div class="mb-3">
                        <label for="formControlEndereco" class="form-label">Endereço</label>
                        <input type="text" name="endereco" class="form-control form-control-sm" id="formControlEndereco" placeholder="Rua, número, complemento" required>
                    </div>
                    <div class="mb-3">
                        <label for="formControlEmail" class="form-label">Email</label>
                        <input type="email" name="email" class="form-control form-control-sm" id="formControlEmail" placeholder="seuemail@exemplo.com" required>
                    </div>
                    <div class="mb-3">
                        <label for="formControlLogin" class="form-label">Login</label>
                        <input type="text" name="login" class="form-control form-control-sm" id="formControlLogin" placeholder="Nome de usuário" required>
                    </div>
                    <div class="mb-3">
                        <label for="formControlSenha" class="form-label">Senha</label>
                        <input type="password" name="senha" class="form-control form-control-sm" id="formControlSenha" placeholder="Senha segura" required>
                    </div>
                    <div class="d-flex justify-content-end gap-2">
                        <a href="Inicio" class="btn btn-secondary">Retornar</a>
                        <button type="submit" class="btn btn-custom ">Salvar</button>
                    </div>
                </form>
            </div>
    


</body>
<%@include file="rodape.jsp" %>

