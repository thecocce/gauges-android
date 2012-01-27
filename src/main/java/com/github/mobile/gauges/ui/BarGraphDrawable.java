package com.github.mobile.gauges.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.PaintDrawable;

/**
 * Drawable bar graph
 */
public class BarGraphDrawable extends PaintDrawable {

	/**
	 * Color of view counts on weekdays
	 */
	public static final int COLOR_VIEWS_WEEKDAY = Color.parseColor("#53685E");

	/**
	 * Color of view counts on weekends
	 */
	public static final int COLOR_VIEWS_WEEKEND = Color.parseColor("#626262");

	/**
	 * Color of people count on weekdays
	 */
	public static final int COLOR_PEOPLE_WEEKDAY = Color.parseColor("#6E9180");

	/**
	 * Color of people count on weekends
	 */
	public static final int COLOR_PEOPLE_WEEKEND = Color.parseColor("#7C7C7C");

	private static final int MIN_HEIGHT = 4;

	private static final int SPACING_X = 2;

	private final int[][] colors;

	private final long[][] data;

	private long max = 1;

	/**
	 * Create drawable bar graph for data and colors
	 *
	 * @param data
	 * @param colors
	 */
	public BarGraphDrawable(final long[][] data, final int[][] colors) {
		this.data = data;
		this.colors = colors;
		for (int i = 0; i < data.length; i++)
			for (int j = 0; j < data[i].length; j++)
				max = Math.max(max, data[i][j]);
	}

	@Override
	public void draw(final Canvas canvas) {
		final Paint paint = getPaint();
		final Rect bound = getBounds();
		final int width = bound.width() / data.length - SPACING_X;
		final int height = bound.height();
		int x = 0;
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				paint.setColor(colors[i][j]);
				float percentage = (float) data[i][j] / max;
				canvas.drawRect(x,
						height - Math.max(MIN_HEIGHT, percentage * height), x
								+ width, bound.bottom, paint);
			}
			x += width + SPACING_X;
		}
	}
}
