/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import static com.alee.managers.notification.NotificationIcon.file;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Limpar {

    EnviarEmail enviarEmail = new EnviarEmail();

    public void executeSqlScript() {
        try {
            // Delimiter
            String delimiter = ";";
            // Create scanner
            Scanner scanner;
            InputStream reader = (getClass().getResourceAsStream("/arquivos/REFRESH.sql"));
            scanner = new Scanner(reader).useDelimiter(delimiter);
            // Loop through the SQL file statements 
            Statement currentStatement = null;
            while (scanner.hasNext()) {
                // Get statement 
                String rawStatement = scanner.next() + delimiter;
                try {
                    // Execute statement
                    currentStatement = ConexaoMysql.getConexaoMySQL().createStatement();
                    currentStatement.executeUpdate(rawStatement);
                } catch (SQLException e) {
                    if (e.getMessage().contains("Query was empty")) {
                        Notificacao.infoBox("DADOS REINICIALIZADOS", true);
                    }else{
                      enviarEmail.enviaEmail(e.getMessage(), "Erro ao realizar refresh");
                    }
                } finally {
                    // Release resources
                    if (currentStatement != null) {
                        try {
                            currentStatement.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    currentStatement = null;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
