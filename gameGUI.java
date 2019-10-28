import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Formatter;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
/*
Name: Moazzam Salman, Abdul Moid, Vaibhav Singh
NET ID: msalman, vsingh11, amunawar.
Project 4
Lab : Tuesday and Thursday (11:05am-12:20pm)

*/
public class gameGUI extends JFrame implements KeyListener  {
	JFrame gameFrame;
	int counter=0;
	JPanel panel;
	boolean isPressed=false;
	int velocityX=40,velocityY=30;
	Timer timer;
	String a="0";
	Scanner fileReader;
	double timeX; //the time is incremented by this value whenever this actionPerformed is called by timer
	double timeY;
	File highScoreFile;
	int directionX=1;
	FileWriter file;
	int directionY=1;
	int highScore=0;
	int distanceX,distanceY;
	int velocityO;
	int score=0;
	int levelNumber=1;
	double timeRemaining=1;
	Formatter fileWriter;
	double timeInitial=60;
	AudioClip audioNote,audioNoteWall,audioWin,audioStart,audioGameOver,audioLose;
	double timeElapsed=0;
	double angleRad;
	int sliderMovement=0;
	int ballDiameter=30;
	double t=0;
	boolean gameOver=false;
	int livesLeft=3;
	public Canvas canvas;
	int sliderxCord,slideryCord;
	double ballxCord,ballyCord;
	int angleSelection;
	JLabel scoreLabel,livesLabel,timeLabel,gap,gap2,levelText,gap3,gap4,highScoreLabel;
	public int frameHeight,frameWidth;
	public gameGUI() {
		
		
		//the 7 lines below create objects of audioclips and assigns mp3 files to it
		audioNote=new AudioClip(this.getClass().getResource("/sound/slider.mp3").toString());
		audioNoteWall=new AudioClip(this.getClass().getResource("/sound/wall.mp3").toString());
		audioWin=new AudioClip(this.getClass().getResource("/sound/win.mp3").toString());
		audioStart=new AudioClip(this.getClass().getResource("/sound/gameStart.mp3").toString());
		audioGameOver=new AudioClip(this.getClass().getResource("/sound/loss.mp3").toString());
		audioLose=new AudioClip(this.getClass().getResource("/sound/roundLose.wav").toString());
		
		
		audioStart.play(); //the audiostart audio is played when game starts
		panel=new JPanel(); //creates a new JPanel
		try {
			file= new FileWriter("highScore.txt", true); //writes the highscore into this file
			fileWriter=new Formatter(file); //used to write to file
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			fileReader=new Scanner(new File("highScore.txt")); //used to read highScore from file
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
		a=fileReader.next(); //reads the HighScore from the file
		}catch(Exception e) {
			System.out.println("you dont have a highscore");
		}

		
		highScore=Integer.parseInt(a); //highScore converted into integer
		
		gameFrame=new JFrame("Lob Pong"); //creates JFrame
		gameFrame.setSize(1200,500); //sets the size
		gameFrame.setLayout(new BorderLayout()); //sets the layout of the frame to borderLayout
		scoreLabel=new JLabel("Score: 0"); //creates a JLabel
		highScoreLabel=new JLabel("highScore: "+highScore); //creates a JLabel
		panel.add(highScoreLabel); //adds the score label to the panel
		gap4=new JLabel("           "); //creates a space
		panel.add(gap4); //adds it to the panel
		panel.add(scoreLabel); //adds the score label to the panel
		gap=new JLabel("           "); //creates a space
		panel.add(gap); //adds it to the panel
		livesLabel=new JLabel("Lives: 3"); //creates a lives label
		panel.add(livesLabel); //adds it to the panel
		gap2=new JLabel("           "); //creates a space
        
        
		panel.add(gap2); //adds it to the panel
		timeLabel=new JLabel("Time Left: 60s"); //creates the timeleft label
		panel.add(timeLabel); //adds it the panel
		gap3=new JLabel("           "); //creates space
		panel.add(gap3); //adds it to the panel
		levelText=new JLabel("Level: "+levelNumber);
		panel.add(levelText);
		gameFrame.add(panel,BorderLayout.NORTH); //the frame panel is set to NORTH
		gameFrame.setResizable(false); //we cant resize the game screen
		gameFrame.addKeyListener(this); //the keylistener is added to the frame
		canvas=new Canvas(); //creates a new canvas
		gameFrame.add(canvas,BorderLayout.CENTER); //adds it to the center
		
		
		
		
		
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setVisible(true);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int directionKey = e.getKeyCode(); //gets the key code that user presses and stores it in an int
		System.out.println(directionKey);
		if (directionKey == 39 || directionKey == 68) { //checks whether the right key is pressed
			if(sliderxCord<=canvas.getWidth()-100) { //it restricts the sliderBar to stay within the screen
			isPressed=true; //makes the boolean true
			sliderMovement = 20; //variable for slider movement set to a fixed value
			gameFrame.repaint(); //the paintComponent is recalled
			}
		} else if (directionKey == 37 || directionKey == 65) {  //checks if the left key is pressed
			if(sliderxCord>=0) { //restricts the sliderbar to within the screen
				isPressed=true;
			
			sliderMovement = -20;
			gameFrame.repaint(); //calls paintComponent
			}
			
		}
		if(gameOver==true && directionKey==27) { //if the game is over and the user presses the esc key
			System.out.println("assdadasd");
			//the below lines just reset all the variables to the state they were in the start of the game
			ballxCord=300;
			ballyCord=250;
			sliderxCord=250;
			directionX=1;
			directionY=1;
			score=0;
			livesLeft=3;
			t=0;
			timeRemaining=1;
			fileWriter=new Formatter(file); //creates a new instance of the highScore writer format.
			timeInitial=60;
			levelNumber=1;
			gameOver=false;
			timeElapsed=0;
			audioStart.play();
			gameFrame.repaint(); //the paintComponent is called
			timer.start(); //timer starts
		}
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		new gameGUI(); //calls the constructor of the gameGUI class
	}
	
