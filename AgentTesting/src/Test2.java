import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.SequenceInputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.compress.utils.IOUtils;
import org.jacoco.core.analysis.Analyzer;
import org.jacoco.core.analysis.CoverageBuilder;
import org.jacoco.core.analysis.IClassCoverage;
import org.jacoco.core.analysis.ICounter;
import org.jacoco.core.data.ExecutionDataStore;
import org.jacoco.core.data.SessionInfoStore;
import org.jacoco.core.instr.Instrumenter;
import org.jacoco.core.runtime.IRuntime;
import org.jacoco.core.runtime.LoggerRuntime;
import org.jacoco.core.runtime.RuntimeData;

import bsh.EvalError;
import bsh.Interpreter;
import bsh.This;
import groovy.lang.Tuple2;
import spoon.Launcher;
import spoon.SpoonModelBuilder;
import spoon.compiler.Environment;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.DefaultJavaPrettyPrinter;
import spoon.support.JavaOutputProcessor;

public class Test2 {

	private Runnable targetInstance;
	private Class<?> targetClass;
	private static ArrayList<String> actualTestSamplesData;
	private static HashMap<String, Tuple2<Integer, Integer>> actualCoverageData;
	private static HashMap<Integer, Integer> actualLineCoverage;

	private final PrintStream out;

	public Test2(final PrintStream out) {
		actualTestSamplesData = new ArrayList<String>();
		actualLineCoverage = new HashMap<Integer, Integer>();
		actualCoverageData = new HashMap<String, Tuple2<Integer, Integer>>();
		this.out = out;
	}

