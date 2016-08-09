package ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import model.HistoGram;

/**
 * Displays a photo of a histogram
 * 
 * @author ahmed
 *
 */
public class HistogramDrawer extends JPanel
{
	// constants
	int MARGIN_X = 20;
	int MARGIN_Y = 20;

	// fields
	private BufferedImage bufferedImage;

	// Constructor
	public HistogramDrawer()
	{

	}

	@Override
	protected void paintComponent(Graphics g)
	{
		if (this.bufferedImage != null)
			g.drawImage(bufferedImage, 0, 0, null);
	}

	/**
	 * makes and image of a histogram and updates it to the UI the scales are
	 * dynamic
	 */
	public void drawHistogram(HistoGram histogram)
	{
		// calculate the max value for y
		long maxCount = 0;
		for (long c : histogram.getCount())
			maxCount = Math.max(maxCount, c);
		long maxValue = 10;
		do
		{
			maxValue *= 10;
		} while (maxValue < maxCount);
		while (maxValue / 2 > maxCount)
			maxValue /= 2;

		BufferedImage newImage = getHistogramImage(getWidth(), getHeight(), histogram, histogram.getMinValue(),
				histogram.getMaxValue(), maxValue);
		this.bufferedImage = newImage;
		repaint();
	}

	public void drawHistogram(HistoGram histogram, double minX, double maxX, long maxValue)
	{
		BufferedImage newImage = getHistogramImage(getWidth(), getHeight(), histogram, minX, maxX, maxValue);
		this.bufferedImage = newImage;
		repaint();
	}

	/**
	 * draws an iamge of the given histogram
	 * 
	 * 
	 */
	private BufferedImage getHistogramImage(int imageWidth, int imageHeight, HistoGram histogram, double minX,
			double maxX, long maxValue)
	{
		// make the background
		BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, imageWidth, imageHeight);

		// draw the bars
		double yScale = 1.0 * (getHeight() - 2 * MARGIN_Y) / (maxValue);
		g.setStroke(new BasicStroke(100));
		g.setColor(Color.CYAN);
		for (int i = 0; i < histogram.getnPeriods(); i++)
		{
			// find the top left corner of the coloumn
			double coloumnValue = histogram.getMinValue()
					+ i * (histogram.getMaxValue() - histogram.getMinValue()) / histogram.getnPeriods();
			double x1 = (coloumnValue - minX) * imageWidth / (maxX - minX);
			double coloumnValue2 = histogram.getMinValue()
					+ (i + 1) * (histogram.getMaxValue() - histogram.getMinValue()) / histogram.getnPeriods();
			double x2 = (coloumnValue2 - minX) * imageWidth / (maxX - minX);

			int coloumnHeight = (int) (histogram.getCount()[i] * yScale);
			int y = imageHeight - (coloumnHeight + MARGIN_Y);
			g.fill3DRect((int) x1, y, (int)(x2 - x1), coloumnHeight, true);
		}

		// draw a line at the top
		g.setStroke(new BasicStroke(1));
		g.setColor(Color.BLACK);
		drawDashedLine(g, 0, MARGIN_Y, imageWidth, MARGIN_Y);

		// draw labels
		g.setColor(Color.BLACK);
		g.drawString(String.format("%.2f", minX), 2, imageHeight - 2);
		g.drawString(String.format("%.2f", maxX), imageWidth - 2 * MARGIN_X, imageHeight - 2);
		g.drawString("" + maxValue, 2, MARGIN_Y - 2);
		return image;
	}

	/**
	 * draws a dashed line with thin stroke
	 */
	public void drawDashedLine(Graphics g, int x1, int y1, int x2, int y2)
	{

		// creates a copy of the Graphics instance
		Graphics2D g2d = (Graphics2D) g.create();

		Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]
		{ 9 }, 0);
		g2d.setStroke(dashed);
		g2d.drawLine(x1, y1, x2, y2);

		// gets rid of the copy
		g2d.dispose();
	}

}
