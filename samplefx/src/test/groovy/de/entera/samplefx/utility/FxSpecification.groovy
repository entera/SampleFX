package de.entera.samplefx.utility

import org.testfx.api.FxRobot
import spock.lang.Specification

import static org.testfx.api.FxToolkit.registerPrimaryStage

abstract class FxSpecification extends Specification {

    //---------------------------------------------------------------------------------------------
    // FIELDS.
    //---------------------------------------------------------------------------------------------

    protected FxRobot fx = new FxRobot()

    //---------------------------------------------------------------------------------------------
    // FIXTURE METHODS.
    //---------------------------------------------------------------------------------------------

    def setupSpec() {
        registerPrimaryStage()
    }

}