	public class Canvas extends JComponent {
		public Canvas() {
			//when the canvas is created all these values are set to an initial state
			ballxCord=250;
			ballyCord=300;
			sliderxCord=250;
			angleRad=2;
			slideryCord=400;
			angleRad = (Math.PI / 3);
			//the timer is created below and it is gonna get called every 0.024s
			timer=new Timer(5, new TimerCallBack());
			timer.start();
		}
		public void paintComponent(Graphics g) {
			if(counter==0) {
				//when paintComponent runs for the first time this paints the background black
				g.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
			}
			g.setColor(Color.red);
			if(isPressed==true) {
				sliderMover(g); //this method is only called if the left or right button have been pressed
			}
			else {
			
				g.fillRect(sliderxCord, slideryCord, 100, 20); //just paints the slider to where its coordinates are
			}
			if(gameOver==true) {
				g.setFont(new Font("TimesRoman", Font.PLAIN, 50)); //sets the font and fontsize of the text
				g.drawString("GAME OVER", canvas.getWidth()/2-160,canvas.getHeight()/2-100); //writes Game Over on the screen
				g.drawString("Your Final Score: "+score, canvas.getWidth()/2-160,canvas.getHeight()/2); //writes final score on the screen
				g.drawString("Press esc for new game ", canvas.getWidth()/2-250,canvas.getHeight()/2+100); //writes this on the screen
			}else {
			
			g.fillOval((int)ballxCord-ballDiameter/2,(int)ballyCord-ballDiameter/2-75,ballDiameter,ballDiameter); //if game over isnt true then the ball is drawn on the screen
			}
			
		}
		public class TimerCallBack implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				timeX += directionX * 0.04;
				timeY += directionY * 0.025;
				ballxCord= velocityX*Math.cos(Math.toRadians(30))*timeX; //calculates the distanceX to be moved by ball
				ballyCord=(velocityY*Math.sin(Math.toRadians(30))*timeY)+(0.5*timeY*timeY*9.81); //calculates the distanceY to be moved by ball
				System.out.println("X"+ballxCord);
				System.out.println("Y"+ballyCord);
				if(ballxCord<=(sliderxCord+100) && ballxCord>=(sliderxCord+50) && ballyCord>=(slideryCord+58)) { //if the ball hits the upper half of the sliderbar
						directionY=-1; //the direction of y is reversed
						directionX=1; //the direction of x is set to positive x direction
						score++; //score increments
						audioNote.play(); //an audio is played
					
				}
				if(ballxCord<=(sliderxCord+50) && ballxCord>=(sliderxCord) && ballyCord>=(slideryCord+58)) {// if the slider hits the lower half the slider bar
					
					directionY=-1; // the y direction is reversed
					directionX=-1; //the x direction is put towards the negative axis
					score++; //score increments
					audioNote.play(); //an audio note plays
				
					
				}
				if(ballxCord>=canvas.getWidth()-15 && directionX==1 && directionY==-1) { //if the ball hits the right side of the wall coming from downard
					directionX=-1; //the x direction is put towards negative axis
					System.out.println("done");
					audioNoteWall.play();
					
				}
				if(ballxCord>=canvas.getWidth()-15 && directionX==1 && directionY==1) {//if ball hits right side from upward
					directionX=-1; //the x direction is put torwards negative axis
					System.out.println("xxxxxx");
					audioNoteWall.play();
					
				}
				if(ballyCord<=100 && directionX==1 && directionY==-1) {//if ball hits upward wall coming from left side
					directionY=1; //the y direction is changed to positive
					audioNoteWall.play();
					
				}
				if(ballyCord<=100 && directionX==-1 && directionY==-1) {//if ball hits upward wall coming from right side
					directionY=1; //the y direction is changed to positive
					audioNoteWall.play();
					
				}
				if(ballxCord<=25 && directionX==-1 && directionY==-1) { //if balls hits left wall coming from below
					directionX=1; //x direction changed to positive
					audioNoteWall.play();
					
				}
				if(ballxCord<=25 && directionX==-1 && directionY==1) {  //if balls hits left wall coming from above
					directionX=1; //x direction changed to positive
					audioNoteWall.play();
					
				}
				if(timeRemaining<=0) { //if the user survive the time limit
					timer.stop(); //the timer stops
					System.out.println("You Won this Round"); //user wins the round
					//the values are reset to what they were in the start of the game
					ballxCord=1;
					ballyCord=1;
					sliderxCord=250;
					timeX=0;
					timeY=0;
					audioWin.play();
					score=score+5; //user gets five bonus points
					timeElapsed=0;
					directionX=1;
					levelNumber++; //goes to the next level
					directionY=1;
					timeInitial=timeInitial+5; //the new level starts with an extra time of 5s
					timeRemaining=timeInitial;
					updateGUI(); //updates all the GUI labels
					timer.start(); //the timer restarts
				}
				if(ballyCord>=canvas.getHeight()+80) { //if the ball goes below the sliderbar
					timer.stop(); //the timer stops
					System.out.println("Game Over");
					livesLeft--; //livesleft is decreased
					//all the variables are reset to what they were when the game starts
					ballxCord=1;
					ballyCord=1;
					timeX=0;
					timeY=0;
					directionX=1;
					audioLose.play();
					directionY=1;
					sliderxCord=250;
					t=0;
					timeElapsed=0;
					timeRemaining=60;
					updateGUI(); //updates GUI 
					timer.start(); //restarts the timer
					
				}
				if(livesLeft<0) { //if there are no more lives
					timer.stop(); //the timer stops
					audioGameOver.play(); //plays an audio
					gameOver=true; //gameOver becomes true
					if(score>highScore) { //if the current score is greater than highScore then
						highScore=score; //highScore is set to score
					}
					fileWriter.format("%s",Integer.toString(highScore)); //the highscore is written to the file
					fileWriter.close(); //file writer is closed
					livesLeft=0; //livesLeft are set to 0
					updateGUI(); //updates GUI
					repaint(); //calls PaintComponent
					
				}
				timeElapsed+=0.005; //the time elapsed is incremented by 0.024 each time
				timeRemaining=timeInitial-timeElapsed;
				updateGUI(); //updates the GUI
				
				gameFrame.repaint(); //calls the paintComponent
				
				
			}
			
		}
		public void updateGUI() {
			//sets all the labels to what their values should be
			timeLabel.setText("Time Left: "+(int)timeRemaining+"s");
			scoreLabel.setText("Score: "+score);
			livesLabel.setText("Lives "+livesLeft);
			levelText.setText("Level: "+levelNumber);
			highScoreLabel.setText("High Score: "+highScore);
			
		}
		public void sliderMover(Graphics g) {
			sliderxCord+=sliderMovement; //the sliderxCord is incremented by sliderMovement
			g.fillRect(sliderxCord, slideryCord, 100, 20); //the sliderbar is drawn
			isPressed=false; //isPressed is set to false
		}
	
	}

}
