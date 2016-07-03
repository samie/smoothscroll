package org.vaadin.example.panelscroll;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of a html page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */
@Theme("mytheme")
@Push
public class PanelScrollSampleUI extends UI {

	@Override
	protected void init(VaadinRequest vaadinRequest) {

		VerticalLayout content = new VerticalLayout();
		content.setSizeFull();
		content.setMargin(true);
		content.setSpacing(true);
		setContent(content);

		// Panel for scrolling content
		Panel panel = new Panel("This is a scrollable Panel");
		content.addComponent(panel);
		panel.setSizeFull();
		content.setExpandRatio(panel, 1f);

		// Content of the panel
		VerticalLayout panelLayout = new VerticalLayout();
		panelLayout.setMargin(true);
		panelLayout.setSpacing(true);
		panel.setContent(panelLayout);

		// Create some Labels
		for (int i = 0; i < 50; i++) {
			Label l = new Label("Label #" + i);
			l.setStyleName(ValoTheme.LABEL_H1);
			panelLayout.addComponent(l);
		}

		// Add extension for smooth scrolling
		SmoothScrollExtension scrollExtension = new SmoothScrollExtension(panel);
		this.addExtension(scrollExtension);

		// Some buttons for testing scrolling...

		// Button for first item scroll
		Button first = new Button("First", e -> {
			scrollExtension.scrollTo(panelLayout.getComponent(0));
			Notification.show("Scroll to the first Label ");
		});
		first.setIcon(FontAwesome.ARROW_UP);

		
		// Button for last item scroll
		Button last = new Button("Last", e -> {
			scrollExtension.scrollTo(panelLayout.getComponent(panelLayout
					.getComponentCount() - 1));
			Notification.show("Scroll to the last Label ");
		});
		last.setIcon(FontAwesome.ARROW_DOWN);
		
		// Button for random item scroll
		Button random = new Button("Random scroll", e -> {
			int i = (int) (Math.random() * 50);
			scrollExtension.scrollTo(panelLayout.getComponent(i));
			Notification.show("Scroll to Label #" + i);
		});
		random.setIcon(FontAwesome.RANDOM);

		// Button for timer based random scrolling
		Button randomTimer = new Button("Start random timer", e -> {
			if (e.getButton().getData() == null) {
				// Start if not running
				Button b = e.getButton();
				Thread t = new Thread(() -> {
					
					// Keep on running until cleared
					b.setData(true); 
					while (b.getData() != null) {
												
						// Must use "access" block to modify UI from another thread.
						getUI().access(() -> {
							
							// Scroll to random label
							int i = (int) (Math.random() * 50);
							scrollExtension.scrollTo(panelLayout
									.getComponent(i));
							
							// Indicate the label in caption
							b.setCaption("Timer running (" + i + ")");
						});
						try {
							Thread.sleep(1500);
						} catch (Exception e1) {
						}
					}
					// Must use "access" block to modify UI from another thread.
					getUI().access(() -> {
						b.setCaption("Start random timer");
					});
				});
				t.start();
			} else {
				// Stop by clearing the flag
				e.getButton().setData(null);
			}
			int i = (int) (Math.random() * 50);
			scrollExtension.scrollTo(panelLayout.getComponent(i));
			Notification.show("Scroll to Label #" + i);
		});
		randomTimer.setIcon(FontAwesome.CLOCK_O);

		HorizontalLayout buttons = new HorizontalLayout(first, last, random, randomTimer);
		buttons.setSpacing(true);
		content.addComponent(buttons);

	}

	@WebServlet(urlPatterns = "/*", name = "PanelScrollSampleUI", asyncSupported = true)
	@VaadinServletConfiguration(ui = PanelScrollSampleUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}
}
