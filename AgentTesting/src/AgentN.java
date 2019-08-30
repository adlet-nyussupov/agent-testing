
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
import spoon.SpoonException;
import spoon.reflect.code.CtExpression;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtParameter;
import spoon.reflect.factory.Factory;
import spoon.reflect.visitor.filter.TypeFilter;

public class AgentN extends Agent {


	private static final long serialVersionUID = 2L;



	protected void setup() {

		regAgent(this, new Property("AgentStatus", "default"), false);
		String methodTestCase = testCaseGenerator(this);
		ArrayList<String> actualTestSamplesData = new ArrayList<String>();
		actualTestSamplesData.add(methodTestCase);
		addBehaviour(new DynamicStateBehavior(this, actualTestSamplesData));
		Constants.readyAgents++;
		System.out.println("Generated test case: " + methodTestCase + " " + this.getLocalName());
	}

	public static class DynamicStateBehavior extends Behaviour {

		private static final long serialVersionUID = 2L;

		DynamicStateBehavior(AgentN aThis, ArrayList<String> actualTestSamplesData) {
			this.actualTestSamplesData = actualTestSamplesData;
		}

		private HashMap<Integer, Integer> receivedLineCoverage = new HashMap<Integer, Integer>();
		private HashMap<String, Tuple2<Integer, Integer>> actualCoverageData = new HashMap<String, Tuple2<Integer, Integer>>();
		private HashMap<Integer, Integer> actualLineCoverage = new HashMap<Integer, Integer>();
		private MessageTemplate requestTemplate = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
		private HashMap<String, Tuple2<Integer, Integer>> receivedCoverageData;
		private ArrayList<String> receivedTestSamplesData;
		private String COVERAGE_STATUS = "ERROR_UNDEFINED";
		private String ACTUAL_REQUEST_SENDER;
		private boolean isCoverageImproved;
		private ArrayList<String> actualTestSamplesData;
		private ArrayList<String> optimizedTestSamplesData;
		private Runnable targetInstance;
		private Class<?> targetClass;
		private int finish;
		private ACLMessage rep;
		boolean checker;

