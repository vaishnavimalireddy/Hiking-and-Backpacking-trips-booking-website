package com.neu.edu.view;

import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.FontSelector;
import com.lowagie.text.pdf.PdfWriter;
import com.neu.edu.POJO.CustomerItems;

public class MyPDFView extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
	     HttpSession session = request.getSession();
		CustomerItems cust = (CustomerItems) session.getAttribute("customeritems");
		
			FontSelector sel = new FontSelector();
			sel.addFont(new Font(Font.TIMES_ROMAN, 30));
			Phrase ph = sel.process("TICKET CONFIRMATION");
			document.add(new Paragraph(ph));
		    
			
			document.add(new Paragraph("   "));
			FontSelector sel1 = new FontSelector();
			sel1.addFont(new Font(Font.NORMAL, 30));
			Phrase ph1 = sel1.process("Trip Name :"+cust.getTrip().getTripname());
			document.add(new Paragraph(ph1));
			
			document.add(new Paragraph("  "));
			
			
			FontSelector sel2 = new FontSelector();
			sel2.addFont(new Font(Font.NORMAL, 20));
			Phrase ph2 = sel2.process("Trip Destination :"+cust.getTrip().getDestination());
			document.add(new Paragraph(ph2));
			
			document.add(new Paragraph("    "));
			
			FontSelector sel3 = new FontSelector();
			sel3.addFont(new Font(Font.NORMAL, 30));
			Phrase ph3 = sel3.process("Trip Duration :"+cust.getTrip().getDuration());
			document.add(new Paragraph(ph3));
			
			
             
			document.add(new Paragraph(" "));
			
		
			
			FontSelector sel4 = new FontSelector();
			sel4.addFont(new Font(Font.NORMAL, 30));
			Phrase ph4 = sel4.process("Trip Date  :"+cust.getTrip().getDate());
			document.add(new Paragraph(ph4));
             
			document.add(new Paragraph("   "));
			
			
			FontSelector sel5 = new FontSelector();
			sel5.addFont(new Font(Font.NORMAL, 30));
			Phrase ph5 = sel5.process("Number of People :"+cust.getNumberofpersons());
			document.add(new Paragraph(ph5));
			
			document.add(new Paragraph(" "));
			
			
			FontSelector sel6 = new FontSelector();
			sel6.addFont(new Font(Font.NORMAL, 30));
			Phrase ph6 = sel6.process("Name  :"+cust.getCustdetails().getName());
			document.add(new Paragraph(ph6));
			
			document.add(new Paragraph(" "));
			
			FontSelector sel7 = new FontSelector();
			sel7.addFont(new Font(Font.NORMAL, 20));
			Phrase ph7 = sel7.process("Email :"+cust.getCustdetails().getEmail());
			document.add(new Paragraph(ph7));
			
			
			document.add(new Paragraph(" "));
			
			FontSelector sel8 = new FontSelector();
			sel8.addFont(new Font(Font.NORMAL, 30));
			Phrase ph8 = sel8.process("Phone Number :"+cust.getCustdetails().getPhonenumber());
			document.add(new Paragraph(ph8));
			
			document.add(new Paragraph(" "));
			
			
			FontSelector sel9 = new FontSelector();
			sel9.addFont(new Font(Font.NORMAL, 30));
			Phrase ph9 = sel9.process("Total Price:"+cust.getTotalPrice());
			document.add(new Paragraph(ph9));
			
			
			document.add(new Paragraph("  "));

		
	}
	
	
	

}
