package util;

import org.testng.annotations.Test;

import javax.rmi.CORBA.Util;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Conexao {
    Properties prop;
    Utils util;
    public Connection conectarBancoTeste() throws IOException {


        prop = new Properties();
        util= new Utils();
        util.lerArquivo("/home/daiane/Desktop/test2.properties", prop);

        String usuario = prop.getProperty("usuario");
        String senha = prop.getProperty("senha");
        String url = prop.getProperty("url");
        System.out.println(url);

        Connection conexao = null;

        try {
            Class.forName("org.postgresql.Driver");
            conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão obtida com sucesso");
            System.out.println(conexao);

        } catch (ClassNotFoundException e1) {

            e1.printStackTrace();
        } catch (SQLException e1) {

            e1.printStackTrace();
        }
        return conexao;

    }
}
