import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import bsh.EvalError;
import bsh.Interpreter;
import spoon.Launcher;
import spoon.SpoonModelBuilder.InputType;
import spoon.pattern.Generator;
import spoon.pattern.Pattern;
import spoon.pattern.PatternBuilder;
import spoon.pattern.PatternBuilderHelper;
import spoon.pattern.internal.DefaultGenerator;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtTypeAccess;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtExecutable;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtParameter;
import spoon.reflect.declaration.CtType;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.CtExecutableReference;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.Filter;
import spoon.reflect.visitor.chain.CtQueryable;
import spoon.reflect.visitor.filter.TypeFilter;
import spoon.support.util.ImmutableMap;

public class Test {
	
	public static void main(String[] args){
		
		/*
		
		 {
			
			CtMethod<?> ctMethod = (CtMethod<?>) classB.filterChildren(new TypeFilter<CtMethod>(CtMethod.class)).list().get(0);
			ValueGenerator creator = new ValueGenerator();		
			
			
		//	System.out.println(creator.generateRandomValue(ctMethod.getParameters().get(0).getType(), 2));
		//	System.out.println(creator.generateRandomValue(ctMethod.getParameters().get(0).getType()));
			
			java.util.Collections.singletonList("tx}vs`/!Z41Tw+bp1oZs");
		//	System.out.println(java.util.Collections.singletonList("tx}vs`/!Z41Tw+bp1oZs"));
				
			
		String[] g = new java.lang.String []{ " !X&>]aL_WU@zr>n#;#Be" };
		//System.out.println(g);
		Interpreter i = new Interpreter();  // Construct an interpreter
		//i.set("foo", 5);                    // Set variables
		//i.set("date", new Date() ); 

	//	Date date = (Date)i.get("date");    // retrieve a variable

		// Eval a statement and get the result
		try {
			i.set("g", g);
		//	i.eval("System.out.println(g);");
			
			
			
		} catch (EvalError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}             
		
		System.out.println(  );

		// Source an external script file
	
	Class d =	String[].class;
		
		*/
		ValueGenerator creator = new ValueGenerator();	
		
		
		Constants.SUTClass = SUTClass.class;
		Launcher spoon = new Launcher();
		spoon.addInputResource("./src/");
		spoon.buildModel();
		Factory factory = spoon.getFactory();
		CtClass<?> classB = factory.Class().get("SUTClass");
		for(CtTypeReference<?> t : classB.getReferencedTypes()) {
			
			if(!t.toString().startsWith("java") && !t.isPrimitive()) {
				System.out.println(t.toString());
				}
			
		}
		
		
		//ArrayList allMethods = (ArrayList) classB.filterChildren(new TypeFilter<CtMethod>(CtMethod.class)).filterChildren(new TypeFilter<CtClass>(CtClass.class)).list();
		//CtMethod<?> ctMethod = (CtMethod<?>) classB.filterChildren(new TypeFilter<CtMethod>(CtMethod.class)).list().get(6);
		//creator.generateRandomValue( ctMethod.getParameters().get(0).getType(), 4);
		//System.out.println( creator.generateRandomValue( ctMethod.getParameters().get(0).getType(), 4) );
		
		
		//CtClass zeclass = ctMethod.getParent(CtClass.class); 
		//CtClass parentClass = ((CtMethod) allMethods.get(3)).getParent(CtClass.class);
		//System.out.println(parentClass.getQualifiedName() +Constants.SUTClass.getName());
		//if (parentClass.getQualifiedName() != Constants.SUTClass.getName()) {
		//	System.out.println("!!!!");
		//}
		

		//System.out.println(zeclass.getQualifiedName() +Constants.SUTClass.getName());
		/*
		ctMethod.getParameters().get(0).getClass();
			
		
		String agent1Example = "targetClass.getMethod(\" " + ctMethod.getSimpleName() + "\", "
				+ ctMethod.getParameters().get(0).getType() + ".class, "
				+ ctMethod.getParameters().get(1).getType() + ".class).invoke(targetInstance," + creator.generateRandomValue(ctMethod.getParameters().get(0).getType(), 5) + ", "+creator.generateRandomValue(ctMethod.getParameters().get(1).getType(), 4)+");";
		agent1Example = agent1Example.replaceAll("\\s+", "");
		agent1Example = agent1Example.replaceAll(";", ";\n");
		*/
		//targetClass.getMethod("go").invoke(targetInstance);
		
		//System.out.println(agent1Example);
		/*
		ArrayList l =   (ArrayList) classB.filterChildren(new TypeFilter<CtMethod>(CtMethod.class)).list();
		
		ArrayList<String> generatedTestSamples = new ArrayList<String>();
		ValueGenerator creator = new ValueGenerator();	
//		i.eval("targetClass.getMethod(\"f\",  new Class[] {String[].class}).invoke(targetInstance, new Object[] {new java.lang.String[]{ \"M}@wKQ?*Z.!I3#FY2=tQ\", \")g;SD$}a]#@zGv2au#a^\" }});");
//      		targetClass.getMethod("f", new Class[] {String[].class}).invoke(targetInstance, new Object[] {new java.lang.String[]{ "^yTj7.kxDFg]v}{4(cs?", "4,g-l,zwj0IdsC[|!tj ", "AYKD1%8?jN(pAbZA8]gs" }});		
		for(Object o : l) {
			StringBuilder builder = new StringBuilder();
			
			List<CtParameter<?>> allp = (List<CtParameter<?>>) ((CtMethod<?>)o).getParameters();
			
		
			if(allp.size() != 0) {
				builder.append("targetClass.getMethod(\""+((CtMethod<?>)o).getSimpleName()+ "\", new Class[] {");

				int i = 0;
			for(CtParameter<?> par : allp) {
				i++;
				if(i != allp.size()) {
					builder.append(par.getType().getSimpleName()+".class, ");
				} else {
					builder.append(par.getType().getSimpleName()+".class}).invoke(targetInstance, new Object[] {");
				}
				
				//System.out.println(par);
			}
			
			i = 0;
			for(CtParameter<?> par : allp) {
				i++;
				if(i != allp.size()) {
					builder.append(creator.generateRandomValue(par.getType(), 3) + ", ");
				} else {
					builder.append(creator.generateRandomValue(par.getType(), 4)+"});");
				}
			}
	
			
			} else {
				builder.append("targetClass.getMethod(\""+((CtMethod<?>)o).getSimpleName()+ "\").invoke(targetInstance);");
			}
			
			System.out.println(builder);
			generatedTestSamples.add(builder.toString());
			
		}
		//System.out.println(generatedTestSamples);	
		//System.out.println(l);	
		//System.out.println("!!!!!!!!!!!! "+ctMethod.getParameters().get(0).getType().box());
		 //System.out.println(creator.generateRandomValue(ctMethod.getParameters().get(0).getType()));
	}
	
*/
	
	}
}
