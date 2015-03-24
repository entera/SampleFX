package de.entera.samplefx.workbench

import javafx.scene.control.Control
import javafx.scene.control.Skin
import javafx.scene.control.ToolBar
import javafx.scene.layout.StackPane

import com.sun.javafx.scene.control.behavior.BehaviorBase
import com.sun.javafx.scene.control.behavior.KeyBinding

class Workbench extends Control {

    //---------------------------------------------------------------------------------------------
    // CONSTANTS.
    //---------------------------------------------------------------------------------------------

    private static final String DEFAULT_STYLE_CLASS = "workbench"

    //---------------------------------------------------------------------------------------------
    // CONSTRUCTORS.
    //---------------------------------------------------------------------------------------------

    public Workbench() {
        this.styleClass << DEFAULT_STYLE_CLASS
    }

    //---------------------------------------------------------------------------------------------
    // METHODS.
    //---------------------------------------------------------------------------------------------

    StackPane getContentContainer() { return this.workbenchSkin.contentContainer }

    StackPane getLeftToolContainer() { return this.workbenchSkin.leftToolContainer }
    StackPane getRightToolContainer() { return this.workbenchSkin.rightToolContainer }
    StackPane getTopToolContainer() { return this.workbenchSkin.topToolContainer }
    StackPane getBottomToolContainer() { return this.workbenchSkin.bottomToolContainer }

    ToolBar getLeftToolBar() { return this.workbenchSkin.leftToolBar }
    ToolBar getRightToolBar() { return this.workbenchSkin.rightToolBar }
    ToolBar getTopToolBar() { return this.workbenchSkin.topToolBar }
    ToolBar getBottomToolBar() { return this.workbenchSkin.bottomToolBar }

    //---------------------------------------------------------------------------------------------
    // PROTECTED METHODS.
    //---------------------------------------------------------------------------------------------

    protected Skin<Workbench> createDefaultSkin() {
        return new WorkbenchSkin(this, new WorkbenchBehavior(this))
    }

    //---------------------------------------------------------------------------------------------
    // PRIVATE METHODS.
    //---------------------------------------------------------------------------------------------

    private WorkbenchSkin getWorkbenchSkin() {
        return this.skin as WorkbenchSkin
    }

    //---------------------------------------------------------------------------------------------
    // STATIC INNER CLASSES.
    //---------------------------------------------------------------------------------------------

    class WorkbenchBehavior extends BehaviorBase<Workbench> {
        public WorkbenchBehavior(Workbench control) {
            super(control, Collections.<KeyBinding>emptyList())
        }
    }

}
