package br.com.caelum.vraptor.taglibs.gravatar;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Generate the image URI in accordance with Gravatar API.
 * 
 * @author Carlos Alberto Junior Spohr Poletto (carlos.spohr@gmail.com).
 */
public class GravatarTag extends TagSupport {

	private static final long serialVersionUID = 1669960932711639142L;

	/**
	 * This attribute represents the 'email' param of gravatar tag.
	 */
	protected String email;
	
	/**
	 * Represents the cssClass content.
	 */
	protected String cssClass;
	
	/**
	 * Represents the element (DOM) id.
	 */
	protected String id;

	/**
	 * Represents the size based on Gravatar API resize.
	 * 
	 * @see ?s= parameter.
	 */
	protected int imageSize = 200;

	/**
	 * This method implements the IMG injection in JSP page content.
	 */
	@Override
	public int doEndTag() throws JspException {
		
		try {
			if(this.getEmail() == null || this.getEmail().isEmpty()){
				this.setEmail("nothing-to-do@gravatar-fail.com");
			}
			
			StringBuilder img = new StringBuilder();
			
			String hash = new MD5().encrypt(this.getEmail());
			
			img.append("<img src='");
			img.append("http://www.gravatar.com/avatar/");
			img.append(hash);
			img.append("?s=");
			img.append(this.imageSize);
			img.append("' ");
			
			if(this.getCssClass() != null && !this.getCssClass().isEmpty()){
				img.append(" class='");
				img.append(this.getCssClass());
				img.append("' ");
			}
			
			if(this.getId() != null && !this.getId().isEmpty()){
				img.append(" id='");
				img.append(this.getId());
				img.append("' ");
			}
			
			img.append(" />");

			this.pageContext.getOut().write(img.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return super.doEndTag();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getImageSize() {
		return imageSize;
	}

	public void setImageSize(int imageSize) {
		this.imageSize = imageSize;
	}
	
	/**
	 * Simple MD5 hash generator for Gravatar API.
	 */
	private final class MD5 {
		private String hex(byte[] array) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		}

		public String encrypt(String word) {
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				
				return this.hex(md.digest(word.getBytes("UTF-8")));
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
}
