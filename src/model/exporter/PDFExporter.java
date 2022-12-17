package model.exporter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

public class PDFExporter {

	private String path;

	public PDFExporter(String path) {
		this.path = path;
	}

	public void createPDF() {
		PDDocument pdfdoc= new PDDocument();
		PDDocumentInformation info = pdfdoc.getDocumentInformation();
		info.setAuthor("Indeed Notepad");
		DateTimeFormatter yearStr = DateTimeFormatter.ofPattern("yyyy");
		DateTimeFormatter monthStr = DateTimeFormatter.ofPattern("MM");
		DateTimeFormatter dayStr = DateTimeFormatter.ofPattern("dd");
		LocalDateTime now = LocalDateTime.now();
		int year = Integer.parseInt(yearStr.format(now));
		int month = Integer.parseInt(monthStr.format(now));
		int day = Integer.parseInt(dayStr.format(now));
		Calendar date = new GregorianCalendar();
		date.set(year, month, day);
		info.setCreationDate(date);
		info.setCreator("Indeed Notepad");
		info.setTitle("PDF Exporter");
		info.setSubject("Testing PDF exporter");
		try {
			PDPage page = new PDPage();
			PDPageContentStream contentStream = new PDPageContentStream(pdfdoc, page);
			contentStream.beginText();
//			contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
			contentStream.newLineAtOffset(25, 500);
			String text = "This is the sample document and we are adding content to it.";
			contentStream.showText(text);
			contentStream.endText();
			System.out.println("Content added");
			contentStream.close();
			pdfdoc.save(path);
			pdfdoc.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
