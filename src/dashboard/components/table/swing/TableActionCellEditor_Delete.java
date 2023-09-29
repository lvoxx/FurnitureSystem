package dashboard.components.table.swing;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author RAVEN
 */
public class TableActionCellEditor_Delete extends DefaultCellEditor {

    private TableActionEvent event;

    public TableActionCellEditor_Delete(TableActionEvent event) {
        super(new JCheckBox());
        this.event = event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int row, int column) {
        PanelAction_Delete action = new PanelAction_Delete();
        action.initEvent(event, row);
        action.setBackground(jtable.getSelectionBackground());
        return action;
    }
}
