# Sobre o Projeto

Trabalho desenvolvido para a disciplina de Programação Orientada a Objetos (POO) do curso de TADS no IFRN.

# 🔍 Como Utilizar o Sistema

## Menu Principal

Ao iniciar o programa, o usuário é apresentado ao menu principal com as opções:

- **Cliente (C) 👤:** Para acessar o fluxo de compras.
- **Administrador (A) 🔧:** Para acessar o menu administrativo de gerenciamento.
- **Sair (X) ❌:** Para encerrar o programa.

## Fluxo de Uso para Clientes

### Cadastro ou Login
- Informe se já possui cadastro.
- Caso possua, insira o email para recuperação dos dados.
- Se não possuir cadastro, será solicitado o preenchimento dos dados (**nome, endereço e telefone**) para criar um novo cadastro.

### Informação do Saldo
- O cliente deve informar o valor disponível para realizar compras.

### Visualização do Catálogo
- O catálogo de produtos é exibido com os dados de cada item (**ID, nome, preço, quantidade em estoque**).

### Seleção e Compra
- Selecione um produto pelo seu **ID** e informe a quantidade desejada.
- O sistema realiza as verificações necessárias:
  - Verifica se a quantidade solicitada está disponível no estoque.
  - Verifica se o cliente possui saldo suficiente para a compra.
- Em caso de sucesso, a compra é processada, o saldo é atualizado e os detalhes da compra são exibidos.

## Fluxo de Uso para Administradores

- **Adicionar Produto:** Insira os detalhes do novo produto, como **nome, preço, quantidade em estoque, marca, modelo e tempo de garantia**.
- **Remover Produto:** Informe o **ID** do produto que deseja remover do catálogo.
- **Listar Produtos:** Visualize a lista completa dos produtos cadastrados.
- **Outras Opções:** Voltar ao menu principal ou encerrar o programa.

## 🗂️ Estrutura do Código

- **Main.java:** Classe principal que contém a lógica do menu e o fluxo de operações para clientes e administradores.
- **Entidades:**
  - **Cliente:** Representa o cliente do sistema, contendo informações pessoais e métodos para realizar pedidos.
  - **Produto:** Classe base para os produtos.
  - **Eletronico:** Subclasse de **Produto** com atributos específicos (**marca, modelo e garantia**).
  - **Pedido:** Representa um pedido de compra realizado pelo cliente.
  - **PagamentoPix:** Simula um método de pagamento via PIX.
  - **PersistenciaDados:** Responsável por ler e salvar os dados de clientes e produtos em arquivos de texto (**clientes.txt** e **produtos.txt**).

## ⚙️ Considerações Finais

- **Tratamento de Erros:** O sistema inclui tratamento de exceções para entradas inválidas, garantindo uma melhor experiência ao usuário.
- **Persistência:** Ao final da execução, os dados são salvos automaticamente, permitindo que as informações sejam mantidas entre diferentes execuções.

## 🤝 Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para enviar pull requests ou reportar issues para ajudar a melhorar o projeto.
