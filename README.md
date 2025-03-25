# Mini Social Media Platform

- project developed during the **Object-Oriented Programming** course in my second year at *University Politehnica of Bucharest*, *Faculty of Automatic Control and Computers*.


## Description

- Every implemented command can be seen in `task/CommandsDescription.pdf`.
- Users must create an account in order to gain access to its capabilities.
- **ArrayLists** are used to store data such as *users* or *comments*.
- Project consists of four main *classes* and one *interface*:
    - `App class` holds *database* information and calls the right functions based on the given input
    - `User class` holds *users* infomration
    - `Post class` holds *posts* information
    - `Comment class` holds *comments* information
    - `Likeable interface` implements the like() and unlike() methods, which were later overridden in the `Post` and `Comment` classes, as only posts and comments can receive likes


## Detailed implementation

### User Management

To create a user, the username and password were extracted from the arguments and used to instantiate a `User` object. An `ArrayList` named `userArray` was also created to store all users, ensuring better data management. Each user has an `ArrayList` of posts as an attribute of the `User` class to track ownership. Additionally, an `ArrayList` containing all posts and another containing all comments were defined in the `App` class to maintain a complete record of posts and comments on the social media platform.

### Post and Comment Management

When creating or deleting a post, the post was added or removed from both the `ArrayList` containing all posts and the corresponding user’s post list. Similarly, when adding or deleting a comment on a specific post, an `ArrayList` named `postCommentArray` was created in the `Post` class to store comments for each post. Another `ArrayList`, `userCommentArray`, was created in the `User` class to track the comments created by each user. The comment was then added or removed from both the corresponding post’s comment list and the platform-wide comment list.

Each post and comment were assigned a unique `id`, declared as an attribute in the `Post` and `Comment` classes, which was incremented sequentially.

### Follow and Unfollow Functionality

In the `User` class, an `ArrayList` was implemented to store the users that a given user follows and another for the users following that user. When a user follows another user, their username is added to the target user’s `followersArray`, and the target user is added to the follower’s `followingArray`. The same logic applies to unfollowing a user.

### Like and Unlike Mechanism

For liking and unliking posts and comments, an `ArrayList` was created for each user to store the posts and comments they have liked. The `like()` and `unlike()` methods were overridden in the `Post` and `Comment` classes, where the number of likes (an attribute of the `Likeable` interface) was incremented or decremented accordingly.

### Displaying Posts

To print the posts of followed users or the posts of a specific user in descending order by date, the relevant details were displayed by iterating through the corresponding `ArrayList` in reverse order (from the highest to the lowest index). Since posts are added to the `ArrayList` in chronological order, reversing the index results in displaying the most recent posts first. The methods `printFollowingPosts()` and `printUserPosts()` in the `App` class were used for this purpose.

### Displaying Post Details

Post details were displayed following the required format using the `printPostDetails()` method.

### Displaying Followers and Followings

To display the followers or followings of a specific user, the usernames in the `followingArray` and `followersArray` were printed using the `printFollowingArray()` and `printFollowersArray()` methods in the `App` class.

### Top 5 Rankings

- **Most Liked Posts**: The list of all posts was sorted in descending order (based on the number of likes) and details of the top five posts were displayed.

- **Most Commented Posts**: The `Post` class includes an attribute `nrComments`, which was incremented or decremented each time a comment was added or removed. The posts were then sorted in descending order based on the number of comments, and details of the top five were displayed.

- **Most Followed Users**: The `User` class contains an attribute `nrFollowers`, which was updated each time a follower was added or removed. The list of users was sorted in descending order based on the number of followers, and details of the top five were displayed.

- **Most Liked Users**: The `User` class includes the attribute `nrTotalLikes`, which stores the sum of likes received across all posts and comments created by a user. The users were sorted in descending order based on total likes, and details of the top five were displayed.

### Cleanup Operation

The `cleanup-all` operation clears all `ArrayList` objects storing *users*, *posts*, and *comments* on the platform and resets the *IDs* assigned to posts and comments.
