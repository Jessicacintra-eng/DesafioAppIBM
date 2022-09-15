package com.ibm.desafioappandroid.helper;

import android.util.Base64;

public class Base64Custom {
    public static String codificarbase64(String texto){
       return Base64.encodeToString(texto.getBytes(),Base64.DEFAULT).replaceAll("(\\n|\\r)","");
    }
    public static String decodificarbase64(String textocodificado){
        return new String(Base64.decode(textocodificado,Base64.DEFAULT));
    }

}
