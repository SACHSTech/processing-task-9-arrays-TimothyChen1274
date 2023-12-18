import processing.core.PApplet;

public class Sketch extends PApplet {
	
	
  /**
   * @author T. Chen
   * A program that creates a snowfall minigame with keyboard and mouse controls using arrays.
   */
  float[] circleX = new float[25];
  float[] circleY = new float[25];
  float[] snowSpeed = new float[1];
  boolean[] blnHideBall;
  boolean aLeft, dRight;
  int playerX = 200;
  int playerY = 339;
  int playerLives = 3; 
  int playerR = 20;

  public void settings() {
    size(400, 400);
  }

  public void setup() {
    background(175, 175, 175);
    for (int i = 0; i < circleY.length; i++) {
      circleY[i] = random(height);
    }
  }

  public void draw() {
    background(175, 175, 175);
    player();
    playerControl();
    drawLives();
    // Creates the platform for the block
    noStroke();
    fill(255, 255, 255);
	  rect(0, 350, 400, 100);

    // Loop that creates the snow
    for (int i = 0; i < circleY.length; i++) {
      circleX[i] = width * i / circleY.length;
      ellipse(circleX[i], circleY[i], 15, 15);
  
      circleY[i]++;
  
      if (circleY[i] > height) {
        circleY[i] = 0;
      }
      if (dist(circleX[i], circleY[i], playerX, playerY) < playerR) {
        playerLives--;
        circleX[i] = random(width);
        circleY[i] = random(-200, 0);
      }
      // Speeds up snowfall while down arrow is pressed and the inverse
      if (keyCode == UP && snowSpeed[i] < 10) {
        snowSpeed[i]++;
      } else if (keyCode == DOWN && snowSpeed[i] > 1)  {
        snowSpeed[i]--;
      }
      
    }
    
  }

   // Drawing player
  public void player(){ 
    stroke(0, 0, 0);
    fill(192, 246, 251);
    ellipse(playerX, playerY, playerR, playerR);
  }

  // Drawing the lives system
  public void drawLives(){
    for (int i = 0; i < playerLives; i++) {
      stroke(0);
      fill(255, 0, 0);
      rect(355 - i * 30, 10, 30, 30);

    }
    if (playerLives == 3) {
    noFill();
    rect(295, 10, 30, 30);
    rect(325, 10, 30, 30);
    rect(355, 10, 30, 30);
    } else {
    if (playerLives == 2) {
    noFill();
    rect(325, 10, 30, 30);
    rect(355, 10, 30, 30);
    } else { 
    if (playerLives == 1) {
    noFill();
    rect(355, 10, 30, 30);
    } else {
      
    }
  }
}
  }

  public void keyPressed() {
    if (key == 'a') {
      aLeft = true;
    } else if (key == 'd') {
      dRight = true;
    }
  }
  
  public void keyReleased() {
    if (key == 'a') {
      aLeft = false;
    } else if (key == 'd') {
      dRight = false;
    }
  }

  public void playerControl() {
    if (playerX <= 15){
      playerX = 15;
    }
    else if(playerX >= 385){
      playerX = 385;
    }
    if (aLeft) {
      playerX -= 8;
    }
    if (dRight) {
      playerX += 8;
    }
  }
}