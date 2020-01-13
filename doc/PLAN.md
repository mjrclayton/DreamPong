# Game Plan
## DreamPong

### Breakout Variant

- **Jet Ball**: I found the Jet Ball variant to be
interesting because of the possibilities that moving bricks open up.
My main vision for my version of breakout is to focus on the
relaxing and satisfying aspect of pong/breakout games, and I think
that moving bricks in floaty and unconstrained patterns is a great
way to convey that feeling. 
- **Vortex**: I also found the Vortex variant to be interesting
for a couple of reasons:
  1. I grew up playing Vortex on my third generation iPod nano,
  so there's an element of nostalgia associated.
  2. I find the element of depth to be really cool in a game that
  is stereotypically two-dimensional. However, for this project
  I am a little concerned about the added complexity of adding
  at least the illusion of 3D, so I don't think it's very likely
  I end up implementing it. 
  
### General Level Descriptions
- Ball speed will increase by 10% after each level
- Level Designs (5 Total Levels)
  - Level 1
    - Bricks are all animated to be mildly floating
     (12 total hits)
    - [Level 1 Text File](../resources/Level1.txt) 
  - Level 2
    - Bricks are all animated to be floating more
     aggressively (22 total hits)
    - [Level 2 Text File](../resources/Level2.txt)
  - Level 3
    - Bricks in middle are rotating, middle four are 
      rotating clockwise, while outer 1’s are rotating
      counter-clockwise (26 total hits)
    - [Level 3 Text File](../resources/Level3.txt)
  - Level 4
    - 1 bricks are going to move back and forth between
    the 3 and two bricks in the outer rows of the top
    larger square, all other bricks will be floating
    (49 total hits)
    - [Level 4 Text File](../resources/Level4.txt)
  - Level 5
    - the two pair of twos and the pair of three rows
     will move back and forth, crossing over each other.
     The 3’s in the middle will rotate clockwise and the
     3’s on the sides will move up and down. The quads of
     1’s will rotate counter clockwise and the layer of 
     2’s and 3’s at the bottom will simply float.
    - [Level 5 Text File](../resources/Level5.txt)
  
- As the levels increase, aspects of the levels will change to simulate
the player entering deeper and deeper into the DreamWorld. Some ideas 
for these effects are as follows:
  - In between levels, there will be an all black screen with white
  writing from an unknown speaker in the center
    - During each of these "dialogue" screens, the text will appear
    and pause in that position until a small, slowly bouncing arrow appears
    below to show that the player can continue to the next text screen or
    level screen.
    - Before Level 1
      - "..."
      - "do you know where you are?"
    - Between Levels 1 and 2
      - "..."
      - "when the sun rises, this world ceases to exist"
    - Between Levels 2 and 3
      - "..."
      - "but while you're here, you can travel deeper"
    - Between Levels 3 and 4
      - "..."
      - "deeper into..."
    - Between Levels 4 and 5
      - "..."
      - "dreamworld."
    - After Level 5
      - "congratulations, you have completed DreamPong"
      
### Bricks Ideas
- Add shadow textures to bricks if time allows
- All bricks will have a random chance (15-30%) of dropping
 a power up of available power ups depending on
- All bricks will have a random chance (5-10%) of dropping
a DreamBuck
- The final brick of each level, regardless of its type, will
change texture into a wormhole/portal type thing when it's on
its final hit to represent beating the level and moving deeper
into the dreamworld.
- Brick Type 1 (Worth 5 points)
  - Standard brick, takes 1 hit from the ball to break
  - Will have its own specific texture/color
- Brick Type 2 (Worth 15 points)
  - Heavy brick, takes 2 hits from the ball to break
  - Will have its own specific texture/color that will
  change after it's been hit the first time
- Brick Type 3 (Worth 40 points)
  - Extra heavy brick, takes 4 hits from the ball to break
  - Will have its own specific texture/color that will 
  change after it's been hit the first, second, and third
  time

### Power Up Ideas
- All power ups worth 30 points to collect
- Starter Power Ups
  - Big Paddle: The paddle enlarges for up to ten ball contacts
  - Slow Ball: The ball halves in speed
  - Multi-ball: Each ball on screen splits into three balls
- Unlockable Power Ups
  - Power Ball: For three bounces off the paddle, the ball powers
    directly through any bricks in its way, destroying them immediately
  - Laser: Every few seconds, the paddle shoots a laser that does
    the same damage to a brick as the ball

### Cheat Key Ideas
- 'L': Add additional lives to the player
- 'R': Resets the ball and paddle to their starting position
- '1-9': When the player presses a numeric key, clear the current
level and jump to the level corresponding to the key pressed, or
the highest available

### Something Extra
- DreamBucks system in which as the player collects DreamBucks - 
which randomly spawn from bricks and are awarded as levels are
completed - they will be able to purchase the ability to use new
power ups as well as paddles and ball skins from a separate menu
selection.
- **Other small something extra possibly**: On each bounce off the paddle, the ball will randomly change
color.
