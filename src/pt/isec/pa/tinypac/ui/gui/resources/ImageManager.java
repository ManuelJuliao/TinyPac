package pt.isec.pa.tinypac.ui.gui.resources;

import javafx.scene.image.Image;

import java.io.InputStream;
import java.util.HashMap;

/**
 * This class represents the ImageManager
 */
public class ImageManager {
    /**
     * Constructs an ImageManager object
     */
    private ImageManager() { }
    private static final HashMap<String, Image> images = new HashMap<>();

    /**
     * Returns image given the filename
     * @param filename image file
     * @return image
     */
    public static Image getImage(String filename) {
        Image image = images.get(filename);
        if (image == null)
            try (InputStream is = ImageManager.class.getResourceAsStream("images/"+filename)) {
                image = new Image(is);
                images.put(filename,image);
            } catch (Exception e) {
                return null;
            }
        return image;
    }
    /**
     * Returns external image given the filename
     * @param filename image file
     * @return image
     */
    public static Image getExternalImage(String filename) {
        Image image = images.get(filename);
        if (image == null)
            try {
                image = new Image(filename);
                images.put(filename,image);
            } catch (Exception e) {
                return null;
            }
        return image;
    }
    /**
     * Deletes image given the filename
     * @param filename image name
     */
    public static void purgeImage(String filename) { images.remove(filename); }

}
