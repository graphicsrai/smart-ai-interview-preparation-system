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


## Upcoming Features


### Day 5
- ATS Score Calculator

### Day 6
- AI Interview Question Generator

### Day 7
- Dashboard & Analytics

---

## Author

Mohit Kumar

MCA Final Year Project