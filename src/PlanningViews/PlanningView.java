/*
 * PlanningView.java
 */

package PlanningViews;

import Planning.DateUtils;
import Planning.GestionCalendar;
import Planning.DisplayUtil;
import Planning.PlanningApp;

import PlanningData.LinkWithDB;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * The application's main frame.
 */
public class PlanningView extends FrameView{

    private ArrayList<JTextField> textFields;

    private GestionCalendar cal;
    private boolean isConnectToDB;
    
    private static TaskFrame taskFrame;
    
    public PlanningView(SingleFrameApplication app) {
        super(app);
        initComponents();
        
        textFields = new ArrayList<JTextField>(42);
        cal = new GestionCalendar();
        
        // Récupération des identifiants de connection //
        connectionDialog.setVisible(true);
        
        // Initialisation du calendrier //
        
        panelCal.setLayout(new GridLayout(6,7));
        changeMonthsActionListener();
        initEmptyCalendar(initMouseListener());
        initDisplay();
        
        
        /* Auto-generated code */
        
        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = PlanningApp.getApplication().getMainFrame();
            aboutBox = new PlanningAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        PlanningApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        monthField = new javax.swing.JTextField();
        todayField = new javax.swing.JTextField();
        panelCal = new javax.swing.JPanel();
        nextButton = new javax.swing.JButton();
        prevButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        connectDBItem = new javax.swing.JMenuItem();
        allTasksMenuItem = new javax.swing.JMenuItem();
        findDateItem = new javax.swing.JMenuItem();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        jDialog1 = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox3 = new javax.swing.JComboBox();
        connectionDialog = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        connectionButton = new javax.swing.JButton();
        passwordField = new javax.swing.JPasswordField();
        cancelConnectionButton = new javax.swing.JButton();

        mainPanel.setName("mainPanel"); // NOI18N

        jTextField1.setEditable(false);
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(Planning.PlanningApp.class).getContext().getResourceMap(PlanningView.class);
        jTextField1.setText(resourceMap.getString("lunTxt.text")); // NOI18N
        jTextField1.setAutoscrolls(false);
        jTextField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextField1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextField1.setFocusable(false);
        jTextField1.setName("lunTxt"); // NOI18N

        jTextField2.setEditable(false);
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText(resourceMap.getString("marTxt.text")); // NOI18N
        jTextField2.setAutoscrolls(false);
        jTextField2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextField2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextField2.setFocusable(false);
        jTextField2.setName("marTxt"); // NOI18N

        jTextField3.setEditable(false);
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setText(resourceMap.getString("merTxt.text")); // NOI18N
        jTextField3.setAutoscrolls(false);
        jTextField3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextField3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextField3.setFocusable(false);
        jTextField3.setName("merTxt"); // NOI18N

        jTextField4.setEditable(false);
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.setText(resourceMap.getString("jeuTxt.text")); // NOI18N
        jTextField4.setAutoscrolls(false);
        jTextField4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextField4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextField4.setFocusable(false);
        jTextField4.setName("jeuTxt"); // NOI18N

        jTextField5.setEditable(false);
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField5.setText(resourceMap.getString("venTxt.text")); // NOI18N
        jTextField5.setAutoscrolls(false);
        jTextField5.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextField5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextField5.setFocusable(false);
        jTextField5.setName("venTxt"); // NOI18N

        jTextField6.setEditable(false);
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField6.setText(resourceMap.getString("samTxt.text")); // NOI18N
        jTextField6.setAutoscrolls(false);
        jTextField6.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextField6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextField6.setFocusable(false);
        jTextField6.setName("samTxt"); // NOI18N

        jTextField7.setEditable(false);
        jTextField7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField7.setText(resourceMap.getString("dimTxt.text")); // NOI18N
        jTextField7.setAutoscrolls(false);
        jTextField7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextField7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextField7.setFocusable(false);
        jTextField7.setName("dimTxt"); // NOI18N

        monthField.setBackground(resourceMap.getColor("monthField.background")); // NOI18N
        monthField.setEditable(false);
        monthField.setForeground(resourceMap.getColor("monthField.foreground")); // NOI18N
        monthField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        monthField.setText(resourceMap.getString("monthField.text")); // NOI18N
        monthField.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        monthField.setName("monthField"); // NOI18N

        todayField.setBackground(resourceMap.getColor("todayField.background")); // NOI18N
        todayField.setEditable(false);
        todayField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        todayField.setText(resourceMap.getString("todayField.text")); // NOI18N
        todayField.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        todayField.setName("todayField"); // NOI18N

        panelCal.setMinimumSize(new java.awt.Dimension(100, 260));
        panelCal.setName("panelCal"); // NOI18N
        panelCal.setPreferredSize(new java.awt.Dimension(100, 260));

        javax.swing.GroupLayout panelCalLayout = new javax.swing.GroupLayout(panelCal);
        panelCal.setLayout(panelCalLayout);
        panelCalLayout.setHorizontalGroup(
            panelCalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 499, Short.MAX_VALUE)
        );
        panelCalLayout.setVerticalGroup(
            panelCalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 271, Short.MAX_VALUE)
        );

        nextButton.setText(resourceMap.getString("nextButton.text")); // NOI18N
        nextButton.setName("nextButton"); // NOI18N

        prevButton.setText(resourceMap.getString("prevButton.text")); // NOI18N
        prevButton.setName("prevButton"); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(todayField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelCal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mainPanelLayout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(prevButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(monthField, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(todayField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(monthField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nextButton)
                    .addComponent(prevButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelCal, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        connectDBItem.setText(resourceMap.getString("connectDBItem.text")); // NOI18N
        connectDBItem.setName("connectDBItem"); // NOI18N
        connectDBItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectDBItemActionPerformed(evt);
            }
        });
        fileMenu.add(connectDBItem);

        allTasksMenuItem.setText(resourceMap.getString("allTasksMenuItem.text")); // NOI18N
        allTasksMenuItem.setName("allTasksMenuItem"); // NOI18N
        allTasksMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allTasksMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(allTasksMenuItem);

        findDateItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        findDateItem.setText(resourceMap.getString("findDateItem.text")); // NOI18N
        findDateItem.setName("findDateItem"); // NOI18N
        fileMenu.add(findDateItem);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(Planning.PlanningApp.class).getContext().getActionMap(PlanningView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 349, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        jDialog1.setTitle(resourceMap.getString("jDialog1.title")); // NOI18N
        jDialog1.setName("jDialog1"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));
        jComboBox1.setName("jComboBox1"); // NOI18N

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.setName("jComboBox2"); // NOI18N

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.setName("jComboBox3"); // NOI18N

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDialog1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        connectionDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        connectionDialog.setTitle(resourceMap.getString("connectionDialog.title")); // NOI18N
        connectionDialog.setAlwaysOnTop(true);
        connectionDialog.setMinimumSize(new java.awt.Dimension(410, 200));
        connectionDialog.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        connectionDialog.setName("connectionDialog"); // NOI18N
        connectionDialog.setResizable(false);

        jPanel1.setName("jPanel1"); // NOI18N

        jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setFont(resourceMap.getFont("jLabel4.font")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        usernameField.setText(resourceMap.getString("usernameField.text")); // NOI18N
        usernameField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        usernameField.setName("usernameField"); // NOI18N

        connectionButton.setText(resourceMap.getString("connectionButton.text")); // NOI18N
        connectionButton.setName("connectionButton"); // NOI18N
        connectionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectionButtonActionPerformed(evt);
            }
        });

        passwordField.setText(resourceMap.getString("passwordField.text")); // NOI18N
        passwordField.setName("passwordField"); // NOI18N

        cancelConnectionButton.setText(resourceMap.getString("cancelConnectionButton.text")); // NOI18N
        cancelConnectionButton.setName("cancelConnectionButton"); // NOI18N
        cancelConnectionButton.setPreferredSize(new java.awt.Dimension(87, 23));
        cancelConnectionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelConnectionButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(passwordField)
                    .addComponent(usernameField, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE))
                .addContainerGap(70, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(199, Short.MAX_VALUE)
                .addComponent(connectionButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelConnectionButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(199, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelConnectionButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(connectionButton))
                .addContainerGap())
        );

        javax.swing.GroupLayout connectionDialogLayout = new javax.swing.GroupLayout(connectionDialog.getContentPane());
        connectionDialog.getContentPane().setLayout(connectionDialogLayout);
        connectionDialogLayout.setHorizontalGroup(
            connectionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        connectionDialogLayout.setVerticalGroup(
            connectionDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        connectionDialog.setLocationRelativeTo(this.getComponent());

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void connectionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectionButtonActionPerformed
        LinkWithDB DB = PlanningApp.getApplication().getLinkWithDB();
        checkConnection(DB);
    }//GEN-LAST:event_connectionButtonActionPerformed

    private void allTasksMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_allTasksMenuItemActionPerformed
//        if(isConnectToDB) {
//            TachesView tv = new TachesView(cal.getTodayFormat());
//            tv.displayAllDatas();
//        }
    }//GEN-LAST:event_allTasksMenuItemActionPerformed

    private void cancelConnectionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelConnectionButtonActionPerformed
        isConnectToDB = false;
        resetConnectionDialog();
        JOptionPane.showMessageDialog(this.getComponent(),"Vous n'avez pas pu vous connecter à la base de données.", "Connection failed", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_cancelConnectionButtonActionPerformed

    private void connectDBItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectDBItemActionPerformed
        if(!isConnectToDB) {
            connectionDialog.setVisible(true);
        }
    }//GEN-LAST:event_connectDBItemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem allTasksMenuItem;
    private javax.swing.JButton cancelConnectionButton;
    private javax.swing.JMenuItem connectDBItem;
    private javax.swing.JButton connectionButton;
    private javax.swing.JDialog connectionDialog;
    private javax.swing.JMenuItem findDateItem;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JTextField monthField;
    private javax.swing.JButton nextButton;
    private javax.swing.JPanel panelCal;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JButton prevButton;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JTextField todayField;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables


    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
    
    
    /**
     * Initialize the ArrayList of JTextField
     * @param ml 
     */
    private void initEmptyCalendar(MouseListener ml) {
        for(int i=0 ; i<42 ; i++) {
            JTextField field = new JTextField();
            panelCal.add(field);
            field.setEditable(false);
            field.setText("");
            field.setHorizontalAlignment(SwingConstants.CENTER);
            field.addMouseListener(ml);
            textFields.add(field);
        }
    }
    
    /** Définit la date de chaque case du calendrier */
    public void initDisplay() {
        ArrayList<Date> dayList = cal.getMatrice();
        Font f = new Font(Font.SANS_SERIF,Font.BOLD,15);
        LinkWithDB DB = PlanningApp.getApplication().getLinkWithDB();
        
        // Affiche la date du jour //
        todayField.setFont(f);
        todayField.setText(cal.getTodayStr());
        
        // Affiche le mois en haut du calendrier //
        monthField.setFont(f);
        monthField.setText(cal.getCurrentMonthStr()+" "+cal.getCurrentYear());
        
        // Remplit le calendrier //
        int i=0;
        for(JTextField field : textFields) {
            field.setFont(f);
            if(DateUtils.getMonth(dayList.get(i))!=cal.getCurrentMonth())
                field.setForeground(Color.GRAY);
            else if(isConnectToDB && DB.haveSmthingToDo(DateUtils.dateToString(dayList.get(i))))
                field.setForeground(Color.red);
            else field.setForeground(Color.BLACK);
            // TODO : ajouter background sur Today //
//            if(dayList.get(i).equals(cal.getToday()))
//                field.setBackground(Color.CYAN);
            
            field.setText(DateUtils.getDate(dayList.get(i))+"");
            i++;
        }
        
    }
    
    /**
     * Initialize the action listener to change the month of the calendar
     */
    private void changeMonthsActionListener() {
        ActionListener actionListener = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                // Action de changement de mois //
                if(e.getSource() instanceof javax.swing.JButton) {
                    if(e.getSource().equals(prevButton))
                        cal.goToLastMonth();
                    else if(e.getSource().equals(nextButton))
                        cal.goToNextMonth();
                }
//                else if(e.getSource() instanceof javax.swing.JMenuItem)
//                    if(e.getSource().equals(findDateItem))
//                    {
//                        System.out.println("Lu'");
//                    }
                initDisplay();
            }
        };
        
                
        prevButton.addActionListener(actionListener);
        nextButton.addActionListener(actionListener);
