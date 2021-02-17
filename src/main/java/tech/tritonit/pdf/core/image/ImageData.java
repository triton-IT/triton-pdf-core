package tech.tritonit.pdf.core.image;

/**
 * Interface representing the raw data of an image.
 * Its is used as a temporary object
 *
 * @author Régis Ramillien
 */
public class ImageData {
    /**
     * The width of the rendered image.
     */
    protected final int width;
    /**
     * The height of the rendered image.
     */
    protected final int height;
    /**
     * The pixels as RGB of the image.
     */
    protected final byte[] pixels;
    /**
     * The alpha value of each pixel.
     * It can be null is there is no transparency in image.
     */
    protected final byte[] alpha;

    /**
     * Construct an image data from width, height and pixels bytes.
     *
     * @param width  The width of the image.
     * @param height The height of the image.
     * @param pixels The pixels as byte array.
     */
    public ImageData(int width, int height, byte[] pixels) {
        this.width = width;
        this.height = height;
        this.pixels = pixels;
        this.alpha = null;
    }

    /**
     * Construct an image data from width, height and pixels bytes.
     *
     * @param width  The width of the image.
     * @param height The height of the image.
     * @param pixels The pixels as byte array.
     * @param alpha  The alpha value of each pixels.
     */
    public ImageData(int width, int height, byte[] pixels, byte[] alpha) {
        this.width = width;
        this.height = height;
        this.pixels = pixels;
        this.alpha = alpha;
    }

    /**
     * Get the width of the rendered image.
     *
     * @return The width of the rendered image.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get the height of the rendered image.
     *
     * @return The height of the rendered image.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get the pixels of the image.
     * The data is represented as 3 bytes per pixel (RGB).
     *
     * @return The data of the image.
     */
    public byte[] getPixels() {
        return pixels;
    }

    /**
     * Check if image as transparency.
     *
     * @return true if image contains alpha channel, false otherwise.
     */
    public boolean hasTransparency() {
        return alpha != null;
    }

    /**
     * Get alpha value of each pixel of image.
     *
     * @return The alpha value of each pixel.
     */
    public byte[] getPixelsAlpha() {
        return alpha;
    }
}
