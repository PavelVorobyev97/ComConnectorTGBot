package diodswitchertg;

import jssc.SerialPort;
import jssc.SerialPortException;

public class ComConnection {

    public static SerialPort serialPort;
    private PortReader pr;
    public String output;

    public ComConnection() {
        ///Передаём в конструктор имя порта
        serialPort = new SerialPort("/dev/ttyACM0");
        pr = new PortReader();
        try {
            //Открываем порт
            serialPort.openPort();
            //Выставляем параметры
            serialPort.setParams(SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            //Включаем аппаратное управление потоком
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN
                    | SerialPort.FLOWCONTROL_RTSCTS_OUT);
            //Устанавливаем ивент лисенер и маску
            serialPort.addEventListener(pr, SerialPort.MASK_RXCHAR);
            //send("Test!");
        } catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }

    public static void send(String input) {
        try {
            if (serialPort.isOpened()) {
                System.out.println("Port is opened!" + " Message: " + input);
                serialPort.writeString(input);
            }            
        } catch (SerialPortException ex) {
            System.out.println(ex.getMessage());
        }

    }

    

}
