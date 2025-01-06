# Wordle Scoreboard Rest Endpoints

**Authentication Microservice:**

1. **POST /register**: Create a new user account
	* Request Body: { username, email, password }
	* Response: { user_id, token }
2. **POST /login**: Login to an existing user account
	* Request Body: { username, password }
	* Response: { user_id, token }
3. **GET /users**: Get a list of all users
	* Response: [ { user_id, username, email } ]
4. **GET /users/{user_id}**: Get a specific user
	* Response: { user_id, username, email }
5. **PUT /users/{user_id}**: Update a specific user
	* Request Body: { username, email }
	* Response: { user_id, username, email }
6. **DELETE /users/{user_id}**: Delete a specific user
	* Response: { message: "User deleted" }
7. **POST /password/reset**: Reset a user's password
	* Request Body: { email }
	* Response: { message: "Password reset email sent" }
8. **POST /password/update**: Update a user's password
	* Request Body: { old_password, new_password }
	* Response: { message: "Password updated" }

**Score Microservice:**

1. **POST /scores**: Create a new score
	* Request Body: { user_id, game_id, score }
	* Response: { score_id, user_id, game_id, score }
2. **GET /scores**: Get a list of all scores
	* Response: [ { score_id, user_id, game_id, score } ]
3. **GET /scores/{score_id}**: Get a specific score
	* Response: { score_id, user_id, game_id, score }
4. **PUT /scores/{score_id}**: Update a specific score
	* Request Body: { score }
	* Response: { score_id, user_id, game_id, score }
5. **DELETE /scores/{score_id}**: Delete a specific score
	* Response: { message: "Score deleted" }
6. **GET /scores/users/{user_id}**: Get a list of scores for a specific user
	* Response: [ { score_id, user_id, game_id, score } ]
7. **GET /scores/games/{game_id}**: Get a list of scores for a specific game
	* Response: [ { score_id, user_id, game_id, score } ]

**Leaderboard Microservice:**

1. **POST /leaderboards**: Create a new leaderboard
	* Request Body: { game_id, leaderboard_name }
	* Response: { leaderboard_id, game_id, leaderboard_name }
2. **GET /leaderboards**: Get a list of all leaderboards
	* Response: [ { leaderboard_id, game_id, leaderboard_name } ]
3. **GET /leaderboards/{leaderboard_id}**: Get a specific leaderboard
	* Response: { leaderboard_id, game_id, leaderboard_name }
4. **PUT /leaderboards/{leaderboard_id}**: Update a specific leaderboard
	* Request Body: { leaderboard_name }
	* Response: { leaderboard_id, game_id, leaderboard_name }
5. **DELETE /leaderboards/{leaderboard_id}**: Delete a specific leaderboard
	* Response: { message: "Leaderboard deleted" }
6. **GET /leaderboards/games/{game_id}**: Get a list of leaderboards for a specific game
	* Response: [ { leaderboard_id, game_id, leaderboard_name } ]
7. **GET /leaderboards/users/{user_id}**: Get a list of leaderboards for a specific user
	* Response: [ { leaderboard_id, game_id, leaderboard_name } ]

**Notification Microservice:**

1. **POST /notifications**: Create a new notification
	* Request Body: { user_id, notification_type, notification_message }
	* Response: { notification_id, user_id, notification_type, notification_message }
2. **GET /notifications**: Get a list of all notifications
	* Response: [ { notification_id, user_id, notification_type, notification_message } ]
3. **GET /notifications/{notification_id}**: Get a specific notification
	* Response: { notification_id, user_id, notification_type, notification_message }
4. **PUT /notifications/{notification_id}**: Update a specific notification
	* Request Body: { notification_message }
	* Response: { notification_id, user_id, notification_type, notification_message }
5. **DELETE /notifications/{notification_id}**: Delete a specific notification
	* Response: { message: "Notification deleted" }
6. **GET /notifications/users/{user_id}**: Get a list of notifications for a specific user
	* Response: [ { notification_id, user_id, notification_type, notification_message } ]

**Friendship Microservice:**

1. **POST /friendships**: Create a new friendship
	* Request Body: { user_id, friend_id }
	* Response: { friendship_id, user_id, friend_id }
2. **GET /friendships**: Get a list of all friendships
	* Response: [ { friendship_id, user_id, friend_id } ]
3. **GET /friendships/{friendship_id}**: Get a specific friendship
	* Response: { friendship_id, user_id, friend_id }
4. **PUT /friendships/{friendship_id}**: Update a specific friendship
	* Request Body: { friend_id }
	* Response: { friendship_id, user_id, friend_id }
5. **DELETE /friendships/{friendship_id}**: Delete a specific friendship
	* Response: { message: "Friendship deleted" }
6. **GET /friendships/users/{user_id}**: Get a list of friendships for a specific user
	* Response: [ { friendship_id, user_id, friend_id } ]

**Game Microservice:**

1. **POST /games**: Create a new game
	* Request Body: { game_name, game_type }
	* Response: { game_id, game_name, game_type }
2. **GET /games**: Get a list of all games
	* Response: [ { game_id, game_name, game_type } ]
3. **GET /games/{game_id}**: Get a specific game
	* Response: { game_id, game_name, game_type }
4. **PUT /games/{game_id}**: Update a specific game
	* Request Body: { game_name, game_type }
	* Response: { game_id, game_name, game_type }
5. **DELETE /games/{game_id}**: Delete a specific game
	* Response: { message: "Game deleted" }
6. **GET /games/types/{game_type}**: Get a list of games for a specific game type
	* Response: [ { game_id, game_name, game_type } ]