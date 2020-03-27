package multithreading;

public class ThreadDemo {
  public static void show() {
    System.out.println(Thread.currentThread().getName());
    Thread thread = new Thread(new DownloadFileTask());
    thread.start();
  }
}
