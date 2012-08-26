package org.jtalks.console.env

/**
 * @author stanislav bashkirtsev
 */
class EnvStarterTest extends GroovyTestCase {


  void testGetAllStartCommands() {
    String file = new File("./software-packages.xml").exists() ? "./software-packages.xml" : "../software-packages.xml"
    List<String> commands = EnvStarter.create(file).allStartCommands
    assert !commands.empty
    assert commands.first() == "/etc/init.d/mysqld start"
  }
}
