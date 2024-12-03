package codingblackfemales.dictionary;

public class MessageHeaderDecoder {

    // Example fields of a message header
    private byte[] headerBytes; // Raw bytes for the header
    private int messageLength;
    private int messageType;
    private int sequenceNumber;

    // Constructor to initialize the decoder with the header bytes
    public MessageHeaderDecoder(byte[] headerBytes) {
        this.headerBytes = headerBytes;
        decodeHeader(); // Decodes the header upon instantiation
    }

    // Decodes the message header from the raw byte array
    private void decodeHeader() {
        if (headerBytes == null || headerBytes.length < 10) {
            throw new IllegalArgumentException("Invalid header data.");
        }

        // Example decoding logic
        // Assuming the first 4 bytes represent the message length
        this.messageLength = ((headerBytes[0] & 0xFF) << 24)
                | ((headerBytes[1] & 0xFF) << 16)
                | ((headerBytes[2] & 0xFF) << 8)
                | (headerBytes[3] & 0xFF);

        // The next 2 bytes represent the message type
        this.messageType = ((headerBytes[4] & 0xFF) << 8) | (headerBytes[5] & 0xFF);

        // The next 4 bytes represent the sequence number
        this.sequenceNumber = ((headerBytes[6] & 0xFF) << 24)
                | ((headerBytes[7] & 0xFF) << 16)
                | ((headerBytes[8] & 0xFF) << 8)
                | (headerBytes[9] & 0xFF);
    }

    // Getter methods to retrieve decoded values
    public int getMessageLength() {
        return messageLength;
    }

    public int getMessageType() {
        return messageType;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    // A method to return decoded header info as a string
    public String getDecodedHeaderInfo() {
        return "Message Length: " + messageLength + ", Message Type: " + messageType + ", Sequence Number: " + sequenceNumber;
    }
}

