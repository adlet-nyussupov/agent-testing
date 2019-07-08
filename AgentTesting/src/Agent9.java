
import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.jacoco.core.runtime.LoggerRuntime;
import org.jacoco.core.analysis.Analyzer;
import org.jacoco.core.analysis.CoverageBuilder;
import org.jacoco.core.analysis.IClassCoverage;
import org.jacoco.core.analysis.ICounter;
import org.jacoco.core.data.ExecutionDataStore;
import org.jacoco.core.data.SessionInfoStore;
import org.jacoco.core.instr.Instrumenter;
import org.jacoco.core.runtime.IRuntime;
import org.jacoco.core.runtime.RuntimeData;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Tuple2;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.Property;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import spoon.Launcher;
import spoon.legacy.NameFilter;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.factory.Factory;

public class Agent9 extends Agent {
	private Runnable targetInstance;
	private Class<?> targetClass;
	private static ArrayList<String> actualTestSamplesData;
	private static ArrayList<String> optimizedTestSamplesData;
	private static HashMap<String, Tuple2<Integer, Integer>> actualCoverageData;
	private static HashMap<Integer, Integer> receivedLineCoverage;
	private static HashMap<Integer, Integer> actualLineCoverage;
	private static DFAgentDescription dfd;
	private static ServiceDescription sd;
	private static boolean actualTestSamplesUpdate = false;
	private static boolean executorIsOn = false;
	private static int finish;

