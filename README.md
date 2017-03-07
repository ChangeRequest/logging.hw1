Logging. Home Work 1
===============

[![Join the chat at https://gitter.im/changerequest/logging.hw1](https://badges.gitter.im/changerequest/logging.hw1.svg)](https://gitter.im/changerequest/logging.hw1?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

1 SLF4J + Logback
-----------------

 1. Add latest dependencies of SLF4J + Logback.
 2. Add logging messages with TRACE, DEBUG, INFO, WARN, ERROR levels for modules:
    * **in-memory-storage**, on each of CRUD operations
    * **api**, for each repository class and basket api
    * **store**, for each class

NOTE: Your log messages **must** be meaningful have appropriate log level.

2 Custom Logback configuration
------------------------------

Create custom Logback configuration.

* **in-memory-storage**: 
  * should print messages with level TRACE and above to console;
  * should print messages with level WARN and above to file;
* **api**: 
  * should print messages with level INFO and above to console;
  * should print messages with exactly WARN level to HTML file
* **store**:
  * should print messages with DEBUG level to console

3 Rolling File Appender:
------------------------

* **in-memory-storage**:
  * file with WARN level messages should contains only 1 day logs;
  * history for 10 days should be stored;
* **api**:
  * new file should be created if current file size > 1KB
  * you should store at least 10 latest files
