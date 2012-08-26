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
    println '[JTALKS] Installing software in order run JTalks apps..'
    allInstallCommands.each() {
      println "[JTALKS] $it"
      Process proc = "$it".execute()
      proc.waitFor()
      printInfoIfError(proc)
    }
  }

  private void printInfoIfError(Process process) {
    if (process.exitValue() != 0) {
      println "return code: ${process.exitValue()}"
      println "Errors: ${process.err.text}"
    }
  }

  List<String> getAllInstallCommands() {
    def allCommands = []
    for (software in xmlWithPackages.software) {
      allCommands.add("yum install " + software."@name" + " -v " + software."@version" + " -y")
    }
    allCommands
  }
}
