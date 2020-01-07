public class ColorSettings {
    private int bobberColor;
    private int bitingColor;
    private int bobberSensitivity;
    private int bitingSensitivity;

    public ColorSettings(int bobberColor, int bobberSensitivity, int bitingColor, int bitingSensitivity) {
        this.bobberColor = bobberColor;
        this.bobberSensitivity = bobberSensitivity;
        this.bitingColor = bitingColor;
        this.bitingSensitivity = bitingSensitivity;
    }

    public String getCommandArguments() {
        return bobberColor + " " + bobberSensitivity + " " + bitingColor + " " + bitingSensitivity;
    }
}
