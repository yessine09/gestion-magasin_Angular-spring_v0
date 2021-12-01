package tn.esprit.spring.pdfgenerator;
import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;

import tn.esprit.spring.entity.Facture;

public class FacturePDFExporter {
	private List<Facture> listFactures;

	public FacturePDFExporter(List<Facture> listFactures) {
		super();
		this.listFactures = listFactures;
	}
	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);
		
	Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);
		
		cell.setPhrase(new Phrase("Facture ID", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Facture Date", font));
		table.addCell(cell);
		
		
		cell.setPhrase(new Phrase("Montant Facture", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("MontRemise", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Client", font));
		table.addCell(cell);

	}

	private void writeTableData(PdfPTable table) {
		for (Facture facture : listFactures) {
			table.addCell(String.valueOf(facture.getIdFacture()));
		
			table.addCell(String.valueOf(facture.getDateFacture()));
			//table.addCell(facture.getActive().toString());
			//table.addCell(String.valueOf(facture.isEnabled()));
			table.addCell(String.valueOf(facture.getMontantFacture()));
			table.addCell(String.valueOf(facture.getMontantRemise()));
			table.addCell(facture.getClient().getNom()+""+facture.getClient().getPrenom());
			
		}
		

	}

	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		document.setMargins(40, 40, 40, 40);
		PdfWriter.getInstance(document, response.getOutputStream() );

		document.open();
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setColor(Color.BLUE);
		font.setSize(18);

		Paragraph title = new Paragraph("List of all factures", font);
		document.add(title);

		PdfPTable table = new PdfPTable(5); 
		table.setWidthPercentage(100);
		table.setSpacingBefore(15);
		table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f , 1.5f });
		
		
		
		writeTableHeader(table);
		writeTableData(table);
		
		document.add(table);

		document.close();
	}





}
