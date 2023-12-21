import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Scanner;
import src.*;
//import src.EffacerEcran;
import java.util.Random;



//*************************************************               Walid Mattar, Seydou Diallo, Groupe B1, jeu 2048.

public class jeu
{

  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_BLACK = "\u001B[30m";
  public static final String ANSI_GREEN = "\u001B[32m";           // initialisation de plusieurs de couleur de Police de caractere
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_BLUE = "\u001B[34m";
  public static final String ANSI_PURPLE = "\u001B[35m";
  public static final String ANSI_CYAN = "\u001B[36m";
  public static final String ANSI_WHITE = "\u001B[37m";

static int[] multiple2 = {2,2,2,2,2,2,2,2,2,4};
static int iDimension = 4;
static int random;
static int [][] grille = new int[6][6];
static boolean isNewGame = true;

//*************************************************


    public static void main(String[] args) 
    {

//*************************************************

        Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
        int largeur = (int) tailleEcran.getWidth();           // Dimension de l'ecran sur lequel le programme est lancer
        int hauteur = (int) tailleEcran.getHeight();

//**************************************************

        System.out.println("");
        int hauteurMenu = (int)(hauteur * 0.077);   // lance le menu par rapport a la dimesion de l'ecran pour qu'il convient a tous les ecrans
         menu(84,5);                                // exactement a une taille voulu et calculé au prealable, cette tentative est un echec.
        

        int hauteurbarre = (int)(hauteur * 0.0555);
        barre(hauteurbarre);              // pareil pour la barre, cependant la tentative a ete bien plus facile et c'est un succes, la barre s'adapte au dimension de l'ecran.

        // affiche les dimensions de l'ecran, couleur de police violet.
        System.out.println( ANSI_PURPLE + "\n" + "Votre ecran mesure en" + "\n" + " - Hauteur "  + hauteur +"\n" + " - Largeur " + largeur + "\n" + ANSI_RESET);
        barre(hauteurbarre);
        // affiche la barre pour une separation claire des elements et une meuilleure mise en forme


//**************************************************

        // partie du main qui s'ocuppe de la dimension du plateau.
        
        Scanner scanner = new Scanner(System.in);
        String imput = "";  
        boolean error = false;
        do 
            {
                try {
                  error = false;
                  System.out.print("Saisir la Dimension du Plateau que vous voulez [default: 4, max. 6]: ");
                  imput = scanner.nextLine();
                  iDimension = Integer.parseInt(imput);
                    if (iDimension > 6){
                            System.out.println("La Dimension Max du Plateau est 6!");
                            error = true;
                                       }
                      
                    if (iDimension < 4){
                            System.out.println("La Dimension Min du Pkateau est 4!");
                            error = true;
                                       }

                    } catch(Exception ex)
                {
                      if (imput.isEmpty() || imput.matches("[ ]{1,}")) // verifie si le nombre saisie est bien un chiffre, 
                      {                                                // dans la cas ou rien n'est saisie la dimension 4 est selectionner par default.
                        iDimension = 4;
                        error = false;
                      } 
                      else
                      {
                        System.out.println("Saisie Invalide!");
                        error = true;
                      }
                }
                          
            }while(error) ;
                  
        
        
//**************************************************

        char quitter = 'i';
        while (quitter != 'x')

            {

      // remplit 2 case par tour si c'est un nouveau joueur, et apres 2 tour, remplit une case par tour.
                  int i = 0;
                  
                
                 if(isNewGame == true){
                    Outil.main(grille);
                    }

                  Outil.main(grille);

                    if (i < 2) {
                     
                    isNewGame = false;
                    i++;
                  }   

                  
            // affiche le plateau de jeu avec la grille
                  map(iDimension,grille);
                  try{
                  // lit le prochain coup
                  System.out.println("Entrer votre prochain mouuvement (up(z), down(s), left(q), right(d), x pour quitter):");
                  String move = scanner.nextLine();
                  quitter = move.charAt(0);
                  // change le grille en fonction du mouvement
                            if (move.equals("z")){
                              grille = moveUp(grille);
                          } else if (move.equals("s")) {
                              grille = moveDown(grille);
                          } else if (move.equals("q")) {
                              grille = moveLeft(grille);
                          } else if (move.equals("d")) {
                              grille = moveRight(grille);
                          }
                      }catch(Exception ex) {
                     /* if (move.isEmpty() || move.matches("[ ]{a,}"))
                      {
                        System.out.println("Saisie Invalide!");
                        error = false;
                      }*/
                    }
                    
                  barre(hauteurbarre);  // pour une separation claire
            }
            // la méthode equals() en Java détermine si l’objet qui appelle la méthode est égal à l’objet qui est passé en argument.
    
}


//*************************************************


    public static void ligne(String c, int longueur) 
    {
      
       if (longueur < 0) 
          {
          System.out.println("ERREUR! afficherEnLigne : longueur négative ("
             + longueur + ")");
          } 
       else 
        {

          for (int colonne = 1; colonne <= longueur; colonne++) 
              {
              System.out.print(ANSI_BLUE + c + ANSI_RESET);
              }

        }


    }


//*************************************************


