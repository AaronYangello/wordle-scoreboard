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
4. **Notification Service**:
	* Handles notifications for friend requests, new high scores, and other events
	* Uses a message queue (e.g., RabbitMQ, Apache Kafka) for notification processing
	* Provides APIs for sending and retrieving notifications
	* Technology: Node.js, Express.js, RabbitMQ
5. **Friendship Service**:
	* Handles friend requests, acceptances, and rejections
	* Provides APIs for creating, reading, updating, and deleting friendships
	* Uses the Authentication Service for user authentication
	* Technology: Node.js, Express.js, Sequelize
6. **Web App Service**:
	* Handles web app requests and responses
	* Uses the Authentication Service for user authentication
	* Uses the Score Service, Leaderboard Service, and Friendship Service for data retrieval
	* Technology: Node.js, Express.js, React.js