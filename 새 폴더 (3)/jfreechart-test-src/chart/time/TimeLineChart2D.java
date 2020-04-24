package chart.time;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

import javax.swing.JInternalFrame;
import javax.swing.Timer;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import chart.ChartViewer;
/**
 * 시간의 흐름에 따른 값 
 * 
 * @author kh2un
 *
 */
public class TimeLineChart2D implements ChartViewer {
	/**
	 * 생성자 
	 */
	public TimeLineChart2D() { 
		
	}

	@Override
	public JInternalFrame getChartFrame() {
		// 그래프 만들기 
		JFreeChart chart = ChartFactory.createTimeSeriesChart(getClass().getName(), // title 
										"time", // timeAxisLabel 
										"value", // valueAxisLabel 
										getDataSet(), 
										true, // legend 
										true, // tooltip 
										false); // urls
		
		XYPlot plot = (XYPlot) chart.getPlot(); 
		// 배경색 
		plot.setBackgroundPaint(Color.white); 
		// 시간 모양 변경 
		DateAxis timeAxis = (DateAxis) plot.getDomainAxis(); 
		timeAxis.setDateFormatOverride(new SimpleDateFormat("m:s"));  // 분:초  
		
		// 값 추가해주는 애 달기 
		ValueGenerator gen = new ValueGenerator(plot); 
		gen.start(); 
		
		// 보여주기 위한 형태로 만들어서 반환 
		JInternalFrame frame = new JInternalFrame(); 
		frame.getContentPane().add(new ChartPanel(chart)); 
		
		
		
		return frame; 
	}
	/**
	 * 
	 * @return
	 */
	private XYDataset getDataSet() { 
		TimeSeries series = new TimeSeries("time#1"); 
		TimeSeriesCollection dataSet = new TimeSeriesCollection(); 
		
		dataSet.addSeries(series); 
		
		return dataSet; 
	}
	
	/**
	 * 값 넣어주는 애 
	 * @author kh2un
	 *
	 */
	class ValueGenerator extends Timer implements ActionListener { 
		private static final long serialVersionUID = 0x00L; 
		private Logger logger; 
		private XYPlot plot; 
		private Random random; 
		
		/**
		 * 생성자 
		 */
		public ValueGenerator(XYPlot plot) { 
			super(1000, null); 
			logger = Logger.getLogger(getClass()); 
			
			this.plot = plot; 
			this.random = new Random(); 
			this.random.setSeed(System.currentTimeMillis()); 
			this.addActionListener(this); 
		}
		/*
		 * (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(final ActionEvent event) { 
			TimeSeries series = ((TimeSeriesCollection) this.plot.getDataset()).getSeries(0); 
			RegularTimePeriod period = RegularTimePeriod.createInstance(Second.class, new Date(), TimeZone.getDefault());
			int value = random.nextInt() % 50; 
			
			if(value < 0) { 
				value *= -1; 
			}
			
			series.add(period, value);
			if(series.getItemCount() > 11) { 
				int remove = series.getItemCount() - 11; 
				series.delete(0, remove);
				logger.debug("삭제 : 전체개수=" + series.getItemCount()); 
			}
		}		
	}
}
