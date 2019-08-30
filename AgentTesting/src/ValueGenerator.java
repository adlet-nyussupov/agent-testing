import spoon.SpoonException;
import spoon.reflect.code.CtConstructorCall;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.code.CtLiteral;
import spoon.reflect.code.CtLocalVariable;
import spoon.reflect.code.CtNewArray;
import spoon.reflect.code.CtTypeAccess;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtConstructor;
import spoon.reflect.declaration.CtInterface;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtParameter;
import spoon.reflect.declaration.CtType;
import spoon.reflect.declaration.ModifierKind;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.CtArrayTypeReference;
import spoon.reflect.reference.CtExecutableReference;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.reference.CtWildcardReference;
import spoon.reflect.visitor.filter.TypeFilter;
import spoon.support.SpoonClassNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.sun.istack.internal.Nullable;

public class ValueGenerator {

	private static Random randomValue;
	private static final int SIZE_OF_ARRAY = 4;
	private static Map<String, Boolean> allValueIsSupportedForType = new HashMap<>();
	private static List<String> checkingValueName = new ArrayList<>();

	public ValueGenerator() {
		randomValue = new Random();
		randomValue.nextDouble();
	}

	public static CtExpression<?> genRandVal(CtTypeReference typeOfValue, int mixOfValue,
			CtExpression<?>... avoidExpr) {
		if (isPrim(typeOfValue)) {
			return genPrimRandVal(typeOfValue);
		} else {
			try {
				if (isArr(typeOfValue)) {
					return genArr(typeOfValue);
				} else if (typeOfValue.getActualClass() == String.class) {
					return typeOfValue.getFactory().createLiteral(getRandString(20));
				} else if (typeOfValue.getActualClass() == Collection.class
						|| typeOfValue.getActualClass() == List.class) {
					return genColl(typeOfValue, "List", List.class);
				} else if (typeOfValue.getActualClass() == Set.class) {
					return genColl(typeOfValue, "Set", Set.class);
				} else if (typeOfValue.getActualClass() == Map.class) {
					return genColl(typeOfValue, "Map", Map.class);
				}
			} catch (SpoonException exception) {
				return genConstr(typeOfValue, mixOfValue, avoidExpr);
			}
		}
		return genConstr(typeOfValue, mixOfValue, avoidExpr);
	}

	public static boolean isPrim(CtTypeReference typeOfValue) {
		try {
			return typeOfValue.unbox().isPrimitive();
		} catch (SpoonException e) {
			return false;
		}
	}

	public static boolean isArr(CtTypeReference typeOfValue) {
		return typeOfValue instanceof CtArrayTypeReference;
	}

	public static char getRandChar() {
		int value = getRand().nextInt(94) + 32;
		char c = (char) ((value == 34 || value == 39 || value == 92) ? value + (getRand().nextBoolean() ? 1 : -1)
				: value);
		return c;
	}

	public static Random getRand() {
		return randomValue;
	}

	public static String getRandString(int len) {
		return IntStream.range(0, len).map(i -> getRandChar())
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
	}

	private static CtExpression<?> genPrimRandVal(CtTypeReference typeOfValue) {
		if (typeOfValue.getActualClass() == Boolean.class || typeOfValue.getActualClass() == boolean.class) {
			return typeOfValue.getFactory().createLiteral(getRand().nextBoolean());
		}
		if (typeOfValue.getActualClass() == Character.class || typeOfValue.getActualClass() == char.class) {
			return typeOfValue.getFactory().createLiteral(getRandChar());
		}
		if (typeOfValue.getActualClass() == Byte.class || typeOfValue.getActualClass() == byte.class) {
			return typeOfValue.getFactory().createLiteral((byte) getRand().nextInt());
		}
		if (typeOfValue.getActualClass() == Short.class || typeOfValue.getActualClass() == short.class) {
			return typeOfValue.getFactory().createLiteral((short) getRand().nextInt());
		}
		if (typeOfValue.getActualClass() == Integer.class || typeOfValue.getActualClass() == int.class) {
			return typeOfValue.getFactory().createLiteral((getRand().nextInt()));
		}
		if (typeOfValue.getActualClass() == Long.class || typeOfValue.getActualClass() == long.class) {
			return typeOfValue.getFactory().createLiteral((long) getRand().nextInt());
		}
		if (typeOfValue.getActualClass() == Float.class || typeOfValue.getActualClass() == float.class) {
			return typeOfValue.getFactory().createLiteral((float) getRand().nextDouble());
		}
		if (typeOfValue.getActualClass() == Double.class || typeOfValue.getActualClass() == double.class) {
			return typeOfValue.getFactory().createLiteral(getRand().nextDouble());
		}
		throw new RuntimeException();
	}

