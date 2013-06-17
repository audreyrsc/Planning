/*
 * PlanningApp.java
 */

package Planning;

import PlanningData.LinkWithDB;
import PlanningViews.*;
import java.sql.SQLException;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class PlanningApp extends SingleFrameApplication {

    private PlanningView view;
    private GestionCalendar cal;
    private LinkWithDB DB;
    private boolean isTaskViewOpen;
    private TaskPanel taskPanel;
    
    public PlanningApp() {
        cal = new GestionCalendar();
        DB = new LinkWithDB();
        isTaskViewOpen = false;
    } 
    
    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
//        tachesView = new TachesView();
        view = new PlanningView(this);  
        show(view);
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of PlanningApp
     */
    public static PlanningApp getApplication() {
        return Application.getInstance(PlanningApp.class);
    }

    public GestionCalendar getCal() {
        return cal;
    }

    public PlanningView getView() {
        return view;
    }

    public LinkWithDB getLinkWithDB() {
        return DB;
    }

    public void setTaskPanel(TaskPanel taskPanel) {
        this.taskPanel = taskPanel;
    }

    public TaskPanel getTaskPanel() {
        return taskPanel;
    }

    
    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(PlanningApp.class, args);   
    }
}
