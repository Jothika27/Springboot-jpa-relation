INSERT INTO departments (name, description) VALUES ('Computer Science', 'Department of Computer Science and Engineering');
INSERT INTO departments (name, description) VALUES ('Mathematics', 'Department of Mathematics and Statistics');
INSERT INTO departments (name, description) VALUES ('Physics', 'Department of Physics and Astronomy');
INSERT INTO departments (name, description) VALUES ('Chemistry', 'Department of Chemistry and Biochemistry');

INSERT INTO teachers (first_name, last_name, email, phone, department_id) VALUES('John', 'Smith', 'john.smith@university.edu', '+1-555-0101', 1);
INSERT INTO teachers (first_name, last_name, email, phone, department_id) VALUES('Emily', 'Johnson', 'emily.johnson@university.edu', '+1-555-0102', 1);
INSERT INTO teachers (first_name, last_name, email, phone, department_id) VALUES('Michael', 'Brown', 'michael.brown@university.edu', '+1-555-0103', 2);
INSERT INTO teachers (first_name, last_name, email, phone, department_id) VALUES('Sarah', 'Davis', 'sarah.davis@university.edu', '+1-555-0104', 2);
INSERT INTO teachers (first_name, last_name, email, phone, department_id) VALUES('David', 'Wilson', 'david.wilson@university.edu', '+1-555-0105', 3);
INSERT INTO teachers (first_name, last_name, email, phone, department_id) VALUES('Lisa', 'Anderson', 'lisa.anderson@university.edu', '+1-555-0106', 4);

INSERT INTO subjects (name, code, credits, description, department_id, teacher_id) VALUES('Data Structures', 'CS101', 3, 'Introduction to fundamental data structures', 1, 1);
INSERT INTO subjects (name, code, credits, description, department_id, teacher_id) VALUES('Algorithms', 'CS201', 3, 'Design and analysis of algorithms', 1, 1);
INSERT INTO subjects (name, code, credits, description, department_id, teacher_id) VALUES('Database Systems', 'CS301', 4, 'Introduction to database design and management', 1, 2);
INSERT INTO subjects (name, code, credits, description, department_id, teacher_id) VALUES('Calculus I', 'MATH101', 4, 'Differential and integral calculus', 2, 3);
INSERT INTO subjects (name, code, credits, description, department_id, teacher_id) VALUES('Linear Algebra', 'MATH201', 3, 'Vector spaces and linear transformations', 2, 4);
INSERT INTO subjects (name, code, credits, description, department_id, teacher_id) VALUES('Physics I', 'PHYS101', 4, 'Classical mechanics and thermodynamics', 3, 5);
INSERT INTO subjects (name, code, credits, description, department_id, teacher_id) VALUES('Organic Chemistry', 'CHEM201', 4, 'Structure and reactions of organic compounds', 4, 6);

INSERT INTO students (first_name, last_name, email, student_id, phone, date_of_birth, enrollment_date) VALUES('Alice', 'Johnson', 'alice.johnson@student.edu', 'STU001', '+1-555-1001', '2000-05-15', '2022-09-01');
INSERT INTO students (first_name, last_name, email, student_id, phone, date_of_birth, enrollment_date) VALUES('Bob', 'Williams', 'bob.williams@student.edu', 'STU002', '+1-555-1002', '1999-08-22', '2022-09-01');
INSERT INTO students (first_name, last_name, email, student_id, phone, date_of_birth, enrollment_date) VALUES('Charlie', 'Brown', 'charlie.brown@student.edu', 'STU003', '+1-555-1003', '2001-03-10', '2022-09-01');
INSERT INTO students (first_name, last_name, email, student_id, phone, date_of_birth, enrollment_date) VALUES('Diana', 'Miller', 'diana.miller@student.edu', 'STU004', '+1-555-1004', '2000-11-28', '2022-09-01');
INSERT INTO students (first_name, last_name, email, student_id, phone, date_of_birth, enrollment_date) VALUES('Edward', 'Davis', 'edward.davis@student.edu', 'STU005', '+1-555-1005', '1999-12-05', '2021-09-01');
INSERT INTO students (first_name, last_name, email, student_id, phone, date_of_birth, enrollment_date) VALUES('Fiona', 'Wilson', 'fiona.wilson@student.edu', 'STU006', '+1-555-1006', '2001-07-18', '2023-09-01');

