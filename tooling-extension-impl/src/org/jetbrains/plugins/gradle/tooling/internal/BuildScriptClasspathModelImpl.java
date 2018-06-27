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
package org.jetbrains.plugins.gradle.tooling.internal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.gradle.tooling.model.DomainObjectSet;
import org.gradle.tooling.model.internal.ImmutableDomainObjectSet;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.jetbrains.plugins.gradle.model.BuildScriptClasspathModel;
import org.jetbrains.plugins.gradle.model.ClasspathEntryModel;

/**
 * @author Vladislav.Soroka
 * @since 12/20/13
 */
public class BuildScriptClasspathModelImpl implements BuildScriptClasspathModel
{

  private final List<ClasspathEntryModel> myClasspathEntries;
  @javax.annotation.Nullable
  private File gradleHomeDir;
  @Nonnull
  private String myGradleVersion;

  public BuildScriptClasspathModelImpl() {
    myClasspathEntries = new ArrayList<ClasspathEntryModel>();
  }

  @Override
  public DomainObjectSet<? extends ClasspathEntryModel> getClasspath() {
    return ImmutableDomainObjectSet.of(myClasspathEntries);
  }

  public void setGradleHomeDir(@Nullable File file) {
    gradleHomeDir = file;
  }

  @javax.annotation.Nullable
  @Override
  public File getGradleHomeDir() {
    return gradleHomeDir;
  }

  public void add(@Nonnull ClasspathEntryModel classpathEntryModel) {
    myClasspathEntries.add(classpathEntryModel);
  }

  public void setGradleVersion(@Nonnull String gradleVersion) {
    myGradleVersion = gradleVersion;
  }

  @Nonnull
  @Override
  public String getGradleVersion() {
    return myGradleVersion;
  }
}
