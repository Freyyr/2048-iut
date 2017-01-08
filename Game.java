import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class Game extends JPanel{

    private Case[][] matrix = new Case[4][4];
    private static Font stringFont = new Font("Arial", Font.PLAIN, 18);
    private static int SIZE = 100;
    private static JFrame game = new JFrame();
    private static final int MARGE = 10;
    
    public Game(){
	setFocusable(true);
	addKeyListener(new KeyAdapter(){
		@Override
		public void keyPressed(KeyEvent e){
		    if(! gameLost() && !gameWin()){
			switch(e.getKeyCode()){
			case KeyEvent.VK_LEFT :
			    moveLeft();
			    break;
			case KeyEvent.VK_RIGHT :
			    moveRight();
			    break;
			case KeyEvent.VK_DOWN :
			    moveDown();
			    break;
			case KeyEvent.VK_UP :
			    moveUp();
			    break;
			}
		    }
		    repaint();
		}
	    });
	restart();
    }

M	matrix = new Case[4][4];
	
	Case case1 = this.randomGenerate();
	matrix[case1.getX()][case1.getY()] = case1;
	
	Case case2 = this.randomGenerate();
	matrix[case2.getX()][case2.getY()] = case2;
    }
    
    @Override
    public void paint(Graphics g){
	super.paint(g);
	g.setColor(new Color(0xbbada0));
	g.fillRect(0, 0, 450, 450);
	for(int y = 0; y < 4; y++){
	    for(int x = 0; x < 4; x++){
		draw(g, matrix[x][y], x, y);
	    }
	}
    }

    public void draw(Graphics g2, Case case1, int x, int y){
	Graphics2D g = ((Graphics2D) g2);
	if(case1 != null){
	    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
	    int num = case1.getNum();
	    String value = Integer.toString(case1.getNum());

	    //print case
	    g.setColor(getColor(case1));
	    g.fillRoundRect(x*SIZE+MARGE+x*MARGE, y*SIZE+MARGE+y*MARGE, SIZE, SIZE, MARGE, MARGE);

	    //print int de la case
	    g.setColor(Color.BLACK);
	    g.setFont(stringFont);
	    if(num < 10){
		g.drawString(value, x*SIZE+MARGE+x*MARGE + 45 , y*SIZE+MARGE+y*MARGE + 55);
	    }
	    else if(num < 100){
		g.drawString(value, x*SIZE+MARGE+x*MARGE + 36 , y*SIZE+MARGE+y*MARGE + 55);
	    }
	    else if(num < 1000){
		g.drawString(value, x*SIZE+MARGE+x*MARGE + 32 , y*SIZE+MARGE+y*MARGE + 55);
	    }
	    else{
		g.drawString(value, x*SIZE+MARGE+x*MARGE + 24 , y*SIZE+MARGE+y*MARGE + 55);
	    }
	}
	else{
	    g.setColor(Color.GRAY);
	    g.fillRoundRect(x*SIZE+MARGE+MARGE*x, y*SIZE+MARGE+MARGE*y, SIZE, SIZE, MARGE, MARGE);
	}
    }
    	
    public void moveLeft(){
	//L'objectif est de deplacer les cases des chacunes des lignes vers la gauche et de
	//les fusionner si leurs valeurs est la meme
	int x = 0, y = 0, a, b;
	boolean actionDone = false;
	for(y = 0; y < 4; y++){ //On parcourt lignes par lignes 
	    for(x = 0; x < 3; x++){ //Case par case
		b = 0;
		for(a = x ; a < 4; a++){
		    if(this.matrix[a][y] != null) { //On verifie qu'apres la case x(avance au fur et a mesure) il existe bien encore au moins une case non vide
			b = 1;
		    }
		}
		if(b == 1){ // Si il existe au moins encore une case non vide
		    while(this.matrix[x][y] == null){ //alors tant que la case x est vide on deplace toutes les cases suivantes vers la gauche (dépend de la case x)
			if(x == 0){ 
			    this.matrix[x][y] = this.matrix[x+1][y];
			    this.matrix[x+1][y] = this.matrix[x+2][y];
			    this.matrix[x+2][y] = this.matrix[x+3][y];
			    this.matrix[x+3][y] = null;
			    actionDone = true;
			}
			else if(x == 1){
			    this.matrix[x][y] = this.matrix[x+1][y];
			    this.matrix[x+1][y] = this.matrix[x+2][y];
			    this.matrix[x+2][y] = null;
			    actionDone = true;
			}
			else if(x == 2){
			    this.matrix[x][y] = this.matrix[x+1][y];
			    this.matrix[x+1][y] = null;
			    actionDone = true;
			}
		    }
		}
	    }
	    for(x = 0; x < 3; x++){//On s'occupe maintenant de la fusion des cases identiques
		//la fusion n'est possible ssi on est sur les cases 0 ou 2 et que la case x+1 est non null et que les cases x et x+1 ont la meme valeurs
		if((x == 0) && (this.matrix[x+1][y] != null) && (this.matrix[x][y].getNum() == this.matrix[x+1][y].getNum())){
		    this.matrix[x][y].setNum(this.matrix[x][y].getNum()*2);
		    this.matrix[x+1][y] = this.matrix[x+2][y];
		    this.matrix[x+2][y] = this.matrix[x+3][y];
		    this.matrix[x+3][y] = null;
		    actionDone = true;
		}
		else if((x==1) && (this.matrix[x+1][y] != null) && (this.matrix[x][y].getNum() == this.matrix[x+1][y].getNum())){
		    this.matrix[x][y].setNum(this.matrix[x][y].getNum()*2);
		    this.matrix[x+1][y] = this.matrix[x+2][y];
		    this.matrix[x+2][y] = null;
		    actionDone = true;
		}
		else if((x == 2) &&  (this.matrix[x+1][y] != null) && (this.matrix[x][y].getNum() == this.matrix[x+1][y].getNum())){
		    this.matrix[x][y].setNum(this.matrix[x][y].getNum()*2);
		    this.matrix[x+1][y] = null;
		    actionDone = true;
		}
	    }
	};
	if(actionDone == true){
	    Case case1 = this.randomGenerate();// On créé une nouvelle case aleatoirement(2 ou 4) a chaque mouvement du joueur
	    matrix[case1.getX()][case1.getY()] = case1;
	}
    }
    
    public void moveRight(){
	int x = 3, y = 0, a, b;
	boolean actionDone = false;
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
			    actionDone = true;
			}
			else if(x == 2){
			    this.matrix[x][y] = this.matrix[x-1][y];
			    this.matrix[x-1][y] = this.matrix[x-2][y];
			    this.matrix[x-2][y] = null;
			    actionDone = true;
			}
			else if(x == 1){
			    this.matrix[x][y] = this.matrix[x-1][y];
			    this.matrix[x-1][y] = null;
			    actionDone = true;
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
		    actionDone = true;
		}
		else if((x == 2) && (this.matrix[x-1][y] != null) && (this.matrix[x][y].getNum() == this.matrix[x-1][y].getNum())){
		    this.matrix[x][y].setNum(this.matrix[x][y].getNum()*2);
		    this.matrix[x-1][y] = this.matrix[x-2][y];
		    this.matrix[x-2][y] = null;
		    actionDone = true;
		}
		else if((x == 1) &&  (this.matrix[x-1][y] != null) && (this.matrix[x][y].getNum() == this.matrix[x-1][y].getNum())){
		    this.matrix[x][y].setNum(this.matrix[x][y].getNum()*2);
		    this.matrix[x-1][y] = null;
		    actionDone = true;
		}
	    }
	}
	if(actionDone == true){
	    Case case1 = this.randomGenerate();
	    matrix[case1.getX()][case1.getY()] = case1;
	}
    }

    public void moveUp(){
	int x = 0, y = 0, a, b;
	boolean actionDone = false;
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
			    actionDone = true;
			}
			else if(y == 1){
			    this.matrix[x][y] = this.matrix[x][y+1];
			    this.matrix[x][y+1] = this.matrix[x][y+2];
			    this.matrix[x][y+2] = null;
			    actionDone = true;
			}
			else if(y == 2){
			    this.matrix[x][y] = this.matrix[x][y+1];
			    this.matrix[x][y+1] = null;
			    actionDone = true;
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
		    actionDone = true;
		}
		else if((y == 1) && (this.matrix[x][y+1] != null) && (this.matrix[x][y].getNum() == this.matrix[x][y+1].getNum())){
		    this.matrix[x][y].setNum(this.matrix[x][y].getNum()*2);
		    this.matrix[x][y+1] = this.matrix[x][y+2];
		    this.matrix[x][y+2] = null;
		    actionDone = true;
		}
		else if((y == 2) &&  (this.matrix[x][y+1] != null) && (this.matrix[x][y].getNum() == this.matrix[x][y+1].getNum())){
		    this.matrix[x][y].setNum(this.matrix[x][y].getNum()*2);
		    this.matrix[x][y+1] = null;
		    actionDone = true;
		}
	    }
	}
	if(actionDone == true){
	    Case case1 = this.randomGenerate();
	    matrix[case1.getX()][case1.getY()] = case1;
	}
    }

    public void moveDown(){
	int x = 0, y = 0, a, b;
	boolean actionDone = false;
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
			    actionDone = true;
			}
			else if(y == 2){
			    this.matrix[x][y] = this.matrix[x][y-1];
			    this.matrix[x][y-1] = this.matrix[x][y-2];
			    this.matrix[x][y-2] = null;
			    actionDone = true;
			}
			else if(y == 1){
			    this.matrix[x][y] = this.matrix[x][y-1];
			    this.matrix[x][y-1] = null;
			    actionDone = true;
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
		    actionDone = true;
		}
		else if((y == 2) && (this.matrix[x][y-1] != null) && (this.matrix[x][y].getNum() == this.matrix[x][y-1].getNum())){
		    this.matrix[x][y].setNum(this.matrix[x][y].getNum()*2);
		    this.matrix[x][y-1] = this.matrix[x][y-2];
		    this.matrix[x][y-2] = null;
		    actionDone = true;
		}
		else if((y == 1) &&  (this.matrix[x][y-1] != null) && (this.matrix[x][y].getNum() == this.matrix[x][y-1].getNum())){
		    this.matrix[x][y].setNum(this.matrix[x][y].getNum()*2);
		    this.matrix[x][y-1] = null;
		    actionDone = true;
		}
	    }
	}
	if(actionDone == true){
	    Case case1 = this.randomGenerate();
	    matrix[case1.getX()][case1.getY()] = case1;
	}
    }

    public boolean gameLost(){
	//Check si la partie est perdue return true si oui et false si non
	int nbCaseVide = 0;
	for(int x = 0; x < 4; x++){ //Parcourt toutes les cases et compte les nombres de cases vide
	    for(int y = 0; y < 4; y++){
		if(matrix[x][y] == null) nbCaseVide++;
	    }
	}
	if(nbCaseVide == 0){
	    for(int x = 0; x < 4; x++){ //Parcourt toutes les cases et compte les nombres de cases vide
		for(int y = 0; y < 4; y++){
		    if(x == 0 && y == 0){
			if(matrix[x][y].getNum() == matrix[x+1][y].getNum() || matrix[x][y].getNum() == matrix[x][y+1].getNum()){
			    return false;
			}
		    }
		    else if(x == 3 && y == 0){
			if(matrix[x][y].getNum() == matrix[x-1][y].getNum() || matrix[x][y].getNum() == matrix[x][y+1].getNum()){
			    return false;
			}
		    }
		    else if(x == 0 && y == 3){
			if(matrix[x][y].getNum() == matrix[x+1][y].getNum() || matrix[x][y].getNum() == matrix[x][y-1].getNum()){
			    return false;
			}
		    }
		    else if(x == 3 && y == 3){
			if(matrix[x][y].getNum() == matrix[x-1][y].getNum() || matrix[x][y].getNum() == matrix[x][y-1].getNum()){
			    return false;
			}
		    }
		    else if(x == 0){
			if(matrix[x][y].getNum() == matrix[x+1][y].getNum() || matrix[x][y].getNum() == matrix[x][y+1].getNum() || matrix[x][y].getNum() == matrix[x][y-1].getNum()){
			    return false;
			}
		    }
		    else if(x == 3){
			if(matrix[x][y].getNum() == matrix[x-1][y].getNum() || matrix[x][y].getNum() == matrix[x][y+1].getNum() || matrix[x][y].getNum() == matrix[x][y-1].getNum()){
			    return false;
			}
		    }
		    else if(y == 0){
			if(matrix[x][y].getNum() == matrix[x-1][y].getNum() || matrix[x][y].getNum() == matrix[x][y+1].getNum() || matrix[x][y].getNum() == matrix[x+1][y].getNum()){
			    return false;
			}
		    }
		    else if(y == 3){
			if(matrix[x][y].getNum() == matrix[x+1][y].getNum() || matrix[x][y].getNum() == matrix[x][y-1].getNum() || matrix[x][y].getNum() == matrix[x-1][y].getNum()){
			    return false;
			}
		    }
		    else{
			if(matrix[x][y].getNum() == matrix[x-1][y].getNum() || matrix[x][y].getNum() == matrix[x+1][y].getNum() || matrix[x][y].getNum() == matrix[x][y-1].getNum() || matrix[x][y].getNum() == matrix[x][y+1].getNum()){
			    return false;
			}
		    }
		}
	    }
	}
	if(nbCaseVide > 0) return false;
	System.out.println("Vous avez perdu !");
	return true;
    }
    
    public boolean gameWin(){
	//Check si la partie est gagné return true si oui et false si non
	for(int x = 0; x < 4; x++){ // Parcourt toutes les cases a la recherche d'une case de valeur 2048
	    for(int y = 0; y < 4; y++){
		if(this.matrix[x][y] != null && this.matrix[x][y].getNum() == 2048) {
		    System.out.println("Vous avez gagné");
		    return true;
		}
	    }
	}
	return false;
    }
    
    public Case randomGenerate(){
	//Crée case 2 ou 4 aléatoirement
	int number, x, y;
	Random alea = new Random();
	number = alea.nextInt(2);
	if(number == 0) number = 2;
	else if(number == 1) number = 4;
	do{
	    x = alea.nextInt(4);
	    y = alea.nextInt(4);
	}
	while(this.matrix[x][y] != null);
	return new Case(number, x, y);
    }

    
    public static void main(String args[]){
	game.setTitle("2048 Game");
	game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	game.setSize(450, 450);
	game.setResizable(false);

	game.add(new Game());

	game.setLocationRelativeTo(null);
	game.setVisible(true);
    }
}
