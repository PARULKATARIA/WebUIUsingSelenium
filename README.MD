What is this Project all about?
This project shows how we can do Web UI automation testing for demo website.
Website : https://www.demoblaze.com/index.html


What we chose to solve this problem and why?
Automation Tool Used : Selenium (It is an open source,multiple browser support and quite reliable tool with a 
large community base.In addition to that it can be easily integrated with build tools,testing frameworks and CI/CD tools.
Language : Java (It is one of the best programming languages that has been introduced in the programming world so far.Selenium 
with Java is a great combination.
Logging element : log4j2 (Logging plays a very crucial role when the automation test suite becomes huge and we need find the
RCA for failures.log4j2 is easy to integrate and really powerful.)
Reporting : Extent Reports (Extent reports offers interesting,interactive and detailed reports for test results)
Design Pattern for storing web elements : Page Object Model (POM is the best thing to go for as it promotes reusability and less effort
in code maintenance)
Testing Framework : TestNG (TestNG can create nice reports and helps in including assertions,grouping test cases and setting test 
case execution order.It can help in parallel execution of test cases.
Build Tool : Maven is the build tool which we used.POM.xml will contain all the dependencies and help in getting all the required
jars.Maven provides an auto created folder structure which is commendable and helps in organizing code.
Webdrivermanager : In order to avoid the task of keeping the browser executables, we have used this artifact.



What is the setup process? 
1.Please install Eclipse IDE
2.Please download Maven and set the appropriate path.
3.Please have JDK installed and Set the appropriate path.
4.TestNG Plugin in eclipse
5.Maven plugin in eclipse if not already present

Once you have imported the project in eclipse please follow the following steps:
1.Go to command line
2.Execute the following maven command(inside the project folder where pom.xml file is located): 
mvn clean install -DskipTests


How can I run tests?
Please go to testng.xml in runner folder and run as TESTNG Suite so that the reports folder can be created.
testng.xml file is present here ---> /WebFEAutomation/runners/testng.xml

In case we run it using maven the extent report wont be created as the listener logic is present there.

Where can I see the test execution outputs?
Extent Test Report ----> /WebFEAutomation/reports/extentreport.html
(In case there is any failure then the screenshot will be attached in the report)
TestNG Test Report -----> /WebFEAutomation/test-output/index.html
Log file -----> /WebFEAutomation/logs/prints.log


# WebUIUsingSelenium
