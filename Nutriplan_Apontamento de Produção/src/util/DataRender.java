package util;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class DataRender extends DefaultTableCellRenderer {

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public Component getTableCellRendererComponent(JTable tabela, Object valor, boolean selecionado, boolean foco, int linha, int coluna) {
        super.getTableCellRendererComponent(tabela, valor, selecionado, foco, linha, coluna);
        Object val = (Date) tabela.getValueAt(linha, coluna);
        Date data = (Date) (val);
        setText(data != null ? sdf.format(data) : "");
        return this;
    }
}
