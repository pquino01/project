package model;
import java.awt.Desktop; 
import java.io.File; 
import java.io.FileNotFoundException; 
import java.io.IOException;
import java.io.PrintWriter; 
import java.io.UnsupportedEncodingException; 
import java.text.DecimalFormat; 

/** Classe responsable de generar i obrir el fitxer HTML que conte el percentatge de mans guanyades i folds en una partida */
public class HTMLGenerator { 
  
    private int nfPreFlop; 
    private int mjnfPreFlop; 
      
    private int nfFlop; 
    private int mjnfFlop; 
      
    private int nfTurn; 
    private int mjnfTurn; 
      
    private int nfRiver; 
    private int mjnfRiver; 
      
    private int mgPreFlop;  
    private int mgFlop; 
    private int mgTurn; 
    private int mgRiver;  
    private int mgShowdown;
    private int contmans;

      
    public HTMLGenerator() { 
          
        nfPreFlop = 0; 
        mjnfPreFlop = 0; 
          
        nfFlop = 0; 
        mjnfFlop = 0; 
          
        nfTurn = 0; 
        mjnfTurn = 0; 
          
        nfRiver = 0; 
        mjnfRiver = 0; 
          
        mgPreFlop = 0;          
        mgFlop = 0;         
        mgTurn = 0;          
        mgRiver = 0;          
        mgShowdown = 0; 
        contmans = 0;
          
    } 
    
    /** Funcio que genera amb les dades que guarda un HTML a la direccio i amb el nom que indica la url d'entrada
     * @param url String que conté la direcció on es guarda el fitxer HTML (que sera la carpeta files del projecte) i el nom que tindra (que sera grafica)
     */
    public void generateHTML(String url) { 
  
        PrintWriter pw = null; 
        try { 
              
            pw = new PrintWriter(url, "UTF-8"); 
            double d; 
            DecimalFormat df = new DecimalFormat("#.##"); 
            pw.println("<HTML>"); 
              
            pw.println("<HEAD>"); 
            pw.println("<TITLE>" + "% Folds i Mans Guanyades" + "</TITLE>"); 
            pw.println("</HEAD>"); 
              
            pw.println("<BODY>"); 
              
            pw.println("<CENTER>"); 
            pw.println("<TABLE BORDER=1 CELLPADDING=0 CELLSPACING=0 WIDTH=350"); 
                  
            //Nombre Folds en PreFlop 
            pw.println("<TR>"); 
            pw.println("<TD><CENTER><B>"); 
            pw.println("% Folds en PreFlop"); 
            pw.println("</B></CENTER></TD>"); 
                  
            if (mjnfPreFlop > 0) { 
                d = (double)nfPreFlop/mjnfPreFlop*100; 
                pw.println("<TD><CENTER>"+df.format(d)+"</CENTER></TD>"); 
            } else { 
                pw.println("<TD><CENTER>"+"0"+"</CENTER></TD>"); 
            } 
            pw.println("</TR>"); 
              
            //Nombre Folds en Flop 
            pw.println("<TR>"); 
            pw.println("<TD><CENTER><B>"); 
            pw.println("% Folds en Flop"); 
            pw.println("</B></CENTER></TD>"); 
                  
            if (mjnfFlop > 0) { 
                d = (double)nfFlop/mjnfFlop*100; 
                pw.println("<TD><CENTER>"+df.format(d)+"</CENTER></TD>"); 
            } else { 
                pw.println("<TD><CENTER>"+"0"+"</CENTER></TD>"); 
            } 
            pw.println("</TR>"); 
              
            //Nombre Folds en Turn 
            pw.println("<TR>"); 
            pw.println("<TD><CENTER><B>"); 
            pw.println("% Folds en Turn"); 
            pw.println("</B></CENTER></TD>"); 
                  
            if (mjnfTurn > 0) { 
                d = (double)nfTurn/mjnfTurn*100; 
                pw.println("<TD><CENTER>"+df.format(d)+"</CENTER></TD>"); 
            } else { 
                pw.println("<TD><CENTER>"+"0"+"</CENTER></TD>"); 
            } 
            pw.println("</TR>"); 
              
            //Nombre Folds en River 
            pw.println("<TR>"); 
            pw.println("<TD><CENTER><B>"); 
            pw.println("% Folds en River"); 
            pw.println("</B></CENTER></TD>"); 
                  
            if (mjnfRiver > 0) { 
                d = (double)nfRiver/mjnfRiver*100; 
                pw.println("<TD><CENTER>"+df.format(d)+"</CENTER></TD>"); 
            } else { 
                pw.println("<TD><CENTER>"+"0"+"</CENTER></TD>"); 
            } 
            pw.println("</TR>"); 
              
            //Nombre Mans Guanyades PreFlop 
            pw.println("<TR>"); 
            pw.println("<TD><CENTER><B>"); 
            pw.println("% Mans Guanyades en PreFlop"); 
            pw.println("</B></CENTER></TD>"); 
                  
            if (mgPreFlop > 0) { 
                d = (double)mgPreFlop/contmans*100; 
                pw.println("<TD><CENTER>"+df.format(d)+"</CENTER></TD>"); 
            } else { 
                pw.println("<TD><CENTER>"+"0"+"</CENTER></TD>"); 
            } 
            pw.println("</TR>"); 
              
            //Nombre Mans Guanyades Flop 
            pw.println("<TR>"); 
            pw.println("<TD><CENTER><B>"); 
            pw.println("% Mans Guanyades en Flop"); 
            pw.println("</B></CENTER></TD>"); 
                  
            if (mgFlop > 0) { 
                d = (double)mgFlop/contmans*100; 
                pw.println("<TD><CENTER>"+df.format(d)+"</CENTER></TD>"); 
            } else { 
                pw.println("<TD><CENTER>"+"0"+"</CENTER></TD>"); 
            } 
            pw.println("</TR>"); 
              
            //Nombre Mans Guanyades Turn 
            pw.println("<TR>"); 
            pw.println("<TD><CENTER><B>"); 
            pw.println("% Mans Guanyades en Turn"); 
            pw.println("</B></CENTER></TD>"); 
                  
            if (mgTurn > 0) { 
                d = (double)mgTurn/contmans*100; 
                pw.println("<TD><CENTER>"+df.format(d)+"</CENTER></TD>"); 
            } else { 
                pw.println("<TD><CENTER>"+"0"+"</CENTER></TD>"); 
            } 
            pw.println("</TR>"); 
              
            //Nombre Mans Guanyades River 
            pw.println("<TR>"); 
            pw.println("<TD><CENTER><B>"); 
            pw.println("% Mans Guanyades en River"); 
            pw.println("</B></CENTER></TD>"); 
                  
            if (mgRiver > 0) { 
                d = (double)mgRiver/contmans*100; 
                pw.println("<TD><CENTER>"+df.format(d)+"</CENTER></TD>"); 
            } else { 
                pw.println("<TD><CENTER>"+"0"+"</CENTER></TD>"); 
            } 
            pw.println("</TR>"); 
              
            //Nombre Mans Guanyades Showdown 
            pw.println("<TR>"); 
            pw.println("<TD><CENTER><B>"); 
            pw.println("% Mans Guanyades en Showdown"); 
            pw.println("</B></CENTER></TD>"); 
                  
            if (mgShowdown > 0) { 
                d = (double)mgShowdown/contmans*100; 
                pw.println("<TD><CENTER>"+df.format(d)+"</CENTER></TD>"); 
            } else { 
                pw.println("<TD><CENTER>"+"0"+"</CENTER></TD>"); 
            } 
            pw.println("</TR>"); 
                  
            pw.println("</TABLE>"); 
            pw.println("</CENTER>"); 
            pw.println("</BODY></HTML>"); 
              
        } catch (FileNotFoundException e) { 
              
            e.printStackTrace(); 
              
        } catch (UnsupportedEncodingException e) { 
              
            e.printStackTrace(); 
              
        } finally { 
              
            if (pw != null) pw.close(); 
              
        } 
          
    } 
  
