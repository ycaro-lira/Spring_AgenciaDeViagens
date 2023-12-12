# Sistema de Vendas de Passagens Aéreas - Protótipo

Este é um projeto Java que utiliza o Java Persistence API (JPA) para persistência de dados. O sistema gerencia clientes, passagens aéreas e compras associadas.

## Entidades

### Cliente

Representa um cliente que pode efetuar compras de passagens.

Atributos:
- `id`: Identificador único do cliente.
- `nome`: Nome do cliente.
- `email`: Endereço de e-mail do cliente.
- `senha`: Senha do cliente.
- `compras`: Lista de compras associadas ao cliente.

### Passagem

Representa uma passagem aérea disponível para compra.

Atributos:
- `id`: Identificador único da passagem.
- `saindo`: Local de partida da passagem.
- `indo`: Destino da passagem.
- `valor`: Valor da passagem.
- `compras`: Lista de compras associadas à passagem.

### Compra

Representa uma transação de compra de passagem realizada por um cliente.

Atributos:
- `id`: Identificador único da compra.
- `cliente`: Cliente que realizou a compra (relacionamento muitos-para-um).
- `passagem`: Passagem comprada (relacionamento muitos-para-um).
- `dataCompra`: Data e hora da compra.
- `valorTotal`: Valor total da compra.

## Configuração

O projeto utiliza a biblioteca Spring para injeção de dependência e configuração. Certifique-se de configurar corretamente o arquivo `application.properties` para a conexão com o banco de dados.

```properties
# Configurações do Banco de Dados
spring.datasource.url=jdbc:mysql://localhost:3306/nome_do_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update


