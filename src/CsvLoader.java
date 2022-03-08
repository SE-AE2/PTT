import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvLoader {
    private String filePath;
    private String encoding;
    List<String[]> lines = new ArrayList<>();

    public CsvLoader(String csvFilePath, String encoding) {
        filePath = csvFilePath;
        this.encoding = encoding;      
    }

    public void loadCsv() throws IOException {
        DataInputStream fin = new DataInputStream(new FileInputStream(filePath));
        BufferedReader br = new BufferedReader(new InputStreamReader(fin, encoding));

        String line;
        while ((line = br.readLine()) != null) {
            String[] attribute = line.split(",");
            lines.add(attribute);
        }
        br.close();
    }

    public <T> List<T> loadCsv(CsvSerializer<T> serializer) {
        try {
            loadCsv();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<T> list = new ArrayList<>();
        for (String[] line : lines) {
            list.add(serializer.serialize(line));
        }
        return list;
    }

    public <T> void saveCsv(List<T> list, CsvDeserializer<T> deserializer) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filePath, false));
            list.forEach(t -> writer.println(deserializer.deserialize(t)));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
