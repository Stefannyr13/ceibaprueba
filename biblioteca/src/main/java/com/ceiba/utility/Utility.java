package com.ceiba.utility;

import com.ceiba.entity.TipoUsuario;

public  class Utility {
     public static final String MENSAJE_FALLIDO_POR_USUARIO_INEXISTENTE = "Tipo de usuario no permitido en la biblioteca";
     public static final String MENSAJE_FALLIDO_POR_USUARIO_INVITADO = "El usuario con identificación %s ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo";
     public static final int USUARIO_AFILIADO = 1;
     public static final int USUARIO_EMPLEADO = 2;
     public static final int USUARIO_INVITADO = 3;
     public static final int CANTIDAD_DIAS_POR_AFILIADO = 10;
     public static final int CANTIDAD_DIAS_POR_EMPLEADO = 8;
     public static final int CANTIDAD_DIAS_POR_INVITADO = 7;


}
