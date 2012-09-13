package br.com.caelum.vraptor.taglibs.html;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * A simple class to append the 'checked' attribute on checkbox and/or radiobox
 * elements.
 * 
 * @author Carlos Alberto Junior Spohr Poletto (carlosjrcabello@gmail.com)
 */
public class CheckedTag extends TagSupport {

	private static final long serialVersionUID = 1669960932711639142L;

	protected Boolean test;
	
	@Override
	public int doEndTag() throws JspException {
		
		try {
			String selected = "";
			
			if(this.test != null && this.test.equals(Boolean.TRUE)){
				selected = " checked='checked' ";
			}
			
			this.pageContext.getOut().write(selected);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return super.doEndTag();
	}

	public Boolean getTest() {
		return test;
	}

	public void setTest(Boolean test) {
		this.test = test;
	}
}
