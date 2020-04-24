package chart.pie;

import java.awt.Color;

import javax.swing.JInternalFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import chart.ChartViewer;
/**
 * �⺻ ���� 
 * @author kh2un
 *
 */
public class PieChart2D implements ChartViewer {
	/**
	 * ������ 
	 */
	public PieChart2D() { 
		
	}
	/*
	 * (non-Javadoc)
	 * @see chart.ChartViewer#getChartPanel()
	 */
	public JInternalFrame getChartFrame() { 
		DefaultPieDataset dataSet = new DefaultPieDataset(); 
		
		// ������ �� ���� 
		dataSet.setValue("data#1", 10.3); 
		dataSet.setValue("data#2", 53.7); 
		dataSet.setValue("data#3", 36.0); 
		
		// �׷��� ����� 
		JFreeChart chart = ChartFactory.createPieChart(getClass().getName(), // title
								dataSet, // dataset
								true, // legend
								true, // tooltips
								false); // url 

		
		PiePlot plot = (PiePlot) chart.getPlot();
		// �̷������� ��ĥ ����.. ������ ���� ������, �⺻���� ���̺귯���� ������ ��
		plot.setSectionPaint("data#1", new Color(21, 213, 88)); 
		plot.setSectionPaint("data#2", new Color(147, 244, 181)); 
		//plot.setSectionPaint("data#3", new Color(213, 251, 226)); // �̰� �ý����� ������ ������... 
		// ������ ���� ���� 
		plot.setExplodePercent("data#1", 0.30); 
		
		// �����ֱ� ���� ���·� ���� ��ȯ 
		JInternalFrame frame = new JInternalFrame(); 
		frame.getContentPane().add(new ChartPanel(chart)); 
		
		return frame; 
	}	
}
