<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<html>
  <head>
    <title>Welcome Page</title>
    <script type="text/javascript">
      // JavaScript validation function to disallow special characters in name field
      function validateName() {
        var name = document.getElementById("name").value;
        var regex = /^[a-zA-Z ]*$/; // Regular expression to match only letters and spaces
        if (!regex.test(name)) {
          alert("Name should contain only letters and spaces.");
          return false;
        }
        return true;
      }
    </script>
  </head>
  <body>
    <h1>Welcome to the application</h1>
<s:form action="/generatepdf" method="post" >
<s:textfield name="name" label="name"></s:textfield>
<s:textfield name="address" label="address"></s:textfield>
<s:textfield name="dob" label="dob"></s:textfield>
<s:textfield name="email" label="email"></s:textfield>
<s:submit value="Save"></s:submit>

</s:form>
  </body>
</html>