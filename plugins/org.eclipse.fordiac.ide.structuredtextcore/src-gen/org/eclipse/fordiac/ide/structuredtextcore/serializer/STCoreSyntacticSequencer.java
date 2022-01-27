/*
 * generated by Xtext 2.25.0
 */
package org.eclipse.fordiac.ide.structuredtextcore.serializer;

import com.google.inject.Inject;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.fordiac.ide.structuredtextcore.services.STCoreGrammarAccess;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AbstractElementAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AlternativeAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.TokenAlias;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynNavigable;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynTransition;
import org.eclipse.xtext.serializer.sequencer.AbstractSyntacticSequencer;

@SuppressWarnings("all")
public class STCoreSyntacticSequencer extends AbstractSyntacticSequencer {

	protected STCoreGrammarAccess grammarAccess;
	protected AbstractElementAlias match_BOOL_LITERAL_BOOLKeyword_1_q;
	protected AbstractElementAlias match_STAtomicExpression_LeftParenthesisKeyword_0_0_a;
	protected AbstractElementAlias match_STAtomicExpression_LeftParenthesisKeyword_0_0_p;
	protected AbstractElementAlias match_STRING_LITERAL___CHARKeyword_0_2_or_WCHARKeyword_0_3_or_WSTRINGKeyword_0_1__q;
	
	@Inject
	protected void init(IGrammarAccess access) {
		grammarAccess = (STCoreGrammarAccess) access;
		match_BOOL_LITERAL_BOOLKeyword_1_q = new TokenAlias(false, true, grammarAccess.getBOOL_LITERALAccess().getBOOLKeyword_1());
		match_STAtomicExpression_LeftParenthesisKeyword_0_0_a = new TokenAlias(true, true, grammarAccess.getSTAtomicExpressionAccess().getLeftParenthesisKeyword_0_0());
		match_STAtomicExpression_LeftParenthesisKeyword_0_0_p = new TokenAlias(true, false, grammarAccess.getSTAtomicExpressionAccess().getLeftParenthesisKeyword_0_0());
		match_STRING_LITERAL___CHARKeyword_0_2_or_WCHARKeyword_0_3_or_WSTRINGKeyword_0_1__q = new AlternativeAlias(false, true, new TokenAlias(false, false, grammarAccess.getSTRING_LITERALAccess().getCHARKeyword_0_2()), new TokenAlias(false, false, grammarAccess.getSTRING_LITERALAccess().getWCHARKeyword_0_3()), new TokenAlias(false, false, grammarAccess.getSTRING_LITERALAccess().getWSTRINGKeyword_0_1()));
	}
	
	@Override
	protected String getUnassignedRuleCallToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		return "";
	}
	
	
	@Override
	protected void emitUnassignedTokens(EObject semanticObject, ISynTransition transition, INode fromNode, INode toNode) {
		if (transition.getAmbiguousSyntaxes().isEmpty()) return;
		List<INode> transitionNodes = collectNodes(fromNode, toNode);
		for (AbstractElementAlias syntax : transition.getAmbiguousSyntaxes()) {
			List<INode> syntaxNodes = getNodesFor(transitionNodes, syntax);
			if (match_BOOL_LITERAL_BOOLKeyword_1_q.equals(syntax))
				emit_BOOL_LITERAL_BOOLKeyword_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_STAtomicExpression_LeftParenthesisKeyword_0_0_a.equals(syntax))
				emit_STAtomicExpression_LeftParenthesisKeyword_0_0_a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_STAtomicExpression_LeftParenthesisKeyword_0_0_p.equals(syntax))
				emit_STAtomicExpression_LeftParenthesisKeyword_0_0_p(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_STRING_LITERAL___CHARKeyword_0_2_or_WCHARKeyword_0_3_or_WSTRINGKeyword_0_1__q.equals(syntax))
				emit_STRING_LITERAL___CHARKeyword_0_2_or_WCHARKeyword_0_3_or_WSTRINGKeyword_0_1__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else acceptNodes(getLastNavigableState(), syntaxNodes);
		}
	}

	/**
	 * Ambiguous syntax:
	 *     'BOOL#'?
	 *
	 * This ambiguous syntax occurs at:
	 *     (rule start) (ambiguity) keyWordValue=BOOL_VALUES
	 *     not='NOT' (ambiguity) keyWordValue=BOOL_VALUES
	 */
	protected void emit_BOOL_LITERAL_BOOLKeyword_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     '('*
	 *
	 * This ambiguous syntax occurs at:
	 *     (rule start) (ambiguity) boolLiteral=BOOL_LITERAL
	 *     (rule start) (ambiguity) dateLiteral=DATE_LITERAL
	 *     (rule start) (ambiguity) numericLiteral=NUMERIC_LITERAL
	 *     (rule start) (ambiguity) op=UnaryOperator
	 *     (rule start) (ambiguity) stringLiteral=STRING_LITERAL
	 *     (rule start) (ambiguity) symbol=[VarDeclaration|ID]
	 *     (rule start) (ambiguity) timeLiteral=DATE_AND_TIME_LITERAL
	 *     (rule start) (ambiguity) timeLiteral=TIME_LITERAL
	 *     (rule start) (ambiguity) timeOfDayLiteral=TIME_OF_DAY_LITERAL
	 *     (rule start) (ambiguity) type=[DataType|ID]
	 *     (rule start) (ambiguity) {STBinaryExpression.left=}
	 *     (rule start) (ambiguity) {STMemberSelection.receiver=}
	 */
	protected void emit_STAtomicExpression_LeftParenthesisKeyword_0_0_a(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     '('+
	 *
	 * This ambiguous syntax occurs at:
	 *     (rule start) (ambiguity) boolLiteral=BOOL_LITERAL
	 *     (rule start) (ambiguity) dateLiteral=DATE_LITERAL
	 *     (rule start) (ambiguity) numericLiteral=NUMERIC_LITERAL
	 *     (rule start) (ambiguity) op=UnaryOperator
	 *     (rule start) (ambiguity) stringLiteral=STRING_LITERAL
	 *     (rule start) (ambiguity) timeLiteral=DATE_AND_TIME_LITERAL
	 *     (rule start) (ambiguity) timeLiteral=TIME_LITERAL
	 *     (rule start) (ambiguity) timeOfDayLiteral=TIME_OF_DAY_LITERAL
	 *     (rule start) (ambiguity) {STBinaryExpression.left=}
	 *     (rule start) (ambiguity) {STMemberSelection.receiver=}
	 */
	protected void emit_STAtomicExpression_LeftParenthesisKeyword_0_0_p(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     ('WSTRING#' | 'CHAR#' | 'WCHAR#')?
	 *
	 * This ambiguous syntax occurs at:
	 *     (rule start) (ambiguity) value=STRING
	 */
	protected void emit_STRING_LITERAL___CHARKeyword_0_2_or_WCHARKeyword_0_3_or_WSTRINGKeyword_0_1__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
}
