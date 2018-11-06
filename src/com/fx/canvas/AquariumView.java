package com.fx.canvas;

import com.fx.api.PillarPaint;
import com.fx.api.WaterPaint;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;

import javafx.scene.canvas.Canvas;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.LinkedList;
import java.util.List;

public class AquariumView extends Canvas implements PillarPaint, WaterPaint {

    // List of pillars for drawing
    private List<Shape> pillars;
    // List of water blocks for drawing
    private List<Shape> waterBlocks;

    // Starting of coordinate plane [0;0]
    private IntegerProperty OX;
    private IntegerProperty OY;

    // Width & height scale of single pillar or water block (square shape). Scale around pixel.
    private FloatProperty scale;

    // Width & height a single pillar (in pixels)
    private FloatProperty pillarWidth;
    private FloatProperty pillarHeight;

    // Colors for pillars and water
    private Color pillarColor;
    private Color waterColor;


    /**
     * Default constructor
     * All fields seated by default values.
     * OX = 50px;
     * OY = CANVAS_HEIGHT / 2;
     * scale = 10px;
     */
    public AquariumView() {
        this(1024 * 24, 768 );
    }

    public AquariumView(float prefWidth, float prefHeight) {
        this(prefWidth, prefHeight, 1.0f);
    }
    public AquariumView(float prefWidth, float prefHeight, float scale) {
        this.pillars = new LinkedList<>();
        this.waterBlocks = new LinkedList<>();

        this.OX = new SimpleIntegerProperty(50);
        this.OY = new SimpleIntegerProperty((int) (this.getHeight() / 2));
        this.scale = new SimpleFloatProperty(1.0f );

        this.pillarWidth = new SimpleFloatProperty(10.0f);
        this.pillarHeight = new SimpleFloatProperty(10.0f);

        this.setWidth(prefWidth * scale);
        this.setHeight(prefHeight * scale);
        this.setScale(scale);

        this.pillarColor = Color.BLACK;
        this.waterColor = new Color(0x10, 0x10,0xFA, 0x90);
    }

    @Override
    public Shape drawPillar(int pos, int size) {
        float pox = this.getOX() + (pos * this.getPillarWidth() * this.getScale()) + 1 ;
        float poy = this.getOY() - (size * this.getPillarHeight() * this.getScale());
        Rectangle pillar = new Rectangle( pox, poy, getPillarWidth() * getScale(), getPillarHeight() * getScale() * size);
        this.pillars.add(pillar);
        repaint();
        return pillar;
    }



    @Override
    public Shape drawWater(int posFrom, int height) {
        return null;
    }

    private void repaint() {
        GraphicsContext context = this.getGraphicsContext2D();
        context.setStroke(this.getPillarColor());
        context.setFill(this.getPillarColor().brighter());
        pillars.forEach((pillar)-> {
            Rectangle p = (Rectangle) pillar;
            context.fillRect(p.getX(), p.getY(), p.getWidth(), p.getHeight());
            context.strokeRect(p.getX(), p.getY(), p.getWidth(), p.getHeight());
        });
        context.setFill(this.waterColor);
        waterBlocks.forEach((waterBlock)-> {
            Rectangle w =  (Rectangle) waterBlock;
            context.fillRect(w.getX(), w.getY(), w.getWidth(), w.getHeight());
        });
        context.restore();
    }
    public int getOX() {
        return OX.get();
    }

    public IntegerProperty OXProperty() {
        return OX;
    }

    public int getOY() {
        return OY.get();
    }

    public IntegerProperty OYProperty() {
        return OY;
    }

    public float getScale() {
        return scale.get();
    }
    public void setScale(float scale) {
        this.scale.set(scale);
    }

    public FloatProperty scaleProperty() {
        return scale;
    }

    public float getPillarWidth() {
        return pillarWidth.get();
    }

    public FloatProperty pillarWidthProperty() {
        return pillarWidth;
    }

    public float getPillarHeight() {
        return pillarHeight.get();
    }

    public FloatProperty pillarHeightProperty() {
        return pillarHeight;
    }

    public Color getPillarColor() {
        return pillarColor;
    }

    public void setPillarColor(Color pillarColor) {
        this.pillarColor = pillarColor;
    }

    public Color getWaterColor() {
        return waterColor;
    }

    public void setWaterColor(Color waterColor) {
        this.waterColor = waterColor;
    }
}
