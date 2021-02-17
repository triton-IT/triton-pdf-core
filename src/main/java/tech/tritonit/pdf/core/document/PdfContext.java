package tech.tritonit.pdf.core.document;

import tech.tritonit.pdf.core.font.FontVariant;

public class PdfContext {
    private Unit unit = Unit.MM;
    private FontVariant defaultFontVariant;

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public FontVariant getDefaultFontVariant() {
        return defaultFontVariant;
    }

    public void setDefaultFontVariant(FontVariant fontVariant) {
        this.defaultFontVariant = fontVariant;
    }
}
