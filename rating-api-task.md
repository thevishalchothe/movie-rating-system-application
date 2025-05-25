## Task: Building a Movie Rating System API


### Objective: Build APIs using **Spring Boot** that allow the users to:

* Rate movies (with a numeric score).
* View movie ratings and average ratings.
* List all ratings for a specific movie.
* Perform basic validation on ratings.
* Recommended to use **Spring Security's built-in permissions system** to ensure that only authenticated users can add or update ratings. (Optional)

---

### Requirements:

#### 1. Models:

* **Movie model:**

  * `title`: The title of the movie (String).
  * `description`: A short description of the movie (Text/String).
  * `releaseDate`: The release date of the movie (LocalDate).
  * `createdAt`: Timestamp when the movie is added (auto generated).

* **Rating model:**

  * `user`: Relationship with User table if using built-in user model else this should be an Integer field representing user id but not related to another model.
  * `rating`: A numeric rating between 1 and 10 (Integer).
  * `createdAt`: Timestamp when the rating was created (auto generated).

* You need to define appropriate relationships between Movie and Rating models.

---

#### 2. API Endpoints:

* **Movies:**

  * `GET /api/movies/` – List all movies (optional - pagination).
  * `POST /api/movies/` – Create a new movie (Optional: authenticated user, admin role).
  * `GET /api/movies/{id}/` – Retrieve details of a movie, including its average rating and the list of ratings.

* **Ratings:**

  * `POST /api/movies/{id}/rate/` – Rate a movie (Optional: authenticated user only). Ensure users can rate a movie only once. If the user has already rated the movie, allow them to update their rating.
  * `GET /api/movies/{id}/ratings/` – List all ratings for a specific movie.
  * Ensure ratings are validated to be between 1 and 10, and appropriate error messages are returned for invalid ratings.

---

### 3. Features:

* **Movie rating validation:** Ensure ratings are between 1 and 10. Return an error if an invalid rating is provided.
* **Database:** Use SQL to persist the movie and rating data. Implement any necessary database constraints like foreign keys, unique constraints, and data validation rules.
* **Average Rating:** When retrieving a movie's details, calculate and return the average rating for that movie.
* **Permissions:**

  * Use Spring Security's built-in permissions system.
  * Only authenticated users should be able to rate or update ratings.
  * Admin users should be able to create new movies.

---

### 4. Deliverables:

1. **Code Files:** A zip or GitHub link to your implementation, including Spring Boot models, controllers, services, repositories, DTOs, and configuration files.
2. **Documentation:** Instructions on how to run the project, including setting up a virtual environment (Maven or Gradle), installing dependencies, etc., will be appreciated.

