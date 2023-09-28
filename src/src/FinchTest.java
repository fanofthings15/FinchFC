import java.util.Random;

public 
class FinchTest {
    public static void main(String[] args) 
    {
    	Random r = new Random();
        Finch f = new Finch("A");
        //Finch f2 = new Finch("B");
        
        f.setBeak(100,100,100);
        while(!(f.getButton("A") && f.getButton("B")))
        {
        	f.setMotors(50,50); //go forward
        	f.setTail("all", 0, 0, 100);
        	boolean isLeft = r.nextFloat() >= 0.5; //it will turn left if the value is >= 0.5
        	
        	while(f.getDistance() < 4)
        	{
        		System.out.println(f.getDistance());
	        	if(isLeft)
	        	{
	        		f.setMotors(25,-25);
	        		f.setTail(1, 100, 0, 0);
	        	}
	        	else
	        	{
	        		f.setMotors(-25,25);
	        		f.setTail(4, 100, 0, 0);
	        	}
	        	f.pause(.01);
        	}
        	f.pause(.01);
        	
        }
        f.stopAll();
        f.disconnect();
    }
	    	
    	
        
        
   


        //f.disconnect();
    }

