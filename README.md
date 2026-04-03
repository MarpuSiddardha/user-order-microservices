# User-Order Microservices Application

A Spring Boot-based microservices architecture demonstrating user management and order processing with service discovery, API gateway, and inter-service communication.

## 🏗️ Architecture Overview

This application consists of four main microservices:

### 1. **Service Registry** (Port: 8761)
- **Technology**: Spring Boot + Eureka Server
- **Purpose**: Service discovery and registration
- **Features**: Netflix Eureka server for microservice registration

### 2. **User Service** (Port: 8080)
- **Technology**: Spring Boot + JPA + PostgreSQL
- **Purpose**: User management operations
- **Database**: PostgreSQL (`user_db`)
- **Features**: 
  - CRUD operations for users
  - RESTful API endpoints
  - Data validation
  - Exception handling

### 3. **Order Service** (Port: 8081)
- **Technology**: Spring Boot + JPA + PostgreSQL + OpenFeign
- **Purpose**: Order management and processing
- **Database**: PostgreSQL (`order_db`)
- **Features**:
  - Order creation and retrieval
  - Feign client for user service communication
  - Inter-service communication
  - Order filtering by user ID

### 4. **API Gateway** (Port: 8090)
- **Technology**: Spring Cloud Gateway
- **Purpose**: Single entry point for all microservices
- **Features**:
  - Route configuration
  - Request filtering
  - Load balancing capabilities
  - Centralized access point

## 📋 Prerequisites

- **Java 17**
- **Maven 3.6+**
- **PostgreSQL 12+**
- **Spring Boot 3.x**

## 🗄️ Database Setup

Create two PostgreSQL databases:

```sql
-- For User Service
CREATE DATABASE user_db;

-- For Order Service  
CREATE DATABASE order_db;
```

Default credentials:
- **Username**: `postgres`
- **Password**: `postgres`

## 🚀 Quick Start

### 1. Start Service Registry
```bash
cd service-registry
mvn spring-boot:run
```
Access: http://localhost:8761

### 2. Start User Service
```bash
cd UserService
mvn spring-boot:run
```
Access: http://localhost:8080

### 3. Start Order Service
```bash
cd OrderService
mvn spring-boot:run
```
Access: http://localhost:8081

### 4. Start API Gateway
```bash
cd api-gateway
mvn spring-boot:run
```
Access: http://localhost:8090

## 📡 API Endpoints

### User Service
- `POST /api/users` - Create a new user
- `GET /api/users/{id}` - Get user by ID
- `GET /api/users` - Get all users

### Order Service
- `POST /api/orders` - Create a new order
- `GET /api/orders/user/{userId}` - Get orders by user ID

### Through API Gateway
- `POST http://localhost:8090/api/users` - Create user via gateway
- `GET http://localhost:8090/api/users/{id}` - Get user via gateway
- `POST http://localhost:8090/api/orders` - Create order via gateway
- `GET http://localhost:8090/api/orders/user/{userId}` - Get orders via gateway

## 🏛️ Data Models

### User Entity
```json
{
  "id": "Long",
  "name": "String",
  "email": "String"
}
```

### Order Entity
```json
{
  "id": "Long",
  "userId": "Long",
  "productName": "String",
  "amount": "Double"
}
```

## 🔧 Configuration

### Service Registry (8761)
- Eureka server with self-registration disabled
- Acts as central service registry

### User Service (8080)
- PostgreSQL connection to `user_db`
- JPA with Hibernate DDL auto-update
- Eureka client registration

### Order Service (8081)
- PostgreSQL connection to `order_db`
- Feign client for User Service communication
- Configured timeouts and logging

### API Gateway (8090)
- Routes to User Service (`/api/users/**`)
- Routes to Order Service (`/api/orders/**`)
- Request filtering and routing

## 🛠️ Technologies Used

- **Spring Boot 3.x** - Application framework
- **Spring Cloud** - Microservices infrastructure
- **Spring Data JPA** - Database abstraction
- **PostgreSQL** - Primary database
- **Eureka Server** - Service discovery
- **Spring Cloud Gateway** - API gateway
- **OpenFeign** - Declarative REST client
- **Lombok** - Code generation
- **Maven** - Build tool

## 🔄 Inter-Service Communication

The Order Service communicates with the User Service using OpenFeign client to validate user existence and retrieve user information during order processing.

## 📊 Monitoring

- **Eureka Dashboard**: http://localhost:8761
- Service registration status
- Health checks for all services

## 🧪 Testing

Each service includes basic Spring Boot test configurations. Run tests with:

```bash
mvn test
```

## 🐛 Troubleshooting

1. **Database Connection**: Ensure PostgreSQL is running and databases exist
2. **Port Conflicts**: Check if ports 8080, 8081, 8090, 8761 are available
3. **Service Registration**: Verify services appear in Eureka dashboard
4. **Gateway Routing**: Check gateway logs for routing errors

## 📈 Scalability

This architecture supports:
- Horizontal scaling of individual services
- Load balancing through the gateway
- Independent deployment of services
- Database per service pattern

## 🔒 Security Considerations

- Currently uses basic authentication
- Consider implementing JWT or OAuth2
- Add rate limiting at gateway level
- Implement service-to-service authentication

---

**Note**: This is a demonstration project showcasing microservices patterns. For production use, consider adding comprehensive logging, monitoring, security, and CI/CD pipelines.
