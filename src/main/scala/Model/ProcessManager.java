package Model;
import java.io.*;


public class ProcessManager {
    private Process process;
    private BufferedWriter writer;
    private BufferedReader reader;

    // Function to start the executable
    public void startExecutable(String executablePath) throws IOException {
        process = new ProcessBuilder(executablePath).start();
        writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
        reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
    }

    // Function to write to the executable
    public void writeToExecutable(String input) throws IOException {
        if (writer != null) {
            writer.write(input);
            writer.flush();
        } else {
            throw new IllegalStateException("Process is not started or writer is not initialized.");
        }
    }

    // Function to read from the executable
    public String readFromExecutable() throws IOException {
        if (reader != null) {
            return reader.readLine();
        } else {
            throw new IllegalStateException("Process is not started or reader is not initialized.");
        }
    }

    // Function to terminate the executable
    public void terminateExecutable() {
        if (process != null) {
            process.destroy();
            process = null;
        }
    }

    public static void main(String[] args) {
        ProcessManager manager = new ProcessManager();
        String executablePath = "path/to/your/executable"; // Replace with your executable's path

        try {
            // Start the executable
            manager.startExecutable(executablePath);

            // Write to the executable
            manager.writeToExecutable("Hello from Java!\n");

            // Read response from the executable
            String response = manager.readFromExecutable();
            System.out.println("Response from executable: " + response);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Terminate the executable
            manager.terminateExecutable();
        }
    }
}
