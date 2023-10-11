Sam Fath, Justin Martz, Jay Perkins, Alex Apakidze

# Duality

![](Duality.png)


# Overview

Try Duality here (desktop only) - http://3.131.63.12:8080/Duality

A new awareness in the general space has opened that is catering to well being and general mental health. “Duality” is specifically for individuals who struggle with bipolar disorder. Bipolar disorder can be debilitating for some in day to day life and can be hard for individuals to track and find patterns, as well as it can make them feel isolated. With “Duality” we saw the potential struggles for bipolar individuals and built an application around them. Duality allows users to track their daily moods and attitudes using a daily mental well being test. The data is then tracked and saved on an interactive calendar where users can then see how they compare to past days and can in turn find general patterns for a potential upscale in their lifestyles. We also created a forum for our users to help with the feelings of loneliness that can accompany such a disorder. On the forums users are able to create posts and comment on others posts. Our hope in building duality is to create an application that adds to the mental health sphere in a positive way and can inspire those going through it, to find peace.

# Technologies
Java, Spring Boot, Spring Data, SQL, REST, DOM, AJAX, XHR, JSON, Javascript, Typescript, Angular, HTML, Bootstrap, Agile

# Description 
When a potential user come to the home, they are directed to log in or to register an account.
When they are logged in they are brought to their profile page that also shows their personal information, and allows them to update their information. 
On their profile they are also greeted by the calendar where they can track their general mental state and past days of data.



# Lessons Learned
Creating an application that had simplified features while making sure it was also viable in a real world scenario was a challenge. We wanted the interface to be simple, as people struggling with Bipolar Disorder can get overwhelmed. In the end I do believe we created a calming interface, with a balanced effective application. One of the biggest challenges was implementing Angular, as our experience with it has been limited, and mapping enough so that we could update our users information without deleting it.


# REST Endpoints

| HTTP Verb | URI                      | Request Body | Response Body |
|-----------|--------------------------|--------------|---------------|
| GET    | `/authenticate/` | Representation of authenticating a user | Get an authenticated user |
| GET    | `api/behaviors/` | Representation of getting a behavior | Get a behavior |
| GET    | `api/behaviors/{id}` | Representation of getting a behavior by id | Get a behavior by id |
| GET    | `/api/behaviorReports` | Representation of getting a behavior report | Get a behavior report | 
| GET    | `/api/comments/posts/{postId}` | Representation of getting a comment by post |  Get a comment by id |   
| POST   | `/register/` | Representation of registering a new user | Post a new user |
| POST   | `/api/behaviorReports/month` | Representation of posting a behavior report by month  |  Post a behavior report by month
| PUT | `/api/users/{id}` | Representation of updating | Representation of updating a users' information | Update a users' information | 
| DELETE | `/api/behaviorReports` | Representation of deleting a behavior report | Delete a behavior report |      