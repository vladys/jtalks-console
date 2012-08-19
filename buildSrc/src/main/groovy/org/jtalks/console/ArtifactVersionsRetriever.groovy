package org.jtalks.console

import org.gradle.api.Nullable
/**
 * @author stanislav bashkirtsev
 */
class ArtifactVersionsRetriever {
  public List<String> getPoulpeReleaseVersions() {
    return getVersions(POULPE_RELEASES_PAGE)
  }

  public String getPoulpeReleaseVersionsOutput() {
    return "Poulpe released versions: " + getVersions(POULPE_RELEASES_PAGE) + "\n"
  }

  public String getJcommuneReleaseVersionsOutput() {
    return "JCommune released versions: " + getVersions(JCOMMUNE_RELEASES_PAGE) + "\n"
  }

  public String getPoulpeReleasedWarUrl(@Nullable String version) {
    if (version == null) {
      version = poulpeReleaseVersions.last()
    }
    POULPE_RELEASES_PAGE + version + "/poulpe-web-view-" + version + ".war"
  }

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
