# Project Management System (PMS)

A full‑stack OTC Project Management System for end‑to‑end project lifecycle management: project initiation, task/plan tracking, budgeting, team collaboration, progress dashboards, and performance insights.

- Backend: Spring Boot 3.3, MyBatis‑Plus, MySQL 8.0, Redis, Spring Security, JWT
- Frontend: Vue 3 + Vite, Element Plus, ECharts, TinyMCE

## Key Features

- Project lifecycle management (create, edit, list projects) with categories, stages, regions, departments, and companies
- Plan/Task management with priority, dependencies, due dates, completion tracking
- Budget management with multi‑currency support and settlement status
- Member/Role management with per‑project roles and JSON‑encoded permissions
- Authentication and authorization using Spring Security + JWT; captcha provider included
- Redis‑backed caching/sessions and utility helpers
- Rich dashboards and charts built with ECharts
- Rich‑text editing for project documentation via TinyMCE

## Monorepo Layout

- `pms1/`: Spring Boot backend service
- `front/`: Vue 3 frontend application
- `pms.sql`: MySQL schema and seed data

## Tech Stack

- **Java 17**, **Spring Boot 3.3.1**
- **MyBatis‑Plus 3.5.7**
- **MySQL 8.0.x**
- **Redis 6+**
- **Spring Security**, **JWT (jjwt 0.9.0)**
- **Vue 3**, **Vite 3**, **Element Plus**, **ECharts 5**, **TinyMCE 6**

## Getting Started

### Prerequisites

- JDK 17+
- Maven 3.8+ (or use the included Maven Wrapper `./mvnw`)
- Node.js 16+ and npm
- MySQL 8.0+
- Redis 6+

### 1) Database Setup

1. Create a database named `pms` in MySQL 8.0.
2. Import the schema and seed data:
   ```sql
   -- in MySQL client
   SOURCE /absolute/path/to/pms.sql;
   ```
   The dump includes tables: `project_info`, `plans`, `budgets`, `project_members`, `users`, and reference tables.

### 2) Backend Configuration

Edit `pms1/src/main/resources/application.yml` if needed:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/pms
    username: root
    password: root
redis:
  host: 127.0.0.1
  port: 6379
```

### 3) Run Backend

From the `pms1/` directory:

```bash
./mvnw spring-boot:run
```

The service listens on `http://127.0.0.1:8080` by default.

### 4) Frontend Configuration

The API base URL is configured in `front/src/config.js`:

```js
export default { baseURL: "http://127.0.0.1:8080" };
```

Adjust if your backend host/port differs.

### 5) Run Frontend

From the `front/` directory:

```bash
npm install
npm run dev
```

The dev server runs on Vite’s default port (usually `http://127.0.0.1:5173`).

## Core Modules (Backend)

- `controller/`: REST controllers (`ProjectListController`, `PlanController`, `BudgetController`, `UserController`, etc.)
- `service/` and `service/impl/`: Business services (project list, project info, members, plans, budgets, login/register, user info)
- `mapper/` + `resources/mapper/`: MyBatis‑Plus mappers and XMLs
- `entity/`: JPA‑style entities for MyBatis‑Plus
- `security/`: Captcha auth provider and token classes
- `config/`: Security, Redis, MyBatis‑Plus, CORS, static resource, and web configs
- `filter/`: `JwtAuthenticationTokenFilter` for request authentication
- `util/`: `JwtUtil`, `RedisCache`, `CaptchaUtil`, etc.

## Security & Auth

- Login issues a JWT; subsequent requests require the token via headers (e.g., `Authorization: Bearer <token>`)
- Spring Security configuration and a JWT authentication filter protect APIs
- Optional captcha authentication provider is included

## API Documentation

- The backend integrates `smart-doc` Maven plugin. You can generate API docs (HTML) during build:

```bash
cd pms1
./mvnw clean package
```

Docs are emitted under `pms1/target/smart-doc/` when the plugin runs at compile phase.

## Frontend Highlights

- Vue 3 + Vite, Element Plus component library
- ECharts dashboards (project progress, workload, budget usage)
- Pinia stores with persistence
- TinyMCE editor for project documents
- Router guards in `router/permission.js` and API client with Axios

## Environment Variables

If you prefer using environment variables instead of `application.yml`, set:

- `SPRING_DATASOURCE_URL`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`
- `SPRING_DATA_REDIS_HOST`
- `SPRING_DATA_REDIS_PORT`

## Testing

Run unit/integration tests:

```bash
cd pms1
./mvnw test
```

Includes tests for password encoding, Redis connectivity, and domain modules.

## Production Build

- Backend: `./mvnw clean package` produces a runnable jar under `pms1/target/`
- Frontend: `npm run build` emits static assets under `front/dist/`
- Serve the frontend via a static server or a reverse proxy (e.g., NGINX) that forwards `/api` to the backend

## Project Structure

```
HFUT-Java-Training/
├─ pms1/                   # Spring Boot backend
│  ├─ src/main/java/com/example/pms1/
│  ├─ src/main/resources/
│  └─ pom.xml
├─ front/                  # Vue 3 frontend (Vite)
│  ├─ src/
│  ├─ index.html
│  └─ package.json
└─ pms.sql                 # MySQL schema and seed data
```

## Notes

- Default demo credentials are not included in source. Use the registration endpoint or insert a user into the `users` table; passwords should be stored as BCrypt hashes.
- Ensure Redis is running; JWT and cache utilities depend on it.
- The SQL seed uses sample Chinese labels and sample data for categories, regions, departments, and projects.

## License

This project is for educational purposes (university Java training). Choose an appropriate license before open‑sourcing.
