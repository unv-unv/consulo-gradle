/*
 * Copyright 2000-2014 JetBrains s.r.o.
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
package org.jetbrains.plugins.gradle.tooling;

import org.gradle.api.Project;
import javax.annotation.Nonnull;

import java.io.Serializable;

/**
 * @author Vladislav.Soroka
 * @since 11/5/13
 */
public interface ModelBuilderService extends Serializable {
  boolean canBuild(String modelName);

  Object buildAll(String modelName, Project project);

  @Nonnull
  ErrorMessageBuilder getErrorMessageBuilder(@Nonnull Project project, @Nonnull Exception e);
}
