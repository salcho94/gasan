package chart.pie;

import java.awt.Color;

import javax.swing.JInternalFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

import chart.ChartViewer;

/**
 * 3���� ������ ���� 
 * 
 * @author kh2un
 *
 */
public class PieChart3D implements ChartViewer {
	/**
	 * ������ 
	 */
	public PieChart3D() { 
		
	}

	@Override
	public JInternalFrame getChartFrame() {
		DefaultPieDataset dataSet = new DefaultPieDataset(); 
		
		// ������ �� ���� 
		dataSet.setValue("data#1", 10.3); 
		dataSet.setValue("data#2", 3.7); 
		dataSet.setValue("data#3", 36.0); 
		dataSet.setValue("data#4", 20.7); 
		
		// �׷��� ����� 
		JFreeChart chart = ChartFactory.createPieChart3D(getClass().getName(), // title
								dataSet, // dataset
								true, // legend
								true, // tooltips
								false); // url 

		PiePlot plot = (PiePlot) chart.getPlot();
		// ù��° �׸��� ���۵Ǵ� ���� 
		plot.setStartAngle(45); 
		// ������ �����Ǵ� ���� 
		plot.setDirection(Rotation.CLOCKWISE); 
		// ������ ���� 
		plot.setForegroundAlpha(0.8f); 
		// ��� 
		plot.setBackgroundPaint(Color.white); 
		
		
		// �����ֱ� ���� ���·� ���� ��ȯ 
		JInternalFrame frame = new JInternalFrame(); 
		frame.getContentPane().add(new ChartPanel(chart)); 
		
		return frame;
	}
}
