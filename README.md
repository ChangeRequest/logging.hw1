Dependency Management. Practice Task 1
===============
1\. Create Maven multi-module project
---------------

You should create multi-module maven project that will define initial structure
of online store.

Create new maven project with:
* group ID equal to `com.github.YOUR_ACCOUNT_NAME.store`, for example: `com.github.xSAVIKx.store`;
* artifact ID equal to `store-root`;
* properties section with dependencies versions defined as properties, for example: `<jUnit.version>4.12</jUnit.version>`;
* dependency management section with pre-defined test dependencies (`jUnit`, `mockito`);
* plugin management section with pre-defined configuration of following plugins:
  * [maven-compiler-plugin][maven_compiler_plugin] (java source and target should be 1.8);
  * [maven-source-plugin][maven_source_plugin] (sources should not be attached to artifacts);
  * [maven-javadoc-plugin][maven_javadoc_plugin] (javadocs should be generated for public members only).
* filled description tag with valuable project description;
* filled URL, licenses, developers, sections;

See [POM reference][pom_reference] for additional information about POM file structure.

To find correct `groupId` and `artifactId` of dependencies and plugins, use MVN repository web [search][mvn_repository].

2\. Create `model` module
----------------

Create new maven module named `model`.

Define appropriate classes hierarchy that describes store items and related entities.

Reference classes hierarchy:
* `Property` fields:
  * `Key`
  * `Value`
* `Category` fields:
  * `ID`
  * `Title`
  * `Description`
* `Item` fields:
  * `ID`
  * `Title`
  * `Categories`
  * `Price`
  * `Description`
  * `Properties`
* `Catalog` fields:
  * `ID`
  * `Items`
* `Basket` fields:
  * `Items`

3\. Create `storage` module
----------------

Create new maven module named `storage`.

Define appropriate `Storage` interface with Create/Read/Update/Delete(CRUD) methods.
`Storage` interface should use Generics.

`Storage` methods should be based on `ID` field of entities:
* ID field of entity should be filled or generated
* read operation could be performed only using `ID` field
* update operation could be performed only on item with filled `ID` field
* delete operation could be performed only using `ID` field

4\. Create `api` module
----------------

Create new maven module named `api`.

Define appropriate number of Store API interfaces that provides API to access/modify shop entities.

`CategoryRepository`:
* saveOrUpdate
* find
* remove
* getAll

`CatalogRepository`:
* saveOrUpdate
* find
* remove
* getAll

`BasketApi`:
* add `Item`
* getLast `n` items
* remove `Item`
* checkout - return all items with their amount and cleanup basket

Repositories should be `Storage` to get/read/update/delete items.

Create reference implementation of each Store API interface.

Create jUnit tests for your APIs.

5\. Create `in-memory-storage` module
----------------

Create new module named `in-memory-storage`.

Create new implementation of `Storage` interface that will store all items in in-memory cache.

Create jUnit tests for your implementation.

6\. Create demo implementation of your store.
----------------

Create demo `ConsoleStore` implementation, that uses `in-memory-storage` and handles operations using store `api`.

User should be able to:
* show all catalogs
  * show all items within catalog
  * show item details
* put item into basket
* remove item/items from basket
* show items in basket
* show last `n` items that were added to basket
* checkout all items from basket - message with following information should be shown:
```
#  | Title             |   Amount |       Price
1  | Baby toy          |        3 |          24
2  | Small handmade    |        1 |          99
     butterfly         
                                   Total:   172      
```

Create jUnit tests for your implementation (you will probably need to add `system-rules` library to perform IO-related tests).

Configure POM to create executable .jar file (use [maven-shade-plugin][maven_shade_plugin])
[maven_javadoc_plugin]: https://maven.apache.org/plugins/maven-javadoc-plugin/
[maven_source_plugin]: https://maven.apache.org/plugins/maven-source-plugin/
[maven_compiler_plugin]: https://maven.apache.org/plugins/maven-compiler-plugin/
[pom_reference]: https://maven.apache.org/pom.html
[mvn_repository]: http://mvnrepository.com
[maven_shade_plugin]: https://maven.apache.org/plugins/maven-shade-plugin/