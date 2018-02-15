package keyboard;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author ivan
 */
public class KeyConfigurator extends javax.swing.JDialog {

    private static KeyConfiguratorTableModel tableModel;
    private static DefaultComboBoxModel comboBoxModel;

    private Map<String, KeyMap> layouts;
    private String currentMap;

    /**
     * Creates new form KeyConfigurator
     *
     * @param parent
     */
    public KeyConfigurator(java.awt.Frame parent) {
        super(parent, true);

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("map.dat"))) {
            layouts = (HashMap<String, KeyMap>) ois.readObject();
            currentMap = ois.readObject().toString();
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("map.dat read error! " + ex.getMessage());
        }

        if (layouts == null || layouts.isEmpty()) {
            layouts = new HashMap<>();
            for (int i = 0; i < 4; i++) {
                KeyMap keyMap = new KeyMap(i);
                layouts.put(keyMap.getName(), keyMap);
            }
            currentMap = "PC";
            save();
        }

        tableModel = new KeyConfiguratorTableModel(layouts.get(currentMap));
        comboBoxModel = new DefaultComboBoxModel();
        layouts.keySet().stream().sorted().forEach((s) -> comboBoxModel.addElement(s));

//        tableModel.addTableModelListener((e) -> {
//            System.out.println(String.format("Data in table model was changed.\n "
//                    + "Column: %s Row: %s", e.getColumn(), e.getFirstRow()));
//        });

        initComponents();
    }

    public KeyMap getMap() {
        return layouts.get(currentMap);
    }

    private void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("map.dat"))) {
            oos.writeObject(layouts);
            oos.writeObject(currentMap);
        } catch (IOException ex) {
            System.err.println("map.dat write error! " + ex.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        messageLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Key layouts");
        setResizable(false);

        jTable1.setModel(tableModel);
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        jTable1.setRowSelectionAllowed(false);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.setSurrendersFocusOnKeystroke(true);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getTableHeader().setFocusable(false);
        jTable1.getTableHeader().setReorderingAllowed(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setToolTipText("");
        jPanel1.setPreferredSize(new java.awt.Dimension(287, 75));

        jComboBox1.setModel(comboBoxModel);
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        messageLabel.setText("Press key...");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(messageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(messageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jComboBox1.setSelectedItem(currentMap);
        messageLabel.setVisible(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
//        KeyConfiguratorTableModel model = (KeyConfiguratorTableModel) jTable1.getModel();
        int xSelected = jTable1.getSelectedColumn();
        int ySelected = jTable1.getSelectedRow();

        Arrays.asList(jTable1.getKeyListeners()).forEach((l) -> jTable1.removeKeyListener(l));
        messageLabel.setVisible(false);

        if (xSelected > 0) {
            messageLabel.setVisible(true);
            jTable1.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
//                    System.out.println("Value: " + model.getValueAt(ySelected, xSelected));
//                    System.out.println("Pressed: " + KeyEvent.getKeyText(e.getKeyCode()));
                    layouts.get(currentMap).setKey(ySelected, xSelected - 1, e.getKeyCode());
                    tableModel.setValues(layouts.get(currentMap));
                    //jTable1.setModel(new KeyConfiguratorTableModel(layouts.get(currentMap)));
                    messageLabel.setVisible(false);
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    jTable1.removeKeyListener(this);
                }
            });
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        if (!currentMap.equals(jComboBox1.getSelectedItem().toString())) {
            currentMap = jComboBox1.getSelectedItem().toString();
//            jTable1.setModel(new KeyConfiguratorTableModel(layouts.get(currentMap)));
            tableModel.setValues(layouts.get(currentMap));
            save();
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel messageLabel;
    // End of variables declaration//GEN-END:variables
}
