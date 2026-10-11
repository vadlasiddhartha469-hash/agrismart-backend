-- AgriSmart MySQL Database Schema
-- Note: Spring Boot automatically creates and updates these tables on startup 
-- via 'spring.jpa.hibernate.ddl-auto=update'. You can also run this script manually.

CREATE DATABASE IF NOT EXISTS agrismart;
USE agrismart;

CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS farms (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    farm_name VARCHAR(255),
    location VARCHAR(255),
    area DOUBLE,
    soil_type VARCHAR(255),
    water_availability VARCHAR(255),
    user_id BIGINT,
    CONSTRAINT fk_farms_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS crops (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    crop_name VARCHAR(255),
    status VARCHAR(100),
    sowing_date DATE,
    expected_harvest_date DATE,
    expected_yield DOUBLE,
    expected_revenue DOUBLE,
    farm_id BIGINT,
    CONSTRAINT fk_crops_farm FOREIGN KEY (farm_id) REFERENCES farms(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS fertilizers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fertilizer_name VARCHAR(255),
    quantity DOUBLE,
    application_date DATE,
    crop_id BIGINT,
    CONSTRAINT fk_fertilizers_crop FOREIGN KEY (crop_id) REFERENCES crops(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS irrigation (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    irrigation_date DATE,
    water_quantity DOUBLE,
    method VARCHAR(100),
    crop_id BIGINT,
    CONSTRAINT fk_irrigation_crop FOREIGN KEY (crop_id) REFERENCES crops(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS expenses (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    category VARCHAR(255),
    amount DOUBLE,
    expense_date DATE,
    farm_id BIGINT,
    CONSTRAINT fk_expenses_farm FOREIGN KEY (farm_id) REFERENCES farms(id) ON DELETE CASCADE
);
