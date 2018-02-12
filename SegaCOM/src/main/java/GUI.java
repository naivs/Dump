
import keyboard.KeyConfigurator;
import keyboard.KeyMapManager;
import java.awt.AWTException;
import java.awt.Robot;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import jssc.SerialPortException;
import jssc.SerialPortList;
import serial.Connection;
import serial.ConnectionFake;
import serial.ConnectionImpl;
import serial.PortFinder;

/**
 *
 * @author ivan
 */
public class GUI extends javax.swing.JFrame implements Observer {

    private PortFinder portFinder;
    private Connection connection;

    private final KeyMapManager keyMapManager;
    private final int[][] currentKeyMap;
    private static final int[] CODE = {
        1,
        2,
        4,
        8,
        16,
        32,
        64,
        128,
        256,
        512,
        1024,
        2048
    };
    private static Robot robot;

    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();

        System.out.println("Available ports: " + Arrays.toString(SerialPortList.getPortNames("/dev/")));
        String portName = "/dev/ttyUSB0";
        //connection = new ConnectionImpl(portName);
        connection = new ConnectionFake(portName);

//        portFinder = new PortFinder();
//        portFinder.addObserver(this);
//        Thread scannerThread = new Thread(portFinder);
//        scannerThread.start();

        try {
            robot = new Robot();
        } catch (AWTException e) {
            System.err.println("Can't create robot.\n" + e.getMessage());
        }
        keyMapManager = new KeyMapManager();
        currentKeyMap = keyMapManager.getLayout("Default");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new Display();
        buttonSetKeys = new javax.swing.JButton();
        buttonConnect = new javax.swing.JButton();
        buttonStart = new javax.swing.JButton();
        tButtonPad1 = new javax.swing.JToggleButton();
        tButtonPad2 = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sega controls");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setFocusable(false);
        jPanel1.setMaximumSize(new java.awt.Dimension(1920, 1080));

        buttonSetKeys.setText("Set keys");
        buttonSetKeys.setFocusable(false);
        buttonSetKeys.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSetKeysActionPerformed(evt);
            }
        });

        buttonConnect.setText("Connect");
        buttonConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConnectActionPerformed(evt);
            }
        });

        buttonStart.setText("Start");
        buttonStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonStartActionPerformed(evt);
            }
        });

        tButtonPad1.setText("Gamepad 1");

        tButtonPad2.setText("Gamepad 2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonConnect, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonSetKeys, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
                .addGap(50, 50, 50)
                .addComponent(tButtonPad1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 392, Short.MAX_VALUE)
                .addComponent(tButtonPad2)
                .addGap(127, 127, 127))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonSetKeys)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonConnect)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonStart)
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tButtonPad1)
                    .addComponent(tButtonPad2))
                .addContainerGap(232, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSetKeysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSetKeysActionPerformed
        KeyConfigurator keyConfigurator = new KeyConfigurator(this, currentKeyMap);
        keyConfigurator.setVisible(true);
    }//GEN-LAST:event_buttonSetKeysActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        System.out.println("Exit...");
        if (connection != null) {
            connection.close();
        }
        if (portFinder != null) {
            portFinder.stop();
        }
    }//GEN-LAST:event_formWindowClosing

    private void buttonConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConnectActionPerformed
        try {
            if (!connection.isOpen()) {
                connection.addObserver(this);
                connection.open();
            }
            //connection.send(Connection.RQ_STAT);
        } catch (SerialPortException ex) {
            System.err.println("Can't open port.\n" + ex.getMessage());
        }
    }//GEN-LAST:event_buttonConnectActionPerformed

    private void buttonStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonStartActionPerformed
        connection.send(ConnectionImpl.RQ_START);
    }//GEN-LAST:event_buttonStartActionPerformed

    @Override
    public void update(Observable o, Object arg) {
        String className = o.getClass().getName();
        if (className.equals(ConnectionFake.class.getName())) {

            if (arg.equals(Connection.RPL_STAT)) {
//            connection = (Connection) arg;
//            connection.addObserver(this);
                System.out.print("Gamepad connected! Response: " + arg);
            } else if (arg.equals(Connection.RPL_START)) {
                System.out.print("Handle started! Response: " + arg);
            } else {
                //System.out.print("Button data! Response: " + arg);

                String[] raw;
                int code_1, code_2;
                int i;

                try {
                    if (arg.toString().contains(" ")) {
                        raw = arg.toString().split(" ");

                        if (tButtonPad1.isSelected()) {
                            code_1 = raw[0].isEmpty() ? 0 : Integer.parseInt(raw[0]);
                        } else {
                            code_1 = 0;
                        }
                        
                        if (tButtonPad2.isSelected()) {
                            code_2 = raw[1].isEmpty() ? 0 : Integer.parseInt(raw[1].trim());
                        } else {
                            code_2 = 0;
                        }

                        for (i = 0; i < CODE.length; i++) {
                            if ((code_1 & CODE[i]) == CODE[i]) {
                                robot.keyPress(currentKeyMap[i][0]);
                                System.out.println("Joy 1: press-" + currentKeyMap[i][0]);
                                ((Display) jPanel1).press(0, i);
                            } else if (((Display) jPanel1).isPressed(0, i)) {
                                robot.keyRelease(currentKeyMap[i][0]);
                                System.out.println("Joy 1: release-" + currentKeyMap[i][0]);
                                ((Display) jPanel1).release(0, i);
                            }

                            if ((code_2 & CODE[i]) == CODE[i]) {
                                robot.keyPress(currentKeyMap[i][1]);
                                System.out.println("Joy 2: press-" + currentKeyMap[i][1]);
                                ((Display) jPanel1).press(1, i);
                            } else if (((Display) jPanel1).isPressed(1, i)) {
                                robot.keyRelease(currentKeyMap[i][1]);
                                System.out.println("Joy 2: release-" + currentKeyMap[i][1]);
                                ((Display) jPanel1).release(1, i);
                            }
                        }
                        jPanel1.repaint();
                    }
                } catch (NumberFormatException ex) {
                    System.out.println(ex);
                }
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

//        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new GUI().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonConnect;
    private javax.swing.JButton buttonSetKeys;
    private javax.swing.JButton buttonStart;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToggleButton tButtonPad1;
    private javax.swing.JToggleButton tButtonPad2;
    // End of variables declaration//GEN-END:variables
}
