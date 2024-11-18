# Car Whisperer App
A mobile application that allows users to search for car parts, view detailed product information, and manage their shopping cart. The app supports user authentication, product search, and cart management using Java and Room Database.

## Features
User Authentication:

Register a new account with a username, password, and email.
Log in securely with stored credentials.

## Product Search:

Search for car parts using keywords.
View detailed information about each product, including name, price, brand, and image.

## Shopping Cart:

Add products to the shopping cart.
Update quantities of products in the cart.
Calculate and display the total price of items in the cart.

## Local Database:

Manage user accounts, products, and cart items locally using Room Database.

## Technologies Used:

Android Studio: IDE for Android development.
Java: Programming language used for the app.
Room Database: Local database for managing data.
Retrofit (optional): For API integration, if needed for product data.
Firebase Authentication (optional): For user authentication.

# Getting Started

## Prerequisites

Android Studio (latest version)
Android SDK and Emulator setup
Basic knowledge of Java and Android development

# Setup Instructions

## Clone the repository:

git clone https://github.com/yourusername/CarPartsApp.git
Open the project in Android Studio.
Sync the Gradle files to download dependencies.
Run the project on an emulator or physical device.

## Project Structure

src/
├── main/
│   ├── java/com/example/carpartsapp/
│   │   ├── database/
│   │   │   ├── AppDatabase.java          # Room Database setup
│   │   │   ├── entities/
│   │   │   │   ├── User.java             # User table
│   │   │   │   ├── Product.java          # Product table
│   │   │   │   ├── CartItem.java         # Cart item table
│   │   │   ├── dao/
│   │   │   │   ├── UserDao.java          # User DAO
│   │   │   │   ├── ProductDao.java       # Product DAO
│   │   │   │   ├── CartDao.java          # Cart DAO
│   │   ├── ui/
│   │   │   ├── LoginActivity.java        # Login screen
│   │   │   ├── RegisterActivity.java     # Registration screen
│   │   │   ├── ProductSearchActivity.java # Search functionality
│   │   │   ├── CartActivity.java         # Shopping cart management
│   ├── res/
│   │   ├── layout/                       # XML files for UI design
│   │   ├── drawable/                     # Image assets
│   │   ├── values/                       # Colors, styles, and strings

Database Schema
User Table
Column	Type	Description
userId	INT	Primary key, auto-increment
username	TEXT	Unique username
password	TEXT	User password
email	TEXT	User email address
Product Table
Column	Type	Description
productId	INT	Primary key, auto-increment
name	TEXT	Product name
price	DOUBLE	Product price
brand	TEXT	Product brand
imageUrl	TEXT	Product image URL
details	TEXT	Product details
CartItem Table
Column	Type	Description
cartItemId	INT	Primary key, auto-increment
userId	INT	Foreign key (User)
productId	INT	Foreign key (Product)
quantity	INT	Number of items

## How to Use
Register or Log In:

New users must create an account.
Existing users can log in using their credentials.
Search for Products:

Enter keywords to search for products in the database.
View Product Details:

Click on a product to view detailed information.
Add to Cart:

Add desired products to your shopping cart.
Update quantities as needed.
Checkout (optional):

View the total price of the items in the cart (further integration required for payment).
Future Enhancements
Add online product data fetching using APIs.
Implement secure authentication using Firebase Authentication.
Include payment gateway integration for in-app purchases.
Add push notifications for updates and promotions.
