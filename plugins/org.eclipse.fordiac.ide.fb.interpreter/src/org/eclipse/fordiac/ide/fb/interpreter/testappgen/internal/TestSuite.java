/*******************************************************************************
 * Copyright (c) 2023 Johannes Kepler University Linz
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Melanie Winter - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.fordiac.ide.fb.interpreter.testappgen.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.fordiac.ide.model.NameRepository;
import org.eclipse.fordiac.ide.model.libraryElement.FBType;
import org.eclipse.fordiac.ide.model.libraryElement.Service;
import org.eclipse.fordiac.ide.model.libraryElement.ServiceSequence;

public class TestSuite {
	private Service dataSource;
	private List<TestCase> testCases = new ArrayList<TestCase>();
	
	TestSuite(FBType type) {
		if (type.getService() == null || type.getService().getServiceSequence().isEmpty()) {
			throw new IllegalArgumentException("Test suites must be defined as service model");
		}
		dataSource = type.getService();
		
	}
	
	public TestSuite(List<ServiceSequence> sequences) {
		for (ServiceSequence serviceSequence : sequences) {
			testCases.add(TestCase.createTestCase(serviceSequence));
		}
	}
	
	public TestCase getTestCaseByName(String name) {
		return null;
		
	}
	
	public static TestSuite createTestSuite(FBType type) {
		TestSuite testSuite = new TestSuite(type);
		for (ServiceSequence serviceSequence : type.getService().getServiceSequence()) {
			testSuite.testCases.add(TestCase.createTestCase(serviceSequence));
		}
		return testSuite;
	}

	public List<TestCase> getTestCases() {
		return testCases;
	}
}
