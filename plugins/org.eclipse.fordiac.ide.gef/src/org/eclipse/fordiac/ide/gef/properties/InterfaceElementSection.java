/*******************************************************************************
 * Copyright (c) 2017 fortiss GmbH 
 * 				 2019 Johannes Kepler Unviersity
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Monika Wenger, Alois Zoitl
 *     - initial API and implementation and/or initial documentation
 *   Alois Zoitl - fixed sub-app type update, code clean-up  
 *******************************************************************************/
package org.eclipse.fordiac.ide.gef.properties;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.fordiac.ide.gef.DiagramEditorWithFlyoutPalette;
import org.eclipse.fordiac.ide.gef.editparts.InterfaceEditPart;
import org.eclipse.fordiac.ide.model.Palette.AdapterTypePaletteEntry;
import org.eclipse.fordiac.ide.model.Palette.Palette;
import org.eclipse.fordiac.ide.model.Palette.PaletteEntry;
import org.eclipse.fordiac.ide.model.Palette.PaletteGroup;
import org.eclipse.fordiac.ide.model.commands.change.ChangeCommentCommand;
import org.eclipse.fordiac.ide.model.commands.change.ChangeSubAppIENameCommand;
import org.eclipse.fordiac.ide.model.commands.change.ChangeTypeCommand;
import org.eclipse.fordiac.ide.model.commands.change.ChangeValueCommand;
import org.eclipse.fordiac.ide.model.data.DataType;
import org.eclipse.fordiac.ide.model.libraryElement.AdapterDeclaration;
import org.eclipse.fordiac.ide.model.libraryElement.Event;
import org.eclipse.fordiac.ide.model.libraryElement.IInterfaceElement;
import org.eclipse.fordiac.ide.model.libraryElement.SubApp;
import org.eclipse.fordiac.ide.model.libraryElement.VarDeclaration;
import org.eclipse.fordiac.ide.model.typelibrary.DataTypeLibrary;
import org.eclipse.fordiac.ide.model.typelibrary.EventTypeLibrary;
import org.eclipse.fordiac.ide.model.typelibrary.TypeLibrary;
import org.eclipse.fordiac.ide.util.IdentifierVerifyListener;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

public class InterfaceElementSection extends AbstractSection {
	protected Text nameText;
	protected Text commentText;
	protected Combo typeCombo;
	protected Text parameterText;
	protected CLabel valueCLabel;

	@Override
	public void createControls(final Composite parent, final TabbedPropertySheetPage tabbedPropertySheetPage) {
		createSuperControls = false;
		super.createControls(parent, tabbedPropertySheetPage);
		parent.setLayout(new GridLayout(1, true));
		parent.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
		createTypeAndCommentSection(parent);
	}

