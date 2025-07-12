# Clone de Spotify (Fullstack) com Spring Boot 3.3.13, Angular 19, Bootstrap 5, PostgreSQL, Auth0 (2025) (Backend)

## Tecnologias Usadas
- **Spring Boot 3.3.13**
- **Angular 19**
- **Bootstrap 5**
- **PostgreSQL**
- **Auth0 (OAuth2)**
- **Java 21+**
- **Maven ou Gradle** (para gerenciamento de dependências)
- **JPA / Hibernate** (para ORM)
- **REST APIs**
- **JWT (JSON Web Tokens)** (para autenticação e autorização)
- **Docker** (opcional para containerização)


[Projeto Angular Frontend](https://github.com/erolkss/spotify-clone-front-api)

### Funcionalidades Principais:
- 🔐 Segurança com Auth0 (OAuth2)  
- 🎶➕ Envio de Músicas  
- 🎧 Transmissão de Áudio  
- 🔍 Função de Pesquisa  
- ❤️ Favoritos do Usuário  
- 📱💻 Interface Responsiva com Bootstrap  
- 🅰️ Últimos Recursos do Angular: Signal, Componente Independente, Nova Sintaxe de Fluxo de Controle

## Uso
### Pré-requisitos
- [JDK 21](https://adoptium.net/temurin/releases/)
- IDE (WebStorm 2025.1, Intellij 2024.3)
- [PostgreSQL](https://www.postgresql.org/download/)

### Clonar o repositório
``git clone https://github.com/erolkss/spotify-clone-back-api``

### Configuração arquivo .env 
Crie um arquivo `.env` na raiz do projeto
````
# Database
POSTGRES_DB=
POSTGRES_USERNAME=
POSTGRES_URL=
POSTGRES_PASSWORD=

#AUTH
AUTH0_CLIENT_ID=
AUTH0_CLIENT_SECRET=
````

### Buscar as dependências
``./mvnw install -Dmaven.test.skip=true``

### Executar  
No WebStorm, adicione o arquivo `.env` às variáveis de ambiente e então execute o projeto.
