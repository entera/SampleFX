package de.entera.samplefx.workbench

import java.util.concurrent.TimeUnit
import javafx.geometry.BoundingBox
import javafx.geometry.Bounds
import javafx.geometry.Side
import javafx.scene.Group
import javafx.scene.Node
import javafx.scene.Scene
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.Region

import com.google.common.base.Predicate
import org.hamcrest.Matcher
import org.testfx.matcher.base.GeneralMatchers

import de.entera.samplefx.utility.FxSpecification

import static org.hamcrest.Matchers.equalTo
import static org.hamcrest.Matchers.not
import static org.testfx.api.FxAssert.verifyThat
import static org.testfx.api.FxToolkit.setupScene
import static org.testfx.api.FxToolkit.showStage

class WorkbenchSpec extends FxSpecification {

    //---------------------------------------------------------------------------------------------
    // FIELDS.
    //---------------------------------------------------------------------------------------------

    Workbench workbench

    //---------------------------------------------------------------------------------------------
    // FIXTURE METHODS.
    //---------------------------------------------------------------------------------------------

    def setup() {
        setupScene() {
            workbench = new Workbench()
            return new Scene(workbench, 700, 550)
        }
        showStage()
    }

    //---------------------------------------------------------------------------------------------
    // FEATURE METHODS.
    //---------------------------------------------------------------------------------------------

    def "has workbench"() {
        expect:
        verifyThat ".workbench", equalTo(workbench)
    }

    def "xxx"() {
        setup:
        for (Group fill in fx.lookup(".fill").queryAll()) {
            def width = (fill.parent as Region).width
            (fill.children[0] as Region).prefWidth = width
        }
        for (Group fill in fx.lookup(".fill-height").queryAll()) {
            def width = (fill.parent as Region).height
            (fill.children[0] as Region).prefWidth = width
        }

        for (Region spacer in fx.lookup(".spacer").queryAll()) {
            println spacer
            //def spacer = new Region()
            HBox.setHgrow(spacer, Priority.ALWAYS)
            spacer.minWidth = Region.USE_PREF_SIZE
            spacer.prefWidth = Region.USE_COMPUTED_SIZE
        }
        fx.sleep(30, TimeUnit.MINUTES)
    }

    def "has content container"() {
        expect:
        verifyThat ".workbench .content-container", equalTo(workbench.contentContainer)
    }

    def "has tool bars"() {
        expect:
        verifyThat ".workbench .tool-bar.left", equalTo(workbench.leftToolBar)
        verifyThat ".workbench .tool-bar.right", equalTo(workbench.rightToolBar)
        verifyThat ".workbench .tool-bar.top", equalTo(workbench.topToolBar)
        verifyThat ".workbench .tool-bar.bottom", equalTo(workbench.bottomToolBar)
    }

    def "has tool containers"() {
        expect:
        verifyThat ".workbench .tool-container.left", equalTo(workbench.leftToolContainer)
        verifyThat ".workbench .tool-container.right", equalTo(workbench.rightToolContainer)
        verifyThat ".workbench .tool-container.top", equalTo(workbench.topToolContainer)
        verifyThat ".workbench .tool-container.bottom", equalTo(workbench.bottomToolContainer)
    }

    def "isInsideBounds()"() {
        def parentBounds = bounds(0, 0, 500, 80)

        expect:
        verifyThat bounds(0, 0, 500, 80), isInsideBounds(parentBounds)
        verifyThat bounds(0, 0, 100, 10), isInsideBounds(parentBounds)
        verifyThat bounds(400, 70, 100, 10), isInsideBounds(parentBounds)
        verifyThat bounds(0, 0, 1000, 1000), not(isInsideBounds(parentBounds))
        verifyThat bounds(1000, 1000, 0, 0), not(isInsideBounds(parentBounds))
    }

    def "isInsideBisectionOfBounds()"() {
        def parentBounds = bounds(0, 0, 500, 80)

        expect:
        verifyThat bounds(  0,  0, 250, 80), isInsideBisectionOfBounds(parentBounds, Side.LEFT)
        verifyThat bounds(250,  0, 250, 80), isInsideBisectionOfBounds(parentBounds, Side.RIGHT)
        verifyThat bounds(  0,  0, 500, 40), isInsideBisectionOfBounds(parentBounds, Side.TOP)
        verifyThat bounds(  0, 40, 500, 40), isInsideBisectionOfBounds(parentBounds, Side.BOTTOM)
    }

    //---------------------------------------------------------------------------------------------
    // HELPER METHODS.
    //---------------------------------------------------------------------------------------------

    private Bounds bounds(double minX,
                          double minY,
                          double width,
                          double height) {
        return new BoundingBox(minX, minY, width, height)
    }

    private Matcher<Node> hasStyleClass(String styleClass) {
        def description = "has style class \"${styleClass}\""
        return GeneralMatchers.typeSafeMatcher(Node, description, { Node node ->
            styleClass in node.styleClass
        } as Predicate<Node>)
    }

    private Matcher<Bounds> isInsideBounds(Bounds bounds) {
        def description = "is inside \"${bounds}\""
        return GeneralMatchers.typeSafeMatcher(Bounds, description, { Bounds otherBounds ->
            bounds.contains(otherBounds)
        } as Predicate)
    }

    private Matcher<Bounds> isInsideBisectionOfBounds(Bounds bounds,
                                                     Side side) {
        def description = "is inside bisection \"${side}\" of \"${bounds}\""
        return GeneralMatchers.typeSafeMatcher(Bounds, description, { Bounds otherBounds ->
            bounds.contains(otherBounds)
        } as Predicate)
    }

}
