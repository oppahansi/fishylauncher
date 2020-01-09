import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.prefs.Preferences;

public class Utils {
    public static String FishyStartCommand = "java -jar fishy.jar";

    static String getRandomString() {
        int leftLimitUC = 65;
        int rightLimitUC = 90;
        int leftLimit = 97;
        int rightLimit = 122;
        Random random = new Random();

        int targetStringLength = random.nextInt(11) + 5;
        StringBuilder buffer = new StringBuilder(targetStringLength);

        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedIntUC = leftLimitUC + (int) (random.nextFloat() * (rightLimitUC - leftLimitUC + 1));
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));

            if (random.nextBoolean())
                buffer.append((char) randomLimitedInt);
            else
                buffer.append((char) randomLimitedIntUC);
        }

        return buffer.toString();
    }

    static void generateSampleImage(ImageView sampleImage) {
        try {
            Robot robot = new Robot();
            Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
            int width = 380;
            int height = 150;
            Rectangle captureRect = new Rectangle((int) mouseLocation.getX() - width / 4, (int) mouseLocation.getY() - height / 2, width, height);
            BufferedImage sampleImageCaptured = robot.createScreenCapture(captureRect);
            sampleImage.setImage(SwingFXUtils.toFXImage(sampleImageCaptured, null));
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    static void generateColorImage(ImageView imageView) {
        try {
            BufferedImage image = new BufferedImage(190, 150, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = image.createGraphics();
            Robot robot = new Robot();
            Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
            Color pixelColor = robot.getPixelColor((int) mouseLocation.getX(), (int) mouseLocation.getY());
            graphics.setColor(pixelColor);
            graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
            imageView.setImage(SwingFXUtils.toFXImage(image, null));
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    static void setupDefault() {
        Preferences prefs = Preferences.userRoot().node("fishybot");

        prefs.put("authKeyField", "authKey");

        prefs.putInt("fishingCycleField", 26000);
        prefs.putInt("searchDelayField", 3000);
        prefs.putInt("buffDurationField", 10);
        prefs.putInt("buffCountField", 10);
        prefs.putBoolean("useBuffCheckBox", false);
        prefs.putBoolean("shiftLootCheckBox", false);

        prefs.putBoolean("timeCheckBox", false);
        prefs.put("timeField", "13:37:42");
        prefs.putBoolean("hooksCheckBox", false);
        prefs.putInt("hooksField", 123);
        prefs.putBoolean("castsCheckBox", false);
        prefs.putInt("castsField", 123);
        prefs.putBoolean("hoursCheckBox", false);
        prefs.putInt("hoursField", 123);
        prefs.putBoolean("buffsUsedUpCheckBox", false);
        prefs.putBoolean("neverCheckBox", true);

        prefs.putInt("bobberSensitivity", 11);
        prefs.putInt("bobberSensSlider", 11);
        prefs.putInt("bitingSensitivity", 8);
        prefs.putInt("bitingSensSlider", 8);
    }
}
