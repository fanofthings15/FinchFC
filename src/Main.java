 /**
  * test
  */
 public class Main {

   public static Finch f = new Finch("A");
   public static Control Con = new Control();

   public static void main(String[] args) {
      System.out.println("Run Successful");
      
        f.setBeak(100, 0, 0);


        try {
          Con.run(0, f);
        } catch (Exception e) {
          e.printStackTrace();

        f.disconnect();
        f.stopAll();
    }
  }
}