	private static CtExpression genArr(CtTypeReference typeOfValue) {
		CtArrayTypeReference typeOfArr = (CtArrayTypeReference) typeOfValue;
		CtTypeReference typeOfComp = typeOfArr.getComponentType();
		CtNewArray<?> newArr = typeOfValue.getFactory().createNewArray();
		final int size = getRand().nextInt(SIZE_OF_ARRAY);
		newArr.setType(typeOfArr);
		if (size == 0) {
			newArr.setDimensionExpressions(Collections.singletonList(typeOfValue.getFactory().createLiteral(size)));
		} else if (canGenSValForType(typeOfComp)) {
			IntStream.range(0, size).mapToObj(i -> genRandVal(typeOfComp, 0)).forEach(newArr::addElement);
		}
		return newArr;
	}

	public static boolean canGenSValForType(CtTypeReference typeOfValue) {
		boolean res;
		if (allValueIsSupportedForType.containsKey(typeOfValue.getQualifiedName())) {
			return allValueIsSupportedForType.get(typeOfValue.getQualifiedName());
		}
		if (checkingValueName.contains(typeOfValue.getQualifiedName())) {
			return true;
		}
		checkingValueName.add(typeOfValue.getQualifiedName());
		try {
			if (typeOfValue instanceof CtWildcardReference) {
				res = false;
			} else if (isPrim(typeOfValue)) {
				res = true;
			} else {
				try {
					if (isArr(typeOfValue) || typeOfValue.isSubtypeOf(typeOfValue.getFactory().Class().STRING)
							|| typeOfValue.isSubtypeOf(typeOfValue.getFactory().Class().COLLECTION)
							|| typeOfValue.isSubtypeOf(typeOfValue.getFactory().Class().LIST)
							|| typeOfValue.isSubtypeOf(typeOfValue.getFactory().Class().SET)
							|| typeOfValue.isSubtypeOf(typeOfValue.getFactory().Class().MAP)) {
						res = true;
					} else {
						res = canGenConstr(typeOfValue);
					}
				} catch (SpoonClassNotFoundException exception) {
					res = canGenConstr(typeOfValue);
				}
			}
		} catch (Exception e) {
			res = false;
		}
		allValueIsSupportedForType.put(typeOfValue.getQualifiedName(), res);
		checkingValueName.remove(typeOfValue.getQualifiedName());
		return res;
	}

	private static boolean canGenConstr(CtTypeReference typeOfValue) {
		CtType<?> declarType = typeOfValue.getDeclaration() == null ? typeOfValue.getTypeDeclaration()
				: typeOfValue.getDeclaration();

		if (declarType == null) {
			return false;
		}
		final boolean canBeConstructed = (!declarType
				.getElements(new TypeFilter<CtConstructor<?>>(CtConstructor.class) {
					@Override
					public boolean matches(CtConstructor<?> element) {
						return element.hasModifier(ModifierKind.PUBLIC) && element.getParameters().stream()
								.map(CtParameter::getType).filter(reference -> !reference.equals(typeOfValue))
								.allMatch(ValueGenerator::canGenSValForType);
					}
				}).isEmpty())
				|| !(typeOfValue.getFactory().getModel().getElements(new METHOD_FILTER(typeOfValue)).isEmpty());

		return (typeOfValue.getActualTypeArguments().isEmpty()
				|| !typeOfValue.getActualTypeArguments().isEmpty() && typeOfValue.getActualTypeArguments().stream()
						.noneMatch(reference -> reference instanceof CtWildcardReference))
				&& !typeOfValue.getModifiers().contains(ModifierKind.ABSTRACT) && canBeConstructed;
	}

