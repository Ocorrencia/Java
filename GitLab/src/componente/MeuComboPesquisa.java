package componente;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import util.ConexaoMysql;

public class MeuComboPesquisa extends JPanel implements MeuComponente {

    public MeuComboBox combo = new MeuComboBox(null, false, "");

    private String dica;
    private String sql;
    List<Integer> pks;

    private boolean obrigatorio;
    List<Integer> pk;
    private boolean atualizando = false;
    Icon iconeAdd = new ImageIcon(getClass().getResource("/imagem/comboAdd2.png"));
    protected MeuJButton jb = new MeuJButton("", (ImageIcon) iconeAdd);

    public MeuComboPesquisa(String sql, boolean obrigatorio, String dica, int tamanho, final Class tela) {
        this.dica = dica;
        this.sql = sql;
        setLayout(new GridBagLayout());
        add(combo);
        add(jb);
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Method metodoChamaTela = tela.getMethod("getTela", new Class[]{});
                    metodoChamaTela.invoke(null, new Object[]{});
                    fechaTela();
                } catch (Exception a) {
                    a.printStackTrace();
                }
            }
        });
        combo.setPreferredSize(new Dimension(tamanho, 27));
        preencher();
    }

    public void fechaTela() {

    }

    public void setSql(String sql) {
        this.sql = sql;
        preencher();

    }

    public void preencher() {
        try {
            atualizando = true;
            pk = new ArrayList();
            combo.removeAllItems();
            if (sql == null) {
                return;
            }
            ResultSet rs = ConexaoMysql.executaQuery(sql);
            while (rs.next()) {
                pk.add(rs.getInt(1));
                combo.addItem(rs.getString(2));
            }
            atualizando = false;
            if (combo.getItemCount() > 0) {
                combo.setSelectedIndex(-1);
                combo.setSelectedIndex(0);
            }
        } catch (SQLException e) {
            atualizando = false;
        }
    }

    public boolean estaAtualizando() {
        return atualizando;
    }

    @Override
    public void limpar() {
        combo.setSelectedIndex(-1);
    }

    @Override
    public boolean eValido() {
        return combo.getSelectedIndex() > -1;
    }

    @Override
    public boolean eObrigatorio() {
        return true;
    }

    @Override
    public boolean eVazio() {
        return combo.getSelectedIndex() == -1;
    }

    @Override
    public void setValor(Object valor) {
        int valorPk = (Integer) valor;
        for (int i = 0; i < pk.size(); i++) {
            if ((int) pk.get(i) == valorPk) {
                combo.setSelectedIndex(i);
                break;
            }
        }
        if (combo.getSelectedIndex() == -1) {
            System.out.println("Não foi possível selecionar um valor em " + getDica());
        }
    }

    @Override
    public Object getValor() {
        return pk.get(combo.getSelectedIndex());
    }

    public Object getValorCombo() {
        return combo.getSelectedItem();
    }

    @Override
    public String getDica() {
        return dica;
    }

    @Override
    public void setEnabled(boolean status
    ) {
        combo.setEnabled(status);
        jb.setEnabled(status);
    }

    protected void atualizar() {
        preencher();
    }

}
