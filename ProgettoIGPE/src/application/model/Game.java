package application.model;

import application.config.Utility;

public class Game {

	private static Game game = null;
	private Paddle paddle;
	private Ball ball;

	public static Game getInstance() {
		if (game == null)
			game = new Game();
		return game;
	}

	private Game() {
		paddle = new Paddle();
		paddle.x = Utility.WIDTH_SIZE / 2 - Utility.DIM_X_PADDLE / 2;
		paddle.y = Utility.HEIGHT_SIZE - 100;
		paddle.speed = 10;

		ball = new Ball();
		ball.x = Utility.WIDTH_SIZE / 2 - Utility.DIM_BALL / 2;
		ball.y = Utility.HEIGHT_SIZE - 175;
		ball.dirX = -1;
		ball.dirY = -1;
	}

	public void movePaddle(int direction) {
		switch (direction) {
		case 0:
			if (paddle.x + paddle.speed <= Utility.WIDTH_SIZE - Utility.DIM_X_PADDLE)
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

	public void updateBall() {
		if ((ball.x <= 0 && ball.dirX < 0) || (ball.x + Utility.DIM_BALL >= Utility.WIDTH_SIZE && ball.dirX > 0))
			ball.dirX = -ball.dirX;
		if ((ball.y <= 0 && ball.dirY < 0) || (ball.y + Utility.DIM_BALL >= Utility.HEIGHT_SIZE && ball.dirY > 0))
			ball.dirY = -ball.dirY;
		int cont = 0;
		while (cont < 10) {
		ball.x += ball.dirX;
		ball.y += ball.dirY;
		cont++;
		}
		checkCollision();
	}

	public void checkCollision() {

		if (paddle.getRect().intersects(ball.getRect())) {
			int posPaddle = (int) paddle.getRect().getMinX();
			int posBall = (int) ball.getRect().getMinX();

			int firstHalf = posPaddle + 36;
			int secondHalf = posPaddle + 72;
			int thirdHalf = posPaddle + 108;
			int fourthHalf = posPaddle + 144;
			
			if (posBall < firstHalf) {
				if (ball.dirX >= 0) 
					ball.dirX =- ball.dirX;
				ball.dirY=- ball.dirY;
			}
			else if (posBall >= firstHalf && posBall < secondHalf) {
				if (ball.dirX > 0) 
					ball.dirX =- ball.dirX;
				ball.dirY =- ball.dirY;
			}
			else if (posBall >= secondHalf && posBall < thirdHalf) {
				ball.dirX = 0;
				ball.dirY =- ball.dirY;
			}
			else {
				if (ball.dirX <= 0) 
					ball.dirX =- ball.dirX;
				ball.dirY =- ball.dirX;
			}
		}
	}

	public Paddle getPaddle() {
		return paddle;
	}

	public Ball getBall() {
		return ball;
	}

}
