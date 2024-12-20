# Wordle Scoreboard Requirements

**1. User Authentication**

* The web app shall provide two methods for user registration:
	+ Facebook sign-up: users can register using their Facebook account
	+ Email sign-up: users can register using a unique email address and password
* During Facebook sign-up, the user shall be prompted to choose a unique username, with their Facebook username as the default if available
* Usernames must be unique across the platform
* The web app shall store user authentication data securely, using industry-standard encryption and password hashing

**2. Score Calculation**

* The web app shall calculate user scores based on the number of guesses and a streak bonus
* The streak bonus shall be calculated manually using a laddered approach:
	+ Less than 10 days: +0
	+ 10-30 days: +10
	+ 30-50 days: +15
	+ 50-100 days: +20
	+ 100-200 days: +25
	+ 200-300 days: +28
	+ 300+ days: +30
* The score calculation formula shall be: (6 - number of guesses) * 10 + streak bonus

**3. Friend System**

* The web app shall allow users to add friends based on username
* The web app shall suggest potential Facebook friends if their Facebook account is linked
* Friend requests shall be sent to the user's email if they've signed up for email notifications
* Users shall be able to accept or decline friend requests

**4. Scoreboard and Feed**

* The web app shall display a worldwide scoreboard, showing the top 6 users with their username and score
* The web app shall display a worldwide feed, showing real-time updates of user scores
* Users shall be able to view their friend's scoreboard and feed
* The scoreboard shall be sorted by score, with longest streak as a tiebreaker, and then alphabetically by username
* The web app shall display a "Show All Rankings" button, which shall dynamically load all user rankings on the same page

**5. Notifications**

* The web app shall provide a notifications system, which shall display a count of unread notifications
* Users shall be able to opt-in to receive email notifications for all events (friend requests, new high scores, streak milestones)
* Email notifications shall be sent to users who have opted-in, with a summary of the event and a link to the relevant page

**6. Design and Accessibility**

* The web app shall be optimized for mobile web, with a responsive design that adapts to different screen sizes and devices
* The web app shall match the design elements of wordle.com as closely as possible, including color scheme, typography, and layout
* The web app shall be accessible to users with disabilities, following Web Content Accessibility Guidelines (WCAG 2.1)

**7. Data Storage**

* The web app shall store data in a relational database
* The web app shall use a data buffer and locking mechanism to ensure data consistency and prevent corruption
* The web app shall handle data concurrency and synchronization to prevent data loss or corruption

**8. Error Handling**

* The web app shall display an error message if a user inputs a malformed score, explaining the correct format
* The web app shall not display an error message if a user's account is not linked to Facebook
* The web app shall handle other errors and exceptions, displaying user-friendly error messages and providing guidance on how to resolve the issue

**9. Security**

* The web app shall use industry-standard encryption and password hashing to store user authentication data securely
* The web app shall validate user input to prevent SQL injection and cross-site scripting (XSS) attacks
* The web app shall use secure protocols for data transmission, such as HTTPS

**10. Performance**

* The web app shall be optimized for performance, with fast page loads and responsive user interactions
* The web app shall use caching and other optimization techniques to minimize the load on the server and improve user experience

**11. Testing**

* The web app shall be thoroughly tested for functionality, security, and performance
* The web app shall be tested on different devices and browsers to ensure cross-browser compatibility
* The web app shall be tested for accessibility, following Web Content Accessibility Guidelines (WCAG 2.1)