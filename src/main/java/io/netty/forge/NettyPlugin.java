package io.netty.forge;

import io.netty.forge.facet.Netty3Facet;
import io.netty.forge.facet.NettyFacet;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.jboss.forge.project.Facet;
import org.jboss.forge.project.Project;
import org.jboss.forge.project.facets.events.InstallFacets;
import org.jboss.forge.shell.Shell;
import org.jboss.forge.shell.plugins.Alias;
import org.jboss.forge.shell.plugins.Plugin;
import org.jboss.forge.shell.plugins.RequiresProject;
import org.jboss.forge.shell.plugins.SetupCommand;

/**
 * Netty Forge Plugin
 * 
 * @author <a href="mailto:ggastald@redhat.com">George Gastaldi</a>
 */
@Alias("netty")
@RequiresProject
public class NettyPlugin implements Plugin
{
   @Inject
   private Project project;

   @Inject
   private Event<InstallFacets> event;

   @Inject
   private Shell shell;

   @SetupCommand
   public void setupNetty()
   {
      int choice = shell.promptChoice("Which Netty version would you like to install?", "3.x", "4.x or later");
      if (choice == 0)
      {
         // Install Netty 3.x
         installFacet(Netty3Facet.class);
      }
      else if (choice == 1)
      {
         // Install Netty 4.x
         installFacet(NettyFacet.class);
      }
   }

   private void installFacet(Class<? extends Facet> facet)
   {
      event.fire(new InstallFacets(facet));
      if (project.hasFacet(facet))
      {
         shell.println("Netty is installed.");
      }
   }
}
