package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CsvUtils {

    public static class Credential {
        private final String username;
        private final String password;

        public Credential(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        @Override
        public String toString() {
            return "Username: [" + (username.isEmpty() ? "EMPTY" : username) + "]" +
                   " | Password: [" + (password.isEmpty() ? "EMPTY" : password) + "]";
        }
    }

    public static List<Credential> readCredentials(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        List<Credential> credentials = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            int lineNumber = 0;

            // Skip header
            if ((line = reader.readLine()) != null) {
                lineNumber++;
                System.out.println("🔹 Header skipped: " + line);
            }

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();

                if (line.isEmpty()) {
                    System.err.println("⚠️ Line " + lineNumber + " skipped: blank line");
                    continue;
                }

                String[] parts = line.split(",", -1); // preserve empty fields

                if (parts.length >= 2) {
                    String username = parts[0].replace("\"", "").trim();
                    String password = parts[1].replace("\"", "").trim();

                    Credential credential = new Credential(username, password);
                    credentials.add(credential);
                    System.out.println("✅ Line " + lineNumber + ": " + credential);
                } else {
                    System.err.println("⚠️ Line " + lineNumber + " skipped: malformed format");
                }
            }
        }

        System.out.println("✅ Total credentials loaded: " + credentials.size());
        return credentials;
    }
}
