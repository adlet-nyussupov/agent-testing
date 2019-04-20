
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

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
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Tuple2;
import jade.core.AID;
import jade.core.Agent;
import jade.core.AgentContainer;
import jade.core.ContainerID;
import jade.core.behaviours.Behaviour;
import jade.domain.AMSService;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.Property;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.JADEAgentManagement.CreateAgent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import jade.util.leap.Properties;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import spoon.Launcher;
import spoon.legacy.NameFilter;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.factory.Factory;

public class Agent6 extends Agent {
	private Runnable targetInstance;
	private Class<?> targetClass;
	private static ArrayList<String> actualTestSamplesData;
	private static ArrayList<String> optimizedTestSamplesData;
	private static ArrayList<AID> allAgents;
	private static ArrayList<AID> allReceiver;
	private static AID randAgentID;
	private static HashMap<String, Tuple2<Integer, Integer>> actualCoverageData;
	private static HashMap<Integer, Integer> receivedLineCoverage;
	private static HashMap<Integer, Integer> actualLineCoverage;
	private AMSAgentDescription[] agents = null;
	private AMSAgentDescription agentState = null;
	private static DFAgentDescription dfd;
	private static DFAgentDescription[] agentsDF = null;
	private static DFAgentDescription dfSearch;
	private static ServiceDescription sd;
	private static ServiceDescription sdSearch;
	private static ACLMessage aclInitialRequest;
	private static SearchConstraints conDF;
	private static boolean actualTestSamplesUpdate = false;
	private static boolean sendToNewAgents = false;
	private static boolean executorIsOn = false;
	private static int badResultCounter = 0;
	private static int resultCounter = 0;
	private static int finish;
	private static int randomAgentsQuantity = 3;

