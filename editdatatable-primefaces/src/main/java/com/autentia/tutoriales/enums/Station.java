package com.autentia.tutoriales.enums;


public enum Station {
	ALMACENADO, REPARADO;
	
	public String getLabel(){
		return this.getClass().getSimpleName() + "_" + this.name();
	}


}
