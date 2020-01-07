import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.prefs.Preferences;

public class FishyLauncherController {
    public ImageView bobberColorImage;
    public ImageView bitingColorImage;
    public Slider bobberSensSlider;
    public Slider bitingSensSlider;
    public TextField bobberSensitivity;
    public TextField bitingSensitivity;
    public ImageView sampleImage;

    public TextField fishingHotKey;
    public TextField buffHotKey;
    public TextField sampleHotKey;
    public TextField bobberColorHotKey;
    public TextField biteColorHotKey;
    public TextField searchDelayField;
    public CheckBox useBuffCheckBox;
    public CheckBox shiftLootCheckBox;
    public TextField buffDurationField;
    public TextField buffCountField;
    public TextField fishingCycleField;
    public Button startButton;
    public Button closeButton;
    public RadioButton timeCheckBox;
    public RadioButton hooksCheckBox;
    public RadioButton castsCheckBox;
    public RadioButton hoursCheckBox;
    public RadioButton neverCheckBox;
    public RadioButton buffsUsedUpCheckBox;
    public TextField timeField;
    public TextField hooksField;
    public TextField castsField;
    public TextField hoursField;
    public Label timeFormatLabel;
    public LocalTime endTime;
    public CheckBox logoutCheckBox;
    public CheckBox shutDownPcCheckBox;

    private ColorSettings colorSettings;
    private FishingPlan fishingPlan;
    private ExitPlan exitPlan;

    private int bobberColor;
    private int bitingColor;

    @FXML
    public void initialize() {
        bobberSensSlider.valueProperty().addListener((observable, oldValue, newValue) -> bobberSensitivity.setText(String.valueOf(newValue.intValue())));
        bitingSensSlider.valueProperty().addListener((observable, oldValue, newValue) -> bitingSensitivity.setText(String.valueOf(newValue.intValue())));

        timeField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue == null || newValue == null) return;
            if (!oldValue.isEmpty() && !newValue.isEmpty())
                if (oldValue.equals(newValue)) return;

