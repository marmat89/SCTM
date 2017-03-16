
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class GraficoALinea extends ApplicationFrame {

	final XYSeries ds= new XYSeries("Dati");
	public GraficoALinea(final String titolo) {

		super(titolo);

		final XYDataset dataset = createDataset();

		final JFreeChart chart = createChart(dataset);

		ChartPanel chartPanel = new ChartPanel(chart);

		chartPanel.setPreferredSize(new java.awt.Dimension(700, 470));

		setContentPane(chartPanel);

		// try {
		//
		// ChartUtilities.saveChartAsPNG(new File(“E:\\chart3.png”), chart,
		// 1024, 768);
		//
		// } catch (IOException ex) {
		//
		// System.out.println(ex.getLocalizedMessage());
		//
		// }

	}

	/**
	 * 
	 * Creazione del dataset da utilizzare per la generazione del grafi
	 * 
	 * Ogni grafico ha un suo dataset specifico
	 * 
	 * @return un dataset di default.
	 */

	private XYDataset createDataset() {


		ds.add(1, 1);

		ds.add(2, 5);

		ds.add(3, -3);

		ds.add(4, 0);

		ds.add(5, 9);

		ds.add(6, 15);
		
		return new XYSeriesCollection(ds);
		
	}

	/**
	 * 
	 * Metodo deputato alla creazione del grafico.
	 * 
	 * @param dataset
	 *            il dataset creato dal metodo createDataset
	 * 
	 * @return il grafico.
	 */

	private JFreeChart createChart(final XYDataset dataset) {

		final JFreeChart chart = ChartFactory.createXYLineChart(
				"Grafico a Linea", // titolo

				"Label", // label asse delle X

				"Valori", // label asse dell Y

				dataset, // sorgente dei dati

				PlotOrientation.VERTICAL, // orientamento del grafico

				true, // mostra la legenda

				true, // usa i tooltip

				false

		);

		XYPlot plot = (XYPlot) chart.getPlot();

		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, true);

		plot.setRenderer(renderer);

		renderer.setBaseShapesVisible(true);

		renderer.setBaseShapesFilled(true);

		NumberFormat format = NumberFormat.getNumberInstance();

		format.setMaximumFractionDigits(2);

		XYItemLabelGenerator generator =

		new StandardXYItemLabelGenerator(

		StandardXYItemLabelGenerator.DEFAULT_ITEM_LABEL_FORMAT,

		format, format);

		renderer.setBaseItemLabelGenerator(generator);

		renderer.setBaseItemLabelsVisible(true);

		return chart;

	}

	public static void main(final String[] args) {

		final GraficoALinea demo = new GraficoALinea(
				"Grafici al gusto di caffè");

		demo.pack();

		RefineryUtilities.centerFrameOnScreen(demo);
		
		demo.setVisible(true);

		int count=7;
		while(true)
		{
			demo.ds.add(count, Math.random()*1000);
			if(demo.ds.getItemCount()>30)
			demo.ds.remove(0);
			count++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
