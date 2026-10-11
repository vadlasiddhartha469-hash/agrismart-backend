# AgriSmart – Smart Agriculture Management and Crop Recommendation System

AgriSmart is a React + Spring Boot + MySQL full-stack application for centralized farm management and agricultural decision support.

## Website features
- Secure registration and JWT-based login
- Dashboard with farms, active crops, irrigation activities, expenses, expected revenue and expected profit
- Farm management: add, edit and delete farms
- Crop management: add, edit and delete crops, sowing/harvest dates, expected yield and revenue
- Crop recommendation using soil type, water availability and season
- Fertilizer recommendation and fertilizer application records
- Irrigation recommendation and irrigation scheduling records
- Expense management and total expense tracking
- Farm-wise expected revenue, expenses and profit estimation
- Responsive React interface for desktop and mobile

## Technology
- Frontend: React.js + Vite + Axios
- Backend: Java 17+ / Spring Boot 3.5.6 / REST APIs / Spring Data JPA / JWT
- Database: MySQL

## Run on Windows
1. Start MySQL.
2. Open the `AgriSmart` folder in VS Code.
3. Backend terminal:
   ```powershell
   cd backend
   .\mvnw.cmd spring-boot:run
   ```
4. Frontend terminal:
   ```powershell
   cd frontend
   npm.cmd install
   npm.cmd run dev
   ```
5. Open the `Local` URL printed by Vite. If port 5173 is busy, Vite may use 5174 or another available port.

You can also double-click `start-agrismart.bat` after MySQL is running.

## Database
The backend uses `agrismart` by default and automatically creates/updates tables using Hibernate (`ddl-auto=update`). `database.sql` is provided for manual setup/reference.

## Important note
Crop, fertilizer and irrigation recommendations are starter rule-based recommendations. Exact fertilizer dose and irrigation should be adjusted using soil tests, weather, crop stage and local agricultural guidance.
