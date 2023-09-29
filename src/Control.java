import com.github.strikerx3.jxinput.*;

public class Control {
    public static int m_Con = 1;
    public static Finch m_finch;

    public static double lstick = 0;
    public static double rstick = 0;

    public void run(int ControllerIndex, Finch finch) throws Exception{
        m_finch = finch;
        m_Con = ControllerIndex;

        if (!XInputDevice.isAvailable()) {
            System.out.println("XInput is not available!");
        }

        XInputDevice controllerA = XInputDevice.getDeviceFor(m_Con);

        while (true) {
            if (!controllerA.poll()) {
                System.out.println("Failed to poll controller " + ControllerIndex +"!");
                continue;
            }

            XInputComponents components = controllerA.getComponents();

            XInputButtons buttons = components.getButtons();
            XInputAxes axes = components.getAxes();

            // System.out.print(axes.lx + " " + axes.ly + "            \r");
            
            lstick  = axes.ly * 100;
            rstick = axes.rx * 100;
            System.out.print(lstick + " " + rstick + "            \r");

            if(lstick > (20) || lstick < (-20)){
                m_finch.setMotors(lstick, lstick);
            }
             if(rstick > 20){
                m_finch.setMotors(rstick, -rstick);
            }
            if(rstick < -20){
                m_finch.setMotors(rstick, -rstick); 
            }
            if((rstick < (20)) && (rstick > (-20)) && (lstick < (20)) && (lstick > (-20))){
                m_finch.setMotors(0, 0);
            }
        
        }
    }
}