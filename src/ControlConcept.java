import com.github.strikerx3.jxinput.listener.*;
import com.github.strikerx3.jxinput.enums.*;
import com.github.strikerx3.jxinput.natives.*;
import com.github.strikerx3.jxinput.*;
import java.lang.Math;
//commit pls
public class ControlConcept {
    public static double flattenAxesValue(double value) {
        if (value > 1.0) {
            value = 1.0;
        } else if (value < -1.0) {
            value = -1.0;
        } else if (value > 0.0 && value < 0.1) {
            value = 0.0;
        } else if (value < -0.0 && value > -0.1) {
            value = 0.0;
        }

        return value;
    }

    public static int[] getWheelPowers(XInputAxes axes) {
        // double ly = flattenAxesValue(axes.ly);
        // double ly = flattenAxesValue(axes.rt * (1 / 0.3));
        double lx = flattenAxesValue(axes.lx);

        // int direction;
        // if (ly > -0.8) {
        //     direction = 1;
        // } else {
        //     direction = -1;
        // }

        // double power = flattenAxesValue(Math.sqrt((lx * lx) + (ly * ly)) * direction);
        double lt = flattenAxesValue(axes.lt * (1 / 0.3));
        double rt = flattenAxesValue(axes.rt * (1 / 0.3));
        double power = rt - lt;

        if (lt == 1.0 && rt == 1.0) {
            int[] powers = {-100, 100};

            return powers;
        }

        int leftPowerPercent;
        int rightPowerPercent;

        if (lx < 0) {
            rightPowerPercent = (int) (100 * power);

            double leftMultiplier = 1 - Math.abs(lx);
            double leftPower = leftMultiplier * power;

            leftPowerPercent = (int) (100 * leftPower);
        } else {
            leftPowerPercent = (int) (100 * power);

            double rightMultiplier = 1 - lx;
            double rightPower = rightMultiplier * power;

            rightPowerPercent = (int) (100 * rightPower);
        }

        int[] powers = {leftPowerPercent, rightPowerPercent};

        return powers;
    }

    public static int getVibration(XInputDevice controller) {
        XInputComponents components = controller.getComponents();
        XInputAxes axes = components.getAxes();

        double lt = flattenAxesValue(axes.lt * (1 / 0.3));
        double rt = flattenAxesValue(axes.rt * (1 / 0.3));
        double sum = lt + rt;
        if (sum > 1.0) {
            sum = 1.0;
        }

        int vibration = (int) (1000 * sum);

        return vibration;
    }

    public static void main(String[] args) throws Exception {
        if (!XInputDevice.isAvailable()) {
            System.out.println("XInput is not available!");
        }

        XInputDevice aController = XInputDevice.getDeviceFor(0);

        Finch aFinch = new Finch("A");

        while (true) {
            if (!aController.poll()) {
                System.out.println("Failed to poll controller A!");
                continue;
            }

            XInputComponents aComponents = aController.getComponents();
            XInputAxes aAxes = aComponents.getAxes();

            int vibration = getVibration(aController);

            System.out.println(vibration);

            aController.setVibration(vibration, vibration);

            int[] wheelPowers = getWheelPowers(aAxes);

            aFinch.setMotors(wheelPowers[0], wheelPowers[1]);
            // aFinch.setMotors(0, 0);
        }
    }
}
