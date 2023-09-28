import com.github.strikerx3.jxinput.listener.*;
import com.github.strikerx3.jxinput.enums.*;
import com.github.strikerx3.jxinput.natives.*;
import com.github.strikerx3.jxinput.*;
import java.lang.Math;

public class ControlConcept {
    public static double flattenAxesValue(double value) {
        if (value > 1.0) {
            value = 1.0;
        } else if (value < -1.0) {
            value = -1.0
        } else if (value > 0.0 && value < 0.1) {
            value = 0.0
        } else if (value < -0.0 && value > -0.1) {
            value = 0.0
        }

        return value
    }

    public static int[] getWheelPowers(XInputAxes axes) {
        double ly = flattenAxesValue(axes.ly);
        double lx = flattenAxesValue(axes.lx);

        double power = Math.sqrt((lx * lx) + (ly * ly)) * (int) ly;

        double leftMultiplier = (lx + 1.0) / 2.0;
        double rightMultiplier = 1.0 - leftMultiplier;

        double leftPower = leftMultiplier * power;
        double rightPower = rightMultiplier * power;

        int leftPowerPercent = (int) (100 * leftPower);
        int rightPowerPercent = (int) (100 * rightPower);

        int[] powers = {leftPowerPercent, rightPowerPercent};

        return powers
    }

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

            System.out.println(getWheelPowers(aAxes));
        }
    }
}
