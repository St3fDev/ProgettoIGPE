package application.model;

import java.awt.Point;
import java.util.Random;

import application.config.Utilities;
import application.view.Maps;

public class Game {

	private static Game game = null;
	private boolean pause;
	private Paddle paddle;
	private Ball ball;
	private Brick[] bricks = null;
	private int level;
	private int lives;
	private int score;
	private boolean pwr_life;
	private boolean pwr_3ball;
	private boolean pwr_fireball;

	public static Game getInstance() {
		if (game == null)
			game = new Game();
		return game;
	}

	private Game() {
		paddle = new Paddle();
		paddle.x = Utilities.WIDTH_SIZE / 2 - Utilities.DIM_X_PADDLE / 2;
		paddle.y = Utilities.HEIGHT_SIZE - 120;
		paddle.speed = 25;

		ball = new Ball();
		ball.x = Utilities.WIDTH_SIZE / 2 - Utilities.DIM_BALL / 2;
		ball.y = Utilities.HEIGHT_SIZE - 140;
		ball.dirX = -1;
		ball.dirY = -1;
		pause = true;
		bricks = new Brick[42];
		lives = 3;
		score = 0;
		pwr_life = false;
		pwr_3ball = false;
		pwr_fireball = false;
	}

	public void showLevel() {
		showCurrentLevel(Maps.getIstance().ReadMap(getLevel()));
	}

	public void movePaddle(int direction) {
		if (!pause) {
			switch (direction) {
			case 0:
				if (paddle.x + paddle.speed <= Utilities.WIDTH_SIZE - Utilities.DIM_X_PADDLE)
					paddle.x += paddle.speed;
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
		int bricksBroken = 0;
		for (int i = 0; i < 42; i++) {
			if (bricks[i].getDestroyed())
				bricksBroken++;
		}
		if (bricksBroken == 42)
			resetGame();
		if (!pause) {
			if ((ball.x <= 0 && ball.dirX < 0)
					|| (ball.x + Utilities.DIM_BALL >= Utilities.WIDTH_SIZE && ball.dirX > 0))
				ball.dirX = -ball.dirX;
			if ((ball.y <= 0 && ball.dirY < 0)
					|| (ball.y + Utilities.DIM_BALL >= Utilities.HEIGHT_SIZE && ball.dirY > 0))
				ball.dirY = -ball.dirY;
			if (ball.y <= 0 && ball.dirX == 0)
				ball.dirX = -1;
			ballCollision();
			int cont = 0;
			while (cont != 8) {
				ball.x += ball.dirX;
				ball.y += ball.dirY;
				cont++;
			}
		}
	}

	public void ballCollision() {
		paddleCollision();
		brickCollision();
	}

	private void paddleCollision() {
		if (ball.getRect().getMaxY() > Utilities.LIMIT_LINE) {
			lives--;
			resetGame();
		}
		if (paddle.getRect().intersects(ball.getRect())) {
			int posPaddle = (int) paddle.getRect().getMinX();
			int posBall = (int) ball.getRect().getMinX();

			int firstHalf = posPaddle + 30;
			int secondHalf = posPaddle + 60;
			int thirdHalf = posPaddle + 90;
			int fourthHalf = posPaddle + 120;

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
		for (int i = 0; i < 42; i++) {
			if (ball.getRect().intersects(bricks[i].getRect())) {
				int ballLeft = (int) ball.getRect().getMinX();
				int ballHeight = (int) ball.getRect().getHeight();
				int ballWidth = (int) ball.getRect().getWidth();
				int ballTop = (int) ball.getRect().getMinY();

				Point pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
				Point pointLeft = new Point(ballLeft - 1, ballTop);
				Point PointTop = new Point(ballLeft, ballTop - 1);
				Point PointBottom = new Point(ballLeft, ballTop + ballHeight + 1);
				// se utilizzavo anche ballRight e ballBottom in modo da gestire meglio le
				// collisioni
				// se due brick vicini avevano uno il pointRight e uno il pointLeft si crea una
				// situazione in cui la dirX
				// cambiava e ritornava come prima e il rimbalzo non avveniva
				if (!bricks[i].getDestroyed()) {
					if (i % 2 == 0)
						spawnPwr();
					if (bricks[i].resistance == Utilities.BRICK_RES_1) {
						bricks[i].setDestroyed(true);
						score++;
					}
					bricks[i].resistance--;
					if (bricks[i].getRect().contains(pointRight))
						ball.dirX = -1;
					else if (bricks[i].getRect().contains(pointLeft))
						ball.dirX = 1;
					if (bricks[i].getRect().contains(PointTop)) {
						if (ball.dirX == 0)
							ball.dirX = -1;
						ball.dirY = 1;
					} else if (bricks[i].getRect().contains(PointBottom))
						ball.dirY = -1;
				}
			}
		}
	}

	public void showCurrentLevel(int[][] level) {
		int k = 0;
		for (int i = 0; i < level.length; i++) {
			for (int j = 0; j < level[i].length; j++) {
				if (level[i][j] >= Utilities.BRICK_RES_1) {
					bricks[k] = new Brick(j * Utilities.DIM_X_BRICK, i * Utilities.DIM_Y_BRICK);
					bricks[k].resistance = level[i][j];
					bricks[k].resistanceInit = level[i][j];
					k++;
				}
			}
		}
	}
	
	public void spawnPwr() {
		Random r = new Random();
		int rand = r.nextInt(100)+1;
		if (rand <= 2) {
			pwr_life = true;
		}
		else if (rand > 2 && rand <= 7)
			pwr_3ball = true;
		else if (rand > 8 && rand <= 12)
			pwr_fireball = true;
		System.out.println(rand);
	}

	public void resetGame() {
		paddle.x = Utilities.WIDTH_SIZE / 2 - Utilities.DIM_X_PADDLE / 2;
		paddle.y = Utilities.HEIGHT_SIZE - 120;
		ball.x = Utilities.WIDTH_SIZE / 2 - Utilities.DIM_BALL / 2;
		ball.y = Utilities.HEIGHT_SIZE - 140;
		ball.dirX = -1;
		ball.dirY = -1;
	}

	public Brick[] getBrick() {
		return bricks;
	}

	public Paddle getPaddle() {
		return paddle;
	}

	public Ball getBall() {
		return ball;
	}

	public void setPause(boolean p) {
		pause = p;
	}

	public boolean getPause() {
		return pause;
	}

	public void setLevel(int lvl) {
		level = lvl;
	}

	private int getLevel() {
		return level;
	}

	public int getLives() {
		return lives;
	}
	
	public int getScore() {
		return score;
	}
}
