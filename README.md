# ZONY Campus Backend 🚀 🎓

**Smart Learning Management System (LMS) powered by Spring Boot** - A comprehensive backend solution for managing educational institutions, courses, and user interactions. This repository implements secure authentication, role-based access control, and integrations with external services.

---

## 🌟 Features

- 👥 **User Management**
  - Secure user registration and authentication
  - Profile management and updates
  - Role-based access control
  
- 📚 **Course Management**
  - Create, read, update, and delete courses
  - Course enrollment and progress tracking
  - Assignment and resource management
  
- 👨‍🏫 **Faculty Management**
  - Faculty assignment to courses
  - Teaching schedule management
  - Performance tracking
  
- 💳 **Payment Integration**
  - Secure payment processing
  - Fee management
  - Transaction history
  
- 📱 **Notifications**
  - Real-time updates
  - Email notifications
  - SMS alerts (optional)

---

## 🛠️ Tech Stack

- **Backend Framework**: Java Spring Boot
- **Security**: Spring Security, JWT Authentication
- **Database**: MySQL
- **ORM**: Hibernate
- **Build Tool**: Maven
- **Documentation**: Swagger/OpenAPI

---

## 📦 Installation

1. **Prerequisites**
   ```bash
   - Java 11 or higher
   - MySQL 8.0 or higher
   - Maven 3.6+
   ```

2. **Clone the repository**
   ```bash
   git clone https://github.com/Chathupachamika/ZONY_Campus-backend.git
   cd ZONY_Campus-backend
   ```

3. **Configure Database**
   Create a new MySQL database and update `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/zony_campus
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

4. **Build and Run**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

---

## 🚀 API Endpoints

### Authentication
- **POST** `/api/auth/register` - Register new user
- **POST** `/api/auth/login` - User login
- **GET** `/api/auth/profile` - Get user profile

### Courses
- **GET** `/api/courses` - List all courses
- **POST** `/api/courses` - Create new course
- **GET** `/api/courses/{id}` - Get course details
- **PUT** `/api/courses/{id}` - Update course
- **DELETE** `/api/courses/{id}` - Delete course

### Faculty
- **GET** `/api/faculty` - List all faculty members
- **POST** `/api/faculty` - Add new faculty
- **PUT** `/api/faculty/{id}` - Update faculty details

### Payments
- **POST** `/api/payments/process` - Process payment
- **GET** `/api/payments/history` - Get payment history

---

## 📂 Project Structure

```plaintext
ZONY_Campus-backend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/zony/campus/
│   │   │       ├── config/
│   │   │       ├── controller/
│   │   │       ├── model/
│   │   │       ├── repository/
│   │   │       ├── service/
│   │   │       └── util/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

---

## 🔐 Security Considerations

- Implements JWT-based authentication
- Role-based access control (RBAC)
- Password encryption using BCrypt
- Input validation and sanitization
- CORS configuration
- Rate limiting for API endpoints

---

## 🚀 Deployment

1. **Build the JAR file**
   ```bash
   mvn clean package
   ```

2. **Run the application**
   ```bash
   java -jar target/zony-campus-backend.jar
   ```

---

## 🧪 Testing

Run the test suite:
```bash
mvn test
```

---

## 📖 Documentation

API documentation is available at:
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- API Docs: `http://localhost:8080/v3/api-docs`

---

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📜 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 📧 Contact

Chathupa Chamika - [chathupachamika765@gmail.com]

Project Link: [https://github.com/Chathupachamika/ZONY_Campus-backend](https://github.com/Chathupachamika/ZONY_Campus-backend)

---
Made with ❤️ by the Chathupa Chamika
