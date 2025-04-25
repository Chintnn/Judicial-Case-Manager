# Judicial Case Manager

## Flexi Credit Course - Object Oriented Programming in Java CA3 - Mini Project

---

**This project is a follow-up project to the initially built database: [Judicial Case Management System](https://github.com/Chintnn/Judicial-Case-Management-System)**

The **Judicial Case Manager** repository hosts two integrated Java applications—a desktop Swing client and a Java Servlet–based web interface—both backed by a centralized MySQL judicial database. Together, these modules provide a unified solution for legal professionals to:

- **Record** and **manage** detailed case files (petitioners, respondents, hearing notes, evidence, verdicts).
- **Track** scheduling and outcomes of hearings, bail conditions, settlements, and appeals.
- **Audit** all changes with history tables, timestamps, and user-based action logs.
- **Extend** or **integrate** with existing judicial workflows via modular DAO and utility layers.

Whether you prefer a responsive web console or an offline-capable desktop tool, Judicial Case Manager delivers full CRUD, reporting, and data-visualization capabilities in a secure, transactional environment.

---

## Technology Stack

| Layer                | Tools / Frameworks          |
|----------------------|-----------------------------|
| Database             | MySQL 5.7+ / MariaDB        |
| Persistence (DAO)    | JDBC, MySQL Connector/J     |
| Desktop GUI          | Java Swing (JDK 21)         |
| Web Backend          | Java Servlets (Tomcat 10.1) |
| Build & Dependency   | Maven / Manual JAR imports  |
| IDE                  | IntelliJ IDEA, Eclipse      |
| Version Control      | Git, GitHub                 |
| Testing              | JUnit, Mockito (optional)   |
| Logging              | java.util.logging (custom)  |

---

## Repository Structure

```bash
Judicial-Case-Manager/
├── JudicialCaseManager GUI/           # Desktop Swing client
│   ├── pom.xml or lib/                # (If using Maven) or External JARs
│   ├── src/main/java/
│   │   ├── dao/                       # CRUD classes per entity (CaseDAO, HearingDAO)
│   │   ├── db/                        # Singleton DatabaseConnection util
│   │   ├── gui/                       # Frames, Panels, Dialogs
│   │   ├── model/                     # POJOs representing tables (Case, Person)
│   │   ├── exception/                 # Custom exceptions (DAOException)
│   │   ├── utils/                     # DateFormatter, ValidationUtils
│   │   └── App.java                   # Main launcher (login flow)
│   └── src/main/resources/
│       └── schema/JudicialCaseManagerSystem.sql  # DDL and sample data inserts
├── JudicialCaseManager WEB/           # Web application
│   ├── pom.xml or lib/                # (If Maven) or manually added JARs
│   ├── src/main/java/
│   │   ├── dao/                       # Same DAO layer reused or separate for web
│   │   ├── db/                        # JDBC util for web context
│   │   ├── model/                     # Shared or web-specific POJOs
│   │   ├── servlet/                   # HttpServlet classes (LoginServlet, CaseServlet)
│   │   ├── exception/                 # WebException, FilterException
│   │   └── utils/                     # WebUtils, SessionValidator
│   ├── src/main/webapp/
│   │   ├── WEB-INF/
│   │   │   ├── web.xml                # Servlet mappings, security constraints
│   │   │   └── lib/mysql-connector-j.jar
│   │   ├── jsp/                       # JSP pages (login.jsp, dashboard.jsp)
│   │   ├── css/                       # Stylesheets
│   │   └── js/                        # Client-side scripts (optional)
│   └── context.xml                    # (Optional) Tomcat resource config
└── LICENSE
```

---

## Architecture & Module Breakdown

### 1. Database Layer (Shared)
- **Schema**: Defined in `schema/JudicialCaseManagerSystem.sql`, includes ~23 tables covering cases, hearings, petitions, evidence, staff, and history tables for audit trails.
- **Connection**: `DatabaseConnection` uses a static singleton pattern to manage a single `Connection` instance per application lifecycle. Supports connection pooling when extended.
- **Transaction Management**: Manual commit/rollback in DAOs around multi-statement operations (e.g., creating a case and initial hearing record atomically).

### 2. DAO Layer
- Each DAO (e.g., `CaseDAO`, `HearingDAO`, `VerdictDAO`) implements basic CRUD:
  - `create(Entity)` inserts new records and returns generated keys.
  - `findById(id)`, `findAll()`, and parameterized `findByStatus(status)` for queries.
  - `update(Entity)` updates mutable fields.
  - `delete(id)` performs soft deletes by toggling a `status` flag.
- **Error Handling**: Wraps `SQLException` in a custom `DAOException` to decouple SQL errors from business logic.

### 3. Desktop GUI Module
- **Login Flow**:
  1. `LoginFrame` collects username/password.
  2. Validates credentials via `UserDAO.authenticate()`.
  3. Opens `DashboardFrame` on success or shows error dialog.
- **Dashboard**:
  - Tabbed UI: Cases, Hearings, Reports, Settings.
  - Dynamic tables via `JTable` with custom `TableModel` reflecting live DB state.
- **Forms & Dialogs**:
  - `CaseFormDialog` for Create/Edit with field validation.
  - `HearingDialog` capturing date/time pickers and room selection.
- **Utilities**:
  - Date formatting, numeric input masks, and centralized `Logger` for user actions.

### 4. Web Application Module
- **Deployment Descriptor**: `web.xml` configures servlet mappings, session timeouts, and security roles.
- **Servlets**:
  - `LoginServlet`, `CaseServlet`, `HearingServlet`, each delegating to respective DAOs and forwarding to JSPs.
- **Views (JSP)**:
  - Uses JSTL for loops/conditionals to render case lists, forms, and error messages.
  - CSS/JavaScript assets under `/css` and `/js` for UI enhancements.
- **Session Handling**:
  - `AuthFilter` intercepts protected URLs, redirects unauthenticated users to login.
- **Error Pages**:
  - Custom `error.jsp` for 404/500 with user-friendly messages and stack traces in dev mode.

---

## Getting Started

### Prerequisites
1. **Java SE Development Kit 21** installed and `JAVA_HOME` set.
2. **Apache Tomcat 10.1** downloaded and configured.
3. **MySQL Server 5.7+** running locally or remotely.
4. **MySQL Connector/J** JAR added to both modules’ classpaths.
5. **Git** CLI for version control.
6. **IDE** of choice (IntelliJ, Eclipse) with Java EE support.

### Initial Setup

1. **Clone the repo**:
   ```bash
   git clone https://github.com/Chintnn/Judicial-Case-Manager.git
   cd Judicial-Case-Manager
   ```
2. **Create and initialize the database**:
   ```sql
   CREATE DATABASE judicial_case_manager;
   USE judicial_case_manager;
   SOURCE src/main/resources/schema/JudicialCaseManagerSystem.sql;
   ```
3. **Verify tables**:
   ```bash
   mysql -u user -p -e "USE judicial_case_manager; SHOW TABLES;"
   ```

---

## Configuration

Both modules share similar connection settings, stored in `DatabaseConnection.java`:
```java
private static final String URL = "jdbc:mysql://localhost:3306/judicial_case_manager";
private static final String USER = "root";
private static final String PASSWORD = "password";
```
Modify these to match your environment. For production, consider externalizing via `.properties` or JNDI resources.

---

## Running the Applications

### Desktop GUI
1. **Import** `JudicialCaseManager GUI` into your IDE as a Java project.
2. **Add** MySQL Connector JAR to the project’s classpath.
3. **Run** `App.java` (contains `public static void main`).
4. **Interact** with the Swing UI to login, browse cases, schedule hearings, and generate reports.

### Web Application
1. **Import** `JudicialCaseManager WEB` as a Dynamic Web Project.
2. **Copy** `mysql-connector-j.jar` into `WEB-INF/lib`.
3. **Deploy** to Tomcat (via IDE or manually drop `*.war`).
4. **Browse** to `http://localhost:8080/JudicialCaseManagerWEB/`.
5. **Login** and access servlet-driven pages (cases, hearings, reports).

---

## Testing & Validation

- **JUnit Tests**: Place under `src/test/java/dao/` to validate CRUD DAO operations. Mock `Connection` for unit tests using Mockito.
- **Integration Tests**: Use an in-memory MySQL (e.g., MariaDB4j) or Docker container and execute SQL scripts then run DAO methods.
- **Manual QA**: Verify login, case creation/editing, hearing scheduling, verdict entry, and appeal workflows.
- **Logging**: Check `app.log` (configured via `logging.properties`) for errors, SQL exceptions, and user actions.

---

## Troubleshooting & FAQs

**Q1: Connection refused on `localhost:3306`.**
- Ensure MySQL service is running and port matches. Check `my.cnf` for bind address.

**Q2: `ClassNotFoundException: com.mysql.cj.jdbc.Driver`.**
- Confirm Connector/J JAR is in the module’s classpath or `WEB-INF/lib`.

**Q3: Servlet 404s after deploy.**
- Review `web.xml` servlet-mapping and project context path. Check server logs for deployment errors.

**Q4: UI freeze in Swing app.**
- Swing actions should run off the Event Dispatch Thread (EDT). Use `SwingWorker` for long-running tasks.

---

## Extending & Customization

- **Add New Entities**: Create new table DDL in schema, generate corresponding model, DAO, GUI form, and servlet/JSP.
- **Reporting**: Integrate JasperReports or Apache POI to export PDF/XLS of case summaries.
- **Authentication**: Replace basic username/password with LDAP or OAuth2 via filter extensions.
- **API Layer**: Add a RESTful API module using JAX-RS (Jersey) for third-party integrations.

