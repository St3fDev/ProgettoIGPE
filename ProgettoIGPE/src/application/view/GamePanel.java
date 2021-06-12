package application.view;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import application.config.Utilities;
import application.model.Brick;
import application.model.Game;
import application.model.Powerups;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = -2171231079412649632L;

	private PaddleView paddle = new PaddleView();
	private BallView ball = new BallView();
	private BrickView brick = new BrickView();
	private PowerupsView pwr = new PowerupsView();
	private Image background = null;
	private boolean game = true;
	private boolean firstTime = true;
	// private CardLayout card = new CardLayout();
	// private JPanel pauseGame = new JPanel();

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		var g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		if (game) {
			inGame(g2d);
		} else {
			gameOver(g2d);
		}
		/*
		 * background = ImageIO.read(getClass().getResourceAsStream(
		 * "/application/resources/backgrounds/start.jpeg")); g.drawImage(background, 0,
		 * 0, null);
		 */
		// GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

	}

	public void inGame(Graphics2D g2d) {
		if (!Game.getInstance().getPause() || firstTime) {
		if (loseLives(g2d))
			return;
			int x_paddle = Game.getInstance().getPaddle().getX();
			int y_paddle = Game.getInstance().getPaddle().getY();
			if (Game.getInstance().getManagerTimePwr().get(Utilities.PWR_LARGE_PADDLE)) {
				paddle.setDimX(200);
				g2d.drawImage(paddle.img2, x_paddle, y_paddle, paddle.dimX, paddle.dimY, null);
			} else {
				paddle.setDimX(Utilities.DIM_X_PADDLE);
				g2d.drawImage(paddle.img, x_paddle, y_paddle, paddle.dimX, paddle.dimY, null);
			}
			int x_ball = Game.getInstance().getBall().getX();
			int y_ball = Game.getInstance().getBall().getY();
			g2d.drawImage(ball.img, x_ball, y_ball, ball.dimX, ball.dimY, null);
			g2d.drawString("TARGET: " + Game.getInstance().getScore() + "/" + Game.getInstance().dimBricks(), 700, 850);

			ArrayList<Brick> bricks = Game.getInstance().getBrick();
			for (int i = 0; i < bricks.size(); i++) {
				if (!bricks.get(i).getDestroyed()) {
					if (bricks.get(i).getResistance() == Utilities.BRICK_RES_1) {
						// g2d.fillRect(bricks[i].getX(), bricks[i].getY(), Utilities.DIM_X_BRICK,
						// Utilities.DIM_Y_BRICK);
						g2d.drawImage(brick.img3, bricks.get(i).getX(), bricks.get(i).getY(), brick.dimX, brick.dimY,
								null);
					} else if (bricks.get(i).getResistance() == Utilities.BRICK_RES_2) {
						if (bricks.get(i).getLivesBrick() == 2)
							g2d.drawImage(brick.img, bricks.get(i).getX(), bricks.get(i).getY(), brick.dimX, brick.dimY,
									null);
						if (bricks.get(i).getLivesBrick() == 1)
							g2d.drawImage(brick.img2, bricks.get(i).getX(), bricks.get(i).getY(), brick.dimX,
									brick.dimY, null);
					} else {
						g2d.fillRect(bricks.get(i).getX(), bricks.get(i).getY(), Utilities.DIM_X_BRICK,
								Utilities.DIM_Y_BRICK);
					}
				}
			}
			ArrayList<Powerups> pwr = Game.getInstance().getPwr();
			for (int i = 0; i < pwr.size(); i++) {
				switch (pwr.get(i).getPower()) {
				case Utilities.PWR_LIFE:
					g2d.fillRect(pwr.get(i).getX(), pwr.get(i).getY(), Utilities.DIM_PWR, Utilities.DIM_PWR);
					break;
				case Utilities.PWR_LARGE_PADDLE:
					g2d.fillRect(pwr.get(i).getX(), pwr.get(i).getY(), Utilities.DIM_PWR, Utilities.DIM_PWR);
					break;
				case Utilities.PWR_FIREBALL:
					g2d.fillOval(pwr.get(i).getX(), pwr.get(i).getY(), Utilities.DIM_PWR, Utilities.DIM_PWR);
					break;
				case Utilities.NERF_VEL_BALL:
					g2d.drawOval(pwr.get(i).getX(), pwr.get(i).getY(), Utilities.DIM_PWR, Utilities.DIM_PWR);
					break;
				case Utilities.NERF_VEL_PADDLE:
					g2d.drawOval(pwr.get(i).getX(), pwr.get(i).getY(), Utilities.DIM_PWR, Utilities.DIM_PWR);
					break;
				default:
					return;
				}
			}
		}
		else {
			g2d.drawString("PAUSED", 400, 500);
		}
	}

	public void gameOver(Graphics2D g2d) {
		// DA SETTARE FONT, DIMENSIONE,
		g2d.drawString(Utilities.GAME_OVER, 400, 300);
	}

	public boolean loseLives(Graphics2D g2d) {
		int lives = Game.getInstance().getLives();
		if (lives == 0) {
			game = false;
			return true;
		}
		int cont = 0;
		while (lives > 0) {
			g2d.drawImage(ball.img, 50 + cont, 850, 20, 20, null);
			cont += 25;
			lives--;
		}
		return false;
	}

	public void update() {
		repaint();
	}

	public void setFirstTime(boolean firstTime) {
		this.firstTime = firstTime;
	}
}
