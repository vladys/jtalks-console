package org.jtalks.console
/**
 * @author stanislav bashkirtsev
 */
class ArtifactVersionsRetriever {
    private final static String POULPE_RELEASES_PAGE = "http://repo.jtalks.org/content/repositories/releases/org/jtalks/poulpe/poulpe-web-view/"

    public List<String> getPoulpeReleaseVersions(){
        return getVersions(POULPE_RELEASES_PAGE)
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
}
