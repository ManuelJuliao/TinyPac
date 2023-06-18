package pt.isec.pa.tinypac.ui.gui.resources;

import javafx.scene.Parent;

/**
 * This class represents the CSSManager
 */
public class CSSManager {
    /**
     * Constructs a CSSManager object
     */
    private CSSManager() { }

    /**
     * Applies CSS given the parent and the filename
     * @param parent parent
     * @param filename css file
     */
    public static void applyCSS(Parent parent, String filename) {
        var url = CSSManager.class.getResource("css/"+filename);
        if (url == null)
            return;
        String fileCSS = url.toExternalForm();
        parent.getStylesheets().add(fileCSS);
    }
}
