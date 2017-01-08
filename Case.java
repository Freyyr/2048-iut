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
	    this.color = new Color(0xc7de20);
	    break;
	case 4:
	    this.color = new Color(0xdeb620);
	    break;
	case 8 :
	    this.color = new Color(0xde7720);
	    break;
	case 16 :
	    this.color = new Color(0xde3720);
	    break;
	case 32 :
	    this.color = new Color(0xde2048);
	    break;
	case 64 :
	    this.color = new Color(0xde2087);
	    break;
	case 128:
	    this.color = new Color(0xde20c7);
	    break;
	case 256:
	    this.color = new Color(0xb620de);
	    break;
	case 512:
	    this.color = new Color(0x7720de);
	    break;
        case 1024:
	    this.color = new Color(0x3720de);
	    break;
        case 2048:
	    this.color = new Color(0x2048de);
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
}
