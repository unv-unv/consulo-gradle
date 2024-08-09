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

import consulo.annotation.component.ExtensionImpl;
import consulo.maven.rt.server.common.model.MavenRemoteRepository;
import consulo.project.Project;
import org.jetbrains.idea.maven.indices.MavenRepositoryProvider;

import javax.annotation.Nonnull;
import java.util.Set;

/**
 * @author Vladislav.Soroka
 * @since 10/25/13
 */
@ExtensionImpl
public class GradleMavenRepositoryProvider implements MavenRepositoryProvider {
    @Nonnull
    @Override
    public Set<MavenRemoteRepository> getRemoteRepositories(@Nonnull Project project) {
        return MavenRepositoriesHolder.getInstance(project).getRemoteRepositories();
    }
}
