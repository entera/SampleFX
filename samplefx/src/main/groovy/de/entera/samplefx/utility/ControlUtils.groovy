package de.entera.samplefx.utility

import javafx.fxml.FXMLLoader
import javafx.scene.Parent

/**
 * This class provides some helper methods for JavaFX `Control`'s.
 */
final class ControlUtils {

    //---------------------------------------------------------------------------------------------
    // PRIVATE CONSTRUCTORS.
    //---------------------------------------------------------------------------------------------

    private ControlUtils() {
        throw new UnsupportedOperationException()
    }

    //---------------------------------------------------------------------------------------------
    // STATIC METHODS.
    //---------------------------------------------------------------------------------------------

    /**
     * Loads and creates `Node`s from an FXML definition file.
     *
     * @param location The location of the FXML file.
     * @param controller The controller object.
     * @return The parent node from the FXML file.
     */
    static Parent loadFxmlNodes(URL location,
                                Object controller) {
        def fxmlLoader = new FXMLLoader()
        fxmlLoader.location = location
        fxmlLoader.controller = controller
        def rootNode = fxmlLoader.load() as Parent
        return rootNode
    }

}
