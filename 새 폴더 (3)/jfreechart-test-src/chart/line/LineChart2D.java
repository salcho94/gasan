package chart.line;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JInternalFrame;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import chart.ChartViewer;
/**
 * 꺽은선 그래프와 한글 출력 
 * 
 * @author kh2un
 *
 */
public class LineChart2D implements ChartViewer {
	private static Logger logger; 
	
	/**
	 * 생성자 
	 */
	public LineChart2D() { 
		logger = Logger.getLogger(getClass()); 
	}
	
	@Override
	public JInternalFrame getChartFrame() {
		// 그래프 만들기 
		JFreeChart chart = ChartFactory.createLineChart(getClass().getName(), // title
								"실험대상", // categoryAxisLabel 
								"실험방법", // valueAxisLabel
								getDataSet(), // dataset
								PlotOrientation.VERTICAL,// orientation 
								true, // legend
								true, // tooltips
								false); // url
		
		// 폰트변경 
		changeFont(chart); 
		// plot 꺼내고 
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		// 선 모양 변경 
		changeLineShape(plot); 
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
		String category1 = "입력"; 
		String category2 = "출력"; 
		String test1 = "실험1"; 
		String test2 = "실험2"; 
		String test3 = "실험3"; 
		String test4 = "실험4"; 
		String test5 = "실험5"; 
		
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
		
		return dataSet; 
	}
	/**
	 * 꺽은 선의 모양 변경 
	 * @param plot
	 */
	private void changeLineShape(CategoryPlot plot) { 
		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
		
		// 굵기 
		renderer.setSeriesStroke(0, new BasicStroke(3));  
		// 각 값에 표시 넣기 
		renderer.setSeriesShapesVisible(0, true); 
		// 표시하는 도형 내부 비우기 
		renderer.setSeriesShapesFilled(0, false); 
	}
	
	/**
	 * 한글 출력을 위해 폰트 변경하기 
	 * @param plot
	 */
	@SuppressWarnings("unchecked")
	private void changeFont(JFreeChart chart) { 
		CategoryPlot plot = (CategoryPlot) chart.getPlot(); 
		Font labelFont = null; 
		
		// 가로축 제목  
		labelFont = plot.getDomainAxis().getLabelFont(); 
		plot.getDomainAxis().setLabelFont(new Font("돋움", labelFont.getStyle(), labelFont.getSize()));
		// 
		// 가로 축의 값에 대한 레이블 
		labelFont = plot.getDomainAxis().getTickLabelFont(); 
		plot.getDomainAxis().setTickLabelFont(new Font("궁서", labelFont.getStyle(), labelFont.getSize())); 
		// 
		// 세로축 제목 
		labelFont = plot.getRangeAxis().getLabelFont(); 
		plot.getRangeAxis().setLabelFont(new Font("돋움", labelFont.getStyle(), labelFont.getSize())); 
		// 
		// 세로 축의 값에 대한 레이블 (여긴 한글이 없지만... 바꿔본다) 
		labelFont = plot.getRangeAxis().getTickLabelFont(); // 세로축 값 
		plot.getRangeAxis().setTickLabelFont(new Font("궁서", labelFont.getStyle(), labelFont.getSize())); 	
		// 
		// 범례에 대한 폰트 변경. 이건 폰트를 설정했는데, 왜 안먹지?
//		if(plot.getLegendItems() != null) { 
//			Iterator<LegendItem> legendIter = plot.getLegendItems().iterator(); 
//			LegendItem item = null;
//			
//			while(legendIter.hasNext()) { 
//				item = legendIter.next(); 
//				item.setLabelFont(new Font("맑은고딕", Font.PLAIN, 9)); 
//				logger.debug("범례 폰트 설정 --> (" + item.getLabel() + ")"); 
//			}	
//		}
		// 범례.. 이걸로 하면 먹는다. 
		// LegendItem에 폰트가 설정되지 않으면 사용되는건데... 버그 같애. 버그같애..
		chart.getLegend().setItemFont(new Font("돋움", Font.PLAIN, 10)); 
	}
}
