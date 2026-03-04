# Payments Microservice (Kotlin + Spring Boot + Hexagonal Architecture)

Microserviço simples de pagamentos desenvolvido com **Kotlin + Spring
Boot**, utilizando **Arquitetura Hexagonal (Ports and Adapters)**.

O objetivo do projeto é demonstrar uma implementação **clean,
desacoplada e próxima de padrões utilizados no mercado**, mantendo o
código simples e fácil de entender.

O sistema permite:

-   Criar clientes
-   Criar pagamentos associados a clientes
-   Persistência em banco **H2 em memória**
-   Separação clara entre **Domain, Application e Infrastructure**

------------------------------------------------------------------------

# Arquitetura

O projeto utiliza **Arquitetura Hexagonal (Ports and Adapters)**.

Essa arquitetura separa o **núcleo de negócio (Domain)** da
**infraestrutura (REST, banco de dados, etc.)**, permitindo que o
domínio permaneça independente de frameworks.

Fluxo da aplicação:

Controller (Adapter In)\
↓\
UseCase (Application)\
↓\
Domain (Entities / Business Rules)\
↓\
Port (Repository Interface)\
↓\
Adapter Out (Persistence JPA)\
↓\
Database (H2)

Estrutura das camadas:

  Camada           Responsabilidade
  ---------------- ------------------------------------
  Domain           Regras de negócio e entidades
  Application      Casos de uso do sistema
  Ports            Interfaces de entrada e saída
  Adapters         Implementações REST e persistência
  Infrastructure   Banco de dados e frameworks

------------------------------------------------------------------------

# Estrutura do Projeto

src/main/kotlin/com/fagner/payments

adapters\
├─ input\
│ └─ rest\
│ ├─ controller\
│ └─ dto\
│\
└─ output\
└─ persistence\
├─ adapter\
├─ entity\
├─ mapper\
└─ repository

application\
├─ port\
│ ├─ input\
│ └─ output\
│\
└─ service

domain\
├─ model\
└─ exception

------------------------------------------------------------------------

# Tecnologias Utilizadas

-   Kotlin\
-   Spring Boot\
-   Spring Web\
-   Spring Data JPA\
-   H2 Database\
-   Maven\
-   Jakarta Validation

------------------------------------------------------------------------

# Como Executar o Projeto

## 1️⃣ Clonar o repositório

git clone https://github.com/seu-usuario/payments-ms.git

Entrar na pasta:

cd payments-ms

------------------------------------------------------------------------

## 2️⃣ Rodar a aplicação

mvn spring-boot:run

A aplicação iniciará em:

http://localhost:8080

------------------------------------------------------------------------

# Banco de Dados (H2)

O projeto usa **H2 em memória**.

Console do banco:

http://localhost:8080/h2-console

Configuração:

  Campo      Valor
  ---------- ------------------------
  JDBC URL   jdbc:h2:mem:paymentsdb
  User       sa
  Password   (vazio)

------------------------------------------------------------------------

# Endpoints da API

## Criar Cliente

POST /v1/clients

Request:

{ "name": "Maria Silva", "document": "12345678901" }

Response (201):

{ "id": "uuid", "name": "Maria Silva", "document": "12345678901" }

------------------------------------------------------------------------

## Buscar Cliente

GET /v1/clients/{id}

Response:

{ "id": "uuid", "name": "Maria Silva", "document": "12345678901" }

------------------------------------------------------------------------

## Criar Pagamento

POST /v1/payments

Request:

{ "clientId": "uuid-do-cliente", "amount": 49.90, "currency": "BRL" }

Response (201):

{ "id": "uuid", "clientId": "uuid", "amount": 49.90, "currency": "BRL",
"status": "CREATED", "createdAt": "2026-03-04T22:30:00Z" }

------------------------------------------------------------------------

# Validações

Validações são aplicadas em **três níveis**:

DTO → valida entrada da API\
Application → regras de aplicação\
Domain → invariantes de negócio

Exemplos:

-   valor não pode ser negativo
-   cliente precisa ter documento
-   cliente não encontrado

------------------------------------------------------------------------

# Mappers

O projeto utiliza **mappers manuais** para converter entre:

Domain ↔ Entity

Exemplo:

Client ↔ ClientEntity\
Payment ↔ PaymentEntity

Os mappers são implementados como `object` em Kotlin (singleton).

------------------------------------------------------------------------

# Possíveis Melhorias

Para evoluir o projeto para algo ainda mais próximo de produção:

-   Adicionar OpenAPI / Swagger
-   Implementar idempotência em pagamentos
-   Adicionar eventos de domínio
-   Adicionar testes unitários
-   Adicionar Flyway ou Liquibase
-   Persistência com PostgreSQL
-   Dockerização

------------------------------------------------------------------------

# Conceitos Demonstrados

Este projeto demonstra:

-   Arquitetura Hexagonal
-   Ports and Adapters
-   Clean Architecture
-   Separação de camadas
-   DTO Pattern
-   Repository Pattern
-   Domain Model

------------------------------------------------------------------------

# Autor

Fagner Tavares\
Desenvolvedor Backend focado em **Java, Kotlin, Spring Boot e
Microservices**
