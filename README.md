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

## Etapa 3: Configuração, Persistência e Execução em Ambiente Containerizado

Nesta etapa, a arquitetura foi evoluída para o padrão *Cloud Native*, garantindo isolamento de ambiente, persistência definitiva de dados e centralização de configurações.

### Evoluções Implementadas
* **Migração de Base de Dados:** Substituição do banco em memória (H2) pelo **PostgreSQL 16**, executado num container dedicado com volume persistente (`postgres_data`).
* **Perfis (Profiles) e Variáveis de Ambiente:** Segregação das configurações em `application-dev.properties` (desenvolvimento local) e `application-prod.properties` (ambiente de containers), parametrizadas via variáveis de ambiente (`DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, `SERVICO_INTEGRACAO_URL`).
* **Configuração Centralizada:** Implementação do **Spring Cloud Config Server** (porta `8888`, operando no perfil `native`), centralizando o fornecimento de propriedades para os microsserviços do ecossistema.
* **Containerização (Docker):** Criação de ficheiros `Dockerfile` utilizando *multi-stage build* (compilação via Maven e execução enxuta em Eclipse Temurin JRE 21 Alpine) para cada aplicação.
* **Orquestração Local (Docker Compose):** Unificação de toda a infraestrutura (`postgres-db`, `config-server`, `integracao-service` e `matheus-api`) num único ficheiro `docker-compose.yml`, utilizando rede interna customizada (`microsservicos-net`) e controlo de ordem de arranque (`depends_on` com `healthcheck`).

### Como Executar Todo o Ecossistema (Docker Compose)
Certifique-se de que os projetos `matheus-api`, `integracao-service` e `config-server` estão no mesmo diretório base e execute na raiz da `matheus-api` o comando:

    docker compose up --build

### Endpoints da Infraestrutura
* **Matheus API (Swagger):** `http://localhost:8080/swagger-ui/index.html`
* **Integração Service (Swagger):** `http://localhost:8081/swagger-ui/index.html`
* **Config Server (Propriedades da API):** `http://localhost:8888/matheus-api/prod`
