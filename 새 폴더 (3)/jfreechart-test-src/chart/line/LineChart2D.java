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
 * ������ �׷����� �ѱ� ��� 
 * 
 * @author kh2un
 *
 */
public class LineChart2D implements ChartViewer {
	private static Logger logger; 
	
	/**
	 * ������ 
	 */
	public LineChart2D() { 
		logger = Logger.getLogger(getClass()); 
	}
	
	@Override
	public JInternalFrame getChartFrame() {
		// �׷��� ����� 
		JFreeChart chart = ChartFactory.createLineChart(getClass().getName(), // title
								"������", // categoryAxisLabel 
								"������", // valueAxisLabel
								getDataSet(), // dataset
								PlotOrientation.VERTICAL,// orientation 
								true, // legend
								true, // tooltips
								false); // url
		
		// ��Ʈ���� 
		changeFont(chart); 
		// plot ������ 
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		// �� ��� ���� 
		changeLineShape(plot); 
		// �׷��� ���� ���� 
		plot.setBackgroundPaint(Color.white); 
		
		// �����ֱ� ���� ���·� ���� ��ȯ 
		JInternalFrame frame = new JInternalFrame(); 
		frame.getContentPane().add(new ChartPanel(chart)); 
		
		return frame; 
	}
	
	/**
	 * �׷��� ������ 
	 * @return
	 */
	private DefaultCategoryDataset getDataSet() { 
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset(); 
		String category1 = "�Է�"; 
		String category2 = "���"; 
		String test1 = "����1"; 
		String test2 = "����2"; 
		String test3 = "����3"; 
		String test4 = "����4"; 
		String test5 = "����5"; 
		
		// ������ �� ����
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
	 * ���� ���� ��� ���� 
	 * @param plot
	 */
	private void changeLineShape(CategoryPlot plot) { 
		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
		
		// ���� 
		renderer.setSeriesStroke(0, new BasicStroke(3));  
		// �� ���� ǥ�� �ֱ� 
		renderer.setSeriesShapesVisible(0, true); 
		// ǥ���ϴ� ���� ���� ���� 
		renderer.setSeriesShapesFilled(0, false); 
	}
	
	/**
	 * �ѱ� ����� ���� ��Ʈ �����ϱ� 
	 * @param plot
	 */
	@SuppressWarnings("unchecked")
	private void changeFont(JFreeChart chart) { 
		CategoryPlot plot = (CategoryPlot) chart.getPlot(); 
		Font labelFont = null; 
		
		// ������ ����  
		labelFont = plot.getDomainAxis().getLabelFont(); 
		plot.getDomainAxis().setLabelFont(new Font("����", labelFont.getStyle(), labelFont.getSize()));
		// 
		// ���� ���� ���� ���� ���̺� 
		labelFont = plot.getDomainAxis().getTickLabelFont(); 
		plot.getDomainAxis().setTickLabelFont(new Font("�ü�", labelFont.getStyle(), labelFont.getSize())); 
		// 
		// ������ ���� 
		labelFont = plot.getRangeAxis().getLabelFont(); 
		plot.getRangeAxis().setLabelFont(new Font("����", labelFont.getStyle(), labelFont.getSize())); 
		// 
		// ���� ���� ���� ���� ���̺� (���� �ѱ��� ������... �ٲ㺻��) 
		labelFont = plot.getRangeAxis().getTickLabelFont(); // ������ �� 
		plot.getRangeAxis().setTickLabelFont(new Font("�ü�", labelFont.getStyle(), labelFont.getSize())); 	
		// 
		// ���ʿ� ���� ��Ʈ ����. �̰� ��Ʈ�� �����ߴµ�, �� �ȸ���?
//		if(plot.getLegendItems() != null) { 
//			Iterator<LegendItem> legendIter = plot.getLegendItems().iterator(); 
//			LegendItem item = null;
//			
//			while(legendIter.hasNext()) { 
//				item = legendIter.next(); 
//				item.setLabelFont(new Font("�������", Font.PLAIN, 9)); 
//				logger.debug("���� ��Ʈ ���� --> (" + item.getLabel() + ")"); 
//			}	
//		}
		// ����.. �̰ɷ� �ϸ� �Դ´�. 
		// LegendItem�� ��Ʈ�� �������� ������ ���Ǵ°ǵ�... ���� ����. ���װ���..
		chart.getLegend().setItemFont(new Font("����", Font.PLAIN, 10)); 
	}
}
