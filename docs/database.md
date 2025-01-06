# Wordle Scoreboard Database Design

**Authentication Microservice Database:**

* **Users Table:**
	+ id (primary key)
	+ username
       + facebook_id
	+ email
	+ password (hashed)
	+ role (e.g. admin, user)
* **Sessions Table:**
	+ id (primary key)
	+ user_id (foreign key referencing the Users table)
	+ session_token
	+ expires_at
* **Roles Table:**
	+ id (primary key)
	+ role_name

**Score Microservice Database:**

* **Scores Table:**
	+ id (primary key)
	+ user_id (foreign key referencing the Users table in the Authentication Microservice database)
	+ score
	+ game_id (foreign key referencing the Games table in the Game Microservice database)
	+ created_at
	+ updated_at
* **Games Table:**
	+ id (primary key)
	+ game_name
	+ created_at
	+ updated_at

**Leaderboard Microservice Database:**

* **Leaderboards Table:**
	+ id (primary key)
	+ game_id (foreign key referencing the Games table in the Game Microservice database)
	+ leaderboard_name
	+ leaderboard_type (e.g. daily, weekly, monthly)
	+ created_at
	+ updated_at
* **Leaderboard Entries Table:**
	+ id (primary key)
	+ leaderboard_id (foreign key referencing the Leaderboards table)
	+ user_id (foreign key referencing the Users table in the Authentication Microservice database)
	+ score
	+ rank
	+ created_at
	+ updated_at

**Notification Microservice Database:**

* **Notifications Table:**
	+ id (primary key)
	+ user_id (foreign key referencing the Users table in the Authentication Microservice database)
	+ notification_type (e.g. email, SMS)
	+ notification_message
	+ created_at
	+ updated_at
* **Notification Settings Table:**
	+ id (primary key)
	+ user_id (foreign key referencing the Users table in the Authentication Microservice database)
	+ notification_type (e.g. email, SMS)
	+ notification_frequency (e.g. daily, weekly, monthly)
	+ created_at
	+ updated_at

**Friendship Microservice Database:**

* **Friendships Table:**
	+ id (primary key)
	+ user_id (foreign key referencing the Users table in the Authentication Microservice database)
	+ friend_id (foreign key referencing the Users table in the Authentication Microservice database)
	+ friendship_status (e.g. pending, accepted, rejected)
	+ created_at
	+ updated_at
* **Friendship Requests Table:**
	+ id (primary key)
	+ user_id (foreign key referencing the Users table in the Authentication Microservice database)
	+ friend_id (foreign key referencing the Users table in the Authentication Microservice database)
	+ request_status (e.g. pending, accepted, rejected)
	+ created_at
	+ updated_at

**Game Microservice Database:**

* **Games Table:**
	+ id (primary key)
	+ game_name
	+ game_type (e.g. Wordle, Crossword)
	+ created_at
	+ updated_at
* **Game Settings Table:**
	+ id (primary key)
	+ game_id (foreign key referencing the Games table)
	+ setting_name
	+ setting_value
	+ created_at
	+ updated_at

**Database Relationships:**

* The Users table in the Authentication Microservice database has a one-to-many relationship with the Scores table in the Score Microservice database.
* The Scores table in the Score Microservice database has a many-to-one relationship with the Games table in the Game Microservice database.
* The Leaderboards table in the Leaderboard Microservice database has a one-to-many relationship with the Leaderboard Entries table.
* The Leaderboard Entries table has a many-to-one relationship with the Leaderboards table and the Users table in the Authentication Microservice database.
* The Notifications table in the Notification Microservice database has a many-to-one relationship with the Users table in the Authentication Microservice database.
* The Friendships table in the Friendship Microservice database has a many-to-many relationship with the Users table in the Authentication Microservice database.