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
import application.model.Countdown;
import application.model.Game;
import application.model.Powerups;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = -2171231079412649632L;

	private PaddleView paddle = new PaddleView();
	private BallView ball = new BallView();
	private BrickView brick = new BrickView();
	private PowerupsView pwrView = new PowerupsView();
	private Image background = null;
	private Image gameOver = null;
	private Image paused = null;
	private Image levelUp = null;
	private Image win = null;
	private boolean game = true;
	private boolean firstTime = true;

	public GamePanel() {
		try {
			levelUp = ImageIO.read(getClass().getResourceAsStream( "/application/resources/backgrounds/levelUp.png"));
			gameOver = ImageIO.read(getClass().getResourceAsStream( "/application/resources/backgrounds/gameOver.png"));
			paused = ImageIO.read(getClass().getResourceAsStream( "/application/resources/backgrounds/pause.png"));
			win = ImageIO.read(getClass().getResourceAsStream( "/application/resources/backgrounds/win.png"));
			
		} catch (IOException e) {
			System.out.println("Errore nella lettura del file");
		} 
	}
	
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
		if (Game.getInstance().isLevelUp()) {
			g2d.drawImage(levelUp, 0, 0, null);
			return;
		}
		if (Game.getInstance().isWon()) {
			g2d.drawImage(win, 0, 0, null);
			return;
		}
		try {
			background = ImageIO.read(getClass().getResourceAsStream( "/application/resources/bgLevels/" + Game.getInstance().getLevel() + ".png"));
		} catch (IOException e) {
			System.out.println("Errore nella lettura del file");
		} 
		if (!Game.getInstance().isPause() || firstTime) {
			if (LevelLocked.getIstance().readAbout()) {
				g2d.drawString("COSE A CASO", 400, 500);
				return;
			}
			g2d.drawImage(background, 0, 0, null);
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
			if (Game.getInstance().getManagerTimePwr().get(Utilities.PWR_FIREBALL))
				g2d.drawImage(ball.fireball_img, x_ball, y_ball, ball.dimX, ball.dimY, null);
			else 
				g2d.drawImage(ball.img, x_ball, y_ball, ball.dimX, ball.dimY, null);
			g2d.setFont(new Font("Azonix", Font.PLAIN, 15));
			g2d.setColor(Color.red);
			g2d.drawString("TARGET: " + Game.getInstance().getScore() + "/" + Game.getInstance().dimBricks(), 650, 875);

			ArrayList<Brick> bricks = Game.getInstance().getBrick();
			for (int i = 0; i < bricks.size(); i++) {
				if (!bricks.get(i).getDestroyed()) {
					if (bricks.get(i).getResistance() == Utilities.BRICK_RES_1) {
						g2d.drawImage(brick.img.get(Game.getInstance().getLevel()), bricks.get(i).getX(), bricks.get(i).getY(), brick.dimX, brick.dimY, null);
					} else if (bricks.get(i).getResistance() == Utilities.BRICK_RES_2) {
						if (bricks.get(i).getLivesBrick() == 2)
							g2d.drawImage(brick.img1, bricks.get(i).getX(), bricks.get(i).getY(), brick.dimX, brick.dimY,
									null);
						else 
							g2d.drawImage(brick.img2, bricks.get(i).getX(), bricks.get(i).getY(), brick.dimX,
									brick.dimY, null);
					} else if (bricks.get(i).getResistance() == Utilities.BRICK_RES_3){
						if (bricks.get(i).getLivesBrick() == 3)
							g2d.drawImage(brick.img3, bricks.get(i).getX(), bricks.get(i).getY(), brick.dimX, brick.dimY, null);
						else if (bricks.get(i).getLivesBrick() == 2)
							g2d.drawImage(brick.img4, bricks.get(i).getX(), bricks.get(i).getY(), brick.dimX, brick.dimY, null);
						else 
							g2d.drawImage(brick.img5, bricks.get(i).getX(), bricks.get(i).getY(), brick.dimX, brick.dimY, null);	
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
					g2d.drawImage(pwrView.life_img,pwr.get(i).getX(), pwr.get(i).getY(), Utilities.DIM_X_PWR, Utilities.DIM_Y_PWR, null);
					break;
				case Utilities.PWR_LARGE_PADDLE:
					g2d.drawImage(pwrView.largePaddle_img,pwr.get(i).getX(), pwr.get(i).getY(), Utilities.DIM_X_PWR, Utilities.DIM_Y_PWR, null);
					break;
				case Utilities.PWR_FIREBALL:
					g2d.drawImage(pwrView.fireball_img,pwr.get(i).getX(), pwr.get(i).getY(), Utilities.DIM_X_PWR, Utilities.DIM_Y_PWR, null);
					break;
				case Utilities.NERF_VEL_BALL:
					g2d.drawImage(pwrView.fastBall_img,pwr.get(i).getX(), pwr.get(i).getY(), Utilities.DIM_X_PWR, Utilities.DIM_Y_PWR, null);
					break;
				case Utilities.NERF_VEL_PADDLE:
					g2d.drawImage(pwrView.slowPaddle_img,pwr.get(i).getX(), pwr.get(i).getY(), Utilities.DIM_X_PWR, Utilities.DIM_Y_PWR, null);
					break;
				default:
					return;
				}
			}
		}
		else {
			g2d.drawImage(paused, 0, 0, null);
		}
	}

	public void gameOver(Graphics2D g2d) {
		g2d.drawImage(gameOver, 0, 0, null);
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
