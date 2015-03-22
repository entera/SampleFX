package de.entera.samplefx.workbench

import javafx.fxml.FXML
import javafx.scene.Parent
import javafx.scene.control.ToolBar
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.Region
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox

import com.sun.javafx.scene.control.skin.BehaviorSkinBase
import de.entera.samplefx.utility.ControlUtils
import de.entera.samplefx.workbench.Workbench.WorkbenchBehavior
class WorkbenchSkin extends BehaviorSkinBase<Workbench, WorkbenchBehavior> {

    //---------------------------------------------------------------------------------------------
    // CONSTANTS.
    //---------------------------------------------------------------------------------------------

    private static final String FXML_FILE = "res/workbench.fxml"

    //---------------------------------------------------------------------------------------------
    // FIEDLS.
    //---------------------------------------------------------------------------------------------

    @FXML ToolBar leftToolBar
    @FXML ToolBar rightToolBar
    @FXML ToolBar topToolBar
    @FXML ToolBar bottomToolBar

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
        this.installSkin(rootNode)
        this.installBehaviour()
    }

    //---------------------------------------------------------------------------------------------
    // PRIVATE METHODS.
    //---------------------------------------------------------------------------------------------

    protected void layoutChildren(double contentX,
                                  double contentY,
                                  double contentWidth,
                                  double contentHeight) {
        def spacers = this.skinnable.lookupAll(".spacer")
        for (spacer in spacers) {
            if (spacer instanceof Region) {
                this.assignSpacerLayout(spacer)
            }
        }
        def fills = this.skinnable.lookupAll(".fill")
        for (fill in fills) {
            if (fill instanceof Region) {
                HBox.setHgrow(fill, Priority.ALWAYS)
                HBox.setHgrow(fill.parent, Priority.ALWAYS)
            }
        }
        super.layoutChildren(contentX, contentY, contentWidth, contentHeight)
    }

    private void installSkin(Parent rootNode) {
        this.children.setAll(rootNode.childrenUnmodifiable)
        this.skinnable.stylesheets.setAll(rootNode.stylesheets)
    }

    private void installBehaviour() {}

    private void assignSpacerLayout(Region spacer) {
        HBox.setHgrow(spacer, Priority.ALWAYS)
        spacer.minWidth = Region.USE_PREF_SIZE
        spacer.prefWidth = Region.USE_COMPUTED_SIZE
        VBox.setVgrow(spacer, Priority.ALWAYS)
        spacer.minHeight = Region.USE_PREF_SIZE
        spacer.prefHeight = Region.USE_COMPUTED_SIZE
    }

}