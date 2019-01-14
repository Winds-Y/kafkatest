package application;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Created by: Changze
 * Date: 2019/1/14
 * Time: 16:36
 */
//https://blog.csdn.net/galen2016/article/details/80752147
public class TestKafkaProducer {
    private static KafkaProducer<String,String> producer;
    //在服务器上创建的topic：testkafka
    private final static String topic="testkafka";
    private TestKafkaProducer(){
        Properties properties=new Properties();
        //kafka服务器的ip地址
        properties.put("bootstrap.servers", "192.168.237.6:9092");
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer= new KafkaProducer<>(properties);
    }
    private void produce(){
        for(int i=0;i<10;i++){
            String data="hahahahaha : "+i;
            producer.send(new ProducerRecord<>(topic, i + "", data));
            System.out.println(data);
        }
        producer.close();
    }
    public static void main(String[] args) {
        new TestKafkaProducer().produce();
    }
}
