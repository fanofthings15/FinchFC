public class LightsConcept {
    int[][] COLORS = {{0, 0, 0}, {255, 0, 0}, {255, 255, 0}, {255, 85, 0}, {0, 255, 0}};

    public static void setTailLights(Finch finch, int hp) {
        if (hp < 0) {
            return;
        }

        int lightsEnabled = (int) (hp / 25);
        for (int i = 1; i < lightsEnabled + 1; i++) {
            finch.setTail(i, 0, 100, 0);
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

        int r = 0;
        int g = 0;
        int b = 0;
        switch (lastLightLevel) {
            case 0:
                r = 0;
                g = 0;
                b = 0;
                break;
        
            case 1:
                r = 100;
                g = 0;
                b = 0;
                break;

            case 2:
                r = 100;
                g = 40;
                b = 0;
                break;

            case 3:
                r = 100;
                g = 100;
                b = 0;
                break;

            case 4:
                r = 0;
                g = 100;
                b = 0;
                break;

            default:
                break;
        }

        finch.setTail(lightsEnabled + 1, r, g, b);
    }

    public static void main(String[] args) throws Exception {
        Finch finch = new Finch("A");

        int hp = 100;

        while (true) {
            setTailLights(finch, hp);

            System.out.println(hp);

            hp--;

            Thread.sleep(100);
        }
    }
}