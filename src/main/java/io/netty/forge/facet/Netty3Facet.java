/*
 * Copyright 2013 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package io.netty.forge.facet;

import org.jboss.forge.project.dependencies.Dependency;
import org.jboss.forge.project.dependencies.DependencyBuilder;
import org.jboss.forge.project.dependencies.ScopeType;
import org.jboss.forge.project.facets.DependencyFacet;
import org.jboss.forge.shell.plugins.Alias;
import org.jboss.forge.shell.plugins.RequiresFacet;

@Alias("io.netty.3x.facet")
@RequiresFacet({ DependencyFacet.class })
public class Netty3Facet extends AbstractFacet
{
   public static final Dependency NETTY_DEP = DependencyBuilder.create().setGroupId("io.netty")
            .setArtifactId("netty").setScopeType(ScopeType.COMPILE);

   @Override
   public Dependency getFacetDependency()
   {
      return NETTY_DEP;
   }
}
