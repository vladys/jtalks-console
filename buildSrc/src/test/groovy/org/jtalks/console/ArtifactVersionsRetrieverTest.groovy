package org.jtalks.console

/**
 * @author stanislav bashkirtsev
 */
class ArtifactVersionsRetrieverTest extends GroovyTestCase {
  void testGetPoulpeReleasedWarUrl() {
    assert sut.getPoulpeReleasedWarUrl("0.18") == "http://repo.jtalks.org/content/repositories/releases/org/jtalks/poulpe/poulpe-web-view/0.18/poulpe-web-view-0.18.war"
  }

  ArtifactVersionsRetriever sut = new ArtifactVersionsRetriever()
}
