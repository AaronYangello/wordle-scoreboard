# Wordle Scoreboard Database Design

**1. Users Table**

* `id` (primary key, unique identifier for each user)
* `username` (unique username chosen by the user)
* `email` (email address associated with the user's account)
* `facebook_id` (Facebook ID, if the user signed up with Facebook)
* `password_hash` (hashed password for email sign-ups)
* `created_at` (timestamp when the user account was created)
* `updated_at` (timestamp when the user account was last updated)

**2. Wordles Table**

* `id` (primary key, unique identifier for each Wordle)
* `wordle_number` (unique identifier for each Wordle, e.g., 1, 2, 3, etc.)
* `answer` (correct answer for the Wordle)
* `created_at` (timestamp when the Wordle was created)
* `updated_at` (timestamp when the Wordle was last updated)

**3. Scores Table**

* `id` (primary key, unique identifier for each score)
* `user_id` (foreign key referencing the Users table)
* `wordle_id` (foreign key referencing the Wordles table)
* `score` (calculated score based on the number of guesses and streak bonus)
* `guesses` (number of guesses taken to solve the puzzle)
* `streak` (current streak of consecutive days with a correct solution)
* `created_at` (timestamp when the score was recorded)
* `updated_at` (timestamp when the score was last updated)

**4. Friendships Table**

* `id` (primary key, unique identifier for each friendship)
* `user_id` (foreign key referencing the Users table)
* `friend_id` (foreign key referencing the Users table)
* `status` (status of the friendship, e.g., "pending", "accepted", "declined")
* `created_at` (timestamp when the friendship was created)
* `updated_at` (timestamp when the friendship was last updated)

**5. Notifications Table**

* `id` (primary key, unique identifier for each notification)
* `user_id` (foreign key referencing the Users table)
* `notification_type` (type of notification, e.g., "friend request", "new high score")
* `message` (text message associated with the notification)
* `read_at` (timestamp when the notification was read, or null if unread)
* `created_at` (timestamp when the notification was created)
* `updated_at` (timestamp when the notification was last updated)

This updated design includes the Wordles table to store the Wordle number and associated answer for all previous days. The Scores table now references the Wordles table to associate each score with a specific Wordle.

## Entity-Relationship Diagram
```
+---------------+
|  Users      |
+---------------+
|  id (PK)    |
|  username    |
|  email       |
|  facebook_id |
|  password_hash|
+---------------+
       |
       | 1:N
       v
+---------------+
|  Scores     |
+---------------+
|  id (PK)    |
|  user_id (FK)|
|  wordle_id (FK)|
|  score       |
|  guesses     |
|  streak      |
+---------------+
       |
       | 1:N
       v
+---------------+
|  Wordles    |
+---------------+
|  id (PK)    |
|  wordle_number|
|  answer      |
+---------------+
       |
       | 1:N
       v
+---------------+
|  Friendships |
+---------------+
|  id (PK)    |
|  user_id (FK)|
|  friend_id (FK)|
|  status      |
+---------------+
       |
       | 1:N
       v
+---------------+
|  Notifications|
+---------------+
|  id (PK)    |
|  user_id (FK)|
|  notification_type|
|  message     |
|  read_at     |
+---------------+
```