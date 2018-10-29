package edu.colostate.cs.cs414.betterbytes.p3.ui;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import edu.colostate.cs.cs414.betterbytes.p3.utilities.Tools;

public class Mouse implements MouseListener, MouseMotionListener {

	private Point mouseLocation = new Point(-1, -1);
	private BufferPanel bf = null;

	public Mouse(BufferPanel bf) {
		this.bf = bf;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		setLocation(new Point(e.getX(), e.getY()));

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		setLocation(new Point(e.getX(), e.getY()));
		for (PaintButton b : bf.getButtons()) {
			if (b.getBounds().contains(getLocation())) {
				b.setMouseOver(true);
			} else {
				if (b.getMouseOver())
					b.setMouseOver(false);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		setLocation(new Point(e.getX(), e.getY()));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		setLocation(new Point(e.getX(), e.getY()));
		for (Cell c : bf.getGrid().getCells()) {
			if (c.getBounds().contains(this.getLocation())) {
				if (!bf.getGrid().isCellSelected()) {
					if (c.isSelected()) {
						c.setSelected(false);
					} else {
						c.setSelected(true);
						Tools.log("Selected: " + c.getX() + "," + c.getY() + "," + c.hasPiece());
					}
					break;
				} else {
					if (!c.equals(bf.getGrid().getSelectedCell())
							&& bf.getGrid().getSelectedCell().getPiece() != null) {
						// c.setPiece(bf.getGrid().getSelectedCell().getPiece());
						// bf.getGrid().getSelectedCell().setPiece(null);
						bf.getGame().movePiece(bf.getGrid().getSelectedCell().getPiece(),
								bf.getGrid().getSelectedCell(), c);
						bf.getGrid().clearSelected();
					} else {
						bf.getGrid().clearSelected();
					}
				}
			}
		}
		for (PaintButton b : bf.getButtons()) {
			if (b.getBounds().contains(getLocation())) {
				b.clicked();
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		setLocation(new Point(e.getX(), e.getY()));

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		setLocation(new Point(e.getX(), e.getY()));

	}

	@Override
	public void mouseExited(MouseEvent e) {
		setLocation(new Point(e.getX(), e.getY()));

	}

	public Point getLocation() {
		return mouseLocation;
	}

	public void setLocation(Point mouseLocation) {
		this.mouseLocation = mouseLocation;
	}

}
