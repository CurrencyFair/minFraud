
repositories.remote << 'http://repo1.maven.org/maven2/'
repositories.release_to = 'https://oss.sonatype.org/service/local/staging/deploy/maven2/'
repositories.snapshot_to = 'https://oss.sonatype.org/content/repositories/snapshots/'

SLF4J_API = 'org.slf4j:slf4j-api:jar:1.7.5'
COMMONS_LANG3 = 'org.apache.commons:commons-lang3:jar:3.1'
HTTPCLIENT = transitive('org.apache.httpcomponents:httpclient:jar:4.3.2')
EASYMOCK = 'org.easymock:easymock:jar:3.2'

VERSION_NUMBER = '1.0.1-SNAPSHOT'

define 'minFraud' do
  project.version = VERSION_NUMBER
  project.group = 'com.currencyfair'
  compile.options.source = '1.7'
  compile.options.target = '1.7'
  compile.with SLF4J_API, COMMONS_LANG3, HTTPCLIENT
  test.with EASYMOCK
  package :jar
end
