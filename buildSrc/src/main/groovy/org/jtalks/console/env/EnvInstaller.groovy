package org.jtalks.console.env

/**
 * @author stanislav bashkirtsev
 */
class EnvInstaller {
  private final Node xmlWithPackages;

  EnvInstaller(Node xmlWithPackages) {
    this.xmlWithPackages = xmlWithPackages
  }

  static EnvInstaller create(String packagesFile) {
    Node xml = new XmlParser().parse(packagesFile)
    new EnvInstaller(xml)
  }

  void installAllPackages() {
    allInstallCommands.each() {"${it}".execute()}
  }

  List<String> getAllInstallCommands() {
    def allCommands = []
    for (software in xmlWithPackages.software) {
      allCommands.add("yum install " + software."@name" + " -v " + software."@version" + " -y")
    }
    allCommands
  }
}
