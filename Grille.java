import java.util.Random;
import java.util.Scanner;

public class Grille{

    private Case[][] matrix = new Case[4][4];

    public Grille(){
	//Constructeur de début de partie
	Case case1 = randomGenerate(this.nbCasePleine());
	matrix[case1.getX()][case1.getY()] = case1;
	Case case2 = randomGenerate(this.nbCasePleine());
	matrix[case2.getX()][case2.getY()] = case2;
    }

    public void partie(){
	Scanner sc = new Scanner(System.in); 
	System.out.println(this.toString());
	while(! gameWin() && ! gameLost()){
	    System.out.println("Appuyez sur z, q, s, d pour bouger la grille");
	    char commande = sc.next().charAt(0);
	    if(commande == 'z'){
		this.moveUp();
	    }
	    else if(commande == 'q'){
		this.moveLeft();
	    }
	    else if(commande == 's'){
		this.moveDown();
	    }
	    else if(commande == 'd'){
		this.moveRight();
	    }
	    else System.out.println("Veuillez saisir une entrée valide !");
	    System.out.println(this.toString());
	}
	
    }
	
    public void moveLeft(){
	int x = 0, y = 0, a, b;
	for(y = 0; y < 4; y++){
	    for(x = 0; x < 3; x++){
		b = 0;
		for(a = x ; a < 4; a++){
		    if(this.matrix[a][y] != null) {
			b = 1;
		    }
		}
		if(b == 1){
		    while(this.matrix[x][y] == null){
			if(x == 0){
			    this.matrix[x][y] = this.matrix[x+1][y];
			    this.matrix[x+1][y] = this.matrix[x+2][y];
			    this.matrix[x+2][y] = this.matrix[x+3][y];
			    this.matrix[x+3][y] = null;
			}
			else if(x == 1){
			    this.matrix[x][y] = this.matrix[x+1][y];
			    this.matrix[x+1][y] = this.matrix[x+2][y];
			    this.matrix[x+2][y] = null;
			}
			else if(x == 2){
			    this.matrix[x][y] = this.matrix[x+1][y];
			    this.matrix[x+1][y] = null;
			}
		    }
		}
	    }
	    for(x = 0; x < 3; x++){
		if((x == 0) && (this.matrix[x+1][y] != null) && (this.matrix[x][y].getNum() == this.matrix[x+1][y].getNum())){
		    this.matrix[x][y].setNum(this.matrix[x][y].getNum()*2);
		    this.matrix[x+1][y] = this.matrix[x+2][y];
		    this.matrix[x+2][y] = this.matrix[x+3][y];
		    this.matrix[x+3][y] = null;
		}
		else if((x == 2) &&  (this.matrix[x+1][y] != null) && (this.matrix[x][y].getNum() == this.matrix[x+1][y].getNum())){
		    this.matrix[x][y].setNum(this.matrix[x][y].getNum()*2);
		    this.matrix[x+1][y] = null;
		}
	    }
	}
	Case case1 = randomGenerate();
	matrix[case1.getX()][case1.getY()] = case1;
    }
    
    public void moveRight(){
	int x = 3, y = 0, a, b;
	for(y = 0; y < 4; y++){
	    for(x = 3; x > 0 ; x--){
		b = 0;
		for(a = x ; a >= 0; a--){
		    if(this.matrix[a][y] != null) {
			b = 1;
		    }
		}
		if(b == 1){
		    while(this.matrix[x][y] == null){
			if(x == 3){
			    this.matrix[x][y] = this.matrix[x-1][y];
			    this.matrix[x-1][y] = this.matrix[x-2][y];
			    this.matrix[x-2][y] = this.matrix[x-3][y];
			    this.matrix[x-3][y] = null;
			}
			else if(x == 2){
			    this.matrix[x][y] = this.matrix[x-1][y];
			    this.matrix[x-1][y] = this.matrix[x-2][y];
			    this.matrix[x-2][y] = null;
			}
			else if(x == 3){
			    this.matrix[x][y] = this.matrix[x-1][y];
			    this.matrix[x-1][y] = null;
			}
		    }
		}
	    }
	    for(x = 3; x > 0; x--){
		if((x == 3) && (this.matrix[x-1][y] != null) && (this.matrix[x][y].getNum() == this.matrix[x-1][y].getNum())){
		    this.matrix[x][y].setNum(this.matrix[x][y].getNum()*2);
		    this.matrix[x-1][y] = this.matrix[x-2][y];
		    this.matrix[x-2][y] = this.matrix[x-3][y];
		    this.matrix[x-3][y] = null;
		}
		else if((x == 1) &&  (this.matrix[x-1][y] != null) && (this.matrix[x][y].getNum() == this.matrix[x-1][y].getNum())){
		    this.matrix[x][y].setNum(this.matrix[x][y].getNum()*2);
		    this.matrix[x-1][y] = null;
		}
	    }
	}
	Case case1 = randomGenerate();
	matrix[case1.getX()][case1.getY()] = case1;
    }

