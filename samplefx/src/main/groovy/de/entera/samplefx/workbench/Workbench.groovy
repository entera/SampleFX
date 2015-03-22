package de.entera.samplefx.workbench

import javafx.scene.control.Control
import javafx.scene.control.Skin
import javafx.scene.control.ToolBar

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

    ToolBar getLeftToolBar() { return (this.skin as WorkbenchSkin).leftToolBar }
    ToolBar getRightToolBar() { return (this.skin as WorkbenchSkin).rightToolBar }
    ToolBar getTopToolBar() { return (this.skin as WorkbenchSkin).topToolBar }
    ToolBar getBottomToolBar() { return (this.skin as WorkbenchSkin).bottomToolBar }

    //---------------------------------------------------------------------------------------------
    // PROTECTED METHODS.
    //---------------------------------------------------------------------------------------------

    protected Skin<Workbench> createDefaultSkin() {
        return new WorkbenchSkin(this, new WorkbenchBehavior(this))
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
