package com.ibs.actions;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Namespaces;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@Action(value = "/generatepdf", results = {
		@Result(name = "SUCCESS", location = "/success.jsp", type = "stream", params = { "contentType",
				"application/pdf", "inputName", "inputStream", "contentDisposition", "inline;filename=\"example.pdf\"",
				"bufferSize", "1024" }),
		@Result(name = "ERROR", location = "/error.jsp") })
@Namespaces(value = { @Namespace("/") })
public class PdfGenerationAction implements ServletResponseAware {
	 private HttpServletResponse response;
	private String name, address, dob, email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String execute() {

		// Set response headers
		 response.setContentType("application/pdf");
		 response.setHeader("Content-disposition", "attachment; filename=example.pdf");

		try {
			// Create PDF document
			Document document = new Document();
			//OutputStream out = new FileOutputStream("C://Users//lenovo//OneDrive//Documents//pdf/output.pdf");
			 OutputStream out=response.getOutputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PdfWriter.getInstance(document, baos);

			// Generate content
			document.open();
			// Add some content to the document
			document.add(new Paragraph("Name: " + name));
			document.add(new Paragraph("Email: " + email));
			document.add(new Paragraph("DOB: " + dob));
			document.add(new Paragraph("Address: " + address));

			// Close the document
			document.close();

			// Write PDF to response output stream
			document.close();
			out.write(baos.toByteArray());
			out.flush();
			out.close();
			// response.flushBuffer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERROR";
		}
		return "SUCCESS";
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {

		this.response=response;
	}

}
