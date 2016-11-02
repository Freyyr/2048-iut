import java.util.Random;
    
public class Test{

    public static void main(String args[]){
	Grille jeu = new Grille();
	System.out.println(jeu.toString());
	
	//System.out.println(jeu.nbCasePleine());


	Random alea = new Random();
	System.out.println(alea.nextInt(2));
	System.out.println(alea.nextInt(2));

    }
}
