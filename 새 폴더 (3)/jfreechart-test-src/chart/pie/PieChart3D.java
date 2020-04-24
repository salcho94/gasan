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
 * 3차원 형태의 파이 
 * 
 * @author kh2un
 *
 */
public class PieChart3D implements ChartViewer {
	/**
	 * 생성자 
	 */
	public PieChart3D() { 
		
	}

	@Override
	public JInternalFrame getChartFrame() {
		DefaultPieDataset dataSet = new DefaultPieDataset(); 
		
		// 보여줄 값 설정 
		dataSet.setValue("data#1", 10.3); 
		dataSet.setValue("data#2", 3.7); 
		dataSet.setValue("data#3", 36.0); 
		dataSet.setValue("data#4", 20.7); 
		
		// 그래프 만들기 
		JFreeChart chart = ChartFactory.createPieChart3D(getClass().getName(), // title
								dataSet, // dataset
								true, // legend
								true, // tooltips
								false); // url 

		PiePlot plot = (PiePlot) chart.getPlot();
		// 첫번째 항목이 시작되는 각도 
		plot.setStartAngle(45); 
		// 각도가 증가되는 방향 
		plot.setDirection(Rotation.CLOCKWISE); 
		// 파이의 투명도 
		plot.setForegroundAlpha(0.8f); 
		// 배경 
		plot.setBackgroundPaint(Color.white); 
		
		
		// 보여주기 위한 형태로 만들어서 반환 
		JInternalFrame frame = new JInternalFrame(); 
		frame.getContentPane().add(new ChartPanel(chart)); 
		
		return frame;
	}
}
