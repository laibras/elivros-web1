<%-- 
    Document   : index
    Created on : 22 de mai. de 2025, 18:42:53
    Author     : Leonardo Oliveira Moreira
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setAttribute("paginaAtual", "login"); %>
<%@include file="cabecalho.jsp" %>
<div class="container d-flex justify-content-center align-items-center" >
    <div class="card shadow-sm" style="max-width: 400px; width: 100%;">
        
       <div class="card-header bg-white">
        <h3 class="mb-0 text-center">Identificação do Usuário</h3>
        </div>
        <div class="card-body">
             
            <form action="Login" method="post" novalidate>
                <div class="mb-3">
                    <label for="formControlLogin" class="form-label">Login</label>
                    <input type="text" name="login" class="form-control" id="formControlLogin" placeholder="Seu login" required />
                </div>
                <div class="mb-3">
                    <label for="formControlSenha" class="form-label">Senha</label>
                    <input type="password" name="senha" class="form-control" id="formControlSenha" placeholder="Sua senha" required />
                </div>
                <div class="d-flex justify-content-between">
                    <input class="btn btn-custom" type="submit" value="Entrar" />
                    <a class="btn btn-outline-secondary" href="cadastrarCliente.jsp">Novo Cliente</a>
                </div>
            </form>
        </div>
    </div>
</div>
<%@include file="rodape.jsp" %>
