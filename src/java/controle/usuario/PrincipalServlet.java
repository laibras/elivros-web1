/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controle.usuario;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;



/**
 *
 * @author jsrob
 */

public class PrincipalServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Verifica se o usuário está logado (opcional mas recomendado)
        HttpSession sessao = request.getSession(false);
        if (sessao != null && sessao.getAttribute("usuario") != null) {
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/principal.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("index.jsp"); // ou página de login
        }
    }
    }

