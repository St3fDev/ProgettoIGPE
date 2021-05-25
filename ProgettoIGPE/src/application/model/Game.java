package application.model;


import application.config.Utility;

public class Game {

	private static Game game = null;
	private Paddle paddle;
	private Ball ball;
	
	
	public static Game getInstance() {
		if(game == null)
			game = new Game();
		return game;
	}
	
	private Game() {
		paddle = new Paddle();
		paddle.x = Utility.WIDTH_SIZE / 2 - Utility.DIM_X_PADDLE / 2;
		paddle.y = Utility.HEIGHT_SIZE - 150;
		paddle.speed = 10;
		
		ball = new Ball();
		ball.x = Utility.WIDTH_SIZE / 2 - Utility.DIM_X_BALL / 2;
		ball.y = Utility.HEIGHT_SIZE - 176;
		ball.dirX =- 10;
		ball.dirY =- 10;
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
		if ((ball.x <= 0 && ball.dirX < 0) || (ball.x + Utility.DIM_X_BALL >= Utility.WIDTH_SIZE && ball.dirX > 0)) 
			ball.dirX = -ball.dirX;
		if ((ball.y <= 0 && ball.dirY < 0) || (ball.y + Utility.DIM_Y_BALL >= Utility.HEIGHT_SIZE && ball.dirY > 0)) 
			ball.dirY = -ball.dirY;
		ball.x += ball.dirX;
		ball.y += ball.dirY;
	}

	public Paddle getPaddle() {
		return paddle;
	}
	
	public Ball getBall() {
		return ball;
	}
	
}