INSERT INTO student_subjects (student_id, subject_id) VALUES(1, 1), (1, 2), (1, 4);     -- Alice: CS101, CS201, MATH101
INSERT INTO student_subjects (student_id, subject_id) VALUES(2, 1), (2, 3), (2, 5);     -- Bob: CS101, CS301, MATH201
INSERT INTO student_subjects (student_id, subject_id) VALUES(3, 2), (3, 3), (3, 6);     -- Charlie: CS201, CS301, PHYS101
INSERT INTO student_subjects (student_id, subject_id) VALUES(4, 4), (4, 5), (4, 7);     -- Diana: MATH101, MATH201, CHEM201
INSERT INTO student_subjects (student_id, subject_id) VALUES(5, 1), (5, 2), (5, 3); -- Edward: CS101, CS201, CS301
INSERT INTO student_subjects (student_id, subject_id) VALUES(6, 4),(6, 6),(6, 7); -- Fiona: MATH101, PHYS101, CHEM201

//build the docker container
docker build -t springboot-jpa-app .

// run the docker container
docker run -p 8080:8080 springboot-jpa-app

10.1Build and Run Commands

# Navigate to project directory
cd student-management-system

# Clean and build the project
mvn clean compile

# Run the application
mvn spring-boot:run

# Alternative: Build JAR and run
mvn clean package
java -jar target/student-management-system-1.0.0.jar

10.2Testing Endpoints
Department Endpoints:

bash

# Get all departments
curl -X GET http://localhost:8080/api/departments

# Get department by ID
curl -X GET http://localhost:8080/api/departments/1

# Create new department
curl -X POST http://localhost:8080/api/departments \
-H "Content-Type: application/json" \
-d '{"name":"Biology","description":"Department of Biological Sciences"}'

# Update department
curl -X PUT http://localhost:8080/api/departments/1 \
-H "Content-Type: application/json" \
-d '{"name":"Computer Science","description":"Updated description"}'

Student Endpoints:

bash

# Get all students
curl -X GET http://localhost:8080/api/students

# Get student with subjects
curl -X GET http://localhost:8080/api/students/1/subjects

# Create new student
curl -X POST http://localhost:8080/api/students \
-H "Content-Type: application/json" \
-d '{
"firstName":"John",
"lastName":"Doe", "email":"john.doe@student.edu", "studentId":"STU007", "phone":"+1-555-1007"
}'

# Enroll student in subject
curl -X POST http://localhost:8080/api/students/1/enroll/1

Teacher Endpoints:

bash

# Get all teachers
curl -X GET http://localhost:8080/api/teachers

# Get teachers by department
curl -X GET http://localhost:8080/api/teachers/department/1

# Search teachers by name
curl -X GET "http://localhost:8080/api/teachers/search?name=John"

Subject Endpoints:

# Get all subjects
curl -X GET http://localhost:8080/api/subjects

# Get subject with students
curl -X GET http://localhost:8080/api/subjects/1/students

# Get subjects by department
curl -X GET http://localhost:8080/api/subjects/department/1

10.3Accessing H2 Console
1.Open browser and go to:
2.Use these settings:
 JDBC URL:
 User Name:
 Password:
3.Click Connect to access the database

10.4Actuator Endpoints

bash

# Health check
curl -X GET http://localhost:8080/actuator/health

# Application info
curl -X GET http://localhost:8080/actuator/info

# All endpoints
curl -X GET http://localhost:8080/actuator

# Metrics
curl -X GET http://localhost:8080/actuator/metrics

# Environment
curl -X GET http://localhost:8080/actuator/env

Relationship Summary
The application implements the following relationships:

1.Department ↔ Teacher: One-to-Many
 One department can have many teachers  Each teacher belongs to one department
2.Department ↔ Subject: One-to-Many
 One department can offer many subjects  Each subject belongs to one department
3.Teacher ↔ Subject: One-to-Many
 One teacher can teach many subjects  Each subject is taught by one teacher
4.Student ↔ Subject: Many-to-Many
 One student can enroll in many subjects
 One subject can have many students enrolled

Key Features Implemented
 ✅ Complete CRUD operations for all entities
 ✅ Proper JPA relationships with cascade operations
  ✅ Input validation using Bean Validation
  ✅ Custom repository methods with JPQL queries
 ✅ Service layer with transaction management
  ✅ RESTful API endpoints with proper HTTP status codes
 ✅ Global exception handling
  ✅ H2 in-memory database with console access
  ✅ Spring Boot Actuator for monitoring
  ✅ Sample data initialization
  ✅ Lazy loading optimization
 ✅ Cross-origin resource sharing (CORS) support
