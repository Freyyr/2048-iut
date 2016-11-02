import java.util.Random;
    
public class Test{

    public static void main(String args[]){
	Grille jeu = new Grille();
	System.out.println(jeu.toString());
	jeu.moveLeft();
	System.out.println(jeu.toString());
    }
}
