package org.jtalks.console

/**
 * @author stanislav bashkirtsev
 */
class WorkDirs {
  private final String[] dirs = ["./workdir/apps/", "./workdir/servers/", "./workdir/configs/", "./workdir/deployed/"]
  private final def config = new ConfigSlurper().parse(new File("gradle.properties").toURL())
  final downloadedAppsDir = dirs[0], wevServersDir = dirs[1], configsDir = dirs[2], deployedAppsDir = dirs[3]

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

  void unpackServer() {

//    def zipFile = new java.util.zip.ZipFile(new File(wevServersDir + config.server.name + ".zip"))
  }

  void downloadTomcat() {
    String address = "http://ftp.ps.pl/pub/apache/tomcat/tomcat-6/v6.0.35/bin/" + config.server.name + ".zip"
    def file = new FileOutputStream(wevServersDir + address.tokenize("/")[-1])
    def out = new BufferedOutputStream(file)
    out << new URL(address).openStream()
    out.close()

    def ant = new AntBuilder()   // create an antbuilder

    ant.unzip(  src: wevServersDir + config.server.name + ".zip",
        dest: wevServersDir,
        overwrite:"false" )
  }
}
