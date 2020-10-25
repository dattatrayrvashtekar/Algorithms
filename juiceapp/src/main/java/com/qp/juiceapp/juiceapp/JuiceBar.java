package com.qp.juiceapp.juiceapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qp.juiceapp.juiceapp.filehandler.InputFileReader;
import com.qp.juiceapp.juiceapp.filehandler.OutputFileWriter;
import com.qp.juiceapp.juiceapp.mixer.JuiceMixer;
import com.qp.juiceapp.juiceapp.model.Juice;
import com.qp.juiceapp.juiceapp.model.MixJuiceOrder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JuiceBar {

	@Autowired
	private JuiceMixer juiceMixer;
	@Autowired
	private InputFileReader inputFileReader;
	@Autowired
	private OutputFileWriter outputFileWriter;

	public static final String NO_JUICE_MESSAGE = "SORRY, YOU JUST HAVE WATER";
	
	public void start(String inputFilePath, String outputFilePath) {
		try {
			List<MixJuiceOrder> mixJuiceOrders = inputFileReader.readFile(inputFilePath);
			List<String> orderCompletionStatus = new ArrayList<String>();
			for (MixJuiceOrder mixJuiceOrder : mixJuiceOrders) {
				List<Juice> mixJuice = juiceMixer.prepareJuice(mixJuiceOrder.getCupboardJuiceList(),
						mixJuiceOrder.getCaloriIntake());
				if (mixJuice.size() > 0) {
					List<String> mixJuiceName = mixJuice.stream().map(juice -> juice.getJuiceName())
							.collect(Collectors.toList());
					orderCompletionStatus.add(String.join("", mixJuiceName));
				} else {
					orderCompletionStatus.add(NO_JUICE_MESSAGE);
				}
			}
			outputFileWriter.writeFile(outputFilePath, orderCompletionStatus);
		} catch (Exception e) {
			log.info("Juice bar has issue", e);
		}
	}
}
