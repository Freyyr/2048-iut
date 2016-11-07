import java.util.Scanner;

public class Test{

    public static void main(String args[]){
	boolean quitter = false;
	Grille jeu = new Grille();
	System.out.println("Bienvenue dans le jeu 2048");
	System.out.println("Tapez\n1 pour lancer une nouvelle partie\n2 pour quitter");
	Scanner sc = new Scanner(System.in);
	while(! quitter){
	    int choix = sc.nextInt();
	    if(choix == 1){
		jeu.partie();
	    }
	    else if(choix == 2){
		quitter = true;
	    }
	    else{
		System.out.println("Veuillez entrer une valeur valide !");
	    }
	}
    }
}