	static final class METHOD_FILTER extends TypeFilter<CtMethod<?>> {
		private final CtTypeReference typeOfValue;

		public METHOD_FILTER(CtTypeReference typeOfValue) {
			super(CtMethod.class);
			this.typeOfValue = typeOfValue;
		}

	}

	static CtExpression genConstr(CtTypeReference typeOfValue, int mixOfValue, CtExpression<?>... avoidExpr) {
		CtType<?> declarType = typeOfValue.getDeclaration() == null ? typeOfValue.getTypeDeclaration()
				: typeOfValue.getDeclaration();
		if (declarType != null && declarType instanceof CtClass<?>) {
			final List<CtConstructor<?>> constr = ((CtClass<?>) declarType).getConstructors().stream()
					.filter(ctConstructor -> ctConstructor.hasModifier(ModifierKind.PUBLIC)
							&& ctConstructor.getParameters().stream().map(CtParameter::getType)
									.allMatch(ValueGenerator::canGenSValForType))
					.collect(Collectors.toList());
			if (!constr.isEmpty()) {
				CtConstructorCall<?> callConstr = typeOfValue.getFactory().createConstructorCall();
				callConstr.setType(typeOfValue);
				final CtConstructor<?> selectedConstructor;
				if (mixOfValue > 3) {
					Collections.sort(constr, new ComparToGenParams());
					selectedConstructor = constr.get(0);
				} else {
					selectedConstructor = constr.get(getRand().nextInt(constr.size()));
				}
				selectedConstructor.getParameters().forEach(parameter -> callConstr
						.addArgument(ValueGenerator.genRandVal(parameter.getType(), mixOfValue + 1)));
				return callConstr;
			} else {
				return buildConstr(typeOfValue, avoidExpr);
			}
		} else {
			return buildConstr(typeOfValue, avoidExpr);
		}
	}

	@Nullable
	private static CtExpression buildConstr(CtTypeReference typeOfValue, CtExpression<?>[] avoidExpr) {
		final List<CtExpression<?>> constrFactMethod = genConstrWithFactory(typeOfValue);
		if (!constrFactMethod.isEmpty()) {
			CtExpression<?> selConstr = constrFactMethod.remove(getRand().nextInt(constrFactMethod.size()));
			while (!constrFactMethod.isEmpty() && Arrays.stream(avoidExpr).anyMatch(selConstr::equals)) {
				selConstr = constrFactMethod.remove(getRand().nextInt(constrFactMethod.size()));
			}
			final CtTypeReference<?> declaringType = ((CtInvocation<?>) selConstr).getExecutable().getDeclaringType();
			((CtInvocation<?>) selConstr).setTarget(selConstr.getFactory().createTypeAccess(declaringType));
			return selConstr;
		}
		return typeOfValue.getFactory().createLiteral(null);
	}

	static List<CtExpression<?>> genConstrWithFactory(final CtTypeReference typeOfValue) {
		final Factory factory = typeOfValue.getFactory();
		final CtTypeReference<?> buildRef;
		if (typeOfValue.getDeclaration() instanceof CtInterface<?>) {
			final Optional<CtClass<?>> firstModel = factory.getModel()
					.getElements(new TypeFilter<CtClass<?>>(CtClass.class) {
						@Override
						public boolean matches(CtClass<?> element) {
							return element.getSuperInterfaces().contains(typeOfValue.getDeclaration());
						}
					}).stream().findFirst();
			if (!firstModel.isPresent()) {
				return Collections.emptyList();
			} else {
				buildRef = firstModel.get().getReference();
			}
		} else {
			buildRef = typeOfValue;
		}
		final List<CtMethod<?>> methodOfFactory = factory.getModel().getElements(new METHOD_FILTER(buildRef));
		return methodOfFactory.stream()
				.map(method -> factory.createInvocation(
						factory.createTypeAccess(method.getParent(CtType.class).getReference(), true),
						method.getReference(),
						method.getParameters().stream()
								.map(parameter -> ValueGenerator.genRandVal(parameter.getType(), 0))
								.collect(Collectors.toList())))
				.collect(Collectors.toList());
	}

