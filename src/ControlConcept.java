import com.github.strikerx3.jxinput.listener.*;
import com.github.strikerx3.jxinput.enums.*;
import com.github.strikerx3.jxinput.natives.*;
import com.github.strikerx3.jxinput.*;
import java.lang.Math;
//commit pls
public class ControlConcept {
    // keep analog value below 0.1 to 0 and above 1.0 to 1.0
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

    // return an int of the powers for the two wheels
    public static int[] getWheelPowers(XInputAxes axes) {
        // double ly = flattenAxesValue(axes.ly);
        // double ly = flattenAxesValue(axes.rt * (1 / 0.3));

        // flatten analog value of left stick horizontal
        double lx = flattenAxesValue(axes.lx);

        // int direction;
        // if (ly > -0.8) {
        //     direction = 1;
        // } else {
        //     direction = -1;
        // }

        // double power = flattenAxesValue(Math.sqrt((lx * lx) + (ly * ly)) * direction);

        // flatten analog values of both triggers (max is 0.3 on triggers so its scaled up)
        double lt = flattenAxesValue(axes.lt * (1 / 0.3));
        double rt = flattenAxesValue(axes.rt * (1 / 0.3));

        // left trigger (reverse) limits right trigger (go forward) if pressed
        double power = rt - lt;

        if (lt == 1.0 && rt == 1.0) {
            int[] powers = {-100, 100};

            return powers;
        }

        int leftPowerPercent;
        int rightPowerPercent;

        // if left stick to left of center, limit left wheel power while keeping right wheel at
        // full power to create a left turn
        // if left stick to right of center, the wheels are flipped
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

        // flatten analog values of both triggers (max is 0.3 on triggers so its scaled up)
        double lt = flattenAxesValue(axes.lt * (1 / 0.3));
        double rt = flattenAxesValue(axes.rt * (1 / 0.3));

        // both triggers contribute to vibration
        double sum = lt + rt;

        // if both triggers make over 1.0 strength pressed together, limit it back to 1.0
        // change in future to allow the feeling of more vibration when trying to brake?
        if (sum > 1.0) {
            sum = 1.0;
        }

        // vibration scales off of 1000 (max vibration is 65535)
        int vibration = (int) (1000 * sum);

        return vibration;
    }

    public static void main(String[] args) throws Exception {
        if (!XInputDevice.isAvailable()) {
            System.out.println("XInput is not available!");
        }

        // get first connected controller
        XInputDevice aController = XInputDevice.getDeviceFor(0);

        // get first finch
        Finch aFinch = new Finch("A");

        while (true) {
            if (!aController.poll()) {
                System.out.println("Failed to poll controller A!");
                continue;
            }

            // get components and axes of controller
            XInputComponents aComponents = aController.getComponents();
            XInputAxes aAxes = aComponents.getAxes();

            // use function to get trigger values and calculate vibration
            int vibration = getVibration(aController);

            // System.out.println(vibration);

            // set vibration
            aController.setVibration(vibration, vibration);

            // use function to get power for both wheels
            int[] wheelPowers = getWheelPowers(aAxes);

            // set motors based on array from function
            aFinch.setMotors(wheelPowers[0], wheelPowers[1]);

            // aFinch.setMotors(0, 0);
        }
    }
}
