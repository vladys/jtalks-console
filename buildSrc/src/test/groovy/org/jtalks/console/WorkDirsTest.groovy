package org.jtalks.console

/**
 * @author stanislav bashkirtsev
 */
class WorkDirsTest extends GroovyTestCase {
  void testPoulpeCopyingWarRegexWithOneMajorDigit() {
    String originalFileName = "poulpe-web-view-0.18.war"
    String[] splits = originalFileName.split(WorkDirs.FROM_LONG_WAR_REGEX)
    assert splits.length == 1
    assert splits[0] == "poulpe"
  }

  void testPoulpeCopyingWarRegexWithTwoMajorDigits() {
    String originalFileName = "poulpe-web-view-10.1.war"
    String[] splits = originalFileName.split(WorkDirs.FROM_LONG_WAR_REGEX)
    assert splits.length == 1
    assert splits[0] == "poulpe"
  }
}
