import java.util.Scanner;

public class battle {
    /**
     * @param args
     */
    public static void main(String[] args)
    {
       Finch f1 = new Finch("A");
       Scanner sc = new Scanner(System.in);
       int availablePoints = 20;
       int damage = 0;
       int armor = 0;
       int speed = 0;

       String selection= "";
       
       while(!(selection.equalsIgnoreCase("fire") || selection.equalsIgnoreCase("water") || selection.equalsIgnoreCase("earth") || selection.equalsIgnoreCase("air")))
       {
            System.out.println("Choose your class: \n Fire: \n Water: \n Earth: \n Air:");
            selection = sc.nextLine();
       }

       System.out.println(selection.toUpperCase());

       

       //damage, armor, speed

      
       
       //Finch f2 = new Finch("B");  

       


    //    while(!(f1.getButton("A") && f1.getButton("B")))
    //    {
            
    //    }
    f1.stopAll();
    f1.disconnect();
    }
}
