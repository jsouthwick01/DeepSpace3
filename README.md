# DeepSpace3
Semester long project
There are multiple goals for this project, which will be an asteroids clone. To make it a clone, more or less, is the first goal. Secondary goals include adding power ups and menus. Tertiary goals would be fun stuff like easter eggs or animations or something.
How to accomplish first goals:
First I need to build classes for the different objects that we will be dealing with.
Then I need figure out how to use the gui. Based on the book, I guess I'll try using JavaFX.
Then figure things out? I'm honestly still not 100% sure how I will implement everything just yet.





Post Project Ruminations:
Technically, I believe this project hits all of the requirements for the semester long project, even if just barely. It isn't super complex, but there are some high level thinking sections of code. It is object-oriented and has a gui. Well documented and housed a github are arguably true. It is on github, and it has some documentation. I feel that, although it is pretty lame, my project is complex enough to warrant a semester project. At the very least, I could not have done this last semester.
My project didn't quite meet my goals in the slightest. Not only did I have way too many issues trying to get the gui into a functional state, requiring heavy review and borrowing from demo code, but even trying to get the shooting method to work properly was way to hard. For some reason that I may never quite understand, it refused to register hitting the asteroid along a diagonal or the horizontal axis. Only the vertical axis. That made getting all the other goals much harder. I added a few menu-like Message Dialogs, but only enough to let you know how to play and to tell you you lost.
In hindsight, I should have gone for a much simpler concept. Trying to deal with many moving objects is hard, especially when things to add up properly, or you get null pointer exceptions for seemingly no reason (I think there was an issue with the paint method thread keeping up to the rest of the program, because there would be times when every object was initialized, but somehow a null pointer exception would pop up). However, this project ended up being a lot like the things we went over in class, which made it a good punctuation mark to the end of the semester.
