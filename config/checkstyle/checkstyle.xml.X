<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE module PUBLIC "-//Puppy Crawl//DTD Check Configuration 1.3//EN" "http://www.puppycrawl.com/dtds/configuration_1_3.dtd">

<!--
    This configuration file was written by the eclipse-cs plugin configuration editor
-->
<!--
    Checkstyle-Configuration: Beilers.com
    Description: none
-->
<module name="Checker">
  <property name="severity" value="error"/>
  <module name="TreeWalker">
    <module name="ConstantName"/>
    <module name="LocalFinalVariableName"/>
    <module name="LocalVariableName"/>
    <module name="MemberName"/>
    <module name="MethodName"/>
    <module name="PackageName"/>
    <module name="ParameterName"/>
    <module name="StaticVariableName"/>
    <module name="TypeName"/>
    <module name="AvoidStarImport"/>
    <module name="IllegalImport"/>
    <module name="RedundantImport"/>
    <module name="UnusedImports"/>
    <module name="LineLength">
      <property name="ignorePattern" value="^import|^package"/>
      <property name="max" value="130"/>
    </module>
    <module name="MethodLength"/>
    <module name="ParameterNumber"/>
    <module name="EmptyForIteratorPad"/>
    <module name="MethodParamPad"/>
    <module name="NoWhitespaceAfter">
      <property name="tokens" value="ARRAY_INIT,BNOT,DEC,DOT,INC,LNOT,UNARY_MINUS,UNARY_PLUS"/>
    </module>
    <module name="NoWhitespaceBefore"/>
    <module name="OperatorWrap"/>
    <module name="ParenPad"/>
    <module name="TypecastParenPad"/>
    <module name="WhitespaceAfter"/>
    <module name="WhitespaceAround">
      <property name="tokens" value="ASSIGN,BAND,BAND_ASSIGN,BOR,BOR_ASSIGN,BSR,BSR_ASSIGN,BXOR,BXOR_ASSIGN,COLON,DIV,DIV_ASSIGN,EQUAL,GE,GT,LAND,LCURLY,LE,LITERAL_ASSERT,LITERAL_CATCH,LITERAL_DO,LITERAL_ELSE,LITERAL_FINALLY,LITERAL_FOR,LITERAL_IF,LITERAL_RETURN,LITERAL_SYNCHRONIZED,LITERAL_TRY,LITERAL_WHILE,LOR,LT,MINUS,MINUS_ASSIGN,MOD,MOD_ASSIGN,NOT_EQUAL,PLUS,PLUS_ASSIGN,QUESTION,SL,SLIST,SL_ASSIGN,SR,SR_ASSIGN,STAR,STAR_ASSIGN,LITERAL_ASSERT,TYPE_EXTENSION_AND"/>
    </module>
    <module name="ModifierOrder"/>
    <module name="RedundantModifier"/>
    <module name="AvoidNestedBlocks"/>
    <module name="EmptyBlock"/>
    <module name="LeftCurly">
      <property name="tokens" value="CLASS_DEF, CTOR_DEF, INTERFACE_DEF, LITERAL_CATCH                                             , LITERAL_DO, LITERAL_ELSE, LITERAL_FINALLY, LITERAL_FOR, LITERAL_IF, LITERAL_SWITCH, LITERAL_SYNCHRONIZED, LITERAL_TRY, LITERAL_WHILE, METHOD_DEF"/>
    </module>
    <module name="NeedBraces"/>
    <module name="AvoidInlineConditionals"/>
    <module name="DoubleCheckedLocking"/>
    <module name="EmptyStatement"/>
    <module name="EqualsHashCode"/>
    <module name="HiddenField">
      <property name="severity" value="error"/>
      <property name="ignoreConstructorParameter" value="true"/>
      <property name="ignoreSetter" value="true"/>
      <property name="ignoreAbstractMethods" value="true"/>
    </module>
    <module name="IllegalInstantiation"/>
    <module name="InnerAssignment"/>
    <module name="MagicNumber"/>
    <module name="MissingSwitchDefault"/>
    <module name="RedundantThrows"/>
    <module name="SimplifyBooleanExpression"/>
    <module name="SimplifyBooleanReturn"/>
    <module name="FinalClass"/>
    <module name="HideUtilityClassConstructor"/>
    <module name="InterfaceIsType"/>
    <module name="VisibilityModifier">
      <property name="packageAllowed" value="true"/>
      <property name="protectedAllowed" value="true"/>
    </module>
    <module name="ArrayTypeStyle"/>
    <module name="FinalParameters"/>
    <module name="TodoComment"/>
    <module name="UpperEll"/>
    <module name="ExecutableStatementCount">
      <property name="max" value="50"/>
      <property name="tokens" value="INSTANCE_INIT,STATIC_INIT,METHOD_DEF,CTOR_DEF"/>
    </module>
    <module name="UnnecessaryParentheses"/>
    <module name="DeclarationOrder"/>
    <module name="AbstractClassName"/>
    <module name="NoWhitespaceAfter">
      <property name="tokens" value="ARRAY_INIT,BNOT,DEC,DOT,INC,LNOT,UNARY_MINUS,UNARY_PLUS"/>
    </module>
    <module name="RightCurly">
      <property name="option" value="alone"/>
    </module>
    <module name="ClassDataAbstractionCoupling"/>
    <module name="MissingOverride">
      <property name="javaFiveCompatibility" value="true"/>
    </module>
    <module name="ClassTypeParameterName"/>
    <module name="MethodTypeParameterName"/>
    <module name="DefaultComesLast"/>
    <module name="ReturnCount"/>
    <module name="CyclomaticComplexity"/>
    <module name="Regexp">
      <property name="id" value="JULCheck"/>
      <property name="format" value="^.*Logger.getLogger\(.*$"/>
      <property name="message" value="Use Simple Logging Facade 4 Java - Not JUL"/>
      <property name="illegalPattern" value="true"/>
    </module>
    <module name="Regexp">
      <property name="id" value="CLCheck"/>
      <property name="format" value="^.*LogFactory.getLog\(.*$"/>
      <property name="message" value="Use Simple Logging Facade 4 Java - Not commons logging"/>
      <property name="illegalPattern" value="true"/>
    </module>
  </module>
  <module name="NewlineAtEndOfFile"/>
  <module name="Translation"/>
  <module name="FileLength"/>
</module>
