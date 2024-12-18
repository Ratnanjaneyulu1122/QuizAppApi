# QuizAppApi
Spring Boot Quiz Application

--------------------------------

# Quiz API Application

This is a **Quiz API's Application** built using **Spring Boot**, which allows users to participate in quizzes, submit answers, and view results. The application supports features like question management, user statistics tracking, and quiz sessions.

--------------------------------
## Basic Features
--------------------------------
- **Start a Quiz**: Users can initiate a quiz session.
- **Fetch Random Questions**: Retrieve a random question for the quiz.
- **Submit Answers**: Validate submitted answers and update user statistics.
- **View Results**: Get a summary of the user's quiz performance.
--------------------------------
## Technologies Used

- **Spring Boot**: For creating the RESTful API.
- **H2 Database**: An in-memory relational database for quick testing and development.
- **Spring Data JPA**: For database operations.
- **Lombok**: To reduce boilerplate code.
- **Maven**: For project management and dependencies.

## Database Configuration

This project uses **H2 Database** as an in-memory database for development purposes. By default, the H2 database runs in memory,
so data will be lost once the application stops.

### Application Properties

The default configuration for H2 Database can be found in `src/main/resources/application.properties`:

```properties
# H2 Database Configuration
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
```
---------------------------------------------
## Prerequisites
--------------------------------
1. **Java 17 or later** installed on your system.
2. **Maven** for dependency management.
3. **MySQL** database instance running.
4. **Postman** or any REST API testing tool.
--------------------------------
## Setup Instructions
--------------------------------
### 1. Clone the Repository
--------
```bash
     git clone https://github.com/Ratnanjaneyulu1122/QuizAppApi.git
```
--------------------------------
### 2. Configure the Database
- Open the `application.properties` file located in `src/main/resources/`.
- Update the following properties with your database details ex mysql daatbase details provided below , if you are using any other database like mysql,oracle,db2,etc based on the database database url change so if any one want to change so database url need to change:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/quizdb
spring.datasource.username=your-db-username
spring.datasource.password=your-db-password
spring.jpa.hibernate.ddl-auto=update
```
-------------------------
### 3. Build and Run the Application
-------------------------
- Use Maven to build the application:
```bash
mvn clean install
```
- Run the application:
```bash
mvn spring-boot:run
```
------------------------
### 4. Test APIs
-----------------------
- Use Postman or any API testing tool to interact with the endpoints.
- Example endpoints:
  - **Start a Quiz**: `POST /quiz/start`
  - **Get Random Question**: `GET /quiz/question`
  - **Submit Answer**: `POST /quiz/submit`
  - **View Results**: `GET /quiz/results`
    --------------------------------
- Example 1 endpoints :
  - **Start a Quiz**: `POST /quiz/start?userId=User22`
  - **Get Random Question**: `GET /quiz/question?userId=User22`
  - **Submit Answer**: `POST /quiz/submit?userId=User22&questionId=1&answer=A`
  - **View Results**: `GET /quiz/results?userId=User22`
    
---
-----------
## API Endpoints
-----------
### 1. **Start Quiz**
```http
POST /quiz/start
```
- **Description**: Initializes a new quiz session for a user.
- **Response**: `200 OK`
  ```json
  {
    "id": 1,
    "userId": "User2",
    "answeredQuestions": []
  }
  ```
 -------------------
### 2. **Get Random Question**
```http
GET /quiz/question
```
- **Description**: Fetches a random quiz question.
- **Response**: `200 OK`
  ```json
  {
    "id": 3,
    "questionText": "What is the color of the sky?",
    "optionA": "Green",
    "optionB": "Blue",
    "optionC": "Red",
    "optionD": "Yellow",
    "correctAnswer": "B" ---   for this correcetAnswer is  only testing purpose sending,
   }
  ```
  ---------------------
### 3. **Submit Answer**
```http
POST /quiz/submit
```
- **Parameters**:
  - `questionId` (Long): ID of the question.
  - `answer` (String): User's selected answer.
- **Response**: `200 OK`
  ```json
  Answer submitted
  ```
  --------------------------------
### 4. **View Results**
```http
GET /quiz/results
```
- **Description**: Displays the user's performance in the quiz session.
- **Response**: `200 OK`
  ```json
  {
    "id": 1,
    "userId": "User2",
    "totalAnswered": 5,
    "correctAnswers": 3,
    "incorrectAnswers": 2
  }
  ```
--------------------------
## Database Schema
--------------------------

### Question Table
| Column         | Type    | Description              |
|----------------|---------|--------------------------|
| id             | Long    | Primary Key              |
| question_text  | String  | The quiz question text   |
| correct_answer | String  | Correct answer option    |

### QuizSession Table
| Column         | Type    | Description                  |
|----------------|---------|------------------------------|
| id             | Long    | Primary Key                  |
| user_id        | Long    | Foreign Key to User          |
| question_id    | Long    | Foreign Key to Question      |
| answered       | Boolean | Whether the question is answered correctly |

### UserStats Table
| Column          | Type    | Description                |
|-----------------|---------|----------------------------|
| id              | Long    | Primary Key                |
| user_id         | Long    | Foreign Key to User        |
| total_answered  | Integer | Total questions answered   |
| correct_answers | Integer | Total correct answers      |
| incorrect_answers | Integer | Total incorrect answers  |


------------------
## Troubleshooting
------------------

### Common Errors and Solutions

1. **Error**: `User stats not found for user ID`.
   - **Cause**: `UserStats` was not initialized.
   - **Solution**: Start the quiz before submitting answers.

2. **Error**: `Question not found with ID`.
   - **Cause**: Invalid question ID was provided.
   - **Solution**: Use the correct question ID from the `/quiz/question` response.

 ------------------

## Future Enhancements

1. **User Authentication**: Add JWT-based authentication.
2. **Question Categories**: Allow users to select categories for questions.
3. **Leaderboard**: Introduce a leaderboard to display top performers.


