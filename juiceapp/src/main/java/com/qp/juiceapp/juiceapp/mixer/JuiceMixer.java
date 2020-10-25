package com.qp.juiceapp.juiceapp.mixer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.qp.juiceapp.juiceapp.model.Juice;

@Component
public class JuiceMixer {

	public List<Juice> prepareJuice(List<Juice> cupboardJuiceList, int caloriIntake) {
		List<Juice> mixJuice = new ArrayList<>();
		for (int i = 0, startIndex = i + 1; i < cupboardJuiceList.size();) {
			int j = startIndex;
			mixJuice.add(cupboardJuiceList.get(i));
			int sum = cupboardJuiceList.get(i).getCalori();
			for (; j < cupboardJuiceList.size(); j++) {
				sum = sum + cupboardJuiceList.get(j).getCalori();
				mixJuice.add(cupboardJuiceList.get(j));
				if (sum >= caloriIntake) {
					break;
				}
			}
			if (sum == caloriIntake) {
				break;
			}else {
				mixJuice = new ArrayList<>();
			}
			if (startIndex < cupboardJuiceList.size()) {
				startIndex++;
			} else {
				i++;
				startIndex = i + 1;
			}
		}
		return mixJuice;
	}
}
