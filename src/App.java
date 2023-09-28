// import com.github.strikerx3.jxinput.XInputDevice;
// import com.github.strikerx3.jxinput.listener.*;
// import com.github.strikerx3.jxinput.enums.*;
// import com.github.strikerx3.jxinput.natives.*;
import com.github.strikerx3.jxinput.*;

public class App {
    public static void main(String[] args) throws Exception {
        // Finch finch = new Finch("A");

        // Check if XInput 1.4 is available
        if (!XInputDevice.isAvailable()) {
            System.out.println("XInput is not available!");
        }

        XInputDevice controllerA = XInputDevice.getDeviceFor(0);

        while (true) {
            if (!controllerA.poll()) {
                System.out.println("Failed to poll controller A!");
                continue;
            }

            XInputComponents components = controllerA.getComponents();

            XInputButtons buttons = components.getButtons();
            XInputAxes axes = components.getAxes();

            System.out.print(axes.lx + " " + axes.ly + "            \r");
        }
    }
}
