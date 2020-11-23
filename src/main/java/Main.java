import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.*;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.logging.Logger;

public class Main
{
    public static void main(String[] args) throws  Exception
    {
        final  String TOPIC = "k1-topic";
        final  String BOOTSTRAP_SERVERS = "172.30.5.235:9092,172.30.5.236:9092,172.30.5.237:9092";
            Properties props = new Properties();
            props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
            props.put(StreamsConfig.APPLICATION_ID_CONFIG, "bigd");
            props.put("key.serializer", StringSerializer.class.getName());
            props.put("value.serializer", StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);

        ProducerRecord<String, String> record = new ProducerRecord<String, String>("bigd", "bigd", "Another one");
        try
        {
            /*
            producer.send(record, new Callback() {
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    Logger logger= (Logger) LoggerFactory.getLogger(Main.class);
                    if (e== null) {
                        logger.info("Successfully received the details as: \n" +
                                "Topic:" + recordMetadata.topic() + "\n" +
                                "Partition:" + recordMetadata.partition() + "\n" +
                                "Offset" + recordMetadata.offset() + "\n" +
                                "Timestamp" + recordMetadata.timestamp());
                    }

                    else {
                        logger.warning(("Can't produce,getting error"));

                    }
                }
            });
             */
            producer.send(record);
            producer.flush();
            producer.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
