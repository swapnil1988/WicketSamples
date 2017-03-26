package com.beton;

import org.apache.wicket.ajax.AbstractDefaultAjaxBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class JavascriptWicket extends WebPage {
	private static final long serialVersionUID = 1L;
	
	
	private AbstractDefaultAjaxBehavior backButtonDetection;
	
	public JavascriptWicket(final PageParameters parameters) {
		super(parameters);
		
		
		backButtonDetection = new AbstractDefaultAjaxBehavior() {
			
			
			@Override
			protected void respond(AjaxRequestTarget target) {
				String isBackButtonClicked	= getRequestCycle().getRequest().getRequestParameters().getParameterValue("isBackButtonClicked").toString();
				if(isBackButtonClicked.equalsIgnoreCase("true")){
					//user has clicked back button
					
					getSession().invalidateNow();
					
					setResponsePage(Logout.class); 
					
					
				}
			}
		};
		
		add(backButtonDetection);
		
		HiddenField<String> callBackURL = new HiddenField<String>("callBackURL"){
			
			@Override
			protected void onComponentTag(ComponentTag tag) {
				tag.put("value", backButtonDetection.getCallbackUrl()); 
			}
		};
		
		add(callBackURL);
		
		
	
	  
    }
}
