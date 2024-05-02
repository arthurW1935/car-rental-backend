# Car Rental Management System

## Description
In this project we have developed a comprehensive system for managing car rental operations, including vehicle records, customer information, rental transactions, and branch management. The system provides functionalities for creating, updating, retrieving, and deleting details about cars, customers, rentals, and branches.

## Authors
* [@arthurW1935](https://github.com/arthurW1935/) Sourashis
* [@yashathwani](https://github.com/yashathwani) Yash
* [@TUSHAR1651](https://github.com/TUSHAR1651) Tushar
* [@Souvik-Cyclic](https://github.com/Souvik-Cyclic) Souvik
* [@aannaannyaaa](https://github.com/aannaannyaaa) Ananya
* [@garvit420](https://github.com/garvit420) Garvit

## Installation
* Clone the repository to your local machine using the command ` git clone https://github.com/arthurW1935/car-rental-backend.git `.
* We recommend installing an IDE for this project. We used [IntelliJ](https://www.jetbrains.com/idea/).
* Install [Docker](https://www.docker.com/products/docker-desktop/) and set it up as per your operating system.
* Docker Installation Guide [Windows](https://youtu.be/WDEdRmTCSs8), [Linux](https://youtu.be/5_EA3rBCXmU), [Mac](https://youtu.be/-EXlfSsP49A).
* After installing Docker, ensure it is running. Then, navigate to your project directory and run the command `docker compose up` in the terminal. If you encounter any errors, check the `docker-compose.yml` file in the project directory for conflicting port usage.
* We recommend installing [Postman](https://www.postman.com/downloads/) for testing api calls.
* Now, finally run the `CarRentalBackendApplication` present in your project directory to start the project.

## Documentation
### Models Involved
* Branch
* Customer
* Employee
* Rental
* Vehicle
* Vehicle Status
* Vehicle Type

##### The Branch model includes the following:
* Branch Id
* Branch Location
* Branch Phone Number
* Branch Email
* Branch Manager

##### The Customer model includes the following:
* Customer Id
* Customer Name
* Customer Driving License Number
* Customer Phone Number
* Customer Email
* Customer Address

##### The Employee model includes the following:
* Employee Id
* Employee Name
* Employee Phone Number
* Employee email
* Employee Joining Date
* Employee Current Branch

##### The Rental model includes the following:
* Rental Id
* Rental Vehicle
* Rental Customer
* Rental Start Date
* Rental End Date
* Rental Total Cost

##### The Vehicle model includes the following:
* Vehicle Id
* Vehicle Manufacturer
* Vehicle Model
* Vehicle Registration Year
* Vehicle Number Plate
* Vehicle Mileage
* Branch to which Vehicle belongs
* Vehicle Type
* Vehicle Status

#### Technologies Used
Spring Boot
MySQL
Docker
Postman
IntelliJ IDEA

##### The Vehicle Status includes the following:
* Status of the vehicle, such as whether it is AVAILABLE, already RENTED, or out for MAINTENANCE.

##### The Vehicle Type includes the following:
* Type of the vehicle, indicating whether it is a 2-wheeler or 4-wheeler.

# Project Details
* Architecture: RESTful API architecture using Spring Boot
* Database: MySQL Database

## Api Endpoints
### Branch Controller:
* /branch/{id} (GET): Retrieve a branch by its ID.
* /branch (GET): Retrieve all branches.
* /branch (POST): Create a new branch.
* /branch (PUT): Update an existing branch.
* /branch/{id} (DELETE): Delete a branch by its ID.
### Customer Controller:
* /customers/{id} (GET): Retrieve a customer by their ID.
* /customers (GET): Retrieve all customers.
* /customers (POST): Add a new customer.
* /customers (PUT): Update an existing customer.
* /customers/{id} (DELETE): Delete a customer by their ID.
### Employee Controller:
* /employee/{id} (GET): Retrieve an employee by their ID.
* /employee (GET): Retrieve all employees.
* /employee (POST): Create a new employee.
* /employee (PUT): Update an existing employee.
* /employee/{id} (DELETE): Delete an employee by their ID.
* /employee/branch/{id} (GET): Retrieve all employees of a specific branch.
### Rental Controller:
* /rental (POST): Create a new rental transaction.
* /rental/{id} (GET): Retrieve a rental transaction by its ID.
* /rental (GET): Retrieve all ongoing rentals.
* /rental/history/customer/{id} (GET): Retrieve rental history for a customer by their ID.
* /rental/history/vehicle/{id} (GET): Retrieve rental history for a vehicle by its ID.
* /rental/reservation/vehicle/{id} (GET): Retrieve reservations for a vehicle by its ID.
* /rental/reservation/customer/{id} (GET): Retrieve reservations for a customer by their ID.
* /rental/cost (GET): Calculate the rental cost based on provided parameters.
* /rental/{id} (DELETE): Cancel a rental transaction by its ID.
### Vehicle Controller:
* /vehicles/{id} (GET): Retrieve a vehicle by its ID.
* /vehicles (GET): Retrieve all vehicles.
* /vehicles (POST): Create a new vehicle.
* /vehicles/{id} (PUT): Update an existing vehicle by its ID.
* /vehicles/{id} (DELETE): Remove a vehicle by its ID.
* /vehicles/type (GET): Retrieve vehicles by type.
* /vehicles/branch/{id} (GET): Retrieve vehicles by branch.
* /vehicles/status (GET): Retrieve vehicles by status.

### Some examples for the sample input and output of the API endpoints are as follows:





1. /branch/{id} (GET)

   Input: An ID of an existing branch, for example `1`.

   Output: A `BranchDto` object with the details of the branch with the given ID. For example:
   ```json
   {
       "id": 1,
       "location": "New York",
       "managerId": 1,
       "phoneNumber": "1234567890",
       "email": "branch.newyork@example.com"
   }
   ```

2. /branch (GET)

   Input: No input is required.

   Output: A list of `BranchDto` objects with the details of all branches. For example:
   ```json
   [
       {
           "id": 1,
           "location": "New York",
           "managerId": 1,
           "phoneNumber": "1234567890",
           "email": "branch.newyork@example.com"
       },
       {
           "id": 2,
           "location": "Los Angeles",
           "managerId": 2,
           "phoneNumber": "0987654321",
           "email": "branch.losangeles@example.com"
       }
   ]
   ```

3. /employee (POST)

   Input: A `Branch` object without an ID. For example:
   ```json
   {
       "location": "New York",
       "managerId": 1,
       "phoneNumber": "1234567890",
       "email": "branch.newyork@example.com"
   }
   ```

   Output: A `BranchDto` object with the details of the created branch, including the generated ID. For example:
   ```json
   {
       "id": 1,
       "location": "New York",
       "managerId": 1,
       "phoneNumber": "1234567890",
       "email": "branch.newyork@example.com"
   }
   ```

4. /branch (PUT)

   Input: A `Branch` object with an ID. For example:
   ```json
   {
       "id": 1,
       "location": "New York",
       "managerId": 1,
       "phoneNumber": "1234567890",
       "email": "branch.newyork@example.com"
   }
   ```

   Output: A `BranchDto` object with the details of the updated branch. For example:
   ```json
   {
       "id": 1,
       "location": "New York",
       "managerId": 1,
       "phoneNumber": "1234567890",
       "email": "branch.newyork@example.com"
   }
   ```

5. /branch/{id} (DELETE)

   Input: An ID of an existing branch, for example `1`.

   Output: A `BranchDto` object with the details of the deleted branch. For example:
   ```json
   {
       "id": 1,
       "location": "New York",
       "managerId": 1,
       "phoneNumber": "1234567890",
       "email": "branch.newyork@example.com"
   }
   ```

1. /employee/{id} (GET)

   Input: An ID of an existing employee, for example `1`.

   Output: An `EmployeeDto` object with the details of the employee with the given ID. For example:
   ```json
   {
       "id": 1,
       "name": "John Doe",
       "phoneNumber": "1234567890",
       "email": "john.doe@example.com",
       "joiningDate": "2022-01-01",
       "branchId": 1
   }
   ```

2. /employee (GET)

   Input: No input is required.

   Output: A list of `EmployeeDto` objects with the details of all employees. For example:
   ```json
   [
       {
           "id": 1,
           "name": "John Doe",
           "phoneNumber": "1234567890",
           "email": "john.doe@example.com",
           "joiningDate": "2022-01-01",
           "branchId": 1
       },
       {
           "id": 2,
           "name": "Jane Doe",
           "phoneNumber": "0987654321",
           "email": "jane.doe@example.com",
           "joiningDate": "2022-02-01",
           "branchId": 2
       }
   ]
   ```

3. /employee (POST)

   Input: An `Employee` object without an ID. For example:
   ```json
   {
       "name": "John Doe",
       "phoneNumber": "1234567890",
       "email": "john.doe@example.com",
       "joiningDate": "2022-01-01",
       "branch": {
           "id": 1
       }
   }
   ```

   Output: An `EmployeeDto` object with the details of the created employee, including the generated ID. For example:
   ```json
   {
       "id": 1,
       "name": "John Doe",
       "phoneNumber": "1234567890",
       "email": "john.doe@example.com",
       "joiningDate": "2022-01-01",
       "branchId": 1
   }
   ```

4. /employee (PUT)

   Input: An `Employee` object with an ID. For example:
   ```json
   {
       "id": 1,
       "name": "John Doe",
       "phoneNumber": "1234567890",
       "email": "john.doe@example.com",
       "joiningDate": "2022-01-01",
       "branch": {
           "id": 1
       }
   }
   ```

   Output: An `EmployeeDto` object with the details of the updated employee. For example:
   ```json
   {
       "id": 1,
       "name": "John Doe",
       "phoneNumber": "1234567890",
       "email": "john.doe@example.com",
       "joiningDate": "2022-01-01",
       "branchId": 1
   }
   ```

5. /employee/{id} (DELETE)

   Input: An ID of an existing employee, for example `1`.

   Output: An `EmployeeDto` object with the details of the deleted employee. For example:
   ```json
   {
       "id": 1,
       "name": "John Doe",
       "phoneNumber": "1234567890",
       "email": "john.doe@example.com",
       "joiningDate": "2022-01-01",
       "branchId": 1
   }
   ```

6. `getEmployeesByBranch(Long branchId)`

   Input: An ID of an existing branch, for example `1`.

   Output: A list of `EmployeeDto` objects with the details of all employees in the given branch. For example:
   ```json
   [
       {
           "id": 1,
           "name": "John Doe",
           "phoneNumber": "1234567890",
           "email": "john.doe@example.com",
           "joiningDate": "2022-01-01",
           "branchId": 1
       },
       {
           "id": 2,
           "name": "Jane Doe",
           "phoneNumber": "0987654321",
           "email": "jane.doe@example.com",
           "joiningDate": "2022-02-01",
           "branchId": 1
       }
   ]
   ```

1. /rental (POST)

   Input: A `RentalDto` object without an ID. For example:
   ```json
   {
       "customerId": 1,
       "vehicleId": 1,
       "startDate": "2022-01-01",
       "endDate": "2022-01-10"
   }
   ```

   Output: A `Rental` object with the details of the created rental, including the generated ID. For example:
   ```json
   {
       "id": 1,
       "customerId": 1,
       "vehicleId": 1,
       "startDate": "2022-01-01",
       "endDate": "2022-01-10"
   }
   ```

2. /rental/{id} (GET)

   Input: An ID of an existing rental, for example `1`.

   Output: A `Rental` object with the details of the rental with the given ID. For example:
   ```json
   {
       "id": 1,
       "customerId": 1,
       "vehicleId": 1,
       "startDate": "2022-01-01",
       "endDate": "2022-01-10"
   }
   ```

3. /rental (GET)

   Input: No input is required.

   Output: A list of `Rental` objects with the details of all ongoing rentals.

4. /rental/history/customer/{id} (GET)

   Input: An ID of an existing customer, for example `1`.

   Output: A list of `Rental` objects with the details of all rentals by the given customer.

5. /rental/history/vehicle/{id} (GET)

   Input: An ID of an existing vehicle, for example `1`.

   Output: A list of `Rental` objects with the details of all rentals of the given vehicle.

6. /rental/reservation/vehicle/{id} (GET)

   Input: An ID of an existing vehicle, for example `1`.

   Output: A list of `Rental` objects with the details of all reservations of the given vehicle.

7. /rental/reservation/customer/{id} (GET)

   Input: An ID of an existing customer, for example `1`.

   Output: A list of `Rental` objects with the details of all reservations by the given customer.

8. /rental/cost (GET)

   Input: A `CostDto` object with the details of the rental for which the cost is to be calculated. For example:
   ```json
   {
       "vehicleId": 1,
       "startDate": "2022-01-01",
       "endDate": "2022-01-10"
   }
   ```

   Output: The cost of the rental as a `Double`.

9. /rental/{id} (DELETE)

   Input: An ID of an existing rental, for example `1`.

   Output: A `Rental` object with the details of the cancelled rental.



1. /vehicles/{id}

   Input: An ID of an existing vehicle, for example `1`.

   Output: A `VehicleDto` object with the details of the vehicle with the given ID. For example:
   ```json
   {
       "id": 1,
       "manufacturer": "Toyota",
       "model": "Camry",
       "year": 2018,
       "licensePlateNumber": "ABC-123",
       "currentMileage": 50000.0,
       "vehicleType": "FOUR_WHEELER",
       "vehicleStatus": "AVAILABLE",
       "branchId": 1
   }
   ```

2. /vehicles (GET)

   Input: No input is required.

   Output: A list of `VehicleDto` objects with the details of all vehicles. For example:
   ```json
   [
       {
           "id": 1,
           "manufacturer": "Toyota",
           "model": "Camry",
           "year": 2018,
           "licensePlateNumber": "ABC-123",
           "currentMileage": 50000.0,
           "vehicleType": "FOUR_WHEELER",
           "vehicleStatus": "AVAILABLE",
           "branchId": 1
       },
       {
           "id": 2,
           "manufacturer": "Honda",
           "model": "Civic",
           "year": 2019,
           "licensePlateNumber": "XYZ-456",
           "currentMileage": 40000.0,
           "vehicleType": "FOUR_WHEELER",
           "vehicleStatus": "RENTED",
           "branchId": 2
       }
   ]
   ```

3. /vehicles (POST)

   Input: A `VehicleDto` object without an ID. For example:
   ```json
   {
       "manufacturer": "Toyota",
       "model": "Camry",
       "year": 2018,
       "licensePlateNumber": "ABC-123",
       "currentMileage": 50000.0,
       "vehicleType": "FOUR_WHEELER",
       "vehicleStatus": "AVAILABLE",
       "branchId": 1
   }
   ```

   Output: A `VehicleDto` object with the details of the created vehicle, including the generated ID. For example:
   ```json
   {
       "id": 1,
       "manufacturer": "Toyota",
       "model": "Camry",
       "year": 2018,
       "licensePlateNumber": "ABC-123",
       "currentMileage": 50000.0,
       "vehicleType": "FOUR_WHEELER",
       "vehicleStatus": "AVAILABLE",
       "branchId": 1
   }
   ```

4. /vehicles/{id} (PUT)

   Input: A `VehicleDto` object with an ID. For example:
   ```json
   {
       "id": 1,
       "manufacturer": "Toyota",
       "model": "Camry",
       "year": 2018,
       "licensePlateNumber": "ABC-123",
       "currentMileage": 50000.0,
       "vehicleType": "FOUR_WHEELER",
       "vehicleStatus": "AVAILABLE",
       "branchId": 1
   }
   ```

   Output: A `VehicleDto` object with the details of the updated vehicle. For example:
   ```json
   {
       "id": 1,
       "manufacturer": "Toyota",
       "model": "Camry",
       "year": 2018,
       "licensePlateNumber": "ABC-123",
       "currentMileage": 50000.0,
       "vehicleType": "FOUR_WHEELER",
       "vehicleStatus": "AVAILABLE",
       "branchId": 1
   }
   ```

5. /vehicles/{id} (DELETE)

   Input: An ID of an existing vehicle, for example `1`.

   Output: A `VehicleDto` object with the details of the deleted vehicle. For example:
   ```json
   {
       "id": 1,
       "manufacturer": "Toyota",
       "model": "Camry",
       "year": 2018,
       "licensePlateNumber": "ABC-123",
       "currentMileage": 50000.0,
       "vehicleType": "FOUR_WHEELER",
       "vehicleStatus": "AVAILABLE",
       "branchId": 1
   }
   ```

6. /vehicles/type (GET)

   Input: An ID of an existing branch, for example `1`.

   Output: A list of `VehicleDto` objects with the details of all vehicles in the given branch. For example:
   ```json
   [
       {
           "id": 1,
           "manufacturer": "Toyota",
           "model": "Camry",
           "year": 2018,
           "licensePlateNumber": "ABC-123",
           "currentMileage": 50000.0,
           "vehicleType": "FOUR_WHEELER",
           "vehicleStatus": "AVAILABLE",
           "branchId": 1
       },
       {
           "id": 2,
           "manufacturer": "Honda",
           "model": "Civic",
           "year": 2019,
           "licensePlateNumber": "XYZ-456",
           "currentMileage": 40000.0,
           "vehicleType": "FOUR_WHEELER",
           "vehicleStatus": "RENTED",
           "branchId": 1
       }
   ]
   ```



