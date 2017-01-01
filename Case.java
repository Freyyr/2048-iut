import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.*;

public class Case {

    private int value;
    private Color color;
    private int x;
    private int y;

    public Case(){
    }

    public Case(int value, int x, int y){
	this.value = value;
	this.x = x;
	this.y = y;
	switch(value){
	case 2:
	    this.color = Color.YELLOW;
	    break;
	case 4:
	    this.color = Color.ORANGE;
	    break;
	case 8 :
	    this.color = Color.ORANGE;
	    break;
	case 16 :
	    this.color = Color.RED;
	    break;
	case 32 :
	    this.color = Color.BLUE;
	    break;
	case 64 :
	    this.color = Color.GREEN;
	    break;
	case 128:
	    this.color = Color.PINK;
	    break;
	}
    }

    public int getX(){
	return this.x;
    }

    public int getY(){
	return this.y;
    }

    public Color getColor(){
	return this.color;
    }

    public int getNum(){
	return this.value;
    }

    public void setNum(int num){
	this.value = num;
    }

	
    
    /*public Case(int value) {
      switch (value) {
      case 2:
      defaultPanel.setBackground(Color.ORANGE);
      JLabel a = new JLabel("2",SwingConstants.CENTER);
      defaultPanel.add(a);
      break;
      case 4:
      defaultPanel.setBackground(Color.YELLOW);
      JLabel b = new JLabel("4");
      defaultPanel.add(b);
      break;
      case 8:
      defaultPanel.setBackground(Color.GREEN);
      JLabel c = new JLabel("8");
      defaultPanel.add(c);
      break;
      case 16:
      defaultPanel.setBackground(Color.CYAN);
      JLabel d = new JLabel("16");
      defaultPanel.add(d);
      break;
      case 32:
      defaultPanel.setBackground(Color.BLUE);
      JLabel e = new JLabel("32");
      defaultPanel.add(e);
      break;
      case 64:
      defaultPanel.setBackground(Color.RED);
      JLabel f = new JLabel("64");
      defaultPanel.add(f);
      break;
      case 128:
      defaultPanel.setBackground(Color.MAGENTA);
      JLabel g = new JLabel("128");
      defaultPanel.add(g);
      break;
      case 256:
      defaultPanel.setBackground(Color.PINK);
      JLabel h = new JLabel("256");
      defaultPanel.add(h);
      break;
      case 512:
      defaultPanel.setBackground(new Color(153, 0, 153));
      JLabel i = new JLabel("512");
      defaultPanel.add(i);
      break;
      case 1024:
      defaultPanel.setBackground(new Color(102, 51, 0));
      JLabel j = new JLabel("1024");
      defaultPanel.add(j);
      break;
      case 2048:
      defaultPanel.setBackground(new Color(128, 128, 128));
      JLabel k = new JLabel("2048");
      defaultPanel.add(k);
      break;
      }
      }*/
}
