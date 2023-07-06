[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/x6ckGcN8)
# 3500 PA05 Project Repo

[PA Write Up](https://markefontenot.notion.site/PA-05-8263d28a81a7473d8372c6579abd6481)

# Week View 
![Week View](/assets/images/WeekView.png)

# Pitch
The BulletJournal(JKN product) has the innovation that excites! Pertaining to any user's ability, this application 
allows access to the future, present, and past. The Bullet Journal is the best place to jot down notes, make 
checklists, and save affirmations. At the forefront of organization, we allow a copious collection of event and task 
creation. Stay connected to have the PERFECT form of bullet journaling with no risk of expense!

# Features.md
# Section 1
## Week View
The Week View on our bullet journal(JKN product) is a visual representation of all days of the week.
A user can create their planned events and intended tasks to complete on an associated day. Once this action is completed
the task/event will appear on our GUI view where the user will be able to locate their creation at the forefront of the 
user interface.
![Current Week View](/assets/images/CurrentWeekView.png)

## Event and Task Creation
Allowing the ability for the user to dynamically create and display, this section of our product programmatically 
displays each task/event creation on the Week View. By granting the user access to press "+"(add) in our Week 
View, the user can manually enter answers in associated fields. The event creation allows for a user
to set a name, description, day of the week, start time, and duration. In contrast, the task creation allows a user
to set a name, description, day of the week, and if it is complete. By doing so, a user can go throughout their week 
and check off their tasks as they complete them.
![Current Week View](/assets/images/WhatToAdd.png)
![Current Week View](/assets/images/AddEvent.png)
![Current Week View](/assets/images/AddTask.png)
![Current Week View](/assets/images/TasksAdded.png)

## Persistence
In regard to persistence, the user will be able to save the contents of their file by clicking the "save" icon.
In doing so, a user will be able to edit their week view and save the contents of their file to a .bujo file to 
refer back to later. Once they save their contents, the user will have access to their file in their directory. 
If a user would like to go back and edit their bullet journal, they are granted the access to open the file from 
their directory to continue working!
![Current Week View](/assets/images/save.png)

## Commitment Warnings
When creating a bullet journal, the user will be able to set a maximum number of tasks/events for each day. If the user 
exceeds the maximum number for tasks and/or events there will be a warning displayed for the User's
knowledge.
![Current Week View](/assets/images/MaxEvents.png)


# Section 2
## Task Queue
Helps the user visibly locate all of their tasks in one location on the week view. In addition to the day associated 
with each task, the tasks will also be grouped together in the side task que.
![Current Week View](/assets/images/TasksAdded.png)

## Themes
The user will be able to manually change the theme in the settings from the options: lightTheme, darkTheme, 
superDarkTheme, and blueTheme. In addition to the background color changing, the user will also see the fontType and
icon color change accordingly.
![Current Week View](/assets/images/themes.png)
![Current Week View](/assets/images/dark.png)

# Section 3
## Quotes and Notes
In the settings, the user will have the ability to add a quote or note for their week. The user will 
have a section in the bottom of their week view that will be designated for their added notes.
![Current Week View](/assets/images/QuotesAndNotes.png)

## Mini Viewer 
Represents a way for the user to just read the tasks and events that are located in task que and the days of the week.
On their week view, the user will have the option to ONLY view the contents of each task and event made.
![Current Week View](/assets/images/MiniView.png)

## Weekly Overview
Assists the user in keeping track of overall statics for their week's overview. The week overview displays the total 
number of tasks and events created by the user. In addition, there is a progress bar which assist's the user experience 
in keeping track of the completed tasks out of all the tasks.
![Current Week View](/assets/images/CurrentWeekView.png)


# Section 4 
## Links
If a user were to create a link in their task or event description, this allows for the user to click the link and refer
to it. By using the mini viewer, the user is granted the opportunity to click on any real link indicated.
![Current Week View](/assets/images/links.png)

## Mind Changes
Assists the user in changing what a task or event contains. Directs the user back to the page to edit the 
task/event's contents intended.

# Section 5
## Visual Flourish
In addition to the three themes, we have a blueTheme which captures a way to feel underwater 
and in the sky simultaneously! In addition, we have animations for our icons which allow for a unique user experience.
![Current Week View](/assets/images/blueTheme.png)

## Splash Screen
When the user opens a new or old file, before the contents appear, there is an animated screen which shows our
company's brand name. This gives users a feel into our world as JKN!
![Current Week View](/assets/images/Splash.png)

## Privacy Lock
Before getting into an old file or creating a new file, the user will have to submit a password to get into their
chosen file!
![Current Week View](/assets/images/password.png)



# SOLID principles
## S - Single Responsibility
One example of single responsibility is the Task class in the model. The Task class has one responsibility, with one 
single purpose, which is to represent a single task on a Week Overview. When there is functionality that needs to be 
altered for a task, then the functionality will only be changed in the Task class. Thus, this class follows the single 
responsibility because all the necessary functionality is only needed to create a "task".

## O - Open/Closed
An example of the open/closed principle is the EventType class. The EventType class represents an abstract class which
has extensions from Event and Task. This principle states that software entities should be open for extension, but 
closed for modification. Today I have Event and Task classes that extend the EventType class; however, what if tomorrow 
I wanted to create an Issue class. The reason I would be able to successfully do so is because the EventType class
follows the open/closed principle. Furthermore, I can extend from the EventType class however 
it is closed for modification.

## L - Liskov Substitution
An example of the LisKov Substitution principle is the Event class. The Event class contains many methods that are in 
common with the Task class. For example, the getName() and getDescription() are the same in terms of functionality. 
However, it is not possible to have an isComplete() in the Event class due to the LisKov Substitution principle. 
This principle states that you cannot replace objects of a parent class, which is in relation to the Event class.

## I - Interface Segregation
One example of Interface Segregation is the EventType class. This principle states that no client should depend on 
methods it does not use. For instance, in the EventType class there are enough methods that can be used by both the Task
and Event class which extends the EventType class. Therefore, the clients of the EventType class shouldn't be forced to 
depend on methods they don't use.

## D - Dependency Inversion
An example of Dependency Inversion is the EditEventController and BulletJournalView class. The Dependency Inversion 
is the principle which allows coupling between different classes or modules. One example is in the EditEventController
we inject the Model class Event into. Additionally, in the BulletJournalView class we inject the Controller interface/



# Additional non-implemented feature
One additional feature that we have not implemented would be creating a vertical view for the bullet journal.
In this vertical view the days of the week will be listed vertically, in comparison to listed horizontally.
We will do this by created a new FXML which is in the vertical view and reassigning the fx:id's to be fitting for
a vertical view. In addition, we will add buttons to represent a vertical and horizontal view at the top of the GUIs.
These buttons will handle the implementation of changing the scenes and their views.


# Attribution
https://wallpaperaccess.com/modern-dark-abstract
https://icons8.com/icons/set/royalty-free
