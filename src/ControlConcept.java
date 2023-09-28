import com.github.strikerx3.jxinput.listener.*;
import com.github.strikerx3.jxinput.enums.*;
import com.github.strikerx3.jxinput.natives.*;
import com.github.strikerx3.jxinput.*;
import java.lang.Math;

public class ControlConcept {
    public static void main(String[] args) throws Exception {
        if (!XInputDevice.isAvailable()) {
            System.out.println("XInput is not available!");
        }

        XInputDevice aController = XInputDevice.getDeviceFor(0);

        while (true) {
            if (!aController.poll()) {
                System.out.println("Failed to poll controller A!");
                continue;
            }

            XInputComponents aComponents = aController.getComponents();

            XInputAxes aAxes = aComponents.getAxes();

            int leftWheelPower;
            int rightWheelPower;

            double ly = aAxes.ly;
            double lx = aAxes.lx;

            if (aAxes.ly > 1.0) {
                ly = 1.0;
            } else if (aAxes.ly < -1.0) {
                ly = -1.0;
            } else if (aAxes.ly < 0.1 && aAxes.ly > 0) {
                ly = 0.0;
            } else if (aAxes.ly > -0.1 && aAxes.ly < 0) {
                ly = 0.0;
            }

            if (aAxes.lx > 1.0) {
                lx = 1.0;
            } else if (aAxes.lx < -1.0) {
                lx = -1.0;
            } else if (aAxes.lx < 0.1 && aAxes.lx > 0) {
                lx = 0.0;
            } else if (aAxes.lx > -0.1 && aAxes.lx < 0) {
                lx = 0.0;
            }

            double power = Math.sqrt((lx * lx) + (ly * ly));
            if (power > 1.0) {
                // may not be perfect because the middle edges (inbetween straight down, straight 
                // right, etc) are ~0.1 over 1
                power = 1.0;
            }

            System.out.println(power);


        }
    }
}
