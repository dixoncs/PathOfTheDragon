JUNIT5_JAR = junit-platform-console-standalone-1.2.0.jar
JUNIT5_RUNNER = org.junit.platform.console.ConsoleLauncher
CKSTYLE_XML = cs_appstate_checks.xml
CKSTYLE_COMMAND =  -jar /usr/local/checkstyle-5.5/checkstyle-5.5-all.jar
PROG_DRIVER = @java GameState

default:
	@echo "5 available targets:" 
	@echo "____________________ default - shows makefile target choices"
	@echo "____________________ clean - removes editor tmpfiles and .class files"
	@echo "____________________ compile - compiles all necessary java files" 
	@echo "____________________ test - builds JUnit5 tests"
	@echo "____________________ check - runs checkstyle"
	@echo "____________________ run - runs main in GameState class" 

run: 
	$(PROG_DRIVER)
	
compile: GameState.java GameStateTest.java $(JUNIT5_JAR)
	javac AbstractGame.java
	javac GameBoard.java
	javac Tile.java
	javac GameState.java
	@javac -cp .:$(JUNIT5_JAR) GameStateTest.java

clean:
	rm -f *.class

test: $(JUNIT5_JAR)
	@java -cp .:$(JUNIT5_JAR) $(JUNIT5_RUNNER) --scan-class-path 

check: GameState.java style.xml
	@java $(CKSTYLE_COMMAND) -c style.xml GameState.java
#	@java $(CKSTYLE_COMMAND) -c style.xml *.java

# makefile syntax
# #target-name: files dependent on (can use multiple lines by ending
# #             lines with \
# #<TAB char>Unix command-line command
# #<TAB char>Unix command-line command
# #etc.
# #Essential that command lines start with single TAB character

