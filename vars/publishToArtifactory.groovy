
def call(Map args = [:]) {
  def repo          = args.repo ?: 'libs-release-local'
  def file          = args.file ?: error('file is required')
  def server        = args.server ?: error('server URL required')
  def credentialsId = args.credentialsId ?: 'jfrog-creds'

  withCredentials([usernamePassword(credentialsId: credentialsId,
                                    usernameVariable: 'ART_USER',
                                    passwordVariable: 'ART_PASS')]) {
    sh """
      set +x
      curl -u "$ART_USER:$ART_PASS" -T "${file}" "${server}/${repo}/"
    """
  }
}
