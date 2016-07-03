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


Demo
=======

Demo is available at [sami.app.fi/smoothscroll](http://sami.app.fi/smoothscroll)

Relevant files
=======

While this extension is not yet released in the [Vaadin Directory](https://vaadin.com/directory), you can use it by copying the relevant files from this project to your project.

[SmoothScrollExtension.java](src/main/java/org/vaadin/example/smoothscroll/SmoothScrollExtension.java) is the Java API.

[smoothscrollextension.js](src/main/resources/org/vaadin/example/smoothscroll/smoothscrollextension.js) is the JavaSprint implementation.

As this is implemented as JavaScript-only extension, you can you use it right away (i.e. no need to recompile the widgetset).


Running
=======

To compile the entire project, run "mvn install".

To run the demo application, run "mvn jetty:run" and open http://localhost:8080/ .

