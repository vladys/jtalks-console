package org.jtalks.console.env

/**
 * @author stanislav bashkirtsev
 */
class Environment {
  private final EnvInstaller installer
  private final EnvStarter starter

  Environment(EnvInstaller installer, EnvStarter starter) {
    this.installer = installer
    this.starter = starter
  }

  void install() {
    installer.installAllPackages()
  }

  void start() {
    starter.startPackagesWithSpecifiedStartCommand()
  }

  static Environment create(String packagesFile) {
    Node xml = new XmlParser().parse(packagesFile)
    new Environment(new EnvInstaller(xml), new EnvStarter(xml))
  }
}
