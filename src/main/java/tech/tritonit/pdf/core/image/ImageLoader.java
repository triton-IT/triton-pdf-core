package tech.tritonit.pdf.core.image;

import java.io.IOException;
import java.io.InputStream;

/**
 * Platform-specific and file specific interface to implement to be able to load an image.
 * It can be used to load an image from any source or to apply filter to an image.
 */
public interface ImageLoader {
    /**
     * Load an image from input stream to an ImageDate object.
     *
     * @param imageStream The stream to read to generate the image.
     * @return The image data.
     * @throws IOException When image cannot be loaded.
     */
    ImageData load(InputStream imageStream) throws IOException;
}
