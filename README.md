# Payment Service

Payment Service is a web-based payment system inspired by payment terminals. It is not a real payment system but rather a practice project designed for learning purposes.

## Overview

The Payment Service project consists of two main Java applications: PaymentService and PaymentApiGateway, along with a React-based frontend.

### PaymentService

PaymentService acts as the core backend application responsible for handling payments. It provides APIs that allow different companies to process payments using various payment methods like bank card payments, credit transfers, and more.

#### Features

- **Company Management**: Store company details including logos and available payment methods.
- **Payment Processing**: Process payments using bank APIs for different payment types.
- **Dynamic API Integration**: PaymentService is designed to be integrated with multiple services via PaymentApiGateway.

### PaymentApiGateway

PaymentApiGateway serves as the gateway for PaymentService, enabling it to connect with various services. It routes requests from PaymentService to the appropriate endpoints and manages the flow of data.

### Frontend

The frontend of Payment Service is built using React and is inspired by the interface of payment terminals. It provides a user-friendly interface for interacting with the payment system.

## Installation

### Backend

1. Navigate to the `PaymentService` and `PaymentApiGateway` directories.
2. Run `mvn install` to install dependencies and build the projects.

### Frontend

1. Navigate to the `frontend` directory.
2. Run `npm install` to install dependencies.
3. Run `npm start` to start the React application.

## Usage

1. Start the backend services (`PaymentService` and `PaymentApiGateway`).
2. Open the frontend application in your browser.
3. Use the provided interface to manage companies, payment methods, and process payments.

## Contributing

Feel free to contribute to this project by creating pull requests or submitting issues.

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.
