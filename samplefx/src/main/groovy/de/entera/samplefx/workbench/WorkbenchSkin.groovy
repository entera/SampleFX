package de.entera.samplefx.workbench

import javafx.fxml.FXML
import javafx.scene.Parent
import javafx.scene.layout.StackPane

import com.sun.javafx.scene.control.skin.BehaviorSkinBase
import de.entera.samplefx.utility.ControlUtils
import de.entera.samplefx.workbench.Workbench.WorkbenchBehavior

class WorkbenchSkin extends BehaviorSkinBase<Workbench, WorkbenchBehavior> {

    //---------------------------------------------------------------------------------------------
    // CONSTANTS.
    //---------------------------------------------------------------------------------------------

    private static final String FXML_FILE = "res/workbench.fxml"

    //---------------------------------------------------------------------------------------------
    // PRIVATE FIELDS.
    //---------------------------------------------------------------------------------------------

    private @FXML StackPane container

    //---------------------------------------------------------------------------------------------
    // CONSTRUCTORS.
    //---------------------------------------------------------------------------------------------

    public WorkbenchSkin(Workbench control,
                         WorkbenchBehavior behavior) {
        super(control, behavior)

        def rootNode = ControlUtils.loadFxmlResource(this.class.getResource(FXML_FILE), this)
        this.installBehaviour()
        this.installSkin(rootNode)
    }

    //---------------------------------------------------------------------------------------------
    // PRIVATE METHODS.
    //---------------------------------------------------------------------------------------------

    private void installBehaviour() {}

    private void installSkin(Parent rootNode) {
        this.children.setAll(rootNode.childrenUnmodifiable)
        this.skinnable.stylesheets.setAll(rootNode.stylesheets)
    }

}
