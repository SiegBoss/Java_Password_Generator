//Generador de contraseñas aleatorias | Random Password Generator

//Librerias | Libraries

import javax.swing.*;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import java.io.FileWriter;

//Clase principal | Main class
public class password_generator {

    //Componentes | Components
    private JPanel form;
    private JButton button_generate;
    private JTextField number_digit;
    private JTextField password;
    private JRadioButton activate;
    private JTextField nametxt;
    private JLabel label;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JSeparator Separator2;
    private JSeparator Separator1;
    private JSeparator Separator3;


    //Variables | Variables
    private static FileWriter writer;
    int index = 0;

    public password_generator() {

        //Boton para generar la contraseña | Button to generate the password
        button_generate.addActionListener(e -> {

            //Obtiene la longitud de la contraseña | Get the length of the password
            String length = number_digit.getText();

            //Verifica si el guardado de la contraseña en un archivo de texto esta activado | Check if saving the password to a text file is enabled
            if (activate.isSelected()) {

                //Obtiene el nombre del archivo | Get the file name
                String file_name = nametxt.getText();

                //Valida que el campo no este vacio | Validate that the field is not empty
                if (Objects.equals(file_name, "")) {
                    JOptionPane.showMessageDialog(null, "Ingrese un nombre de archivo");
                    return;
                }
                //Valida que el campo no tenga caracteres especiales | Validate that the field does not have special characters
                else if (file_name.matches("[^a-zA-Z0-9]*")) {
                    JOptionPane.showMessageDialog(null, "Ingrese un nombre de archivo valido");
                    return;
                } else {

                    //Crear el archivo | Create the file
                    try {
                        file_name += ".txt";
                        writer = new FileWriter(file_name, true);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Error al crear el archivo");
                    }
                }
            }

            //Valida que el usuario ingrese un valor | Validate that the user enters a value
            if (Objects.equals(length, "")) {
                JOptionPane.showMessageDialog(null, "Ingrese un valor");
            }
            //Valida que el usuario no ingrese letras | Validate that the user does not enter letters
            else if (!length.matches("[0-9]*")) {
                JOptionPane.showMessageDialog(null, "Ingrese un numero");
            } else {
                //Convierte el valor a entero | Convert the value to integer
                int length_int = Integer.parseInt(length);

                if (length_int < 12) {
                    //Valida que la longitud sea mayor a 12 pero aun asi genera la contraseña | Validate that the length is greater than 12 but still generates the password
                    JOptionPane.showMessageDialog(null, "Para Generar una Contraseña Segura, Ingrese un valor mayor a 12");
                    String password_generate = generate_password(length_int);
                    password.setText(password_generate);
                } else {
                    //Genera la contraseña | Generate the password
                    String password_generate = generate_password(length_int);
                    password.setText(password_generate);
                }
            }
        });
    }

    //Funcion para generar la contraseña | Function to generate the password
    public String generate_password(int length) {

        //Caracteres que se usaran para generar la contraseña | Characters that will be used to generate the password
        String lower_case = "abcdefghijklmnopqrstuvwxyz";
        String upper_case = lower_case.toUpperCase();
        String numbers = "0123456789";
        String special_characters = "!#$%&@";

        String password_generate = "";
        int num_rand = 0;

        //Genera un numero aleatorio | Generate a random number
        Random rand = new Random();

        //Genera la contraseña | Generate the password
        for (int i = 0; i < length; i++) {

            num_rand = rand.nextInt(1, 5);

            switch (num_rand) {
                case 1:
                    password_generate += lower_case.charAt(rand.nextInt(0, 25));
                    break;
                case 2:
                    password_generate += upper_case.charAt(rand.nextInt(0, 25));
                    break;
                case 3:
                    password_generate += numbers.charAt(rand.nextInt(0, 9));
                    break;
                case 4:
                    password_generate += special_characters.charAt(rand.nextInt(0, 6));
                    break;
            }
        }

        index += 1;

        //Guarda la contraseña en un archivo de texto | Save the password to a text file
        if (activate.isSelected()) {
            try {
                writer.write(index + ".- " + password_generate + "\n");
                writer.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error al escribir en el archivo");
            }
        }

        //Retorna la contraseña | Return the password
        return password_generate;
    }

    //Funcion principal | Main function
    public static void main(String[] args) {
        //Crea la ventana | Create the window
        JFrame frame = new JFrame("Password Generator");
        //Agrega el contenido a la ventana | Add content to the window
        frame.setContentPane(new password_generator().form);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    //Estilos de los componentes | Component styles
    private void createUIComponents() {
        number_digit = new JTextField();
        number_digit.setBorder(BorderFactory.createLineBorder(null, 1));

        nametxt = new JTextField();
        nametxt.setBorder(BorderFactory.createLineBorder(null, 1));

        password = new JTextField();
        password.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

    }
}
