LojaJdbc

Sistema de gerenciamento de vendas desenvolvido em Java puro com JDBC, sem uso de frameworks ORM. A aplicação roda no terminal com menus interativos e utiliza MySQL como banco de dados relacional.

Sobre o que se trata o projeto?

A LojaJdbc simula o back-end de uma loja, permitindo o cadastro e gerenciamento de produtos, categorias e pedidos diretamente via Java e SQL, sem frameworks ORM. O objetivo é demonstrar o uso direto da API JDBC para operações de CRUD.

Funcionalidades

    Clientes — cadastro, consulta, alteração e remoção com endereço vinculado
    Vendedores — cadastro com salário, telefone e e-mail, comissão(a comissão só será atribuida caso pedido que esteja relacionado esteja finalizado)
    Produtos — cadastro com preço (BigDecimal), estoque, categoria e unidade de medida
    Pedidos — criação com múltiplos itens, controle de estoque em transação, cancelamento com devolução dos itens ao estoque
    Status de pedido — ciclo ABERTO → FILA → PROCESSANDO → FINALIZADO
    Thread de processamento — ProcessadorPedido roda em background a cada 5 segundos, avançando pedidos da fila automaticamente
    Menus interativos — navegação via console com validação de entrada

Arquitetura do Sistema
    src/
    ├── Main.java                   # Ponto de entrada — inicia thread + menu
    ├── conexao/
    │   └── Conexao.java            # Singleton de conexão + fábrica de conexões isoladas
    ├── entidades/
    │   ├── Cliente.java
    │   ├── Vendedor.java
    │   ├── Produto.java            # Preço com BigDecimal
    │   ├── Endereco.java
    │   ├── Pedido.java
    │   └── ItemPedido.java         # Calcula subtotal no construtor
    ├── DAO/
    │   ├── ClienteDAO.java         # CRUD com transação e rollback
    │   ├── VendedorDAO.java
    │   ├── ProdutoDAO.java
    │   ├── PedidoDAO.java          # Venda atômica: pedido + itens + estoque
    │   ├── EnderecoDAO.java
    │   ├── EnderecoClienteDAO.java
    │   ├── EnderecoVendedorDAO.java
    │   ├── ItemPedidoDAO.java
    │   ├── ClasseDAO.java          # Categorias de produto
    │   └── MedidaDAO.java          # Unidades de medida
    ├── servicos/
    │   ├── ClienteService.java
    │   ├── VendedorSer.java
    │   ├── ProdutoService.java
    │   ├── PedidoService.java
    │   ├── EnderecoService.java
    │   ├── EnderecoClienteSer.java
    │   ├── EnderecoVendedorService.java
    │   ├── ClasseService.java
    │   └── MedidaService.java
    ├── menu/
    │   ├── MenuPrincipal.java      # Menu raiz com ASCII art
    │   ├── Menuadd.java
    │   ├── cliente/                # Submenus de cliente
    │   ├── pedido/                 # Submenus de pedido
    │   ├── produto/                # Submenus de produto
    │   ├── vendedor/               # Submenus de vendedor
    │   └── endereco/               # Submenus de endereço
    ├── threads/
    │   └── ProcessadorPedido.java  # Thread daemon de processamento de pedidos
    └── util/
        └── Console.java

Modelo de dados

    clientes
    ├── id_clientes (PK)
    ├── nome_clientes
    ├── cpf_clientes
    └── email_clientes

    vendedores
    ├── id_vendedor (PK)
    ├── nome_vendedor
    ├── telefone_vendedor
    ├── email_vendedor
    └── salario

    enderecos
    ├── id_endereco (PK)
    ├── rua, numero, bairro, cidade, cep
    └── (vinculado a cliente ou vendedor via tabela associativa)

    classe              ← categorias de produto
    ├── idClasse (PK)
    └── nome_classe

    unidade_medida
    ├── idUnidade (PK)
    └── nome_medida

    produtos
    ├── id_produtos (PK)
    ├── nome_produtos
    ├── preco (DECIMAL)
    ├── estoque
    ├── idClasse (FK → classe)
    └── idUnidade (FK → unidade_medida)

    pedido
    ├── id_pedido (PK)
    ├── id_clientes (FK → clientes)
    ├── id_vendedor (FK → vendedores)
    ├── status_pedido   ← ABERTO | FILA | PROCESSANDO | FINALIZADO
    ├── valor_total
    └── observacao

    item_pedido
    ├── id_pedido (FK → pedido)
    ├── id_produtos (FK → produtos)
    ├── quantidade
    ├── preco_unitario
    └── subtotal

 Pré-requisitos

