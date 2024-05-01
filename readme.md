# Car Rental Management System

## Description
In this project we have developed a comprehensive system for managing car rental operations, including vehicle records, customer information, rental transactions, and branch management. The system provides functionalities for creating, updating, retrieving, and deleting details about cars, customers, rentals, and branches.

## Authors
* [@arthurW1935](https://github.com/arthurW1935/)
* [@yashathwani](https://github.com/yashathwani)
* [@TUSHAR1651](https://github.com/TUSHAR1651)
* [@Souvik-Cyclic](https://github.com/Souvik-Cyclic)
* [@aannaannyaaa](https://github.com/aannaannyaaa)
* [@garvit420](https://github.com/garvit420)

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