# 📚 Book Review Similarity App

Welcome to the **Book Review Similarity App**!  
This is a Java web application built with **Spring Boot** and **Thymeleaf** that allows you to explore a list of books and view other books that are similar based on the content of their reviews.

---

## 🧠 What Does This App Do?

- Loads a list of books from a file (`books.json`)
- Displays those books in a dropdown menu on a webpage
- When you select a book:
  - It shows you the full review
  - It calculates and displays the **top 3 most similar books** using **cosine similarity** based on their review content

---

## 🧰 Technologies Used

- Java 17+
- Spring Boot
- Thymeleaf (HTML templating)
- Maven (build tool)
- JSON (for book data)

---

## 🚀 How to Run It

### ✅ Requirements:
- Java 17+
- Maven

### ✅ Steps:

```bash
# 1. Move into the project directory
cd book-review-app

# 2. Start the app
mvn spring-boot:run
```

Then open your browser and go to:

```
http://localhost:8080
```

---

## 🧮 How Similarity Works

Each book has a small numeric **vector (embedding)** that represents the meaning of its review.

When a book is selected, the app:
- Calculates the **cosine similarity** between that book’s embedding and every other book’s embedding
- Sorts the results by similarity score
- Displays the top 3 most similar books

This simulates how AI models can group similar ideas by meaning!

---

## 🧱 Project Structure

```
book-review-app/
├── pom.xml                          # Project config + dependencies
├── src/main/java/
│   └── edu/canisius/csc/lsp/
│       ├── BookApp.java             # Main Spring Boot app entry point
│       ├── model/
│       │   └── Book.java            # The Book object model
│       ├── storage/
│       │   └── BookRepository.java  # Loads book data from books.json
│       ├── service/
│       │   └── SimilarityService.java # Calculates cosine similarity
│       └── controller/
│           └── BookController.java  # Handles web requests
├── src/main/resources/
│   ├── books.json                   # Book review data
│   └── templates/
│       └── index.html               # HTML template (Thymeleaf)
```

---

## 🔁 Sequence Diagram: What Happens When You Select a Book?

```
+--------+          +-------------+         +----------------+        +-------------------+
| Browser|          |BookController|         |BookRepository  |        |SimilarityService  |
+--------+          +-------------+         +----------------+        +-------------------+
    |                      |                         |                         |
    | --- GET "/" + id --->|                         |                         |
    |                      |                         |                         |
    |                      |--- getAll() ----------->|                         |
    |                      |<-- list of books -------|                         |
    |                      |                         |                         |
    |                      |--- getById(id)--------->|                         |
    |                      |<-- selected book -------|                         |
    |                      |                         |                         |
    |                      |--- findTop3Similar() ---------------------------->|
    |                      |<-- list of top 3 books ---------------------------|
    |                      |                         |                         |
    |                      |-- render Thymeleaf template: index.html ---------|
    |                      |   (model = books, selected, similar)             |
    |<----- HTML response -|                                                  
```

---

## 💡 What Can You Modify?

Here are some ideas:
- Add new books to `books.json`
- Show similarity scores in the UI
- Style the page with CSS
- Add a search box or sort filter
- Replace books with **consumer complaints**
- Add a REST API layer (extra credit!)

---

## ❓ Troubleshooting

### Getting a `javax.annotation` error?
You're likely on Java 17+. This project already uses:
```xml
<dependency>
  <groupId>jakarta.annotation</groupId>
  <artifactId>jakarta.annotation-api</artifactId>
  <version>2.1.1</version>
</dependency>
```

Make sure your `BookRepository.java` uses:
```java
import jakarta.annotation.PostConstruct;
```

---


