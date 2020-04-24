package chart.pie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JInternalFrame;
import javax.swing.Timer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import chart.ChartViewer;
/**
 * 뱅글 뱅글 돌아가는 파이 
 * @author kh2un
 *
 */
public class RotationPie implements ChartViewer {
	/**
	 * 생성자 
	 */
	public RotationPie() { 
		
	}
	
	@Override
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
		(new Rotator(plot)).start(); 
		
		// 보여주기 위한 형태로 만들어서 반환 
		JInternalFrame frame = new JInternalFrame(); 
		frame.getContentPane().add(new ChartPanel(chart)); 
		
		return frame; 
	}
	
	/**
	 * 돌리기 
	 * @author kh2un
	 *
	 */
	class Rotator extends Timer implements ActionListener { 
		private static final long serialVersionUID = 0x00l; 
		private PiePlot plot;
		private int angle; 
		/**
		 * 생성자 
		 * @param p
		 */
		public Rotator(PiePlot p) { 
			super(100, null); 
			
			this.plot = p; 
			this.angle = 0; 
			this.addActionListener(this); 
		}
		/*
		 * (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(final ActionEvent event) { 
			this.plot.setStartAngle(this.angle); 
			this.angle++; 
			// 한 바퀴만 돌자 
			if(this.angle == 360) { 
				this.removeActionListener(this); 
				this.stop(); // 정지 
			}
		}
	}
}
