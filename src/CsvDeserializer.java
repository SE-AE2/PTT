public interface CsvDeserializer<T> {

    String deserialize(T record);
}
