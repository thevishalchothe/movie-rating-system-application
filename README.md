#  movie-rating-system-application  🎬🌐📊

A Spring Boot-based RESTful API that allows users to rate movies, view average ratings, and manage movie and rating data with proper validation and authentication.

---

## Project Objective 🎯

Build a RESTful Movie Rating System using **Spring Boot** and **Rest-API** that supports the following:

- Users can **rate** movies with a score between 1 and 10. ⭐ 
- View **average movie ratings** and individual ratings. 📊 
- List all ratings for a specific movie. 📃 
- Enforce validation (rating value between 1 to 10). ✅ 
- Use **Spring Security** to ensure: 🔐 
  - Only authenticated users can rate/update ratings.
  - Only admin users can create movies.
 
---

## Permissions 🔐 

- Only authenticated users can rate or update ratings
- Only ADMIN users can create movies

---

## API Endpoints 🌐 

### Movies 🎬 

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET`  | `/api/movies` | Get all movies (pagination optional) |
| `POST` | `/api/movies` | Create a new movie (admin only) |
| `GET`  | `/api/movies/{id}` | Get movie details with average rating and all ratings |

### Ratings ⭐ 

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/movies/{id}/rate` | Add or update a rating (auth user only) |
| `GET`  | `/api/movies/{id}/ratings` | Get all ratings for a movie |

---

## Validations 🧪

- Ratings must be between 1 and 10
- One user can rate a movie only once (update allowed)
- Error responses returned with proper status codes

---

## Setup Instructions of Application ⚙️ 

### 1. Clone the Repo
```bash
git clone https://github.com/thevishalchothe/movie-rating-system-application.git
cd movie-rating-system-application
```

### 2. Setup MySQL Database
```
CREATE DATABASE movie_rating_db;
```
### 3. Configure application.properties

```
# Application name
spring.application.name=movie-rating-system-application

# Add MySQL Configuration and JPA / Hibernate settings

# (Optional)
spring.security.user.name=admin
spring.security.user.password=admin
```
### 4. Run the App
```
mvn spring-boot:run
```
---

## Movie Rating System API - Testing Endpoints & Sample Users 🎬 

---

### 👤 Sample Users for Authentication

| Username | Password  | Role  |
|----------|-----------|-------|
| admin    | adminpass | ADMIN |
| user     | userpass  | USER  |

---

## Authentication 🔐 

- Use **Basic Auth** with username and password for all secured endpoints.
- `admin` has role ADMIN and can create movies.
- `user` has role USER and can rate movies.

---

## 🎬 Movie Rating System API — Testing with Postman

---

## 👤 Sample Users (for Basic Auth)

| Username | Password  | Role  |
|----------|-----------|-------|
| admin    | adminpass | ADMIN |
| user     | userpass  | USER  |

---


## How to Test Endpoints in Postman

### 1. Set Authentication (Basic Auth) for Requests That Require It

- Go to **Authorization** tab in Postman
- Select **Basic Auth**
- Enter **Username** and **Password** from above table

---

## Endpoints & Postman Setup 🔥 

---

### Get All Movies (Public, No Auth Required)

- Method: **GET**
- URL: `http://localhost:8080/api/movies`

No authorization needed.

---

### Create Movie (Admin Only)

- Method: **POST**
- URL: `http://localhost:8080/api/movies`
- Authorization: **Basic Auth** (admin/adminpass)
- Headers: `Content-Type: application/json`
- Body (raw JSON):
  
```json
{
  "title": "Inception",
  "description": "A mind-bending thriller",
  "releaseDate": "2010-07-16"
}
```

### Get Movie Details by ID (Public)

  -  Method: GET
    -  URL: http://localhost:8080/api/movies/{id}
    -  Replace {id} with movie ID (e.g., 1)

No authorization needed.

### Add or Update Rating (Authenticated User)

-  Method: POST
  -  URL: http://localhost:8080/api/movies/{movieId}/ratings
  -  Replace {movieId} with actual movie ID (e.g., 1)

Authorization: Basic Auth (user/userpass)
  Headers: Content-Type: application/json

Body (raw JSON):
 ```     
{
  "userId": 2,
  "score": 8
}
```
### Get All Ratings for a Movie (Public)

-  Method: GET
  -  URL: http://localhost:8080/api/movies/{movieId}/ratings/get-all
  -  Replace {movieId} with actual movie ID (e.g., 1)

No authorization needed.

---
## Logging ⚠️ 

Loggers integrated via SLF4J in both Controllers and Services

Example:
```
private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

logger.info("Fetching all movies");
logger.error("Movie not found with ID: {}", movieId);

```

---

⚠️ Important Notes:

  -  Only admins can create movies.
  -  Only authenticated users can rate or update ratings.
  -  Ratings must be between 1 and 10, else you'll get validation errors.
  -  userId in rating request can be your logged-in user ID or any integer if user management is not connected.
