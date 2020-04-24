package chart.pie;

import java.awt.Color;

import javax.swing.JInternalFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.RingPlot;
import org.jfree.data.general.DefaultPieDataset;

import chart.ChartViewer;
/**
 * �� ��Ʈ 
 * @author kh2un
 *
 */
public class RingChart2D implements ChartViewer {
	/**
	 * ������ 
	 */
	public RingChart2D() { 
		
	}
	
	@Override
	public JInternalFrame getChartFrame() {
		DefaultPieDataset dataSet = new DefaultPieDataset(); 
		
		// ������ �� ���� 
		dataSet.setValue("data#1", 10.3); 
		dataSet.setValue("data#2", 53.7); 
		dataSet.setValue("data#3", 36.0); 
		
		// �׷��� ����� 
		JFreeChart chart = ChartFactory.createRingChart(getClass().getName(), // title
								dataSet, // dataset
								true, // legend
								true, // tooltips
								false); // url 

		
		RingPlot plot = (RingPlot) chart.getPlot();
		// ������ ���� ���� 
		plot.setExplodePercent("data#1", 0.30); 
		// ���� �β� 
		plot.setSectionDepth(0.5); 
		// ���� 
		plot.setBackgroundPaint(Color.white); 
		
		// �����ֱ� ���� ���·� ���� ��ȯ 
		JInternalFrame frame = new JInternalFrame(); 
		frame.getContentPane().add(new ChartPanel(chart)); 
		
		return frame; 
	}

}
