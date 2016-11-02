import java.util.Random;

public class Grille{

    private Case[][] matrix = new Case[4][4];

    public Grille(){
	//Constructeur de début de partie
	Case case1 = randomGenerate(this.nbCasePleine());
	matrix[case1.getX()][case1.getY()] = case1;
	/*Case case2 = randomGenerate(this.nbCasePleine());
	matrix[case2.getX()][case2.getY()] = case2;*/
    }


    /* public void moveLeft(){

    }

    public void moveRight(){

    }

    public void moveUp(){

    }

    public void moveDown(){

    }*/
    
    public String toString(){
	StringBuilder affichage = new StringBuilder();
	for(int y = 0; y < 4; y++){
	    for(int i = 0; i < 17; i++) affichage.append("-");
	    affichage.append("\n");
	    for(int x = 0; x < 4; x++){
		
		if(this.matrix[x][y] == null && x == 3){
		    affichage.append("|   | ");
		}
		else if(this.matrix[x][y] == null){
		    affichage.append("|   ");
		}
		else if(x == 3){
		    affichage.append("| " + this.matrix[x][y].getNum() + " | ");
		}
		else{
		    affichage.append("|  " + this.matrix[x][y].getNum());
		}
	    }
	    affichage.append("\n");    
	}
	for(int i = 0; i < 17; i++) affichage.append("-");
	return affichage.toString();
    }

    public Case randomGenerate(int nbCase){
	//Crée case 2 ou 4 aléatoirement
	int number, x = 0, y = 0, i = 1;
	Random alea = new Random();
	number = alea.nextInt(2); //Genere le nombre de la case (0=2 et 1=4)
	if(number == 0) number = 2;
	else if(number == 1) number = 4;
	int posInMatrix=alea.nextInt(16-nbCase); //16 case(0 a 15)- nbCase dans matrice = position dans matrice
	y++;
	while(i < posInMatrix){
	    if(matrix[x][y] == null){
		i++;
	    }
	    x++;
	    if(x > 3){
		y++;
		x = x%4;
	    }
    	}
	return new Case(number, x, y);
    }

    public int nbCasePleine(){
	//Compte le nombre de case pleine, plus complexe mais permet de ne pas avoir a faire une boucle imbriqué, donc plus rapide
	int i = 1, x = 0, y = 0, nbCase = 0;
	while(i < 17){
	    if(matrix[x][y] != null){
		nbCase++;
	    }
	    x++;
	    if(x > 3){
		y++;
		x = x%4;
	    }
	    i++;
    	}
	return nbCase;
    }
}
