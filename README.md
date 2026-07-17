# 🚀 IntelliTutor - AI Powered Learning Assistant

IntelliTutor is an AI-powered learning platform built using **Spring Boot**, **Spring AI**, **Google Gemini**, and **Retrieval-Augmented Generation (RAG)**. It allows users to upload technical PDFs, ask questions, generate interview quizzes, evaluate answers, and track their learning progress.

---

# 📌 Features

## 🔐 Authentication
- User Registration
- User Login
- JWT Authentication
- BCrypt Password Encryption
- Spring Security

---

## 📚 PDF Based Learning (RAG)

- Read PDF documents
- Automatic document chunking
- Generate embeddings
- Store embeddings in Vector Store
- Semantic Search
- Context-aware AI responses

---

## 💬 Ask AI

Users can ask any question related to the uploaded PDF.

Example:

```
Explain Apache Kafka Consumer Groups.
```

The AI retrieves the most relevant document chunks and answers using RAG.

---

## 📝 AI Quiz Generator

Generate interview-style multiple choice questions from any topic.

### Features

- Dynamic number of questions
- Difficulty Level
- Unique Question IDs
- Four Options (A/B/C/D)
- JSON Response
- Topic-wise Quiz

Example Response

```json
{
  "topic":"Apache Kafka",
  "difficulty":"Medium",
  "questions":[]
}
```

---

## ✅ Answer Evaluation

Evaluate user answers using AI.

Response includes

- Correct Answers
- Wrong Answers
- Explanation
- Total Score
- Percentage
- Grade
- Strengths
- Weak Areas
- Recommended Next Topic

Example

```json
{
  "totalScore":4,
  "percentage":80,
  "grade":"Excellent",
  "strength":"Strong Kafka Fundamentals",
  "weakPoint":"Consumer Groups",
  "nextTopic":"Kafka Streams"
}
```

---

## 📈 Daily Learning Summary

Generate today's complete learning summary using Chat Memory.

Summary contains

- Topics Learned
- Quiz Topics
- Strengths
- Weaknesses
- Recommended Topics
- AI Feedback

---


# 🏗️ Architecture

```
                        User
                          │
                          ▼
                 Spring Boot REST APIs
                          │
              Spring Security + JWT
                          │
                          ▼
                    Chat Memory
                          │
                          ▼
                Spring AI ChatClient
                          │
          ┌───────────────┴───────────────┐
          │                               │
          ▼                               ▼
 Google Gemini Chat Model         Embedding Model
          │                               │
          └───────────────┬───────────────┘
                          ▼
                  Simple Vector Store
                          │
                          ▼
                  Relevant PDF Chunks
                          │
                          ▼
                     AI Generated Response
                          │
                          ▼
                    MySQL Database
```

---

# ⚙️ Tech Stack

## Backend

- Java 21
- Spring Boot 3.5
- Spring Security
- Spring Data JPA
- Hibernate
- Maven

## AI

- Spring AI
- Google Gemini
- Retrieval-Augmented Generation (RAG)
- Embedding Model
- Chat Memory
- Simple Vector Store

## Database

- MySQL

## Authentication

- JWT
- BCrypt

## Tools

- IntelliJ IDEA
- Postman
- Git
- GitHub

---

# 📁 Project Structure

```
src
│
├── configs
│
├── controllers
│
├── entities
│
├── records
│
├── repositories
│
├── security
│
├── services
│
└── IntelliTutorApplication
```

---

# 🌐 REST APIs

| Method | Endpoint | Description |
|----------|------------------------------|----------------------------|
| POST | `/api/auth/register` | Register User |
| POST | `/api/auth/login` | Login User |
| POST | `/api/rag/ask` | Ask AI |
| POST | `/api/rag/quiz` | Generate Quiz |
| POST | `/api/rag/check-answer` | Evaluate Quiz |
| GET | `/api/rag/summary` | Learning Summary |


---

# 🗄️ Database Design

## User

```
User
----
id
name
email
password
role
```

---

## Quiz Attempt

```
QuizAttempt
-----------
id
topic
difficulty
totalQuestions
totalScore
percentage
grade
strength
weakPoint
nextTopic
attemptedAt
user_id
```

Relationship

```
User (1)
   │
   │
   ▼
QuizAttempt (Many)
```

---

# 🔄 Application Flow

```
User Login
      │
      ▼
Receive JWT Token
      │
      ▼
Upload PDF
      │
      ▼
Generate Embeddings
      │
      ▼
Store in Vector Store
      │
      ▼
Ask Questions / Generate Quiz
      │
      ▼
Evaluate Answers
      │
      ▼
Save Quiz Attempt
      │
      ▼
Generate Learning Summary
```

---

# 🚀 Future Improvements

- PostgreSQL + pgvector
- Pinecone Vector Database
- Redis Chat Memory
- Multi PDF Support
- PDF Upload API
- Docker
- AWS Deployment
- Kubernetes
- Microservices Architecture
- Leaderboard
- AI Learning Roadmap
- Resume Based Quiz Generation
- Voice Assistant

---

# 💡 Skills Demonstrated

- Core Java
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Data JPA
- Hibernate
- REST APIs
- Retrieval-Augmented Generation (RAG)
- Spring AI
- Google Gemini
- Prompt Engineering
- Embedding Models
- Vector Stores
- Chat Memory
- MySQL
- Maven
- Git
- GitHub

---

# 👨‍💻 Author

**Karthik**

Java Backend Developer | Spring Boot | Spring AI | RAG | Microservices | Distributed Systems

---

## ⭐ If you like this project, don't forget to give it a Star!
