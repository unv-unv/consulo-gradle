/*
 * Copyright 2000-2013 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jetbrains.plugins.gradle.integrations.maven;

import consulo.application.ApplicationManager;
import consulo.project.DumbService;
import consulo.project.Project;
import consulo.project.startup.StartupActivity;
import consulo.ui.UIAccess;

import javax.annotation.Nonnull;

/**
 * @author Vladislav.Soroka
 * @since 10/28/13
 */
public class GradleProjectStartupActivity implements StartupActivity {

  @Override
  public void runActivity(@Nonnull final Project project, @Nonnull UIAccess uiAccess) {
    DumbService.getInstance(project).smartInvokeLater(new ImportMavenRepositoriesTask(project));
  }
}
