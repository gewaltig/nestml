/*
 * Copyright (c) 2015 RWTH Aachen. All rights reserved.
 *
 * http://www.se-rwth.de/
 */
package org.nest.nestml._cocos;

import de.se_rwth.commons.SourcePosition;
import org.nest.ode._cocos.DerivativeOrderAtLeastOne;
import org.nest.ode._cocos.EquationsOnlyForStateVariables;
import org.nest.utils.AstUtils;

/**
 * Factory for CoCo error strings. The dispatch is done by the static type of the context condition object.
 *
 * @author plotnikov, traeder
 */
public class NestmlErrorStrings {
  private static NestmlErrorStrings instance = new NestmlErrorStrings();
  private NestmlErrorStrings() {
  }

  public static NestmlErrorStrings getInstance() {
    return instance;
  }

  static String message(final AliasHasOneVar coco, final SourcePosition sourcePosition) {
    final String ERROR_MESSAGE_FORMAT = "'alias' declarations must only declare exactly one variable.";
    return code(coco) + " " + AstUtils.print(sourcePosition) + ": " + ERROR_MESSAGE_FORMAT;
  }

  @SuppressWarnings("unused") // parameter is used for dispatch
  static String code(final AliasHasOneVar coco) {
    return "NESTML_ALIAS_HAS_ONE_VAR";
  }

  static String message(final VectorVariableInNonVectorDeclaration coco, final String usedAlias, final SourcePosition sourcePosition) {
    final String ERROR_MSG_FORMAT = "A vector '" + usedAlias + "' cannot be used as part of an initial expression of " +
                                    "non-vector variable declaration.";
    return code(coco) + " " + AstUtils.print(sourcePosition) + ": " + ERROR_MSG_FORMAT;
  }

  @SuppressWarnings("unused") // parameter is used for dispatch
  static String code(final VectorVariableInNonVectorDeclaration coco) {
    return "NESTML_ALIAS_IN_NON_ALIAS_DECL";
  }

  static String message(final ComponentHasNoDynamics coco, final String name, final SourcePosition sourcePosition) {
    final String ERROR_MSG_FORMAT = "Component " + name + " doesn't have dynamics function.";
    return code(coco) + " " + AstUtils.print(sourcePosition) + ": " + ERROR_MSG_FORMAT;

  }

  @SuppressWarnings("unused") // parameter is used for dispatch
  static String code(final ComponentHasNoDynamics coco) {
    return "NESTML_COMPONENT_HAS_NO_DYNAMICS";
  }

  static String message(final ComponentWithoutInput coco, final String componentName, final SourcePosition sourcePosition) {
    final String ERROR_MSG_FORMAT = "Problem with the component: " + componentName +
                                    ". Components cannot have any inputs, since they are not elements of a "
                                    + "neuronal network, but serve as a part of a neuron declaration.";
    return code(coco) + " " + AstUtils.print(sourcePosition) + ": " + ERROR_MSG_FORMAT;
  }

  @SuppressWarnings("unused") // parameter is used for dispatch
  static String code(final ComponentWithoutInput coco) {
    return "NESTML_COMPONENT_WITHOUT_INPUT";
  }

  static String message(final ComponentWithoutOutput coco, final String componentName, final SourcePosition sourcePosition) {
    final String ERROR_MSG_FORMAT = "Problem with the component: " + componentName +
                                    ". Components cannot have any output, since they are not elements of a "
                                    + "neuronal network, but serve as a part of a neuron declaration.";
    return code(coco) + " " + AstUtils.print(sourcePosition) + ": " + ERROR_MSG_FORMAT;
  }

  @SuppressWarnings("unused") // parameter is used for dispatch
  static String code(final ComponentWithoutOutput coco) {
    return "NESTML_COMPONENT_WITHOUT_OUTPUT";
  }

  static String message(final FunctionParameterHasTypeName coco, final String variable, SourcePosition sourcePosition){
    final String ERROR_MSG_FORMAT = "The function parameter '%s' has name of an existing NESTML type.";

    return code(coco) + " " + AstUtils.print(sourcePosition) + ": " + String.format(ERROR_MSG_FORMAT, variable);
  }

  @SuppressWarnings({"unused"}) // used for the routing
  public static String code(final FunctionParameterHasTypeName coco) {
    return "NESTML_FUNCTION_PARAMETER_HAS_TYPE_NAME";
  }

  String getErrorMsg(UnitDeclarationOnlyOnesAllowed coco){
    return UnitDeclarationOnlyOnesAllowed.ERROR_CODE + ": Literals in Unit types may only be \"1\" (one) ";
  }