		@Override
		public void action() {
			ACLMessage aclRequest = myAgent.receive(requestTemplate);
			String ACTUAL_REQUEST;
			if (aclRequest != null) {
				try {
					ACTUAL_REQUEST_SENDER = aclRequest.getSender().getLocalName();
					rep = aclRequest.createReply();

					if (aclRequest.getConversationId().contentEquals("COVERAGE_ID")) {
						receivedCoverageData = (HashMap<String, Tuple2<Integer, Integer>>) aclRequest
								.getContentObject();
					}
					if (aclRequest.getConversationId().contentEquals("LINE_COVERAGE_ID")) {
						receivedLineCoverage = (HashMap<Integer, Integer>) aclRequest.getContentObject();
					}
					if (aclRequest.getConversationId().contentEquals("TEST_SAMPLES_ID")) {
						receivedTestSamplesData = (ArrayList<String>) aclRequest.getContentObject();
					}
					if (aclRequest.getConversationId().contentEquals("MESSAGES_ID")) {
						ACTUAL_REQUEST = aclRequest.getContent();
						if (ACTUAL_REQUEST.equals("REQUEST_FOR_HELP")) {
							System.out.println(myAgent.getLocalName() + " received message: "
									+ aclRequest.getContent().toString() + " from " + ACTUAL_REQUEST_SENDER);
						}
						switch (ACTUAL_REQUEST) {
						case "REQUEST_FOR_HELP":
							System.out.println(myAgent.getLocalName() + " received coverage data from "
									+ aclRequest.getSender().getLocalName());
							try {
								execute(true);
							} catch (InstantiationException e) {
								Constants.exceptions.append("> Error of SUT class file. Please check that the SUT class"
										+ " implements Runnable interface, has a \"run\" method, does not have a constructor and has a public access modifier. \n");
								Constants.finishMainAgent = 1;
								Constants.exceptionCounter++;
								Constants.isError = true;
							} catch (ClassCastException ce) {
								if (ce.getLocalizedMessage().contains("java.lang.Runnable")) {
									Constants.exceptions
											.append("> Error in SUT class file. Please check that the SUT class"
													+ " implements \"Runnable\" interface, has a \"run\" method, does not have a constructor and has a public access modifier. \n");
									Constants.finishMainAgent = 1;
									Constants.exceptionCounter++;
									Constants.isError = true;
								}
							} catch (IllegalAccessException ille) {
								Constants.exceptions.append("> Error in SUT class file. Please check that the SUT class"
										+ " implements \"Runnable\" interface, has a \"run\" method, does not have a constructor and has a public access modifier. \n");
								Constants.finishMainAgent = 1;
								Constants.exceptionCounter++;
								Constants.isError = true;
							} catch (Exception e) {
								e.printStackTrace();
							}

							break;
						case "LINE_COVERAGE_WAS_SENT":
							if(!checker) {
								break;
							}
							System.out.println(myAgent.getLocalName() + " received line coverage data from "
									+ aclRequest.getSender().getLocalName());
							String lineCoverageResult = checkLineCoverage(actualLineCoverage, receivedLineCoverage,
									ACTUAL_REQUEST_SENDER, myAgent);
							actualLineCoverage = null;
							if (lineCoverageResult.equals("goodCoverage")) {
								COVERAGE_STATUS = "COVERAGE_WAS_IMPROVED_BY_REQUEST";

								for (Entry<String, Tuple2<Integer, Integer>> actual : actualCoverageData.entrySet()) {
									for (Entry<String, Tuple2<Integer, Integer>> received : receivedCoverageData
											.entrySet()) {
										if (actual.getKey().contentEquals(received.getKey())) {
											if (actual.getValue().getFirst() > received.getValue().getFirst()) {
												System.out.println("Improved " + actual.getKey() + " result: "
														+ actual.getValue().getFirst() + " of "
														+ actual.getValue().getSecond() + " - "
														+ myAgent.getLocalName());
											}
										}
									}
								}
								rep.setConversationId("TEST_SAMPLES_ID");
								rep.setContentObject(actualTestSamplesData);
								myAgent.send(rep);
								rep.setConversationId("MESSAGES_ID");
								rep.setContent(COVERAGE_STATUS);
								myAgent.send(rep);
								System.out.println("------------------------------------------------");
							} else if (lineCoverageResult.equals("equalCoverage")) {
								COVERAGE_STATUS = "COVERAGE_IS_EQUAL";
								rep.setConversationId("MESSAGES_ID");
								rep.setContent(COVERAGE_STATUS);
								myAgent.send(rep);
							} else  {
								COVERAGE_STATUS = "CAN_NOT_HELP_TO_IMPROVE_COVERAGE";
								rep.setConversationId("MESSAGES_ID");
								rep.setContent(COVERAGE_STATUS);
								myAgent.send(rep);
							}
							COVERAGE_STATUS = "";
							break;
						case "TEST_SAMPLES_WERE_SENT_E":
							System.out.println(myAgent.getLocalName() + " received test samples data from "
									+ aclRequest.getSender().getLocalName());
							optimizedTestSamplesData = new ArrayList<String>();
							optimizedTestSamplesData.addAll(receivedTestSamplesData);
							optimizedTestSamplesData.addAll(actualTestSamplesData);
							Set<String> set = new HashSet<>(optimizedTestSamplesData);
							optimizedTestSamplesData = new ArrayList<String>(); 
							optimizedTestSamplesData.addAll(set);
						
							try {
								execute(false);
							} catch (Exception e) {
								Constants.finishMainAgent = 1;
								Constants.exceptionCounter++;
								Constants.isError = true;
								e.printStackTrace();
							}		
							
							
							for (Entry<Integer, Integer> actual : actualLineCoverage.entrySet()) {
								for (Entry<Integer, Integer> received : receivedLineCoverage.entrySet()) {
									if (received.getKey().equals(actual.getKey())) {
										if (received.getValue() != null && actual.getValue() != null) {
											if (received.getValue() < actual.getValue()) {
												isCoverageImproved = true;
												break;
											}
										}
									}
								}
							}
							
							if (isCoverageImproved) {
								for (Entry<String, Tuple2<Integer, Integer>> actual : actualCoverageData.entrySet()) {
									for (Entry<String, Tuple2<Integer, Integer>> received : receivedCoverageData
											.entrySet()) {
										if (actual.getKey().contentEquals(received.getKey())) {
											if (actual.getValue().getFirst() > received.getValue().getFirst()) {
												System.out.println("Improved " + actual.getKey() + " result: "
														+ actual.getValue().getFirst() + " of "
														+ actual.getValue().getSecond() + " - "
														+ myAgent.getLocalName());
											}
										}
									}
								}
							}
							
							
							actualCoverageData = null;
							ACTUAL_REQUEST = "";
							if (isCoverageImproved) {
								// System.out.println("Coverage was improved");
								System.out.println("------------------------------------------------");
								rep.setConversationId("TEST_SAMPLES_ID");
								rep.setContentObject(actualTestSamplesData);
								myAgent.send(rep);
								rep.setConversationId("MESSAGES_ID");
								rep.setContent("COVERAGE_WAS_IMPROVED_BY_REQUEST");
								myAgent.send(rep);

							} else {
								// System.out.println("Coverage was not improved");
								System.out.println("------------------------------------------------");
								rep.setConversationId("MESSAGES_ID");
								rep.setContent("CAN_NOT_HELP_TO_IMPROVE_COVERAGE");
								myAgent.send(rep);

							}

							break;
						case "START_NEW_ROUND":
							
							checker = false;
							String methodTestCase = testCaseGenerator(myAgent);
							actualTestSamplesData = new ArrayList<String>();
							receivedCoverageData = new HashMap<String, Tuple2<Integer,Integer>>();
							receivedLineCoverage = new HashMap<Integer, Integer>();
							receivedTestSamplesData = new ArrayList<String>();
							actualLineCoverage = new HashMap<Integer, Integer>();
							actualTestSamplesData.add(methodTestCase);
							System.out.println("Generated test case: " + methodTestCase + " " + myAgent.getLocalName());
							COVERAGE_STATUS = "ERROR_UNDEFINED";
							isCoverageImproved = false;
							optimizedTestSamplesData = null;
							requestTemplate = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
							aclRequest = null;
							finish = 0;
							rep = null;
							Constants.readyAgents++;
							
			
							break;
							
							
						}
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

		private void execute(boolean defaultTestCases) throws Exception {
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
			checker = runTestSamples(defaultTestCases);
			if (checker) {
				targetInstance.run();
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
					actualCoverageData.put("instruction coverage", new Tuple2(
							cc.getInstructionCounter().getCoveredCount(), cc.getInstructionCounter().getTotalCount()));
					actualCoverageData.put("branch coverage",
							new Tuple2(cc.getBranchCounter().getCoveredCount(), cc.getBranchCounter().getTotalCount()));
					actualCoverageData.put("line coverage",
							new Tuple2(cc.getLineCounter().getCoveredCount(), cc.getLineCounter().getTotalCount()));
					actualCoverageData.put("method coverage",
							new Tuple2(cc.getMethodCounter().getCoveredCount(), cc.getMethodCounter().getTotalCount()));
					actualCoverageData.put("complexity coverage", new Tuple2(
							cc.getComplexityCounter().getCoveredCount(), cc.getComplexityCounter().getTotalCount()));

					for (int i = cc.getFirstLine(); i <= cc.getLastLine(); i++) {
						actualLineCoverage.put(Integer.valueOf(i), getNumberOfStatus(cc.getLine(i).getStatus()));
					}

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

		private boolean runTestSamples(boolean defaultTestCases) throws IllegalAccessException, IllegalArgumentException,
				InvocationTargetException, NoSuchMethodException, SecurityException, EvalError {

			Interpreter i = new Interpreter();
			try {
				i.set("targetClass", targetClass);
				i.set("targetInstance", targetInstance);
				if (defaultTestCases) {
					for (String testSample : actualTestSamplesData) {
						i.eval(testSample);	
						i.eval("targetInstance = (Runnable) targetClass.newInstance();");
						i.eval("targetInstance.run();");
					}
				} else {
					for (String testSample : optimizedTestSamplesData) {
						i.eval(testSample);	
						i.eval("targetInstance = (Runnable) targetClass.newInstance();");
						i.eval("targetInstance.run();");
					}
				}
				
			}catch (Exception ex) {
				targetClass = null;
				targetInstance = null;
				if (ex.getMessage()
						.contains("Method Invocation")  || ex.getMessage() .contains("Object constructor") ) {
					COVERAGE_STATUS = "CAN_NOT_HELP_TO_IMPROVE_COVERAGE";
					rep.setConversationId("MESSAGES_ID");
					rep.setContent(COVERAGE_STATUS);
					Constants.exceptions.append(
							"> SUTClass bug. "+ex.getLocalizedMessage()+"\n");
					Constants.exceptionCounter++;
					//ex.printStackTrace();
					myAgent.send(rep);
					return false; 
				
				}
				
				ex.printStackTrace();
				System.out.println(ex.getLocalizedMessage());
			}
			return true; 

		}

		private String checkLineCoverage(HashMap<Integer, Integer> actualM, HashMap<Integer, Integer> receivedM,
				String sender, Agent newThis) {

			for (Entry<Integer, Integer> received : receivedM.entrySet()) {
				for (Entry<Integer, Integer> actual : actualM.entrySet()) {
					if (received.getKey().equals(actual.getKey())) {
						if (received.getValue() != null && actual.getValue() != null) {
							if (received.getValue() < actual.getValue()) {
								System.out.println("------------------------------------------------");
								System.out.println(newThis.getAID().getLocalName() + " line analysis result:"
										+ "\n{One of the lines has better coverage. \nBetter coverage line is: ("
										+ sender + " " + received + ") < (" + actual + " " + newThis.getLocalName()
										+ ")}");
								System.out.println("------------------------------------------------");
								return "goodCoverage";
							}
						}
					}
				}
			}
			int o = 0;
			for (Entry<Integer, Integer> received : receivedM.entrySet()) {
				for (Entry<Integer, Integer> actual : actualM.entrySet()) {
					if (received.getKey().equals(actual.getKey())) {
						o++;
						if (received.getValue() != null && actual.getValue() != null && o != 1) {
							if (received.getValue().equals(actual.getValue())) {
								System.out.println("------------------------------------------------");
								System.out.println(newThis.getAID().getLocalName() + " line analysis result:"
										+ "\n{One of the lines has equal coverage. \nEqual coverage line is: ("
										+ sender + " " + received + ") = (" + actual + " " + newThis.getLocalName()
										+ ")}");
								System.out.println("------------------------------------------------");
								return "equalCoverage";
							}
						}
						
					}
				}
			}
			System.out.println("------------------------------------------------");
			return "badCoverage";
		}
	}

	private static void regAgent(Agent newThis, Property value, Boolean deregisterAgent) {
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(newThis.getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setName("defaultName");
		sd.setType("defaultType");
		sd.addProperties(value);
		dfd.addServices(sd);
		try {
			if (!deregisterAgent) {
				DFService.register(newThis, dfd);
			} else {
				// DFService.deregister(newThis);
				DFService.modify(newThis, dfd);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


	private static String testCaseGenerator(Agent newThis) {
		int methodIndex = 0;
		CtClass<?> cl = null;
		String result = "";
		try {
			boolean sameValue = false;
			if (Constants.sameValueCounter <= Constants.agentQuantity) {
				Constants.sameValueCounter++;
				sameValue = true;
			}
			ValueGenerator generator = new ValueGenerator();
			Launcher spoon = new Launcher();
			spoon.addInputResource("./src/");
			spoon.buildModel();
			Factory factory = spoon.getFactory();
			spoon = null;
			cl = factory.Class().get(Constants.SUTClass);
			factory = null;
			ArrayList allMethods = (ArrayList) cl.filterChildren(new TypeFilter<CtMethod>(CtMethod.class)).list();
			StringBuilder builder = new StringBuilder();
			methodIndex = Constants.methodIndex;
			Constants.methodIndex++;
			int currentMethodNumber = Constants.allMethodsOrdered.get(methodIndex);
			List<CtParameter<?>> allp = (List<CtParameter<?>>) ((CtMethod<?>) allMethods.get(currentMethodNumber))
					.getParameters();
			if (allp.size() != 0) {
				builder.append("targetClass.getMethod(\""
						+ ((CtMethod<?>) allMethods.get(currentMethodNumber)).getSimpleName() + "\", new Class[] {");

				int i = 0;
				for (CtParameter<?> par : allp) {
					i++;
					if (i != allp.size()) {
						builder.append(par.getType().getSimpleName() + ".class, ");
					} else {
						builder.append(
								par.getType().getSimpleName() + ".class}).invoke(targetInstance, new Object[] {");
					}
				}
				i = 0;

				boolean allEqual = false;
				CtExpression<?> ct = null;

				if (sameValue) {
					allEqual = true;
					for (CtParameter<?> ctp : allp) {
						if (!ctp.getType().equals(allp.get(0).getType()))
							allEqual = false;
					}
					if (allEqual) {
						ct = ValueGenerator.genRandVal(allp.get(0).getType(), 4);
					}
				}

				for (CtParameter<?> par : allp) {
					i++;
					if (i != allp.size()) {
						if (sameValue && allEqual) {
							if (par.getType().getSimpleName().toString().equals("Byte")) {
								builder.append("(byte)" + ct + ", ");
							} else if (par.getType().getSimpleName().toString().equals("Short")) {
								builder.append("(short)" + ct + ", ");
							} else {
								builder.append(ct + ", ");
							}
						} else if (par.getType().getSimpleName().toString().equals("Byte")) {
							builder.append("(byte)" + ValueGenerator.genRandVal(par.getType(), 4) + ", ");
						} else if (par.getType().getSimpleName().toString().equals("Short")) {
							builder.append("(short)" + ValueGenerator.genRandVal(par.getType(), 4) + ", ");
						}

						else {
							builder.append(ValueGenerator.genRandVal(par.getType(), 4) + ", ");
						}
					} else {
						if (sameValue && allEqual) {
							if(par.getType().getSimpleName().toString().equals("Byte")) {
								builder.append("(byte)" +ct + "});");
							} else if (par.getType().getSimpleName().toString().equals("Short")) {
								builder.append("(short)" +ct + "});");
							} else {
								builder.append(ct + "});");
							}
						} else if (par.getType().getSimpleName().toString().equals("Byte")) {
							builder.append("(byte)" + ValueGenerator.genRandVal(par.getType(), 4) + "});");
						} else if (par.getType().getSimpleName().toString().equals("Short")) {
							builder.append("(short)" + ValueGenerator.genRandVal(par.getType(), 4) + "});");
						}

						else {
							builder.append(ValueGenerator.genRandVal(par.getType(), 4) + "});");
						}
					}
				}
			} else {
				builder.append(
						"targetClass.getMethod(\"" + ((CtMethod<?>) allMethods.get(currentMethodNumber)).getSimpleName()
								+ "\", new Class[] {}).invoke(targetInstance, new Object[] {});");
				Constants.allMethodsOrdered.remove(methodIndex);
				Constants.methodIndex--;
				Constants.readyAgents--;
				regAgent(newThis, new Property("AgentStatus", "unnecessaryMethod"), true);
			}
			
			allMethods = null;
			generator = null;
			allp = null;
			result = builder.toString();
			int b = result.indexOf("<java");
			int e = result.indexOf(">()");
			int t = e-b;
			if (b != -1 && e != -1 && t > 0) {
				result = result.replace(result.substring(b, e + 1), "");
			}
			b = result.indexOf("t<");
			e = result.indexOf(">()");
			t = e-b;
			if (b != -1 && e != -1 && t > 0) {
			result = result.replace(result.substring(b+1, e+1), "");
			}
			
			b = result.indexOf("p<");
			e = result.indexOf(">()");
			t = e-b;
			if (b != -1 && e != -1 && t > 0) {
			result = result.replace(result.substring(b+1, e+1), "");
			}
			
			b = result.indexOf("e<");
			e = result.indexOf(">()");
			t = e-b;
			if (b != -1 && e != -1 && t > 0) {
			result = result.replace(result.substring(b+1, e+1), "");
			}
			
			b = result.indexOf("n<");
			e = result.indexOf(">()");
			t = e-b;
			if (b != -1 && e != -1 && t > 0) {
			result = result.replace(result.substring(b+1, e+1), "");
			}

			
		} catch (StackOverflowError error) {
			CtMethod<?> ctMethod = (CtMethod<?>) cl.filterChildren(new TypeFilter<CtMethod>(CtMethod.class)).list()
					.get(Constants.allMethodsOrdered.get(methodIndex));
			Constants.allMethodsOrdered.remove(methodIndex);
			Constants.methodIndex--;
			Constants.readyAgents--;
			regAgent(newThis, new Property("AgentStatus", "error"), true);
			Constants.exceptions.append("> \"" + ctMethod.getSimpleName() + "\" method is unsupported. \n");
			Constants.exceptionCounter++;
			result = "";
		} catch (SpoonException exception) {
			CtMethod<?> ctMethod = (CtMethod<?>) cl.filterChildren(new TypeFilter<CtMethod>(CtMethod.class)).list()
					.get(Constants.allMethodsOrdered.get(methodIndex));
			Constants.allMethodsOrdered.remove(methodIndex);
			Constants.methodIndex--;
			Constants.readyAgents--;
			regAgent(newThis, new Property("AgentStatus", "error"), true);
			Constants.exceptions.append("> \"" + ctMethod.getSimpleName() + "\" method is unsupported. \n");
			Constants.exceptionCounter++;
			result = "";
		}  catch (Exception e) {
			Constants.exceptionCounter++;
			e.printStackTrace();
		}
		return result;
	}

}
