package de.jeha.fwj.jgrapht;

public enum ProcessingState {
    NEW,
    ACKNOWLEDGED,
    REJECTED,
    IN_PROGRESS,
    COMPLETED,
    CANCELED
}