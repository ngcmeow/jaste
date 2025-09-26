public class FeatureSet {
    protected String featureSetName;
    private boolean isEnabled = true;

    public String getFeatureSetName() {
        return featureSetName;
    }

    public void setFeatureSetName(String featureSetName) {
        this.featureSetName = featureSetName;
    }

    public void enable() {
        isEnabled = true;
    }

    public void disable() {
        isEnabled = false;
    }

    public boolean getState() {
        return isEnabled;
    }
}
