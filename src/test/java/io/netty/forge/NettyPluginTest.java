package io.netty.forge;

import io.netty.forge.facet.Netty3Facet;
import io.netty.forge.facet.NettyFacet;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.forge.project.Project;
import org.jboss.forge.test.AbstractShellTest;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;

public class NettyPluginTest extends AbstractShellTest
{
   @Deployment
   public static JavaArchive getDeployment()
   {
      return AbstractShellTest.getDeployment()
               .addPackages(true, NettyPlugin.class.getPackage());
   }

   @Test
   public void testAbortSetup() throws Exception
   {
      Project project = initializeJavaProject();
      queueInputLines("0");
      getShell().execute("netty setup");
      Assert.assertFalse(project.hasFacet(Netty3Facet.class));
      Assert.assertFalse(project.hasFacet(NettyFacet.class));
   }

   @Test
   public void testSetupCommandNetty3() throws Exception
   {
      Project project = initializeJavaProject();
      queueInputLines("1", "");
      getShell().execute("netty setup");
      Assert.assertTrue(project.hasFacet(Netty3Facet.class));
      Assert.assertFalse(project.hasFacet(NettyFacet.class));
   }

   @Test
   public void testSetupCommandNetty() throws Exception
   {
      Project project = initializeJavaProject();
      queueInputLines("2", "");
      getShell().execute("netty setup");
      Assert.assertFalse(project.hasFacet(Netty3Facet.class));
      Assert.assertTrue(project.hasFacet(NettyFacet.class));
   }
}
