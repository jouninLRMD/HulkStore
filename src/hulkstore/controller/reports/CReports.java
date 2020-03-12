package hulkstore_.controller.reports;

import hulkstore_.model.dto.inventory_.KardexDetailView;
import hulkstore_.model.dto.inventory_.KardexView;
import hulkstore_.model.dto.product_.ProductView;
import hulkstore_.model.dto.store_.StoreDto;
import java.awt.Font;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.BaseColor;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Reports Controller.
 * 
 * Generate inventory control reports.
 * 
 * @author Luis Mercado
 * @version 0.1
 * @since 2020-03-10
 */
public final class CReports
{    
    private Document document;
    private PdfPTable pdfTable;
    
    /**
     * Generate the initial part of the report.
     * 
     * @param title
     * @param subtitle
     * @param header 
     */
    private void startReport(String title, String subtitle,  ArrayList<String> header) {
        String path = fileChooser();
        
        if(!path.equals("")) {
            try {
                document = new Document(PageSize.A4, 40, 40, 20, 20);
                PdfWriter.getInstance(document, new FileOutputStream(path));
                document.open();
               
                Paragraph endLine = new Paragraph("\n", FontFactory.getFont(FontFactory.COURIER_BOLD, 10, BaseColor.BLACK));
                
                // START OF REPORT                
                Paragraph paragraph = new Paragraph(title + "\n\n", FontFactory.getFont(FontFactory.COURIER_BOLD, 14, BaseColor.BLACK));
                paragraph.setAlignment(Element.ALIGN_CENTER);
                document.add(paragraph);
                
                pdfTable = new PdfPTable(1);
                pdfTable.setTotalWidth(PageSize.A4.getWidth() - 80);
                pdfTable.setLockedWidth(true);
                
                PdfPCell pdfCell = new PdfPCell(new Paragraph(subtitle, FontFactory.getFont(FontFactory.COURIER, 17, Font.BOLD, BaseColor.WHITE)));
                pdfCell.setFixedHeight(40);
                pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                pdfCell.setBackgroundColor(BaseColor.BLACK);
                pdfTable.addCell(pdfCell);
                document.add(pdfTable);
                document.add(endLine);
                
                // TABLE                
                pdfTable = new PdfPTable(1);
                pdfTable.setTotalWidth(PageSize.A4.getWidth() - 80);
                pdfTable.setLockedWidth(true);
                
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat format = new SimpleDateFormat("dd 'de' MMMM 'del' yyyy 'a las' hh:mm a");
              
                pdfCell = new PdfPCell(new Paragraph("Inventarios Kardex - Reporte\nGenerado el " + format.format(calendar.getTime()), FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK)));
                pdfCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                pdfCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                pdfCell.setBackgroundColor(BaseColor.WHITE);
                pdfCell.setPadding(10);
                pdfTable.addCell(pdfCell);
                document.add(pdfTable);
                document.add(endLine);
                
                // TABLE HEADER
                pdfTable = new PdfPTable(header.size());
                pdfTable.setTotalWidth(PageSize.A4.getWidth() - 80);
                pdfTable.setLockedWidth(true);

                for (String column : header) {
                    pdfCell = new PdfPCell(new Paragraph(column, FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD, BaseColor.WHITE)));
                    pdfCell.setFixedHeight(30);
                    pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    pdfCell.setBackgroundColor(new BaseColor(51, 139, 204));
                    pdfCell.setBorderColor(BaseColor.WHITE);
                    pdfCell.setBorderWidth(1);
                    pdfTable.addCell(pdfCell);
                }
                
            } catch (DocumentException | IOException e) {}            
        }
    }
    
    /**
     * Generate the final part of the report.
     */
    private void finishReport() {
        try {
            document.add(pdfTable);
            document.close();
            pdfTable = null;
            JOptionPane.showMessageDialog(null, "Se ha generado el reporte.", "REPORTE", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (DocumentException ex) {}
    }

    /**
     * Select the location where the report is saved.
     * 
     * @return String
     */
    private String fileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        
        fileChooser.setDialogTitle("Guardar Reporte PDF");
        FileFilter filter = new FileNameExtensionFilter("Archivo PDF", "pdf");
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(filter);
        
        int option = fileChooser.showSaveDialog(null);

        if(option == JFileChooser.APPROVE_OPTION)
        {
            String path = fileChooser.getSelectedFile().getAbsoluteFile() + "";
        
            if(!path.substring(path.length() - 3, path.length()).equals("pdf"))
                { path = path + ".pdf"; }
            
            return path;
            
        } else { return ""; }
    }
    
    /**
     * Generate the report of the store_s.
     * 
     * @param store_s 
     */
    public void generateStoreReport(StoreDto[] store_s) {        
                    
        ArrayList <String> header = new ArrayList <> ();            
        header.add("Código");
        header.add("Nombre Tienda");
        header.add("Ubicación");
        
        startReport("REPORTE DE TIENDAS ACTIVAS", "TIENDAS", header);
        
        // DATA TABLE
        for(int i = 0; i < store_s.length; i++)
        {
            for(int j = 0; j <= 2; j++)
            {
                String fact = "";
                switch(j){
                    case 0:
                        fact = String.valueOf(store_s[i].getStoreId());
                        break;

                    case 1:
                        fact = store_s[i].getStoreName();
                        break;

                    case 2:
                        fact = store_s[i].getAddress();
                        break;
                }

                PdfPCell pdfCell = new PdfPCell(new Paragraph(fact, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD, BaseColor.BLACK)));
                pdfCell.setFixedHeight(20);
                pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfCell.setVerticalAlignment(Element.ALIGN_MIDDLE);


                if(i%2 == 0)
                {
                    pdfCell.setBackgroundColor(BaseColor.WHITE);
                    pdfCell.setBorderColor(BaseColor.WHITE);
                }
                else
                {
                    pdfCell.setBackgroundColor(new BaseColor(228, 239, 248));
                    pdfCell.setBorderColor(new BaseColor(228, 239, 248));
                }
                pdfTable.addCell(pdfCell);

            }
        }
        
        finishReport();
    }

    /**
     * Generate the report of the product_s.
     * 
     * @param viewProducts 
     */
    public void generateProductReport(ProductView[] viewProducts) {
        
        ArrayList <String> header = new ArrayList <> ();            
        header.add("Código");
        header.add("Nombre Producto");
        header.add("Unidad");
        
        startReport("REPORTE DE PRODUCTOS ACTIVOS", "PRODUCTOS", header);
        
        // DATA TABLE
        for(int i = 0; i < viewProducts.length; i++)
        {
            for(int j = 0; j <= 2; j++)
            {
                String fact = "";
                switch(j){
                    case 0:
                        fact = String.valueOf(viewProducts[i].getProductId());
                        break;

                    case 1:
                        fact = viewProducts[i].getProductName();
                        break;

                    case 2:
                        fact = String.valueOf(viewProducts[i].getUnityDescription());
                        break;
                }

                PdfPCell pdfCell = new PdfPCell(new Paragraph(fact, FontFactory.getFont(FontFactory.COURIER, 8, Font.BOLD, BaseColor.BLACK)));
                pdfCell.setFixedHeight(20);
                pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfCell.setVerticalAlignment(Element.ALIGN_MIDDLE);


                if(i%2 == 0)
                {
                    pdfCell.setBackgroundColor(BaseColor.WHITE);
                    pdfCell.setBorderColor(BaseColor.WHITE);
                }
                else
                {
                    pdfCell.setBackgroundColor(new BaseColor(228, 239, 248));
                    pdfCell.setBorderColor(new BaseColor(228, 239, 248));
                }
                pdfTable.addCell(pdfCell);

            }
        }
        
        finishReport();
    }
    
    /**
     * Generate the inventory_ report.
     * 
     * @param inventory_View
     * @param inventory_DetailView 
     */
    public void generateKardexReport(KardexView inventory_View, KardexDetailView[] inventory_DetailView)
    {
        String path = fileChooser();
        
        if(!path.equals(""))
        {
            try
            {
                document = new Document(PageSize.A4.rotate(), 40, 40, 20, 20);                                
                PdfWriter.getInstance(document, new FileOutputStream(path));                
                document.open();
                
                Paragraph endLine = new Paragraph("\n", FontFactory.getFont(FontFactory.COURIER_BOLD, 10, BaseColor.BLACK));
                             
                // START OF REPORT      
                Paragraph paragraph = new Paragraph("INVENTORY DE ENTRADA Y SALIDA DEL ALMACÉN \n\n", FontFactory.getFont(FontFactory.COURIER_BOLD, 14, BaseColor.BLACK));
                paragraph.setAlignment(Element.ALIGN_CENTER);
                document.add(paragraph);
                
                // Kardex Header
                PdfPTable table = new PdfPTable(3);
                table.setTotalWidth(PageSize.A4.getHeight() - 80);
                table.setLockedWidth(true);
                
                // Title                               
                PdfPCell cell = new PdfPCell(new Paragraph("DATOS PRINCIPALES", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD, BaseColor.BLACK)));
                cell.setColspan(3);
                cell.setFixedHeight(30);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBackgroundColor(new BaseColor(210, 210, 210));
                cell.setBorderColor(BaseColor.BLACK);
                cell.setBorderWidth(1);
                table.addCell(cell);
                
                // Content                
                cell = new PdfPCell(new Paragraph("Codigo de Producto: " + inventory_View.getProductId(), FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK)));
                cell.setFixedHeight(20);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorderColor(BaseColor.WHITE);
                cell.setBorderWidth(1);
                table.addCell(cell);
                
                cell = new PdfPCell(new Paragraph("Nombre de Producto: " + inventory_View.getProductName(), FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK)));
                cell.setFixedHeight(20);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorderColor(BaseColor.WHITE);
                cell.setBorderWidth(1);
                table.addCell(cell);
                
                cell = new PdfPCell(new Paragraph("Unidad: " + inventory_View.getUnityDescription(), FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK)));
                cell.setFixedHeight(20);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorderColor(BaseColor.WHITE);
                cell.setBorderWidth(1);
                table.addCell(cell);
                
                cell = new PdfPCell(new Paragraph("Codigo de Almacen:  " + inventory_View.getStoreId(), FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK)));
                cell.setFixedHeight(20);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorderColor(BaseColor.WHITE);
                cell.setBorderWidth(1);
                table.addCell(cell);
                
                cell = new PdfPCell(new Paragraph("Nombre de Almacen:  " + inventory_View.getStoreName(), FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK)));
                cell.setFixedHeight(20);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorderColor(BaseColor.WHITE);
                cell.setBorderWidth(1);
                table.addCell(cell);
                
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat format = new SimpleDateFormat("yy'/'MM'/'dd hh:mm a");
                
                cell = new PdfPCell(new Paragraph("Fecha de Generación: " + format.format(calendar.getTime()), FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK)));
                cell.setFixedHeight(20);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorderColor(BaseColor.WHITE);
                cell.setBorderWidth(1);
                table.addCell(cell);
                
                document.add(table);
                document.add(endLine);
                
                // Quantity and price                
                table = new PdfPTable(4);
                table.setTotalWidth(PageSize.A4.getHeight()/2);
                table.setLockedWidth(true);
                
                cell = new PdfPCell(new Paragraph("Cantidad:", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD, BaseColor.BLACK)));
                cell.setFixedHeight(20);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBackgroundColor(new BaseColor(210, 210, 210));
                cell.setBorderColor(BaseColor.BLACK);
                cell.setBorderWidth(1);
                table.addCell(cell);
                
                cell = new PdfPCell(new Paragraph(String.valueOf(inventory_View.getQuantity()), FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK)));
                cell.setFixedHeight(20);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorderColor(BaseColor.BLACK);
                cell.setBorderWidth(1);
                table.addCell(cell);
                
                cell = new PdfPCell(new Paragraph("Precio Unitario:", FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD, BaseColor.BLACK)));
                cell.setFixedHeight(20);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBackgroundColor(new BaseColor(210, 210, 210));
                cell.setBorderColor(BaseColor.BLACK);
                cell.setBorderWidth(1);
                table.addCell(cell);
                
                cell = new PdfPCell(new Paragraph(String.valueOf(inventory_View.getUnityValue()), FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK)));
                cell.setFixedHeight(20);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBackgroundColor(BaseColor.WHITE);
                cell.setBorderColor(BaseColor.BLACK);
                cell.setBorderWidth(1);
                table.addCell(cell);
                
                document.add(table);
                document.add(endLine);
                
                // Kardex_Details                
                table = new PdfPTable(9);
                table.setTotalWidth(PageSize.A4.getHeight() - 80);
                table.setLockedWidth(true);
                
                //  Title                
                cell = new PdfPCell(new Paragraph("Codigo", FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK)));
                cell.setRowspan(2);
                cell.setFixedHeight(20);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBackgroundColor(new BaseColor(210, 210, 210));
                cell.setBorderColor(BaseColor.BLACK);
                cell.setBorderWidth(1);
                table.addCell(cell);
                
                cell = new PdfPCell(new Paragraph("Fecha", FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK)));
                cell.setRowspan(2);
                cell.setFixedHeight(20);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBackgroundColor(new BaseColor(210, 210, 210));
                cell.setBorderColor(BaseColor.BLACK);
                cell.setBorderWidth(1);
                table.addCell(cell);
                
                cell = new PdfPCell(new Paragraph("Documento", FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK)));
                cell.setRowspan(2);
                cell.setFixedHeight(20);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBackgroundColor(new BaseColor(210, 210, 210));
                cell.setBorderColor(BaseColor.BLACK);
                cell.setBorderWidth(1);
                table.addCell(cell);
                
                cell = new PdfPCell(new Paragraph("Nº Doc.", FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK)));
                cell.setRowspan(2);
                cell.setFixedHeight(20);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBackgroundColor(new BaseColor(210, 210, 210));
                cell.setBorderColor(BaseColor.BLACK);
                cell.setBorderWidth(1);
                table.addCell(cell);
                
                cell = new PdfPCell(new Paragraph("Operación", FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK)));
                cell.setRowspan(2);
                cell.setFixedHeight(20);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBackgroundColor(new BaseColor(210, 210, 210));
                cell.setBorderColor(BaseColor.BLACK);
                cell.setBorderWidth(1);
                table.addCell(cell);
                
                cell = new PdfPCell(new Paragraph("Movimiento", FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK)));
                cell.setColspan(3);
                cell.setFixedHeight(20);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBackgroundColor(new BaseColor(210, 210, 210));
                cell.setBorderColor(BaseColor.BLACK);
                cell.setBorderWidth(1);
                table.addCell(cell);
                
                cell = new PdfPCell(new Paragraph("Obs.", FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK)));
                cell.setRowspan(2);
                cell.setFixedHeight(20);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBackgroundColor(new BaseColor(210, 210, 210));
                cell.setBorderColor(BaseColor.BLACK);
                cell.setBorderWidth(1);
                table.addCell(cell);
                
                cell = new PdfPCell(new Paragraph("Cantidad", FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK)));
                cell.setFixedHeight(20);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBackgroundColor(new BaseColor(210, 210, 210));
                cell.setBorderColor(BaseColor.BLACK);
                cell.setBorderWidth(1);
                table.addCell(cell);
                
                cell = new PdfPCell(new Paragraph("Val. Uni.", FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK)));
                cell.setFixedHeight(20);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBackgroundColor(new BaseColor(210, 210, 210));
                cell.setBorderColor(BaseColor.BLACK);
                cell.setBorderWidth(1);
                table.addCell(cell);
                
                cell = new PdfPCell(new Paragraph("Total", FontFactory.getFont(FontFactory.COURIER, 10, BaseColor.BLACK)));
                cell.setFixedHeight(20);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setBackgroundColor(new BaseColor(210, 210, 210));
                cell.setBorderColor(BaseColor.BLACK);
                cell.setBorderWidth(1);
                table.addCell(cell);

                // Content                
                for(int i = 0; i < inventory_DetailView.length; i++)
                {
                    for(int j = 0; j < 9; j++)
                    {
                        String operation = "";
                        if(inventory_DetailView[i].getOperation() == 1) { operation = "Entrada"; }
                        else if(inventory_DetailView[i].getOperation() == 0) { operation = "Salida"; }
                        
                        String fact = "";                        
                        switch(j){
                            case 0: fact = String.valueOf(inventory_DetailView[i].getDetailId()); break;

                            case 1: fact = inventory_DetailView[i].getKardexDetailDate(); break;

                            case 2: fact = inventory_DetailView[i].getDocumentDescription(); break;
                                
                            case 3: fact = String.valueOf(inventory_DetailView[i].getDocumentNumber()); break;

                            case 4: fact = operation; break;

                            case 5: fact = String.valueOf(inventory_DetailView[i].getQuantity()); break;
                                
                            case 6: fact = String.valueOf(inventory_DetailView[i].getUnityValue()); break;

                            case 7: fact = String.valueOf(inventory_DetailView[i].getTotalValue()); break;

                            case 8: fact = inventory_DetailView[i].getObservations(); break;
                        }
                        
                        cell = new PdfPCell(new Paragraph(fact, FontFactory.getFont(FontFactory.COURIER, 8, BaseColor.BLACK)));
                        cell.setFixedHeight(20);
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

                        if(i%2 == 0)
                        {
                            cell.setBackgroundColor(BaseColor.WHITE);
                            cell.setBorderColor(BaseColor.WHITE);
                            
                        } else {
                            cell.setBackgroundColor(new BaseColor(230, 230, 230));
                            cell.setBorderColor(new BaseColor(230, 230, 230));
                        }
                        
                        table.addCell(cell);
                    }
                }
                document.add(table);
                document.close();                
            }
            catch (DocumentException | IOException ex) {}
        }
    }    
}