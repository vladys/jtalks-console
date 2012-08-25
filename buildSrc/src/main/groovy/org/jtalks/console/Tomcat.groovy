package org.jtalks.console

/**
 * @author stanislav bashkirtsev
 */
class Tomcat {

  Tomcat(WorkDirs workDirs) {
    this.workDirs = workDirs
    this.homeDir = WorkDirs.deployedAppsDir + config.server.name
  }

  void start() {
    "$homeDir/bin/startup.sh".execute()
  }

  void download() {
    String address = "http://ftp.ps.pl/pub/apache/tomcat/tomcat-6/v6.0.35/bin/" + config.server.name + ".zip"
    if (!(new File(config.server.name + ".zip").exists())) {
      print "[JTALKS] Downloading Tomcat from: $address"
      def file = new FileOutputStream(workDirs.webServersDir + address.tokenize("/")[-1])
      def out = new BufferedOutputStream(file)
      out << new URL(address).openStream()
      out.close()
    }

    def ant = new AntBuilder()
    ant.unzip(src: WorkDirs.webServersDir + config.server.name + ".zip",
        dest: WorkDirs.deployedAppsDir,
        overwrite: "false")
  }

  void stop() {
    String killCommand = "ps aux | grep " + homeDir.replaceFirst('.', '') + "| grep -v grep | awk '{print \$2}'| xargs kill -9"
    print "[JTALKS] Killing: $killCommand"
    killCommand.execute()
  }


  private final String homeDir;
  private final WorkDirs workDirs;
  private final ConfigObject config = new ConfigSlurper().parse(new File("gradle.properties").toURL())
}
