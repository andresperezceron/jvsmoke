package jvsmoke.core;

import jvsmoke.JvsConfig;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private ControllerForTest ctrl = new ControllerForTest(new JFrame(), null);

    @Test
    void index() {
        assertTrue(!ctrl.executeIndex);
        ctrl.executeAction(Controller.ACTION_INDEX);
        assertTrue(ctrl.executeIndex);
    }

    @Test
    void create() {
        assertTrue(!ctrl.executeCreate);
        ctrl.executeAction(ctrl.ACTION_CREATE);
        assertTrue(ctrl.executeCreate);
    }

    @Test
    void update() {
        assertTrue(!ctrl.executeUpdate);
        ctrl.executeAction(ctrl.ACTION_UPDATE);
        assertTrue(ctrl.executeUpdate);
    }

    @Test
    void delete() {
        assertTrue(!ctrl.executeDelete);
        ctrl.executeAction(ctrl.ACTION_DELETE);
        assertTrue(ctrl.executeDelete);
    }

    @Test
    void render() {
        assertTrue(!ctrl.executeRender);
        ctrl.executeAction(ctrl.ACTION_RENDER);
        assertTrue(ctrl.executeRender);
    }

    @Test
    void executeAction() {
        ctrl.executeCreate = false;
        ctrl.executeAction(ctrl.ACTION_CREATE);
        assertTrue(ctrl.executeCreate);
    }

    @Test
    void redirect() {
        ControllerForTest controller;
        ctrl.getView().dispose();
        ctrl.redirect(controller = new ControllerForTest(new JFrame(), null));
        assertNotNull(controller);
    }

    @Test
    void eActionContoller() {
        assertNotNull(ctrl.eActionContoller(ctrl.ACTION_DELETE));
    }

    @Test
    void eRedirect() {
        assertNotNull(ctrl.eRedirect(new ControllerForTest(new JFrame(), null)));
    }

    @Test
    void getView() {
        JFrame frame = new JFrame();
        Model model = null;
        ctrl = new ControllerForTest(frame, model);
        assertEquals(frame, ctrl.getView());

    }

    @Test
    void getModel() {
        JvsConfig.SQLITE3_STR_CONEXION = "jdbc:sqlite:db/jvsmoketest.db";
        ModelForTest model = new ModelForTest();
        ctrl = new ControllerForTest(new JFrame(), model);
        assertEquals(model, ctrl.getModel());
    }
}