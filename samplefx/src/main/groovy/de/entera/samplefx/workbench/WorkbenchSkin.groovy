package de.entera.samplefx.workbench

import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.control.ToolBar
import javafx.scene.input.KeyCode
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

    @FXML StackPane contentContainer

    @FXML StackPane leftToolContainer
    @FXML StackPane rightToolContainer
    @FXML StackPane topToolContainer
    @FXML StackPane bottomToolContainer

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

    private void installBehaviour() {
        boolean toolBarsVisible = true
        boolean toolContainerTopVisible = true
        boolean toolContainerRightVisible = true
        boolean toolContainerBottomVisible = true
        boolean toolContainerLeftVisible = true

        this.skinnable.scene.onKeyPressed = { event ->
            if (event.code == KeyCode.ESCAPE) {
                toolBarsVisible = !toolBarsVisible
            }
            else if (event.altDown && event.code == KeyCode.DIGIT1) {
                toolContainerLeftVisible = !toolContainerLeftVisible
            }
            else if (event.altDown && event.code == KeyCode.DIGIT2) {
                toolContainerRightVisible = !toolContainerRightVisible
            }
            else if (event.altDown && event.code == KeyCode.DIGIT3) {
                toolContainerBottomVisible = !toolContainerBottomVisible
            }
            else if (event.altDown && event.code == KeyCode.DIGIT4) {
                toolContainerTopVisible = !toolContainerTopVisible
            }
            changeToolBarVisibility(
                toolBarsVisible,
                toolBarsVisible,
                toolBarsVisible,
                toolBarsVisible
            )
            changeToolContainerVisibility(
                toolContainerTopVisible,
                toolContainerRightVisible,
                toolContainerBottomVisible,
                toolContainerLeftVisible
            )
            event.consume()
        } as EventHandler
    }

    private void assignSpacerLayout(Region spacer) {
        HBox.setHgrow(spacer, Priority.ALWAYS)
        spacer.minWidth = Region.USE_PREF_SIZE
        spacer.prefWidth = Region.USE_COMPUTED_SIZE
        VBox.setVgrow(spacer, Priority.ALWAYS)
        spacer.minHeight = Region.USE_PREF_SIZE
        spacer.prefHeight = Region.USE_COMPUTED_SIZE
    }

    private void changeToolBarVisibility(boolean top,
                                         boolean right,
                                         boolean bottom,
                                         boolean left) {
        changeNodeVisibility(topToolBar, top)
        changeNodeVisibility(rightToolBar, right)
        changeNodeVisibility(bottomToolBar, bottom)
        changeNodeVisibility(leftToolBar, left)
    }

    private void changeToolContainerVisibility(boolean top,
                                               boolean right,
                                               boolean bottom,
                                               boolean left) {
        changeNodeVisibility(topToolContainer, top)
        changeNodeVisibility(rightToolContainer, right)
        changeNodeVisibility(bottomToolContainer, bottom)
        changeNodeVisibility(leftToolContainer, left)
    }

    private void changeNodeVisibility(Node node,
                                      boolean visible) {
        node.visible = visible
        node.managed = visible
    }

}