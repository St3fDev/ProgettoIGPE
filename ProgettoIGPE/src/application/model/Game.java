package application.model;

import java.awt.Point;
import java.util.Vector;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;

import application.config.Utilities;
import application.view.ManagerFile;
import application.view.Maps;
import application.view.Sounds;

public class Game {

	private static Game game = null;
	private boolean pause;
	private boolean lose;
	private boolean won;
	private boolean levelUp;
	private int firstHalfPaddle;
	private int widthPaddle;
	private int velBall;
	private int level;
	private int lives;
	private int score;
	private int count_sound;
	private Paddle paddle;
	private Ball ball;
	private Vector<Brick> bricks;
	private Vector<Powerups> pwr;
	private Vector<Boolean> pwrActivated;
	private Vector<Integer> pwrDuration;
	private Vector<Boolean> managerTimePwr;
	private Vector<Integer> positionLight;
	private HashMap<Integer, Boolean> lightOff;
	private Sounds sound;
	private Sounds loseLife;
	private Sounds paddleSound;
	private Sounds gameOver;
	private Sounds blockHit;
	private Sounds win_sound;
	private Sounds block_hit2;
	private Sounds power_up;
	private Sounds nerf;
	private Timer timer;

	
	public static Game getInstance() {
		if (game == null)
			game = new Game();
		return game;
	}

	private Game() {
		pause = true;
		lose = false;
		won = false;
		levelUp = false;
		bricks = new Vector<Brick>();
		pwr = new Vector<Powerups>();
		pwrActivated = new Vector<Boolean>();
		pwrDuration = new Vector<Integer>();
		managerTimePwr = new Vector<Boolean>();
		positionLight = new Vector<Integer>();
		lightOff = new HashMap<Integer, Boolean>();
		lives = 3;
		score = 0;
		count_sound = 0;
		velBall = 9;
		timer = new Timer();
		initAllSound();
		initPaddle();
		initBall();
		setPwr();
	}
	
	private void initAllSound() {
		sound = new Sounds("levelCompleted.wav");
		loseLife = new Sounds("loseLife.wav");
		paddleSound = new Sounds("paddle.wav");
		gameOver = new Sounds("gameover.wav");
		blockHit = new Sounds("block_hit.wav");
		win_sound = new Sounds("sound_win.wav");
		block_hit2 = new Sounds("block_hit.wav");
		power_up = new Sounds("power-ups.wav");
		nerf = new Sounds("nerf.wav");
	}
	
	private void initPaddle() {
		paddle = new Paddle();
		paddle.x = Utilities.WIDTH_SIZE / 2 - Utilities.DIM_X_PADDLE / 2;
		paddle.y = Utilities.HEIGHT_SIZE - 120;
		firstHalfPaddle = 30;
		paddle.speed = 25;
		widthPaddle = 0;
	}
	
	private void initBall() {
		ball = new Ball();
		ball.x = Utilities.WIDTH_SIZE / 2 - Utilities.DIM_BALL / 2;
		ball.y = Utilities.HEIGHT_SIZE - 140;
		ball.dirX = -1;
		ball.dirY = -1;
	}
	
	private void setPwr() {
		for (int i = 0; i < 6; i++) {
			pwrDuration.add(5);
			pwrActivated.add(false);
			managerTimePwr.add(false);
		}
	}

	public void showLevel() {
		showCurrentLevel(Maps.getIstance().ReadMap(getLevel()));
	}

	public void movePaddle(int direction) {
		if (!pause) {
			switch (direction) {
			case 0:
				if (paddle.x + paddle.speed <= Utilities.WIDTH_SIZE - (Utilities.DIM_X_PADDLE + widthPaddle)) {
					paddle.x += paddle.speed;
				}
				break;
			case 1:
				if (paddle.x - paddle.speed >= 0)
					paddle.x -= paddle.speed;
				break;
			default:
				return;
			}
		}
	}

