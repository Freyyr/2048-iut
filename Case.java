import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.*;

public class Case {

    private int value;
    private int x;
    private int y;

    public Case(){
    }

    public Case(int value, int x, int y){
	this.value = value;
	this.x = x;
	this.y = y;
    }

    public int getX(){
	return this.x;
    }

    public int getY(){
	return this.y;
    }

    public int getNum(){
	return this.value;
    }

    public void setNum(int num){
	this.value = num;
    }
}
