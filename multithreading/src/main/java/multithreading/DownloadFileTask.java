package multithreading;

public class DownloadFileTask implements Runnable {
  public void run() {
    System.out.println("Downloading a file: "
    + Thread.currentThread().getName());
  }
}
