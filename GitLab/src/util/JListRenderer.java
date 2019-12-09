/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;
import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

class MyCellRenderer extends JLabel implements ListCellRenderer {

  public MyCellRenderer() {
    setOpaque(true);
  }

  public Component getListCellRendererComponent(JList list, Object value, int index,
      boolean isSelected, boolean cellHasFocus) {
    setText(value.toString());

    if (isSelected) {
      setBackground(list.getSelectionBackground());
      setForeground(list.getSelectionForeground());
    } else {
      setBackground(list.getBackground());
      setForeground(list.getForeground());
    }
    return this;
  }
}

public class JListRenderer {

  public static void main(String[] a) {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    
    JList list = new JList(new String[]{"A","B","C"});  
    list.setCellRenderer(new MyCellRenderer());  
    frame.add(new JScrollPane(list));   


    frame.setSize(300, 200);
    frame.setVisible(true);
  }

}