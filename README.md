
# Customer Onboarding Application

A Spring Boot application for managing customer onboarding, accounts, and KYC details using Oracle Database.

---

## Prerequisites
Before running the project, ensure you have:
- **Java 17+**
- **Maven 3.8+**
- **Oracle Database** (Configured and running)
- **An IDE** (IntelliJ IDEA / Eclipse recommended)

---

## Database Setup
1. **Create a new Oracle schema** for the project:
   ```sql
   CREATE USER CustomerOnboarding IDENTIFIED BY YourPasswordHere;
   GRANT CONNECT, RESOURCE TO CustomerOnboarding;
   ```
2. **Update `application.properties`** (in `src/main/resources`) with your DB credentials:
   ```properties
   spring.datasource.url=jdbc:oracle:thin:@//<host>:<port>/<service_name>
   spring.datasource.username=CustomerOnboarding
   spring.datasource.password=YourPasswordHere
   spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

   # Disable Hibernate auto-update (using schema.sql & data.sql)
   spring.jpa.hibernate.ddl-auto=none

   # Load schema & data on startup
   spring.sql.init.mode=always
   spring.sql.init.schema-locations=classpath:schema.sql
   spring.sql.init.data-locations=classpath:data.sql
   ```

3. **Tables & Sample Data**
   - The project uses **`schema.sql`** (for table creation) and **`data.sql`** (for inserting sample records).
   - These scripts are automatically executed at startup.

---

## Running the Application
1. **Build the project:**
   ```bash
   mvn clean install
   ```
2. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```
3. **Access API documentation:**
   ```
   http://localhost:8080/swagger-ui.html
   ```

---

## Project Structure
```
CustomerOnboardingApp_Skeleton/
 ├── src/
 │   ├── main/
 │   │   ├── java/               # Spring Boot source code
 │   │   └── resources/
 │   │       ├── application.properties
 │   │       ├── schema.sql      # Database schema
 │   │       └── data.sql        # Sample data
 ├── pom.xml                     # Maven build file
 ├── .gitignore
 └── README.md
```

---

## API Endpoints
Some key endpoints:
- **Customers:**  
  - `GET /customers` – Get all customers  
  - `POST /customers` – Add a new customer  
  - `PUT /customers/{id}` – Update customer  
  - `DELETE /customers/{id}` – Delete customer  

- **Accounts:**  
  - `GET /accounts` – Get all accounts  
  - `POST /accounts` – Add a new account  

- **KYC Status & Documents:**  
  - `GET /kyc-status` – Get KYC statuses  
  - `POST /kyc-status` – Add KYC status  

*(Explore all APIs at Swagger UI once the application runs)*

---

## Default Test Data
- **Admin User:** `admin / admin123`
- **Sample Customers:** John Doe, Jane Smith  
- **Sample Accounts & KYC details:** Preloaded via `data.sql`

---

## Notes
- **Do NOT commit real credentials** to version control. Replace with placeholders in shared repositories.
- To refresh schema & sample data, **drop existing tables** or use a fresh schema before restart.
