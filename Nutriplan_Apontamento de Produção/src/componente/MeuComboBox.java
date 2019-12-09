package componente;

import java.awt.Color;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import util.ConexaoMysql;
import util.ConexaoOracle;

public class MeuComboBox extends JComboBox<Object> implements MeuComponente {

    private String sql;
    private boolean obrigatorio;
    private String dica;
    List<Integer> pk;
    private boolean atualizando = false;

    public MeuComboBox(String sql, boolean obrigatorio, String dica) {
        this.sql = sql;

        this.obrigatorio = obrigatorio;
        this.dica = dica;
        preencher();

        if (eObrigatorio()) {
            setBackground(Color.WHITE);
        }
        setSelectedIndex(-1);
    }

    public void setSql(String sql) {
        this.sql = sql;
        preencher();

    }

    public boolean estaAtualizando() {
        return atualizando;
    }

    public void preencher() {
        try {
            atualizando = true;
            pk = new ArrayList();
            removeAllItems();
            if (sql == null) {
                return;
            }
            ResultSet rs = ConexaoOracle.executaQuery(sql);
            while (rs.next()) {
                pk.add(rs.getInt(1));
                addItem(rs.getString(2));
            }
            atualizando = false;
            if (getItemCount() > 0) {
                setSelectedIndex(-1);
                setSelectedIndex(0);
            }
        } catch (Exception e) {
            atualizando = false;
        }
    }

    @Override
    public void limpar() {
        setSelectedIndex(-1);
    }

    @Override
    public boolean eValido() {
        return true;
    }

    @Override
    public boolean eObrigatorio() {
        return obrigatorio;
    }

    @Override
    public boolean eVazio() {
        return getSelectedIndex() == -1;
    }

    @Override
    public String getDica() {
        return dica;
    }

    @Override
    public void setValor(Object valor) {
        int valorPk = (Integer) valor;
        for (int i = 0; i < pk.size(); i++) {
            if ((int) pk.get(i) == valorPk) {
                setSelectedIndex(i);
                break;
            }
        }
        if (getSelectedIndex() == -1) {
            System.out.println("Não foi possível selecionar um valor em " + getDica());
        }
    }

    @Override
    public Object getValor() {
        return pk.get(getSelectedIndex());
    }
}
