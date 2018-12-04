package edu.colostate.cs.cs414.betterbytes.p4.client.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;

import edu.colostate.cs.cs414.betterbytes.p4.server.utilities.Tools;

/**
 * Author: Daniel McClure 830437441 This class acts as a button, it is
 * referenced in the Mouse class
 * 
 */
public class PaintButton implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String text = null;
	private int x, y;
	private Color fill = null;
	private int width = 70;
	private int height = 35;
	private boolean mo = false;
	private GameFrame game = null;
	private boolean disabled = false;

	/**
	 * Constructor for button
	 * 
	 * @param String text that will be displayed.
	 * @param        int x coordinate
	 * @param        int y coordinate
	 * @param Game   class reference needed for executing actions
	 */
	public PaintButton(String text, int x, int y, GameFrame game) {
		this.setText(text);
		this.setX(x);
		this.setY(y);
		this.setFill(new Color(0, 0, 0, 180));
		this.width = 9 * text.length();
		this.game = game;
	}

	/**
	 * This method receives graphics and paints this button
	 * 
	 * @param g is the Graphics object being used
	 */
	public void paint(Graphics g) {
		if (fill != null) {
			g.setColor(fill);
			if (mo)
				g.setColor(new Color(255, 255, 255, 200));
			g.fillRect(getX(), getY(), width, height);
		}
		g.setColor(new Color(255, 255, 255, 150));
		g.drawRect(getX(), getY(), width - 1, height - 1);
		if (!this.disabled) {
			Tools.drawSharpText(getText(), x + (this.width / 7), y + 22, Color.white, Color.BLACK, g);
		} else {
			Tools.drawSharpText(getText(), x + (this.width / 7), y + 22, Color.BLACK, Color.BLACK, g);
		}
	}

	/**
	 * @return if mouse is over button
	 */
	public boolean getMouseOver() {
		return this.mo;
	}

	public void setMouseOver(boolean mo) {
		this.mo = mo;
	}

	public void setFill(Color c) {
		this.fill = c;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Rectangle getBounds() {
		return new Rectangle(this.getX(), this.getY(), this.width, this.height);
	}

	/**
	 * This method is called when the button is clicked, this is essentially the
	 * action listener
	 */
	public void clicked() {
		if (!game.gameover()) {
			switch (this.getText()) {
			case "Send Move":
				if (game.isSecondCheck() && !disabled) {
					Tools.log("Sending Move");
					disabled = true;
					game.setSecondCheck(false);
					if (game != null && game.sendMoveToServer()) {
						game.setStatus("Move Sent!");
						game.moveSent = true;
					} else {
						game.setStatus("Connection failed, Try again!");
						disabled = false;
					}
				} else {
					game.setStatus("Are you sure?");
					game.setSecondCheck(true);
				}
				break;
			case "Revert":
				Tools.log("Reverting last move");
				game.getGrid().revertLastMove();
				break;
			}
		}
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

}
