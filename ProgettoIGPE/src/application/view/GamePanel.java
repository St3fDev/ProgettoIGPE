package application.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;
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
	private PowerupsView pwrView = new PowerupsView();
	private Image background = null;
	private boolean game = true;
	private boolean firstTime = true;
	// private CardLayout card = new CardLayout();
	// private JPanel pauseGame = new JPanel();

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		var g2d = (Graphics2D) g;
		setFont();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		if (game) {
			inGame(g2d);
		} else {
			gameOver(g2d);
		}

	}

	public void inGame(Graphics2D g2d) {
		if (Game.getInstance().isWon()) {
			g2d.drawString("YOU COMPLETED THE GAME", 400, 500);
			return;
		}
		if (!Game.getInstance().isPause() || firstTime) {
			try {
				background = ImageIO.read(getClass().getResourceAsStream( "/application/resources/bgLevels/prova.png"));
				g2d.drawImage(background, 0, 0, null);
				
			} catch (IOException e) {
				System.out.println("Errore nella lettura del file");
			} 
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
			g2d.setFont(new Font("Azonix", Font.PLAIN, 15));
			g2d.setColor(Color.red);
			g2d.drawString("TARGET: " + Game.getInstance().getScore() + "/" + Game.getInstance().dimBricks(), 650, 875);

			ArrayList<Brick> bricks = Game.getInstance().getBrick();
			for (int i = 0; i < bricks.size(); i++) {
				if (!bricks.get(i).getDestroyed()) {
					if (bricks.get(i).getResistance() == Utilities.BRICK_RES_1) {
						g2d.drawImage(brick.img3, bricks.get(i).getX(), bricks.get(i).getY(), brick.dimX, brick.dimY, null);
					} else if (bricks.get(i).getResistance() == Utilities.BRICK_RES_2) {
						if (bricks.get(i).getLivesBrick() == 2)
							g2d.drawImage(brick.img1, bricks.get(i).getX(), bricks.get(i).getY(), brick.dimX, brick.dimY,
									null);
						if (bricks.get(i).getLivesBrick() == 1)
							g2d.drawImage(brick.img2, bricks.get(i).getX(), bricks.get(i).getY(), brick.dimX,
									brick.dimY, null);
					} else if (bricks.get(i).getResistance() == Utilities.BRICK_RES_3){
						g2d.fillRect(bricks.get(i).getX(), bricks.get(i).getY(), Utilities.DIM_X_BRICK,
								Utilities.DIM_Y_BRICK);
					}
					else if (bricks.get(i).getResistance() == Utilities.BRICK_LIGHT) {
						if (bricks.get(i).getLivesBrick() == Utilities.BRICK_LIGHT)
							g2d.fillRect(bricks.get(i).getX(), bricks.get(i).getY(), Utilities.DIM_X_BRICK, Utilities.DIM_Y_BRICK);
						else 
							g2d.drawRect(bricks.get(i).getX(), bricks.get(i).getY(), Utilities.DIM_X_BRICK, Utilities.DIM_Y_BRICK);
					}
				}
			}
			ArrayList<Powerups> pwr = Game.getInstance().getPwr();
			for (int i = 0; i < pwr.size(); i++) {
				switch (pwr.get(i).getPower()) {
				case Utilities.PWR_LIFE:
					g2d.fillRect(pwr.get(i).getX(), pwr.get(i).getY(), Utilities.DIM_X_PWR, 25);
					break;
				case Utilities.PWR_LARGE_PADDLE:
					g2d.fillRect(pwr.get(i).getX(), pwr.get(i).getY(), Utilities.DIM_X_PWR, Utilities.DIM_Y_PWR);
					break;
				case Utilities.PWR_FIREBALL:
					g2d.fillRect(pwr.get(i).getX(), pwr.get(i).getY(), Utilities.DIM_X_PWR, Utilities.DIM_Y_PWR);
					break;
				case Utilities.NERF_VEL_BALL:
					g2d.drawImage(pwrView.fastBall_img,pwr.get(i).getX(), pwr.get(i).getY(), Utilities.DIM_X_PWR, Utilities.DIM_Y_PWR, null);
					break;
				case Utilities.NERF_VEL_PADDLE:
					g2d.drawOval(pwr.get(i).getX(), pwr.get(i).getY(), Utilities.DIM_X_PWR, Utilities.DIM_Y_PWR);
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
		g2d.setFont(new Font("Azonix", Font.PLAIN, 15));
		g2d.setColor(Color.red);
		g2d.drawString("LIVES:", 22, 875);
		while (lives > 0) {
			g2d.drawImage(ball.img, 80 + cont, 860, 20, 20, null);
			cont += 25;
			lives--;
		}
		return false;
	}
	
	public void setFont() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		try {
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Font/Azonix.otf")));
		} catch (Exception e) {
			System.out.println("Impossibile caricare il font");
		}
	}

	public void update() {
		repaint();
	}

	public void setFirstTime(boolean firstTime) {
		this.firstTime = firstTime;
	}
	
	public void setGame(boolean game) {
		this.game = game;
	}
}
