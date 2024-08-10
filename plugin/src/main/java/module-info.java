/**
 * @author VISTALL
 * @since 2023-03-31
 */
open module org.jetbrains.plugins.gradle {
    requires com.google.gson;
    requires com.intellij.properties;

    requires consulo.ide.api;
    requires consulo.internal.jdi;
    requires consulo.java.execution.api;
    requires consulo.java.execution.impl;
    requires consulo.util.nodep;

    requires gradle.all;
    requires kryo;
    requires minlog;
    requires objenesis;

    requires org.apache.groovy;
    requires org.intellij.groovy;
    requires org.intellij.groovy.psi;
    requires org.jetbrains.plugins.gradle.api;
    requires org.jetbrains.plugins.gradle.tooling;
    requires org.jetbrains.plugins.gradle.tooling.impl;

    // TODO remove in future
    requires consulo.ide.impl;
    requires java.desktop;

    exports consulo.gradle.codeInspection.localize;
}