/*
 * Line
 * models a straight line
 * (C) 2025 Papadopol Lucian-Ioan 
 * All rights reserved
 */
package entities;
import java.awt.*;

// Modello l'oggetto linea

public class Line {
	private int x1, y1, x2, y2;
	private Color color;
	private int thickness;

	public Line(int x1, int y1, int x2, int y2, Color color, int thickness) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.color = color;
		this.thickness = thickness;
	}

	public int getX1() { 
		return x1; 
	}

	public int getY1() { 
		return y1; 
	}

	public int getX2() { 
		return x2; 
	}

	public int getY2() { 
		return y2; 
	}

	public Color getColor() { 
		return color; 
	}
	
	public int getThickness() {
		return thickness;
	}

}