	public void updateBall() {
		if (level < 8) {
			int bricksBroken = 0;
			for (int i = 0; i < bricks.size(); i++) {
				if (bricks.get(i).getDestroyed())
					bricksBroken++;
			}
			if (bricksBroken == bricks.size()) {
				timer.schedule(new Countdown(3, 0), 0, 1000);
				if (Utilities.audioOn)
					sound.restart();
				levelUp = true;
				level++;
				score = 0;

				bricks.clear();
				showCurrentLevel(Maps.getIstance().ReadMap(getLevel()));
				resetGame();
			}
		}
		if (!pause) {
			if ((ball.x <= 0 && ball.dirX < 0)
					|| (ball.x + Utilities.DIM_BALL >= Utilities.WIDTH_SIZE && ball.dirX > 0))
				ball.dirX = -ball.dirX;
			if ((ball.y <= 0 && ball.dirY < 0)
					|| (ball.y + Utilities.DIM_BALL >= Utilities.HEIGHT_SIZE && ball.dirY > 0))
				ball.dirY = -ball.dirY;
			if (ball.y <= 0 && ball.dirX == 0) {
				randomDirection();
			}
			ballCollision();
			int cont = 0;
			while (cont != velBall) {
				ball.x += ball.dirX;
				ball.y += ball.dirY;
				cont++;
			}
		}
	}

	public void ballCollision() {
		if (ball.getRect().getMaxY() > Utilities.LIMIT_LINE) { 
			lives--;
			if (lives == 0) {
				lose = true;
				gameOver.start();
				return;
			} else
				if (Utilities.audioOn)
					loseLife.start();
			resetGame();
		}
		paddleCollision();
		brickCollision();
	}

	private void paddleCollision() {
		if (paddle.getRect().intersects(ball.getRect())) {
			int posPaddle = (int) paddle.getRect().getMinX();
			int posBall = (int) ball.getRect().getMinX();
			if (Utilities.audioOn)
				paddleSound.start();
			int firstHalf = posPaddle + firstHalfPaddle;
			int secondHalf = posPaddle + firstHalfPaddle * 2;
			int thirdHalf = posPaddle + firstHalfPaddle * 3;
			int fourthHalf = posPaddle + firstHalfPaddle * 4;

			if (posBall < firstHalf) {
				if (ball.dirX > 0)
					ball.dirX = -ball.dirX;
				if (ball.dirX == 0)
					ball.dirX = -1;
				ball.dirY = -ball.dirY;
			} else if (posBall >= firstHalf && posBall < secondHalf) {
				if (ball.dirX > 0)
					ball.dirX = -ball.dirX;
				if (ball.dirX == 0)
					ball.dirX = -1;
				ball.dirY = -ball.dirY;
			} else if (posBall >= secondHalf && posBall < thirdHalf) {
				ball.dirX = 0;
				ball.dirY = -ball.dirY;
			} else if (posBall >= thirdHalf && posBall < fourthHalf) {
				if (ball.dirX < 0)
					ball.dirX = -ball.dirX;
				if (ball.dirX == 0)
					ball.dirX = 1;
				ball.dirY = -ball.dirY;
			} else if (posBall > fourthHalf) {
				if (ball.dirX < 0)
					ball.dirX = -ball.dirX;
				if (ball.dirX == 0)
					ball.dirX = 1;
				ball.dirY = -ball.dirY;
			}
		}
	}

