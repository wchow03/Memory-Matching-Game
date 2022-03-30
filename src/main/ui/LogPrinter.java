package ui;

import model.Event;
import model.EventLog;

// Represents an event log printer
public class LogPrinter {

    // EFFECTS: constructs a LogPrinter object and calls printLog() method
    public LogPrinter(EventLog el) {
        printLog(el);
    }

    // EFFECTS: prints event log to console
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString() + "\n");
        }
    }
}
