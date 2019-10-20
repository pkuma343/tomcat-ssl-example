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
		//def buildInfo = rtMaven.run pom: 'pom.xml', goals: 'install'
		def buildInfo = rtMaven.run pom: 'pom.xml', goals: "install -Drevision=RELEASE-SNAPSHOT"
		def buildInfo1 = rtMaven.run pom: 'pom.xml', goals: "install -Drevision=0.0.2"
		server.publishBuildInfo buildInfo
		server.publishBuildInfo buildInfo1
	}
}
