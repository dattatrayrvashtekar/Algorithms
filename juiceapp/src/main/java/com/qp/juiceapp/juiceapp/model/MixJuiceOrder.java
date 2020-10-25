package com.qp.juiceapp.juiceapp.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MixJuiceOrder {
	private List<Juice> cupboardJuiceList;
	private int caloriIntake;
}
