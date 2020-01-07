public class FishingPlan {
    private int fishingCycle;
    private int searchDelay;
    private boolean shiftLoot;
    private boolean useBuff;
    private int buffDuration;
    private int buffCount;

    public FishingPlan(int fishingCycle, int searchDelay, boolean shiftLoot, boolean useBuff, int buffDuration, int buffCount) {
        this.fishingCycle = fishingCycle;
        this.searchDelay = searchDelay;
        this.shiftLoot = shiftLoot;
        this.useBuff = useBuff;
        this.buffDuration = buffDuration * 60 * 1000;
        this.buffCount = buffCount;
    }

    public String getCommandArguments() {
        return fishingCycle + " " + searchDelay + " " + shiftLoot + " " + useBuff + " " + buffDuration + " " + buffCount;
    }
}
