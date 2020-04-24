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
 * 메뉴 표현 
 * @author kh2un
 *
 */
public class MenuTree {
	//private static Logger logger; 
	private JTree tree; 
	private TreeSelectionListener listener; 
	
	/**
	 * 생성자 
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
	 * 초기화 
	 */
	private void initComponents() { 
		DefaultMutableTreeNode root = null; 
		DefaultMutableTreeNode node = null; 
		
		root = new DefaultMutableTreeNode("chart"); 
		// 디렉토리 목록 찾기 
		File file = new File("bin/chart"); // 경로는 바꿔야.... 
		File[] subDir = file.listFiles(); 
		
		if(subDir != null) { 
			for(int i = 0; i < subDir.length; i++) { 
				// 디렉토리만 추가 
				if(subDir[i].isDirectory()) { 
					// 폴더를 노드로 만들고 
					node = new DefaultMutableTreeNode(subDir[i].getName(), true);
					// 루트에 폴더 추가하고 
					root.add(node); 
					// 폴더 밑에 있는 애들은 노드에 추가하고 
					addNodes(node, subDir[i]); 
				} 
			}
		}
		
		// 루트 노드를 트리에 추가 
		TreeModel model = new DefaultTreeModel(root); 
		this.tree = new JTree(model); 
		this.tree.setEditable(false); 
		this.tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION); 
		this.tree.addTreeSelectionListener(this.listener); 
	}
	/**
	 * .class 떼기 
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
			// 파일 이름 꺼내고 
			fileName = getClassName(files[i].getName()); 
			// inner class는 무시 
			if(fileName.indexOf('$') >= 0) { 
				continue; 
			}
			
			// 파일을 노드로 만들고 
			leaf = new DefaultMutableTreeNode(getClassName(files[i].getName()), true); // 변경필요 
			// 노드에 추가 
			node.add(leaf); 
		}
	}
}

