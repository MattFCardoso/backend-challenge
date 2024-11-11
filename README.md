# PicPay Challenge

This is a **PicPay Challenge** implemented in **Java** and **Spring Boot**.

## Goal

The goal of this project is to create a simplified payment platform similar to PicPay. Users can deposit and transfer money between themselves and merchants. There are two types of users in the system: **ordinary users** and **retailers**. Both have wallets to store money and can perform transfers.

## Features

- **Users**: 
  - Both ordinary users and retailers have wallets with a balance.
  - Users can transfer money to other users or merchants.
  - Retailers **only receive transfers** and **do not initiate transfers**.

- **User Registration**:
  - The system requires the following details for registration:
    - **Full Name**
    - **CPF** (Cadastro de Pessoas Físicas) or **CNPJ** (Cadastro Nacional da Pessoa Jurídica) for merchants
    - **Email**
    - **Password**
  - **Unique Constraints**: Both CPF/CNPJ and emails must be unique across the system. Therefore, the system should not allow multiple registrations with the same CPF or email address.

- **Transfer Process**:
  - **Transfer Validation**: Before processing a transfer, the system checks whether the user has sufficient balance in their wallet.
  - **Authorization**: Transfers must be validated by an external authorization service (mocked by a REST API).
    - Use the mock endpoint: `GET https://util.devi.tools/api/v2/authorize` to simulate the authorization.
  - **Transaction Integrity**: Transfers should be handled as **transactions**, meaning that if any inconsistency occurs, the transfer should be **reverted** and the money should be returned to the sender’s wallet.
  
- **Notification**:
  - Upon receiving a transfer, the user or retailer must be notified via a third-party service (email, SMS, etc.).
  - Use the mock endpoint `POST https://util.devi.tools/api/v1/notify` to simulate the notification service. Be aware that the notification service might be unstable or unavailable at times.

## Requirements

- Implement a **RESTful API** that satisfies the requirements described above.
- Use **Spring Boot** to create the application.
- Ensure **transactional integrity** when processing transfers.

## External Services

- **Authorization Service**: [https://util.devi.tools/api/v2/authorize](https://util.devi.tools/api/v2/authorize)
- **Notification Service**: [https://util.devi.tools/api/v1/notify](https://util.devi.tools/api/v1/notify)

## Docker Instructions

You can build and run this application using Docker.

### Prerequisites

Make sure you have **Docker** installed on your machine. You can follow the installation guide from the official Docker website: [Docker Installation Guide](https://docs.docker.com/get-docker/).

### Building the Docker Image

1. Clone this repository:
   ```bash
   git clone git@github.com:MattFCardoso/backend-challenge.git
   ```

2. Navigate to the project directory:
   ```bash
   cd backend-challenge
   ```

3. Build the Docker image:
   ```bash
   docker build -t picpay:1.0 .
   ```

### Running the Docker Container

Once the image is built, you can run the application in a Docker container:

1. Run the container:
   ```bash
   docker run -p 8080:8080 backend-challenge
   ```

   This will expose the application on port `8080` of your localhost.

2. Access the application in your browser:
   - Visit `http://localhost:8080` to interact with the application.

### Stopping the Container

To stop the Docker container, press `Ctrl+C` or use the following command:

```bash
docker stop <container_id>
```

You can find the `<container_id>` by running:

```bash
docker ps
```

---

## Getting Started Locally

If you prefer to run the application locally without Docker, you can follow these steps:

1. Clone this repository:
   ```bash
   git clone git@github.com:MattFCardoso/backend-challenge.git
   ```

2. Navigate to the project directory:
   ```bash
   cd backend-challenge
   ```

3. Build and run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

4. The application will be available at `http://localhost:8080`.

## API Endpoints

### User Registration

- `POST /users`: Register a new user (either ordinary or retailer).
- **Request body**:
  ```json
  {
    "firstName": "John",
  "lastName: "Doe",
    "cpf": "12345678900",
    "email": "johndoe@example.com",
    "password": "password123",
  "userType: "COMMON"
  }
  ```

### Transfer Money

- `POST /transactions: Transfer money from one user to another (or merchant).
- **Request body**:
  ```json
  {
    "senderId": "1",
    "receiverId": "2",
    "amount": 50.00
  }
  ```
