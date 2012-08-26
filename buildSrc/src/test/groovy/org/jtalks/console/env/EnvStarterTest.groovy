package org.jtalks.console.env

/**
 * @author stanislav bashkirtsev
 */
class EnvStarterTest extends GroovyTestCase {


  void testGetAllStartCommands() {
    List<String> commands = EnvStarter.create("./software-packages.xml").allStartCommands
    assert !commands.empty
    assert commands.first() == "/etc/init.d/mysqld start"
  }
}
