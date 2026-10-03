# Integração Service - Matheus API (Etapa 2)

Este é um microsserviço independente desenvolvido para a Etapa 2 da disciplina de Arquitetura de Microsserviços e Cloud Native. Ele atua como um *Gateway* de domínio, isolando a comunicação com APIs de terceiros.

## Responsabilidade
O `integracao-service` foi extraído da aplicação principal (`matheus-api`) para lidar exclusivamente com chamadas de rede externas. Atualmente, ele consome a **CheapShark API** via OpenFeign para buscar informações sobre jogos (como a URL da capa) e devolve esses dados formatados em contratos internos (DTOs).

## Tecnologias Utilizadas
* **Java 21**
* **Spring Boot 3.3.2**
* **Spring Cloud OpenFeign** (Client HTTP)
* **Spring Validation** (Validação de dados)
* **Swagger/OpenAPI** (Documentação)

## Como Executar
O serviço está configurado para rodar na porta `8081` para não conflitar com a aplicação principal.

1. Clone o repositório.
2. Atualize as dependências do Maven.
3. Execute a classe `IntegracaoServiceApplication`.

## Documentação da API
A interface interativa do Swagger contendo os endpoints expostos por este serviço pode ser acedida localmente em:
`http://localhost:8081/swagger-ui/index.html`

---

## Etapa 3: Containerização e Configuração Centralizada
* Adicionado suporte a perfis de execução (`dev` e `prod`) e externalização da URL da CheapShark via variável de ambiente (`CHEAPSHARK_API_URL`).
* Integração com o **Spring Cloud Config Server** (`spring-cloud-starter-config`).
* Adicionado `Dockerfile` com *multi-stage build* (Java 21) para orquestração via Docker Compose.

---

## Etapa 4: Consumidor de Eventos Assíncronos (RabbitMQ)
* Adicionada dependência `spring-boot-starter-amqp` e configuração da fila `jogo.cadastrado.queue`.
* Implementado o listener `JogoEventConsumer` (`@RabbitListener`) para processamento assíncrono dos eventos de novos jogos publicados pela aplicação principal.
