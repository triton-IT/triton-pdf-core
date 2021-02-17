package tech.tritonit.pdf.core.document;

public enum Unit {
    NATIVE(1.0f),
    INCH(72.0f),
    MM(72.0f / 25.4f),
    CM(72.0f / 2.54f);

    private final float multiplier;

    Unit(float multiplier) {
        this.multiplier = multiplier;
    }

    public int toNative(float value) {
        return Math.round(value * multiplier);
    }

    public float toUnit(float value) {
        return value / multiplier;
    }
}
