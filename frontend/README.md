# HotelReservationFront

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 16.2.16.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via a platform of your choice. To use this command, you need to first add a package that implements end-to-end testing capabilities.

## Adding a Default Admin User

To add a default admin user (`abdo@gmail.com` with the password `1999`), you need to ensure that this user is automatically created when the application starts. This functionality is typically implemented in the backend (Spring Boot).

### Backend Steps (Spring Boot)
1. **Admin User Creation**: When the backend application starts, a `CommandLineRunner` will insert the default admin user into the database if it does not already exist. The admin user will have the following details:
  - **Email**: `abdo@gmail.com`
  - **Password**: `1999` (hashed with bcrypt)

2. **Ensure the User is Created**: Ensure that the `AdminUserLoader` class is implemented and properly sets up the default admin user during the backend startup. The user will be inserted into the `reservation_hotel` database.