    public static void ligneln(String c, int longueur) {
        ligne(c, longueur);
        System.out.println();

       }


//*************************************************


    public static void menu( int largeur, int hauteur)
    {
            if (hauteur > 0 && largeur > 0) 
        {
                    ligne(" " ,2);
                    ligneln("*", largeur); // première ligne
                    if (hauteur > 1) 
              {
                          for (int ligne = 2; ligne < hauteur; ligne++) 
                    {
                            if (ligne == 2) 
                            {
                                ligne(" " ,2);
                                ligne("*", 1);
                                ligne(" ", 1);
                                ligne("Pour une meuilleur experience de jeu, il est conseiller d'aller sur le terminal", 1);
                                ligne(" ", largeur - 82);
                                ligneln("*", 1);
                            }

                            //*************************************************

                            else if( ligne == 3)
                            {
                                ligne(" " ,2);
                                ligne("*", 1);
                                ligne(" ", 1);
                                ligne("de se rendre dans Edition ---> Préfèrences ---> Apparence puis de cliquer sur", 1);
                                ligne(" ", largeur - 80);
                                ligneln("*", 1); 
                            }

                            //*************************************************

                            else if( ligne == 4)
                            {
                                ligne(" " ,2);
                                ligne("*", 1);
                                ligne(" ", 1);
                                ligne("    police personnaliseé et de mettre la police a 14, Bon Jeu ^ᴗ^    ", 1);
                                ligne(" ", largeur - 72);
                                ligneln("*", 1); 
                            }

                            //*************************************************


                          else if (largeur > 1) {
                               ligne(" " ,2);
                               ligne("*", 1);
                               ligne(" " , largeur - 2);
                               ligneln("*", 1);
                            }
                    }
                        ligne(" " ,2);
                        ligneln("*", largeur); // dernière ligne
              }
               

        }

    }


//*************************************************


    public static void barre(int largeur)
        {

          int difference = 85 - largeur;
          ligne(" ", difference/2);
          
          for(int i = 0; i<= largeur  ; i++ )
             {
            ligneBarre("*" , 1);
             }

          ligneln(" ", difference/2);

             
        }

//*************************************************


   public static void ligneBarre(String a, int longueur) 
       {
            
         if (longueur < 0) 
            {
               System.out.println("ERREUR! afficherEnLigne : longueur négative ("
                 + longueur + ")");
            } 
            else 
            {

               for (int colonne = 1; colonne <= longueur; colonne++) 

               {
                  System.out.print(ANSI_YELLOW + a + ANSI_RESET);
               }

            }


       }



//*************************************************

    // Methode pour la creation du plateau, fait appel a un sous Programme dont le but est une mise en page clair
   public static void map (int iDimension, int[][] grille)
      {
        int plusGrandNombre = verifiePlusGrandNombre(iDimension, grille); // appel de la fonction pour verifier le plus grand nombre
        int plusGrandeLongueur = Integer.toString(plusGrandNombre).length(); // transforme la classe de int a String
      

      //*************************************************

       // affiche le plateau

            for(int i=1;i<=(3+plusGrandeLongueur) * iDimension;i++)
            {
                if (i==1)
                {
                   System.out.print(":-");
                }
                else if (i==(3+plusGrandeLongueur)*iDimension)
                {
                   System.out.print(":");
                }
                else
                {
                   System.out.print("-");
                }
            }

         System.out.println("");

      //*************************************************
      
        for(int i=0; i < iDimension;i++)
          {

            for(int j=0; j < iDimension;j++)
              {
                
            if (j == iDimension-1)
                  {
                String sFormat = String.format("| %-" + plusGrandeLongueur + "d |", grille[i][j]);
                 System.out.print(sFormat);
                   }

            else
                  {
                String sFormat = String.format("| %-" + plusGrandeLongueur + "d ", grille[i][j]);
                 System.out.print(sFormat);
                   }
               }
        

              System.out.println("");

      //*************************************************

       
             for (int j=1;j<=(3+plusGrandeLongueur)*iDimension;j++)
               {
                 if (j==1)
                   { 
                      System.out.print(":-");
                   }
                 else if (j==(3+plusGrandeLongueur)*iDimension)
                   {
                      System.out.print(":");
                   }
                 else
                   {
                       System.out.print("-");
                   }

               }

            System.out.println("");
          }

      System.out.println("");
    }


//*************************************************


  public static int verifiePlusGrandNombre(int iDimension, int[][] tab)
      {
        int tmp = -1;
          for(int i=0; i < iDimension; i++)
          {
              for(int j=0; j < iDimension; j++)
              {
                  if (tab[i][j] > tmp)
                  {
                      tmp = tab[i][j];
                  }
              }
          }

        return tmp;
      }




  //*************************************************


    // Methode pour se deplacer vers les case du hauts
    public static int[][] moveUp(int[][] grille) 