            try {
                endTime = LocalTime.parse(newValue);
                timeFormatLabel.setTextFill(javafx.scene.paint.Paint.valueOf("#00ff00"));
            } catch (DateTimeParseException e) {
                timeFormatLabel.setTextFill(javafx.scene.paint.Paint.valueOf("#ff0000"));
                endTime = null;
            }
        });

        setPreferences();
    }

    @FXML
    public void onFishyButtonPressed(ActionEvent actionEvent) {
        saveConfig();
        prepareConfigurations();
        startFishy(actionEvent);
    }

    @FXML
    public void onCloseButtonPressed(ActionEvent actionEvent) {
        exit();
    }

    @FXML
    public void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent == null || keyEvent.getCode() == null)
            return;

        KeyCode keyCode = keyEvent.getCode();

        if (keyCode == KeyCode.ESCAPE) {
            exit();
        } else if (keyCode == KeyCode.S) {
            Utils.generateSampleImage(sampleImage);
        } else if (keyCode == KeyCode.D) {
            Utils.generateColorImage(bobberColorImage);
            bobberColor = bobberColorImage.getImage().getPixelReader().getArgb(0, 0);
        } else if (keyCode == KeyCode.F) {
            Utils.generateColorImage(bitingColorImage);
            bitingColor = bitingColorImage.getImage().getPixelReader().getArgb(0, 0);
        }
    }

    private void exit() {
        saveConfig();
        System.exit(1);
    }

    private void startFishy(ActionEvent actionEvent) {
        try {
            String fullCommand = Utils.FishyStartCommand + " " +
                    colorSettings.getCommandArguments() + " " +
                    fishingPlan.getCommandArguments() + " " +
                    exitPlan.getCommandArguments();
            ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).setIconified(true);
            Process process = Runtime.getRuntime().exec(fullCommand);
            process.waitFor();
            ((Stage) ((Button) actionEvent.getSource()).getScene().getWindow()).setIconified(false);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void prepareConfigurations() {
        colorSettings = new ColorSettings(
                bobberColor, Integer.parseInt(bobberSensitivity.getText()),
                bitingColor, Integer.parseInt(bitingSensitivity.getText())
        );

        fishingPlan = new FishingPlan(
                Integer.parseInt(fishingCycleField.getText()),
                Integer.parseInt(searchDelayField.getText()),
                shiftLootCheckBox.isSelected(),
                useBuffCheckBox.isSelected(),
                Integer.parseInt(buffDurationField.getText()),
                Integer.parseInt(buffCountField.getText())
        );

        if (endTime == null) endTime = LocalTime.parse(timeField.getText());
        exitPlan = new ExitPlan(
                timeCheckBox.isSelected(), endTime,
                hooksCheckBox.isSelected(), Integer.parseInt(hooksField.getText()),
                castsCheckBox.isSelected(), Integer.parseInt(castsField.getText()),
                hoursCheckBox.isSelected(), Integer.parseInt(hoursField.getText()),
                neverCheckBox.isSelected(),
                buffsUsedUpCheckBox.isSelected(),
                logoutCheckBox.isSelected(),
                shutDownPcCheckBox.isSelected()
        );
    }

    private void setPreferences() {
        Preferences prefs = Preferences.userRoot().node("fishybot");

        int fishingCycle = prefs.getInt("fishingCycleField", 0);
        if (fishingCycle == 0)
            Utils.setupDefault();
        else {
            fishingCycleField.setText(String.valueOf(prefs.getInt("fishingCycleField", 26000)));
            searchDelayField.setText(String.valueOf(prefs.getInt("searchDelayField", 3000)));
            buffDurationField.setText(String.valueOf(prefs.getInt("buffDurationField", 10)));
            buffCountField.setText(String.valueOf(prefs.getInt("buffCountField", 10)));
            useBuffCheckBox.setSelected(prefs.getBoolean("useBuffCheckBox", false));
            shiftLootCheckBox.setSelected(prefs.getBoolean("shiftLootCheckBox", false));

            timeCheckBox.setSelected(prefs.getBoolean("timeCheckBox", false));
            timeField.setText(prefs.get("timeField", "13:37:42"));
            hooksCheckBox.setSelected(prefs.getBoolean("hooksCheckBox", false));
            hooksField.setText(String.valueOf(prefs.getInt("hooksField", 123)));
            castsCheckBox.setSelected(prefs.getBoolean("castsCheckBox", false));
            castsField.setText(String.valueOf(prefs.getInt("castsField", 123)));
            hoursCheckBox.setSelected(prefs.getBoolean("hoursCheckBox", false));
            hoursField.setText(String.valueOf(prefs.getInt("hoursField", 123)));
            buffsUsedUpCheckBox.setSelected(prefs.getBoolean("buffsUsedUpCheckBox", false));
            neverCheckBox.setSelected(prefs.getBoolean("neverCheckBox", true));

            bobberSensitivity.setText(String.valueOf(prefs.getInt("bobberSensitivity", 11)));
            bobberSensSlider.setValue(prefs.getInt("bobberSensitivity", 11));
            bitingSensitivity.setText(String.valueOf(prefs.getInt("bitingSensitivity", 8)));
            bitingSensSlider.setValue(prefs.getInt("bitingSensSlider", 11));
        }
    }

    public void saveConfig() {
        Preferences prefs = Preferences.userRoot().node("fishybot");

        prefs.putInt("fishingCycleField", Integer.parseInt(fishingCycleField.getText()));
        prefs.putInt("searchDelayField", Integer.parseInt(searchDelayField.getText()));
        prefs.putInt("buffDurationField", Integer.parseInt(buffDurationField.getText()));
        prefs.putInt("buffCountField", Integer.parseInt(buffCountField.getText()));
        prefs.putBoolean("useBuffCheckBox", useBuffCheckBox.isSelected());
        prefs.putBoolean("shiftLootCheckBox", shiftLootCheckBox.isSelected());

        prefs.putBoolean("timeCheckBox", timeCheckBox.isSelected());
        prefs.put("timeField", "13:37:42");
        prefs.putBoolean("hooksCheckBox", hooksCheckBox.isSelected());
        prefs.putInt("hooksField", Integer.parseInt(hooksField.getText()));
        prefs.putBoolean("castsCheckBox", castsCheckBox.isSelected());
        prefs.putInt("castsField", Integer.parseInt(castsField.getText()));
        prefs.putBoolean("hoursCheckBox", hoursCheckBox.isSelected());
        prefs.putInt("hoursField", Integer.parseInt(hoursField.getText()));
        prefs.putBoolean("buffsUsedUpCheckBox", buffsUsedUpCheckBox.isSelected());
        prefs.putBoolean("neverCheckBox", neverCheckBox.isSelected());

        prefs.putInt("bobberSensitivity", Integer.parseInt(bobberSensitivity.getText()));
        prefs.putInt("bobberSensSlider", Integer.parseInt(bobberSensitivity.getText()));
        prefs.putInt("bitingSensitivity", Integer.parseInt(bitingSensitivity.getText()));
        prefs.putInt("bitingSensSlider", Integer.parseInt(bitingSensitivity.getText()));
    }
}
