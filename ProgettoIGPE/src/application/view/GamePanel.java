package application.view;

import java.awt.Graphics;
import javax.swing.JPanel;

import application.config.Utility;
import application.model.Game;

public class GamePanel extends JPanel {
	
	private static final long serialVersionUID = -2171231079412649632L;
	
	private PaddleView paddle = new PaddleView();
	private BallView ball = new BallView();
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int x_paddle = Game.getInstance().getPaddle().getX();
		int y_paddle = Game.getInstance().getPaddle().getY();
		g.drawImage(paddle.img, x_paddle, y_paddle, paddle.dimX, paddle.dimY, null);
		
		int x_ball = Game.getInstance().getBall().getX();
		int y_ball = Game.getInstance().getBall().getY();
		g.drawImage(ball.img, x_ball, y_ball, ball.dimX, ball.dimY, null);
		g.drawRect(x_paddle, y_paddle, Utility.DIM_X_PADDLE,Utility.DIM_Y_PADDLE);
		g.drawRect(x_ball,y_ball, Utility.DIM_BALL,Utility.DIM_BALL);
		}

	public void update() {
		repaint();
	}
	
}
