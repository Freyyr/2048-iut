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
    private static final int width = 500;
    private static final int height = 550;
    
    public Game(){
	setFocusable(true);
	addKeyListener(new KeyAdapter(){
		@Override
		public void keyPressed(KeyEvent e){
		    if(e.getKeyCode() == KeyEvent.VK_ENTER){
			restart();
		    }
		    if(!gameLost() && !gameWin()){
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

    public void restart(){
	matrix = new Case[4][4];
	
	Case case1 = this.randomGenerate();
	matrix[case1.getX()][case1.getY()] = case1;
	
	Case case2 = this.randomGenerate();
	matrix[case2.getX()][case2.getY()] = case2;
    }
    
    @Override
    public void paint(Graphics g){
	super.paint(g);
	g.setColor(new Color(0xbbada0));
	g.fillRect(0, 0, width, height);
	g.setColor(Color.BLACK);
	g.setFont(new Font("Arial", Font.BOLD, 20));
	g.drawString("Appuyez sur Entrer pour relancer", SIZE/2 - MARGE +25, 30);
	for(int y = 0; y < 4; y++){
	    for(int x = 0; x < 4; x++){
		draw(g, matrix[x][y], x, y);
	    }
	}
    }

    public void draw(Graphics g2, Case case1, int x, int y){
	Graphics2D g = ((Graphics2D) g2);
	if(case1 != null){
	    //parametrage de l'affichage
	    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
	    int num = case1.getNum();
	    String value = Integer.toString(case1.getNum());

	    //print case
	    g.setColor(getColor(case1.getNum()));
	    g.fillRoundRect(x*SIZE+MARGE+x*MARGE +25, y*SIZE+MARGE+y*MARGE +50, SIZE, SIZE, MARGE, MARGE);

	    //print int de la case
	    g.setColor(Color.BLACK);
	    g.setFont(stringFont);
	    if(num < 10){
		g.drawString(value, x*SIZE+MARGE+x*MARGE + 70 , y*SIZE+MARGE+y*MARGE + 105);
	    }
	    else if(num < 100){
		g.drawString(value, x*SIZE+MARGE+x*MARGE + 63 , y*SIZE+MARGE+y*MARGE + 105);
	    }
	    else if(num < 1000){
		g.drawString(value, x*SIZE+MARGE+x*MARGE + 56 , y*SIZE+MARGE+y*MARGE + 105);
	    }
	    else{
		g.drawString(value, x*SIZE+MARGE+x*MARGE + 49 , y*SIZE+MARGE+y*MARGE + 105);
	    }
	}
	else{
	    g.setColor(Color.GRAY);
	    g.fillRoundRect(x*SIZE+MARGE+MARGE*x+ 25, y*SIZE+MARGE+MARGE*y+50, SIZE, SIZE, MARGE, MARGE);
	}
	if(gameWin() || gameLost()){
	    g.setColor(new Color(10, 10, 10, 30));
	    g.fillRect(0, 0, width, height);
	    g.setColor(Color.WHITE);
	    g.setFont(new Font("Arial", Font.BOLD, 25));
	    if(gameWin()){
		g.drawString("Vous avez gagné !", SIZE, height/2);
	    }
	    else if(gameLost()){
		g.drawString("Vous avez perdu !", SIZE, height/2);
	    }
	    g.setFont(new Font("Arial", Font.BOLD, 15));
	    g.drawString("Appuyez la touche entrer pour relancer", SIZE- 3*MARGE + MARGE/4, height/2 + MARGE*2);
	}
    }

    public Color getColor(int value) {
	switch(value){
	case 2:
	    return new Color(0xc7de20);
	case 4:
	    return new Color(0xdeb620);
	case 8 :
	    return new Color(0xde7720);
	case 16 :
	    return new Color(0xde3720);
	case 32 :
	    return new Color(0xde2048);
	case 64 :
	    return new Color(0xde2087);
	case 128:
	    return new Color(0xde20c7);
	case 256:
	    return new Color(0xb620de);
	case 512:
	    return new Color(0x7720de);
        case 1024:
	    return new Color(0x3720de);
        case 2048:
	    return new Color(0x2048de);
	default:
	    break;
	}
	return Color.GRAY;
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
	return true;
    }
    
    public boolean gameWin(){
	//Check si la partie est gagné return true si oui et false si non
	for(int x = 0; x < 4; x++){ // Parcourt toutes les cases a la recherche d'une case de valeur 2048
	    for(int y = 0; y < 4; y++){
		if(this.matrix[x][y] != null && this.matrix[x][y].getNum() == 2048) {
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
	game.setSize(width, height);
	game.setResizable(false);

	game.add(new Game());

	game.setLocationRelativeTo(null);
	game.setVisible(true);
    }
}
