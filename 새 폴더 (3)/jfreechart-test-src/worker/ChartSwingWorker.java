package worker;

import java.util.List;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.SwingWorker;

import org.apache.log4j.Logger;

import chart.ChartViewer;
/**
 * íƮ �����ֱ� �۾��� 
 * 
 * @author kh2un
 *
 */
public class ChartSwingWorker extends SwingWorker<Void, String> {
	private static Logger logger; 
	private String className; 
	private JDesktopPane deskTop; 
	
	/**
	 * ������ 
	 */
	public ChartSwingWorker(String className, JDesktopPane deskTop) { 
		logger = Logger.getLogger(getClass()); 
		this.className = className; 
		this.deskTop = deskTop; 
	}

	@Override 
	protected Void doInBackground() throws Exception { 
		publish(this.className); 
		return null; 
	}
	
	@Override 
	protected void process(List<String> dataList) { 
		for(String value : dataList) { 
			Class<?> clazz = null; 
			JInternalFrame frame = null; 
			
			try { 
				clazz = Class.forName(value);
				Object obj = clazz.newInstance();
				ChartViewer viewer = (ChartViewer) obj;
				// ������ ������ 
				frame = viewer.getChartFrame();
			}
			catch(Exception ex) {
				logger.error("�׷��� �����ֱ� ����", ex); 
			}
			
			// �Ӽ� �����ϰ� 
			if(frame != null) { 
				// ��ġ 
				int count = this.deskTop.getComponentCount() / 2;
				frame.setLocation(count * 20, 0); 
				// 
				frame.setMaximizable(true); 
				frame.setIconifiable(true); 
				frame.setClosable(true); 
				frame.setTitle("chart : " + clazz.getName()); 
				frame.setSize(500, 400); 
				frame.setVisible(true); 
				this.deskTop.add(frame); 
				
				try { 
					frame.setSelected(true); 
				}
				catch(Exception ex) {} 
			}				
		}

	}
}