	protected void createTypeAndCommentSection(Composite parent) {
		Composite composite = getWidgetFactory().createComposite(parent);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, 0, true, false));
		getWidgetFactory().createCLabel(composite, "Name:");
		nameText = createGroupText(composite, true);
		nameText.addVerifyListener(new IdentifierVerifyListener());
		nameText.addModifyListener(e -> {
			removeContentAdapter();
			executeCommand(new ChangeSubAppIENameCommand(getType(), nameText.getText()));
			addContentAdapter();
		});

		getWidgetFactory().createCLabel(composite, "Comment:");
		commentText = createGroupText(composite, true);
		commentText.addModifyListener(e -> {
			removeContentAdapter();
			executeCommand(new ChangeCommentCommand(getType(), commentText.getText()));
			addContentAdapter();
		});
		getWidgetFactory().createCLabel(composite, "Type: ");
		typeCombo = new Combo(composite, SWT.SINGLE | SWT.READ_ONLY);
		typeCombo.addListener(SWT.Selection, event -> {
			Command cmd = null;
			if (getType() instanceof AdapterDeclaration) {
				DataType newType = getTypeForSelection(typeCombo.getText());
				cmd = newChangeTypeCommand((VarDeclaration) getType(), newType);
			} else {
				if (getType() instanceof VarDeclaration) {
					cmd = newChangeTypeCommand((VarDeclaration) getType(),
							DataTypeLibrary.getInstance().getType(typeCombo.getText()));
				}
			}
			executeCommand(cmd);
		});
		valueCLabel = getWidgetFactory().createCLabel(composite, "Value:");
		parameterText = createGroupText(composite, true);
		parameterText.addModifyListener(e -> {
			removeContentAdapter();
			executeCommand(new ChangeValueCommand((VarDeclaration) getType(), parameterText.getText()));
			addContentAdapter();
		});
	}

	private DataType getTypeForSelection(String text) {
		for (AdapterTypePaletteEntry adaptertype : getAdapterTypes()) {
			if (adaptertype.getType().getName().equals(text)) {
				return adaptertype.getType();
			}
		}
		return null;
	}

	private void fillTypeCombo(String text) {
		typeCombo.removeAll();
		if (getType() instanceof Event) {
			EventTypeLibrary.getInstance().getEventTypes().forEach(eType -> typeCombo.add(eType.getName()));
		} else if (getType() instanceof AdapterDeclaration) {
			if (null != getType().getFBNetworkElement().getFbNetwork().getApplication()) {
				getAdapterTypes().forEach(adp -> typeCombo.add(adp.getType().getName()));
			}
		} else if (getType() instanceof VarDeclaration) {
			DataTypeLibrary.getInstance().getDataTypesSorted().forEach(dataType -> typeCombo.add(dataType.getName()));
		}

		if (typeCombo.getItems().length > 0) {
			int i = typeCombo.getItems().length - 1;
			while (!text.equals(typeCombo.getItems()[i]) && i > 0) {
				--i;
			}
			typeCombo.select(i);
		}
	}

	private List<AdapterTypePaletteEntry> getAdapterTypes() {
		Palette pal = getPalette();
		if (null == pal) {
			pal = TypeLibrary.getInstance().getPalette();
		}
		return getAdapterGroup(pal.getRootGroup());
	}

	private Palette getPalette() {
		return getType().getFBNetworkElement().getFbNetwork().getApplication().getAutomationSystem().getPalette();
	}

	private static List<AdapterTypePaletteEntry> getAdapterGroup(
			final org.eclipse.fordiac.ide.model.Palette.PaletteGroup group) {
		List<AdapterTypePaletteEntry> retVal = new ArrayList<>();
		for (Iterator<PaletteGroup> iterator = group.getSubGroups().iterator(); iterator.hasNext();) {
			PaletteGroup paletteGroup = iterator.next();
			retVal.addAll(getAdapterGroup(paletteGroup));
		}
		retVal.addAll(getAdapterGroupEntries(group));
		return retVal;
	}

	private static List<AdapterTypePaletteEntry> getAdapterGroupEntries(
			final org.eclipse.fordiac.ide.model.Palette.PaletteGroup group) {
		List<AdapterTypePaletteEntry> retVal = new ArrayList<>();
		for (PaletteEntry entry : group.getEntries()) {
			if (entry instanceof AdapterTypePaletteEntry) {
				retVal.add((AdapterTypePaletteEntry) entry);
			}
		}
		return retVal;
	}

	@Override
	public void refresh() {
		CommandStack commandStackBuffer = commandStack;
		commandStack = null;
		if (null != type) {
			setEditabelFields(getType().getFBNetworkElement() instanceof SubApp);
			nameText.setText(getType().getName() != null ? getType().getName() : ""); //$NON-NLS-1$
			commentText.setText(getType().getComment() != null ? getType().getComment() : ""); //$NON-NLS-1$
			String itype = ""; //$NON-NLS-1$
			if (getType() instanceof VarDeclaration) {
				VarDeclaration var = (VarDeclaration) getType();
				itype = var.getType() != null ? var.getType().getName() : ""; //$NON-NLS-1$
				if (getType().isIsInput()) {
					parameterText.setVisible(true);
					valueCLabel.setVisible(true);
					parameterText.setText(
							var.getValue() != null && var.getValue().getValue() != null ? var.getValue().getValue()
									: ""); //$NON-NLS-1$
				} else {
					valueCLabel.setVisible(false);
					parameterText.setVisible(false);
				}
			} else {
				itype = "Event";
				valueCLabel.setVisible(false);
				parameterText.setVisible(false);
			}
			fillTypeCombo(itype);
		}
		commandStack = commandStackBuffer;
	}

	/**
	 * Set the input fields edit able or not
	 * 
	 * @param editAble flag indicating if the fields should be editable
	 */
	private void setEditabelFields(boolean editAble) {
		nameText.setEditable(editAble);
		nameText.setEnabled(editAble);
		commentText.setEditable(editAble);
		commentText.setEnabled(editAble);
		// if it should be editable only allow to change the type if there is no
		// connection attached
		typeCombo.setEnabled(
				editAble && getType().getInputConnections().isEmpty() && getType().getOutputConnections().isEmpty());

	}

	@Override
	protected CommandStack getCommandStack(IWorkbenchPart part, Object input) {
		if (part instanceof DiagramEditorWithFlyoutPalette) {
			return ((DiagramEditorWithFlyoutPalette) part).getCommandStack();
		}
		return null;
	}

	@SuppressWarnings("static-method") // this method allows sub-classes to provide own change type commands, e.g.,
										// subapps
	protected ChangeTypeCommand newChangeTypeCommand(VarDeclaration data, DataType newType) {
		return new ChangeTypeCommand(data, newType);
	}

	@Override
	protected IInterfaceElement getInputType(Object input) {
		if (input instanceof InterfaceEditPart) {
			return ((InterfaceEditPart) input).getModel();
		}
		return null;
	}

	@Override
	protected IInterfaceElement getType() {
		return (IInterfaceElement) type;
	}

	@Override
	protected void setInputInit() {
	}

	@Override
	protected void setInputCode() {
	}
}