    String getErrorMsg(AliasHasDefiningExpression coco) {
    return AliasHasDefiningExpression.ERROR_CODE + ":" + "'alias' must be defined through an expression.";
  }

  String getErrorMsg(AliasHasNoSetter coco, String aliasVar, String varTypeName) {
    return AliasHasNoSetter.ERROR_CODE + ":" + "Alias-variable '" + aliasVar + "' needs a setter-function: set_"
        + aliasVar + "(v " + varTypeName + ")";
  }



  String getErrorMsgInvariantMustBeBoolean(InvalidTypeOfInvariant coco, String expressionType) {
    return InvalidTypeOfInvariant.ERROR_CODE + ":" + "The type of the invariant expression must be boolean and not: " +
           expressionType;
  }

  String getErrorMsgCannotComputeType(InvalidTypeOfInvariant coco, String invariantType) {
    return InvalidTypeOfInvariant.ERROR_CODE + ":" + "Cannot compute the type: " + invariantType;
  }

  String getErrorMsg(BufferNotAssignable coco, String bufferName) {
    return BufferNotAssignable.ERROR_CODE + ":" + "Buffer '" + bufferName + "' cannot be reassigned.";
  }

  String getErrorMsgWrongReturnType(FunctionReturnsIncorrectValue coco,
                                           String functionName, String functionReturnTypeName) {
    return FunctionReturnsIncorrectValue.ERROR_CODE + ":" + "Function '" + functionName + "' must return a result of type "
           + functionReturnTypeName + ".";
  }

  String getErrorMsgCannotConvertReturnValue(FunctionReturnsIncorrectValue coco,
                                                    String expressionTypeName, String functionReturnTypeName) {
    return FunctionReturnsIncorrectValue.ERROR_CODE + ":" + "Cannot convert from " + expressionTypeName
           + " (type of return expression) to " + functionReturnTypeName
           + " (return type).";
  }

  String getErrorMsgCannotDetermineExpressionType(FunctionReturnsIncorrectValue coco) {
    return FunctionReturnsIncorrectValue.ERROR_CODE + ":" + "Cannot determine the type of the expression";
  }

  String getErrorMsg(CurrentPortIsInhOrExc coco) {
    return CurrentPortIsInhOrExc.ERROR_CODE + ":" + "Current input can neither be inhibitory nor excitatory.";
  }

  public String getErrorMsgAssignToNonState(
      final EquationsOnlyForStateVariables coco,
      final String variableName) {
    return EquationsOnlyForStateVariables.ERROR_CODE + ":" + "The variable '" + variableName + "' is not a state"
        + " variable and, therefore, cannot be used on the left side of an equation.";
  }

  public String getErrorMsgVariableNotDefined(EquationsOnlyForStateVariables coco, final String variableName) {
    return EquationsOnlyForStateVariables.ERROR_CODE + ":" + "The variable " + variableName + " used as left-hand side " +
           "of the ode is not defined.";
  }

  String getErrorMsg(MissingReturnStatementInFunction coco, String functionName, String returnType) {
    return MissingReturnStatementInFunction.ERROR_CODE + ":" + "Function '" + functionName
           + "' must return a result of type '"
           + returnType;
  }

  String getErrorMsgGet_InstanceDefined(GetterSetterFunctionNames coco) {
    return GetterSetterFunctionNames.ERROR_CODE + ":" + "The function 'get_instance' is going to be generated. Please use another name.";
  }

  String getErrorMsgGeneratedFunctionDefined(GetterSetterFunctionNames coco,
                                                    String functionName, String variableName) {
    return GetterSetterFunctionNames.ERROR_CODE + ":" + "The function '" + functionName + "' is going to be generated, since"
        + " there is a variable called '" + variableName + "'.";
  }

  String getErrorMsg(SumHasCorrectParameter coco, String expression) {
    return SumHasCorrectParameter.ERROR_CODE + ":" + "The arguments of the I_sum must be atomic expressions: "
           + "e.g. V_m and not : " + expression;
  }

  String getErrorMsg(InvalidTypesInDeclaration coco, String typeName) {
    return InvalidTypesInDeclaration.ERROR_CODE + ":" + "The type " + typeName + " is a neuron/component. No neurons/components allowed " +
        "in this place. Use the use-statement.";
  }

  String getErrorMsg(MemberVariableDefinedMultipleTimes coco, String varName,
                            int line, int column) {
    return MemberVariableDefinedMultipleTimes.ERROR_CODE + ":" + "Variable '" + varName + "' defined previously defined in line: "
        + line + ":" + column;
  }

