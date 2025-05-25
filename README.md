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


## 📁 Project Structure
```
movie-rating-system-application/
├── src/
│ └── main/
│ ├── java/
│ │ └── com/killerexpertise/movies/rating/example/
│ │ ├── controller/
│ │ │ ├── MovieController.java
│ │ │ └── RatingController.java
│ │ ├── dto/
│ │ │ └── RatingRequest.java
│ │ ├── exception/
│ │ │ ├── GlobalExceptionHandler.java
│ │ │ └── MovieNotFoundException.java
│ │ ├── model/
│ │ │ ├── Movie.java
│ │ │ └── Rating.java
│ │ ├── repository/
│ │ │ ├── MovieRepository.java
│ │ │ └── RatingRepository.java
│ │ ├── responce/
│ │ │ └── ApiResponse.java
│ │ ├── securityConfig/
│ │ │ └── SecurityConfig.java
│ │ ├── service/
│ │ │ ├── MovieService.java
│ │ │ ├── RatingService.java
│ │ │ └── impl/
│ │ │ ├── MovieServiceImpl.java
│ │ │ └── RatingServiceImpl.java
│ │ └── MovieRatingSystemApplication.java
│ └── resources/
│ ├── application.properties
│ └── static/
├── .gitignore
├── README.md
└── pom.xml
```

---


---

## 🔐 Permissions

- ✅ Only authenticated users can rate or update ratings
- 🛠 Only ADMIN users can create movies

---

## 🌐 API Endpoints

### 🎬 Movies

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET`  | `/api/movies` | Get all movies (pagination optional) |
| `POST` | `/api/movies` | Create a new movie (admin only) |
| `GET`  | `/api/movies/{id}` | Get movie details with average rating and all ratings |

### ⭐ Ratings

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/movies/{id}/rate` | Add or update a rating (auth user only) |
| `GET`  | `/api/movies/{id}/ratings` | Get all ratings for a movie |

---

## 🧪 Validations

- Ratings must be between 1 and 10
- One user can rate a movie only once (update allowed)
- Error responses returned with proper status codes

---

## ⚙️ Setup Instructions

### 1️⃣ Clone the Repo
```bash
git clone https://github.com/thevishalchothe/movie-rating-system-application.git
cd movie-rating-system-application
```

### 2️⃣ Setup MySQL Database
```
CREATE DATABASE movie_rating_db;
```
### 3️⃣ Configure application.properties

```
# Application name
spring.application.name=movie-rating-system-application

# Add MySQL Configuration and JPA / Hibernate settings

# (Optional)
spring.security.user.name=admin
spring.security.user.password=admin
```
### 4️⃣ Run the App
```
mvn spring-boot:run
```
