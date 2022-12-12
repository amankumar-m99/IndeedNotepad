package model.notepad;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.PrinterResolution;

import controller.MainPageController;
import javafx.print.PrinterJob;
import notepadutils.NotepadSavedStatus;

public class NotepadPrinter {
	public static void print(MainPageController mainPageController) {
		
		/*
		PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
		aset.add(MediaSizeName.ISO_A4);
		aset.add(new PrinterResolution(300, 300, PrinterResolution.DPI));
		aset.add(new MediaPrintableArea(2, 2, 210 - 4, 297 - 4, MediaPrintableArea.MM));
		String FILE_NAME ="";
		try {
			FILE_NAME = mainPageController.getNotepad().getFile().getCanonicalPath();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		FileInputStream textStream;
		try {
			textStream = new FileInputStream(FILE_NAME);
			DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
			Doc mydoc = new SimpleDoc(textStream, flavor, null);

			   PrintService[] services = PrintServiceLookup.lookupPrintServices(flavor, aset);
			   PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();

			   if(services.length == 0) {
			       if(defaultService == null) {
			       } else {
			            DocPrintJob job = defaultService.createPrintJob();
			            try {
							job.print(mydoc, null);
						} catch (PrintException e) {
						}
			       }

			    } else {

			       //built in UI for printing you may not use this
			       PrintService service = ServiceUI.printDialog(null, 200, 200, services, defaultService, flavor, aset);
			        if (service != null)
			        {
			           DocPrintJob job = service.createPrintJob();
			           try {
						job.print(mydoc, null);
					} catch (PrintException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        }

			    }
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
		/*
		PrinterJob printerJob = PrinterJob.createPrinterJob();
		if (printerJob != null && printerJob.showPrintDialog(root.getScene().getWindow())){
		    boolean success = printerJob.printPage(notepad);
		    if (success) {
		    	printerJob.endJob();
		    }
		}
		*/
		DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
		PrinterJob printerJob = PrinterJob.createPrinterJob();
	    PrintService printService;
	    if(printerJob.showPrintDialog(mainPageController.root.getScene().getWindow()))
	    {
	        printService = PrintServiceLookup.lookupDefaultPrintService();
	        DocFlavor docType = DocFlavor.INPUT_STREAM.AUTOSENSE;
	        DocPrintJob printJob = printService.createPrintJob();
	        final byte[] byteStream = mainPageController.getNotepad().getText().getBytes();
	        DocAttributeSet das = new HashDocAttributeSet();
	        Doc documentToBePrinted = new SimpleDoc(new ByteArrayInputStream(byteStream), docType, das);
	        try {
				printJob.print(documentToBePrinted, null);
			} catch (PrintException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	}
}
