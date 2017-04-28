package network;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import controller.ControladorBoto;

/** Classe responsable de crear un socket, establir connexió amb el socket de Server i tancar el socket una vegada acabada la operació*/
public class MessageService {
	private InformacioFitxers infof;
	private Socket socket;
	private DataOutputStream doStream;
	private DataInputStream diStream;
	private ObjectInputStream oiStream;
	@SuppressWarnings("unused")
	private ControladorBoto controller;
	private static final String IP = "localhost";
	private static final int PORT = 55555;
	
	
	
	public MessageService(ControladorBoto controller) {
		this.controller = controller;
	}
	
	/**
	 * Funcio que llegeix la peticio de client, envia a client les dades necessaries i espera que Server li envii lo que necessita, per despres tancar el socket
	 * @param message1 Conte o be el nom d'usuari en cas d'accedir o login, o bé el nom de fitxer que volem descarregar en cas de reproduir partida
	 * @param message2 Conté o be el pass de l'usuari en cas d'accedir o login
	 * @param mode Conté el mode que estem: 0=Login, 1=Registrar, 2=LlistaFitxers i 3=DescarregaFitxer
	 * @return Retorna o bé un OK o un KO si estem en mode 0,1 o 2 per saber si hi ha hagut problemes, o bé una URL per saber on està el fitxer descarregat 
	 */
	public String sendMessage(String message1, String message2, int mode) { 
		try {
			socket = new Socket(IP, PORT);
			doStream = new DataOutputStream(socket.getOutputStream());
			if (mode == 0 || mode == 1) {
				diStream = new DataInputStream(socket.getInputStream());
				doStream.writeUTF(message1);
				doStream.writeUTF(message2);
				doStream.writeInt(mode);
				String aux = diStream.readUTF();
				if (aux.equals("KO")) {
					System.out.println("KO");
				} else {
					System.out.println("OK");
				}
				doStream.flush();
				socket.close();
				return aux;
			} else if (mode == 2) {
				doStream.flush();
				doStream.writeUTF(message1);
				doStream.writeUTF(message2);
				doStream.writeInt(mode);
				oiStream = new ObjectInputStream(socket.getInputStream());
				infof = (InformacioFitxers) oiStream.readObject();
				socket.close();
				return "OK";
			} else if (mode == 3) { 
				
				doStream.writeUTF(message1);
				doStream.writeUTF(message2);
				doStream.writeInt(mode);
	
			      // receive file
				
				/*byte[] mybytearray = new byte[1000000];
			    InputStream is = socket.getInputStream();
			    FileOutputStream fos = new FileOutputStream("./files/"+message1);
			    BufferedOutputStream bos = new BufferedOutputStream(fos);
			    int bytesRead = is.read(mybytearray, 0, mybytearray.length);
			    bos.write(mybytearray, 0, bytesRead);
			    bos.close();
			    */
				byte[] buffer = new byte[1024 * 50];
				BufferedInputStream in = 
			               new BufferedInputStream(socket.getInputStream());

			          BufferedOutputStream out = 
			               new BufferedOutputStream(new FileOutputStream("./files/"+message1));
			               
			          int len = 0;
			          while ((len = in.read(buffer)) > 0) {
			               out.write(buffer, 0, len);
			          }
			          in.close();
			          out.flush();
			          out.close();
				socket.close();
	
			    return "./files/"+message1;
				
			} else {
				socket.close();
				return "KO";
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			return "KO";
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return "KO";
		} 
	}
	
	public String[] getListaFitxers() {
		
		return infof.getLlista();
	}
	
}
