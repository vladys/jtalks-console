package org.jtalks.console.env

/**
 * @author stanislav bashkirtsev
 */
class EnvInstallerTest extends GroovyTestCase {

  void testInstallAllPackages() {
    def installer = new EnvInstaller(new XmlParser().parseText(xmlWithPackages))
    def commands = installer.allInstallCommands
    assert commands == ["yum install mysql -v 5.5 -y"]
  }

  String xmlWithPackages = '<packages>\n' +
      '  <software name="mysql" version="5.5"/>\n' +
      '</packages>'
}
