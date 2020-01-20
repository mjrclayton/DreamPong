game
====

This project implements the game of Breakout.

Name: Matthew Clayton

### Timeline

Start Date: 1/11/20

Finish Date: 1/20/20

Hours Spent: ~25-30

### Resources Used
- docs.oracle.com
- tutorialspoint.com
- stackoverflow

### Running the Program

Main class: SceneController

Data files needed: 
- Level1.txt
- Level2.txt
- Level3.txt
- Level4.txt
- Level5.txt

Key/Mouse inputs: 
- A and D to move the paddle left and right
- Click controls in menus

Cheat keys:
- 'L' to add one life
- 'R' to reset ball, paddle, and powerups
- '1-9' to go to levels 1 through 5

Known Bugs:
- Sometimes the stickypaddle powerup causes and issue when
used in tandem with the bigpaddle powerup due to ball positioning
- Bounce physics are mostly good, but can be a little whacky/off
at times

Extra credit: Added dreambucks system, and the necessary building
blocks to support a store, but I did not have time to buildout
the store itself. Additionally, have a fully funcioning main menu
instead of just a splash screen. Also added 5 levels as opposed
to the required three. 


### Notes/Assumptions
- Utilized pixelmator to make all the sprites/images used in the
game myself. 
- Didn't have the time to buildout brick movement the way that
I wanted to, but do believe my software design could support it
relatively easily, maybe with adding a new companion text file
describing brick movements
- Made the ball bouncing off the paddle more of a skillful activity
by calculating the resulting launch angle based on where the ball
hits the paddle. This gives the player more control to aim the
ball at bricks.
- I made a mistake in designing the ball system in a way that
could easily support multiple balls, and as a result I switched
out that power up for stickypaddle due to time.
- I also didnt have time to graphically build out some of the
story components that I had in my plan

### Impressions
- This was a very fun project, but time consuming for sure 
given how early in the class it is. I'm glad to have a real 
project to put on my own github and resume as well too. Physics
in javafx are way easier to work with than my past experience
with swift and 2Dspritekit as well.

