package es.unizar.eina.vv6f.practica3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    //Test numero 1 para comprobar que se lanza la excepción NullPointerException al leer de un fichero cuya referencia sea null
    public void comprobar_Fichero_Nulo(){
        ContadorDeLetras contador = new ContadorDeLetras(null);
        assertThrows(NullPointerException.class, contador::frecuencias);
    }
}