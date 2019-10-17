node {
	stage('Checkout SCM') {
		checkout scm
	}
	
	stage('Deploy to artifactory') {
		def rtMaven = Artifactory.newMavenBuild()
		def server = Artifactory.server("Artifactory")
		rtMaven.tool = "Maven-Local"
		rtMaven.deployer releaseRepo:'libs-release-local', snapshotRepo:'libs-snapshot-local', server: server
		// This is where our artifacts come from
		rtMaven.resolver releaseRepo:'libs-release', snapshotRepo:'libs-snapshot', server: server
		def buildInfo = rtMaven.run pom: 'pom.xml', goals: 'install'
		server.publishBuildInfo buildInfo
	}
}