      {
        // boucle qui parcour la collonne
        for (int j = 0; j < iDimension; j++) 
          {
            // boucle qui parcourt les lignes
              for (int i = iDimension - 1; i >= 0; i--) 
              {
                // Si la case est vide, on le passe
                  if (grille[i][j] == 0) 
                   {
                     continue; // permet d'interompre l'iteration loeque la condition se produit, et continue avec l'iteration suivante.
                   }

      //*************************************************

              // Boucle qui parcoure les lignes en-dessous la case actuel 
                  for (int k = i - 1; k >= 0; k--) 
                  {
                    // si la case au dessus est vide, deplace l'actuel case vers le bas 
                      if (grille[k][j] == 0) 
                          {
                            grille[k][j] = grille[k + 1][j];
                            grille[k + 1][j] = 0;
                          }

                    // si la case au dessus n'est pas vide, on fusionne les deux 
                      else if (grille[k][j] == grille[i][j]) 
                          {
                            grille[k][j] *= 2;
                            grille[i][j] = 0;
                            break;  //L'instruction break sert a stopper l'execution
                          }
                    // si la case au dessus n'est pas vide et qu'elle a une valeur differente, la case ne bouge pas et aucune operation est faite
                      else 
                        {
                      break;
                        }
                  }
              }
          }

        return grille;
      }


//*************************************************


    // Methode pour deplacer les case vers le bas
    public static int[][] moveDown(int[][] grille) 
      {   
          // boucle qui pârcourt les collones
          for (int j = 0; j < iDimension; j++) 
          {

              // boucle qui parcourt les lignes
              for (int i = 0; i < iDimension; i++) 
              {

                    // si la case actuel est vide, on le passe
                    if (grille[i][j] == 0) 
                    {
                       continue;
                    }

                    // Boucle qui parcourt les lignes au dessus de la case actuel
                    for (int k = i + 1; k < iDimension; k++) 
                    {

                      // si la case est vide, on se deplacer vers le bas.
                          if (grille[k][j] == 0) 
                          {     
                              grille[k][j] = grille[k - 1][j];
                              grille[k - 1][j] = 0;

                          }

                          // si la case en dessous n'est pas vide et a la meme valeur que la case actuel, on fusionne les deux
                          else if (grille[k][j] == grille[i][j]) 
                          {
                              grille[k][j] *= 2;
                              grille[i][j] = 0;
                              break;
                          }

                          // si la case en dessous n'est pas vide et qu'elle a une valeur differnete de la case actuel, on arrete le mouvement de la case actuel
                          else 
                          {
                               break;
                          }
                    }
              }
          }
        return grille;
      }


//*************************************************


    // Methode pour boyger les case a la gauche du plateau 
    public static int[][] moveLeft(int[][] grille) 
    {
        // Boucle qui parcourt les lignes 
          for (int i = 0; i < iDimension; i++) 
              {
                // Boucle qui parcourt les collones
                for (int j = iDimension - 1; j >= 0; j--) 
                    {
                          // Si la case actuel est vide, on la passe.
                          if (grille[i][j] == 0) 
                              {
                                continue;
                              }

                          // Boucle qui parcourt les colonnes vers la gauche de la case actuel
                          for (int k = j - 1; k >= 0; k--) 
                          {
                                // Si la case a gauche est vide, on deplace l'actuel case vers la droite 
                                if (grille[i][k] == 0) 
                                {
                                    grille[i][k] = grille[i][k + 1];
                                    grille[i][k + 1] = 0;
                                }

                                // Si la case a gauche n'est pas vide et qu'elle a la meme valeur que la case actuel, on fusionne les 2
                                else if (grille[i][k] == grille[i][j]) 
                                {
                                    grille[i][k] *= 2;
                                    grille[i][j] = 0;
                                    break;
                                }
                                // Si la case a gauche n'est pas vide et qu'elle a une valeur differente, on arrete le mouvemnt de la case actuel.
                                else 
                                {
                                    break;
                                }
                          }
                    }
              }
        return grille;
    }


//*************************************************


    public static int[][] moveRight(int[][] grille) 
    {
          // Boucle qui parcourt les lignes 
          for (int i = 0; i < iDimension ; i++) 
              {
                // Boucle qui parcourt les collones
                for (int j = 3; j >= 0; j--) 
                    {
                          // Si la case actuel est vide, on la passe.
                          if (grille[i][j] == 0) 
                              {
                                continue;
                              }

                          // Boucle qui parcourt les colonnes vers la gauche de la case actuel
                          for (int k = j + 1; k < iDimension; k ++) 
                          {
                                // Si la case a droite est vide, on deplace l'actuel case vers la droite 
                                if (grille[i][k] == 0) 
                                {
                                    grille[i][k] = grille[i][k - 1];
                                    grille[i][k - 1] = 0;
                                }

                                // Si la case a droite n'est pas vide et qu'elle a la meme valeur que la case actuel, on fusionne les 2
                                else if (grille[i][k] == grille[i][j]) 
                                {
                                    grille[i][k] *= 2;
                                    grille[i][j] = 0;
                                    break;
                                    
                                }
                                // Si la case a droite n'est pas vide et qu'elle a une valeur differente, on arrete le mouvement de la case actuel.
                                else 
                                {
                                    break;
                                }
                          }
                    }
              }
      return grille;
    }

   public static void colorNumber(int[][] grille){

      
    


   }

}
