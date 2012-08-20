package org.jtalks.console

/**
 * @author stanislav bashkirtsev
 */
class Tomcat {
  private final String homeDir;

  Tomcat(String homeDir) {
    this.homeDir = homeDir
  }

  void start() {
    "$homeDir/bin/startup.sh".execute()
  }
}
