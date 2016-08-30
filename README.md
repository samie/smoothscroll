Smooth Scroll Extension for Vaadin
==============

With this extension you can scroll to specific Component in a [Scrollable](https://vaadin.com/api/7.6.6/com/vaadin/server/Scrollable.html):

	// Create a Panel and use the extension
	Panel panel = new Panel();
	SmoothScrollExtension scrollExtension = new SmoothScrollExtension(panel);


	// Add some components to the Panel ...
	Grid grid = new Grid("Data");
	Button button = new Button("Button");
	Label label = new Label("Label");
	VerticalLayout content = new VerticalLayout(grid, button, label);
	panel.setContent(content);

	// Scroll (smoothly) to the label
	scrollExtension.scrollTo(label);

## Demo

Demo is available at [sami.app.fi/smoothscroll](http://sami.app.fi/smoothscroll)

## Relevant files

While this extension is not yet released in the [Vaadin Directory](https://vaadin.com/directory), you can use it by copying the relevant files from this project to your project.

[SmoothScrollExtension.java](src/main/java/org/vaadin/example/smoothscroll/SmoothScrollExtension.java) is the Java API.

[smoothscrollextension.js](src/main/resources/org/vaadin/example/smoothscroll/smoothscrollextension.js) is the JavaSprint implementation.

As this is implemented as JavaScript-only extension, you can you use it right away (i.e. no need to recompile the widgetset).

## Development instructions 

1. Import to your favourite IDE
2. Run main method of the Server class to launch embedded web server that lists all your test UIs at http://localhost:9998
3. Code and test
  * create UI's for various use cases for your add-ons, see examples. These can also work as usage examples for your add-on users.
  * create browser level and integration tests under src/test/java/
  * Browser level tests are executed manually from IDE (JUnit case) or with Maven profile "browsertests" (mvn verify -Pbrowsertests). If you have a setup for solidly working Selenium driver(s), consider enabling that profile by default.
4. Test also in real world projects, e.g. create a demo project, build a snapshot release (`mvn install`) and use the snapshot build in it.

## Creating releases

1. Push your changes to e.g. Github 
2. Update pom.xml to contain proper SCM coordinates (first time only)
3. Use Maven release plugin (`mvn release:prepare && mvn release:perform`)
4. Upload the ZIP file generated to target/checkout/target to [Vaadin Directory](https://vaadin.com/directory) service (and/or optionally publish your add-on to Maven central)
