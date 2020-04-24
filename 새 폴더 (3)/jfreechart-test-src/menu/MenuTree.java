package menu;
import java.awt.Component;
import java.io.File;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeSelectionModel;
/**
 * �޴� ǥ�� 
 * @author kh2un
 *
 */
public class MenuTree {
	//private static Logger logger; 
	private JTree tree; 
	private TreeSelectionListener listener; 
	
	/**
	 * ������ 
	 */
	public MenuTree(TreeSelectionListener listener) { 
		//logger = Logger.getLogger(getClass()); 
		this.listener = listener; 
		initComponents(); 
	}
	/**
	 * 
	 * @return
	 */
	public Component getComponent() { 
		return this.tree; 
	}
	/**
	 * �ʱ�ȭ 
	 */
	private void initComponents() { 
		DefaultMutableTreeNode root = null; 
		DefaultMutableTreeNode node = null; 
		
		root = new DefaultMutableTreeNode("chart"); 
		// ���丮 ��� ã�� 
		File file = new File("bin/chart"); // ��δ� �ٲ��.... 
		File[] subDir = file.listFiles(); 
		
		if(subDir != null) { 
			for(int i = 0; i < subDir.length; i++) { 
				// ���丮�� �߰� 
				if(subDir[i].isDirectory()) { 
					// ������ ���� ����� 
					node = new DefaultMutableTreeNode(subDir[i].getName(), true);
					// ��Ʈ�� ���� �߰��ϰ� 
					root.add(node); 
					// ���� �ؿ� �ִ� �ֵ��� ��忡 �߰��ϰ� 
					addNodes(node, subDir[i]); 
				} 
			}
		}
		
		// ��Ʈ ��带 Ʈ���� �߰� 
		TreeModel model = new DefaultTreeModel(root); 
		this.tree = new JTree(model); 
		this.tree.setEditable(false); 
		this.tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION); 
		this.tree.addTreeSelectionListener(this.listener); 
	}
	/**
	 * .class ���� 
	 * @param name
	 * @return
	 */
	private String getClassName(String name)  {
		return name.substring(0, name.length() - 6); 
	}
	/**
	 * 
	 * @param node
	 * @param dir
	 */
	private void addNodes(DefaultMutableTreeNode node, File dir) { 
		File[] files = dir.listFiles();
		
		if(files == null || files.length == 0) { 
			return; 
		}
		
		DefaultMutableTreeNode leaf = null;
		String fileName = null; 
		// 
		for(int i = 0; i < files.length; i++) { 
			// ���� �̸� ������ 
			fileName = getClassName(files[i].getName()); 
			// inner class�� ���� 
			if(fileName.indexOf('$') >= 0) { 
				continue; 
			}
			
			// ������ ���� ����� 
			leaf = new DefaultMutableTreeNode(getClassName(files[i].getName()), true); // �����ʿ� 
			// ��忡 �߰� 
			node.add(leaf); 
		}
	}
}

