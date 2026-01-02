# Mini Task Management API

A simple REST API built with Java and Spring Boot.  
This project simulates a small real-world backend with proper structure, version control, and CRUD operations for managing tasks.

## Tech Stack

- Java (17+)
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 (in-memory database)
- Maven
- GitHub for version control

---

## Task Model

Each task contains:

- `id`
- `title` (required)
- `description` (optional)
- `status` → `TODO | IN_PROGRESS | DONE`
- `createdAt`

---

## API Endpoints

### Create a task
`POST /tasks`

Request body:
```json
{
  "title": "Write documentation",
  "description": "Create project README",
  "status": "TODO"
}