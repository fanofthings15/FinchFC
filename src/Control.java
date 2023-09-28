import com.github.strikerx3.jxinput.*;

public class Control {
    public static int m_Con = 1;
    public static Finch m_finch;

    public void run(int ControllerIndex, Finch finch) throws Exception{
        m_finch = finch;
        m_Con = ControllerIndex;

        double leftpower = 0;
        double rightpower = 0;



        if (!XInputDevice.isAvailable()) {
            System.out.println("XInput is not available!");
        }

        XInputDevice controllerA = XInputDevice.getDeviceFor(m_Con);

        while (true) {
            if (!controllerA.poll()) {
                System.out.println("Failed to poll controller A!");
                continue;
            }

            XInputComponents components = controllerA.getComponents();

            XInputButtons buttons = components.getButtons();
            XInputAxes axes = components.getAxes();

            // System.out.print(axes.lx + " " + axes.ly + "            \r");
            leftpower = axes.ly*100;
            rightpower = axes.ry*100;

            System.out.println(leftpower);
            System.out.println(rightpower);
            if(leftpower > 10 || leftpower < -10){
                m_finch.setMotors(leftpower, rightpower);
            }
            else{
                m_finch.setMotors(0, rightpower);
            }
            if(rightpower > 10 || rightpower < -10){
                m_finch.setMotors(leftpower, rightpower);
            }
            else{
                m_finch.setMotors(leftpower, 0);
            }
        }
    }
}
