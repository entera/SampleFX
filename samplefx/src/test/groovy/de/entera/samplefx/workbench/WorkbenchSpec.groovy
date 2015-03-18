package de.entera.samplefx.workbench

import javafx.scene.Scene
import javafx.scene.control.Control

import org.testfx.api.FxRobot
import spock.lang.Specification

import static org.testfx.api.FxToolkit.registerPrimaryStage
import static org.testfx.api.FxToolkit.setupScene
import static org.testfx.api.FxToolkit.showStage

class WorkbenchSpec extends Specification {

    //---------------------------------------------------------------------------------------------
    // FIELDS.
    //---------------------------------------------------------------------------------------------

    FxRobot fx = new FxRobot()

    Workbench workbench

    //---------------------------------------------------------------------------------------------
    // FIXTURE METHODS.
    //---------------------------------------------------------------------------------------------

    def setup() {
        registerPrimaryStage()
        setupScene() {
            workbench = new Workbench()
            return new Scene(workbench, 700, 550)
        }
        showStage()
    }

    //---------------------------------------------------------------------------------------------
    // FEATURE METHODS.
    //---------------------------------------------------------------------------------------------

    def "is Control element"() {
        expect:
        workbench instanceof Control
    }

    def "has .workbench css class"() {
        expect:
        !fx.lookup(".workbench").queryAll().empty
    }

}