  String getErrorMsgDeclaredInIncorrectOrder(MemberVariablesInitialisedInCorrectOrder coco,
                                                    String varName, String declaredName) {
    return MemberVariablesInitialisedInCorrectOrder.ERROR_CODE + ":" + "Variable '"
        + varName
        + "' must be declared before it can be used in declaration of '"
        + declaredName + "'.";
  }

  String getErrorMsgVariableNotDefined(MemberVariablesInitialisedInCorrectOrder coco,
                                              String pos, String varName) {
    return MemberVariablesInitialisedInCorrectOrder.ERROR_CODE + ":" + pos + ": Variable '" +
        varName + "' is undefined.";
  }

  String getErrorMsgNeuronHasNoSymbol(MultipleFunctionDeclarations coco, String neuronName) {
    return MultipleFunctionDeclarations.ERROR_CODE + ":" + "The neuron symbol: " + neuronName + " has no symbol.";
  }

  String getErrorMsgParameterDefinedMultipleTimes(MultipleFunctionDeclarations coco, String funname) {
    return MultipleFunctionDeclarations.ERROR_CODE + ":" + "The function '" + funname
        + " parameter(s) is defined multiple times.";
  }

  String getErrorMsgNoScopePresent(MultipleFunctionDeclarations coco) {
    return MultipleFunctionDeclarations.ERROR_CODE + ":" + "Run symbol table creator.";
  }

  String getErrorMsgMultipleInhibitory(MultipleInhExcInput coco) {
    return MultipleInhExcInput.ERROR_CODE + ":" + "Multiple occurrences of the keyword 'inhibitory' are not allowed.";
  }

  String getErrorMsgMultipleExcitatory(MultipleInhExcInput coco) {
    return MultipleInhExcInput.ERROR_CODE + ":" + "Multiple occurrences of the keyword 'excitatory' are not allowed.";
  }

  String getErrorMsg(MultipleOutputs coco, int numOutput) {
    return MultipleOutputs.ERROR_CODE + ":" + "Neurons have at most one output and not " + numOutput + ".";
  }

  String getErrorMsg(NESTFunctionNameChecker coco, String funName) {
    return NESTFunctionNameChecker.ERROR_CODE + ":" + "The function-name '" + funName
        + "' is already used by NEST. Please use another name.";
  }

  String getErrorMsgDynamicsNotPresent(NeuronNeedsDynamics coco) {
    return NeuronNeedsDynamics.ERROR_CODE + ":" + "Neurons need at least one dynamics function.";
  }

  String getErrorMsgMultipleDynamics(NeuronNeedsDynamics coco) {
    return NeuronNeedsDynamics.ERROR_CODE + ":" + "Neurons need at most one dynamics function.";
  }

  String getErrorMsg(NeuronWithoutInput coco) {
    return NeuronWithoutInput.ERROR_CODE + ":" + "Neurons need some inputs.";
  }

  String getErrorMsg(NeuronWithoutOutput coco) {
    return NeuronWithoutOutput.ERROR_CODE + ":" + "Neurons need some outputs.";
  }

  String getErrorMsg(TypeIsDeclaredMultipleTimes coco, String typeName) {
    return TypeIsDeclaredMultipleTimes.ERROR_CODE + ":" + "The type '" + typeName + "' is defined multiple times.";
  }

  String getErrorMsgOnlyComponentsForNeurons(UsesOnlyComponents coco, String typeName,
                                                    String predefinedTypeName) {
    return UsesOnlyComponents.ERROR_CODE + ":" + "Only components can be used by neurons/components and not " + typeName + " of the type: " +
        predefinedTypeName + " .";
  }

  String getErrorMsgOnlyComponentsForComponents(UsesOnlyComponents coco, String typeName) {
    return UsesOnlyComponents.ERROR_CODE + ":" + "Only components can be used by components and not " + typeName + " that is a neuron, not a "
        + "component";
  }


  public String getErrorMsg(final DerivativeOrderAtLeastOne coco, final String variableName) {

    return DerivativeOrderAtLeastOne.ERROR_CODE + ":" + "The variable on the righthandside of an equation must be derivative variable, e.g. " + variableName + "'";
  }

  public String getErrorMsg(AssignmentToAlias assignmentToAlias, final String variableName) {
    return AssignmentToAlias.ERROR_CODE + ":" + "You cannot assign a value to an alias: " + variableName;
  }
}
