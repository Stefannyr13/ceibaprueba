package com.ceiba.entity;

public enum TipoUsuario {

	N(0),USUARIO_AFILIADO(1),	USUARIO_EMPLEADO(2),	USUARIO_INVITADO(3);

	private int tipo;

	TipoUsuario(int t) {
		tipo=t;
	}

	public int getTipo() {
		return tipo;
	}


}
