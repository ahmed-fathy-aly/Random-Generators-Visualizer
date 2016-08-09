package ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.awt.Button;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;
import model.ExponentialRandomGenerator;
import model.GaussianRandomGenerator;
import model.GeometricRandomGenerator;
import model.HistoGram;
import model.UniformRandomGenerator;
import model.WeibullRandomGenerator;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JCheckBox;

public class MainApplication
{
	// ui
	JFrame frame;
	JSpinner spinnerNValues;
	JSpinner spinnerPeriods;
	JSpinner spinnerMinX;
	JSpinner spinnerMaxX;
	JSpinner spinnerMaxY;
	JCheckBox chckboxDynamicScale;
	JSpinner spinnerAlpha;
	JSpinner spinnerU;
	JSpinner spinnerLambda;
	JSpinner spinnerBetaWeibull;
	JSpinner spinnerAlphaWeibull;
	JSpinner spinnerP;

	// fields
	HistogramDrawer histogramDrawer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					// set look and feel(comment this part if you don't want to use Synthetica)
					try
					{
						UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
					} catch (Exception e1)
					{
						e1.printStackTrace();
					}

					MainApplication window = new MainApplication();
					window.frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainApplication()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		// frame
		frame = new JFrame();
		frame.setTitle("Rnadom Number Generator");
		frame.setBounds(100, 100, 950, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(6, 118, 395, 332);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		// values and periods
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "  ", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(6, 4, 395, 97);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		// values spinner
		spinnerNValues = new JSpinner();
		spinnerNValues.setBounds(153, 41, 145, 49);
		panel_2.add(spinnerNValues);
		spinnerNValues.setModel(new SpinnerNumberModel(1000000.0, 1.0, 1000000000000.0, 1.0));

		// periods spinner
		spinnerPeriods = new JSpinner();
		spinnerPeriods.setBounds(38, 41, 70, 49);
		panel_2.add(spinnerPeriods);
		spinnerPeriods.setModel(new SpinnerNumberModel(25.0, 1.0, 1000.0, 1.0));

		JLabel lblPeriods = new JLabel("Periods");
		lblPeriods.setBounds(49, 27, 46, 14);
		panel_2.add(lblPeriods);

		JLabel lblNvalues = new JLabel("Values");
		lblNvalues.setBounds(205, 27, 46, 14);
		panel_2.add(lblNvalues);

		// uniform
		JButton buttonUniform = new JButton("Uniform");
		buttonUniform.setBounds(250, 25, 135, 47);
		buttonUniform.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				UniformRandomGenerator generator = new UniformRandomGenerator();
				updateHistogram(generator);
			}
		});
		panel.add(buttonUniform);

		// normal distribution
		JButton btnNormal = new JButton("Normal");
		btnNormal.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// read u and alpha
				double u = (double) spinnerU.getValue();
				double alpha = (double) spinnerAlpha.getValue();

				// make normal generator
				GaussianRandomGenerator generator = new GaussianRandomGenerator(u, alpha);
				updateHistogram(generator);
			}
		});
		btnNormal.setBounds(250, 83, 135, 47);
		panel.add(btnNormal);

		spinnerAlpha = new JSpinner();
		spinnerAlpha.setModel(new SpinnerNumberModel(1, -1000, 1000, 0.1));
		spinnerAlpha.setBounds(173, 83, 46, 47);
		panel.add(spinnerAlpha);

		JLabel lblAlpha = new JLabel("alpha");
		lblAlpha.setBounds(133, 99, 30, 14);
		panel.add(lblAlpha);

		spinnerU = new JSpinner();
		spinnerU.setModel(new SpinnerNumberModel(0, -1000, 1000, 0.1));
		spinnerU.setBounds(67, 83, 46, 47);
		panel.add(spinnerU);

		JLabel lblU = new JLabel("u");
		lblU.setBounds(31, 99, 26, 14);
		panel.add(lblU);

		// exponential distribution
		JButton btnExponential = new JButton("Exponential");
		btnExponential.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				double lambda = (double) spinnerLambda.getValue();
				ExponentialRandomGenerator generator = new ExponentialRandomGenerator(lambda);
				updateHistogram(generator);
			}
		});
		btnExponential.setBounds(250, 141, 135, 47);
		panel.add(btnExponential);

		spinnerLambda = new JSpinner();
		spinnerLambda.setModel(new SpinnerNumberModel(1, -1000, 1000, 0.1));
		spinnerLambda.setBounds(173, 141, 46, 47);
		panel.add(spinnerLambda);

		JLabel lblLambda = new JLabel("lambda");
		lblLambda.setBounds(127, 157, 36, 14);
		panel.add(lblLambda);

		// weibull distribution
		JButton btnWeibull = new JButton("Weibull");
		btnWeibull.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				double alpha = (double) spinnerAlphaWeibull.getValue();
				double beta = (double) spinnerBetaWeibull.getValue();
				WeibullRandomGenerator generator = new WeibullRandomGenerator(alpha, beta);
				updateHistogram(generator);
			}
		});
		btnWeibull.setBounds(250, 199, 137, 47);

		panel.add(btnWeibull);

		spinnerBetaWeibull = new JSpinner();
		spinnerBetaWeibull.setModel(new SpinnerNumberModel(10, 0.1, 1000, 0.1));
		spinnerBetaWeibull.setBounds(173, 199, 46, 47);
		panel.add(spinnerBetaWeibull);

		JLabel lblBeta = new JLabel("Shape");
		lblBeta.setBounds(127, 215, 36, 14);
		panel.add(lblBeta);

		spinnerAlphaWeibull = new JSpinner();
		spinnerAlphaWeibull.setModel(new SpinnerNumberModel(1, -1000, 1000, 0.1));
		spinnerAlphaWeibull.setBounds(67, 199, 46, 47);
		panel.add(spinnerAlphaWeibull);

		JLabel lblAlpha_1 = new JLabel("Scale");
		lblAlpha_1.setBounds(23, 215, 34, 14);
		panel.add(lblAlpha_1);

		// geometric distribution
		JButton btnGeometric = new JButton("Geometric");
		btnGeometric.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				double p = (double) spinnerP.getValue();
				GeometricRandomGenerator generator = new GeometricRandomGenerator(p);
				updateHistogram(generator);
			}
		});
		btnGeometric.setBounds(256, 257, 135, 47);
		panel.add(btnGeometric);

		spinnerP = new JSpinner();
		spinnerP.setModel(new SpinnerNumberModel(0.5, 0.00001, 0.99999, 0.1));
		spinnerP.setBounds(179, 257, 46, 47);
		panel.add(spinnerP);

		JLabel lblPsucceed = new JLabel("pSucced");
		lblPsucceed.setBounds(117, 273, 46, 14);
		panel.add(lblPsucceed);

		// histogram
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Histogram",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(410, -1, 520, 458);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		histogramDrawer = new HistogramDrawer();
		histogramDrawer.setBounds(6, 88, 504, 363);
		panel_1.add(histogramDrawer);

		// histogram controllers
		JLabel lblMinx = new JLabel("minX");
		lblMinx.setBounds(131, 39, 34, 14);
		panel_1.add(lblMinx);

		spinnerMinX = new JSpinner();
		spinnerMinX.setModel(new SpinnerNumberModel(0.0, -100000.0, 100000.0, 1.0));
		spinnerMinX.setBounds(175, 23, 46, 47);
		panel_1.add(spinnerMinX);

		JLabel lblMaxx = new JLabel("maxX");
		lblMaxx.setBounds(253, 39, 30, 14);
		panel_1.add(lblMaxx);

		spinnerMaxX = new JSpinner();
		spinnerMaxX.setModel(new SpinnerNumberModel(1.0, -100000.0, 100000.0, 1.0));
		spinnerMaxX.setBounds(290, 23, 46, 47);
		panel_1.add(spinnerMaxX);

		JLabel lblMaxy = new JLabel("maxY");
		lblMaxy.setBounds(357, 39, 30, 14);
		panel_1.add(lblMaxy);

		spinnerMaxY = new JSpinner();
		spinnerMaxY.setModel(new SpinnerNumberModel(1000000, 1, 10000000000.0, 1));
		spinnerMaxY.setBounds(394, 23, 97, 47);
		panel_1.add(spinnerMaxY);

		chckboxDynamicScale = new JCheckBox("Dynamic Scale");
		chckboxDynamicScale.setSelected(true);
		chckboxDynamicScale.setBounds(17, 35, 108, 23);
		panel_1.add(chckboxDynamicScale);

	}

	/**
	 * updates the histogram view
	 **/
	protected void updateHistogram(UniformRandomGenerator generator)
	{
		// get parameters
		double nValuesD = (double) spinnerNValues.getValue();
		long nValues = (long) nValuesD;
		double nPeriodsD = (double) (spinnerPeriods.getValue());
		int nPeriods = (int) nPeriodsD;
		double minX = (double) spinnerMinX.getValue();
		double maxX = (double) spinnerMaxX.getValue();
		double maxValueD = (double) spinnerMaxY.getValue();
		long maxValue = (long) maxValueD;

		HistoGram histogram = generator.getHistogram(nValues, nPeriods);

		if (chckboxDynamicScale.isSelected())
			histogramDrawer.drawHistogram(histogram);
		else
			histogramDrawer.drawHistogram(histogram, minX, maxX, maxValue);
	}

}
