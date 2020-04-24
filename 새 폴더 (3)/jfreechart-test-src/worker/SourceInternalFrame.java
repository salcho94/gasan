package worker;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/**
 * 소스 보여주기 창 
 * @author kh2un
 *
 */
public class SourceInternalFrame extends JInternalFrame {
	private static final long serialVersionUID = 0x00L; 
    private JScrollPane sourceScrollPane;
    private JTextArea sourceArea;
    
    /**
     * 생성자 
     */
    public SourceInternalFrame(String title, String source) {
    	super(title, true, true, true, true); // title, resizable, closable, maximizable, iconifiable
        initComponents();
        this.sourceArea.setText(source); 
    }    
    
    /**
     * 
     */
    private void initComponents() {
        sourceScrollPane = new JScrollPane();
        sourceArea = new JTextArea();

        sourceScrollPane.setViewportView(sourceArea);
        sourceArea.setEditable(false); 
        sourceArea.setWrapStyleWord(true); 
        sourceArea.setAutoscrolls(true); 
        sourceArea.setTabSize(4); 
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sourceScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sourceScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
        );

        this.setSize(640, 480); 
        pack();
    }// </editor-fold>    
}
