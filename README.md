step 1: To create NiFi Processor

mvn archetype:generate

step 2: "type" nifi "when it asks"

step 3: "it asks again" 1

step 4: press enter

it will generate the nif project

step 5: run at root level =>  mvn install


step 6 : copy the .nar file into your nifi lib directory.



Define value for property 'groupId': : org.apache.nifi
Define value for property 'artifactId': : nifi-helloworld-bundle
Define value for property 'version':  1.0-SNAPSHOT: :
Define value for property 'artifactBaseName': : helloworld
Define value for property 'package':  org.apache.nifi.processors.helloworld: :