	private void brickCollision() {
		for (int i = 0; i < bricks.size(); i++) {
			if (ball.getRect().intersects(bricks.get(i).getRect())) {
				int ballLeft = (int) ball.getRect().getMinX();
				int ballHeight = (int) ball.getRect().getHeight();
				int ballWidth = (int) ball.getRect().getWidth();
				int ballTop = (int) ball.getRect().getMinY();

				Point pointR = new Point(ballLeft + ballWidth + 1, ballTop);
				Point pointR_B = new Point(ballLeft + ballWidth + 1, ballTop + ballHeight); 
				Point pointL = new Point(ballLeft - 1, ballTop);
				Point pointT = new Point(ballLeft, ballTop - 1);
				Point pointB = new Point(ballLeft, ballTop + ballHeight + 1);
				
				if (!bricks.get(i).getDestroyed()) {
					if (count_sound % 2 == 0) {
						if (Utilities.audioOn)
							blockHit.restart();
					}
					else {
						if (Utilities.audioOn)
							block_hit2.restart();
					}
					count_sound++;
					if (managerTimePwr.get(Utilities.PWR_FIREBALL)) {
						bricks.get(i).setDestroyed(true);
						score++;
					} else {
						if (bricks.get(i).resistance == Utilities.BRICK_RES_1) {
							if (i % 2 == 0)
								spawnPwr();
							bricks.get(i).setDestroyed(true);
							score++;
						}
						if (bricks.get(i).resistance < 4)
							bricks.get(i).resistance--;
						if (bricks.get(i).getRect().contains(pointR)) 
							ball.dirX = -1;
						else if (bricks.get(i).getRect().contains(pointL))
							ball.dirX = 1;
						if (bricks.get(i).getRect().contains(pointT)) {
							if (ball.dirX == 0)
								randomDirection();
							ball.dirY = 1;
						} else if (bricks.get(i).getRect().contains(pointB))
							ball.dirY = -1;
						else if (bricks.get(i).getRect().contains(pointR_B))
							ball.dirX = -1;
						
						// il brick di resistenza 4 non si distrugge, cambia stato (acceso/spento)
						if (bricks.get(i).resistance == 4) {
							bricks.get(i).resistance++;
							setLight(i, true);
						} else if (bricks.get(i).resistance == 5) {
							bricks.get(i).resistance--;
							setLight(i, false);
						}
						addPwr(bricks.get(i));
					}
				}
				if (level == 8) {
					// quando entrambi i brick di livello 4 sono spenti, il gioco finisce 
					if (lightOff.get(positionLight.get(0)) && lightOff.get(positionLight.get(1))) {
						win_sound.start();
						won = true;
						ManagerFile.getIstance().setAllUnlocked();
					}
				}
			}
		}
	}
	
	public void setLight(int value, boolean state) {
		if (value == positionLight.get(0))
			lightOff.replace(positionLight.get(0), state);
		else if (value == positionLight.get(1))
			lightOff.replace(positionLight.get(1), state);
	}
	
	public void addPwr(Brick b) {
		for (int i = 0; i < pwrActivated.size(); i++) {
			if (pwrActivated.get(i)) {
				Powerups p = new Powerups();
				p.x = b.x + Utilities.DIM_X_BRICK / 2 - Utilities.DIM_X_PWR / 2;
				p.y = b.y;
				p.speed = 10;
				p.setPower(i);
				pwr.add(p);
			}
		}
	}

	public void pwrCollision() {
		if (!pause) {
			for (int i = 0; i < pwr.size(); i++) {
				pwr.get(i).y += pwr.get(i).speed;
				if (paddle.getRect().intersects(pwr.get(i).getRect())) {
					if (pwr.get(i).getPower() < 4) {
						if (Utilities.audioOn)
							power_up.restart();
					}
					else {
						if (Utilities.audioOn)
							nerf.restart();
					}
					if (pwr.get(i).getPower() == Utilities.PWR_LIFE) {
						if (lives < 5)
							lives++;
					}
					if (pwr.get(i).getPower() == Utilities.PWR_LARGE_PADDLE && !managerTimePwr.get(Utilities.PWR_LARGE_PADDLE))
						timer.schedule(new Countdown(pwrDuration.get(pwr.get(i).power), Utilities.PWR_LARGE_PADDLE), 0, 1000);

					if (pwr.get(i).getPower() == Utilities.PWR_FIREBALL && !managerTimePwr.get(Utilities.PWR_FIREBALL))
						timer.schedule(new Countdown(pwrDuration.get(pwr.get(i).power), Utilities.PWR_FIREBALL), 0, 1000);

					if (pwr.get(i).getPower() == Utilities.NERF_VEL_PADDLE)
						timer.schedule(new Countdown(pwrDuration.get(pwr.get(i).power), Utilities.NERF_VEL_PADDLE), 0, 1000);

					if (pwr.get(i).getPower() == Utilities.NERF_VEL_BALL)
						velBall = 12;

					pwr.remove(i);
				} else if (pwr.get(i).y > Utilities.LIMIT_LINE)
					pwr.remove(i);
			}
			for (int i = 0; i < pwrActivated.size(); i++)
				pwrActivated.set(i, false);
		}
	}

