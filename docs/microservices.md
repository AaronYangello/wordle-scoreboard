# Wordle Scoreboard Microservices

1. **Authentication Service**:
	* Handles user registration, login, and authentication
	* Uses OAuth 2.0 for Facebook authentication
	* Stores user credentials and authentication tokens
	* Provides authentication APIs for other services
	* Technology: Node.js, Express.js, Passport.js
2. **Score Service**:
	* Handles score input, storage, and retrieval
	* Provides APIs for creating, reading, updating, and deleting scores
	* Uses a database (e.g., MySQL, PostgreSQL) for storing scores
	* Technology: Node.js, Express.js, Sequelize
3. **Leaderboard Service**:
	* Handles leaderboard calculations and retrieval
	* Provides APIs for retrieving top scores and rankings
	* Uses the Score Service for data retrieval
	* Technology: Node.js, Express.js, Redis
4. **Followers Service**:
	* Handles following and unfollowing actions without requiring approval
	* Provides APIs for creating, reading, updating, and deleting follow relationships
	* Uses the Authentication Service for user authentication
	* Technology: Node.js, Express.js, Sequelize
5. **Feed Service**:
   * Provides a real-time feed of Wordle scores as they are entered
   * Streams updates to subscribed users through WebSockets or Server-Sent Events (SSE)
   * Uses the Score Service for retrieving and broadcasting scores
   * Supports filtering feeds by followed users or specific criteria
   * Technology: Node.js, Express.js, WebSockets/SSE, Redis
6. **Web App Service**:
	* Handles web app requests and responses
	* Uses the Authentication Service for user authentication
	* Uses the Score Service, Leaderboard Service, and Friendship Service for data retrieval
	* Technology: Node.js, Express.js, React.js