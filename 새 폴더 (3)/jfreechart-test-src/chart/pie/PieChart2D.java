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
 * 기본 파이 
 * @author kh2un
 *
 */
public class PieChart2D implements ChartViewer {
	/**
	 * 생성자 
	 */
	public PieChart2D() { 
		
	}
	/*
	 * (non-Javadoc)
	 * @see chart.ChartViewer#getChartPanel()
	 */
	public JInternalFrame getChartFrame() { 
		DefaultPieDataset dataSet = new DefaultPieDataset(); 
		
		// 보여줄 값 설정 
		dataSet.setValue("data#1", 10.3); 
		dataSet.setValue("data#2", 53.7); 
		dataSet.setValue("data#3", 36.0); 
		
		// 그래프 만들기 
		JFreeChart chart = ChartFactory.createPieChart(getClass().getName(), // title
								dataSet, // dataset
								true, // legend
								true, // tooltips
								false); // url 

		
		PiePlot plot = (PiePlot) chart.getPlot();
		// 이런식으로 색칠 가능.. 지정해 주지 않으면, 기본으로 라이브러리가 선택한 색
		plot.setSectionPaint("data#1", new Color(21, 213, 88)); 
		plot.setSectionPaint("data#2", new Color(147, 244, 181)); 
		//plot.setSectionPaint("data#3", new Color(213, 251, 226)); // 이건 시스템이 결정한 색으로... 
		// 한조각 떼어 내기 
		plot.setExplodePercent("data#1", 0.30); 
		
		// 보여주기 위한 형태로 만들어서 반환 
		JInternalFrame frame = new JInternalFrame(); 
		frame.getContentPane().add(new ChartPanel(chart)); 
		
		return frame; 
	}	
}
