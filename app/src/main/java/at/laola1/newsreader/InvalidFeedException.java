package at.laola1.newsreader; // TODO PK this is part of feed parsing, belongs in same package

public class InvalidFeedException extends Throwable { // TODO PK do not extend Throwable!
    public InvalidFeedException(String message) {
        super(message);
    }
}
