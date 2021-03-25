/**
 * generated by Xtext 2.11.0
 */
package org.eclipse.fordiac.ide.model.xtext.fbt.ui;

import com.google.inject.Provider;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor;
import org.eclipse.xtext.resource.containers.IAllContainersState;
import org.eclipse.xtext.ui.editor.model.IResourceForEditorInputFactory;
import org.eclipse.xtext.ui.editor.model.ResourceForIEditorInputFactory;
import org.eclipse.xtext.ui.resource.IResourceSetProvider;
import org.eclipse.xtext.ui.resource.SimpleResourceSetProvider;
import org.eclipse.xtext.ui.shared.Access;

/**
 * Use this class to register components to be used within the Eclipse IDE.
 */
@FinalFieldsConstructor
@SuppressWarnings("all")
public class FBTypeUiModule extends AbstractFBTypeUiModule {
  @Override
  public Provider<? extends IAllContainersState> provideIAllContainersState() {
    return Access.getWorkspaceProjectsState();
  }
  
  @Override
  public Class<? extends IResourceForEditorInputFactory> bindIResourceForEditorInputFactory() {
    return ResourceForIEditorInputFactory.class;
  }
  
  @Override
  public Class<? extends IResourceSetProvider> bindIResourceSetProvider() {
    return SimpleResourceSetProvider.class;
  }
  
  public FBTypeUiModule(final AbstractUIPlugin plugin) {
    super(plugin);
  }
}
