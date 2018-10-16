package edu.colostate.cs.cs414.p1.betterbytes.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JPanel;

import edu.colostate.cs.cs414.p1.betterbytes.utilities.Tools;

public class BufferPanel extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Image img = null;
	private Mouse mouse = new Mouse(this);
	private Game game = null;
	private Image background = Tools.getLocalImg("data/background.png");
	private ArrayList<PaintButton> buttons = new ArrayList<PaintButton>();

	public BufferPanel(Game game) {
		this.game = game;
		buttons.add(new PaintButton("Send Move", 25, 855, game));
		buttons.add(new PaintButton("Revert", 104, 855, game));
	}

	public void paint(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		this.paintBackGround(g);
		game.getGrid().paint(g);
		this.paintButtons(g);
		this.paintStatus(g);
		// Tools.drawSharpText("" + mouse.getLocation(), 15, 15, Color.red,
		// Color.black, g);
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
				this.paint(this.img.getGraphics());
				this.getGraphics().drawImage(img, 0, 0, null);
			}
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

	public Game getGame() {
		return this.game;
	}

}
