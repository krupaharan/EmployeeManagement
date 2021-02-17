# EmployeeManagement

Sample Spring Boot application to manage Employee Details

## Dependencies

JDK v1.8
Maven v3.6 (Other versions also fine, but preferably 3+)

Please make sure the to install the dependencies before going further.

## Setup

 * Clone the code base, git clone <SSH or HTTPS URL>
 * Ensure you're on the main branch, git branch
 * Navigate to the cloned location and install the app dependencies, mvn clean install
 * Build the application, mvn package (add -DskipTests=true to skip test cases)
 * Once the build is successful, run the package, java -jar target/employeemgmt-0.0.1-SNAPSHOT.jar
    * Application will fail to boot up if port 8080 is occupied, please ensure the port is free before hitting the command
 * Go to browser, hit http://localhost:8080/swagger-ui.html to check the exposed endpoints and its respective request/response. 
 * Application uses H2 in-memory database and its available at http://localhost:8080/h2-console/


