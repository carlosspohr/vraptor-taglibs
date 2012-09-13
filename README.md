vraptor-tablibs
===============

A simple useful collection of taglibs to simplify HTML coding.

Usage
-----

An innocent JSP page:

```
<%@ taglib prefix="v"	uri="http://vraptor.caelum.com.br/taglibs"%>

<select name="foo.bar">
	<option value='1' <v:checked test="${obj.attr eq 1}"/>>Foo</option>
	<option value='2' <v:checked test="${obj.attr eq 2}"/>>Bar</option>
</select>
<br/>
<!-- radio and/or checkboxes -->

<input type="radio" <v:checked test="${obj.attr eq true}"/> value="1"/>
<br/>
<input type="checkbox" <v:checked test="${obj.attr eq true}"/> value="0"/>
```

If you want use user images from Gravatar API you can do this:

```
<v:gravatar email="${usuarioMaroto.email}" cssClass="foo" imageSize="50"/>
```

;)
