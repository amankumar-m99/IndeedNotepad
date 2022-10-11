package model.notepad;

import javafx.print.PrinterJob;

public class NotepadPrinter {
	public static void print() {
		/*
		PrinterJob printerJob = PrinterJob.createPrinterJob();
		if (printerJob != null && printerJob.showPrintDialog(root.getScene().getWindow())){
		    boolean success = printerJob.printPage(notepad);
		    if (success) {
		    	printerJob.endJob();
		    }
		}
		*/
		
		/*
		PrinterJob printerJob = PrinterJob.getPrinterJob();

	    PrintService printService;
	    if(printerJob.showPrintDialog(root.getScene().getWindow()))
	    {
	        printService = printerJob.getPrintService();
	    }
	    DocFlavor docType = DocFlavor.INPUT_STREAM.AUTOSENSE;
	    DocPrintJob printJob = printService.createPrintJob();
	    final byte[] byteStream = notepad.getText().getBytes();
	    Doc documentToBePrinted = new SimpleDoc(new ByteArrayInputStream(byteStream), docType, null);
	    printJob.print(documentToBePrinted, null);
	    */
	}
}
