
// jenkins/develop.Jenkinsfile
stage ('Branch') {
      steps {
        echo "Toto je develop branch"
      }
    }

@NonCPS
boolean isDevelop() {
    return env.BRANCH_NAME == 'develop'
}

@NonCPS
boolean isMain() {
  return env.BRANCH_NAME == 'main'
}

@NonCPS
boolean isPR() {
  return env.CHANGE_TARGET ? true : false
}



node {
  if (isDevelop()) {
    pipeline = readTrusted('develop.Jenkinsfile')
  } else if (isMain()) {
    pipeline = readTrusted('main.Jenkinsfile')
  } else if (isPR()){
    pipeline = readTrusted('pr.Jenkinsfile')
  } else {
    pipeline = readTrusted('feature.Jenkinsfile')
  }
}

evaluate(pipeline)
