import com.github.strikerx3.jxinput.listener.*;
import com.github.strikerx3.jxinput.enums.*;
import com.github.strikerx3.jxinput.natives.*;
import com.github.strikerx3.jxinput.*;
import java.lang.Math;

public class controltest {

    public static void main(String args[]) throws Exception
    {
        final Finch f = new Finch("A");
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

                float acceleration = axes.rt;
                float reverse = axes.lt;

                float stick = axes.lx;

                // if(stick == 1.0)
                // {
                //     f.setMotors(100, -100);
                // }

                //System.out.println(acceleration + ", " + reverse );
                System.out.println();
                f.setMotors((acceleration) * 100, acceleration *100);
                //f.setMotors(-(reverse * 100), -(reverse *100));

            }
        }
        f.playNote(70, 0.25);
        f.playNote(60, 0.25);
        f.stopAll();
        f.disconnect();
       
    }
    
}
