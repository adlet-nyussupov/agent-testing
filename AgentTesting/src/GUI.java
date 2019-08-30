import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.NumberFormatter;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;
import spoon.Launcher;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.filter.TypeFilter;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JFormattedTextField;

public class GUI {
	private JFrame frame;
	private static ContainerController cc;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
 
	public GUI() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setOpacity(1.0f);
		frame.getContentPane().setForeground(SystemColor.inactiveCaptionBorder);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		Constants.stopTestingButton = new JButton("Stop agent testing");
		Constants.stopTestingButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		Constants.stopTestingButton.setEnabled(false);
		Constants.stopTestingButton.setBounds(368, 527, 198, 23);
		frame.getContentPane().add(Constants.stopTestingButton);		
		Constants.startTestingButton = new JButton("Start agent testing");
		Constants.startTestingButton.setEnabled(false);
		Constants.startTestingButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		Constants.startTestingButton.setBounds(586, 527, 198, 23);
		frame.getContentPane().add(Constants.startTestingButton);		
		Constants.startTestingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {			
					if (check(Constants.startTestingButton)) {
						Constants.startTime = new Date().getTime();
						cc = null;
						System.out.println("Loading JADE environment...");
						Constants.startTestingButton.setEnabled(false);
						Constants.makeDefault();
						Launcher spoon = new Launcher();
						spoon.addInputResource("./src/");
						spoon.buildModel();
						Factory factory = spoon.getFactory();
						CtClass<?> cl = factory.Class().get(Constants.SUTClass); 
						ArrayList allMethods = (ArrayList) cl.filterChildren(new TypeFilter<CtMethod>(CtMethod.class))
								.list();
						if(allMethods.size() > 0) {
						for (Object obj : allMethods) {
							CtClass parentClass = ((CtMethod) obj).getParent(CtClass.class);
							if (parentClass.getQualifiedName().equals(Constants.SUTClass.getName())) {
								Constants.allMethodsOrdered.add(allMethods.indexOf(obj));
							//	System.out.println(parentClass.getQualifiedName() + " " + Constants.SUTClass.getName());
							}
						}
						Constants.agentQuantity = Constants.allMethodsOrdered.size();
						Runtime rt = Runtime.instance();
						// rt.instance().setCloseVM(true);								
						try {
							cc = rt.createMainContainer(new ProfileImpl());
							Constants.stopTestingButton.setEnabled(true);
							for (int i = 1; i <= Constants.allMethodsOrdered.size(); ++i) {
								AgentController agentN = cc.createNewAgent("Agent" + i, "AgentN", null);
								agentN.start();
							}
							AgentController mainAgent = cc.createNewAgent("MainAgent", "MainAgent", null);
							mainAgent.start();
						} catch (ControllerException e1) {
							e1.printStackTrace();
						}		
					} else {
						Constants.exceptions.append("> Error in the SUT class. Could not find an external class files that are specified in the SUT class."
								+ " Please put them into the same folder as the SUT class.\n");
						Constants.exceptionCounter++;
						System.out.println("------------------------------------------------");
						System.out.println("Errors: "+Constants.exceptionCounter+".");
						System.out.println(Constants.exceptions.toString());
						
					}
					} else {
						Constants.startTestingButton.setEnabled(false);
						System.out.println(
								"Please set all necessary options: required coverage percentage, testing rounds quantity and SUT class");
					}		
			}
		});

		Constants.stopTestingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (cc != null) {
						Constants.finishMainAgent = 1;
						Constants.isError = true;
						Constants.stopTestingButton.setEnabled(false);
						Constants.startTestingButton.setEnabled(true);
						Constants.stopTime = new Date().getTime();
						long elapsedTime = Constants.stopTime - Constants.startTime;
						System.out.println(elapsedTime*0.001F);
						if (cc.getPlatformController() != null) {
							cc.getPlatformController().kill();
						}
						cc = null;
					}
				} catch (ControllerException e1) {
					e1.printStackTrace();
				}
			}
		});
		

		JLabel mainLabel = new JLabel("Multi-Agent Testing Application");
		mainLabel.setBounds(183, 6, 434, 25);
		frame.getContentPane().add(mainLabel);
		mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainLabel.setVerticalAlignment(SwingConstants.TOP);
		mainLabel.setFont(new Font("Arial", Font.BOLD, 18));
		
		JLabel sutClassLabel = new JLabel("SUT class:");
		sutClassLabel.setBounds(10, 27, 80, 50);
		sutClassLabel.setFont(new Font("Arial", Font.BOLD, 15));
		sutClassLabel.setHorizontalAlignment(SwingConstants.LEFT);
		frame.getContentPane().add(sutClassLabel);

		JTextPane sutClassTextPane = new JTextPane();
		sutClassTextPane.setToolTipText("Select system under test (SUT) class.");
		sutClassTextPane.setBounds(95, 42, 573, 20);
		sutClassTextPane.setEditable(false);
		sutClassTextPane.setBackground(SystemColor.inactiveCaptionBorder);
		
		JButton sutClassSelectButton = new JButton("Select");
		sutClassSelectButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		sutClassSelectButton.setBounds(696, 42, 89, 20);
		frame.getContentPane().add(sutClassSelectButton);

		sutClassSelectButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setName("Open Class File");
				FileNameExtensionFilter filter = new FileNameExtensionFilter(".class", "class");
				fileChooser.setFileFilter(filter);
				fileChooser.setAcceptAllFileFilterUsed(false);
				File chDir = new File("C:\\Users\\Adlet\\Google Диск\\Assignments\\EXPERIMENT\\SUTClasses");
				fileChooser.setCurrentDirectory(chDir);
				int returnVal = fileChooser.showOpenDialog(frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					Constants.manualTestCases.clear();
					System.out.println("Your SUT class: " + fileChooser.getSelectedFile());
					File fileDir = new File(fileChooser.getSelectedFile().getParent().toString());
					Constants.targetFile = new File(fileChooser.getSelectedFile().toString());
					URL urlDir = null;
					try {
						urlDir = fileDir.toURI().toURL();
					} catch (MalformedURLException e1) {

						e1.printStackTrace();
					}
					URL[] urls = new URL[] { urlDir };

					URLClassLoader cl = new URLClassLoader(urls);

					try {
						Constants.SUTClass = cl
								.loadClass(fileChooser.getSelectedFile().getName().replace(".class", ""));

					} catch (NoClassDefFoundError err) {
						System.out.print(
								"Class file was not compiled correctly. Make sure that package information was deleted from SUT class, check that external classes are located in the same folder as the SUT class. Also check this error: \""
										+ err.getLocalizedMessage() + "\"");
						Constants.SUTClass = null;
					}

					catch (ClassNotFoundException e1) {
						Constants.SUTClass = null;
					}

					if (Constants.SUTClass != null) {

						Constants.files = fileDir.listFiles(new FilenameFilter() {
							public boolean accept(File dir, String name) {
								return name.toLowerCase().endsWith(".class");
							}
						});

						Launcher spoon = new Launcher();
						spoon.addInputResource("./src/");
						spoon.buildModel();
						Factory factory = spoon.getFactory();
						CtClass<?> tClass = factory.Class().get(Constants.SUTClass);
						for (CtTypeReference<?> typeRef : tClass.getReferencedTypes()) {
							if (!typeRef.toString().startsWith("java") && !typeRef.isPrimitive() && !typeRef.toString().equals(tClass.getSimpleName()) && !typeRef.toString().contains(tClass.getSimpleName()+".") && typeRef.isShadow()) {
								Constants.externalClasses.add(typeRef.toString() + ".class");
								System.out.println("External class:"+typeRef.toString());
							}
						}
						sutClassTextPane.removeAll();
						sutClassTextPane.setText(fileChooser.getSelectedFile().toString());
					}
					check(Constants.startTestingButton);
				}
			}
		});

		JTextPane manualTestCasesTextPane = new JTextPane();
		manualTestCasesTextPane.setToolTipText("Enter additional manual test cases. Example: \"targetClass.getMethod(\"method_name\", new Class[] {input_type_1, input_type_2, ...}).invoke(targetInstance, new Object[] {input_value_1, input_value_2, ...});\"");
		manualTestCasesTextPane.setBackground(SystemColor.inactiveCaptionBorder);
		manualTestCasesTextPane.setBounds(10, 160, 658, 116);

		JLabel roundsQuantityLabel = new JLabel("Testing rounds quantity:");
		roundsQuantityLabel.setBounds(10, 73, 179, 36);
		roundsQuantityLabel.setHorizontalAlignment(SwingConstants.LEFT);
		roundsQuantityLabel.setFont(new Font("Arial", Font.BOLD, 15));
		frame.getContentPane().add(roundsQuantityLabel);
		
		JLabel manualTestCasesLabel = new JLabel("Additional manual test cases:");
		manualTestCasesLabel.setBounds(10, 120, 252, 25);
		manualTestCasesLabel.setHorizontalAlignment(SwingConstants.LEFT);
		manualTestCasesLabel.setFont(new Font("Arial", Font.BOLD, 15));
		frame.getContentPane().add(manualTestCasesLabel);
		
		JLabel outputLabel = new JLabel("Output:");
		outputLabel.setBounds(10, 306, 67, 30);
		outputLabel.setHorizontalAlignment(SwingConstants.LEFT);
		outputLabel.setFont(new Font("Arial", Font.BOLD, 15));
		frame.getContentPane().add(outputLabel);

		JLabel requiredCoveragePercentageLabel = new JLabel("Required coverage percentage:");
		requiredCoveragePercentageLabel.setBounds(10, 271, 228, 43);
		requiredCoveragePercentageLabel.setHorizontalAlignment(SwingConstants.LEFT);
		requiredCoveragePercentageLabel.setFont(new Font("Arial", Font.BOLD, 15));
		frame.getContentPane().add(requiredCoveragePercentageLabel);
	
		JButton manualTestCasesButton = new JButton("Add");
		manualTestCasesButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		manualTestCasesButton.setBounds(695, 145, 89, 20);

		manualTestCasesButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(manualTestCasesTextPane.getText().contains("targetClass") && manualTestCasesTextPane.getText().contains("targetInstance") && manualTestCasesTextPane.getText().contains(";") ) {
					Constants.manualTestCases.clear();
					//txtpnTestCaseExample.setText(txtpnTestCaseExample.getText().replaceAll("\\s+", ""));
					String text = manualTestCasesTextPane.getText().replace("\n", "").replace("\r", "");
					text = text.replace(";", ";\n");
					manualTestCasesTextPane.setText(text);
					String[] manualText = manualTestCasesTextPane.getText().split("\n");
					Constants.manualTestCases.addAll(Arrays.asList(manualText));
					System.out.println("Manual test cases were added.");
				} else{
					System.out.println("Manual test cases were not added. Enter the test cases according to the template: targetClass.getMethod(\"method_name\", new Class[] {input_type_1, input_type_2, ...}).invoke(targetInstance, new Object[] {input_value_1, input_value_2, ...});");
					//Constants.manualTestCases.add("targetClass targetInstance sdsd;");				
				}	
				check(Constants.startTestingButton);
			}
		});
		frame.getContentPane().add(manualTestCasesButton);
	
		JTextArea outputTextArea = new JTextArea();
		outputTextArea.setWrapStyleWord(true);
		outputTextArea.setTabSize(0);
		outputTextArea.setEditable(false);
		outputTextArea.setLineWrap(true);
		outputTextArea.setBackground(SystemColor.inactiveCaptionBorder);
		outputTextArea.setBounds(10, 344, 761, 172);
		
		PrintStream outputPrintStream = new PrintStream(new OutputStream() {
			@Override
			public void write(int b) throws IOException {
				outputTextArea.append(String.valueOf((char) b));
				outputTextArea.setCaretPosition(outputTextArea.getDocument().getLength());
			}
		});
		
		//System.setOut(outputPrintStream);
		//System.setErr(outputPrintStream);
		
		JScrollPane outputScrollPane = new JScrollPane(outputTextArea);
		outputScrollPane.setBounds(10, 335, 775, 181);
		frame.getContentPane().add(outputScrollPane);
		
		JScrollPane manualTCScrollPane = new JScrollPane();
		manualTCScrollPane.setToolTipText("");
		manualTCScrollPane.setBounds(10, 145, 675, 124);
		manualTCScrollPane.setViewportView(manualTestCasesTextPane);
		frame.getContentPane().add(manualTCScrollPane);
		
		JScrollPane sutClassScrollPane = new JScrollPane();
		sutClassScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		sutClassScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		sutClassScrollPane.setBounds(88, 42, 597, 20);
		sutClassScrollPane.setViewportView(sutClassTextPane);
		frame.getContentPane().add(sutClassScrollPane);
		
		NumberFormat numFormat = NumberFormat.getInstance();
	    NumberFormatter coveragePercNumFormatter = new NumberFormatter(numFormat);
	    coveragePercNumFormatter.setMinimum(0);
	    coveragePercNumFormatter.setMaximum(100);
	    coveragePercNumFormatter.setAllowsInvalid(false);
	    coveragePercNumFormatter.setOverwriteMode(true);
	    coveragePercNumFormatter.setCommitsOnValidEdit(false);    

	    NumberFormatter roundsNumFormatter = new NumberFormatter(numFormat);
		    roundsNumFormatter.setMinimum(0);
		    roundsNumFormatter.setMaximum(1000);
		    roundsNumFormatter.setAllowsInvalid(false);
		    roundsNumFormatter.setOverwriteMode(true);
		    roundsNumFormatter.setCommitsOnValidEdit(false);
	    
		
		JFormattedTextField coveragePercTextField = new JFormattedTextField(coveragePercNumFormatter);
		coveragePercTextField.setToolTipText("Enter the required coverage percentage for the SUT class. Agents will make attempt to reach this coverage.");

		coveragePercTextField.setBounds(244, 283, 54, 20);
		frame.getContentPane().add(coveragePercTextField);
		
		JButton coveragePercButton = new JButton("Set");
		coveragePercButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		coveragePercButton.setBounds(308, 283, 89, 20);
		
		coveragePercButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(coveragePercTextField.getText().isEmpty() == false && !coveragePercTextField.getText().startsWith("0")) {
					Constants.requiredCoveragePercentage = Float.parseFloat( coveragePercTextField.getText());
					System.out.println("Required coverage percentage was set to "+Constants.requiredCoveragePercentage+"%."); 
					check(Constants.startTestingButton);
				} else {
					System.out.println("Zero coverage is not allowed. Required coverage percentage was not set. "); 
				}
			}
		});
		
		frame.getContentPane().add(coveragePercButton);
		
		JFormattedTextField roundsTextField = new JFormattedTextField(roundsNumFormatter);
		roundsTextField.setToolTipText("Enter the amount of the testing rounds (repetitions). Every round generates random test inputs for given methods of the SUT class.");
		roundsTextField.setBounds(194, 82, 54, 20);
		frame.getContentPane().add(roundsTextField);
		
		JButton setExampleButton = new JButton("Set example");
		setExampleButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		setExampleButton.setBounds(10, 527, 117, 23);
		
		setExampleButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			Constants.SUTClass = SUTClass.class;
			Constants.targetFile = new File("./src/SUTClass");
			sutClassTextPane.setText(Constants.SUTClass.getName());
			coveragePercTextField.setText("100");
			Constants.requiredCoveragePercentage = 100.0f;	
			roundsTextField.setText("9");
			Constants.roundsQuantity = 9;
			check(Constants.startTestingButton);		
			System.out.println("Now you can start testing.");
			}	
		});
		
		frame.getContentPane().add(setExampleButton);	
		JButton roundsButton = new JButton("Set");
		roundsButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		roundsButton.setBounds(258, 82, 89, 20);
		frame.getContentPane().add(roundsButton);
		
		roundsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (roundsTextField.getText().isEmpty() == false && !roundsTextField.getText().startsWith("0")) {
					Constants.roundsQuantity = Integer.parseInt(roundsTextField.getText());
					System.out.println("Testing rounds quantity was set to " + Constants.roundsQuantity + ".");
					check(Constants.startTestingButton);
				} else {
					System.out.println("Zero round is not allowed. Testing rounds quantity was not set.");
				}
			}
		});
	}

	private static boolean check(JButton button) {
		if(Constants.roundsQuantity!= 0 && Constants.requiredCoveragePercentage != 0 && Constants.SUTClass != null) {
			button.setEnabled(true);
			return true;
		}
		return false;
	}
}
