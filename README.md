https://nifi.apache.org/developer-guide.html

step 1: To create NiFi Processor

mvn archetype:generate

step 2: "type" nifi "when it asks"

step 3: "it asks again" 1

step 4: press enter

it will generate the nif project

step 5: run at root level =>  mvn install


step 6 : copy the .nar file into your nifi lib directory.



below is the example to give information when we are trying to create the process

Define value for property 'groupId': : org.apache.nifi

Define value for property 'artifactId': : nifi-helloworld-bundle

Define value for property 'version':  1.0-SNAPSHOT: :

Define value for property 'artifactBaseName': : helloworld

Define value for property 'package':  org.apache.nifi.processors.helloworld: :


https://www.batchiq.com/database-ingest-with-nifi.html

https://blogs.apache.org/nifi/entry/record-oriented-data-with-nifi

https://community.hortonworks.com/idea/118504/data-inject-to-database-through-ni-fi.html


https://www.youtube.com/watch?v=KApvTd3UPzo  ( need to go thru it)

https://www.youtube.com/watch?v=2w14d16wR8Y

https://www.youtube.com/watch?v=r6I-Ahc0HB4 (iamshaunjp/regex-playlist)

https://regex101.com/

mvn eclipse:eclipse -DdownloadSources=true
