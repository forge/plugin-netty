/*
 * Copyright 2013 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package io.netty.forge.facet;

import javax.inject.Inject;

import org.jboss.forge.project.dependencies.Dependency;
import org.jboss.forge.project.dependencies.DependencyInstaller;
import org.jboss.forge.project.facets.BaseFacet;
import org.jboss.forge.project.facets.DependencyFacet;

public abstract class AbstractFacet extends BaseFacet
{

   @Inject
   private DependencyInstaller installer;

   @Override
   public boolean install()
   {
      DependencyFacet facet = getProject().getFacet(DependencyFacet.class);
      if (!isInstalled())
      {
         Dependency choice = installer.install(getProject(), getFacetDependency());
         if (!facet.hasEffectiveDependency(getFacetDependency()))
         {
            installer.install(getProject(), choice);
         }
      }
      return true;
   }

   @Override
   public boolean isInstalled()
   {
      DependencyFacet facet = getProject().getFacet(DependencyFacet.class);
      return facet.hasEffectiveDependency(getFacetDependency());
   }

   public abstract Dependency getFacetDependency();

}
