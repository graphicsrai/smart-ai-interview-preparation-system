# Smart AI-Based Interview Preparation and Resume Evaluation System

## Project Description

A full-stack AI-powered web application that helps students and job seekers improve their resumes and prepare for technical interviews.

The system allows users to upload resumes, analyze skills, receive AI-generated feedback, practice mock interviews, and track their progress through a dashboard.

---

## Technology Stack

### Backend
- Java 17
- Spring Boot 3
- Spring Security
- Spring Data JPA
- Maven

### Database
- MySQL 8

### Tools
- IntelliJ IDEA
- Postman
- Git & GitHub

### Upcoming AI Integration
- Apache PDFBox
- Google Gemini API
- ATS Score Analysis
- Skill Extraction Engine

---

## Features Completed

### Day 1 - Authentication Module

#### User Registration
- Register new users
- Email uniqueness validation
- Store user details in MySQL

#### User Login
- Login using email and password
- BCrypt password verification
- Proper exception handling

#### Security
- Password encryption using BCryptPasswordEncoder
- Spring Security configuration

---

### Day 2 - Resume Upload Module

#### Resume Upload
- Upload PDF resumes
- Multipart file handling
- File validation support

#### File Storage
- Store uploaded resumes on server
- Dedicated uploads directory

#### Database Integration
- Resume metadata stored in MySQL
- Automatic table creation using JPA/Hibernate

#### Resume Management
- Resume entity
- Resume repository
- Resume upload service
- Resume upload API

---

## API Endpoints

### Authentication APIs

#### Register User

POST /api/auth/register

Request:

```json
{
  "name": "Mohit Kumar",
  "email": "mohit@test.com",
  "password": "123456"
}
```

#### Login User

POST /api/auth/login

Request:

```json
{
  "email": "mohit@test.com",
  "password": "123456"
}
```

---

### Resume APIs

#### Upload Resume

POST /api/resume/upload

Form Data:

```text
file = resume.pdf
```

Response:

```json
{
  "id": 1,
  "fileName": "resume.pdf",
  "status": "Uploaded Successfully"
}
```

---

## Project Structure

src/main/java/interview_prep_system

- controller
- service
- repository
- entity
- dto
- config

---



### Day 3
- PDF Text Extraction & Skill Extraction Module


### Day 4
- ATS Analysis Module

- ATS Score Calculation
- ATS Rating (Excellent/Good/Average)
- Missing Skills Detection
- Resume Improvement Suggestions


### Day 5 - Smart Resume Recommendation Module

#### Resume Recommendation Engine
- Generate personalized resume feedback
- Analyze ATS score and detected skills
- Identify missing technical skills
- Provide actionable improvement suggestions

#### Recommendation Features
- Docker skill recommendations
- Cloud technology recommendations (AWS/Azure)
- Build tool recommendations (Maven/Gradle)
- Achievement-based resume improvements
- GitHub portfolio recommendations

#### Recommendation API

GET /api/resume/{id}/recommendations

Sample Response:

```json
{
  "overallFeedback": "Strong resume with excellent technical profile and industry experience.",
  "recommendations": [
    "Add Docker experience to demonstrate containerization skills.",
    "Include AWS or Azure projects to strengthen cloud expertise.",
    "Mention Maven or Gradle build tools in technical skills.",
    "Include measurable achievements such as performance improvements or delivery metrics.",
    "Provide GitHub project links to strengthen your technical profile."
  ]
}
```

### Day 6 - Interview Question Generator

#### Features
- Skill-based interview question generation
- Random question selection
- Easy, Medium and Hard difficulty levels
- Multiple questions per skill
- Dynamic interview preparation workflow

#### Supported Skills
- Java
- Spring Boot
- React.js
- MySQL
- Microservices

#### Interview Questions API

GET /api/resume/{id}/interview-questions

Sample Response:

```json
{
  "totalQuestions": 15,
  "skillsCovered": 5,
  "questions": [
    {
      "skill": "Java",
      "difficulty": "Medium",
      "question": "What is the difference between HashMap and ConcurrentHashMap?"
    }
  ]
}
```

### Day 7
- Dashboard & Analytics

---

## Author

Mohit Kumar

MCA Final Year Project