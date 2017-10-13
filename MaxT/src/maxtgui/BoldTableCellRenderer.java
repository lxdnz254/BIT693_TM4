/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxtgui;

import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * A TableCellRenderer class for making a cell contents bold
 * @author acer
 */
public class BoldTableCellRenderer extends DefaultTableCellRenderer
{
     @Override
     public Component getTableCellRendererComponent(
   JTable table, Object value, boolean isSelected,
   boolean hasFocus, int row, int column) {
    setOpaque(true);
    setText(value.toString());

    // set to desired font
    setFont(new Font("Tahoma", Font.BOLD, 12));

    // set cell's foreground to default cell foreground color
    setForeground(table.getForeground());

    // if cell is selected, set background color to default cell selection background color
    if (isSelected) {
      setBackground(table.getSelectionBackground());
    }

    // otherwise, set cell background color to white
    else {
         setBackground(table.getForeground());
    }

    // position cell text at center
    setHorizontalAlignment(SwingConstants.CENTER);

    return this;
 }
}
