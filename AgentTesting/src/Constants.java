import java.awt.Event;
import java.io.File;
import java.net.URLClassLoader;
import java.util.ArrayList;

import javax.swing.JButton;

import org.jacoco.core.runtime.IRuntime;

import jade.core.AID;

public class Constants {
	

	public static float requiredCoveragePercentage = 0;
	public static Class SUTClass = null;
	public static File targetFile = null;
	public static ArrayList<String> manualTestCases = new ArrayList<String>();
	public static JButton stopTestingButton = null;
	public static JButton startTestingButton = null;
	public static File[] files = null;
	public static ArrayList<String> externalClasses = new ArrayList<String>();
	public static int roundsQuantity = 0;
	
	//Parameters that must be default every execution of testing
	public static int methodIndex = 0;
	public static int sameValueCounter = 1;
	public static ArrayList<Integer> allMethodsOrdered = new ArrayList<Integer>();
	public static int round = 1;
	public static StringBuilder exceptions = new StringBuilder();
	public static int exceptionCounter = 0;
	public static boolean isError = false;
	public static int readyAgents = 0;
	public static int finishMainAgent = 0;
	public static long startTime;
	public static long stopTime;
	public static int agentQuantity = 0;

	
	public static void makeDefault() {
		methodIndex = 0;
		sameValueCounter = 1;
		allMethodsOrdered = new ArrayList<Integer>();
		round = 1;
		exceptions = new StringBuilder();
		exceptionCounter = 0;
		isError = false;
		readyAgents = 0;
		finishMainAgent = 0;
		stopTime = 0;
		agentQuantity = 0;
	}

}
