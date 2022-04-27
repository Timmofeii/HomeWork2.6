import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer
/*1.Написатьконсольныйвариантклиент\серверногоприложения,
вкоторомпользовательможет писать сообщения как на клиентской стороне,
так и на серверной.Т.е.если на клиентской стороне написать «Привет»,нажать Enter,
то сообщение должно передаться на сервер и там отпечататься в консоли.
 Если сделать то же самое на серверной стороне, сообщение, соответственно,
  передаётся клиенту и печатается у него в консоли. Есть одна особенность,

   которую нужно учитывать: клиент или сервер может написать несколько сообщений подряд.
    Такую ситуацию необходимо корректно обработать. Разобраться с кодом с занятия — он является фундаментом проекта-чата.
    ВАЖНО! Сервер общается только с одним клиентом, т.е. не нужно запускать цикл, который будет ожидать второго/третьего/n-го клиента.{
 */ {
    public static void main(String[] args) throws IOException {
        Socket socket = null;

        try (ServerSocket serverSocket = new ServerSocket(8189)) {//try with resources
            System.out.println("Сервер запущен, ожидаем подключения...");
            socket = serverSocket.accept();//блокирующий метод
            System.out.println("Клиент подключился");
            final DataInputStream in = new DataInputStream(socket//получение сообщений on client
                    .getInputStream());


            // Нужно переписать код и сделать схожим с клиенским
            // +добавить отдельный поток для отправки сообщений
            // без блоккаи завершение и прописать заверение при написании "/end"

            final DataOutputStream out = new DataOutputStream(socket.getOutputStream());//для отправления сообщений

            final Thread threadServ = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        while (true) {
                            final String message = in.readUTF();//блок. метод до получения смс от клиента- не можем отправить смс мы
                            if ("/end".equalsIgnoreCase(message)) {
                                break;

                            }
                            System.out.println("Сообщение от клиента: " + message);

                        }
                    } catch (IOException e) {
                        e.printStackTrace();

                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();


            System.out.println("Сервер остановлен");
        }

    }
}



