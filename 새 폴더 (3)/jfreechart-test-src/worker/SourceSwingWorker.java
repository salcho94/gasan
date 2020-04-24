package worker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
/**
 * �ҽ� �����ִ� �۾��� 
 * @author kh2un
 *
 */
public class SourceSwingWorker extends SwingWorker<Void, String> {
	//private static Logger logger; 
	private String sourceName; 
	private JDesktopPane deskTop; 
	/**
	 * ������ 
	 * @param className
	 * @param deskTop
	 */
	public SourceSwingWorker(String className, JDesktopPane deskTop) { 
		//logger = Logger.getLogger(getClass());  
		this.deskTop = deskTop; 
		
		StringBuilder builder = new StringBuilder(); 
		builder.append("./src/").append(className.replace('.', '/')).append(".java"); 
		this.sourceName = builder.toString(); 
	}

	@Override
	protected Void doInBackground() throws Exception {
		publish(this.sourceName); 
		return null;
	}
	
	@Override 
	protected void process(List<String> dataList) { 
		for(String value : dataList) { 
			File file = new File(value);
			// �� �����ų�, ���ų�, ���丮�� ��� 
			if(file == null || !file.exists() || file.isDirectory()) { 
				JOptionPane.showMessageDialog(null, "���� ���� ���� (" + value + ")", "", JOptionPane.ERROR_MESSAGE + JOptionPane.YES_OPTION);
				return; 
			}
			
			StringBuilder builder = new StringBuilder(); 
			
			try { 
				BufferedReader reader = new BufferedReader(new FileReader(file)); 
				String line = null; 
				
				while((line = reader.readLine()) != null) { 
					builder.append(line).append("\n"); 
				}
				
				reader.close(); 
			}
			catch(Exception ex) { 
				JOptionPane.showMessageDialog(null, "���� �б� ���� (" + value + ")", "", JOptionPane.ERROR_MESSAGE + JOptionPane.YES_OPTION);
				return; 
			}
			
			SourceInternalFrame frame = new SourceInternalFrame(value, builder.toString());
			
			// ��ġ 
			int count = this.deskTop.getComponentCount() / 2;
			frame.setLocation(count * 20, 420); 
			
			this.deskTop.add(frame);
			frame.setVisible(true); 
			// ������ �׻� íƮ�� �ǵ���... �����ڵ带 �Ϻη� ���� ���� ����... 			
		}
	}

}
