package edu.colostate.cs.cs414.betterbytes.p4.client.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JPanel;

import edu.colostate.cs.cs414.betterbytes.p4.server.utilities.Tools;

public class BufferPanel extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String absPath = System.getProperty("user.dir") + "/src/";
	private Image img = null;
	private Mouse mouse = new Mouse(this);
	private GameFrame game = null;
	private Image background = Tools.getLocalImg(absPath + "edu/colostate/cs/cs414/betterbytes/p4/client/data/background.png");
	private ArrayList<PaintButton> buttons = new ArrayList<PaintButton>();

	public BufferPanel(GameFrame game) {
		this.game = game;
		buttons.add(new PaintButton("Send Move", 25, 855, game));
		buttons.add(new PaintButton("Revert", 104, 855, game));
	}

	public void paint(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		this.paintBackGround(g);
		this.paintCells(g);
		this.paintPieces(g); 
		this.paintButtons(g);
		this.paintStatus(g);
		// Tools.drawSharpText("" + mouse.getLocation(), 15, 15, Color.red,
		// Color.black, g);
	}

	/**
	 * This method paints the cells, it is long because of the effects and
	 * offsetting I use to shadow and emphasize certain cells. Will definitely
	 * generate Code Smells because of similar repeated code
	 * 
	 * @param g Graphics object
	 */
	public void paintCells(Graphics g) {
		for (Cell c : this.getGrid().getCells()) {

			g.setColor(Color.GRAY);
			if (c.isWhiteCell()) {
				g.setColor(new Color(81, 100, 114));
			}

			// CELL ICON
			g.fillRect(this.getGrid().getBaseX() + (c.getX() * c.getSize()),
					this.getGrid().getBaseY() + (c.getY() * c.getSize()), c.getSize(), c.getSize());
			if (c.getIcon() != null) {
				g.drawImage(c.getIcon(), c.getRealX() + 1, c.getRealY() + 1, null);
			}

			// OUTLINE
			g.setColor(new Color(0, 0, 0, 200));
			g.drawRect(this.getGrid().getBaseX() + (c.getX() * c.getSize()),
					this.getGrid().getBaseY() + (c.getY() * c.getSize()), c.getSize(), c.getSize());
			g.drawRect(this.getGrid().getBaseX() + (c.getX() * c.getSize()) + 1,
					this.getGrid().getBaseY() + (c.getY() * c.getSize()) + 1, c.getSize(), c.getSize());

			// SELECTION
			if (c.isSelected()) {
				g.setColor(new Color(83, 183, 25, 85));
				g.fillRect(this.getGrid().getBaseX() + (c.getX() * c.getSize()) + 1,
						this.getGrid().getBaseY() + (c.getY() * c.getSize()) + 1, c.getSize() - 1, c.getSize() - 1);
				g.setColor(new Color(255, 255, 255, 200));
				g.drawRect(this.getGrid().getBaseX() + (c.getX() * c.getSize()) + 1,
						this.getGrid().getBaseY() + (c.getY() * c.getSize()) + 1, c.getSize() - 2, c.getSize() - 2);
			}

			if (this.getGrid().isCellSelected()) {
				Cell selected = this.getGrid().getSelectedCell();
				if (game.canMove(selected, c.getX(), c.getY())) {
					g.setColor(new Color(0, 255, 55, 55));
					g.fillRect(this.getGrid().getBaseX() + (c.getX() * c.getSize()),
							this.getGrid().getBaseY() + (c.getY() * c.getSize()), c.getSize(), c.getSize());
					g.setColor(new Color(0, 0, 0, 200));
					g.drawRect(this.getGrid().getBaseX() + (c.getX() * c.getSize()),
							this.getGrid().getBaseY() + (c.getY() * c.getSize()), c.getSize(), c.getSize());
					g.drawRect(this.getGrid().getBaseX() + (c.getX() * c.getSize()) + 1,
							this.getGrid().getBaseY() + (c.getY() * c.getSize()) + 1, c.getSize(), c.getSize());
				}
			}
		}
	}

	/**
	 * Method for painting the Pieces of the board
	 * 
	 * @param g Graphics
	 */
	public void paintPieces(Graphics g) {
		for (Cell c : this.getGrid().getCells()) {
			if (c.hasPiece() && c.getPiece().getIcon() != null) {
				// g.setColor(Color.BLUE);
				// g.fillRect(c.getRealX() + 10, c.getRealY() + 10, 20, 20);
				g.drawImage(c.getPiece().getIcon(), c.getRealX() - 1, c.getRealY() - 2, 83, 83, null);
			}
		}
	}

	public void paintStatus(Graphics g) {
		g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		g.setColor(Color.RED);
		// 305,862
		Tools.drawSharpText(game.getStatus(), 315, 883, Color.RED, Color.BLACK, g);
	}

	public void paintButtons(Graphics g) {
		for (PaintButton b : this.buttons) {
			b.paint(g);
		}
	}

	public void paintBackGround(Graphics g) {
		if (this.background != null) {
			g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
		}
	}

	@Override
	public void run() {
		this.addMouseListener(getMouse());
		this.addMouseMotionListener(getMouse());
		while (true) {
			if (this.getParent() != null) {
				this.img = this.createImage(super.getWidth(), super.getHeight());
				if (this.img != null && this.img.getGraphics() != null)
					this.paint(this.img.getGraphics());
				if (this.getGraphics() != null && img != null)
					this.getGraphics().drawImage(img, 0, 0, null);
			}
			Tools.sleep(65);
		}
	}

	public Mouse getMouse() {
		return this.mouse;
	}

	public Grid getGrid() {
		return game.getGrid();
	}

	public ArrayList<PaintButton> getButtons() {
		return this.buttons;
	}

	public GameFrame getGame() {
		return this.game;
	}

}
