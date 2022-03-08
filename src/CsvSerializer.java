public interface CsvSerializer<T> {

    T serialize(String[] line);
}
