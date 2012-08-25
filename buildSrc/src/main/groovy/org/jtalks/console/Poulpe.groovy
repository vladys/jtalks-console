package org.jtalks.console

import org.gradle.api.Nullable

/**
 * @author stanislav bashkirtsev
 */
class Poulpe {
  private final ArtifactVersionsRetriever versionsRetriever = new ArtifactVersionsRetriever()

  public String getReleasedWarUrl(@Nullable String version) {
    if (version == null) {
      version = versionsRetriever.poulpeReleaseVersions.versionList.last()
    }
    println "[JTALKS] Downloading $version version of Poulpe..."
    POULPE_RELEASES_PAGE + version + "/poulpe-web-view-" + version + ".war"
  }

  private final static String POULPE_RELEASES_PAGE = "http://repo.jtalks.org/content/repositories/releases/org/jtalks/poulpe/poulpe-web-view/"
}
