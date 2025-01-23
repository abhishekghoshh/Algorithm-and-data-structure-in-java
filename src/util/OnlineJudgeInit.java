package util;

import com.util.PrintUtl;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class OnlineJudgeInit {
	private static final PrintStream printInConsole = System.out;
	private static final InputStream scanInConsole = System.in;
	private static final String testFolderName = "test-files";
	public static Scanner scanner = null;

	public static void set() {
		String rootPath = System.getProperty("user.dir");
		System.out.println(rootPath);
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		String className = stackTraceElements[stackTraceElements.length - 1].getClassName();
		System.out.println(className);
		String[] args = className.split("[.]");
		PrintUtl.print(args);
		String inputFileName = args[args.length - 1] + "-input.txt";
		String outputFileName = args[args.length - 1] + "-output.txt";
		String folderName = args[args.length - 2];

		setInputOutputStream(rootPath, inputFileName, outputFileName, folderName);
	}

	private static void setInputOutputStream(String rootPath, String inputFileName, String outputFileName, String folderName) {
		String inputFilePath = Paths.get(rootPath, testFolderName, folderName, inputFileName).toString();
		String outputFilePath = Paths.get(rootPath, testFolderName, folderName, outputFileName).toString();
		set(inputFilePath, outputFilePath);
	}

	// TODO unset method is not completed
	public static void unset() {
		if (scanner != null) scanner.close();
		System.setIn(scanInConsole);
		System.setOut(printInConsole);
	}

	public static void set(String inputFilePath, String outputFilePath) {
		setInputStream(inputFilePath);
		setOutputStream(outputFilePath);
		scanner = new Scanner(System.in);
	}

	private static void setInputStream(String inputFilePath) {
		try {
			Path path = Paths.get(inputFilePath);
			File initialFile = new File(path.toString());
			InputStream in = new FileInputStream(initialFile);
			System.setIn(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void setOutputStream(String outputFilePath) {
		Path path = Paths.get(outputFilePath);
		File outputFile = new File(path.toString());
		if (!outputFile.exists())
			try {
				System.out.println(outputFile.createNewFile());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		try {
			PrintStream out = new PrintStream(new FileOutputStream(outputFile), true);
			System.setOut(out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
