import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;



public class GraficoATorta  extends ApplicationFrame {    public GraficoATorta(final String titolo) {
        super(titolo);
        final PieDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
    }    /**
     * Creazione del dataset da utilizzare per la generazione del grafi
     * Ogni grafico ha un suo dataset specifico
     * @return un dataset di default.
     */
    private PieDataset createDataset() {
        final DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Uno", new Double(43.2));
        dataset.setValue("Due", new Double(10.0));
        dataset.setValue("Tre", new Double(27.5));
        dataset.setValue("Quattro", new Double(17.5));
        dataset.setValue("Cinque", new Double(11.0));
        dataset.setValue("Sei", new Double(19.4));
        return dataset;        
    }
        
    /**
     * Metodo deputato alla creazione del grafico.
     * @param dataset  il dataset creato dal metodo createDataset
     * @return il grafico.
     */
    private JFreeChart createChart(final PieDataset dataset) {
        
        final JFreeChart chart = ChartFactory.createPieChart(
            "Grafico a Torta",   // Titolo
            dataset,             // Fonte dei dati
            true,               // La legenda non sarà inclusa
            true,                // Utilizza i Tooltips
            false                // Configura il grafico per generare una URL
        );
        final PiePlot plot = (PiePlot) chart.getPlot();
        plot.setInteriorGap(0.0);
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{2} {0}={1}"));
        return chart;
        
    }
    
    
    public static void main(final String[] args) {

        final GraficoATorta demo = new GraficoATorta("Grafici al gusto di caffè");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}