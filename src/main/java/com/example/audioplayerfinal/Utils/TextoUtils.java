package com.example.audioplayerfinal.Utils;

import java.text.Normalizer;
import java.util.Locale;

public class TextoUtils {

    private TextoUtils() {
    }

    public static String normalizarTexto(String texto) {
        if (texto == null) return "";
        // Elimina espacios iniciales y finales, y pasa a minúsculas
        texto = texto.trim().toLowerCase(Locale.ROOT);
        // Elimina acentos y caracteres especiales
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        // Reemplaza múltiples espacios por uno solo
        texto = texto.replaceAll("\\s+", " ");
        return texto;
    }
}
