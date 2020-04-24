package chart.bar;

import java.awt.Color;

import javax.swing.JInternalFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import chart.ChartViewer;
/**
 * 카테고리들이 쌓이는 형태 
 * 
 * @author kh2un
 *
 */
public class StackedBarChart3D implements ChartViewer {
	/**
	 * 생성자 
	 */
	public StackedBarChart3D() { 
		
	}

	@Override
	public JInternalFrame getChartFrame() {
		// 그래프 만들기 
		JFreeChart chart = ChartFactory.createStackedBarChart3D(getClass().getName(), // title
								"Category", // categoryAxisLabel 
								"Value", // valueAxisLabel
								getDataSet(), // dataset
								PlotOrientation.VERTICAL,// orientation 
								true, // legend
								true, // tooltips
								false); // url
		// plot 꺼내고 
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		// 그래프 배경색 변경 
		plot.setBackgroundPaint(Color.white); 
		
		// 보여주기 위한 형태로 만들어서 반환 
		JInternalFrame frame = new JInternalFrame(); 
		frame.getContentPane().add(new ChartPanel(chart)); 
		
		return frame; 
	}
	
	/**
	 * 그려줄 데이터 
	 * @return
	 */
	private DefaultCategoryDataset getDataSet() { 
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset(); 
		String category1 = "category#1"; 
		String category2 = "category#2"; 
		String category3 = "category#3"; 
		String test1 = "test#1"; 
		String test2 = "test#2"; 
		String test3 = "test#3"; 
		String test4 = "test#4"; 
		String test5 = "test#5"; 
		
		// 보여줄 값 설정
		dataSet.addValue(15.0, category1, test1);
		dataSet.addValue(39.0, category1, test2); 
		dataSet.addValue(22.0, category1, test3); 
		dataSet.addValue(45.0, category1, test4); 
		dataSet.addValue(7.0, category1, test5); 
		// 
		dataSet.addValue(37.0, category2, test1);
		dataSet.addValue(26.0, category2, test2); 
		dataSet.addValue(32.0, category2, test3); 
		dataSet.addValue(49.0, category2, test4); 
		dataSet.addValue(23.0, category2, test5); 	
		//
		dataSet.addValue(23.0, category3, test1);
		dataSet.addValue(42.0, category3, test2); 
		dataSet.addValue(12.0, category3, test3); 
		dataSet.addValue(35.0, category3, test4); 
		dataSet.addValue(39.0, category3, test5); 		
		
		return dataSet; 
	}	

}
