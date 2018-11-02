package com.fx.canvas;

import com.fx.api.PillarPaint;
import com.fx.api.WaterPaint;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import javafx.scene.canvas.Canvas;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.LinkedList;
import java.util.List;

public class AquariumView extends Canvas implements PillarPaint, WaterPaint {

    // List of shapes for drawing
    private List<Shape> shapes;

    // Starting of coordinate plane [0;0]
    private IntegerProperty OX;
    private IntegerProperty OY;

    // Width & height of single pillar or water block (square shape). Scale around pixel.
    private IntegerProperty scale;

    /**
     * Default constructor
     * All fields seated by default values.
     * OX = 50px;
     * OY = CANVAS_HEIGHT / 2;
     * scale = 10px;
     */
    public AquariumView() {
        this.shapes = new LinkedList<>();
        this.OX = new SimpleIntegerProperty(50);
        this.OY = new SimpleIntegerProperty((int) (this.getHeight() / 2));
        this.scale = new SimpleIntegerProperty(10 );
    }

    @Override
    public Shape drawPillar(int pos, int size) {
        Shape pillar = new Rectangle(this.OX, this.OY)
    }

    @Override
    public Shape drawWater(int posFrom, int height) {
        return null;
    }
}
