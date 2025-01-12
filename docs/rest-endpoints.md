# Wordle Scoreboard Rest Endpoints

## Authentication Microservice

1. **POST /register**: Create a new user account
	* Request Body: `{ username, email, password }`
	* Response: `{ user_id, token }`
2. **POST /login**: Login to an existing user account
	* Request Body: `{ username, password }`
	* Response: `{ user_id, token }`
3. **GET /users**: Get a list of all users
	* Response: `[ { user_id, username, email } ]`
4. **GET /users/{user_id}**: Get a specific user
	* Response: `{ user_id, username, email }`
5. **PUT /users/{user_id}**: Update a specific user
	* Request Body: `{ username, email }`
	* Response: `{ user_id, username, email }`
6. **DELETE /users/{user_id}**: Delete a specific user
	* Response: `{ message: "User deleted" }`
7. **POST /password/reset**: Reset a user's password
	* Request Body: `{ email }`
	* Response: `{ message: "Password reset email sent" }`
8. **POST /password/update**: Update a user's password
	* Request Body: `{ old_password, new_password }`
	* Response: `{ message: "Password updated" }`

## Score Microservice

1. **POST /scores**: Create a new score
	* Request Body: `{ user_id, game_id, score }`
	* Response: `{ score_id, user_id, game_id, score }`
2. **GET /scores**: Get a list of all scores
	* Response: `[ { score_id, user_id, game_id, score } ]`
3. **GET /scores/{score_id}**: Get a specific score
	* Response: `{ score_id, user_id, game_id, score }`
4. **PUT /scores/{score_id}**: Update a specific score
	* Request Body: `{ score }`
	* Response: `{ score_id, user_id, game_id, score }`
5. **DELETE /scores/{score_id}**: Delete a specific score
	* Response: `{ message: "Score deleted" }`
6. **GET /scores/users/{user_id}**: Get a list of scores for a specific user
	* Response: `[ { score_id, user_id, game_id, score } ]`
7. **GET /scores/games/{game_id}**: Get a list of scores for a specific game
	* Response: `[ { score_id, user_id, game_id, score } ]`

## Leaderboard Microservice

1. **POST /leaderboards**: Create a new leaderboard
	* Request Body: `{ owner_id }`
	* Response: `{ leaderboard_id, owner_id }`
2. **GET /leaderboards**: Get a list of all leaderboards
	* Response: `[ { leaderboard_id, owner_id, [ user_id ] } ]`
3. **GET /leaderboards/{leaderboard_id}**: Get a specific leaderboard
	* Response: `{ leaderboard_id, owner_id, [ user_id ] }`
4. **DELETE /leaderboards/{leaderboard_id}**: Delete a specific leaderboard
	* Response: `{ message: "Leaderboard deleted" }`
5. **PUT /leaderboards/{leaderboard_id}**: Add user to a specific leaderboard
	* Request Body: `{ user_id }`
	* Response: `{ leaderboard_id, owner_id, [ user_id ] }`
6. **DELETE /leaderboards/{leaderboard_id}/users/{user_id}**: Delete a user from a specific leaderboard
	* Response: `{ message: "User removed form leaderboard" }`


## Followers Microservice

1. **POST /followers**: Follow a user  
   * Request Body: `{ follower_id, followee_id }`  
   * Response: `{ follower_id, followee_id }`  
2. **GET /followers**: Get a list of all follower relationships  
   * Response: `[ { follower_id, followee_id } ]`  
3. **GET /followers/{follower_id}**: Get a specific follower relationship  
   * Response: `{ follower_id, followee_id }`  
4. **DELETE /followers/{follower_id}**: Unfollow a user (delete a follower relationship)  
   * Response: `{ message: "Follower relationship deleted" }`  

## Feed Microservice

1. **POST /feeds**: Create a new feed
   * Request Body: `{ owner_id }`  
   * Response: `{ feed_id, owner_id, [ user_id ], [post_id] }`  
2. **GET /feeds**: Get a list of all feeds
   * Response: `[ { feed_id, owner_id, [ user_id ], [post_id] } ]`  
3. **GET /feeds/{feed_id}**: Get a specific feed
   * Response: `{ feed_id, owner_id, [ user_id ], [post_id] }`
4. **PUT /feeds/{feed_id}**: Add user to a specific feed 
   * Request Body: `{ user_id }`  
   * Response: `{ feed_id, owner_id, [ user_id ], [post_id] }`
5. **DELETE /feeds/{feed_id}/users/{user_id}**: Delete a user from feed
   * Response: `{ message: "User removed from feed" }`  
6. **POST /feeds/{feed_id}/posts**: Create a new feed post
   * Request Body: `{ posting_user_id, content, tags }`  
   * Response: `{ post_id, feed_id, posting_user_id, content, tags, created_at }`  
7. **GET /feeds/{feed_id}/posts**: Get a list of all feed posts
   * Response: `[ { post_id, feed_id, posting_user_id, content, tags, created_at } ]`  
8. **GET /feeds/{feed_id}/posts/{post_id}**: Get a specific feed post
   * Response: `{ post_id, feed_id, posting_user_id, content, tags, created_at }`  
9. **PUT /feeds/{feed_id}/posts/{post_id}**: Update a specific feed post 
   * Request Body: `{ content, tags }`  
   * Response: `{ post_id, feed_id, posting_user_id, content, tags, created_at }`  
10. **DELETE /feeds/{feed_id}/posts/{post_id}**: Delete a specific feed post  
      * Response: `{ message: "Feed post deleted" }`  

### To be implemented later

11. **POST /feeds/{feed_id}/posts/{post_id}/like**: Like a specific feed post 
   * Request Body: `{ liking_user_id }`  
   * Response: `{ message: "Post liked", total_likes }`  
12. **POST /feeds/{feed_id}/posts/{post_id}/comment**: Add a comment to a specific feed post
   * Request Body: `{ commenting_user_id, comment }`  
   * Response: `{ comment_id, post_id, feed_id, commenting_user_id, comment, created_at }`  
13. **GET /feeds/{feed_id}/posts/{post_id}/comments**: Get all comments for a specific feed post  
   * Response: `[ { comment_id, post_id, feed_id, commenting_user_id, comment, created_at } ]`  
14. **GET /feeds/{feed_id}/tags/{tag}**: Get all feed posts with a specific tag  
    * Response: `[ { post_id, feed_id, posting_user_id, content, tags, created_at } ]`  
15. **POST /feeds/{feed_id}/report**: Report a specific feed post for review  
    * Request Body: `{ reporting_user_id, reason }`  
    * Response: `{ message: "Report submitted", report_id }`  