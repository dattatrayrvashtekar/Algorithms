package com.qp.juiceapp.juiceapp.filehandler;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OutputFileWriter {

	public void writeFile(String filePath, List<String> lines) throws IOException {
		Path out = Paths.get(filePath);
		Files.write(out, lines, Charset.defaultCharset());
	}
}
