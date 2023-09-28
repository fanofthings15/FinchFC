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

            double moreSteer = Math.abs(lx);
            if (moreSteer == 0) {
                moreSteer = 0.5;
            }

            double lessSteer = 1.0 - moreSteer;

            int leftPower;
            int rightPower;

            if (lx < 0) {
                leftPower = (int) (power * (100 * lessSteer));
                rightPower = (int) (power * (100 * moreSteer));
            } else {
                leftPower = (int) (power * (100 * moreSteer));
                rightPower = (int) (power * (100 * lessSteer));
            }

            System.out.print("Left: " + leftPower + ", Right: " + rightPower + "            \r");
        }
    }
}