	static CtExpression<?> genColl(CtTypeReference typeOfValue, String methodName, Class<?> collType) {
		if (typeOfValue.getActualTypeArguments().stream()
				.anyMatch(reference -> reference instanceof CtWildcardReference) || getRand().nextBoolean()) {
			return genEmptyColl(typeOfValue, "empty" + methodName, collType);
		} else {
			return genSTList(typeOfValue, "singleton" + ("Set".equals(methodName) ? "" : methodName), collType);
		}
	}

	static CtExpression<?> genEmptyColl(CtTypeReference typeOfValue, String methodName, Class<?> typeOfColl) {
		final Factory factory = typeOfValue.getFactory();
		final CtType<?> collType = factory.Type().get(Collections.class);
		final CtTypeAccess<?> collAccess = factory.createTypeAccess(collType.getReference());
		final CtMethod<?> stListMethod = collType.getMethodsByName(methodName).get(0);
		final CtExecutableReference<?> execRef = factory.Core().createExecutableReference();
		execRef.setStatic(true);
		execRef.setSimpleName(stListMethod.getSimpleName());
		execRef.setDeclaringType(collType.getReference());
		execRef.setType(factory.createCtTypeReference(typeOfColl));
		if (typeOfValue.getActualTypeArguments().isEmpty()) {
			execRef.addActualTypeArgument(typeOfValue);
		} else if (typeOfValue.getActualTypeArguments().stream()
				.noneMatch(reference -> reference instanceof CtWildcardReference)) {

			execRef.setActualTypeArguments(typeOfValue.getActualTypeArguments());
		}
		return factory.createInvocation(collAccess, execRef);
	}

	@SuppressWarnings("unchecked")
	static CtExpression<?> genSTList(CtTypeReference typeOfValue, String methodName, Class<?> typeOfColl) {
		final Factory factory = typeOfValue.getFactory();
		final CtType<?> collType = factory.Type().get(Collections.class);
		final CtTypeAccess<?> collAccess = factory.createTypeAccess(collType.getReference());
		final CtMethod<?> stListMethod = collType.getMethodsByName(methodName).get(0);
		final CtExecutableReference execRef = factory.Core().createExecutableReference();
		execRef.setStatic(true);
		execRef.setSimpleName(stListMethod.getSimpleName());
		execRef.setDeclaringType(collType.getReference());
		execRef.setType(factory.createCtTypeReference(typeOfColl));
		if (!typeOfValue.getActualTypeArguments().isEmpty()
				&& typeOfValue.getActualTypeArguments().stream().allMatch(ValueGenerator::canGenSValForType)) {
			execRef.setParameters(typeOfValue.getActualTypeArguments());
			List<CtExpression<?>> par = typeOfValue.getActualTypeArguments().stream()
					.map(reference -> ValueGenerator.genRandVal(reference, 0)).collect(Collectors.toList());
			return factory.createInvocation(collAccess, execRef, par);
		} else {
			return factory.createInvocation(collAccess, execRef,
					factory.createConstructorCall(factory.Type().createReference(Object.class)));
		}
	}

	private static class ComparToGenParams implements Comparator<CtConstructor<?>> {
		@Override
		public int compare(CtConstructor<?> firstConstr, CtConstructor<?> secondConstr) {
			return this.costToGenerateParameters(firstConstr.getParameters())
					- this.costToGenerateParameters(secondConstr.getParameters());
		}

		private int costToGenerateParameters(List<CtParameter<?>> par) {
			return par.stream().mapToInt(this::costOfOneParameter).sum();
		}

		private int costOfOneParameter(CtParameter<?> param) {
			final CtTypeReference<?> type = param.getType();
			if (isPrim(type)) {
				return 0;
			} else {
				try {
					if (isArr(type)) {
						return 1;
					} else if (type.getActualClass() == String.class) {
						return 0;
					} else if (type.getActualClass() == Collection.class || type.getActualClass() == List.class) {
						return 3;
					} else if (type.getActualClass() == Set.class) {
						return 3;
					} else if (type.getActualClass() == Map.class) {
						return 3;
					}
				} catch (SpoonException exc) {
					return 4;
				}
			}
			return 4;
		}
	}

}