package sae_dev.src;

public class EffacerEcran
{


 public static void  main(String[] args)
 
    {

    System.out.print( "\033[H\033[2J" );
    System.out.flush();

    }

}
