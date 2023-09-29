public class health {

 
    public static void main(String[] args)
    {   Finch f = new Finch("A");
       
        f.setBeak(0, 0, 0);
        f.setTail("all", 0, 100, 0);
        int health = 100;
        f.setTail("all", 0, 100, 0);
        
       while(!(f.getButton("A") && f.getButton("B")))
       {
            f.setTail("all", 0, 100, 0);
            f.pause(1);
            f.setTail("all", 30, 70, 0);
            f.pause(1);
            f.setTail("all", 50, 50, 0);
            f.pause(1);
            f.setTail("all", 70, 30, 0);
            f.pause(1);
            f.setTail("all", 100, 0, 0);
            f.pause(1); 

       }
       f.stopAll();
       f.disconnect();
    }


    
    
}
