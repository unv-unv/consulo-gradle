package org.jetbrains.plugins.gradle.settings;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.plugins.gradle.util.GradleConstants;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StoragePathMacros;
import com.intellij.openapi.externalSystem.settings.AbstractExternalSystemLocalSettings;
import com.intellij.openapi.project.Project;

/**
 * @author Denis Zhdanov
 * @since 5/3/12 6:16 PM
 */
@State(name = "GradleLocalSettings", storages = {@Storage(file = StoragePathMacros.WORKSPACE_FILE)} )
public class GradleLocalSettings extends AbstractExternalSystemLocalSettings
  implements PersistentStateComponent<AbstractExternalSystemLocalSettings.State>
{

  public GradleLocalSettings(@NotNull Project project) {
    super(GradleConstants.SYSTEM_ID, project);
  }

  @NotNull
  public static GradleLocalSettings getInstance(@NotNull Project project) {
    return ServiceManager.getService(project, GradleLocalSettings.class);
  }

  @Nullable
  @Override
  public State getState() {
    State state = new State();
    fillState(state);
    return state;
  }

  @Override
  public void loadState(@NotNull State state) {
    super.loadState(state); 
  }
}
