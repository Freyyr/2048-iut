public class Case{

    private int num;
    private int x;
    private int y;

    public Case(){
	//Constructeur vide
    }

    public Case(int number, int x, int y){
	this.num = number;
	this.x = x;
	this.y = y;
    }

    public int getNum(){
	return this.num;
    }

    public void setNum(int number){
	this.num = number;
    }

    public int getX(){
	return this.x;
    }

    public int getY(){
	return this.y;
    }
}
