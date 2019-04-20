
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

import groovy.lang.Tuple2;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.domain.AMSService;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.Property;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import spoon.Launcher;
import spoon.legacy.NameFilter;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.factory.Factory;

public class Agent1 extends Agent {
	private Runnable targetInstance;
	private Class<?> targetClass;
	private static ArrayList<String> actualTestSamplesData;
	private static HashMap<String, Tuple2<Integer, Integer>> actualCoverageData;
	private static DFAgentDescription[] agentsDF = null;
	private static AMSAgentDescription[] agentsAMS = null;
	private static int finish;
	private static ArrayList<AID> allReceiver;
	private static ACLMessage aclInitialRequest;
	private static Boolean sendToNewAgents = false;
	private static AID randAgentID;
	private static ArrayList<AID> allAgents;
	private static DFAgentDescription dfd;
	private static ServiceDescription sd;
	private static int badResultCounter = 0;
	private static String receiverValue;
	private static int resultCounter = 0;

	private static HashMap<Integer, Integer> actualLineCoverage;

	protected void setup() {
		actualTestSamplesData = new ArrayList<String>();
		actualLineCoverage = new HashMap<Integer, Integer>();
		actualCoverageData = new HashMap<String, Tuple2<Integer, Integer>>();
		allReceiver = new ArrayList<AID>();
		dfd = new DFAgentDescription();
		dfd.setName(getAID());
		sd = new ServiceDescription();
		sd.setName("basicName");
		sd.setType("basic");
		sd.addProperties(new Property("isCoverageImproved", "none"));
		dfd.addServices(sd);
		testSamplesToString();

		try {
			DFService.register(this, dfd);
			this.execute();
			sendMessage(this, 0);

		} catch (Exception e) {
			e.printStackTrace();
		}
		this.addBehaviour(new DynamicStatesBehavior(this));

	}

	private static void sendMessage(Agent newThis, int settings) throws IOException {
		AID localAgentID = newThis.getAID();
		allAgents = new ArrayList<AID>();
		switch (settings) {
		case 0:
			SearchConstraints conAMS = new SearchConstraints();
			conAMS.setMaxResults(new Long(-1));
			try {
				agentsAMS = AMSService.search(newThis, new AMSAgentDescription(), conAMS);
			} catch (FIPAException e) {
				e.printStackTrace();
			}

			for (int i = 0; i < agentsAMS.length; i++) {
				AID agentID = agentsAMS[i].getName();
				if (agentID.getLocalName().toLowerCase().contains("agent") && localAgentID.compareTo(agentID) != 0) {
					allAgents.add(agentID);
				}
			}
			break;
		case 1:
			SearchConstraints conDF = new SearchConstraints();
			conDF.setMaxResults(new Long(-1));
			DFAgentDescription dfSearch = new DFAgentDescription();
			ServiceDescription sdSearch = new ServiceDescription();
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
			break;

		}

		if (allAgents.size() != 0) {
			aclInitialRequest = new ACLMessage(ACLMessage.REQUEST);
			Collections.shuffle(allAgents);
			for (int i = 0; i < 3; i++) {
				if (i < allAgents.size()) {
					randAgentID = (AID) allAgents.get(i);
					aclInitialRequest.addReceiver(randAgentID);
				}
			}
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
			System.out.println("All agents were executed");
		}

	}

	private static class DynamicStatesBehavior extends Behaviour {
		DynamicStatesBehavior(Agent1 aThis) {
		}

		private static int finish;
		private static MessageTemplate requestTemplate = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
		private static String ACTUAL_REQUEST = "";
		private static String ACTUAL_REQUEST_SENDER;

		@Override
		public void action() {
			ACLMessage aclRequest = myAgent.receive(requestTemplate);
			if (aclRequest != null) {
				try {
					ACTUAL_REQUEST_SENDER = aclRequest.getSender().getLocalName();
					if (aclRequest.getConversationId().contentEquals("MESSAGES_ID")) {
						System.out.println(myAgent.getLocalName() + " received message: " + aclRequest.getContent()
								+ " from " + aclRequest.getSender().getLocalName());
						ACTUAL_REQUEST = aclRequest.getContent();
					}
					ACLMessage rep = aclRequest.createReply();
					switch (ACTUAL_REQUEST) {
					case "COVERAGE_IS_BETTER":
						rep.setConversationId("MESSAGES_ID");
						rep.setContent("TEST_SAMPLES_WERE_SENT_B");
						System.out.println(myAgent.getAID().getLocalName() + " sent test samples data to "
								+ aclRequest.getSender().getLocalName());
						myAgent.send(rep);
						rep.setConversationId("TEST_SAMPLES_ID");
						rep.setContentObject(actualTestSamplesData);
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
						rep.setContentObject(actualTestSamplesData);
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

				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				this.block();
			}
		}

		@Override
		public boolean done() {
			return finish == 1;
			// return false;
		}
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
		sendMessage(newThis, 1);
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
				}
			}
			if (allAgents.size() != 0) {
				regAgent(newThis, new Property("isCoverageImproved", "false"), true);
				aclInitialRequest = new ACLMessage(ACLMessage.REQUEST);
				Collections.shuffle(allAgents);
				System.out.println("\nSelected leader: " + allAgents.get(0).getLocalName()+"\n");
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
		}
	}

	public void execute() throws Exception {
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
			NoSuchMethodException, SecurityException {
		testSamples();
	}

	public void testSamplesToString() {
		Launcher spoon = new Launcher();
		spoon.addInputResource("src/" + this.getClass().getSimpleName() + ".java");
		spoon.buildModel();
		Factory factory = spoon.getFactory();
		CtMethod method = spoon.getFactory().getModel().getElements(new NameFilter<CtMethod>("testSamples")).get(0);
		for (CtStatement ct : method.getBody().getStatements()) {
			actualTestSamplesData.add(ct.toString().replaceAll("\\s+", "") + ";");
		}
	}

	private void testSamples() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		targetClass.getMethod("setData", int.class, int.class).invoke(targetInstance, 2, 9);
	}

}
