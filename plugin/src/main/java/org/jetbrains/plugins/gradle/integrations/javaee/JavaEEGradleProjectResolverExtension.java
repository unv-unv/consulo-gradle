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
package org.jetbrains.plugins.gradle.integrations.javaee;

import consulo.externalSystem.model.DataNode;
import consulo.externalSystem.model.project.ModuleData;
import consulo.externalSystem.util.ExternalSystemConstants;
import consulo.externalSystem.util.Order;
import consulo.logging.Logger;
import consulo.util.collection.ContainerUtil;
import org.gradle.tooling.model.idea.IdeaModule;
import org.jetbrains.plugins.gradle.model.data.War;
import org.jetbrains.plugins.gradle.model.data.WarDirectory;
import org.jetbrains.plugins.gradle.model.data.WebConfigurationModelData;
import org.jetbrains.plugins.gradle.model.data.WebResource;
import org.jetbrains.plugins.gradle.service.project.AbstractProjectResolverExtension;
import org.jetbrains.plugins.gradle.tooling.web.WebConfiguration;
import org.jetbrains.plugins.gradle.util.GradleConstants;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

/**
 * {@link JavaEEGradleProjectResolverExtension} provides JavaEE project info based on gradle tooling API models.
 *
 * @author Vladislav.Soroka
 * @since 10/14/13
 */
@Order(ExternalSystemConstants.UNORDERED)
public class JavaEEGradleProjectResolverExtension extends AbstractProjectResolverExtension {
  private static final Logger LOG = Logger.getInstance(JavaEEGradleProjectResolverExtension.class);

  @Override
  public void populateModuleExtraModels(@Nonnull IdeaModule gradleModule, @Nonnull DataNode<ModuleData> ideModule) {
    final WebConfiguration webConfiguration = resolverCtx.getExtraProject(gradleModule, WebConfiguration.class);
    if (webConfiguration != null) {
      List<War> warModels = ContainerUtil.map(webConfiguration.getWarModels(), new Function<WebConfiguration.WarModel, War>() {
        @Override
        public War apply(WebConfiguration.WarModel model) {
          War war = new War(model.getWarName(), model.getWebAppDirName(), model.getWebAppDir());
          war.setWebXml(model.getWebXml());
          war.setWebResources(map(model.getWebResources()));
          war.setClasspath(model.getClasspath());
          war.setManifestContent(model.getManifestContent());
          return war;
        }
      });

      ideModule.createChild(WebConfigurationModelData.KEY, new WebConfigurationModelData(GradleConstants.SYSTEM_ID, warModels));
    }

    nextResolver.populateModuleExtraModels(gradleModule, ideModule);
  }

  @Nonnull
  @Override
  public Set<Class> getExtraProjectModelClasses() {
    return Collections.<Class>singleton(WebConfiguration.class);
  }

  private static List<WebResource> map(List<WebConfiguration.WebResource> webResources) {
    return ContainerUtil.mapNotNull(webResources, new Function<WebConfiguration.WebResource, WebResource>() {
      @Override
      public WebResource apply(WebConfiguration.WebResource resource) {
        if (resource == null) {
          return null;
        }

        final WarDirectory warDirectory = WarDirectory.fromPath(resource.getWarDirectory());
        return new WebResource(warDirectory, resource.getRelativePath(), resource.getFile());
      }
    });
  }
}