//        findDateItem.addActionListener(actionListener);
    }
    
    /**
     * Initialize the actions when the user click or enter/exit a case
     * @return MouseListener
     */
    private MouseListener initMouseListener() {
        MouseListener ml = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                
                if(isConnectToDB) {
                    int indexFieldPressed = textFields.indexOf((JTextField)e.getSource());
                    
                    ArrayList<Date> dayList = cal.getMatrice();
                    TaskPanel tp = PlanningApp.getApplication().getTaskPanel();
                    if(tp!=null)
                        DisplayUtil.closeFrame(tp);
                    
                    tp = new TaskPanel(dayList.get(indexFieldPressed));
                    tp.displayDatasOfDay();
                    taskFrame = new TaskFrame("Taches", tp);
  //                tachesView.getTachesTxt().setText(dayList[i][j].getListeStr());
                    PlanningApp.getApplication().setTaskPanel(tp);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                JTextField field = (JTextField)e.getSource();
                field.setBackground(new Color(21, 232, 204));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                JTextField field = (JTextField)e.getSource();
                // TODO : empêcher changement background
                field.setBackground(new Color(240, 240, 240));
            }   
            
        };
        
        return ml;
    }
    
    /**
     * Check that fields are no empty and valide the form if it is ok
     * @param DB
     */
    private void checkConnection(LinkWithDB DB) {
        
        if(usernameField.getText().replaceAll(" ", "").equals(""))
            JOptionPane.showMessageDialog(this.getComponent(),"Merci de renseigner le nom d'utilisateur.", "Error Field", JOptionPane.INFORMATION_MESSAGE);
        else {
            isConnectToDB = DB.connection(usernameField.getText(),passwordField.getPassword());
            resetConnectionDialog();
            if(!isConnectToDB)
                JOptionPane.showMessageDialog(this.getComponent(),"Vous n'avez pas pu vous connecter à la base de données.", "Connection failed", JOptionPane.INFORMATION_MESSAGE);
            else {
                JOptionPane.showMessageDialog(this.getComponent(),"Vous êtes connecté à la base de données.", "Connection success", JOptionPane.INFORMATION_MESSAGE);
                connectDBItem.setEnabled(false);
                initDisplay();
            }
        }
    }    

    /*
     * Erase datas into the connectionDialog and set the visibility to false
     */
    private void resetConnectionDialog() {
        connectionDialog.setVisible(false);
        usernameField.setText("");
        passwordField.setText("");
    }
    
    // Option panel //
    
    private int[] dateList, monthList, yearList;
    
//    private void initFindDateView() {
//        dateList = new int[cal.getNbDaysOfMonth(cal.getCurrentMonth());
//    }
    private void optionPanel() {
        
    }

    public static TaskFrame getTaskFrame() {
        return taskFrame;
    }
    
    
    
}
