# Supplier Data Manager

A full-stack Spring Boot application that demonstrates how to build both **REST API** and **GraphQL API** for managing supplier data, using MySQL as the backend. This project is designed to help you understand the practical differences between REST and GraphQL by providing both implementations side by side.

---

## 🚀 Features

- **CRUD operations** for suppliers and their related entities
- **REST API** endpoints (Spring MVC)
- **GraphQL API** endpoints (Spring GraphQL)
- **MySQL** database integration (with Docker support)
- **phpMyAdmin** for easy database management
- **Robust error handling** for both REST and GraphQL
- **DTOs and validation** for clean data transfer
- **Modular, production-ready code structure**

---

## 🏗️ Architecture

- **Backend:** Java 17+, Spring Boot, Spring Data JPA, Spring GraphQL
- **Database:** MySQL 8 (via Docker)
- **ORM:** Hibernate (JPA)
- **API Styles:** REST & GraphQL (choose what you like!)
- **Frontend:** Not included (API only)

---

## 📦 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/raju4789/supplier-data-manager.git
cd supplier-data-manager
```

### 2. Start MySQL and phpMyAdmin with Docker

```bash
docker-compose up -d
```

- MySQL runs on `localhost:3306`
- phpMyAdmin available at [http://localhost:8082](http://localhost:8082)
- Default credentials:  
  - **User:** `admin`  
  - **Password:** `admin@2025`  
  - **Database:** `supplier_db`

### 3. Run the Spring Boot Application

```bash
./gradlew bootRun
```

- The app runs on [http://localhost:8080](http://localhost:8080)

---

## 🛠️ API Usage

### REST API

Base URL: `http://localhost:8080/api/suppliers`

| Method | Endpoint                  | Description                |
|--------|---------------------------|----------------------------|
| GET    | `/api/suppliers`          | List all suppliers         |
| GET    | `/api/suppliers/{id}`     | Get supplier by ID         |
| POST   | `/api/suppliers`          | Create a new supplier      |
| PUT    | `/api/suppliers/{id}`     | Update supplier (full)     |
| PATCH  | `/api/suppliers/{id}`     | Update supplier (partial)  |
| DELETE | `/api/suppliers/{id}`     | Delete supplier            |

**Example: Create Supplier**

```http
POST /api/suppliers
Content-Type: application/json

{
  "supplierName": "Acme Air",
  "supplierType": "Airline",
  "supplierFromEmail": "contact@acmeair.com",
  "supplierGroup": "GroupA",
  "subSupplierGroup": "SubA",
  "sopModel": "ModelX",
  "createdBy": "user-123"
}
```

### GraphQL API

- **GraphQL endpoint:** `http://localhost:8080/graphql`
- **GraphiQL playground:** [http://localhost:8080/graphiql](http://localhost:8080/graphiql)

**Example: Query Supplier**

```graphql
query {
  getSupplierById(supplierId: 1) {
    supplierName
    partnerConfigInfo {
      productId
    }
  }
}
```

**Example: Create Supplier**

```graphql
mutation {
  createSupplier(supplier: {
    supplierName: "Acme Air"
    supplierType: "Airline"
    supplierFromEmail: "contact@acmeair.com"
    supplierGroup: "GroupA"
    subSupplierGroup: "SubA"
    sopModel: "ModelX"
    createdBy: "user-123"
  }) {
    supplierId
    supplierName
  }
}
```

---

## 🧩 Project Structure

```
src/
  main/
    java/com/raju/supplier_data_manager/
      controllers/         # REST controllers
      graphql/             # GraphQL resolvers
      dtos/                # Data Transfer Objects
      entities/            # JPA entities
      services/            # Business logic
      repositories/        # JPA repositories
      advices/             # Exception handling
      mappers/             # DTO/entity mappers
    resources/
      application.yml      # Spring Boot config
      graphql/schema.graphqls # GraphQL schema
```

---

## 🐳 Database Management

- **phpMyAdmin:** [http://localhost:8082](http://localhost:8082)
- **MySQL:**  
  - Host: `localhost`  
  - Port: `3306`  
  - User: `admin`  
  - Password: `admin@2025`  
  - DB: `supplier_db`

---

## 🧪 Running Tests

```bash
./gradlew test
```

---

## 🤝 Contributing

Contributions are welcome! Please open issues or submit pull requests for improvements, bug fixes, or new features.

1. Fork the repo
2. Create your feature branch (`git checkout -b feature/your-feature`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature/your-feature`)
5. Open a pull request

---

## 📚 Further Reading

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring GraphQL](https://spring.io/projects/spring-graphql)
- [GraphQL Official Docs](https://graphql.org/learn/)
- [REST vs GraphQL](https://www.howtographql.com/basics/1-graphql-is-the-better-rest/)

---

## 📄 License

This project is licensed under the [Apache 2.0 License](LICENSE).

---

**Happy coding!** 🚀  
If you have questions or suggestions, feel free to open an issue or reach out!

---

