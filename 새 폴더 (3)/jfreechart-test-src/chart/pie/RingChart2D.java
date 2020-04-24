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
 * 링 차트 
 * @author kh2un
 *
 */
public class RingChart2D implements ChartViewer {
	/**
	 * 생성자 
	 */
	public RingChart2D() { 
		
	}
	
	@Override
	public JInternalFrame getChartFrame() {
		DefaultPieDataset dataSet = new DefaultPieDataset(); 
		
		// 보여줄 값 설정 
		dataSet.setValue("data#1", 10.3); 
		dataSet.setValue("data#2", 53.7); 
		dataSet.setValue("data#3", 36.0); 
		
		// 그래프 만들기 
		JFreeChart chart = ChartFactory.createRingChart(getClass().getName(), // title
								dataSet, // dataset
								true, // legend
								true, // tooltips
								false); // url 

		
		RingPlot plot = (RingPlot) chart.getPlot();
		// 한조각 떼어 내기 
		plot.setExplodePercent("data#1", 0.30); 
		// 링의 두께 
		plot.setSectionDepth(0.5); 
		// 배경색 
		plot.setBackgroundPaint(Color.white); 
		
		// 보여주기 위한 형태로 만들어서 반환 
		JInternalFrame frame = new JInternalFrame(); 
		frame.getContentPane().add(new ChartPanel(chart)); 
		
		return frame; 
	}

}
