# 🚨 Via Appia API

API desenvolvida em **Java 17 + Spring Boot 3** com **Autenticação JWT**, **Flyway** para versionamento de banco de dados e **Spring Data JPA** para persistência.  
O projeto faz parte do desafio *Via Appia*, responsável por gerenciar **incidentes**, **comentários** e **usuários**.

---

## 📌 Tecnologias Utilizadas
- Java 17
- Spring Boot 3
- Spring Security (JWT)
- Spring Data JPA
- Flyway (migrations)
- PostgreSQL
- Swagger / OpenAPI 3

---

## 📂 Estrutura de Pastas
src/main/java/com/desafioviaappia/api
┣ 📁 domain → entidades (User, Role, Incident, Comment)
┣ 📁 repositories → interfaces do JPA
┣ 📁 services → regras de negócio
┣ 📁 controllers → endpoints REST
┣ 📁 security → JWT, filtros e configs
┗ 📁 config → OpenAPI, etc

---

## 🛠️ Como Rodar o Projeto

### 1. Clonar repositório
```bash
git clone https://github.com/SEU-USUARIO/via-appia-api.git
cd via-appia-api

---
2. Configurar o application.properties

spring.datasource.url=jdbc:postgresql://localhost:5432/viaappia
spring.datasource.username=postgres
spring.datasource.password=postgres

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true

spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration

3. Criar Banco de Dados
CREATE DATABASE viaappia;

4. Rodar a aplicação
mvn spring-boot:run

🔑 Credenciais de Teste (Seeds)

Quando rodar a aplicação, o Flyway cria usuários default:

Admin

email: admin@viaappia.com

senha: admin123

Usuário comum

email: user@viaappia.com

senha: user123

📡 Endpoints Principais
Autenticação

POST /auth/login → gera token JWT

{
  "email": "admin@viaappia.com",
  "password": "admin123"
}

Incidentes

GET /incidents → lista incidentes

POST /incidents → cria novo incidente (necessita JWT)

Comentários

GET /comments/{incidentId} → lista comentários de um incidente

POST /comments → cria comentário (necessita JWT)

Estatísticas

GET /stats/incidents → estatísticas de incidentes

📖 Swagger

A documentação da API está disponível em:
👉 http://localhost:8080/swagger-ui.html

🚀 Próximos Passos

 Implementar o Front-end (React + Vite + Tailwind)

 Deploy em Render / Railway

 Adicionar cache com Caffeine

👨‍💻 Autor

Carlos Eduardo Brandão Rodrigues
📍 Brasília - DF
🔗 LinkedIn


---

Quer que eu já monte também a parte do **“How to test no Postman”** com os requests prontos (login, incident, co# 🚨 Via Appia API

API desenvolvida em **Java 17 + Spring Boot 3** com **Autenticação JWT**, **Flyway** para versionamento de banco de dados e **Spring Data JPA** para persistência.  
O projeto faz parte do desafio *Via Appia*, responsável por gerenciar **incidentes**, **comentários** e **usuários**.

---

## 📌 Tecnologias Utilizadas
- Java 17
- Spring Boot 3
- Spring Security (JWT)
- Spring Data JPA
- Flyway (migrations)
- PostgreSQL
- Swagger / OpenAPI 3

---

## 📂 Estrutura de Pastas
src/main/java/com/desafioviaappia/api
┣ 📁 domain → entidades (User, Role, Incident, Comment)
┣ 📁 repositories → interfaces do JPA
┣ 📁 services → regras de negócio
┣ 📁 controllers → endpoints REST
┣ 📁 security → JWT, filtros e configs
┗ 📁 config → OpenAPI, etc

---

## 🛠️ Como Rodar o Projeto

### 1. Clonar repositório
```bash
git clone https://github.com/SEU-USUARIO/via-appia-api.git
cd via-appia-api

---
2. Configurar o application.properties

spring.datasource.url=jdbc:postgresql://localhost:5432/viaappia
spring.datasource.username=postgres
spring.datasource.password=postgres

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true

spring.flyway.enabled=true
spring.flyway.locations=classpath:db/migration

3. Criar Banco de Dados
CREATE DATABASE viaappia;

4. Rodar a aplicação
mvn spring-boot:run

🔑 Credenciais de Teste (Seeds)

Quando rodar a aplicação, o Flyway cria usuários default:

Admin

email: admin@viaappia.com

senha: admin123

Usuário comum

email: user@viaappia.com

senha: user123

📡 Endpoints Principais
Autenticação

POST /auth/login → gera token JWT

{
  "email": "admin@viaappia.com",
  "password": "admin123"
}

Incidentes

GET /incidents → lista incidentes

POST /incidents → cria novo incidente (necessita JWT)

Comentários

GET /comments/{incidentId} → lista comentários de um incidente

POST /comments → cria comentário (necessita JWT)

Estatísticas

GET /stats/incidents → estatísticas de incidentes

📖 Swagger

A documentação da API está disponível em:
👉 http://localhost:8080/swagger-ui.html

🚀 Próximos Passos

 Implementar o Front-end (React + Vite + Tailwind)

 Deploy em Render / Railway

 Adicionar cache com Caffeine

👨‍💻 Autor

Carlos Eduardo Brandão Rodrigues
📍 Brasília - DF
🔗 LinkedIn


---

Quer que eu já monte também a parte do **“How to test no Postman”** com os requests prontos (login, incident, comment) pra colar no README como *coleção de exemplos*?