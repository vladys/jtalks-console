package org.jtalks.console

import org.gradle.api.Nullable
/**
 * Provides ability to get list of available stable/snapshot versions of the JTalks artifacts (Poulpe, JCommune) and
 * also can provide URLs to download those artifacts.
 *
 * @author stanislav bashkirtsev
 */
class ArtifactVersionsRetriever {
  public ArtifactVersions getPoulpeReleaseVersions() {
    return new ArtifactVersions("Poulpe", getVersions(POULPE_RELEASES_PAGE))
  }

  public ArtifactVersions getJcommuneReleaseVersions() {
    return new ArtifactVersions("JCommune", getVersions(JCOMMUNE_RELEASES_PAGE))
  }

  /**
   *
   * @param version
   * @return
   */
  public String getPoulpeReleasedWarUrl(@Nullable String version) {
    if (version == null) {
      version = poulpeReleaseVersions.versionList.last()
    }
    println "Downloading $version version of Poulpe..."
    POULPE_RELEASES_PAGE + version + "/poulpe-web-view-" + version + ".war"
  }

  /**
   * Gets available versions of the artifacts listed on the specified URL in nexus.
   * @param urlWithVersions an address of the page where versions of WARs are listed
   * @return a list of versions of the artifacts on the specified page (either snapshots or stable versions)
   */
  private List<String> getVersions(String urlWithVersions) {
    List<String> versions = []
    String pageContent = new URL(urlWithVersions).openStream().getText()
    String[] refsFromPage = pageContent.findAll(/>(.+)<\/a/)
    for (String s : refsFromPage) {
      String versionName = s.replaceAll(">", "").replaceAll("</a", "")
      if (versionName.contains("/")) {
        versions.add(versionName.replace("/", ""))
      }
    }
    versions
  }

  private final static String POULPE_RELEASES_PAGE = "http://repo.jtalks.org/content/repositories/releases/org/jtalks/poulpe/poulpe-web-view/"
  private final static String JCOMMUNE_RELEASES_PAGE = "http://repo.jtalks.org/content/repositories/releases/org/jtalks/jcommune/jcommune-web-view/"
}
