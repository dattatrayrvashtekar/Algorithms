package com.qp.juiceapp.juiceapp.filehandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.qp.juiceapp.juiceapp.model.Juice;
import com.qp.juiceapp.juiceapp.model.MixJuiceOrder;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class InputFileReader {
	
	public List<MixJuiceOrder> readFile(String filePath) throws IOException {
		Path path = Paths.get(filePath);
		List<String> lines = Files.readAllLines(path);
		int numberOfFrnds = Integer.valueOf(lines.get(0));
		validateFrndCount(numberOfFrnds);
		List<MixJuiceOrder> mixJuiceOrdersList = new ArrayList<>();
		for (int line = 1; line < lines.size(); line += 3) {
			MixJuiceOrder mixJuiceOrder = getMixJuiceOrderDetails(lines.get(line), lines.get(line + 1),
					lines.get(line + 2));
			mixJuiceOrdersList.add(mixJuiceOrder);
		}
		return mixJuiceOrdersList;
	}

	private MixJuiceOrder getMixJuiceOrderDetails(String juiceCaloriesAndUniqueCount, String cupboardJuiceNameList,
			String intakeCalori) {
		String[] caloriesAndUniqueCount = juiceCaloriesAndUniqueCount.split(" ");
		int uniqueCount = Integer.parseInt(caloriesAndUniqueCount[0]);
		String[] cupBoardJuiceNameArray = cupboardJuiceNameList.split("");
		Arrays.sort(cupBoardJuiceNameArray);
		List<Juice> cupboardJuiceList = new ArrayList<>();
		String prev = null;
		for (int i = 0, j = 1; i < cupBoardJuiceNameArray.length; i++) {
			if (prev != null && !prev.equals(cupBoardJuiceNameArray[i])) {
				j++;
			}
			Juice juice = Juice.builder().juiceName(cupBoardJuiceNameArray[i])
					.calori(Integer.parseInt(caloriesAndUniqueCount[j])).build();
			cupboardJuiceList.add(juice);
			prev = cupBoardJuiceNameArray[i];
		}
		return MixJuiceOrder.builder()
				.cupboardJuiceList(cupboardJuiceList)
				.caloriIntake(Integer.parseInt(intakeCalori))
				.build();
	}

	private void validateFrndCount(int numberOfFrnds) {
		if (numberOfFrnds > 200) {
			throw new RuntimeException("Friend count can not be more than 200");
		}
	}
}
