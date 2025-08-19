# 📖 eLivros - E-commerce de Livros Online

## 📝 Descrição

**eLivros** é um projeto de e-commerce para a venda de livros, simulando o funcionamento de uma loja virtual completa. O sistema foi desenvolvido como parte da avaliação da cadeira de **Programação para Web 1** no período de **2025.1**.

O projeto abrange desde a visualização de produtos por parte dos clientes até um painel administrativo completo para gerenciamento da loja, incluindo o controle de estoque e a geração de relatórios em PDF.

## ✨ Funcionalidades Principais

O sistema é dividido em duas áreas principais: a área do cliente e a área administrativa.

### Para Clientes:

  - **Navegação:** Visualização da vitrine de produtos com imagens, preços e estoque.
  - **Carrinho de Compras:** Adição de múltiplos itens ao carrinho, com ajuste de quantidade.
  - **Finalização de Compra:** Processo de checkout seguro que atualiza o estoque dos produtos em tempo real.
  - **Área do Usuário:** Cadastro, login e uma área para visualizar o histórico de compras com detalhes de cada pedido.

### Para Administradores:

  - **Painel de Controle:** Área segura e exclusiva para administradores.
  - **CRUD de Produtos:** Gerenciamento completo de produtos (criar, ler, atualizar e deletar).
  - **Gerenciamento de Categorias:** Organização dos livros em categorias.
  - **Gestão de Compras:** Visualização de todas as compras realizadas no sistema com a opção de deletá-las.
  - **Relatórios em PDF:** Geração de relatórios dinâmicos, incluindo:
      - Total de compras por cliente em um determinado período.
      - Produtos com estoque zerado ou negativo.
      - Total de valor recebido por dia em um período.

## 🛠️ Tecnologias Utilizadas

  * **Backend:** Java, com Servlets e JSP (JavaServer Pages)
  * **Frontend:** HTML5, CSS3, Bootstrap 5
  * **Banco de Dados:** PostgreSQL
  * **Servidor de Aplicação:** Apache Tomcat
  * **Geração de PDF:** Biblioteca Apache PDFBox
  * **Dependências:** JDBC Driver para PostgreSQL

## 🚀 Como Executar o Projeto

1.  **Pré-requisitos:**

      * JDK 8 ou superior
      * Apache Tomcat 9 ou superior
      * PostgreSQL Server
      * Uma IDE Java (ex: NetBeans, Eclipse, IntelliJ)

2.  **Configuração do Banco de Dados:**

      * Crie um banco de dados no PostgreSQL com o nome `elivros`.
      * Execute o script SQL do projeto (adicione o caminho para o seu arquivo `.sql` aqui) para criar as tabelas e popular os dados iniciais.
      * Configure as credenciais do banco de dados no arquivo `src/config/Config.java`.

3.  **Execução:**

      * Clone este repositório: `git clone https://github.com/seu-usuario/elivros.git`
      * Importe o projeto na sua IDE.
      * Configure o servidor Apache Tomcat na IDE.
      * Compile e execute o projeto no servidor. A aplicação estará disponível em `http://localhost:8080/elivros`.

## 👨‍💻 Autores e Colaboradores

Este projeto foi desenvolvido por:

  * **José Roberto** - *Desenvolvimento* 
  * **Julião Chaves** - *Desenvolvimento*
  * **Prof. Leonardo Oliveira Moreira** - *Orientador*

