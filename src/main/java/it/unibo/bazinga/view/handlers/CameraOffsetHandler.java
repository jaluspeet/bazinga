package it.unibo.bazinga.view.handlers;

import it.unibo.bazinga.view.geometry.GeometryRenderer;
import javafx.geometry.Point2D;

/**
 * Utility class to compute the camera offset.
 */
public final class CameraOffsetHandler {
	private CameraOffsetHandler() { }

	/**
	 * Compute the camera offset.
	 *
	 * @param renderer the renderer that will render the grid.
	 * @param camera the camera that will apply the offset.
	 * @param canvasWidth the width of the canvas.
	 * @param canvasHeight the height of the canvas.
	 * @param gridCols the number of columns of the grid.
	 * @param gridRows the number of rows of the grid.
	 * @return the camera offset.
	 */
	public static Point2D computeCameraOffset(GeometryRenderer renderer, PanZoomHandler camera, double canvasWidth, double canvasHeight, int gridCols, int gridRows) {
		Point2D baseOffset = renderer.computeCenterOffset(canvasWidth, canvasHeight, gridCols, gridRows, camera.getScale());
		return camera.applyCamera(baseOffset);
	}
}
