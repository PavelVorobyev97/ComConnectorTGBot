
package diodswitchertg;

import static diodswitchertg.ComConnection.serialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

class PortReader implements SerialPortEventListener {

        public void serialEvent(SerialPortEvent event) {
            if (event.isRXCHAR() && event.getEventValue() > 0) {
                try {
                    //Получаем ответ от устройства, обрабатываем данные и т.д.
                    String data = serialPort.readString(event.getEventValue() * 2);
                    System.out.println(data);
                    
                } catch (SerialPortException ex) {
                    System.out.println(ex);
                }
            }
        }
    }
