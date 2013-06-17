/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanningViews;

import Planning.DisplayUtil;
import java.awt.HeadlessException;
import javax.swing.JFrame;

/**
 *
 * @author Audrey
 */
public class TaskFrame extends JFrame {

    private TaskPanel panel;
    
    public TaskFrame(String title, TaskPanel panel) throws HeadlessException {
        super(title);
        setSize(435, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        DisplayUtil.centerFrame(this);
        add(panel);
        setVisible(true);
        
        this.panel = panel;
    }

    public TaskPanel getPanel() {
        return panel;
    }
    
    
    
}