    public void actualitzaFPreFlop(int i, int j) { 
          
        nfPreFlop += i;
        mjnfPreFlop += j; 
          
    } 
  
    public void actualitzaFFlop(int i, int j) { 
          
        nfFlop += i; 
        mjnfFlop += j; 
          
    } 
  
    public void actualitzaFTurn(int i, int j) { 
  
        nfTurn += i; 
        mjnfTurn += j; 
          
    } 
      
    public void actualitzaFRiver(int i, int j) { 
  
        nfRiver += i; 
        mjnfRiver += j; 
          
    } 
      
    public void actualitzaMGPreFlop() { 
  
        mgPreFlop ++; 
       
          
    } 
      
    public void actualitzaMGFlop() { 
  
        mgFlop++;  
          
    } 
      
    public void actualitzaMGTurn() { 
  
        mgTurn ++; 
       
          
    } 
      
    public void actualitzaMGRiver() { 
  
        mgRiver ++;
        
          
    } 
      
    public void actualitzaMGShowdown() { 
  
        mgShowdown ++;
       
          
    } 
    
    public void actualitzaTotalMans(int i) {
    	this.contmans = i;
    }
    
    /**
     * Funcio que obre en l'explorador que tenim per defecte el fitxer HTML que s'ha generat previament.
     * @param url String que conté la direcció on s'ha guardat la grafica.html
     */
    public void openHTML(String url) { 
          
        File htmlFile = new File(url); 
        try { 
        	//System.out.println(htmlFile.getCanonicalPath());
            Desktop.getDesktop().browse(htmlFile.toURI()); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
          
    } 
  
      
} 
