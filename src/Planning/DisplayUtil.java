/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Planning;

import java.awt.Dimension;
import java.awt.Window;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Audrey
 */
public class DisplayUtil {

    public static void centerFrame(JFrame frame) {
        Dimension tailleEcran = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int width = ((int)tailleEcran.getWidth() - frame.getWidth())/2;
        int height = ((int)tailleEcran.getHeight() - frame.getHeight())/2;
        frame.setLocation(width,height);
    }
    
//    public static JScrollPane getScrollPane(JPanel panel) {
//        JScrollPane scrollPane = new JScrollPane(panel);
//        scrollPane.setVerticalScrollBar(new JScrollBar());
//        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        scrollPane.setHorizontalScrollBar(null);
//        scrollPane.setBackground(Color.white);
//        
//        return scrollPane;
//    }
    
    /**
     * Return a JFrame, centered on the window and visible
     * @param title
     * @param width 
     * @param height
     * @return JFrame
     */
    public static JFrame getFrame(String title, int width, int height) {
        JFrame frame = new JFrame(title);
        frame.setSize(width,height);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        centerFrame(frame);
        frame.setVisible(true);
        
        return frame;
    }
    
    public static JFrame getFrame(String title) {
        return getFrame(title, 800, 400);
    }

    
    public static void closeFrame(JPanel panel) {
        Window w = SwingUtilities.windowForComponent(panel);
        w.dispose();
    }
}
