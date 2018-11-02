package com.fx.api;

import javafx.scene.shape.Shape;

/**
 * Interface to drawing a pillars on @link{javafx.scene.canvas}.
 * @author T10N
 */
public interface PillarPaint {
    Shape drawPillar(int pos, int size);
}
