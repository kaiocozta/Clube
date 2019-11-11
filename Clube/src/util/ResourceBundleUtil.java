package util;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleUtil {

    private static ResourceBundle bundle;

    static {
        bundle = ResourceBundle.getBundle("clube.i18n.Messages_pt_BR", new Locale("pt_br"));
    }

    public static String getKey(String chave) {
        return bundle.getString(chave);
    }
}
