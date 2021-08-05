
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.header.Header
import org.apache.kafka.common.header.internals.RecordHeader
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.kafka.support.Acknowledgment
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.support.MessageBuilder
import java.io.BufferedReader
import java.io.FileReader
import java.nio.ByteBuffer
import java.time.OffsetDateTime
import java.util.*
import java.util.stream.Collectors


fun createProducer(brokers: String): Producer<String, String> {
        val props = Properties()
        props["bootstrap.servers"] = brokers
        props["client.id"] = "test-producer"
        props["key.serializer"] = StringSerializer::class.java.canonicalName
        props["value.serializer"] = StringSerializer::class.java.canonicalName
        return KafkaProducer<String, String>(props)
    }


fun main(){
    val producer = createProducer("kafka-01.dev.oboz.app:9092")
    val lines = BufferedReader((FileReader("src/main/resources/as.json"))).use { it.readText() }
    println(lines)

    val producerRecord = ProducerRecord<String,String>("notificator.in",0,"key-1",lines)

    producerRecord.headers().add(RecordHeader("spring_json_header_types", ("{\"Uuid\":\"java.util.UUID\",\"messageType\":\"java.lang.String\",\"kafka_acknowledgment\":\"java.lang.String\",\"Version\":\"java.lang.String\",\"uber-trace-id\":\"java.lang.String\",\"StartedAt\":\"java.lang.String\",\"messageSent\":\"java.lang.String\",\"contentType\":\"java.lang.String\"}".toByteArray())))
    MessageBuilder.withPayload(lines).build()
        producer.send(producerRecord)

//spring_json_header_types	{"Uuid":"java.util.UUID","messageType":"java.lang.String","Version":"java.lang.String","uber-trace-id":"java.lang.String","StartedAt":"java.lang.String","messageSent":"java.lang.String","contentType":"java.lang.String"}
    producer.flush()
    println("SUCCESS")
}