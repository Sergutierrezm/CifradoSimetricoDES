package cifradosimetricodes;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.util.Scanner;

public class CifradoSimetricoDES {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {

            //Genera la clave DEES
            KeyGenerator keygen = KeyGenerator.getInstance("DES");
            SecretKey clave = keygen.generateKey();

            //Crea cifrador
            Cipher desCipher = Cipher.getInstance("DES");

            Scanner sc = new Scanner(System.in);
            boolean salir = false;

            //menu
            while (!salir) {
                System.out.println("***MENÚ***");
                System.out.println("1. Cifrar fichero");
                System.out.println("2. Descifrar fichero");
                System.out.println("3. Salir");
                System.out.print("Elige una opción: ");
                int opcionMenu = sc.nextInt();
                sc.nextLine();

                switch (opcionMenu) {

                    case 1:
                        System.out.println("Nombre del fichero que deseas cifrar: ");
                        String ficheroOriginal = "src/data/" + sc.nextLine();
                        System.out.println("¿Que nombre le quieres poner al fichero cifrado?");
                        String ficheroCifrado = "src/data" + sc.nextLine();

                        byte[] datosOriginal = leerFichero(ficheroOriginal);
                        desCipher.init(Cipher.ENCRYPT_MODE, clave);
                        byte[] datosCifrados = desCipher.doFinal(datosOriginal);
                        escribirFichero(ficheroCifrado, datosCifrados);
                        System.out.println("Fichero cifrado correctamente");
                        break;

                    case 2:
                        System.out.println("Indica el nombre del fichero que quieres descifrar");
                        String paraDescifrar = "src/data" + sc.nextLine();

                        byte[] datosEncriptados = leerFichero(paraDescifrar);
                        desCipher.init(Cipher.DECRYPT_MODE, clave);
                        byte[] datosDescifrados = desCipher.doFinal(datosEncriptados);
                        System.out.println("Contenido descifrado: ");
                        System.out.println(new String(datosDescifrados));
                        break;

                    case 3:
                        salir = true;
                        break;

                    default:
                        System.out.println("Opcion no valida, vuelve a indicar una opcion");

                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    // metodo para leer el fichero en byte
    public static byte[] leerFichero(String nombre) throws IOException {

        File file = new File(nombre);
        byte[] datos = new byte[(int) file.length()];
        FileInputStream fis = new FileInputStream(file);
        fis.read(datos);
        fis.close();
        return datos;

    }

    //metodo para escribir el fichero en byte
    public static void escribirFichero(String nombre, byte[] datos) throws IOException {

        FileOutputStream fos = new FileOutputStream(nombre);
        fos.write(datos);
        fos.close();

    }

}
