package src;
import java.util.Random;

public class Outil 
{

static boolean isNewGame = true;

  public static void main(int [][] grille) {

	   


	    // remplit 2 case par tour si c'est un nouveau joueur, et apres 2 tour, remplit une case par tour.
	    
	    	    
	/* for (int i = 0; i <= 2 ; i++ ) 
	    {	
	    if(isNewGame == true)
	    	{

	    addRandomTile(grille);
	    addRandomTile(grille);
	   		}
		}
		isNewGame = false;*/
		addRandomTile(grille);


	 }

	  public static void addRandomTile(int[][] grille) 
	{
	  	int y, x;
	    // genere une position aleatoire 
	    Random random = new Random();
	    do {
	      y = random.nextInt(4);
	      x = random.nextInt(4);
	    } while (grille[x][y] != 0);
	    // remplit ta grille avec des valeur de 2 ou 4, et un probabilite de 90% pour 2 
	    grille[x][y] = random.nextDouble() < 0.9 ? 2 : 4;
	}


}

