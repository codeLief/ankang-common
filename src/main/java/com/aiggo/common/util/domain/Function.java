package com.aiggo.common.util.domain;

import java.io.Serializable;

public interface Function<E, T> extends Serializable{
	
	public T apply(E e);
}
