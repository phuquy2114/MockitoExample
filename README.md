# MockitoExample

Recommended way of getting Mockito is declaring a dependency on “mockito-core” library using your favorite build system. With Gradle one can do:

'
repositories {
        jcenter()
        }
dependencies { testCompile "org.mockito:mockito-core:2.+" }
'

Maven users can declare a dependency on mockito-core. Mockito is automatically published to Bintray’s jcenter and synced to the Maven Central Repository.

Users doing manual dependency management can download the jars directly from Mockito’s Bintray repository, under the Files tab.

Legacy builds with manual dependency management can use 1.* “mockito-all” distribution. It can be downloaded from Mockito’s Bintray repository or Bintray’s jcenter. “mockito-all” distribution has been discontinued in Mockito 2.*.

More info
Main reference documentation features:

mock()/@Mock: create mock
optionally specify how it should behave via Answer/ReturnValues/MockSettings
when()/given() to specify how a mock should behave

If the provided answers don’t fit your needs, write one yourself extending the Answer interface
spy()/@Spy: partial mocking, real methods are invoked but still can be verified and stubbed

@InjectMocks: automatically inject mocks/spies fields annotated with @Spy or @Mock

verify(): to check methods were called with given arguments

can use flexible argument matching, for example any expression via the any()
or capture what arguments were called using @Captor instead
Try Behavior-Driven development syntax with BDDMockito
Use Mockito on Android, thanks to the team working on dexmaker

<b>Remember</b>
Do not mock types you don’t own
Don’t mock value objects
Don’t mock everything
Show love with your tests!

Email : phuquycntt@gmail.com
Skype : phuquy2114
