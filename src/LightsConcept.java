public class LightsConcept {
    int[][] COLORS = {{0, 0, 0}, {255, 0, 0}, {255, 255, 0}, {255, 85, 0}, {0, 255, 0}};

    public static void setTailLights(Finch finch, int hp) {
        int lightsEnabled = (int) (hp / 25);
        for (int i = 0; i < lightsEnabled; i++) {
            finch.setTail(i, 0, 255, 0);
        }

        if (lightsEnabled == 4) {
            return;
        }

        int lastLightHP = (int) (hp % 25);
        int lastLightLevel;
        if (lastLightHP == 0) {
            lastLightLevel = 0;
        } else {
            lastLightLevel = (int) (lastLightHP / 6);
        }

        int r;
        int g;
        int b;
        switch (lastLightLevel) {
            case 0:
                r = 0;
                g = 0;
                b = 0;
                break;
        
            case 1:
                r = 255;
                g = 0;
                b = 0;
                break;

            case 2:
                r = 255;
                g = 85;
                b = 0;
                break;

            case 3:
                r = 255;
                g = 255;
                b = 0;
                break;

            case 4:
                r = 0;
                g = 255;
                b = 0;
                break;

            default:
                break;
        }
    }

    public static void main(String[] args) {
        Finch finch = new Finch("A");

        while (true) {
            setTailLights(finch, 50);
        }
    }
}