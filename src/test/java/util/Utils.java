package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {

    public void lerArquivo(String path, Properties prop) throws FileNotFoundException {
        FileInputStream fs =new FileInputStream(path);
        try {
            prop.load(fs);
            fs.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
