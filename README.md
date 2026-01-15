# 🔗 URL Shortener Application

A Spring Boot–based URL Shortener application that allows users to shorten long URLs, manage privacy (public/private), set expiration days, and securely access their own URLs. The application supports guest users, registered users, and enforces strict privacy and security rules using Spring Security.
---

## 📌 Features

### 🌐 Public Features
* Access public short URLs
* Automatic expiration for guest-created URLs
* Click count tracking

### 👤 Authenticated User Features

* Register & login & My-URLs
* Create **private or public** short URLs
* Set custom expiration dates
* View only **their own URLs** (My URLs page)
* Delete multiple URLs at once
* Pagination support

### 🔐 Security

* Spring Security authentication
* Private URLs accessible only by their creator
* Invalid URL can not be short (Example: abc123
  )

---

## 🛠️ Tech Stack

| Layer      | Technology              |
| ---------- | ----------------------- |
| Backend    | Spring Boot, Spring MVC |
| Security   | Spring Security         |
| ORM        | Spring Data JPA         |
| Database   | MySQL                   |
| Frontend   | Thymeleaf, Bootstrap    |
| Build Tool | Maven                   |

---

## 📂 Project Structure

```
URL-SHORTENER
│
├── src/main/java/com/UrlShortener
│
│   ├── Controller
│   │   ├── HomeController.java
│   │   └── UserController.java
│
│   ├── Services
│   │   ├── ShortUrlService.java
│   │   ├── UserService.java
│   │   ├── SecurityUserDetailsService.java
│   │   └── UrlExistenceValidator.java
│
│   ├── Repositories
│   │   ├── ShortUrlRepository.java
│   │   └── UserRepository.java
│
│   ├── Entities
│   │   ├── User.java
│   │   └── ShortUrl.java
│
│   ├── DTOs
│   │   ├── CreateShortUrlCmd.java
│   │   ├── CreateShortUrlForm.java
│   │   ├── CreateUserCmd.java
│   │   ├── RegisterUserRequest.java
│   │   ├── ShortUrlDto.java
│   │   ├── UserDTOs.java
│   │   └── PagedResult.java
│
│   ├── Mapper
│   │   └── EntityMapper.java
│
│   ├── Config
│   │   └── WebSecurityConfig.java
│
│   ├── Exceptions
│   │   └── ShortUrlNotFoundException.java
│
│   └── Utils
│       └── SecurityUtils.java
│
├── src/main/resources
│
│   ├── templates
│   │   ├── index.html
│   │   ├── my-urls.html
│   │   ├── register.html
│   │   ├── login.html
│   │   ├── pagination.html
│   │   └── layout.html
│
│   ├── static
│   │   └── (css, js, images if needed)
│
│   └── application.properties
│
├── create_table.sql
│
└── README.md

```

---

## 🗄️ Database Schema

### users

```sql
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'USER',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
```

### short_urls

```sql
CREATE TABLE short_urls (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    short_key VARCHAR(10) NOT NULL UNIQUE,
    original_url TEXT NOT NULL,
    is_private BOOLEAN NOT NULL DEFAULT FALSE,
    expires_at TIMESTAMP NULL,
    created_by BIGINT,
    click_count BIGINT NOT NULL DEFAULT 0,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_short_urls_users FOREIGN KEY (created_by) REFERENCES users(id)
);
```
---

## 📑 Pagination Logic

* Page size is configurable
* Buttons (First, Previous, Next, Last) auto-disable when not applicable

---

## 🔐 Security Rules (Overview)

* `/register`, `/login`, `/` → Public
* `/my-urls`, `/delete-urls` → Authenticated users only
* Private URLs → Accessible only by creator

---

## ▶️ How to Run the Project

---

Open browser to Run the application:

```
http://localhost:8080
```


---

## 🚀 Future Enhancements

* Admin dashboard
* Analytics charts
* Custom short keys
---

## 👨‍💻 Author

**Umair Ali**
Java Developer

---

## 📄 License

This project is for **learning and educational purposes**.
