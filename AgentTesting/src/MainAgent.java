import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.jacoco.core.runtime.LoggerRuntime;
import org.apache.commons.compress.utils.IOUtils;
import org.jacoco.core.analysis.Analyzer;
import org.jacoco.core.analysis.CoverageBuilder;
import org.jacoco.core.analysis.IClassCoverage;
import org.jacoco.core.analysis.ICounter;
import org.jacoco.core.data.ExecutionDataStore;
import org.jacoco.core.data.SessionInfoStore;
import org.jacoco.core.instr.Instrumenter;
import org.jacoco.core.runtime.IRuntime;
import org.jacoco.core.runtime.RuntimeData;
import bsh.EvalError;
import bsh.Interpreter;
import bsh.ParseException;
import groovy.lang.Tuple2;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.basic.Action;
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
import jade.domain.JADEAgentManagement.JADEManagementOntology;
import jade.domain.JADEAgentManagement.ShutdownPlatform;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import spoon.support.modelobs.action.AddAction;

public class MainAgent extends Agent {
	
	private static final long serialVersionUID = 1L;

	protected void setup() {

		this.addBehaviour(new DynamicStateBehavior(this));
	}

	private static class DynamicStateBehavior extends Behaviour {

		private static final long serialVersionUID = 1L;

		DynamicStateBehavior(MainAgent newThis) {
			actualTestSamplesData = new ArrayList<String>();
			actualTestSamplesDataTemp = new ArrayList<String>();
			actualTestSamplesData.addAll(Constants.manualTestCases);
			System.out.println("Manual test case: " + actualTestSamplesData);
			actualLineCoverage = new HashMap<Integer, Integer>();
			actualCoverageData = new HashMap<String, Tuple2<Integer, Integer>>();
			allReceiver = new ArrayList<AID>();
			DFAgentDescription dfd = new DFAgentDescription();
			dfd.setName(newThis.getAID());
			ServiceDescription sd = new ServiceDescription();
			sd.setName("basicName");
			sd.setType("basic");
			sd.addProperties(new Property("default", "default"));
			dfd.addServices(sd);
			try {
				waitAllAgents();
				System.out.println(newThis.getAID().getLocalName() + "Ready");
				DFService.register(newThis, dfd);
				this.execute();
			} catch (InstantiationException e) {
				Constants.exceptions.append("> Error in SUT class file. Please check that the SUT class"
						+ " implements \"Runnable\" interface, has a \"run\" method, does not have a constructor and has a public access modifier. \n");
				Constants.finishMainAgent = 1;
				Constants.exceptionCounter++;
				Constants.isError = true;
				System.out.println("------------------------------------------------");
				System.out.println("Errors: " + Constants.exceptionCounter + ".");
				System.out.println(Constants.exceptions.toString());
				stopAgentPlatform(newThis);
				e.printStackTrace();
			} catch (ClassCastException ce) {
				if (ce.getLocalizedMessage().contains("java.lang.Runnable")) {
					Constants.exceptions.append("> Error in SUT class file. Please check that the SUT class"
							+ " implements \"Runnable\" interface, has a \"run\" method, does not have a constructor and has a public access modifier. \n");
					Constants.finishMainAgent = 1;
					Constants.exceptionCounter++;
					Constants.isError = true;
					System.out.println("------------------------------------------------");
					System.out.println("Errors: " + Constants.exceptionCounter + ".");
					System.out.println(Constants.exceptions.toString());
					stopAgentPlatform(newThis);
					ce.printStackTrace();
				}
			} catch (IllegalAccessException ille) {
				Constants.exceptions.append("> Error in SUT class file. Please check that the SUT class"
						+ " implements \"Runnable\" interface, has a \"run\" method, does not have a constructor and has a public access modifier. \n");
				Constants.finishMainAgent = 1;
				Constants.exceptionCounter++;
				Constants.isError = true;
				System.out.println("------------------------------------------------");
				System.out.println("Errors: " + Constants.exceptionCounter + ".");
				System.out.println(Constants.exceptions.toString());
				stopAgentPlatform(newThis);
				ille.printStackTrace();
			}

			catch (Exception e) {
				Constants.isError = true;
				Constants.finishMainAgent = 1;
				e.printStackTrace();
			}
			try {
				sendMessage(newThis, 0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private MessageTemplate requestTemplate = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
		private String ACTUAL_REQUEST = "";
		private String ACTUAL_REQUEST_SENDER;
		private Runnable targetInstance;
		private Class<?> targetClass;
		private ArrayList<String> actualTestSamplesData;
		private ArrayList<String> actualTestSamplesDataTemp;
		private HashMap<String, Tuple2<Integer, Integer>> actualCoverageData;
		private DFAgentDescription[] agentsDF = null;
		private AMSAgentDescription[] agentsAMS = null;
		private ArrayList<AID> allReceiver;
		private ACLMessage aclInitialRequest;
		private AID randAgentID;
		private ArrayList<AID> allAgents;
		private int resultCounter = 0;
		private ArrayList<String> receivedTestSamplesData;
		private float actualCoveragePercentage;
		private float covered = 0.0f;
		private float overall = 0.0f;
		private HashMap<Integer, Integer> actualLineCoverage;

		@Override
		public void action() {
			ACLMessage aclRequest = myAgent.receive(requestTemplate);
			if (aclRequest != null) {
				ACLMessage rep = aclRequest.createReply();
				try {
					// System.out.println("id " + aclRequest.getConversationId());
					ACTUAL_REQUEST_SENDER = aclRequest.getSender().getLocalName();
					if (aclRequest.getConversationId().contentEquals("MESSAGES_ID")) {
						ACTUAL_REQUEST = aclRequest.getContent();
						if (!ACTUAL_REQUEST.equals("COVERAGE_IS_EQUAL")) {
							System.out.println(myAgent.getLocalName() + " received message: " + ACTUAL_REQUEST
									+ " from " + aclRequest.getSender().getLocalName());
						}
					}
					if (aclRequest.getConversationId().contentEquals("TEST_SAMPLES_ID")) {
						try {
							receivedTestSamplesData = (ArrayList<String>) aclRequest.getContentObject();
						} catch (UnreadableException e) {
							e.printStackTrace();
						}
					}

					switch (ACTUAL_REQUEST) {
					case "COVERAGE_WAS_IMPROVED_BY_REQUEST":
						resultCounter++;
						actualTestSamplesData.addAll(receivedTestSamplesData);
						checkCoverageSatisfaction(myAgent, doAllReceiverHaveResults(resultCounter, allReceiver));
						ACTUAL_REQUEST = "";
						break;
					case "CAN_NOT_HELP_TO_IMPROVE_COVERAGE":
						resultCounter++;
						checkCoverageSatisfaction(myAgent, doAllReceiverHaveResults(resultCounter, allReceiver));
						ACTUAL_REQUEST = "";
						break;
					case "COVERAGE_IS_EQUAL":
						rep.setConversationId("TEST_SAMPLES_ID");
						rep.setContentObject(actualTestSamplesDataTemp);
						myAgent.send(rep);
						System.out.println("------------------------------------------------");
						rep.setConversationId("MESSAGES_ID");
						rep.setContent("TEST_SAMPLES_WERE_SENT_E");
						System.out.println(myAgent.getAID().getLocalName() + " sent test samples data to "
								+ aclRequest.getSender().getLocalName());
						myAgent.send(rep);
						ACTUAL_REQUEST = "";
						break;
					case "ERROR_UNDEFINED":
						System.out.println("ERROR - Coverage status undefined");
						ACTUAL_REQUEST = "";
						break;
					}

				} catch (IOException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				this.block();
			}
		}

		@Override
		public boolean done() {
			if (Constants.finishMainAgent == 1) {
				try {
					stopAgentPlatform(myAgent);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return Constants.finishMainAgent == 1;
		}

		private void waitAllAgents() {
			if (Constants.isError == false) {
				while (Constants.readyAgents < Constants.allMethodsOrdered.size()) {
					try {
						TimeUnit.MILLISECONDS.sleep(1);
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
					waitAllAgents();
				}
			}
		}

		private boolean doAllReceiverHaveBadResults(int counter, ArrayList<AID> list) {
			if (counter == list.size()) {
				return true;
			}
			return false;
		}

		private boolean doAllReceiverHaveResults(int counter, ArrayList<AID> list) {
			if (counter == list.size()) {
				return true;
			}
			return false;
		}

		private void checkCoverageSatisfaction(Agent newThis, Boolean doAllReceiverHaveResults)
				throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
				NoSuchMethodException, SecurityException, IOException {
			if (doAllReceiverHaveResults) {
				HashSet<String> set = new HashSet<>(actualTestSamplesData);
				actualTestSamplesData = new ArrayList<String>();
				actualTestSamplesData.addAll(set);
				try {
					this.execute();
				} catch (Exception e) {
					Constants.exceptionCounter++;
					Constants.isError = true;
					Constants.finishMainAgent = 1;
					e.printStackTrace();
				}

				covered = 0.0f;
				overall = 0.0f;

				actualLineCoverage.forEach((x, y) -> {

					if (y != null) {
						overall = overall + 1;
						if (y == 3) {
							covered = covered + 1;
						} else if (y == 2) {
							covered = covered + 0.5f;
						}
					}
				});

				actualCoveragePercentage = (float) ((covered / overall) * 100.0f);
				// System.out.println("SAMPLES "+actualTestSamplesData);

				if (actualCoveragePercentage < Constants.requiredCoveragePercentage) {
					System.out.println("*******************************************************");
					System.out.println(
							"Coverage is not satisfied to the requirement - " + actualCoveragePercentage + "%.");
					System.out.println("*******************************************************");

					System.out.println(/* "No new agents. " */ +Constants.round + " rounds were executed.");
					System.out.println("------------------------------------------------");
					System.out.println("Round " + Constants.round + " selected test cases:");
					for (String t : actualTestSamplesData) {
						System.out.println(t);
					}
					System.out.println("------------------------------------------------");
					int newR = Constants.round + 1;
					if (Constants.round != Constants.roundsQuantity) {
						System.out.println("New round - " + newR + ". Sending to new agents...");
						System.out.println("------------------------------------------------");
						Constants.methodIndex = 0;
						Constants.readyAgents = 0;
						sendMessage(newThis, 1);
						Constants.round++;

					} else {
						System.out.println("*******************************************************");
						System.out.println("All rounds were executed - " + actualCoveragePercentage + "%.");
						System.out.println("*******************************************************");
						System.out.println("------------------------------------------------");
						System.out.println("Final coverage result:");
						System.out.println("------------------------------------------------");
						for (String actual : actualCoverageData.keySet()) {
							System.out.println(actual + " is: " + actualCoverageData.get(actual).getFirst() + " of "
									+ actualCoverageData.get(actual).getSecond());
						}
						System.out.println("------------------------------------------------");
						System.out.println("Final selected test cases " +actualTestSamplesData.size()+":");
						System.out.println("------------------------------------------------");
						for (String t : actualTestSamplesData) {
							System.out.println(t);
						}
						System.out.println("------------------------------------------------");
						System.out.println("Errors: " + Constants.exceptionCounter + ".");
						System.out.println(Constants.exceptions.toString());
						stopAgentPlatform(newThis);
					}

				} else {
					System.out.println("*******************************************************");
					System.out
							.println("Coverage is satisfied to the requirement!!! " + actualCoveragePercentage + "%.");
					System.out.println("*******************************************************");
					System.out.println("Final coverage result:");
					for (String actual : actualCoverageData.keySet()) {
						System.out.println(actual + " is: " + actualCoverageData.get(actual).getFirst() + " of "
								+ actualCoverageData.get(actual).getSecond());
					}

					System.out.println("------------------------------------------------");
					System.out.println("Final selected test cases " +actualTestSamplesData.size()+":");
					System.out.println("------------------------------------------------");
					for (String t : actualTestSamplesData) {
						System.out.println(t);
					}

					System.out.println("------------------------------------------------");
					System.out.println("Errors: " + Constants.exceptionCounter + ".");
					System.out.println(Constants.exceptions.toString());

					stopAgentPlatform(newThis);
				}

			}

		}

		public static class CustomClassLoader extends ClassLoader {
			private final Map<String, byte[]> def = new HashMap<String, byte[]>();

			public void addDefinition(final String name, final byte[] bytes) {
				def.put(name, bytes);
			}

			@Override
			protected Class<?> loadClass(final String name, final boolean resolve) throws ClassNotFoundException {
				final byte[] bytes = def.get(name);
				if (bytes != null) {
					return defineClass(name, bytes, 0, bytes.length);
				}
				return super.loadClass(name, resolve);
			}
		}

		public void execute() throws Exception {
			final HashMap<String, byte[]> instrumentedList = new HashMap<String, byte[]>();
			final String tName = Constants.SUTClass.getName();
			final IRuntime rt = new LoggerRuntime();
			final Instrumenter inst = new Instrumenter(rt);
			InputStream inStream = new FileInputStream(Constants.targetFile);
			byte[] bytes = IOUtils.toByteArray(inStream);
			final byte[] instrByte = inst.instrument(bytes, tName);
			inStream.close();
			if (Constants.files != null) {
				for (File file : Constants.files) {
					if (file.getName().contains(Constants.SUTClass.getSimpleName() + "$")) {
						final String innerTName = file.getName().replace(".class", "");
						InputStream innerInStream = new FileInputStream(file);
						byte[] innerBytes = IOUtils.toByteArray(innerInStream);
						final byte[] innerInstrByte = inst.instrument(innerBytes, innerTName);
						innerInStream.close();
						instrumentedList.put(innerTName, innerInstrByte);
					} else {
						for (String extClass : Constants.externalClasses) {
							if (extClass.equals(file.getName())) {
								final String extTName = file.getName().replace(".class", "");
								InputStream extInStream = new FileInputStream(file);
								byte[] extBytes = IOUtils.toByteArray(extInStream);
								final byte[] extInstrByte = inst.instrument(extBytes, extTName);
								extInStream.close();
								instrumentedList.put(extTName, extInstrByte);
							}
						}
					}
				}
				;
			}
			final RuntimeData dt = new RuntimeData();
			rt.startup(dt);
			final CustomClassLoader custClassLoader = new CustomClassLoader();
			custClassLoader.addDefinition(tName, instrByte);
			instrumentedList.forEach((k, v) -> {
				custClassLoader.addDefinition(k, v);
			});
			targetClass = custClassLoader.loadClass(tName);
			targetInstance = (Runnable) targetClass.newInstance();
			targetInstance.run();
			testSamplesExecutor();
			final ExecutionDataStore execDt = new ExecutionDataStore();
			final SessionInfoStore sessInf = new SessionInfoStore();
			dt.collect(execDt, sessInf, false);
			rt.shutdown();
			final CoverageBuilder cb = new CoverageBuilder();
			final Analyzer analyzer = new Analyzer(execDt, cb);
			inStream = new FileInputStream(Constants.targetFile);
			byte[] bytes2 = IOUtils.toByteArray(inStream);
			analyzer.analyzeClass(bytes2, tName);
			inStream.close();
			actualCoverageData = new HashMap<String, Tuple2<Integer, Integer>>();
			actualLineCoverage = new HashMap<Integer, Integer>();
			for (final IClassCoverage cc : cb.getClasses()) {
				actualCoverageData.put("instruction coverage", new Tuple2(cc.getInstructionCounter().getCoveredCount(),
						cc.getInstructionCounter().getTotalCount()));
				actualCoverageData.put("branch coverage",
						new Tuple2(cc.getBranchCounter().getCoveredCount(), cc.getBranchCounter().getTotalCount()));
				actualCoverageData.put("line coverage",
						new Tuple2(cc.getLineCounter().getCoveredCount(), cc.getLineCounter().getTotalCount()));
				actualCoverageData.put("method coverage",
						new Tuple2(cc.getMethodCounter().getCoveredCount(), cc.getMethodCounter().getTotalCount()));
				actualCoverageData.put("complexity coverage", new Tuple2(cc.getComplexityCounter().getCoveredCount(),
						cc.getComplexityCounter().getTotalCount()));

				for (int i = cc.getFirstLine(); i <= cc.getLastLine(); i++) {
					actualLineCoverage.put(Integer.valueOf(i), getNumberOfStatus(cc.getLine(i).getStatus()));
				}

			}
			targetClass = null;
			targetInstance = null;
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

		private void testSamplesExecutor() {
			Interpreter i = new Interpreter();
			try {
				i.set("targetClass", targetClass);
				i.set("targetInstance", targetInstance);
				for (String testSample : actualTestSamplesData) {
					i.eval(testSample);	
					i.eval("targetInstance = (Runnable) targetClass.newInstance();");
					i.eval("targetInstance.run();");
				}
			} catch (ParseException e) {
				e.printStackTrace();
				actualTestSamplesData = new ArrayList<String>();
				Constants.exceptions.append(
						"> Error in the manual test cases. Please check that test cases were added according to the template: \"targetClass.getMethod(\"method_name\", new Class[] {input_type_1, input_type_2, ...}).invoke(targetInstance, new Object[] {input_value_1, input_value_2, ...});\"\n");
				Constants.exceptionCounter++;
			} catch (Exception ex) {
				Constants.exceptions.append("> SUTClass bug. Cause: " + ex.getLocalizedMessage() + "\n");
				Constants.exceptionCounter++;
				ex.printStackTrace();
			}

		}

		private void sendMessage(Agent newThis, int settings) throws IOException {
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
					if (agentID.getLocalName().toLowerCase().contains("agent")
							&& localAgentID.compareTo(agentID) != 0) {
						allAgents.add(agentID);
					}
				}
				break;
			case 1:
				// System.out.println("Sending to new random agents. ");
				SearchConstraints conDF = new SearchConstraints();
				conDF.setMaxResults(new Long(-1));
				DFAgentDescription dfSearch = new DFAgentDescription();
				ServiceDescription sdSearch = new ServiceDescription();
				sdSearch.addProperties(new Property("AgentStatus", "default"));
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
				for (int i = 0; i < Constants.agentQuantity; i++) {
					if (i < allAgents.size()) {
						randAgentID = (AID) allAgents.get(i);
						aclInitialRequest.addReceiver(randAgentID);
					}
				}
				actualTestSamplesDataTemp = new ArrayList<String>();
				actualTestSamplesDataTemp.addAll(actualTestSamplesData);
				if (settings == 1) {
					aclInitialRequest.setConversationId("MESSAGES_ID");
					aclInitialRequest.setContent("START_NEW_ROUND");
					newThis.send(aclInitialRequest);
					waitAllAgents();
				}
				aclInitialRequest.setConversationId("COVERAGE_ID");
				aclInitialRequest.setContentObject(actualCoverageData);
				newThis.send(aclInitialRequest);
				aclInitialRequest.setConversationId("MESSAGES_ID");
				aclInitialRequest.setContent("REQUEST_FOR_HELP");
				newThis.send(aclInitialRequest);
				aclInitialRequest.setConversationId("LINE_COVERAGE_ID");
				aclInitialRequest.setContentObject(actualLineCoverage);
				newThis.send(aclInitialRequest);
				aclInitialRequest.setConversationId("MESSAGES_ID");
				aclInitialRequest.setContent("LINE_COVERAGE_WAS_SENT");
				newThis.send(aclInitialRequest);
				for (Iterator iterator = aclInitialRequest.getAllReceiver(); iterator.hasNext();) {
					AID comAgent = (AID) iterator.next();
					System.out.println("Receiver: " + comAgent.getLocalName());
					allReceiver.add(comAgent);
				}

			} else {

			}
		}

		private void stopAgentPlatform(Agent newThis) {
			actualCoverageData = new HashMap<String, Tuple2<Integer, Integer>>();
			actualLineCoverage = new HashMap<Integer, Integer>();
			Codec c = new SLCodec();
			Ontology ont = JADEManagementOntology.getInstance();
			newThis.getContentManager().registerLanguage(c);
			newThis.getContentManager().registerOntology(ont);
			ACLMessage messg = new ACLMessage(ACLMessage.REQUEST);
			messg.addReceiver(newThis.getAMS());
			messg.setLanguage(c.getName());
			messg.setOntology(ont.getName());
			Constants.stopTime = new Date().getTime();
			long elapsedTime = Constants.stopTime - Constants.startTime;
			System.out.println(elapsedTime * 0.001F);

			actualTestSamplesData = null;
			actualTestSamplesDataTemp = null;
			actualCoverageData = null;
			agentsDF = null;
			agentsAMS = null;
			allReceiver = null;
			aclInitialRequest = null;
			randAgentID = null;
			allAgents = null;
			resultCounter = 0;
			receivedTestSamplesData = null;
			actualCoveragePercentage = 0.0f;
			covered = 0.0f;
			overall = 0.0f;
			actualLineCoverage = null;

			try {
				newThis.getContentManager().fillContent(messg, new Action(newThis.getAID(), new ShutdownPlatform()));
				Constants.stopTestingButton.setEnabled(false);
				Constants.startTestingButton.setEnabled(true);
				newThis.send(messg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