	protected void setup() {
		actualTestSamplesData = new ArrayList<String>();
		receivedLineCoverage = new HashMap<Integer, Integer>();
		actualCoverageData = new HashMap<String, Tuple2<Integer, Integer>>();
		optimizedTestSamplesData = new ArrayList<String>();
		actualLineCoverage = new HashMap<Integer, Integer>();
		regAgent(this, new Property("isCoverageImproved", "none"), false);
		testSamplesToString();
		try {
			execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		addBehaviour(new DynamicStatesBehavior(this));
	}

	public static class DynamicStatesBehavior extends Behaviour {
		DynamicStatesBehavior(Agent9 aThis) {
		}

		private static MessageTemplate requestTemplate = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
		private static HashMap<String, Tuple2<Integer, Integer>> receivedCoverageData;
		private static ArrayList<String> receivedTestSamplesData;
		private static String ACTUAL_REQUEST;
		private static String COVERAGE_STATUS = "ERROR_UNDEFINED";
		private static String ACTUAL_REQUEST_SENDER;
		private static ACLMessage aclRequest;
		private static boolean goodCoverage;
		private static boolean badCoverage;
		private static boolean equalCoverage;
		private static boolean isCoverageImproved;

		@Override
		public void action() {
			aclRequest = myAgent.receive(requestTemplate);

			if (aclRequest != null) {
				try {
					ACTUAL_REQUEST_SENDER = aclRequest.getSender().getLocalName();
					if (aclRequest.getConversationId().contentEquals("MESSAGES_ID")) {
						ACTUAL_REQUEST = aclRequest.getContent();
						System.out.println(myAgent.getLocalName() + " received message: "
								+ aclRequest.getContent().toString() + " from " + ACTUAL_REQUEST_SENDER);
					}
					ACLMessage rep = aclRequest.createReply();
					switch (ACTUAL_REQUEST) {
					case "COVERAGE_WAS_SENT":
						((Agent9) myAgent).execute();
						if (aclRequest.getConversationId().contentEquals("COVERAGE_ID")) {
							System.out.println(myAgent.getLocalName() + " received coverage data: "
									+ aclRequest.getContentObject().toString() + " from "
									+ aclRequest.getSender().getLocalName());
							receivedCoverageData = (HashMap<String, Tuple2<Integer, Integer>>) aclRequest
									.getContentObject();
							System.out.println("\n" + myAgent.getAID().getLocalName() + " analysis result:");
							for (String actual : actualCoverageData.keySet()) {
								for (String received : receivedCoverageData.keySet()) {
									if (actual.equalsIgnoreCase(received)) {
										if (receivedCoverageData.get(received).getFirst() < actualCoverageData
												.get(actual).getFirst()) {
											System.out.println("[" + ACTUAL_REQUEST_SENDER + " " + actual + " is: "
													+ receivedCoverageData.get(received).getFirst() + " of "
													+ receivedCoverageData.get(actual).getSecond() + "\n"
													+ myAgent.getLocalName() + " " + received + " is: "
													+ actualCoverageData.get(actual).getFirst() + " of "
													+ actualCoverageData.get(actual).getSecond()
													+ "\nCoverage is better.]");
											goodCoverage = true;
										} else if (receivedCoverageData.get(received).getFirst() > actualCoverageData
												.get(actual).getFirst()) {
											System.out.println("[" + ACTUAL_REQUEST_SENDER + " " + actual + " is: "
													+ receivedCoverageData.get(received).getFirst() + " of "
													+ receivedCoverageData.get(actual).getSecond() + "\n"
													+ myAgent.getLocalName() + " " + received + " is: "
													+ actualCoverageData.get(actual).getFirst() + " of "
													+ actualCoverageData.get(actual).getSecond()
													+ "\nCoverage is worse]");
											badCoverage = true;
										} else {
											System.out.println("[" + ACTUAL_REQUEST_SENDER + " " + actual + " is: "
													+ receivedCoverageData.get(received).getFirst() + " of "
													+ receivedCoverageData.get(actual).getSecond() + "\n"
													+ myAgent.getLocalName() + " " + received + " is: "
													+ actualCoverageData.get(actual).getFirst() + " of "
													+ actualCoverageData.get(actual).getSecond()
													+ "\nCoverage is equal]");
											equalCoverage = true;
										}
									}
								}
							}
							System.out.println("\n");
						}
						break;
					case "LINE_COVERAGE_WAS_SENT":
						if (aclRequest.getConversationId().contentEquals("LINE_COVERAGE_ID")) {
							System.out.println(myAgent.getLocalName() + " received line coverage data: "
									+ aclRequest.getContentObject().toString() + " from "
									+ aclRequest.getSender().getLocalName());

							System.out.println(myAgent.getLocalName() + " actual line coverage data: "
									+ actualLineCoverage.toString());

							receivedLineCoverage = (HashMap<Integer, Integer>) aclRequest.getContentObject();
							String lineCoverageResult = checkLineCoverage(actualLineCoverage, receivedLineCoverage,
									ACTUAL_REQUEST_SENDER, myAgent);
							if (goodCoverage || lineCoverageResult == "good") {
								COVERAGE_STATUS = "COVERAGE_IS_BETTER";
								regAgent(myAgent, new Property("isCoverageImproved", "true"), true);
							} else if (!goodCoverage && equalCoverage) {
								COVERAGE_STATUS = "COVERAGE_IS_EQUAL";
							} else if (!goodCoverage && !equalCoverage && badCoverage) {
								COVERAGE_STATUS = "COVERAGE_IS_WORSE";
								regAgent(myAgent, new Property("isCoverageImproved", "false"), true);
							}
							goodCoverage = false;
							badCoverage = false;
							equalCoverage = false;
							ACTUAL_REQUEST = "";
							if (COVERAGE_STATUS != "COVERAGE_IS_BETTER") {
								rep.setConversationId("MESSAGES_ID");
								rep.setContent(COVERAGE_STATUS);
								myAgent.send(rep);
							} else {
								rep.setConversationId("TEST_SAMPLES_ID");
								rep.setContentObject(actualTestSamplesData);
								myAgent.send(rep);
								rep.setConversationId("MESSAGES_ID");
								rep.setContent(COVERAGE_STATUS);
								myAgent.send(rep);
							}
						}
						break;
					case "TEST_SAMPLES_WERE_SENT_E":
						if (aclRequest.getConversationId().contentEquals("TEST_SAMPLES_ID")) {
							System.out.println(myAgent.getLocalName() + " received test samples data: "
									+ aclRequest.getContentObject().toString() + " from "
									+ aclRequest.getSender().getLocalName());
							receivedTestSamplesData = (ArrayList<String>) aclRequest.getContentObject();
							optimizedTestSamplesData.addAll(receivedTestSamplesData);
							if (!actualTestSamplesUpdate) {
								optimizedTestSamplesData.addAll(actualTestSamplesData);
							}
							System.out.println(
									myAgent.getLocalName() + " actual test samples data: " + actualTestSamplesData);
							Set<String> set = new HashSet<>(optimizedTestSamplesData);
							optimizedTestSamplesData.clear();
							optimizedTestSamplesData.addAll(set);
							System.out.println("\n" + myAgent.getAID().getLocalName() + " merged test samples: "
									+ optimizedTestSamplesData);
							actualCoverageData.clear();
							executorIsOn = true;
							((Agent9) myAgent).execute();
							for (Entry<String, Tuple2<Integer, Integer>> actual : actualCoverageData.entrySet()) {
								for (Entry<String, Tuple2<Integer, Integer>> received : receivedCoverageData
										.entrySet()) {
									if (actual.getKey().contentEquals(received.getKey())) {
										if (actual.getValue().getFirst() > received.getValue().getFirst()) {
											System.out.println("Improved " + actual.getKey() + " result: "
													+ actual.getValue().getFirst() + " of "
													+ actual.getValue().getSecond() + " - " + myAgent.getLocalName());
											isCoverageImproved = true;
										}
									}
								}
							}
							ACTUAL_REQUEST = "";
							if (isCoverageImproved) {
								actualTestSamplesUpdate = true;
								regAgent(myAgent, new Property("isCoverageImproved", "true"), true);
								System.out.println("Coverage was improved");
								rep.setConversationId("TEST_SAMPLES_ID");
								rep.setContentObject(actualTestSamplesData);
								myAgent.send(rep);
								rep.setConversationId("MESSAGES_ID");
								rep.setContent("COVERAGE_WAS_IMPROVED");
								myAgent.send(rep);
							} else {
								System.out.println("Coverage was not improved");
								regAgent(myAgent, new Property("isCoverageImproved", "false"), true);
								rep.setConversationId("MESSAGES_ID");
								rep.setContent("COVERAGE_WAS_NOT_IMPROVED");
								myAgent.send(rep);
							}
							isCoverageImproved = false;
						}
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				this.block();
			}
		}

		@Override
		public boolean done() {
			return finish == 1;
		}

	}

	private static String checkLineCoverage(HashMap<Integer, Integer> actualM, HashMap<Integer, Integer> receivedM,
			String sender, Agent newThis) {

		for (Entry<Integer, Integer> received : receivedM.entrySet()) {
			for (Entry<Integer, Integer> actual : actualM.entrySet()) {
				if (received.getKey().equals(actual.getKey())) {
					if (received.getValue() != null || actual.getValue() != null) {
						if (received.getValue() < actual.getValue()) {
							System.out.println("\n" + newThis.getAID().getLocalName() + " line analysis result:"
									+ "\n{One of the lines has better coverage. \nBetter coverage line is: (" + sender
									+ " " + received + ") < (" + actual + " " + newThis.getLocalName() + ")}\n");
							return "good";
						}
					}
				}
			}
		}
		return null;
	}

	private static void regAgent(Agent newThis, Property value, Boolean deregisterAgent) {
		dfd = new DFAgentDescription();
		dfd.setName(newThis.getAID());
		sd = new ServiceDescription();
		sd.setName("defaultName");
		sd.setType("defaultType");
		sd.addProperties(value);
		dfd.addServices(sd);
		try {
			if (!deregisterAgent) {
				DFService.register(newThis, dfd);
			} else {
				DFService.deregister(newThis);
				DFService.register(newThis, dfd);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static class MemoryClassLoader extends ClassLoader {
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

	private void execute() throws Exception {
		final String targetName = SUTClass.class.getName();
		final IRuntime runtime = new LoggerRuntime();
		final Instrumenter instr = new Instrumenter(runtime);
		InputStream original = getTargetClass(targetName);
		final byte[] instrumented = instr.instrument(original, targetName);
		original.close();
		final RuntimeData data = new RuntimeData();
		runtime.startup(data);
		final MemoryClassLoader memoryClassLoader = new MemoryClassLoader();
		memoryClassLoader.addDefinition(targetName, instrumented);
		targetClass = memoryClassLoader.loadClass(targetName);
		targetInstance = (Runnable) targetClass.newInstance();
		// Test samples
		runTestSamples();
		targetInstance.run();
		final ExecutionDataStore executionData = new ExecutionDataStore();
		final SessionInfoStore sessionInfos = new SessionInfoStore();
		data.collect(executionData, sessionInfos, false);
		runtime.shutdown();
		final CoverageBuilder coverageBuilder = new CoverageBuilder();
		final Analyzer analyzer = new Analyzer(executionData, coverageBuilder);
		original = getTargetClass(targetName);
		analyzer.analyzeClass(original, targetName);
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
	}

	private InputStream getTargetClass(final String name) {
		final String resource = '/' + name.replace('.', '/') + ".class";
		return getClass().getResourceAsStream(resource);
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

	private void runTestSamples() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		if (executorIsOn) {
			testSamplesExecutor();
		} else {
			testSamples();
		}
	}

	private void testSamplesToString() {
		Launcher spoon = new Launcher();
		spoon.addInputResource("src/" + this.getClass().getSimpleName() + ".java");
		spoon.buildModel();
		Factory factory = spoon.getFactory();
		CtMethod method = spoon.getFactory().getModel().getElements(new NameFilter<CtMethod>("testSamples")).get(0);
		for (CtStatement ct : method.getBody().getStatements()) {
			actualTestSamplesData.add(ct.toString().replaceAll("\\s+", "") + ";");
		}
	}

	private void testSamplesExecutor() {
		Binding binding = new Binding();
		binding.setVariable("targetClass", targetClass);
		binding.setVariable("targetInstance", targetInstance);
		GroovyShell shell = new GroovyShell(binding);
		for (String testSample : optimizedTestSamplesData) {
			shell.evaluate(testSample);
		}
	}

	private void testSamples() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {

		targetClass.getMethod("d", int.class, int.class).invoke(targetInstance, 0, 0);
		targetClass.getMethod("f", int.class, int.class).invoke(targetInstance, 0, 0);
		targetClass.getMethod("setData", int.class, int.class).invoke(targetInstance, 2, 9);
	}
}