	public void showCurrentLevel(int[][] level) {
		int k = 0;
		for (int i = 0; i < level.length; i++) {
			for (int j = 0; j < level[i].length; j++) {
				if (level[i][j] >= Utilities.BRICK_RES_1) {
					Brick b = new Brick(j * Utilities.DIM_X_BRICK, i * Utilities.DIM_Y_BRICK);
					bricks.add(b);
					if (level[i][j] == Utilities.BRICK_LIGHT) {
						lightOff.put(k, false);
						positionLight.add(k);
					}
					bricks.get(k).resistance = level[i][j];
					bricks.get(k).resistanceInit = level[i][j];
					k++;
				}
			}
		}
	}

	public void spawnPwr() {
		Random r = new Random();
		int rand = r.nextInt(100) + 1;
		int spawn = 0;
		if (level > 5)
			spawn = 15;
		if (rand <= 2 && level < 6 && lives < 5)
			pwrActivated.set(Utilities.PWR_LIFE, true);
		
		else if (rand > 3 && rand <= 8 && level != 8)
			pwrActivated.set(Utilities.PWR_LARGE_PADDLE, true);
		
		else if (rand > 8 && rand <= 13 && level != 8)
			pwrActivated.set(Utilities.PWR_FIREBALL, true);
		
		else if (rand > 13 && rand <= 18 + spawn)
			pwrActivated.set(Utilities.NERF_VEL_BALL, true);
		
		else if (rand > 18 + spawn && rand <= 23 + spawn*2)
			pwrActivated.set(Utilities.NERF_VEL_PADDLE, true);
	}

	public void resetGame() {
		paddle.x = Utilities.WIDTH_SIZE / 2 - Utilities.DIM_X_PADDLE / 2;
		paddle.y = Utilities.HEIGHT_SIZE - 120;
		ball.x = Utilities.WIDTH_SIZE / 2 - Utilities.DIM_BALL / 2;
		ball.y = Utilities.HEIGHT_SIZE - 140;
		pwr.clear();
		paddle.speed = 25;
		velBall = 8;
		ball.dirX = -1;
		ball.dirY = -1;
	}

	public void restartAll() {
		level = 0;
		lives = 3;
		resetGame();
		bricks.clear();
		showCurrentLevel(Maps.getIstance().ReadMap(level));
	}

	public void randomDirection() {
		Random direction = new Random();
		int dir = direction.nextInt(2) + 1;
		if (dir == 1)
			ball.dirX = -1;
		else
			ball.dirX = 1;
	}
	
	public Vector<Brick> getBrick() {
		return bricks;
	}

	public Paddle getPaddle() {
		return paddle;
	}

	public Ball getBall() {
		return ball;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	public boolean isPause() {
		return pause;
	}

	public void setLevel(int lvl) {
		level = lvl;
	}

	public int getLevel() {
		return level;
	}

	public int getLives() {
		return lives;
	}

	public int getScore() {
		return score;
	}

	public Vector<Powerups> getPwr() {
		return pwr;
	}

	public int dimBricks() {
		return bricks.size();
	}

	public void setManagerTimePwr(Vector<Boolean> managerTimePwr) {
		this.managerTimePwr = managerTimePwr;
	}

	public Vector<Boolean> getManagerTimePwr() {
		return managerTimePwr;
	}

	public void setWidthLargePaddle(int widthPaddle) {
		this.widthPaddle = widthPaddle;
	}

	public void setFirstHalfPaddle(int firstHalfPaddle) {
		this.firstHalfPaddle = firstHalfPaddle;
	}

	public void setLose(boolean lose) {
		this.lose = lose;
	}

	public boolean isLose() {
		return lose;
	}

	public boolean isWon() {
		return won;
	}
	
	public void setWon(boolean won) {
		this.won = won;
	}
	
	public boolean isLevelUp() {
		return levelUp;
	}
	
	public void setLevelUp(boolean levelUp) {
		this.levelUp = levelUp;
	}
}