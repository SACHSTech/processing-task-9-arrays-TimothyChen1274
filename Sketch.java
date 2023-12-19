import processing.core.PApplet;

public class Sketch extends PApplet {
	
  /**
   * @author T. Chen
   * A program that creates a snowfall minigame with keyboard and mouse controls using arrays.
   */

  float[] circleX = new float[20];
  float[] circleY = new float[20];
  int snowSpeed = 1;
  boolean[] blnHideBallStatus = new boolean[20];
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
    for (int i = 0; i < circleX.length; i++) {
      circleX[i] = random(width);
    }
    for (int i = 0; i < blnHideBallStatus.length; i++) {
      blnHideBallStatus[i] = false;
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
      if (!blnHideBallStatus[i]) 
      ellipse(circleX[i], circleY[i], 20, 20);
      
  
      circleY[i] += snowSpeed;
      
      if (circleY[i] > height) {
        circleY[i] = 0;
      }
      
      if (dist(circleX[i], circleY[i], playerX, playerY) < playerR) {
        playerLives--;
        circleX[i] = random(width);
        circleY[i] = random(-200, 0);
      }
      if (playerLives == 0) { 
        circleY[i] = 1;
      }
      // Speeds up snowfall while down arrow is pressed and the inverse
      if (keyCode == UP && snowSpeed < 5) {
        snowSpeed++;
        circleY[i] += snowSpeed;
      } else if (keyCode == DOWN && snowSpeed > 1)  {
        snowSpeed--;
        circleY[i] -= snowSpeed;
      }
      if (blnHideBallStatus[i]) {
        circleX[i] = -200;
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
   if (playerLives <= 0) {
    background(255);
    textSize(50);
    noStroke();
    fill(150);
    textAlign(CENTER, CENTER);
    text("GAME OVER", 200, 200);
    }
    }
  }
}
  }

  public void mouseClicked() {
    float snowX[] = new float[20];
    float snowY[] = new float[20];
    for (int i = 0; i < snowX.length; i++) {
      snowX[i] = circleX[i];
    }
    for (int i = 0; i < snowY.length; i++) {
      snowY[i] = circleY[i];
    }
    for (int i = 0; i < snowX.length; i++) {
      if (dist(mouseX, mouseY, snowX[i], snowY[i]) < 20 ) { 
        blnHideBallStatus[i] = true;
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