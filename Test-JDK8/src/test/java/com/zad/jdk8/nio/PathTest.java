package com.zad.jdk8.nio;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author zad
 * @version 1.0
 * @descript
 * @date 2019/7/18 16:06
 */
@Slf4j
public class PathTest {

	@Test
	void pathsGetTest() throws URISyntaxException {
		Path path = Paths.get(getClass().getClassLoader().getResource("fileTest.txt").toURI());
		log.info("the path is -> {}", path);
		log.info("the file is -> {}", path.toFile());
		log.info("the absolutePath is -> {}", path.toFile().getAbsolutePath());
		log.info("the path exists -> {}", path.toFile().exists());

		Path path1 = Paths.get(getClass().getResource("/fileTest.txt").toURI());
		log.info("the path is -> {}", path1);
		log.info("the file is -> {}", path1.toFile());
		log.info("the absolutePath is -> {}", path1.toFile().getAbsolutePath());
		log.info("the path exists -> {}", path1.toFile().exists());


		Path path2 = Paths.get("E:/Code/Test-Java/Test-JDK8/target/test-classes", "/fileTest.txt");
		log.info("the path is -> {}", path2);
		log.info("the file is -> {}", path2.toFile());
		log.info("the absolutePath is -> {}", path2.toFile().getAbsolutePath());
		log.info("the path exists -> {}", path2.toFile().exists());

		Path path3 = Paths.get("fileTest.txt");
		log.info("the path is -> {}", path3);
		log.info("the file is -> {}", path3.toFile());
		log.info("the absolutePath is -> {}", path3.toFile().getAbsolutePath());
		log.info("the path exists -> {}", path3.toFile().exists());
	}


	@Test
	void filesTest() throws IOException {
		Path path = Files.createTempFile("fileTest1", ".txt");

		log.info("file exists -> {}", Files.exists(path));
	}

	@Test
	void s1() {
		Path path = FileSystems.getDefault().getPath("E:/Code/Test-Java/Test-JDK8/target/test-classes", "/fileTest.txt");
		log.info("the path is -> {}", path);
		log.info("the file is -> {}", path.toFile());
		log.info("the absolutePath is -> {}", path.toFile().getAbsolutePath());
		log.info("the path exists -> {}", path.toFile().exists());

		Path path1 = FileSystems.getDefault().getPath("target/test-classes/fileTest.txt");
		log.info("the path is -> {}", path1);
		log.info("the file is -> {}", path1.toFile());
		log.info("the absolutePath is -> {}", path1.toFile().getAbsolutePath());
		log.info("the path exists -> {}", path1.toFile().exists());

	}
}