    public void moveUp(){
	int x = 0, y = 0, a, b;
	for(x = 0; x < 4; x++){
	    for(y = 0; y < 3; y++){
		b = 0;
		for(a = y ; a < 4; a++){
		    if(this.matrix[x][a] != null) {
			b = 1;
		    }
		}
		if(b == 1){
		    while(this.matrix[x][y] == null){
			if(y == 0){
			    this.matrix[x][y] = this.matrix[x][y+1];
			    this.matrix[x][y+1] = this.matrix[x][y+2];
			    this.matrix[x][y+2] = this.matrix[x][y+3];
			    this.matrix[x][y+3] = null;
			}
			else if(y == 1){
			    this.matrix[x][y] = this.matrix[x][y+1];
			    this.matrix[x][y+1] = this.matrix[x][y+2];
			    this.matrix[x][y+2] = null;
			}
			else if(y == 2){
			    this.matrix[x][y] = this.matrix[x][y+1];
			    this.matrix[x][y+1] = null;
			}
		    }
		}
	    }
	    for(y = 0; y < 3; y++){
		if((y == 0) && (this.matrix[x][y+1] != null) && (this.matrix[x][y].getNum() == this.matrix[x][y+1].getNum())){
		    this.matrix[x][y].setNum(this.matrix[x][y].getNum()*2);
		    this.matrix[x][y+1] = this.matrix[x][y+2];
		    this.matrix[x][y+2] = this.matrix[x][y+3];
		    this.matrix[x][y+3] = null;
		}
		else if((y == 2) &&  (this.matrix[x][y+1] != null) && (this.matrix[x][y].getNum() == this.matrix[x][y+1].getNum())){
		    this.matrix[x][y].setNum(this.matrix[x][y].getNum()*2);
		    this.matrix[x][y+1] = null;
		}
	    }
	}
	Case case1 = randomGenerate();
	matrix[case1.getX()][case1.getY()] = case1;
    }

    public void moveDown(){
	int x = 0, y = 0, a, b;
	for(x = 0; x < 4; x++){
	    for(y = 3; y > 0; y--){
		b = 0;
		for(a = y ; a >= 0 ; a--){
		    if(this.matrix[x][a] != null) {
			b = 1;
		    }
		}
		if(b == 1){
		    while(this.matrix[x][y] == null){
			if(y == 3){
			    this.matrix[x][y] = this.matrix[x][y-1];
			    this.matrix[x][y-1] = this.matrix[x][y-2];
			    this.matrix[x][y-2] = this.matrix[x][y-3];
			    this.matrix[x][y-3] = null;
			}
			else if(y == 2){
			    this.matrix[x][y] = this.matrix[x][y-1];
			    this.matrix[x][y-1] = this.matrix[x][y-2];
			    this.matrix[x][y-2] = null;
			}
			else if(y == 1){
			    this.matrix[x][y] = this.matrix[x][y-1];
			    this.matrix[x][y-1] = null;
			}
		    }
		}
	    }
	    for(y = 3; y > 0; y--){
		if((y == 3) && (this.matrix[x][y-1] != null) && (this.matrix[x][y].getNum() == this.matrix[x][y-1].getNum())){
		    this.matrix[x][y].setNum(this.matrix[x][y].getNum()*2);
		    this.matrix[x][y-1] = this.matrix[x][y-2];
		    this.matrix[x][y-2] = this.matrix[x][y-3];
		    this.matrix[x][y-3] = null;
		}
		else if((y == 1) &&  (this.matrix[x][y-1] != null) && (this.matrix[x][y].getNum() == this.matrix[x][y-1].getNum())){
		    this.matrix[x][y].setNum(this.matrix[x][y].getNum()*2);
		    this.matrix[x][y-1] = null;
		}
	    }
	}
	Case case1 = randomGenerate();
	matrix[case1.getX()][case1.getY()] = case1;
    }

    public boolean gameLost(){
	//Check si la partie est perdue return 1 si non et 0 si oui
	int nbCaseVide = 0;
	for(int x = 0; x < 4; x++){
	    for(int y = 0; y < 4; y++){
		if(this.matrix[x][y] == null) nbCaseVide++;
	    }
	}
	if(nbCaseVide == 0){
	    System.out.println("Vous avez perdu !");
	    return true;
	}
	return false;
    }

    public boolean gameWin(){
	for(int x = 0; x < 4; x++){
	    for(int y = 0; y < 4; y++){
		if(this.matrix[x][y] != null && this.matrix[x][y].getNum() == 2048) {
		    System.out.println("Vous avez gagné");
		    return true;
		}
	    }
	}
	return false;
    }
    
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

    public Case randomGenerate(){
	//Crée case 2 ou 4 aléatoirement
	int number, x = 0, y = 0, i = 1;
	Random alea = new Random();
	number = alea.nextInt(2); //Genere le nombre de la case (0=2 et 1=4)
	if(number == 0) number = 2;
	else if(number == 1) number = 4;
	int posInMatrix = alea.nextInt(16-this.nbCasePleine()); //16 case(0 a 15)- nbCase dans matrice = position dans matrice
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
	int x, y, nbCase = 0;
	for(y = 0; y < 4; y++){
	    for(x = 0; x < 4; x++){
		if(this.matrix[x][y] != null) nbCase++;
	    }
	}
	return nbCase;
    }
}
