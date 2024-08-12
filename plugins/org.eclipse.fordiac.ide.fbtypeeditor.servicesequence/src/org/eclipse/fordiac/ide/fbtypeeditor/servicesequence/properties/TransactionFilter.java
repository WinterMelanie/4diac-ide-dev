/*******************************************************************************
 * Copyright (c) 2014 fortiss GmbH, 2022 Felix Roithmayr
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   Monika Wenger
 *     - initial API and implementation and/or initial documentation
 *   Felix Roithmayr
 *     - Update to more generic model filtering
 *******************************************************************************/
package org.eclipse.fordiac.ide.fbtypeeditor.servicesequence.properties;

import org.eclipse.fordiac.ide.model.libraryElement.Primitive;
import org.eclipse.fordiac.ide.model.libraryElement.ServiceTransaction;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.IFilter;

public class TransactionFilter implements IFilter {

	@Override
	public boolean select(Object toTest) {
		if (toTest instanceof EditPart) {
			toTest = ((EditPart) toTest).getModel();
		}

		return (toTest instanceof ServiceTransaction || toTest instanceof Primitive);
	}

}
