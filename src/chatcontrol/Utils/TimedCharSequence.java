package chatcontrol.Utils;

public class TimedCharSequence implements CharSequence {

	private CharSequence message;
    private int timeoutLimit;
    private long timeoutTime;

    public TimedCharSequence(CharSequence message, int timeoutLimit) {
        this.message = message;
        this.timeoutLimit = timeoutLimit;
        this.timeoutTime = System.currentTimeMillis() + timeoutLimit;
    }

    @Override
    public char charAt(int index) {
        if (System.currentTimeMillis() > timeoutTime)
            throw new RuntimeException("String checking timed out after " + timeoutLimit + " ms");
        
        return message.charAt(index);
    }

    @Override
    public int length() {
        return message.length();
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return new TimedCharSequence(message.subSequence(start, end), timeoutLimit);
    }

    @Override
    public String toString() {
        return message.toString();
    }
}