Java 11 ou superior
XAMPP Control Panel
MySQL 8.x rodando localmente
IntelliJ IDEA (recomendado) — o .iml e a lib já estão no repositório

Tutorial de como executar o Código

1- Clone o repositório

git clone https://github.com/matheus15-hub/LojaJdbc.git
cd LojaJdbc

2 - Execute o XAMPP Control Panel e ative o MySql

3 - Execute o arquivo SQL disponível no repositório

4 - Configure a conexão

Abra src/conexao/Conexao.java e ajuste suas credenciais:
javaprivate static final String url  = "jdbc:mysql://localhost:3306/sistema_vendas";(isso já está incluso no código)
private static final String user = "root";
private static final String pass = "sua_senha";

Execute pelo IntelliJ IDEA
Abra o projeto no IntelliJ — a lib mysql-connector-j-9.7.0.jar já está em lib/ e configurada no módulo. Execute a classe Main.

Ou compile manualmente via terminal
bashjavac -cp lib/mysql-connector-j-9.7.0.jar -d out $(find src -name "*.java")
java  -cp out:lib/mysql-connector-j-9.7.0.jar Main


Fluxo de um pedido
    Criação (ABERTO)
        │
        ▼
    Enviado à fila (FILA)
        │
        ▼  ← Thread ProcessadorPedido verifica a cada 5s
    Em processamento (PROCESSANDO)
        │
        ▼  ← Thread aguarda mais 5s
    Finalizado (FINALIZADO)


Tecnologias


Tecnologia          Versão    Uso

Java                11+      Linguagem principal
JDBC                —        Acesso ao banco sem ORM
MySQL               8.x      Banco de dados relacional
MySQL Connector/J   9.7.0    Driver JDBC (incluído em lib/)
XAMPP               —        Ambiente local com Apache e MySQL
IntelliJ IDEA       —        IDE de desenvolvimento


Desenvolvedoress do Projeto Desafio Integrador

Alunos:
    Matheus Rodrigues Santos
    Gabriel Hardt Klipe
    Kevin Richardt
    João Victor Oliveira

Orientadores:
    Moacir Guedes Oliveira
    Bruno Dion Correa dos Santos

    Divisão de Tarefas:

    Matheus Rodrigues Santos: Responsável pelo desenvolvimento da Partição de Produtos (Entidade, classe de validação ProdutoService, criação do ProdutoDAO e submenus de terminal correspondentes).
    Criação e estruturação inicial do banco de dados relacional MySQL.

    Gabriel Hardt Klipe: Criando os metodos Pedidos e ItemPedido (criando a logica do pedido e separação de dados usando PedidoDAO com controle transacional de estoque).
    Implementação da lógica de transição dos Status do Pedido: ABERTO, FILA, PROCESSANDO , FINALIZADO.
    Criação e configuração do sistema de threads ProcessadorPedido.

    Kevin Richardt: Desenvolvimento do módulo de Vendedores Entidade, classe de validação VendedorSer e persistência em VendedorDAO.

    João Victor Oliveira: Desenvolvimento do módulo de Clientes e gerenciamento de múltiplos Endereços(Em conjuto com Matheus) Entidades, ClienteDAO, EnderecoDAO e tabelas associativas
    Padronização visual do console através da classe Console e estruturação da árvore do MenuPrincipal.

2026 Universidade Campo Real