	protected void setup() {
		actualTestSamplesData = new ArrayList<String>();
		receivedLineCoverage = new HashMap<Integer, Integer>();
		actualCoverageData = new HashMap<String, Tuple2<Integer, Integer>>();
		optimizedTestSamplesData = new ArrayList<String>();
		allReceiver = new ArrayList<AID>();
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

	private static void sendMessage(Agent newThis, int settings) throws IOException {
		AID localAgentID = newThis.getAID();
		allAgents = new ArrayList<AID>();
		aclInitialRequest = new ACLMessage(ACLMessage.REQUEST);
		switch (settings) {
		case 0:
			conDF = new SearchConstraints();
			conDF.setMaxResults(new Long(-1));
			dfSearch = new DFAgentDescription();
			sdSearch = new ServiceDescription();
			sdSearch.addProperties(new Property("isCoverageImproved", "none"));
			dfSearch.addServices(sdSearch);
			try {
				agentsDF = DFService.search(newThis, dfSearch, conDF);
			} catch (FIPAException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < agentsDF.length; i++) {
				AID agentID = agentsDF[i].getName();
				if (localAgentID.compareTo(agentID) != 0) {
					allAgents.add(agentID);
				}
			}
			Collections.shuffle(allAgents);
			for (int i = 0; i < randomAgentsQuantity; i++) {
				if (i < allAgents.size()) {
					randAgentID = (AID) allAgents.get(i);
					aclInitialRequest.addReceiver(randAgentID);
				}
			}
			if (allAgents.size() != 0) {
				aclInitialRequest.setConversationId("MESSAGES_ID");
				aclInitialRequest.setContent("COVERAGE_WAS_SENT");
				newThis.send(aclInitialRequest);
				aclInitialRequest.setConversationId("COVERAGE_ID");
				aclInitialRequest.setContentObject(actualCoverageData);
				newThis.send(aclInitialRequest);
				aclInitialRequest.setConversationId("MESSAGES_ID");
				aclInitialRequest.setContent("LINE_COVERAGE_WAS_SENT");
				newThis.send(aclInitialRequest);
				aclInitialRequest.setConversationId("LINE_COVERAGE_ID");
				aclInitialRequest.setContentObject(actualLineCoverage);
				newThis.send(aclInitialRequest);
				for (Iterator iterator = aclInitialRequest.getAllReceiver(); iterator.hasNext();) {
					AID comAgent = (AID) iterator.next();
					System.out.println("Receiver: " + comAgent.getLocalName());
					allReceiver.add(comAgent);
				}
			} else {
				System.out
						.println("All agents were executed. Final coverage result:\n" + actualCoverageData.toString());
			}
			break;
		case 1:
			conDF = new SearchConstraints();
			conDF.setMaxResults(new Long(-1));
			dfSearch = new DFAgentDescription();
			sdSearch = new ServiceDescription();
			sdSearch.addProperties(new Property("isCoverageImproved", "true"));
			dfSearch.addServices(sdSearch);
			try {
				agentsDF = DFService.search(newThis, dfSearch, conDF);
			} catch (FIPAException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < agentsDF.length; i++) {
				AID agentID = agentsDF[i].getName();
				if (localAgentID.compareTo(agentID) != 0) {
					allAgents.add(agentID);
				}
			}
			for (int i = 0; i < allAgents.size(); i++) {
				randAgentID = (AID) allAgents.get(i);
				aclInitialRequest.addReceiver(randAgentID);
			}
			if (allAgents.size() != 0) {
				aclInitialRequest.setConversationId("MESSAGES_ID");
				aclInitialRequest.setContent("COVERAGE_WAS_SENT");
				newThis.send(aclInitialRequest);
				aclInitialRequest.setConversationId("COVERAGE_ID");
				aclInitialRequest.setContentObject(actualCoverageData);
				newThis.send(aclInitialRequest);
				aclInitialRequest.setConversationId("MESSAGES_ID");
				aclInitialRequest.setContent("LINE_COVERAGE_WAS_SENT");
				newThis.send(aclInitialRequest);
				aclInitialRequest.setConversationId("LINE_COVERAGE_ID");
				aclInitialRequest.setContentObject(actualLineCoverage);
				newThis.send(aclInitialRequest);
				for (Iterator iterator = aclInitialRequest.getAllReceiver(); iterator.hasNext();) {
					AID comAgent = (AID) iterator.next();
					System.out.println("Receiver: " + comAgent.getLocalName());
					allReceiver.add(comAgent);
				}
			} else {
				System.out.println("Sending to new "+randomAgentsQuantity+" random agents");
				sendMessage(newThis, 0);
			}
			break;
		}

	}

	public static class DynamicStatesBehavior extends Behaviour {
		DynamicStatesBehavior(Agent6 aThis) {
		}

		private static MessageTemplate requestTemplate = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
		private static MessageTemplate informTemplate = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
		private static HashMap<String, Tuple2<Integer, Integer>> receivedCoverageData;
		private static ArrayList<String> receivedTestSamplesData;
		private static String ACTUAL_REQUEST;
		private static String COVERAGE_STATUS = "ERROR_UNDEFINED";
		private static String ACTUAL_REQUEST_SENDER;
		private static ACLMessage aclRequest;
		private static Tuple2 coveredInstructions;
		private static Tuple2 coveredBranches;
		private static Tuple2 coveredLines;
		private static Tuple2 coveredMethods;
		private static Tuple2 coveredComplexity;
		private static boolean goodCoverage;
		private static boolean badCoverage;
		private static boolean equalCoverage;
		private static boolean isCoverageImproved;
		private static String receiverValue;

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
						((Agent6) myAgent).execute();
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
							rep.setConversationId("MESSAGES_ID");
							rep.setContent(COVERAGE_STATUS);
							myAgent.send(rep);
						}
						break;
					case "TEST_SAMPLES_WERE_SENT_B":
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
							((Agent6) myAgent).execute();
							for (Entry<String, Tuple2<Integer, Integer>> actual : actualCoverageData.entrySet()) {
								for (Entry<String, Tuple2<Integer, Integer>> received : receivedCoverageData
										.entrySet()) {
									if (actual.getKey().equals(received.getKey())) {
										if (actual.getValue().getFirst() > received.getValue().getFirst()) {
											System.out.println("Improved " + actual.getKey() + " result: "
													+ actual.getValue().getFirst() + " of "
													+ actual.getValue().getSecond() + " - " + myAgent.getLocalName());
										}
									}
								}
							}
							ACTUAL_REQUEST = "";
							actualTestSamplesUpdate = true;
							regAgent(myAgent, new Property("isCoverageImproved", "true"), true);
							rep.setConversationId("MESSAGES_ID");
							rep.setContent("COVERAGE_WAS_IMPROVED");
							myAgent.send(rep);

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
							((Agent6) myAgent).execute();
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
					case "YOU_ARE_LEADER":
						sendMessage(myAgent, 1);
						break;
					// Sender
					case "COVERAGE_IS_BETTER":
						rep.setConversationId("MESSAGES_ID");
						rep.setContent("TEST_SAMPLES_WERE_SENT_B");
						System.out.println(myAgent.getAID().getLocalName() + " sent test samples data to "
								+ aclRequest.getSender().getLocalName());
						myAgent.send(rep);
						rep.setConversationId("TEST_SAMPLES_ID");
						rep.setContentObject(optimizedTestSamplesData);
						myAgent.send(rep);
						ACTUAL_REQUEST = "";
						break;
					case "COVERAGE_IS_WORSE":
						badResultCounter++;
						resultCounter++;
						selectRandomLeaderAgent(myAgent, doAllReceiverHaveResults(resultCounter, allReceiver));
						sendToNewAgents = doAllReceiverHaveBadResults(badResultCounter, allReceiver);
						if (sendToNewAgents) {
							System.out.println("All receiver have bad results. Sending to new random agents.");
							sendToNewAgents(myAgent);
						}
						ACTUAL_REQUEST = "";
						break;
					case "COVERAGE_IS_EQUAL":
						rep.setConversationId("MESSAGES_ID");
						rep.setContent("TEST_SAMPLES_WERE_SENT_E");
						System.out.println(myAgent.getAID().getLocalName() + " sent test samples data to "
								+ aclRequest.getSender().getLocalName());
						myAgent.send(rep);
						rep.setConversationId("TEST_SAMPLES_ID");
						rep.setContentObject(optimizedTestSamplesData);
						myAgent.send(rep);
						ACTUAL_REQUEST = "";
						break;
					case "COVERAGE_WAS_IMPROVED":
						resultCounter++;
						selectRandomLeaderAgent(myAgent, doAllReceiverHaveResults(resultCounter, allReceiver));
						ACTUAL_REQUEST = "";
						break;
					case "COVERAGE_WAS_NOT_IMPROVED":
						badResultCounter++;
						resultCounter++;
						sendToNewAgents = doAllReceiverHaveBadResults(badResultCounter, allReceiver);
						if (sendToNewAgents) {
							System.out.println("All receiver have bad results. Sending to new random agents.");
							sendToNewAgents(myAgent);
						}
						selectRandomLeaderAgent(myAgent, doAllReceiverHaveResults(resultCounter, allReceiver));
						ACTUAL_REQUEST = "";
						break;
					case "ERROR_UNDEFINED":
						System.out.println("ERROR - Coverage status undefined");
						ACTUAL_REQUEST = "";
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

	private static boolean doAllReceiverHaveBadResults(int counter, ArrayList<AID> list) {
		if (counter == list.size()) {
			return true;
		}
		return false;
	}

	private static boolean doAllReceiverHaveResults(int counter, ArrayList<AID> list) {
		if (counter == list.size()) {
			return true;
		}
		return false;
	}

	private static void sendToNewAgents(Agent newThis) throws IOException {
		aclInitialRequest.clearAllReceiver();
		aclInitialRequest.clearAllReplyTo();
		allAgents.clear();
		allReceiver.clear();
		badResultCounter = 0;
		resultCounter = 0;
		sendMessage(newThis, 0);
	}

	private static void selectRandomLeaderAgent(Agent newThis, Boolean doAllReceiverHaveResults) {
		if (doAllReceiverHaveResults) {
			allAgents.clear();
			AID localAgentID = newThis.getAID();
			SearchConstraints conDF = new SearchConstraints();
			conDF.setMaxResults(new Long(-1));
			DFAgentDescription dfSearch = new DFAgentDescription();
			ServiceDescription sdSearch = new ServiceDescription();
			sdSearch.addProperties(new Property("isCoverageImproved", "true"));
			dfSearch.addServices(sdSearch);
			try {
				agentsDF = DFService.search(newThis, dfSearch, conDF);
			} catch (FIPAException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < agentsDF.length; i++) {
				AID agentID = agentsDF[i].getName();
				if (localAgentID.compareTo(agentID) != 0) {
					allAgents.add(agentID);
					System.out.println(agentID.getLocalName());
				}
			}
			if (allAgents.size() != 0) {
				regAgent(newThis, new Property("isCoverageImproved", "false"), true);
				aclInitialRequest = new ACLMessage(ACLMessage.REQUEST);
				Collections.shuffle(allAgents);
				System.out.println("\nSelected leader: " + allAgents.get(0).getLocalName() + "\n");
				aclInitialRequest.addReceiver((AID) allAgents.get(0));
				aclInitialRequest.setConversationId("MESSAGES_ID");
				aclInitialRequest.setContent("YOU_ARE_LEADER");
				newThis.send(aclInitialRequest);
				finish = 1;
			}
		}
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

		targetClass.getMethod("d", int.class, int.class).invoke(targetInstance, 7, 7);


	}
}
