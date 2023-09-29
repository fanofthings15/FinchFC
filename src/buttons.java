import com.github.strikerx3.jxinput.listener.*;
import com.github.strikerx3.jxinput.enums.*;
import com.github.strikerx3.jxinput.natives.*;
import com.github.strikerx3.jxinput.*;
import java.lang.Math;



public class buttons {
    
    
    public static void main(String args[]) throws Exception
    {
        Finch f = new Finch("A");
        if (!XInputDevice.isAvailable()) {
            System.out.println("XInput is not available!");
        }

        XInputDevice[] devices = XInputDevice14.getAllDevices();
        XInputDevice device = XInputDevice14.getDeviceFor(0);

        while(!(f.getButton("A") && f.getButton("B")))
        {
             if (device.poll()) 
            {
            // Retrieve the components
                XInputComponents components = device.getComponents();
                XInputButtons buttons = components.getButtons();
                XInputAxes axes = components.getAxes();

 // The A button is currently pressed 
                if (buttons.a) { f.setBeak(0,100,0); }
                else if(buttons.x) { f.setBeak(0,0,100); }
                else if(buttons.b) { f.setBeak(100,0,0); }
                else if(buttons.y) { f.setBeak(100,100,0); }
                else { f.setBeak(0,0,0); }
                
               





            }

            

        }
        f.stopAll();
        f.disconnect();
       
    }
}
