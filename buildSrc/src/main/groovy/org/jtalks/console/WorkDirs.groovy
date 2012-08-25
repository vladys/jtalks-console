package org.jtalks.console

/**
 * @author stanislav bashkirtsev
 */
class WorkDirs {

  void createDirs() {
    for (String dir : dirs) {
      File file = new File(dir)
      if (!file.exists()) {
        file.mkdirs();
      }
    }
  }

  void removeDirs() {
    def file = new File("./workdir")
    if (file.exists()) {
      file.deleteDir()
    }
  }

  void downloadJtalksApp(String address) {
    def file = new FileOutputStream(downloadedAppsDir + address.tokenize("/")[-1])
    def out = new BufferedOutputStream(file)
    out << new URL(address).openStream()
    out.close()
  }

  void unpackPoulpe() {
    def ant = new AntBuilder()
    ant.copy(todir: deployedAppsDir + config.server.name + "/webapps") {
      fileset(dir: downloadedAppsDir) {
        include(name: "*.war")
      }
      globmapper(from: "poulpe*", to: "poulpe.war")
    }
  }

  void startTomcat() {
    new Tomcat(this).start()
  }

  void stopTomcat() {
    new Tomcat(this).stop()
  }

  void downloadTomcat() {
    new Tomcat(this).download()
  }

  static final String FROM_LONG_WAR_REGEX = /(-web-view.*)/
  private static final String[] dirs = ["./workdir/apps/", "./workdir/servers/", "./workdir/configs/", "./workdir/deployed/"]
  static final downloadedAppsDir = dirs[0], webServersDir = dirs[1], configsDir = dirs[2], deployedAppsDir = dirs[3]
  private final def config = new ConfigSlurper().parse(new File("gradle.properties").toURL())
}
