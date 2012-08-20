package org.jtalks.console

/**
 * @author stanislav bashkirtsev
 */
class ArtifactVersions {
  private final String projectName;
  private final List<String> versions = []

  ArtifactVersions(String projectName, List<String> versions) {
    this.versions.addAll(versions)
    this.projectName = projectName;
  }

  void printVersions() {
    print "$projectName released versions: $versions\n"
  }

  public List<String> getVersionList() {
    versions
  }
}
