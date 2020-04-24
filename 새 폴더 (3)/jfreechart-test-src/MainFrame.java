import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import menu.MenuTree;

import org.apache.log4j.Appender;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.PatternLayout;

import worker.ChartSwingWorker;
import worker.SourceSwingWorker;
/**
 * 
 * @author kh2un
 *
 */
public class MainFrame extends JFrame implements TreeSelectionListener {
	private static final long serialVersionUID = 0x00L; 
	//private static Logger logger; 
    private JDesktopPane deskTopPane;
    private MenuTree menuTree;
    private JScrollPane menuTreeScrollPane;
    private JSplitPane splitPane;

	/**
	 * 생성자 
	 */
    public MainFrame() {
    	//logger = Logger.getLogger(getClass()); 
        initComponents();
    }
    
    /*
     * (non-Javadoc)
     * @see javax.swing.event.TreeSelectionListener#valueChanged(javax.swing.event.TreeSelectionEvent)
     */
    public void valueChanged(TreeSelectionEvent e) { 
    	TreePath path = e.getPath();
    	// 
    	if(path.getPathCount() > 2) { 
    		String className = getClassName(path.getPath()); 
    		//
    		// 소스 보여주는 작업자 출동 
    		(new SourceSwingWorker(className, this.deskTopPane)).execute();     		
    		// 챠트 보여주는 작업자 출동 
    		(new ChartSwingWorker(className, this.deskTopPane)).execute();
    	}
    }
    /**
     * 트리 경로로 클래스 이름 찾기 
     * @param obj
     * @return
     */
    private String getClassName(Object[] obj) { 
		StringBuilder builder = new StringBuilder(); 
		
		for(int i = 0; i < obj.length; i++) { 
			builder.append(obj[i].toString()); 
			if(i < (obj.length - 1)) { 
				//builder.append(System.getProperty("file.separator"));
				builder.append("."); 
			}
		}
		
		return builder.toString(); 
    }
    /**
     * 
     */
    private void initComponents() {
        splitPane = new JSplitPane();
        deskTopPane = new JDesktopPane();
        menuTreeScrollPane = new JScrollPane();
        menuTree = new MenuTree(this); 

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        deskTopPane.setAutoscrolls(true); 
        splitPane.setRightComponent(deskTopPane);
        menuTreeScrollPane.setViewportView(menuTree.getComponent());
        splitPane.setLeftComponent(menuTreeScrollPane);
        splitPane.setDividerLocation(250); 
        splitPane.setDividerSize(3); 

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(splitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(splitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
        );
        
        // 제목 
        setTitle("JFreeChart 연습하기"); 

        pack();
        
        // 화면 중앙에 
        int screenWidth = 1280; 
        int screenHeight = 980; 
        // 
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-screenWidth)/2, (screenSize.height-screenHeight)/2, screenWidth, screenHeight);  	        
    }

    /**
     * 
     * @param args
     */
    public static void main(String args[]) {
    	Layout layout = new PatternLayout("[%5p] %l - %d{HH:mm:ss.SSS}%n      + %m%n");
    	Appender appender = new ConsoleAppender(layout);
    	BasicConfigurator.configure(appender);
    	  
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
}
