package helpers;

import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.*;

import javax.swing.JPanel;
import javax.swing.table.*;

public class ViewHelpers {
	public static TreeMap<String, String> constructParamsMap(String... params) {
		TreeMap<String, String> map = new TreeMap<String, String>();
		if (params.length % 2 == 0) {
			for (int i = 0; i < params.length; i+=2) {
				map.put(params[i], params[i + 1]);
			}
		}
		else {
			for (int i = 0; i < params.length - 1; i+=2) {
				map.put(params[i], params[i + 1]);
			}
		}
		
		return map;
	}
	
	public static void loadTable(DefaultTableModel tblModel, List<TreeMap<String, String>> table) {
		Vector<String> vector;
		List<Vector<String>> listOfVectors = new LinkedList<Vector<String>>();
		for (TreeMap<String, String> row : table) {
			vector = new Vector<String>();
			for (String col : row.keySet()) {
//				tblModel.addColumn(col);
				vector.add(row.get(col));
			}
			listOfVectors.add(vector);
		}
		for (int i = 0; i < listOfVectors.size(); i++) {
			tblModel.addRow(listOfVectors.get(i));
		}
		System.out.println(table);
	}
	
	public static void clearJTable(DefaultTableModel tblModel) {
		for (int i = tblModel.getRowCount() - 1; i >= 0; i--) {
			tblModel.removeRow(i);
		}
	}

	public static void switchPanels(CardLayout card, JPanel cardpanel, String toPanel) {
		card = (CardLayout) cardpanel.getLayout();
		card.show(cardpanel, toPanel);
	}
	
	public static void nextPanel(CardLayout card, JPanel cardpanel) {
		card = (CardLayout) cardpanel.getLayout();
		card.next(cardpanel);
	}

	public static void previousPanel(CardLayout card, JPanel cardpanel) {
		card = (CardLayout) cardpanel.getLayout();
		card.previous(cardpanel);
//		System.out.println(card);
	}
	
//	public static void printPanel(JPanel panel) {
//		PrinterJob job = PrinterJob.getPrinterJob();
//		job.setJobName("invoice panel");
//		
//		job.setPrintable(new Printable() {
//			@Override
//			public int print(Graphics g, PageFormat pf, int pageNum) {
//				if(pageNum > 0) {
//					return Printable.NO_SUCH_PAGE;
//				}
//				
//				Graphics2D g2 = (Graphics2D)g;
//				g2.translate(pf.getImageableX(), pf.getImageableY());
//				panel.paint(g2);
//				return Printable.PAGE_EXISTS;
//			}
//		});
//		
//		boolean ok = job.printDialog();
//		if(ok) {
//			try {
//				job.print();
//			} catch (PrinterException e2) {
//				// TODO: handle exception
//			}
//		}
//	}
	
	public static void main(String[] args) {
//		int [] a = {1,2,3,4,5,6,7,8,9,10};
//		for (int i = 0; i < a.length; i+=2) {
//			System.out.println(i + " " + (i + 1));
//		}
//		System.out.println(ViewHelpers.constructParamsMap("name", "Muaad", "occupation", "developer", "me", "you"));
	}
}
