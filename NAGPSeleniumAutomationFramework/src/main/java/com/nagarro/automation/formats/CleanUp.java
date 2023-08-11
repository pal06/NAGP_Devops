package com.nagarro.automation.formats;

/**
 * CleanUp clear the result directory and archive the result set
 *
 * @author Palvika
 */

import java.io.*;
import java.nio.file.*;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.bcel.classfile.SourceFile;
import org.apache.log4j.Logger;

import com.nagarro.automation.reporting.LoggerLoad;

public class CleanUp {
	static Logger logger = LoggerLoad.config("CleanUp");

	public static String resultPath = System.getProperty("user.dir") + "/Current test results";
	public static String archivePath = System.getProperty("user.dir") + "/Archived test results";
	Path zipFile;
	Path tempDir;

	// It copy the result directory
	private void copyResultDir() throws IOException {
		Path sourceDir = Paths.get(resultPath);
		// Create a new ZIP archive for the test results
		zipFile = Paths.get(archivePath + "/archive.zip");
		if (!Files.exists(zipFile)) {
			try {
				Files.createFile(zipFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("zip file is not created");
			}
		}

		// Copy all test result files to a temporary directory
		tempDir = Files.createTempDirectory("test-results");
		Files.walk(sourceDir).filter(Files::isRegularFile).forEach(sourceFile -> {
			try {
				Path destFile = tempDir.resolve(sourceDir.relativize(sourceFile));
				Files.createDirectories(destFile.getParent());
				Files.copy(sourceFile, destFile);
			} catch (IOException ex) {
				logger.error("Error copying file: " + ex.getMessage());
			}
		});
	}

	// it compress the result directory
	private void CompressResultDir() throws FileNotFoundException, IOException {
		// Compress the test result files into the ZIP archive
		try (FileOutputStream fos = new FileOutputStream(zipFile.toFile());
				ZipOutputStream zos = new ZipOutputStream(fos)) {
			Files.walk(tempDir).filter(Files::isRegularFile).forEach(file -> {
				try {
					ZipEntry entry = new ZipEntry(tempDir.relativize(file).toString());
					zos.putNextEntry(entry);
					Files.copy(file, zos);
					zos.closeEntry();
				} catch (IOException ex) {
					logger.error("Error adding file to archive: " + ex.getMessage());
				}
			});
		}

		// Delete the temporary directory and its contents
		deleteDirectory(tempDir.toFile());
	}

	// it deletes the result set
	private void deleteDirectory(File directory) {
		File[] contents = directory.listFiles();
		if (contents != null) {
			for (File file : contents) {
				deleteDirectory(file);
			}
		}
		directory.delete();
	}

	// Method to clean the current directory for new result set
	public void cleanDirectory() throws IOException {
		copyResultDir();
		CompressResultDir();
		File SourceDir = new File(resultPath);
		deleteDirectory(SourceDir);

	}
}
