package org.vaadin.example.smoothscroll;

import com.vaadin.annotations.JavaScript;
import com.vaadin.server.AbstractJavaScriptExtension;
import com.vaadin.server.Scrollable;
import com.vaadin.ui.Component;

@JavaScript("smoothscrollextension.js")
public class SmoothScrollExtension extends AbstractJavaScriptExtension {

	private static final long serialVersionUID = 1L;

	private static final int DEFAULT_DURATION = 1000;

	private Scrollable scrollableComponent;
	private int duration = DEFAULT_DURATION;	

	public SmoothScrollExtension(Scrollable scrollableComponent) {
		this.scrollableComponent = scrollableComponent;
	}
	
	public void scrollTo(Component component) {
		if (component != null && isChildOfPanel(component)) {
			callFunction("scollToComponent", component, getDuration());
		}
	}

	protected boolean isChildOfPanel(final Component component) {
		Component c = component;
		while (c != null) {
			if (scrollableComponent.equals(c.getParent())) return true;
			c = c.getParent();
		}
		return false;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

}
