package com.qp.juiceapp.juiceapp.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Juice {
	private String juiceName;
	private int calori;
}
