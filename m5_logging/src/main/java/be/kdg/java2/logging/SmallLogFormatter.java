package be.kdg.java2.logging;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class SmallLogFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        LocalDateTime ldt = LocalDateTime.ofInstant(record.getInstant(), ZoneId.systemDefault());
        String formattedDate = ldt.toString();

        String level = record.getLevel().toString();
        String message = MessageFormat.format(record.getMessage(), record.getParameters());

        return String.format("%s Level: %s melding: \"%s\"\n", formattedDate, level, message);
    }
}
