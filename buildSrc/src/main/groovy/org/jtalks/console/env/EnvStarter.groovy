package org.jtalks.console.env

/**
 * @author stanislav bashkirtsev
 */
class EnvStarter {
  private final Node xmlWithPackages;

  EnvStarter(Node xmlWithPackages) {
    this.xmlWithPackages = xmlWithPackages
  }

  static EnvStarter create(String packagesFile) {
    Node xml = new XmlParser().parse(packagesFile)
    new EnvStarter(xml)
  }

  void startPackagesWithSpecifiedStartCommand() {
    println '[JTALKS] Starting software in order to run JTalks apps..'
    allStartCommands.each() {
      println "[JTALKS] $it"
      Process proc = "$it".execute()
      proc.waitFor()
      printInfoIfError(proc)
    }
  }

  private void printInfoIfError(Process process) {
    println "[JTALKS] ${process.in.text}"
    if (process.exitValue() != 0) {
      println "[ERROR JTALKS] Return Code: ${process.exitValue()}"
      println "[ERROR JTALKS] Details: ${process.err.text}"
    }
  }

  List<String> getAllStartCommands() {
    xmlWithPackages.depthFirst().grep {it."@start"}."@start"
  }
}
