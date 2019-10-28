# Lob-Pong
Contributors:Moazzam Salman, Abdul Moid, Vaibhav Singh

my gameGUI class has a main which creates an instance of the gameGUI class and the constructor of gameGUI
creates seven audioclips and plays the one which is to be played on gameStart. I created a panel which holds all the level
and score details and this panell is added to the north of the frame and a canvas is created and added to the center of the frame.
when the canvas is created its constructor is called which instantiates the ballxCord,ballyCord, the angle,velocity and various other initial variables
and it also creates the timer and starts it at every 0.024s.  

In the paintComponent the background is painted black and the color is set to Red and the sliderBar is drawn. The circle is also drawn if the game isnt over
if the game is over then in the paintComponent it writes off Game over, final score and press esc to start new game on screen. Also inside the paintComponent
if user presses right or left button then the sliderMover is called which moves the Sliderbar accordingly and draws it on the screen. In the TimerCallBack when the
actionPerformed is called it increments the t by 0.00019 and it calculates the distanceX and distanceY. and these calculates distances are added to the ball coordinates.
then conditions are implied if the ball hits the upper half of the slider then the y direction is reversed and x direction stays positive. score is incremented each time whenever
the ball hits the slider. When the ball hits the lower half of the slider the y direciton goes negative and X directions stays negative. If the ball hits the right wall from downward
the ddirectionX of the ball goes negative and y stays the same. When the ball hits the right wall from upward the X goes negative and y stays same. When the ball hits the upper wall from any
Direction only the Y direction changes to positive. when the ball hits the left wall the x direction changes to positive and the y direction remains the same. The game goes on until the user completes the timer
then a new level starts with extra time of 5s more to endure. If the user ball goes and hits the wall below then the user loses a life and the game is reset. If the user lives goes negative then the game is over 
and a gameover screen is printed and the user pressing esc will trigger a new game.

The keyListener added to the frame causes the movement and the new game functionality. Whenever the user presses the right key or the left key the keycode is taken and compared and the isPressed boolean is set to true.
also the sliderMovement is set accordingly. This causes the movement whenever the repaint is called because the sliderMove is called whenever the boolean isPressed is true. The updateGUI method is called whenever there is a need
to update the labels and text.

I added sound effects to the game upon game start. Upon hitting walls and hitting the padal. I also added gameOver and losing sound effects.
I made a file called highScore and scored the highScore inside that file. The highscore of each game is stored inside in it. The Highscore doesnt reset
when the game is quit and stays on until the user resets it from the file or the user breaks the highScore.

Run Instructions: javac gameGUI.java, java gameGUI.

![image](https://user-images.githubusercontent.com/46281169/61629390-2362d280-ac9e-11e9-9bd0-57e4239958b3.png)
