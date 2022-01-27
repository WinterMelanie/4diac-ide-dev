/**
 * generated by Xtext 2.25.0
 */
package org.eclipse.fordiac.ide.structuredtextcore.sTCore;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>ST Repeat Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.fordiac.ide.structuredtextcore.sTCore.STRepeatStatement#getCondition <em>Condition</em>}</li>
 * </ul>
 *
 * @see org.eclipse.fordiac.ide.structuredtextcore.sTCore.STCorePackage#getSTRepeatStatement()
 * @model
 * @generated
 */
public interface STRepeatStatement extends STLoopStatement
{
  /**
   * Returns the value of the '<em><b>Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Condition</em>' containment reference.
   * @see #setCondition(STExpression)
   * @see org.eclipse.fordiac.ide.structuredtextcore.sTCore.STCorePackage#getSTRepeatStatement_Condition()
   * @model containment="true"
   * @generated
   */
  STExpression getCondition();

  /**
   * Sets the value of the '{@link org.eclipse.fordiac.ide.structuredtextcore.sTCore.STRepeatStatement#getCondition <em>Condition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Condition</em>' containment reference.
   * @see #getCondition()
   * @generated
   */
  void setCondition(STExpression value);

} // STRepeatStatement