	public static void main(String[] args) {

		/*
		 * Launcher spoon = new Launcher(); spoon.addInputResource("./src/");
		 * spoon.buildModel(); Factory factory = spoon.getFactory(); CtClass l =
		 * Launcher.
		 * parseClass("class Food { void m() { System.out.println(\"yeah\");} }");
		 * Environment env = factory.getEnvironment(); env.setAutoImports(true);
		 * env.setCommentEnabled(true); File srcf = new File("./src/");
		 * env.setSourceOutputDirectory(srcf); //JavaOutputProcessor processor = new
		 * JavaOutputProcessor(new DefaultJavaPrettyPrinter(env)); //
		 * processor.setFactory(factory); // processor.createJavaFile(l); //
		 * processor.process();
		 */

		// new Food();

		try {
			new Test2(System.out).execute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (String actual : actualCoverageData.keySet()) {
			System.out.println(actual + " is: " + actualCoverageData.get(actual).getFirst() + " of "
					+ actualCoverageData.get(actual).getSecond());
		}
	}

	public void execute() throws Exception {
		selectClasses();

		final String targetName = class4.getName();
		final IRuntime runtime = new LoggerRuntime();
		final Instrumenter instr = new Instrumenter(runtime);
		InputStream original = new FileInputStream(file4);
		byte[] bytes = IOUtils.toByteArray(original);
		final byte[] instrumented = instr.instrument(bytes, targetName);
		original.close();

		// implInnerClasses(runtime);

		final RuntimeData data = new RuntimeData();
		runtime.startup(data);
		final MemoryClassLoader memoryClassLoader = new MemoryClassLoader();
		memoryClassLoader.addDefinition(targetName, instrumented);

		instrumentedList.forEach((k, v) -> {

			// memoryClassLoader.addDefinition(k, v);
		});

		targetClass = memoryClassLoader.loadClass(targetName);
		targetInstance = (Runnable) targetClass.newInstance();
		// Test samples
		
		targetInstance.run();
		
		
		runTestSamples();
		
		final ExecutionDataStore executionData = new ExecutionDataStore();
		final SessionInfoStore sessionInfos = new SessionInfoStore();
		data.collect(executionData, sessionInfos, false);
		runtime.shutdown();
		final CoverageBuilder coverageBuilder = new CoverageBuilder();

		final Analyzer analyzer = new Analyzer(executionData, coverageBuilder);

		original = new FileInputStream(file4);

		byte[] bytes2 = IOUtils.toByteArray(original);

		analyzer.analyzeClass(bytes2, targetName);

		original.close();

		for (final IClassCoverage cc : coverageBuilder.getClasses()) {
			actualCoverageData.put("instruction coverage", new Tuple2(cc.getInstructionCounter().getCoveredCount(),
					cc.getInstructionCounter().getTotalCount()));
			actualCoverageData.put("branch coverage",
					new Tuple2(cc.getBranchCounter().getCoveredCount(), cc.getBranchCounter().getTotalCount()));
			actualCoverageData.put("line coverage",
					new Tuple2(cc.getLineCounter().getCoveredCount(), cc.getLineCounter().getTotalCount()));
			actualCoverageData.put("method coverage",
					new Tuple2(cc.getMethodCounter().getCoveredCount(), cc.getMethodCounter().getTotalCount()));
			actualCoverageData.put("complexity coverage",
					new Tuple2(cc.getComplexityCounter().getCoveredCount(), cc.getComplexityCounter().getTotalCount()));
			


			for (int i = cc.getFirstLine(); i <= cc.getLastLine(); i++) {
				actualLineCoverage.put(Integer.valueOf(i), getNumberOfStatus(cc.getLine(i).getStatus()));
			}

		}

		System.out.println(actualLineCoverage);
	}

	private InputStream getTargetClass(final String name) {
		final String resource = '/' + name.replace('.', '/') + ".class";
		return getClass().getResourceAsStream(resource);
	}

	private String getColor(final int status) {
		switch (status) {
		case ICounter.NOT_COVERED:
			return "red";
		case ICounter.PARTLY_COVERED:
			return "yellow";
		case ICounter.FULLY_COVERED:
			return "green";
		}
		return "";
	}

	private Integer getNumberOfStatus(final int status) {
		switch (status) {
		case ICounter.NOT_COVERED:
			return 1;
		case ICounter.PARTLY_COVERED:
			return 2;
		case ICounter.FULLY_COVERED:
			return 3;
		}
		return null;
	}

	public void runTestSamples() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, InstantiationException {
		
		targetClass.getMethod("main", new Class[] {String[].class}).invoke(targetInstance, new Object[] {new java.lang.String[]{ "%=z%D^l{*6u]{i#KX54`", "&eZ0,aioBl|I0Z1&[YHD" }}); 
		 targetClass.getMethod("isPrime", new Class[] {int.class}).invoke(targetInstance, new Object[] {289096578}); 
		targetClass.getMethod("numFactors", new Class[] {int.class}).invoke(targetInstance, new Object[] {2129016443}); 
		targetClass.getMethod("getPrimes", new Class[] {int.class}).invoke(targetInstance, new Object[] {537055789}); 
	//	 targetClass.getMethod("printTest", new Class[] {int.class, int.class}).invoke(targetInstance, new Object[] {-758606998, -914287688}); 

		
		
		//targetClass.getMethod("main", new Class[] {String[].class}).invoke(targetInstance, new Object[] {new java.lang.String[]{ "*W$bI-O#]EZr.9&-a<=G", "}#hUNV,5fiX]?vDw)HJV" }}); 

		
		//targetClass.getMethod("run", new Class[] {});
		
		//targetClass.getMethod("run", new Class[] {}).invoke(targetInstance, new Object[] {});

		/*
		 * //targetClass.getMethod("f", new Class[]
		 * {String[].class}).invoke(targetInstance, new Object[] {new
		 * java.lang.String[]{ "M}@wKQ?*Z.!I3#FY2=tQ", ")g;SD$}a]#@zGv2au#a^" }});
		 * //targetClass.getMethod("run", new Class[] {}).invoke(targetInstance, new
		 * Object[] {}); // targetClass.getMethod("DoAlphapeticShifts", new Class[]
		 * {LinkedList.class}).invoke(targetInstance, new Object[] {new
		 * java.util.LinkedList<?>()}); // targetClass.getMethod("DoAlphapeticShifts",
		 * new Class[] {HashMap.class}).invoke(targetInstance, new Object[] {new
		 * java.util.HashMap<java.lang.String, java.lang.Integer>()}); String s =
		 * "targetClass.getMethod(\"DoAlphapeticShifts\", new Class[] {HashMap.class}).invoke(targetInstance, new Object[] {new java.util.HashMap<java.lang.String, java.lang.Integer>()});"
		 * ; int x = s.indexOf("<java"); int y = s.indexOf(">"); s =
		 * s.replace(s.substring(x, y+1), ""); //System.out.println(s);
		 * targetClass.getMethod("run", new Class[] {}).invoke(targetInstance, new
		 * Object[] {}); targetClass.getMethod("method2", new Class[] {int.class,
		 * int.class}).invoke(targetInstance, new Object[] {1787650791, 1787650791});
		 * targetClass.getMethod("method1", new Class[]
		 * {ArrayList.class}).invoke(targetInstance, new Object[] {new
		 * java.util.ArrayList()}); targetClass.getMethod("method3", new Class[]
		 * {int.class, int.class}).invoke(targetInstance, new Object[] {-1704534015,
		 * -1704534015}); targetClass.getMethod("method4", new Class[]
		 * {String.class}).invoke(targetInstance, new Object[]
		 * {"1*pVPA<j2(HLmTuj>*)A"}); targetClass.getMethod("method5", new Class[]
		 * {Double.class}).invoke(targetInstance, new Object[] {0.17325717813187458});
		 * targetClass.getMethod("method6", new Class[]
		 * {Float.class}).invoke(targetInstance, new Object[] {0.086159065F}); //
		 * targetClass.getMethod("method7", new Class[]
		 * {Byte.class}).invoke(targetInstance, new Object[] {-97});
		 * targetClass.getMethod("method8", new Class[]
		 * {Character.class}).invoke(targetInstance, new Object[] {'}'});
		 * targetClass.getMethod("method9", new Class[]
		 * {Long.class}).invoke(targetInstance, new Object[] {218169172L}); //
		 * targetClass.getMethod("method10", new Class[]
		 * {Short.class}).invoke(targetInstance, new Object[] {-13100});
		 * targetClass.getMethod("method11", new Class[]
		 * {Boolean.class}).invoke(targetInstance, new Object[] {false});
		 */
		/*
		 * targetClass.getMethod("solveAllNQueens", new Class[] {char[][].class,
		 * int.class, ArrayList.class}).invoke(targetInstance, new Object[] {new
		 * char[0][], -1737419666, new java.util.ArrayList()});
		 * targetClass.getMethod("main", new Class[] { String[].class
		 * }).invoke(targetInstance, new Object[] { new java.lang.String[0] });
		 * targetClass.getMethod("run", new Class[] {}).invoke(targetInstance, new
		 * Object[] {}); targetClass.getMethod("contains", new Class[] { char[].class,
		 * char.class }).invoke(targetInstance, new Object[] { new char[] { '#' }, '|'
		 * });
		 * 
		 * targetClass.getMethod("canSolve", new Class[] { char[][].class, int.class
		 * }).invoke(targetInstance, new Object[] { new char[][] { new char[0], new
		 * char[] { 'u', 'J' }, new char[] { 'P', 'w' } }, -456916383 });
		 * 
		 * targetClass.getMethod("printBoard", new Class[] { char[][].class
		 * }).invoke(targetInstance, new Object[] { new char[0][] });
		 * targetClass.getMethod("isSquare", new Class[] { char[][].class
		 * }).invoke(targetInstance, new Object[] { new char[][] { new char[] { '/',
		 * '<', '#' }, new char[] { '#', '.' } } }); targetClass.getMethod("makeCopy",
		 * new Class[] { char[][].class }).invoke(targetInstance, new Object[] { new
		 * char[][] { new char[] { '?' }, new char[] { 'Q', '3', 'E' }, new char[] {
		 * ',', '/' } } });
		 * 
		 * targetClass.getMethod("solveNQueens", new Class[] { int.class
		 * }).invoke(targetInstance, new Object[] { -1983231929 });
		 * 
		 * targetClass.getMethod("solveAllNQueens", new Class[] {char[][].class,
		 * int.class, ArrayList.class}).invoke(targetInstance, new Object[] {new
		 * char[0][], -1530052991, new java.util.ArrayList<>()});
		 * targetClass.getMethod("inbounds", new Class[] { int.class, int.class,
		 * char[][].class }).invoke(targetInstance, new Object[] { 1027885005,
		 * 897490233, new char[][] { new char[] { 'D', 'G' }, new char[] { 'K', '8' },
		 * new char[] { ',', ' ', 'f' } } });
		 * 
		 * targetClass.getMethod("getAllNQueens", new Class[] { int.class
		 * }).invoke(targetInstance, new Object[] { -1277824608 });
		 * 
		 * targetClass.getMethod("blankBoard", new Class[] { int.class
		 * }).invoke(targetInstance, new Object[] { -347374318 });
		 * 
		 * targetClass.getMethod("onlyContains", new Class[] { char[][].class,
		 * char[].class }).invoke(targetInstance, new Object[] { new char[][] { new
		 * char[] { 'I' }, new char[0] }, new char[] { '(' } });
		 * targetClass.getMethod("queensAreSafe", new Class[] { char[][].class
		 * }).invoke(targetInstance, new Object[] { new char[][] { new char[] { ']' },
		 * new char[] { 'p' } } });
		 */

		// targetClass.getMethod("main", new Class[]
		// {String[].class}).invoke(targetInstance, new Object[] {new
		// java.lang.String[]{ "gP}d=?^#MXJ|U?.orT+Y", "raj,h&/b/N6e>8)Wv%2)" }});
		// targetClass.getMethod("IsStrike", new Class[] {}).invoke(targetInstance, new
		// Object[] {});
		// targetClass.getMethod("bowlingGame", new Class[]
		// {String.class}).invoke(targetInstance, new Object[]
	/*	// {"[0,10][0,10][0,10][0,10][0,10][0,10][0,10][0,10][0,10][0,10][10]"});
		// targetClass.getMethod("bowlingGame", new Class[]
//		// {String.class}).invoke(targetInstance, new Object[]
//		// {"[0,9][0,9][0,9][0,9][0,9][0,9][0,9][0,9][0,9][0,9]"});
//		String result = "targetClass.getMethod(\"solveAllNQueens\", new Class[] {char[][].class, int.class, ArrayList.class}).invoke(targetInstance, new Object[] {new char[][]{ new char[]{ '#' }, new char[]{ 'H', 'l', '>' }, new char[]{ 'T' } }, -667216632, new java.util.ArrayList<char[][]>()});";
//		int b = result.indexOf("<java");
//		int e = result.indexOf(">");
//		int t = e - b;
//		if (b != -1 && e != -1 && t > 0) {
//			result = result.replace(result.substring(b, e + 1), "");
//		}
//		b = result.indexOf("t<");
//		e = result.indexOf(">()");
//		t = e - b;
//		if (b != -1 && e != -1 && t > 0) {
//			result = result.replace(result.substring(b + 1, e + 1), "");
//		}
//		System.out.println(result);
//		// targetClass.getMethod("solveAllNQueens", new Class[] {char[][].class,
////		// int.class, ArrayList.class}).invoke(targetInstance, new Object[] {new
////		// char[][]{ new char[]{ '#' }, new char[]{ 'H', 'l', '>' }, new char[]{ 'T' }
////		// }, 667216632, new java.util.ArrayList()});
////
////		Interpreter i = new Interpreter();
//		try {
//			i.set("targetClass", targetClass);
//			i.set("targetInstance", targetInstance);
//
//			// targetClass.getMethod("getMaxOverdrawn", new Class[]
//			// {}).invoke(targetInstance, new Object[] {});
//			// i.eval("targetClass.getMethod(\"f\", new Class[]
//			// {String[].class}).invoke(targetInstance, new Object[] {new
////			// java.lang.String[]{ \"M}@wKQ?*Z.!I3#FY2=tQ\", \")g;SD$}a]#@zGv2au#a^\"
////			// }});");
//			// i.eval("targetClass.getMethod(\"run\", new Class[] {}).invoke(targetInstance,
//			// new Object[] {});");
//			// i.eval("targetClass.getMethod(\"DoAlphapeticShifts\", new Class[]
//			// {ArrayList.class}).invoke(targetInstance, new Object[] {new
//*/			// java.util.ArrayList < > ()});");
//			// i.eval("targetClass.getMethod(\"method11\", new Class[]
//			// {Boolean.class}).invoke(targetInstance, new Object[] {false}); ");
//		} catch (EvalError et) {
//			// TODO Auto-generated catch block
//			et.printStackTrace();
//		}
//
//		// targetClass.getMethod("DoAlphapeticShifts",
		// ArrayList.class).invoke(targetInstance,
		// java.util.Collections.singletonList("0<n7vx]/PcS=SbfwY9zz"));

	}

public static class MemoryClassLoader extends ClassLoader {
	private final Map<String, byte[]> definitions = new HashMap<String, byte[]>();

	public void addDefinition(final String name, final byte[] bytes) {
		definitions.put(name, bytes);
	}

	@Override
	protected Class<?> loadClass(final String name, final boolean resolve) throws ClassNotFoundException {
		final byte[] bytes = definitions.get(name);
		if (bytes != null) {
			return defineClass(name, bytes, 0, bytes.length);
		}
		return super.loadClass(name, resolve);
	}}

	private static File file1;
	private static File file2;
	private static File file3;
	private static File file4;
	private static Class class5;
	private static Class class1;
	private static Class class2;
	private static Class class4;
	private static Enumeration e;

	private static File fileDir;
	private static HashMap<String, byte[]> instrumentedList = new HashMap<String, byte[]>();
	private static ClassLoader cl;
	private static ClassLoader cl2;

	public static void implInnerClasses(IRuntime runtime) throws ClassNotFoundException, IOException {

		File[] files = fileDir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(".class");
			}
		});

		for (File file : files) {

			if (file.getName().contains(file3.getName())) {

				Class innerClass = cl.loadClass(file.getName().replace(".class", ""));

				final String targetName = innerClass.getName();

				final Instrumenter instr = new Instrumenter(runtime);
				InputStream original = new FileInputStream(file);
				byte[] bytes = IOUtils.toByteArray(original);
				final byte[] instrumented = instr.instrument(bytes, targetName);
				original.close();
				instrumentedList.put(targetName, instrumented);

				// System.out.println(instrumentedList);

			}

		}
		;

	}

	public static class NewLoader extends URLClassLoader {
		public NewLoader(URL[] urls) {
			super(urls);
			// TODO Auto-generated constructor stub
		}

		private final Map<String, byte[]> definitions = new HashMap<String, byte[]>();

		public void addDefinition(final String name, final byte[] bytes) {
			definitions.put(name, bytes);
		}

		@Override
		protected Class<?> loadClass(final String name, final boolean resolve) throws ClassNotFoundException {
			final byte[] bytes = definitions.get(name);
			if (bytes != null) {
				return defineClass(name, bytes, 0, bytes.length);
			}
			return super.loadClass(name, resolve);
		}
	}

	public static void selectClasses() throws Exception {
		file1 = new File("C:/Users/Adlet/Documents/BowlingGame.class");
		file2 = new File("C:/Users/Adlet/Documents/BowlingGame$Frame.class");
		file3 = new File("C:/Users/Adlet/Documents/Test/Food.class");
		file4 = new File("C:/Users/Adlet/Google Диск/Assignments/EXPERIMENT/SUTClasses/PrimeEx.class");
		fileDir = new File("C:/Users/Adlet/Google Диск/Assignments/EXPERIMENT/SUTClasses");

		URL url = null;
		try {
			url = fileDir.toURI().toURL();
		} catch (MalformedURLException e1) {

			e1.printStackTrace();
		}

		URL[] urls = new URL[] { url };
		NewLoader loader = new NewLoader(urls);

		// InputStream input = new FileInputStream(file3);
		// byte[] bytesss = IOUtils.toByteArray(input);

		// loader.addDefinition("Food.class", bytesss);
		// input.close();

		class4 = loader.loadClass(file4.getName().replace(".class", ""));
		// class5 = loader.loadClass(file3.getName().replace(".class", ""));

		/*
		 * Launcher spoon = new Launcher(); spoon.addInputResource("./src/");
		 * spoon.buildModel(); Factory factory = spoon.getFactory(); CtClass<?> classB =
		 * factory.Class().get(class4); for (CtTypeReference<?> t :
		 * classB.getReferencedTypes()) {
		 * 
		 * if (!t.toString().startsWith("java") && !t.isPrimitive() && t.isShadow()) {
		 * System.out.println(t.toString()); } }
		 * 
		 * cl = new URLClassLoader(urls); cl2 = cl2.getSystemClassLoader();
		 * 
		 * 
		 * try { class1 = cl .loadClass("BowlingGame"); class2 = cl
		 * .loadClass("BowlingGame$Frame"); } catch (ClassNotFoundException e1) { //
		 * TODO Auto-generated catch block e1.printStackTrace(); }
		 */

	}

}
