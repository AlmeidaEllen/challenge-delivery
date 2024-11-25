<h1 align="center" style="font-weight: bold;">Delivery App 💻</h1>

<p align="center">
    <b>A aplicação permite cadastrar, atualizar, consultar e deletar entregas.</b>
</p>

<h2 id="technologies"> Tecnologias </h2>

- Java 17
- Spring Boot
- Spring Data JPA
- Spring MVC
- MySQL 
- H2 Database (para testes)

<h2 id="started">Práticas adotadas</h2>

- API REST
- Consultas com Spring Data JPA
- Injeção de Dependências 
- Tratamento de respostas de erro
- Geração automática do Swagger (OpenAPI)


<h2 id="started">Como executar</h2>
É necessário clonar o repositório do projeto

<h3>Clonar</h3>

```bash
git clone your-project-url-in-github
```

<h3>Executar a aplicação</h3>

```bash
./mvnw spring-boot:run (Linux/Mac) ou mvnw.cmd spring-boot:run (Windows)
```






<h2 id="started">API Endpoints</h2>

- A API poderá ser acessada em http://localhost:8080

- Os endpoints são visualizados através do Swagger em http://localhost:8080/swagger-ui.html

- Para verificar a saúde da aplicação (Health Checks) em http://localhost:8080/actuator/health
