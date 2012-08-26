package org.jtalks.console.env

/**
 * @author stanislav bashkirtsev
 */
class EnvStarterTest extends GroovyTestCase {

  void testGetAllStopCommands() {
    List<String> commands = EnvStarter.create(environmentFile).allStopCommands
    assert !commands.empty
    assert commands.first() == "/etc/init.d/mysqld stop"
  }

  void testGetAllStartCommands() {
    List<String> commands = EnvStarter.create(environmentFile).allStartCommands
    assert !commands.empty
    assert commands.first() == "/etc/init.d/mysqld start"
  }

  private final static String FILE_NAME = "software-packages.xml"
  private final String environmentFile = new File("./$FILE_NAME").exists() ? "./$FILE_NAME" : "../$FILE_NAME"
}
