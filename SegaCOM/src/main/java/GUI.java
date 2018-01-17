
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
import serial.PortFinder;

/**
 *
 * @author ivan
 */
public class GUI extends javax.swing.JFrame implements Observer {

    private PortFinder portFinder;
    private Connection connection;

    private final KeyMapManager keyMapManager;
    private int[][] currentKeyMap;
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
        connection = new Connection(portName);
        connection.addObserver(this);

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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sega controls");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setFocusable(false);

        jButton1.setText("Set keys");
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Connect");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Start");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Stop");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Disconnect");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(763, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addContainerGap(215, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        KeyConfigurator keyConfigurator = new KeyConfigurator(this, currentKeyMap);
        keyConfigurator.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        System.out.println("Exit...");
        if (portFinder != null) {
            portFinder.stop();
        }
    }//GEN-LAST:event_formWindowClosing

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            connection.open();
        } catch (SerialPortException ex) {
            System.err.println("Can't open port.\n" + ex.getMessage());
        }
        
        connection.send("222");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        connection.send("444");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        connection.send("666");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        connection.close();
    }//GEN-LAST:event_jButton5ActionPerformed

    @Override
    public void update(Observable o, Object arg) {
        String className = o.getClass().getName();
        if (className.equals(Connection.class.getName())) {

            if (arg.equals(Connection.STAT)) {
//            connection = (Connection) arg;
//            connection.addObserver(this);
                System.out.print("Gamepad connected! Response: " + arg);
            } else if (arg.equals(Connection.STARTED)) {
                System.out.print("Handle started! Response: " + arg);
            } else if (arg.equals(Connection.STOPPED)) {
                System.out.print("Handle stopped! Response: " + arg);
            } else {
                System.out.print("Button data! Response: " + arg);

                String[] raw;
                int code_1, code_2;
                int i;

                try {
                    if (arg.toString().contains(" ")) {
                        raw = arg.toString().split(" ");

                        code_1 = raw[0].isEmpty() ? 0 : Integer.parseInt(raw[0]);
                        code_2 = raw[1].isEmpty() ? 0 : Integer.parseInt(raw[1].trim());

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

//        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new GUI().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
